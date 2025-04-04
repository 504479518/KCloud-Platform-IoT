/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.seata.server.storage.file.session;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.seata.common.exception.ShouldNeverHappenException;
import org.apache.seata.common.loader.LoadLevel;
import org.apache.seata.common.loader.Scope;
import org.apache.seata.common.util.CollectionUtils;
import org.apache.seata.common.util.StringUtils;
import org.apache.seata.config.ConfigurationFactory;
import org.apache.seata.core.constants.ConfigurationKeys;
import org.apache.seata.core.exception.TransactionException;
import org.apache.seata.core.model.GlobalStatus;
import org.apache.seata.server.session.AbstractSessionManager;
import org.apache.seata.server.session.BranchSession;
import org.apache.seata.server.session.GlobalSession;
import org.apache.seata.server.session.Reloadable;
import org.apache.seata.server.session.SessionCondition;
import org.apache.seata.server.storage.file.ReloadableStore;
import org.apache.seata.server.storage.file.TransactionWriteStore;
import org.apache.seata.server.storage.file.store.FileTransactionStoreManager;
import org.apache.seata.server.store.AbstractTransactionStoreManager;
import org.apache.seata.server.store.SessionStorable;
import org.apache.seata.server.store.TransactionStoreManager;

import static org.apache.seata.common.DefaultValues.DEFAULT_SERVICE_SESSION_RELOAD_READ_SIZE;

/**
 * The type File based session manager.
 *
 */
@LoadLevel(name = "file", scope = Scope.PROTOTYPE)
public class FileSessionManager extends AbstractSessionManager implements Reloadable {

	private static final int READ_SIZE = ConfigurationFactory.getInstance()
		.getInt(ConfigurationKeys.SERVICE_SESSION_RELOAD_READ_SIZE, DEFAULT_SERVICE_SESSION_RELOAD_READ_SIZE);

	/**
	 * The Session map.
	 */
	protected Map<String, GlobalSession> sessionMap = new ConcurrentHashMap<>(64);

	/**
	 * Instantiates a new File based session manager.
	 * @param name the name
	 */
	public FileSessionManager(String name) {
		super(name);
		transactionStoreManager = new AbstractTransactionStoreManager() {
			@Override
			public boolean writeSession(LogOperation logOperation, SessionStorable session) {
				return true;
			}
		};
	}

	/**
	 * Instantiates a new File based session manager.
	 * @param name the name
	 * @param sessionStoreFilePath the session store file path
	 * @throws IOException the io exception
	 */
	public FileSessionManager(String name, String sessionStoreFilePath) throws IOException {
		super(name);
		if (StringUtils.isNotBlank(sessionStoreFilePath)) {
			transactionStoreManager = new FileTransactionStoreManager(sessionStoreFilePath + File.separator + name,
					this);
		}
		else {
			transactionStoreManager = new AbstractTransactionStoreManager() {
				@Override
				public boolean writeSession(LogOperation logOperation, SessionStorable session) {
					return true;
				}
			};
		}
	}

	@Override
	public void reload() {
		restoreSessions();
	}

	@Override
	public void addGlobalSession(GlobalSession session) throws TransactionException {
		CollectionUtils.computeIfAbsent(sessionMap, session.getXid(), k -> {
			try {
				super.addGlobalSession(session);
			}
			catch (TransactionException e) {
				LOGGER.error("addGlobalSession fail, msg: {}", e.getMessage());
			}
			return session;
		});
	}

	@Override
	public GlobalSession findGlobalSession(String xid) {
		return sessionMap.get(xid);
	}

	@Override
	public GlobalSession findGlobalSession(String xid, boolean withBranchSessions) {
		// withBranchSessions without process in memory
		return sessionMap.get(xid);
	}

	@Override
	public void removeGlobalSession(GlobalSession session) throws TransactionException {
		if (sessionMap.remove(session.getXid()) != null) {
			super.removeGlobalSession(session);
		}
	}

	@Override
	public Collection<GlobalSession> allSessions() {
		return sessionMap.values();
	}

	@Override
	public List<GlobalSession> findGlobalSessions(SessionCondition condition) {
		List<GlobalStatus> globalStatuses = null;
		if (null != condition.getStatuses() && condition.getStatuses().length > 0) {
			globalStatuses = Arrays.asList(condition.getStatuses());
		}
		Collection<GlobalSession> list = sessionMap.values();
		List<GlobalStatus> finalGlobalStatuses = globalStatuses;
		return list.parallelStream().filter(globalSession -> {

			if (null != condition.getOverTimeAliveMills() && condition.getOverTimeAliveMills() > 0) {
				if (System.currentTimeMillis() - globalSession.getBeginTime() <= condition.getOverTimeAliveMills()) {
					return false;
				}
			}

			if (!StringUtils.isEmpty(condition.getXid())) {
				// Only one will be found, just add and return
				return Objects.equals(condition.getXid(), globalSession.getXid());
			}

			if (null != condition.getTransactionId() && condition.getTransactionId() > 0) {
				// Only one will be found, just add and return
				return Objects.equals(condition.getTransactionId(), globalSession.getTransactionId());
			}

			if (null != finalGlobalStatuses) {
				return finalGlobalStatuses.contains(globalSession.getStatus());
			}
			// All test pass, add to resp
			return true;
		}).collect(Collectors.toList());
	}

	@Override
	public <T> T lockAndExecute(GlobalSession globalSession, GlobalSession.LockCallable<T> lockCallable)
			throws TransactionException {
		globalSession.lock();
		try {
			return lockCallable.call();
		}
		finally {
			globalSession.unlock();
		}
	}

	private void restoreSessions() {
		final Set<String> removedGlobalBuffer = new HashSet<>();
		final Map<String, Map<Long, BranchSession>> unhandledBranchBuffer = new HashMap<>();

		restoreSessions(true, removedGlobalBuffer, unhandledBranchBuffer);
		restoreSessions(false, removedGlobalBuffer, unhandledBranchBuffer);

		if (!unhandledBranchBuffer.isEmpty()) {
			unhandledBranchBuffer.values().forEach(unhandledBranchSessions -> {
				unhandledBranchSessions.values().forEach(branchSession -> {
					String xid = branchSession.getXid();
					if (removedGlobalBuffer.contains(xid)) {
						return;
					}

					long bid = branchSession.getBranchId();
					GlobalSession found = sessionMap.get(xid);
					if (found == null) {
						// Ignore
						if (LOGGER.isInfoEnabled()) {
							LOGGER.info("GlobalSession Does Not Exists For BranchSession [" + bid + "/" + xid + "]");
						}
					}
					else {
						BranchSession existingBranch = found.getBranch(branchSession.getBranchId());
						if (existingBranch == null) {
							found.add(branchSession);
						}
						else {
							existingBranch.setStatus(branchSession.getStatus());
						}
					}
				});
			});
		}
	}

	private boolean checkSessionStatus(GlobalSession globalSession) {
		GlobalStatus globalStatus = globalSession.getStatus();
		switch (globalStatus) {
			case UnKnown:
			case Committed:
			case CommitFailed:
			case Rollbacked:
			case RollbackFailed:
			case TimeoutRollbacked:
			case TimeoutRollbackFailed:
			case RollbackRetryTimeout:
			case Finished:
				return false;
			default:
				return true;
		}
	}

	private void restoreSessions(boolean isHistory, Set<String> removedGlobalBuffer,
			Map<String, Map<Long, BranchSession>> unhandledBranchBuffer) {
		if (!(transactionStoreManager instanceof ReloadableStore)) {
			return;
		}
		while (((ReloadableStore) transactionStoreManager).hasRemaining(isHistory)) {
			List<TransactionWriteStore> stores = ((ReloadableStore) transactionStoreManager).readWriteStore(READ_SIZE,
					isHistory);
			restore(stores, removedGlobalBuffer, unhandledBranchBuffer);
		}
	}

	private void restore(List<TransactionWriteStore> stores, Set<String> removedGlobalBuffer,
			Map<String, Map<Long, BranchSession>> unhandledBranchBuffer) {
		for (TransactionWriteStore store : stores) {
			TransactionStoreManager.LogOperation logOperation = store.getOperate();
			SessionStorable sessionStorable = store.getSessionRequest();
			switch (logOperation) {
				case GLOBAL_ADD:
				case GLOBAL_UPDATE: {
					GlobalSession globalSession = (GlobalSession) sessionStorable;
					if (globalSession.getTransactionId() == 0) {
						LOGGER.error("Restore globalSession from file failed, the transactionId is zero , xid:"
								+ globalSession.getXid());
						break;
					}
					if (removedGlobalBuffer.contains(globalSession.getXid())) {
						break;
					}
					GlobalSession foundGlobalSession = sessionMap.get(globalSession.getXid());
					if (foundGlobalSession == null) {
						if (this.checkSessionStatus(globalSession)) {
							sessionMap.put(globalSession.getXid(), globalSession);
						}
						else {
							removedGlobalBuffer.add(globalSession.getXid());
							unhandledBranchBuffer.remove(globalSession.getXid());
						}
					}
					else {
						if (this.checkSessionStatus(globalSession)) {
							foundGlobalSession.setStatus(globalSession.getStatus());
						}
						else {
							sessionMap.remove(globalSession.getXid());
							removedGlobalBuffer.add(globalSession.getXid());
							unhandledBranchBuffer.remove(globalSession.getXid());
						}
					}
					break;
				}
				case GLOBAL_REMOVE: {
					GlobalSession globalSession = (GlobalSession) sessionStorable;
					if (globalSession.getTransactionId() == 0) {
						LOGGER.error("Restore globalSession from file failed, the transactionId is zero , xid:"
								+ globalSession.getXid());
						break;
					}
					if (removedGlobalBuffer.contains(globalSession.getXid())) {
						break;
					}
					if (sessionMap.remove(globalSession.getXid()) == null) {
						if (LOGGER.isInfoEnabled()) {
							LOGGER.info("GlobalSession To Be Removed Does Not Exists [" + globalSession.getXid() + "]");
						}
					}
					removedGlobalBuffer.add(globalSession.getXid());
					unhandledBranchBuffer.remove(globalSession.getXid());
					break;
				}
				case BRANCH_ADD:
				case BRANCH_UPDATE: {
					BranchSession branchSession = (BranchSession) sessionStorable;
					if (branchSession.getTransactionId() == 0) {
						LOGGER.error("Restore branchSession from file failed, the transactionId is zero , xid:"
								+ branchSession.getXid());
						break;
					}
					if (removedGlobalBuffer.contains(branchSession.getXid())) {
						break;
					}
					GlobalSession foundGlobalSession = sessionMap.get(branchSession.getXid());
					if (foundGlobalSession == null) {
						unhandledBranchBuffer.computeIfAbsent(branchSession.getXid(), key -> new HashMap<>())
							.put(branchSession.getBranchId(), branchSession);
					}
					else {
						BranchSession existingBranch = foundGlobalSession.getBranch(branchSession.getBranchId());
						if (existingBranch == null) {
							foundGlobalSession.add(branchSession);
						}
						else {
							existingBranch.setStatus(branchSession.getStatus());
						}
					}
					break;
				}
				case BRANCH_REMOVE: {
					BranchSession branchSession = (BranchSession) sessionStorable;
					String xid = branchSession.getXid();
					if (removedGlobalBuffer.contains(xid)) {
						break;
					}
					long bid = branchSession.getBranchId();
					if (branchSession.getTransactionId() == 0) {
						LOGGER.error("Restore branchSession from file failed, the transactionId is zero , xid:"
								+ branchSession.getXid());
						break;
					}
					GlobalSession found = sessionMap.get(xid);
					if (found == null) {
						if (LOGGER.isInfoEnabled()) {
							LOGGER.info("GlobalSession To Be Updated (Remove Branch) Does Not Exists [" + bid + "/"
									+ xid + "]");
						}
					}
					else {
						BranchSession theBranch = found.getBranch(bid);
						if (theBranch == null) {
							if (LOGGER.isInfoEnabled()) {
								LOGGER.info("BranchSession To Be Updated Does Not Exists [" + bid + "/" + xid + "]");
							}
						}
						else {
							found.remove(theBranch);
						}
					}
					break;
				}
				default:
					throw new ShouldNeverHappenException("Unknown Operation: " + logOperation);
			}
		}

	}

	public Map<String, GlobalSession> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, GlobalSession> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public void destroy() {
		transactionStoreManager.shutdown();
	}

}

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
package org.apache.seata.server.cluster.raft;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.alipay.sofa.jraft.Node;
import com.alipay.sofa.jraft.RaftGroupService;
import com.alipay.sofa.jraft.RouteTable;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.option.NodeOptions;
import com.alipay.sofa.jraft.rpc.RpcServer;
import com.codahale.metrics.Slf4jReporter;
import org.apache.commons.io.FileUtils;
import org.apache.seata.config.Configuration;
import org.apache.seata.config.ConfigurationFactory;
import org.apache.seata.core.rpc.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_REPORTER_ENABLED;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_REPORTER_INITIAL_DELAY;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_CLIENT_KEYSTORE_PASSWORD;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_CLIENT_KEYSTORE_PATH;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_CLIENT_KEYSTORE_TYPE;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_ENABLED;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_KMF_ALGORITHM;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_SERVER_KEYSTORE_PASSWORD;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_SERVER_KEYSTORE_PATH;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_SERVER_KEYSTORE_TYPE;
import static org.apache.seata.common.ConfigurationKeys.SERVER_RAFT_SSL_TMF_ALGORITHM;
import static org.apache.seata.common.DefaultValues.DEFAULT_RAFT_SSL_ENABLED;

/**
 */
public class RaftServer implements Disposable, Closeable {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final RaftStateMachine raftStateMachine;

	private final String groupId;

	private final String groupPath;

	private final NodeOptions nodeOptions;

	private final PeerId serverId;

	private final RpcServer rpcServer;

	private RaftGroupService raftGroupService;

	private Node node;

	public RaftServer(final String dataPath, final String groupId, final PeerId serverId, final NodeOptions nodeOptions,
			final RpcServer rpcServer) throws IOException {
		this.groupId = groupId;
		this.groupPath = dataPath + File.separator + groupId;
		// Initialize the state machine
		this.raftStateMachine = new RaftStateMachine(groupId);
		this.nodeOptions = nodeOptions;
		this.serverId = serverId;
		this.rpcServer = rpcServer;
	}

	public void start() throws IOException {
		// Initialization path
		FileUtils.forceMkdir(new File(groupPath));
		// Set the state machine to startup parameters
		nodeOptions.setFsm(this.raftStateMachine);
		// Set the storage path
		// Log, must
		nodeOptions.setLogUri(groupPath + File.separator + "log");
		// Meta information, must
		nodeOptions.setRaftMetaUri(groupPath + File.separator + "raft_meta");
		// Snapshot, optional, is generally recommended
		nodeOptions.setSnapshotUri(groupPath + File.separator + "snapshot");
		boolean reporterEnabled = ConfigurationFactory.getInstance().getBoolean(SERVER_RAFT_REPORTER_ENABLED, false);
		nodeOptions.setEnableMetrics(reporterEnabled);
		// Initialize the raft Group service framework
		this.raftGroupService = new RaftGroupService(groupId, serverId, nodeOptions, rpcServer, true);
		this.node = this.raftGroupService.start(false);
		RouteTable.getInstance().updateConfiguration(groupId, node.getOptions().getInitialConf());
		// Enable SSL authentication for the Raft group if SSL is enabled.
		boolean sslEnabled = ConfigurationFactory.getInstance()
			.getBoolean(SERVER_RAFT_SSL_ENABLED, DEFAULT_RAFT_SSL_ENABLED);
		if (sslEnabled) {
			enableSSL();
		}
		if (reporterEnabled) {
			final Slf4jReporter reporter = Slf4jReporter.forRegistry(node.getNodeMetrics().getMetricRegistry())
				.outputTo(logger)
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS)
				.build();
			reporter.start(ConfigurationFactory.getInstance().getInt(SERVER_RAFT_REPORTER_INITIAL_DELAY, 60),
					TimeUnit.MINUTES);
		}
	}

	public Node getNode() {
		return this.node;
	}

	public RaftStateMachine getRaftStateMachine() {
		return raftStateMachine;
	}

	public PeerId getServerId() {
		return serverId;
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void destroy() {
		Optional.ofNullable(raftGroupService).ifPresent(r -> {
			r.shutdown();
			try {
				r.join();
			}
			catch (InterruptedException e) {
				logger.warn("Interrupted when RaftServer destroying", e);
			}
		});
	}

	private void enableSSL() {
		setSystemProperty("bolt.server.ssl.enable", "true");
		setSystemProperty("bolt.server.ssl.clientAuth", "true");
		setSystemProperty("bolt.client.ssl.enable", "true");

		Configuration instance = ConfigurationFactory.getInstance();
		setSystemProperty("bolt.server.ssl.keystore", instance.getConfig(SERVER_RAFT_SSL_SERVER_KEYSTORE_PATH));
		setSystemProperty("bolt.server.ssl.keystore.password",
				instance.getConfig(SERVER_RAFT_SSL_SERVER_KEYSTORE_PASSWORD));
		setSystemProperty("bolt.server.ssl.keystore.type", instance.getConfig(SERVER_RAFT_SSL_SERVER_KEYSTORE_TYPE));
		setSystemProperty("bolt.server.ssl.kmf.algorithm", instance.getConfig(SERVER_RAFT_SSL_KMF_ALGORITHM));
		setSystemProperty("bolt.client.ssl.keystore", instance.getConfig(SERVER_RAFT_SSL_CLIENT_KEYSTORE_PATH));
		setSystemProperty("bolt.client.ssl.keystore.password",
				instance.getConfig(SERVER_RAFT_SSL_CLIENT_KEYSTORE_PASSWORD));
		setSystemProperty("bolt.client.ssl.keystore.type", instance.getConfig(SERVER_RAFT_SSL_CLIENT_KEYSTORE_TYPE));
		setSystemProperty("bolt.client.ssl.tmf.algorithm", instance.getConfig(SERVER_RAFT_SSL_TMF_ALGORITHM));

		logger.info("Enable ssl communication between raft nodes");
	}

	private void setSystemProperty(String property, String value) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Configuration value for " + property + " cannot be null or empty");
		}
		System.setProperty(property, value);
	}

}

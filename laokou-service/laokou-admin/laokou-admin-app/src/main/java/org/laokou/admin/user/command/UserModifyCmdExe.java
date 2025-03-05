/*
 * Copyright (c) 2022-2025 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.admin.user.command;

import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.user.ability.UserDomainService;
import org.laokou.admin.user.convertor.UserConvertor;
import org.laokou.admin.user.dto.UserModifyCmd;
import org.laokou.admin.user.gatewayimpl.database.UserDeptMapper;
import org.laokou.admin.user.gatewayimpl.database.UserRoleMapper;
import org.laokou.admin.user.model.UserE;
import org.laokou.admin.user.service.extensionpoint.UserParamValidatorExtPt;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 修改用户命令执行器.
 *
 * @author laokou
 */
@Slf4j
@Component
public class UserModifyCmdExe {

	@Autowired
	@Qualifier("modifyUserParamValidator")
	private UserParamValidatorExtPt modifyUserParamValidator;

	private final UserDomainService userDomainService;

	private final TransactionalUtil transactionalUtil;

	private final UserRoleMapper userRoleMapper;

	private final UserDeptMapper userDeptMapper;

	public UserModifyCmdExe(UserDomainService userDomainService, TransactionalUtil transactionalUtil,
			UserRoleMapper userRoleMapper, UserDeptMapper userDeptMapper) {
		this.userDomainService = userDomainService;
		this.transactionalUtil = transactionalUtil;
		this.userRoleMapper = userRoleMapper;
		this.userDeptMapper = userDeptMapper;
	}

	public Flux<Void> executeVoid(UserModifyCmd cmd) throws Exception {
		// 校验参数
		UserE userE = UserConvertor.toEntity(cmd.getCo());
		modifyUserParamValidator.validate(userE);
		return Flux.zip(getUserRoleIds(userE), getUserDeptIds(userE)).map(tuple -> {
			userE.setUserRoleIds(tuple.getT1());
			userE.setUserDeptIds(tuple.getT2());
			return userE;
		}).flatMap(user -> transactionalUtil.executeResultInTransaction(() -> {
			try {
				return userDomainService.update(user);
			}
			catch (Exception e) {
				log.error("未知错误，错误信息：{}", e.getMessage(), e);
				throw new SystemException("S_UnKnow_Error", e.getMessage(), e);
			}
		}));
	}

	private Mono<List<Long>> getUserRoleIds(UserE userE) {
		return Mono.just(userRoleMapper.selectIdsByUserId(userE.getId()));
	}

	private Mono<List<Long>> getUserDeptIds(UserE userE) {
		return Mono.just(userDeptMapper.selectIdsByUserId(userE.getId()));
	}

}

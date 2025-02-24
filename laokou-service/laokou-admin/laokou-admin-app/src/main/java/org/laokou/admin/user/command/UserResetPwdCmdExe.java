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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.user.ability.UserDomainService;
import org.laokou.admin.user.convertor.UserConvertor;
import org.laokou.admin.user.dto.UserResetPwdCmd;
import org.laokou.admin.user.gatewayimpl.database.UserMapper;
import org.laokou.admin.user.model.UserE;
import org.laokou.admin.user.service.extensionpoint.UserParamValidatorExtPt;
import org.laokou.common.extension.BizScenario;
import org.laokou.common.extension.ExtensionExecutor;
import org.laokou.common.i18n.common.exception.ParamException;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.laokou.admin.common.constant.Constant.MODIFY;
import static org.laokou.admin.common.constant.Constant.REST_PWD;
import static org.laokou.common.i18n.common.constant.Constant.SCENARIO;

/**
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserResetPwdCmdExe {

	private final UserDomainService userDomainService;

	private final TransactionalUtil transactionalUtil;

	private final ExtensionExecutor extensionExecutor;

	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	public void executeVoid(UserResetPwdCmd cmd) {
		UserE userE = UserConvertor.toEntity(cmd.getId(), cmd.getPassword());
		extensionExecutor.executeVoid(UserParamValidatorExtPt.class, BizScenario.valueOf(MODIFY, REST_PWD, SCENARIO),
				extension -> {
					try {
						extension.validate(userE, passwordEncoder, userMapper);
					}
					catch (ParamException e) {
						throw e;
					}
					catch (Exception e) {
						log.error("未知错误，错误信息：{}", e.getMessage(), e);
						throw new SystemException("S_UnKnow_Error", e.getMessage(), e);
					}
				});
		transactionalUtil.executeInTransaction(() -> {
			try {
				userDomainService.update(userE);
			}
			catch (Exception e) {
				log.error("未知错误，错误信息：{}", e.getMessage(), e);
				throw new SystemException("S_UnKnow_Error", e.getMessage(), e);
			}
		});
	}

}

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
import org.laokou.admin.user.dto.UserResetPwdCmd;
import org.laokou.admin.user.model.UserE;
import org.laokou.admin.user.service.extensionpoint.UserParamValidatorExtPt;
import org.laokou.common.i18n.common.exception.BizException;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Slf4j
@Component
public class UserResetPwdCmdExe {

	@Autowired
	@Qualifier("resetPwdParamValidator")
	private UserParamValidatorExtPt resetPwdParamValidator;

	private final UserDomainService userDomainService;

	private final TransactionalUtil transactionalUtil;

	public UserResetPwdCmdExe(UserDomainService userDomainService, TransactionalUtil transactionalUtil) {
		this.userDomainService = userDomainService;
		this.transactionalUtil = transactionalUtil;
	}

	public void executeVoid(UserResetPwdCmd cmd) throws Exception {
		UserE userE = UserConvertor.toEntity(cmd.getId(), cmd.getPassword());
		resetPwdParamValidator.validate(userE);
		transactionalUtil.executeInTransaction(() -> {
			try {
				userDomainService.update(userE);
			}
			catch (Exception e) {
				log.error("重置用户密码失败，错误信息：{}", e.getMessage(), e);
				throw new BizException("B_User_RestPwdError", e.getMessage(), e);
			}
		});
	}

}

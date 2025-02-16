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

package org.laokou.admin.role.command;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.role.ability.RoleDomainService;
import org.laokou.admin.role.convertor.RoleConvertor;
import org.laokou.admin.role.dto.RoleSaveCmd;
import org.laokou.admin.role.gatewayimpl.database.RoleMapper;
import org.laokou.admin.role.model.RoleE;
import org.laokou.admin.role.service.extensionpoint.RoleParamValidatorExtPt;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.extension.BizScenario;
import org.laokou.common.extension.ExtensionExecutor;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.springframework.stereotype.Component;

import static org.laokou.admin.common.constant.Constant.ROLE;
import static org.laokou.admin.common.constant.Constant.SAVE;
import static org.laokou.common.i18n.common.constant.Constant.SCENARIO;

/**
 * 保存角色命令执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class RoleSaveCmdExe {

	private final RoleMapper roleMapper;

	private final RoleDomainService roleDomainService;

	private final TransactionalUtil transactionalUtil;

	private final ExtensionExecutor extensionExecutor;

	public void executeVoid(RoleSaveCmd cmd) {
		// 校验参数
		RoleE roleE = RoleConvertor.toEntity(cmd.getCo());
		extensionExecutor.executeVoid(RoleParamValidatorExtPt.class, BizScenario.valueOf(SAVE, ROLE, SCENARIO),
				extension -> extension.validate(roleE, roleMapper));
		roleE.setId(IdGenerator.defaultSnowflakeId());
		transactionalUtil.executeInTransaction(() -> roleDomainService.create(roleE));
	}

}

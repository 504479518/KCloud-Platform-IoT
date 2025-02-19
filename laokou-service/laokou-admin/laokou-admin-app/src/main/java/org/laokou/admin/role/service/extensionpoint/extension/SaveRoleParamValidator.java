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

package org.laokou.admin.role.service.extensionpoint.extension;

import org.laokou.admin.role.gatewayimpl.database.RoleMapper;
import org.laokou.admin.role.model.RoleE;
import org.laokou.admin.role.service.extensionpoint.RoleParamValidatorExtPt;
import org.laokou.common.extension.Extension;
import org.laokou.common.i18n.utils.ParamValidator;

import static org.laokou.admin.common.constant.Constant.*;
import static org.laokou.common.i18n.common.constant.Constant.SCENARIO;

/**
 * @author laokou
 */
@Extension(bizId = SAVE, useCase = ROLE, scenario = SCENARIO)
public class SaveRoleParamValidator implements RoleParamValidatorExtPt {

	@Override
	public void validate(RoleE roleE, RoleMapper roleMapper) {
		ParamValidator.validate(
				// 校验名称
				RoleParamValidator.validateName(roleE, roleMapper, true),
				// 校验数据范围
				RoleParamValidator.validateDataScope(roleE),
				// 校验菜单IDS
				RoleParamValidator.validateMenuIds(roleE),
				// 校验排序
				RoleParamValidator.validateSort(roleE));
	}

}

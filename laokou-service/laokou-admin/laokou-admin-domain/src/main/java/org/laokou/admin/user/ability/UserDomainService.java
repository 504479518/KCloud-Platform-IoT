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

package org.laokou.admin.user.ability;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.user.gateway.*;
import org.laokou.admin.user.model.UserE;
import org.springframework.stereotype.Component;

/**
 * 用户领域服务.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class UserDomainService {

	private final UserGateway userGateway;

	private final UserRoleGateway userRoleGateway;

	private final UserDeptGateway userDeptGateway;

	public void create(UserE userE) throws Exception {
		// 用户名加密
		userE.encryptUsername();
		// 邮箱加密
		userE.encryptMail();
		// 手机号加密
		userE.encryptMobile();
		userGateway.create(userE);
		userRoleGateway.create(userE);
		userDeptGateway.create(userE);
	}

	public void update(UserE userE) throws Exception {
		// 邮箱加密
		userE.encryptMail();
		// 手机号加密
		userE.encryptMobile();
		userGateway.update(userE);
		userRoleGateway.update(userE);
		userDeptGateway.update(userE);
	}

	public void delete(Long[] ids) {
		userGateway.delete(ids);
	}

}

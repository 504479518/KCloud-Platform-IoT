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

package org.laokou.admin.loginLog.gateway;

import org.laokou.admin.loginLog.model.LoginLogE;

/**
 * 登录日志网关【防腐】.
 *
 * @author laokou
 */
public interface LoginLogGateway {

	/**
	 * 新增登录日志.
	 */
	void create(LoginLogE loginLogE);

	/**
	 * 修改登录日志.
	 */
	void update(LoginLogE loginLogE);

	/**
	 * 删除登录日志.
	 */
	void delete(Long[] ids);

}

/*
 * Copyright (c) 2022-2024 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.common.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author laokou
 */
public class UserUtil {

	public static UserDetail user() {
		try {
		return (UserDetail) getAuthentication().getPrincipal();
	} catch (Exception e) {
			return UserDetail.builder().build();
		}
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 用户ID.
	 * @return Long
	 */
	public static Long getUserId() {
		return user().getId();
	}

	/**
	 * 用户名.
	 * @return String
	 */
	public static String getUserName() {
		return user().getUsername();
	}

	/**
	 * 部门ID.
	 * @return Long
	 */
	public static Long getDeptId() {
		return user().getDeptId();
	}

	/**
	 * 部门PATH.
	 * @return Long
	 */
	public static String getDeptPath() {
		return user().getDeptPath();
	}

	/**
	 * 租户ID.
	 * @return Long
	 */
	public static Long getTenantId() {
		return user().getTenantId();
	}

	/**
	 * 数据源名称.
	 * @return String
	 */
	public static String getSourceName() {
		return user().getSourceName();
	}

}

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

package org.laokou.common.mybatisplus.annotation;

import lombok.Getter;

@Getter
public enum DataScope {

	ALL("all", "全部"),

	CUSTOM("custom", "自定义"),

	DEPT_SELF("dept_self", "仅本部门"),

	DEPT("dept", "部门及以下"),

	SELF("self", "仅本人");

	private final String code;

	private final String desc;

	DataScope(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}

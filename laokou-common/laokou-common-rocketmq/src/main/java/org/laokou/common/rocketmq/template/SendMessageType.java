/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
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

package org.laokou.common.rocketmq.template;

import lombok.Getter;

/**
 * @author laokou
 */
@Getter
public enum SendMessageType {

	// @formatter:off
	ASYNC("async", "异步消息"),

	SYNC("SYNC", "同步消息"),

	ONE_WAY("ONE_WAY", "单向消息"),

	TRANSACTION("TRANSACTION", "事务消息");

	private final String code;

	private final String desc;

	SendMessageType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	// @formatter:on

}

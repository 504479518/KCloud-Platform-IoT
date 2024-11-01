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

package org.laokou.common.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.i18n.dto.DefaultDomainEvent;

import static org.laokou.common.domain.constant.MqConstant.LAOKOU_CACHE_TOPIC;
import static org.laokou.common.i18n.common.constant.StringConstant.EMPTY;

/**
 * @author laokou
 */
@Data
@NoArgsConstructor
public class RemoveCacheDomainEvent extends DefaultDomainEvent {

	private String name;

	private String key;

	public RemoveCacheDomainEvent(String name, String key) {
		super(LAOKOU_CACHE_TOPIC, EMPTY);
		this.name = name;
		this.key = key;
	}

}

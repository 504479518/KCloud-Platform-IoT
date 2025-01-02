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

package org.laokou.common.i18n.utils;

import org.laokou.common.i18n.common.exception.SystemException;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author laokou
 */
public final class EnumParser {

	private EnumParser() {
	}

	public static <E extends Enum<E>, O> E parse(Class<E> clazz, Function<E, O> field, O value) {
		if (value == null) {
			return null;
		}
		return Arrays.stream(clazz.getEnumConstants())
			.filter(e -> ObjectUtil.equals(field.apply(e), value))
			.findFirst()
			.orElseThrow(() -> new SystemException("S_Enum_TypeNotExist",
					String.format("枚举类型不存在 - enum class: %s, invalid value: %s", clazz.getName(), value)));
	}

}

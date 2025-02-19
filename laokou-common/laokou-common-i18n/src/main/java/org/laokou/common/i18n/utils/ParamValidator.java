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

package org.laokou.common.i18n.utils;

import org.laokou.common.i18n.common.exception.ParamException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.laokou.common.i18n.common.constant.StringConstant.DROP;
import static org.laokou.common.i18n.common.constant.StringConstant.EMPTY;

/**
 * @author laokou
 */
public final class ParamValidator {

	private ParamValidator() {
	}

	public static void validate(Validate... validates) {
		String validateString = Stream.of(validates)
			.filter(item -> StringUtil.isNotEmpty(item.value))
			.map(item -> item.value)
			.collect(Collectors.joining(DROP));
		if (StringUtil.isNotEmpty(validateString)) {
			throw new ParamException("P_System_ValidateFailed", validateString);
		}
	}

	public static Validate validate() {
		return new Validate();
	}

	public static Validate invalidate(String value) {
		return new Validate(value);
	}

	public static class Validate {

		private final String value;

		public Validate() {
			value = EMPTY;
		}

		public Validate(String value) {
			this.value = value;
		}

	}

}

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

package org.laokou.common.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.laokou.common.crypto.utils.AESUtil;
import org.laokou.common.crypto.utils.RSAUtil;

/**
 * @author laokou
 */
class CryptoTest {

	private static final String USERNAME = "laokou";

	private static final String PASSWORD = "laokou123";

	@Test
	void testRSA() {
		String encryptUsername = RSAUtil.encryptByPublicKey(USERNAME);
		String encryptPassword = RSAUtil.encryptByPublicKey(PASSWORD);
		String decryptUsername = RSAUtil.decryptByPrivateKey(encryptUsername);
		String decryptPassword = RSAUtil.decryptByPrivateKey(encryptPassword);
		Assertions.assertEquals(USERNAME, decryptUsername);
		Assertions.assertEquals(PASSWORD, decryptPassword);
	}

	@Test
	void testAES() {
		String encryptUsername = AESUtil.encrypt(USERNAME);
		String decryptUsername = AESUtil.decrypt(encryptUsername);
		Assertions.assertEquals(USERNAME, decryptUsername);
	}

}

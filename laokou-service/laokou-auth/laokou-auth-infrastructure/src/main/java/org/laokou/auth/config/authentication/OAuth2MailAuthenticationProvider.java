/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
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
package org.laokou.auth.config.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.laokou.auth.common.handler.OAuth2ExceptionHandler;
import org.laokou.common.core.utils.RegexUtil;
import org.laokou.common.i18n.core.StatusCode;
import org.laokou.common.i18n.utils.MessageUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.sensitive.enums.TypeEnum;
import org.laokou.common.sensitive.utils.SensitiveUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import static org.laokou.auth.common.Constant.*;

/**
 * @author laokou
 */
@Slf4j
public class OAuth2MailAuthenticationProvider extends AbstractOAuth2BaseAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return OAuth2MailAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	Authentication login(HttpServletRequest request) {
		// 判断验证码
		String code = request.getParameter(OAuth2ParameterNames.CODE);
		log.info("验证码：{}", code);
		if (StringUtil.isEmpty(code)) {
			throw OAuth2ExceptionHandler.getException(StatusCode.CAPTCHA_NOT_NULL,
					MessageUtil.getMessage(StatusCode.CAPTCHA_NOT_NULL));
		}
		String mail = request.getParameter(MAIL);
		log.info("邮箱：{}", SensitiveUtil.format(TypeEnum.MAIL, mail));
		if (StringUtil.isEmpty(mail)) {
			throw OAuth2ExceptionHandler.getException(StatusCode.MAIL_NOT_NULL,
					MessageUtil.getMessage(StatusCode.MAIL_NOT_NULL));
		}
		boolean isMail = RegexUtil.mailRegex(mail);
		if (!isMail) {
			throw OAuth2ExceptionHandler.getException(StatusCode.MAIL_ERROR,
					MessageUtil.getMessage(StatusCode.MAIL_ERROR));
		}
		// 获取用户信息,并认证信息
		return super.getUserInfo(mail, "", request, code, mail);
	}

	@Override
	AuthorizationGrantType getGrantType() {
		return new AuthorizationGrantType(AUTH_MAIL);
	}

}

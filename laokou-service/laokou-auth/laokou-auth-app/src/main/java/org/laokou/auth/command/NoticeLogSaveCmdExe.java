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

package org.laokou.auth.command;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.laokou.auth.ability.DomainService;
import org.laokou.auth.convertor.NoticeLogConvertor;
import org.laokou.auth.dto.NoticeLogSaveCmd;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class NoticeLogSaveCmdExe {

	private final DomainService domainService;

	private final TransactionalUtil transactionalUtil;

	@DS("domain")
	public void executeVoid(NoticeLogSaveCmd cmd) {
		transactionalUtil
			.executeInTransaction(() -> domainService.createNoticeLog(NoticeLogConvertor.toEntity(cmd.getCo())));
	}

}

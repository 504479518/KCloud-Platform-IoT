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

package org.laokou.flowable.command.task;

import com.baomidou.dynamic.datasource.annotation.DS;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.common.i18n.dto.Result;
import org.laokou.flowable.domain.gateway.TaskGateway;
import org.laokou.flowable.domain.task.Start;
import org.laokou.flowable.dto.task.TaskStartCmd;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DatasourceConstants.TENANT;

/**
 * 任务开始执行器.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TaskStartCmdExe {

	private final TaskGateway taskGateway;

	/**
	 * 执行开始任务流程.
	 * @param cmd 开始任务流程参数
	 */
	@DS(TENANT)
	public Result<Boolean> execute(TaskStartCmd cmd) {
		log.info("开始流程分布式事务 XID：{}", RootContext.getXID());
		taskGateway.start(convert(cmd));
		return Result.of(Boolean.TRUE);
	}

	private Start convert(TaskStartCmd cmd) {
		return Start.builder()
			.instanceName(cmd.getInstanceName())
			.definitionKey(cmd.getDefinitionKey())
			.businessKey(cmd.getBusinessKey())
			.build();
	}

}

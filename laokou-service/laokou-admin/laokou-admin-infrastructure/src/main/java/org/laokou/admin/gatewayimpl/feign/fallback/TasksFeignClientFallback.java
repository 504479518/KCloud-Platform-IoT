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

package org.laokou.admin.gatewayimpl.feign.fallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.dto.resource.*;
import org.laokou.admin.dto.resource.clientobject.StartCO;
import org.laokou.admin.dto.resource.clientobject.TaskCO;
import org.laokou.admin.gatewayimpl.feign.TasksFeignClient;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;

/**
 * @author laokou
 */
@Slf4j
@RequiredArgsConstructor
public class TasksFeignClientFallback implements TasksFeignClient {

    private final Throwable throwable;

    @Override
    public Result<Datas<TaskCO>> list(TaskListQry qry) {
        errLog();
        return Result.of(Datas.of());
    }

    @Override
    public Result<Boolean> audit(TaskAuditCmd cmd) {
        errLog();
        return Result.fail("流程审批失败，请联系管理员");
    }

    @Override
    public Result<Boolean> resolve(TaskResolveCmd cmd) {
        errLog();
        return Result.fail("流程处理失败，请联系管理员");
    }

    @Override
    public Result<StartCO> start(TaskStartCmd cmd) {
        errLog();
        return Result.fail("流程启动失败，请联系管理员");
    }

    @Override
    public Result<String> diagram(String instanceId) {
        errLog();
        return null;
    }

    @Override
    public Result<Boolean> transfer(TaskTransferCmd cmd) {
        errLog();
        return Result.fail("流程转办失败，请联系管理员");
    }

    @Override
    public Result<Boolean> delegate(TaskDelegateCmd cmd) {
        errLog();
        return Result.fail("流程委派失败，请联系管理员");
    }

    private void errLog() {
        log.error("服务调用失败，报错原因：{}", throwable.getMessage());
    }

}

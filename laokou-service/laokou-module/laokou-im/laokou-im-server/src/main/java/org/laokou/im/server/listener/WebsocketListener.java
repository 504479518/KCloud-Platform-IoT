/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.laokou.im.server.listener;

import io.micrometer.common.lang.NonNullApi;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.laokou.im.server.config.WebSocketServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
@NonNullApi
public class WebsocketListener implements ApplicationListener<ApplicationReadyEvent> {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final WebSocketServer webSocketServer;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        taskExecutor.execute(webSocketServer::start);
    }
}

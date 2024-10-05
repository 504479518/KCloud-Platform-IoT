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

package org.laokou.im.gatewayimpl.rpc;

import org.springframework.cloud.openfeign.FeignClient;

import static org.laokou.im.common.constant.Constant.LAOKOU_ADMIN;

/**
 * @author laokou
 */
@FeignClient(name = LAOKOU_ADMIN, contextId = "user", path = "/v3/users", fallbackFactory = Object.class)
public interface UserFeignClient {

}

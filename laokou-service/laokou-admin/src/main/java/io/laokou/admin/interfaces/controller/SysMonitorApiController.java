/**
 * Copyright 2020-2022 Kou Shenhai
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
 */
package io.laokou.admin.interfaces.controller;
import io.laokou.admin.application.service.SysMonitorApplicationService;
import io.laokou.admin.interfaces.vo.CacheVO;
import io.laokou.admin.interfaces.vo.ServerVO;
import io.laokou.common.utils.HttpResultUtil;
import io.laokou.security.annotation.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/7/27 0027 下午 3:13
 */
@RestController
@Api(value = "系统监控API",protocols = "http",tags = "系统监控API")
@RequestMapping("/sys/monitor/api")
public class SysMonitorApiController {

    @Autowired
    private SysMonitorApplicationService sysMonitorApplicationService;

    @GetMapping("/cache")
    @ApiOperation("系统监控>缓存")
    @PreAuthorize("sys:monitor:cache")
    public HttpResultUtil<CacheVO> redis() {
        return new HttpResultUtil<CacheVO>().ok(sysMonitorApplicationService.getCacheInfo());
    }

    @GetMapping("/server")
    @ApiOperation("系统监控>主机")
    @PreAuthorize("sys:monitor:server")
    public HttpResultUtil<ServerVO> server() throws Exception {
        return new HttpResultUtil<ServerVO>().ok(sysMonitorApplicationService.getServerInfo());
    }

}

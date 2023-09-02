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
package org.laokou.admin.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.client.api.UsersServiceI;
import org.laokou.admin.client.dto.user.UserInsertCmd;
import org.laokou.admin.client.dto.user.UserProfileGetQry;
import org.laokou.admin.client.dto.user.clientobject.UserProfileCO;
import org.laokou.admin.domain.annotation.OperateLog;
import org.laokou.common.core.vo.OptionVO;
import org.laokou.common.data.cache.annotation.DataCache;
import org.laokou.common.data.cache.enums.Cache;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.trace.annotation.TraceLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laokou
 */
@RestController
@Tag(name = "UsersController", description = "用户")
@RequiredArgsConstructor
public class UsersController {

	private final UsersServiceI usersServiceI;

	@TraceLog
	@PutMapping("v1/users")
	@Operation(summary = "修改", description = "修改")
	@OperateLog(module = "用户管理", operation = "修改")
	@PreAuthorize("hasAuthority('users:update')")
	@DataCache(name = "users", key = "#dto.id", type = Cache.DEL)
	public Result<Boolean> update() {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users/online-list")
	// @PreAuthorize("hasAuthority('users:online:list')")
	@Operation(summary = "在线用户查询", description = "在线用户查询")
	public Result<?> onlineList() {
		return Result.of(null);
	}

	@TraceLog
	@DeleteMapping("v1/users/online-kill/{token}")
	@Operation(summary = "在线用户强踢", description = "在线用户强踢")
	// @OperateLog(module = "用户管理", operation = "在线用户强踢")
	// @PreAuthorize("hasAuthority('users:online:kill')")
	public Result<Boolean> onlineKill(@PathVariable("token") String token) {
		return Result.of(null);
	}

	@TraceLog
	@GetMapping("v1/users/profile")
	@Operation(summary = "信息", description = "信息")
	public Result<UserProfileCO> profile() {
		return usersServiceI.profile(new UserProfileGetQry());
	}

	@TraceLog
	@GetMapping("v1/users/option-list")
	@Operation(summary = "下拉列表", description = "下拉列表")
	public Result<List<OptionVO>> optionList() {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/profile")
	@Operation(summary = "信息", description = "信息")
	public Result<Boolean> profile(@RequestBody Object obj) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/status/{id}/{status}")
	@Operation(summary = "状态", description = "状态")
	// @OperateLog(module = "用户管理", operation = "状态")
	// @PreAuthorize("hasAuthority('users:status')")
	public Result<Boolean> status(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/pwd/{id}/{pwd}")
	@Operation(summary = "密码", description = "密码")
	// @OperateLog(module = "用户管理", name = "密码")
	@PreAuthorize("hasAuthority('users:pwd')")
	public Result<Boolean> pwd(@PathVariable("id") Long id, @PathVariable("pwd") String pwd) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/profile-pwd/{id}/{pwd}")
	@Operation(summary = "密码", description = "密码")
	public Result<Boolean> profilePwd(@PathVariable("id") Long id, @PathVariable("pwd") String pwd) {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users")
	@Operation(summary = "新增", description = "新增")
	// @OperateLog(module = "用户管理", operation = "新增")
	// @PreAuthorize("hasAuthority('users:insert')")
	public Result<Boolean> insert(@RequestBody UserInsertCmd cmd) {
		return usersServiceI.insert(cmd);
	}

	@TraceLog
	@GetMapping("v1/users/{id}")
	@Operation(summary = "查看", description = "查看")
	// @DataCache(name = "users", key = "#id")
	public Result<?> get(@PathVariable("id") Long id) {
		return Result.of(null);
	}

	@TraceLog
	@DeleteMapping("v1/users")
	@Operation(summary = "删除", description = "删除")
	// @OperateLog(module = "用户管理", operation = "删除")
	// @PreAuthorize("hasAuthority('users:delete')")
	// @DataCache(name = "users", key = "#id", type = CacheEnum.DEL)
	public Result<Boolean> delete(@RequestParam("id") Long id) {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users/list")
	@Operation(summary = "查询", description = "查询")
	// @PreAuthorize("hasAuthority('users:list')")
	public Result<?> list() {
		return Result.of(null);
	}

}

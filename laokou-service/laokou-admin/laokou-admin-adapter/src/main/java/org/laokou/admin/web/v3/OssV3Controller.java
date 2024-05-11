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

package org.laokou.admin.web.v3;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.api.OssServiceI;
import org.laokou.admin.dto.oss.*;
import org.laokou.admin.dto.oss.clientobject.FileCO;
import org.laokou.admin.dto.oss.clientobject.OssCO;
import org.laokou.common.data.cache.annotation.DataCache;
import org.laokou.common.data.cache.constant.TypeEnum;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.idempotent.annotation.Idempotent;
import org.laokou.common.log.annotation.OperateLog;
import org.laokou.common.trace.annotation.TraceLog;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.laokou.common.data.cache.constant.NameConstant.OSS;
import static org.laokou.common.data.cache.constant.TypeEnum.DEL;

/**
 * @author laokou
 */
@RestController
@Tag(name = "OssV3Controller", description = "OSS管理")
@RequiredArgsConstructor
@RequestMapping("v3/oss")
public class OssV3Controller {

	@TraceLog
	@PostMapping("page")
	@Operation(summary = "OSS管理", description = "分页查询OSS列表")
	@PreAuthorize("hasAuthority('oss:page')")
	public Result<Datas<OssCO>> pageV3(@RequestBody OssListQry qry) {
		return null;
	}

	@TraceLog
	@PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "OSS管理", description = "上传文件")
	@OperateLog(module = "OSS管理", operation = "上传文件")
	public Result<?> uploadV3(@RequestPart("file") MultipartFile file) {
		return null;
	}

	@Idempotent
	@PostMapping
	@Operation(summary = "OSS管理", description = "新增OSS")
	@OperateLog(module = "OSS管理", operation = "新增OSS")
	@PreAuthorize("hasAuthority('oss:save')")
	public void saveV3(@RequestBody OssCreateCmd cmd) {
	}

	@TraceLog
	@GetMapping("{id}")
	@Operation(summary = "OSS管理", description = "查看OSS")
	@DataCache(name = OSS, key = "#id")
	public Result<OssCO> getByIdV3(@PathVariable("id") Long id) {
		return null;
	}

	@PutMapping
	@Operation(summary = "OSS管理", description = "修改OSS")
	@OperateLog(module = "OSS管理", operation = "修改OSS")
	@PreAuthorize("hasAuthority('oss:modify')")
	@DataCache(name = OSS, key = "#cmd.co.id", type = DEL)
	public void modifyV3(@RequestBody OssModifyCmd cmd) {
	}

	@DeleteMapping
	@Operation(summary = "OSS管理", description = "删除OSS")
	@OperateLog(module = "OSS管理", operation = "删除OSS")
	@PreAuthorize("hasAuthority('oss:remove')")
	public void removeV3(@RequestBody Long[] ids) {

	}

}

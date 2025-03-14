/*
 * Copyright (c) 2022-2025 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
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

package org.laokou.common.oss.template;

import lombok.RequiredArgsConstructor;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.oss.entity.FileInfo;
import org.laokou.common.oss.entity.OssInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author laokou
 */
@RequiredArgsConstructor
public class StorageTemplate {

	private final ExecutorService virtualThreadExecutor;

	public Result<String> upload(MultipartFile file, OssInfo ossInfo) throws IOException {
		return switch (ossInfo.getType()) {
			case LOCAL -> Result.ok(new LocalStorage(new FileInfo(file), ossInfo, virtualThreadExecutor).upload());
			case CLOUD -> Result.ok(new AmazonS3Storage(new FileInfo(file), ossInfo, virtualThreadExecutor).upload());
		};
	}

}

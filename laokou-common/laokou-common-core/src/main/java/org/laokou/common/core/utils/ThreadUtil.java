/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.common.core.utils;

import org.laokou.common.i18n.utils.ObjectUtil;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 线程池工具类.
 * @author laokou
 */
public class ThreadUtil {

	/**
	 * 关闭线程池.
	 * @param executorService 执行器.
	 * @param timeout 超时时间
	 */
	public static void shutdown(ExecutorService executorService, int timeout) {
		if (ObjectUtil.isNotNull(executorService) && !executorService.isShutdown()) {
			executorService.shutdown();
			try {
				if (!executorService.awaitTermination(timeout, SECONDS)) {
					executorService.shutdownNow();
				}
			}
			catch (InterruptedException e) {
				executorService.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}

}

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

package org.laokou.logstash.common.support;

import lombok.extern.slf4j.Slf4j;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.i18n.common.constant.StringConstant;
import org.laokou.common.i18n.utils.DateUtil;
import org.laokou.common.i18n.utils.JacksonUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.logstash.gatewayimpl.database.dataobject.TraceLogIndex;

@Slf4j
public abstract class AbstractTraceLogStorage implements TraceLogStorage {

	protected static final String TRACE_INDEX = "laokou_trace";

	protected String getIndexName() {
		return TRACE_INDEX + StringConstant.UNDER + DateUtil.format(DateUtil.nowDate(), DateUtil.YYYYMMDD);
	}

	protected TraceLogIndex getTraceLogIndex(String str) {
		try {
			TraceLogIndex traceLogIndex = JacksonUtil.toBean(str, TraceLogIndex.class);
			String traceId = traceLogIndex.getTraceId();
			String spanId = traceLogIndex.getSpanId();
			if (isTraceLog(traceId, spanId)) {
				traceLogIndex.setId(String.valueOf(IdGenerator.defaultSnowflakeId()));
				return traceLogIndex;
			}
		}
		catch (Exception ex) {
			log.error("分布式链路日志JSON转换失败，错误信息：{}", ex.getMessage());
		}
		return null;
	}

	private boolean isTraceLog(String traceId, String spanId) {
		return isTraceLog(traceId) && isTraceLog(spanId);
	}

	private boolean isTraceLog(String str) {
		return StringUtil.isNotEmpty(str) && !str.startsWith("${") && !str.endsWith("}");
	}

}

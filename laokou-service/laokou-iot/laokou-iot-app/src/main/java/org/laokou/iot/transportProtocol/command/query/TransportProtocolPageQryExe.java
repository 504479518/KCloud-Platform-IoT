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

package org.laokou.iot.transportProtocol.command.query;

import lombok.RequiredArgsConstructor;
import org.laokou.common.i18n.dto.Page;
import org.laokou.common.i18n.dto.Result;
import org.laokou.iot.transportProtocol.convertor.TransportProtocolConvertor;
import org.laokou.iot.transportProtocol.dto.TransportProtocolPageQry;
import org.laokou.iot.transportProtocol.dto.clientobject.TransportProtocolCO;
import org.laokou.iot.transportProtocol.gatewayimpl.database.TransportProtocolMapper;
import org.laokou.iot.transportProtocol.gatewayimpl.database.dataobject.TransportProtocolDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 分页查询传输协议请求执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class TransportProtocolPageQryExe {

	private final TransportProtocolMapper transportProtocolMapper;

	public Result<Page<TransportProtocolCO>> execute(TransportProtocolPageQry qry) {
		List<TransportProtocolDO> list = transportProtocolMapper.selectObjectPage(qry);
		long total = transportProtocolMapper.selectObjectCount(qry);
		return Result.ok(Page.create(list.stream().map(TransportProtocolConvertor::toClientObject).toList(), total));
	}

}

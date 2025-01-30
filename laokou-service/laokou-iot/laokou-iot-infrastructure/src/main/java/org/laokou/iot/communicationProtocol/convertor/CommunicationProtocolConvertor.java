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

package org.laokou.iot.communicationProtocol.convertor;

import org.laokou.common.core.utils.IdGenerator;
import org.laokou.iot.communicationProtocol.dto.clientobject.CommunicationProtocolCO;
import org.laokou.iot.communicationProtocol.gatewayimpl.database.dataobject.CommunicationProtocolDO;
import org.laokou.iot.communicationProtocol.model.CommunicationProtocolE;

/**
 *
 * 通讯协议转换器.
 *
 * @author laokou
 */
public class CommunicationProtocolConvertor {

	public static CommunicationProtocolDO toDataObject(CommunicationProtocolE communicationProtocolE,
			boolean isInsert) {
		CommunicationProtocolDO communicationProtocolDO = new CommunicationProtocolDO();
		if (isInsert) {
			communicationProtocolDO.setId(IdGenerator.defaultSnowflakeId());
		}
		else {
			communicationProtocolDO.setId(communicationProtocolE.getId());
		}
		communicationProtocolDO.setName(communicationProtocolE.getName());
		communicationProtocolDO.setCode(communicationProtocolE.getCode());
		communicationProtocolDO.setSort(communicationProtocolE.getSort());
		communicationProtocolDO.setRemark(communicationProtocolE.getRemark());
		return communicationProtocolDO;
	}

	public static CommunicationProtocolCO toClientObject(CommunicationProtocolDO communicationProtocolDO) {
		CommunicationProtocolCO communicationProtocolCO = new CommunicationProtocolCO();
		communicationProtocolCO.setName(communicationProtocolDO.getName());
		communicationProtocolCO.setCode(communicationProtocolDO.getCode());
		communicationProtocolCO.setSort(communicationProtocolDO.getSort());
		communicationProtocolCO.setRemark(communicationProtocolDO.getRemark());
		return communicationProtocolCO;
	}

	public static CommunicationProtocolE toEntity(CommunicationProtocolCO communicationProtocolCO) {
		CommunicationProtocolE communicationProtocolE = new CommunicationProtocolE();
		communicationProtocolE.setName(communicationProtocolCO.getName());
		communicationProtocolE.setCode(communicationProtocolCO.getCode());
		communicationProtocolE.setSort(communicationProtocolCO.getSort());
		communicationProtocolE.setRemark(communicationProtocolCO.getRemark());
		return communicationProtocolE;
	}

}

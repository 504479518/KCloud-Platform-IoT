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

package org.laokou.iot.cp.gatewayimpl;

import lombok.RequiredArgsConstructor;
import org.laokou.iot.cp.model.CpE;
import org.springframework.stereotype.Component;
import org.laokou.iot.cp.gateway.CpGateway;
import org.laokou.iot.cp.gatewayimpl.database.CpMapper;
import java.util.Arrays;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.laokou.iot.cp.convertor.CpConvertor;
import org.laokou.iot.cp.gatewayimpl.database.dataobject.CpDO;

/**
 *
 * 通讯协议网关实现.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class CpGatewayImpl implements CpGateway {

	private final CpMapper cpMapper;

	private final TransactionalUtil transactionalUtil;

	@Override
	public void create(CpE cpE) {
		transactionalUtil.executeInTransaction(() -> cpMapper.insert(CpConvertor.toDataObject(cpE, true)));
	}

	@Override
	public void update(CpE cpE) {
		CpDO cpDO = CpConvertor.toDataObject(cpE, false);
		cpDO.setVersion(cpMapper.selectVersion(cpE.getId()));
		update(cpDO);
	}

	@Override
	public void delete(Long[] ids) {
		transactionalUtil.executeInTransaction(() -> cpMapper.deleteByIds(Arrays.asList(ids)));
	}

	private void update(CpDO cpDO) {
		transactionalUtil.executeInTransaction(() -> cpMapper.updateById(cpDO));
	}

}

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

package org.laokou.common.domain.handler;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.laokou.common.domain.support.DomainEventPublisher;
import org.springframework.stereotype.Component;

import static org.apache.rocketmq.spring.annotation.ConsumeMode.CONCURRENTLY;
import static org.apache.rocketmq.spring.annotation.MessageModel.CLUSTERING;
import static org.laokou.common.domain.constant.MqConstant.LAOKOU_MODIFY_EVENT_CONSUMER_GROUP;
import static org.laokou.common.domain.constant.MqConstant.LAOKOU_MODIFY_EVENT_CONSUMER_GROUP_DLQ;

/**
 * @author laokou
 */
@Component
@RocketMQMessageListener(consumerGroup = LAOKOU_MODIFY_EVENT_CONSUMER_GROUP,
		topic = LAOKOU_MODIFY_EVENT_CONSUMER_GROUP_DLQ, messageModel = CLUSTERING, consumeMode = CONCURRENTLY)
public class ModifyDomainEventDLQHandler extends AbstractDLQHandler {

	public ModifyDomainEventDLQHandler(DomainEventPublisher domainEventPublisher) {
		super(domainEventPublisher);
	}

}

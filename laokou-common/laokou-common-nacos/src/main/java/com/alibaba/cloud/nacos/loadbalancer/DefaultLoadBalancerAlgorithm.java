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

/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.nacos.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * This is a default implementation of load balancing algorithm. use
 * {@link com.alibaba.cloud.nacos.balancer.NacosBalancer}
 *
 * @author <a href="mailto:zhangbin1010@qq.com">zhangbinhub</a>
 * @author laokou
 */
public class DefaultLoadBalancerAlgorithm implements LoadBalancerAlgorithm {

	@Override
	public String getServiceId() {
		return LoadBalancerAlgorithm.DEFAULT_SERVICE_ID;
	}

	@Override
	public ServiceInstance getInstance(Request<?> request, List<ServiceInstance> serviceInstances) {
		return NacosBalancer.getHostByRandomWeight3(serviceInstances);
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}

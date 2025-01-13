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

package org.laokou.common.data.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.laokou.common.redis.config.GlobalJsonJacksonCodec;
import org.laokou.common.redis.config.RedisAutoConfig;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * 数据缓存自动装配类.
 *
 * @author laokou
 */
@AutoConfiguration(after = { RedisAutoConfig.class })
public class DataCacheAutoConfig {

	@Bean
	public CacheManager caffeineCacheManager() {
		Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
			.maximumSize(4096)
			.expireAfterWrite(10, TimeUnit.MINUTES);
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
		caffeineCacheManager.setCaffeine(caffeine);
		return caffeineCacheManager;
	}

	@Bean
	public CacheManager redissonCacheManager(RedissonClient redissonClient) {
		return new RedissonSpringExtCacheManager(redissonClient, GlobalJsonJacksonCodec.INSTANCE);
	}

}

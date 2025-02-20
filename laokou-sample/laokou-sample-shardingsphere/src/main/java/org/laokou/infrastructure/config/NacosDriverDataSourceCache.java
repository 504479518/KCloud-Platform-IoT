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
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.laokou.infrastructure.config;

import com.alibaba.nacos.api.exception.NacosException;
import lombok.Getter;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.url.core.ShardingSphereURL;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Nacos Driver data source cache.
 *
 * @author laokou
 */
@Getter
public final class NacosDriverDataSourceCache {

	private final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

	/**
	 * Get data source.
	 * @param url URL
	 * @param urlPrefix URL prefix
	 * @return got data source
	 */
	public DataSource get(final String url, final String urlPrefix) {
		if (dataSourceMap.containsKey(url)) {
			return dataSourceMap.get(url);
		}
		return dataSourceMap.computeIfAbsent(url,
				driverUrl -> createDataSource(ShardingSphereURL.parse(driverUrl.substring(urlPrefix.length()))));
	}

	@SuppressWarnings("unchecked")
	private <T extends Throwable> DataSource createDataSource(final ShardingSphereURL url) throws T {
		try {
			NacosShardingSphereURLLoadEngine urlLoadEngine = new NacosShardingSphereURLLoadEngine(url);
			return YamlShardingSphereDataSourceFactory.createDataSource(urlLoadEngine.loadContent());
		}
		catch (final IOException ex) {
			throw (T) new SQLException(ex);
		}
		catch (final SQLException ex) {
			throw (T) ex;
		} catch (NacosException e) {
			throw new RuntimeException(e);
		}
	}

}

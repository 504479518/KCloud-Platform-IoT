/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
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
package org.laokou.admin.gatewayimpl.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.laokou.admin.gatewayimpl.database.dataobject.OssDO;
import org.springframework.stereotype.Repository;

/**
 * @author laokou
 */
@Repository
@Mapper
public interface OssMapper extends BaseMapper<OssDO> {

//	/**
//	 * 查询OSS配置
//	 * @return
//	 */
//	SysOssVO queryOssConfig();
//
//	/**
//	 * 获取版本号
//	 * @param id
//	 * @return
//	 */
//	Integer getVersion(@Param("id") Long id);
//
//	/**
//	 * 分页查询
//	 * @param page
//	 * @param qo
//	 * @return
//	 */
//	IPage<SysOssVO> queryOssPage(IPage<SysOssVO> page, @Param("qo") SysOssQo qo);
//
//	/**
//	 * 查询详情
//	 * @param id
//	 * @return
//	 */
//	SysOssVO getOssById(@Param("id") Long id);

}

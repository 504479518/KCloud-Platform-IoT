/**
 * Copyright 2020-2022 Kou Shenhai
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
 */
package io.laokou.admin.application.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.laokou.admin.application.service.SysDictApplicationService;
import io.laokou.admin.domain.sys.entity.SysDictDO;
import io.laokou.admin.domain.sys.repository.service.SysDictService;
import io.laokou.admin.domain.sys.repository.service.SysUserService;
import io.laokou.common.user.SecurityUser;
import io.laokou.admin.interfaces.dto.SysDictDTO;
import io.laokou.admin.interfaces.qo.SysDictQO;
import io.laokou.admin.interfaces.vo.SysDictVO;
import io.laokou.common.user.UserDetail;
import io.laokou.common.utils.ConvertUtil;
import io.laokou.datasource.annotation.DataFilter;
import io.laokou.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
public class SysDictApplicationServiceImpl implements SysDictApplicationService {

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    @DataSource("master")
    @DataFilter(tableAlias = "boot_sys_dict")
    public IPage<SysDictVO> queryDictPage(SysDictQO qo) {
        IPage<SysDictVO> page = new Page<>(qo.getPageNum(),qo.getPageSize());
        return sysDictService.getDictList(page,qo);
    }

    @Override
    @DataSource("master")
    public SysDictVO getDictById(Long id) {
        return sysDictService.getDictById(id);
    }

    @Override
    @DataSource("master")
    public Boolean insertDict(SysDictDTO dto, HttpServletRequest request) {
        SysDictDO dictDO = ConvertUtil.sourceToTarget(dto, SysDictDO.class);
        dictDO.setCreator(SecurityUser.getUserId(request));
        final UserDetail userDetail = sysUserService.getUserDetail(dictDO.getCreator());
        dictDO.setDeptId(userDetail.getDeptId());
        return sysDictService.save(dictDO);
    }

    @Override
    @DataSource("master")
    public Boolean updateDict(SysDictDTO dto, HttpServletRequest request) {
        SysDictDO dictDO = ConvertUtil.sourceToTarget(dto, SysDictDO.class);
        dictDO.setEditor(SecurityUser.getUserId(request));
        return sysDictService.updateById(dictDO);
    }

    @Override
    @DataSource("master")
    public Boolean deleteDict(Long id) {
        sysDictService.deleteDict(id);
        return true;
    }
}

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
package io.laokou.admin.domain.sys.repository.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.laokou.admin.domain.sys.entity.SysResourceDO;
import io.laokou.admin.domain.sys.repository.mapper.SysResourceMapper;
import io.laokou.admin.domain.sys.repository.service.SysResourceService;
import io.laokou.admin.infrastructure.common.index.ResourceIndex;
import io.laokou.admin.interfaces.qo.SysResourceQO;
import io.laokou.admin.interfaces.vo.SysResourceVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/8/19 0019 下午 4:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResourceDO> implements SysResourceService {

    @Override
    public IPage<SysResourceVO> getResourceList(IPage<SysResourceVO> page, SysResourceQO qo) {
        return this.baseMapper.getResourceList(page, qo);
    }

    @Override
    public SysResourceVO getResourceById(Long id) {
        return this.baseMapper.getResourceById(id);
    }

    @Override
    public void deleteResource(Long id) {
        this.baseMapper.deleteResource(id);
    }

    @Override
    public Long getResourceTotal(String code) {
        return this.baseMapper.getResourceTotal(code);
    }

    @Override
    public List<String> getResourceYMPartitionList(String code) {
        return this.baseMapper.getResourceYMPartitionList(code);
    }

    @Override
    public List<ResourceIndex> getResourceIndexList(Integer pageSize, Integer pageIndex, String code) {
        return this.baseMapper.getResourceIndexList(pageSize, pageIndex, code);
    }
}

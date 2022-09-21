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
package io.laokou.admin.application.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.laokou.admin.interfaces.dto.SysResourceDTO;
import io.laokou.admin.interfaces.qo.SysResourceQO;
import io.laokou.admin.interfaces.vo.SysResourceAuditLogVO;
import io.laokou.admin.interfaces.vo.SysResourceVO;
import io.laokou.admin.interfaces.vo.UploadVO;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/8/19 0019 下午 3:42
 */
public interface SysResourceApplicationService {

    IPage<SysResourceVO> queryResourcePage(SysResourceQO qo);

    SysResourceVO getResourceById(Long id);

    Boolean insertResource(SysResourceDTO dto, HttpServletRequest request);

    Boolean updateResource(SysResourceDTO dto, HttpServletRequest request);

    Boolean deleteResource(Long id);

    UploadVO uploadResource(String code,String fileName, InputStream inputStream,Long fileSize) throws Exception;

    Boolean syncAsyncBatchResource(String code);

    List<SysResourceAuditLogVO> queryAuditLogList(Long resourceId);

}

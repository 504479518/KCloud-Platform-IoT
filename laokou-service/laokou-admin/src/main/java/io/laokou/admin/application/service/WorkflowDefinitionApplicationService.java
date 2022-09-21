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
import io.laokou.admin.interfaces.qo.DefinitionQO;
import io.laokou.admin.interfaces.vo.DefinitionVO;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/7/6 0006 下午 6:10
 */
public interface WorkflowDefinitionApplicationService {

    String BPMN_FILE_SUFFIX = ".bpmn";

    Boolean importFile(String name, InputStream in);

    IPage<DefinitionVO> queryDefinitionPage(DefinitionQO qo);

    void imageProcess(String definitionId, HttpServletResponse response);

    Boolean deleteDefinition(String deploymentId);

    Boolean suspendDefinition(String definitionId);

    Boolean activateDefinition(String definitionId);
}

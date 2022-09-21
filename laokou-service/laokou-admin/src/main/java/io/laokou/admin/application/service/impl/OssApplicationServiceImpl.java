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

import io.laokou.admin.application.service.OssApplicationService;
import io.laokou.admin.infrastructure.component.cloud.CloudFactory;
import io.laokou.admin.interfaces.vo.UploadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
@Slf4j
public class OssApplicationServiceImpl implements OssApplicationService {

    @Autowired
    private CloudFactory cloudFactory;

    @Override
    public UploadVO upload(InputStream inputStream, String fileName, Long fileSize) throws Exception {
        UploadVO vo = new UploadVO();
        String url = cloudFactory.build().upload(inputStream, fileName, fileSize);
        log.info("上传文件地址：{}",url);
        vo.setUrl(url);
        return vo;
    }
}

package com.hzqing.admin.service.system;

import com.hzqing.admin.model.dto.system.ConfigDto;

/**
 * @author hzqing
 * @date 2019-06-11 14:55
 */
public interface IConfigService {
    /**
     * 修改配置项
     * @param config
     * @return
     */
    int modifyForBatch(ConfigDto config);

    /**
     * 获取所有的配置项目
     * @return
     */
    ConfigDto getConfigDto();

}

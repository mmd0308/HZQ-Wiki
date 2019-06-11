package com.hzqing.admin.service.config;

import com.hzqing.admin.domain.config.Config;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-06-11 14:55
 */
public interface IConfigService {

    List<Config> selectList(Config config);

    int insert(Config config);

    int update(Config config);

    int deletedById(String id);
}

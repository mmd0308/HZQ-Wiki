package com.hzqing.admin.service.config.impl;

import com.hzqing.admin.domain.config.Config;
import com.hzqing.admin.mapper.config.ConfigMapper;
import com.hzqing.admin.service.config.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-06-11 14:56
 */
@Service
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> selectList(Config config) {
        return configMapper.selectList(config);
    }

    @Override
    public int insert(Config config) {
        return configMapper.insert(config);
    }

    @Override
    public int update(Config config) {
        return configMapper.update(config);
    }

    @Override
    public int deletedById(String id) {
        return configMapper.deletedById(id);
    }
}

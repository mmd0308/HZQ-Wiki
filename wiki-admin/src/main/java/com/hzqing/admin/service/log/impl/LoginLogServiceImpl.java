package com.hzqing.admin.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.mapper.log.LoginLogMapper;
import com.hzqing.admin.model.entity.system.LoginLog;
import com.hzqing.admin.service.log.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hzqing
 * @date 2019-06-29 11:45
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public int insert(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
        return loginLog.getId();
    }

    @Override
    public Page<LoginLog> getPage(int num, int size, LoginLog loginLog) {
        return (Page<LoginLog>) loginLogMapper.selectPage(new Page<>(num,size),new QueryWrapper<>(loginLog).orderByDesc("create_time"));
    }
}

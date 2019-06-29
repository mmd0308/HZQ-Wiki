package com.hzqing.admin.service.log.impl;

import com.hzqing.admin.domain.log.LoginLog;
import com.hzqing.admin.mapper.log.LoginLogMapper;
import com.hzqing.admin.service.log.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-06-29 11:45
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> selectList(LoginLog loginLog) {
        return loginLogMapper.selectList(loginLog);
    }

    @Override
    public int insert(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }
}

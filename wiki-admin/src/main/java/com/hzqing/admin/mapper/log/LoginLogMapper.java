package com.hzqing.admin.mapper.log;

import com.hzqing.admin.domain.log.LoginLog;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
public interface LoginLogMapper {

    List<LoginLog> selectList(LoginLog loginLog);

    int insert(LoginLog loginLog);

}

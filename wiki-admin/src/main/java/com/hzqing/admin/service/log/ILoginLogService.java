package com.hzqing.admin.service.log;

import com.hzqing.admin.domain.log.LoginLog;

import java.util.List;

/**
 * 登陆日志记录
 */
public interface ILoginLogService {

    /**
     * 分页查询
     * @param loginLog
     * @return
     */
    List<LoginLog> selectList(LoginLog loginLog);

    /**
     * 新增日志
     * @param loginLog
     * @return
     */
    int insert(LoginLog loginLog);
}

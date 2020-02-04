package com.hzqing.admin.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.entity.system.LoginLog;

/**
 * 登陆日志记录
 */
public interface ILoginLogService {

    /**
     * 新增日志
     * @param loginLog
     * @return
     */
    int insert(LoginLog loginLog);

    /**
     * 分页查询
     * @param num
     * @param size
     * @param loginLog
     * @return
     */
    Page<LoginLog> getPage(int num, int size, LoginLog loginLog);
}

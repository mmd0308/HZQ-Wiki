package com.hzqing.admin.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.domain.log.LoginLog;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
public interface LoginLogMapper  extends BaseMapper<LoginLog> {

    List<LoginLog> selectList(LoginLog loginLog);

    int insert(LoginLog loginLog);

}

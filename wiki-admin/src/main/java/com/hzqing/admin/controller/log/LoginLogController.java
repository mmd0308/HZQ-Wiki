package com.hzqing.admin.controller.log;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.log.LoginLog;
import com.hzqing.admin.service.log.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-06-29 11:42
 */
@RestController
@RequestMapping("/api/login/log")
public class LoginLogController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param loginLog
     * @return
     */
    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, LoginLog loginLog){
        startPage(pageNum,pageSize);
        List<LoginLog> loginLogs = loginLogService.selectList(loginLog);
        return responseMessage(loginLogs);
    }

}

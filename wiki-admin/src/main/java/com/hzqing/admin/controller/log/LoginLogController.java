package com.hzqing.admin.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.system.LoginLog;
import com.hzqing.admin.service.log.ILoginLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzqing
 * @date 2019-06-29 11:42
 */
@Slf4j
@RestController
@RequestMapping("/api/wiki/login/logs")
public class LoginLogController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;


    @ApiOperation(value = "分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "页码",required = true,dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页显示的数量",required = true,dataType = "int")
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<LoginLog>> getPage(@PathVariable int num, @PathVariable int size, LoginLog loginLog){
        RestResult<Page<LoginLog>> result = RestResultFactory.getInstance().success();
        try{
            Page<LoginLog> datas = loginLogService.getPage(num,size,loginLog);
            result.setData(datas);
        }catch (Exception e){
            log.error("LoginLogController.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

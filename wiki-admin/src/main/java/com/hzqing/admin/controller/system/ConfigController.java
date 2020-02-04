package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.dto.system.ConfigDto;
import com.hzqing.admin.service.system.IConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Api(tags = "系统配置管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/configs")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService configService;

    @ApiOperation("获取配置管理所有数据")
    @GetMapping("/form")
    public RestResult<ConfigDto> form(){
        RestResult result = RestResultFactory.getInstance().success();
        try{
            ConfigDto configDto = configService.getConfigDto();
            result.setData(configDto);
        }catch (Exception e){
            log.error("ConfigController.form occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation("批量修改配置")
    @PutMapping
    public RestResult modifyForBatch(@RequestBody ConfigDto configDto){
        RestResult result = RestResultFactory.getInstance().success();
        try{
            configService.modifyForBatch(configDto);
        }catch (Exception e){
            log.error("ConfigController.modifyForBatch occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }
}

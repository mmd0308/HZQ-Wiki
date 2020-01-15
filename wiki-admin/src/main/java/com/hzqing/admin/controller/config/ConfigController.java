package com.hzqing.admin.controller.config;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.dto.config.ConfigDto;
import com.hzqing.admin.service.config.IConfigService;
import io.swagger.annotations.Api;
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


    @GetMapping("/form")
    public ResponseMessage form(){
        ConfigDto configDto = configService.getConfigDto();
        return responseMessage(configDto);
    }

    /**
     * 修改配置文件
     * @param configDto
     * @return
     */
    @PutMapping()
    public ResponseMessage modifyForBatch(@RequestBody ConfigDto configDto){
        int res = configService.update(configDto);
        return responseMessage(res);
    }
}

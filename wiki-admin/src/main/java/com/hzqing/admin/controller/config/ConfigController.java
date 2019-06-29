package com.hzqing.admin.controller.config;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.config.Config;
import com.hzqing.admin.dto.config.ConfigDto;
import com.hzqing.admin.service.config.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@RestController
@RequestMapping("/api/config")
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
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody ConfigDto configDto){
        int res = configService.update(configDto);
        return responseMessage(res);
    }
}

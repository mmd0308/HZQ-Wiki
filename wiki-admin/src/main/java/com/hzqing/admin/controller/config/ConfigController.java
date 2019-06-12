package com.hzqing.admin.controller.config;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.config.Config;
import com.hzqing.admin.service.config.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取分页列表
     * @param pageNum
     * @param pageSize
     * @param config
     * @return
     */
    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Config config){
        startPage(pageNum,pageSize);
        List<Config> spaces = configService.selectList(config);
        return responseMessage(spaces);
    }

    /**
     * 检查key是否唯一
     * @param key
     * @return
     */
    @GetMapping("/checkKey/{key}")
    public ResponseMessage checkKey(@PathVariable String key){
        Config config = new Config();
        config.setKey(key);
        List<Config> spaces = configService.selectList(config);
        if (spaces.size() == 0)
            return responseMessage(true);
        return responseMessage(false);
    }

    /**
     * 新增或者修改
     * @param config
     * @return
     */
    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Config config){
        int res = -1;
        config = (Config) initAddOrUpdate(config);
        if (config.getId() == null){ //新增
            res = configService.insert(config);
        }else {
            config.setCreateBy(null);
            res = configService.update(config);
        }
        return responseMessage(res);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = configService.deletedById(id);
        return responseMessage(res);
    }
}

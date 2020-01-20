package com.hzqing.admin.controller.show;

import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.service.article.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hzqing
 * @date 2020-01-18 18:55
 */
@Api(tags = "标签管理模块")
@Slf4j
@RestController
@RequestMapping("/api/wiki/show/tags")
public class ShowTagContoller extends BaseController {


    @Autowired
    private ITagService tagService;


    @ApiOperation(value = "获取所有的标签")
    @GetMapping("/all")
    public RestResult<List<Tag>> getListAll(){
        RestResult<List<Tag>> result = RestResultFactory.getInstance().success();
        try {
            List<Tag> tags = tagService.getListAll();
            result.setData(tags);
        }catch (Exception e){
            log.error("ShowTagContoller.getListAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

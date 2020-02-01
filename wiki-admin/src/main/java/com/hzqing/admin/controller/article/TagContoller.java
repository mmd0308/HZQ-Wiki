package com.hzqing.admin.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.model.params.ArticlePage;
import com.hzqing.admin.service.article.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2020-01-18 18:55
 */
@Api(tags = "标签管理模块")
@Slf4j
@RestController
@RequestMapping("/api/wiki/tags")
public class TagContoller extends BaseController {


    @Autowired
    private ITagService tagService;
//
//    @ApiOperation(value = "根据文章id，获取文章。如果是当前用户，直接返回，如果是admin，只能返回已经发布的文章。----存在bug")
//    @GetMapping("/{id}")
//    public ResponseMessage getById(@PathVariable int id){
//        ArticleState article = articleService.getById(id);
//        return responseMessage(article);
//    }
//

    @ApiOperation(value = "创建标签")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Tag tag){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int id = tagService.create(tag);
            result.setData(id);
        } catch (Exception e) {
            log.error("TagContoller.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "获取所有的标签")
    @GetMapping("/all")
    public RestResult<List<Tag>> getListAll(){
        RestResult<List<Tag>> result = RestResultFactory.getInstance().success();
        try {
            List<Tag> tags = tagService.getListAll();
            result.setData(tags);
        }catch (Exception e){
            log.error("TagContoller.getListAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "获取标签分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public  RestResult<Page<Tag>> getPage(@PathVariable int num, @PathVariable int size, Tag tag){
        // 默认返回值
        RestResult<Page<Tag>> result = RestResultFactory.getInstance().success();
        try {
            Page<Tag> articlePages = tagService.getPage(num,size,tag);
            result.setData(articlePages);
        }catch (Exception e){
            log.error("TagContoller.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


    @ApiOperation(value = "根据id，更新标签")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody Tag tag){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
           tagService.modifyById(tag);
        } catch (Exception e) {
            log.error("TagContoller.modifyById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

//    @ApiOperation(value = "根据id，删除博客")
//    @DeleteMapping("/{id}")
//    public ResponseMessage removedById(@PathVariable int id) {
//        int res = articleService.removedById(id);
//        return responseMessage(res);
//    }
//

}

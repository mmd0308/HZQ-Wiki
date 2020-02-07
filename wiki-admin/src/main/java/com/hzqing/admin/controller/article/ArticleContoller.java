package com.hzqing.admin.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.BaseException;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.converter.article.ArticleConverter;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.params.ArticlePage;
import com.hzqing.admin.model.params.ArticleRelease;
import com.hzqing.admin.model.vo.article.ArticleMinimalVO;
import com.hzqing.admin.service.article.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * @author hzqing
 * @date 2019-07-1 18:55
 */
@Api(tags = "文章管理模块")
@Slf4j
@RestController
@RequestMapping("/api/wiki/articles")
public class ArticleContoller extends BaseController {


    @Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleConverter articleConverter;

    @ApiOperation(value = "根据文章id，获取文章.只能获取当前用户自己的")
    @GetMapping("/{id}")
    public RestResult<Article> getById(@PathVariable Integer id){
        RestResult result = RestResultFactory.getInstance().success();
        try {
            Article article = articleService.getById(id);
            // 该文章不是当前用户自己的，不能获取修改
            if (!UserAuthUtils.getUserId().equals(article.getUserId())){
                throw new BaseException(RestResultCodeConstants.ARTICLE_UNAUTHORIZED.getCode(),
                        RestResultCodeConstants.ARTICLE_UNAUTHORIZED.getMsg());
            }
            result.setData(article);
        }catch (Exception e){
            log.error("ArticleContoller.getById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


    @ApiOperation(value = "获取该博客分页查询，如果是已经登陆的，获取该用户的所有博客，如果是admin，获取所有用户的已发布博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public  RestResult<Page<ArticleMinimalVO>> getPage(@PathVariable int num, @PathVariable int size, ArticlePage articlePage){
        // 默认返回值
        RestResult<Page<ArticleMinimalVO>> result = RestResultFactory.getInstance().success();
        try {
            Article article = new Article();
            // 如果不是admin ，超级管理员，我们获取当前登陆用户的文章
            if (!UserAuthUtils.isAdmin()){
                article.setUserId(UserAuthUtils.getUserId());
            }
            article.setTitle(articlePage.getTitle());
            Page<Article> articlePages = articleService.getPage(num,size,article);
            Page<ArticleMinimalVO> res = articleConverter.pageArticleToPageArticleMinimalVo(articlePages);
            result.setData(res);
        }catch (Exception e){
            log.error("ArticleContoller.page occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "创建博客")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Article article){
        log.info("ArticleContoller.create ",article);
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {

            int id = articleService.create(article);
            result.setData(id);
        } catch (Exception e) {
            log.error("ArticleContoller.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "文章发布")
    @PostMapping("/release")
    public RestResult<Integer> createRelease(@RequestBody ArticleRelease articleRelease){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            log.info("ArticleContoller.createRelease ",articleRelease);

            articleService.createRelease(articleRelease);
        } catch (Exception e) {
            log.error("ArticleContoller.createRelease occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，更新博客")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody Article article){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
           articleService.modifyById(article);
        } catch (Exception e) {
            log.error("ArticleContoller.modify occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，删除博客")
    @DeleteMapping("/{id}")
    public RestResult removedById(@PathVariable int id) {
        RestResult result = RestResultFactory.getInstance().success();
        try{
            int res = articleService.removedById(id);
            result.setData(res);
        }catch (Exception e){
            log.error("ArticleContoller.removedById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

}

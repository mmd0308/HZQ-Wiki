package com.hzqing.admin.controller.show;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.params.article.ArticleShowPage;
import com.hzqing.admin.service.article.IArticleService;
import io.swagger.annotations.Api;
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
 * 不需要登陆获取到的文章数据
 * @author hzqing
 * @date 2020-01-16 13:30
 */
@Api("首页展示")
@Slf4j
@RequestMapping("/api/wiki/show/articles")
@RestController
public class ShowArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "公开展示的博客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<Article>> getPage(@PathVariable int num, @PathVariable int size, ArticleShowPage articleShowPage){
        // 默认返回值
        RestResult<Page<Article>> result = RestResultFactory.getInstance().success();
        try {
            ArticleDto article = new ArticleDto();
            // 获取已经发布的博客
            article.setHwState(Constant.ARTICLE_STATE_RELEASE);
            article.setTagId(articleShowPage.getTagId());
            Page<Article> articlePage = articleService.getPageByStateOrTag(num,size,article);
            result.setData(articlePage);
        }catch (Exception e){
            log.error("ArticleContoller.page occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }
}

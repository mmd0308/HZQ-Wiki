package com.hzqing.admin.controller.show;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.BaseException;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.exception.support.UnauthorizedException;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.converter.article.ArticleConverter;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.params.article.ArticleShowPage;
import com.hzqing.admin.model.vo.article.ArticleDetailVO;
import com.hzqing.admin.model.vo.article.ArticleMinimalVO;
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

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private ArticleConverter articleConverter;

    @ApiOperation(value = "公开展示的博客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<ArticleMinimalVO>> getPage(@PathVariable int num, @PathVariable int size, ArticleShowPage articleShowPage){
        // 默认返回值
        RestResult<Page<ArticleMinimalVO>> result = RestResultFactory.getInstance().success();
        try {
            ArticleDto article = new ArticleDto();
            // 获取已经发布的博客
            article.setHwState(ArticleState.RELEASE);
            article.setTagId(articleShowPage.getTagId());
            Page<ArticleDto> articlePage = articleService.getPageByStateOrTag(num,size,article);
            Page<ArticleMinimalVO> minimalVOPage = articleConverter.pageArticleDtoToPageArticleMinimalVo(articlePage);
            result.setData(minimalVOPage);
        }catch (Exception e){
            log.error("ShowArticleController.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据文章id，获取文章")
    @GetMapping("/{id}")
    public RestResult<ArticleDetailVO> getById(@PathVariable int id, HttpServletRequest request){
        RestResult<ArticleDetailVO> result = RestResultFactory.getInstance().success();
        try{
            ArticleDto article = articleService.getDtoById(id);

            // 判断该文章是否已经发布,如果没有发布，不能查看
            if (!article.getHwState().equals(ArticleState.RELEASE) && !UserAuthUtils.isLogin(request)){
                throw new UnauthorizedException(
                        RestResultCodeConstants.ARTICLE_NO_RELEASE.getCode(),
                        RestResultCodeConstants.ARTICLE_NO_RELEASE.getMsg()
                );
            }
            // 登陆，获取的文章不是发布的
            if (UserAuthUtils.isLogin(request) && !article.getHwState().equals(ArticleState.RELEASE)){
                // 查看该文档是否是该用户
                if (!UserAuthUtils.getUserId().equals(article.getUserId())){
                    throw new BaseException(RestResultCodeConstants.ARTICLE_UNAUTHORIZED.getCode(),
                            RestResultCodeConstants.ARTICLE_UNAUTHORIZED.getMsg());
                }
            }

            ArticleDetailVO articleDetailVO = articleConverter.DtoToDetailVO(article);

            result.setData(articleDetailVO);
        }catch (Exception e){
            log.error("ShowArticleController.getById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
       return result;
    }

}

package com.hzqing.admin.controller.article;

import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.article.ArticleTag;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.service.article.IArticleTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:55
 */
@Api(tags = "文章和标签管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/articles/tags")
public class ArticleTagContoller extends BaseController {


    @Autowired
    private IArticleTagService articleTagService;


    @ApiOperation(value = "根据文章id，获取所有的标签Id")
    @GetMapping("/all/{articleId}")
    public RestResult<List<Integer>> getListAll(@PathVariable Integer articleId){
        RestResult<List<Integer>> result = RestResultFactory.getInstance().success();
        try {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            List<ArticleTag> articleTags = articleTagService.getListAll(articleTag);
            List<Integer> res = new ArrayList<>(articleTags.size());
            articleTags.forEach(item -> {
                res.add(item.getTagId());
            });
            result.setData(res);
        }catch (Exception e){
            log.error("TagContoller.getListAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

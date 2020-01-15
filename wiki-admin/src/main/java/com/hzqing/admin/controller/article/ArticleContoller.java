package com.hzqing.admin.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.service.article.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @ApiOperation(value = "根据文章id，获取文章。如果是当前用户，直接返回，如果是admin，只能返回已经发布的文章。----存在bug")
    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable int id){
        Article article = articleService.getById(id);
        return responseMessage(article);
    }


    @ApiOperation(value = "获取该博客分页查询，如果是已经登陆的，获取该用户的所有博客，如果是admin，获取所有用户的已发布博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public  RestResult<Page<Article>> getPage(@PathVariable int num, @PathVariable int size, Article article){
        // 默认返回值
        RestResult<Page<Article>> result = RestResultFactory.getInstance().success();
        try {
//            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (null != userInfo ){
//                if (userInfo.getUsername().equals(Constant.ADMIN_USER_NAME)){
//                    // 如果是admin登陆，获取所有的已经发布的文章
//                    article.setHwState(2);
//                }else {
//                    article.setUserId(userInfo.getId());
//                }
//            }

            Page<Article> articlePage = articleService.getPage(num,size,article);
            result.setData(articlePage);
        }catch (Exception e){
            log.error("ArticleContoller.page occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "创建博客")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Article article){
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
    public ResponseMessage removedById(@PathVariable int id) {
        int res = articleService.removedById(id);
        return responseMessage(res);
    }

    /**
     * 上传图片，返回图片路径
     * @param file
     * @return
     */
    @PostMapping("/uploadImages")
    public ResponseMessage uploadImages(MultipartFile file, String articleId , HttpServletRequest request){
        // 如果文章id为空，存储在tmp目录下
        if (articleId == null || articleId.equals("")) {
            articleId = "tmp";
        }
        return super.uploadImages(file,"article",articleId,request);
    }
}

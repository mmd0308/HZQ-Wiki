package com.hzqing.admin.controller.show;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.constants.Constant;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.exception.support.UnauthorizedException;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.model.dto.article.ArticleDto;
import com.hzqing.admin.model.dto.doc.DocDto;
import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.model.enums.article.ArticleState;
import com.hzqing.admin.model.enums.doc.DocVisitLevel;
import com.hzqing.admin.model.params.article.ArticleShowPage;
import com.hzqing.admin.model.params.doc.DocShowPage;
import com.hzqing.admin.model.vo.article.ArticleDetailVO;
import com.hzqing.admin.service.article.IArticleService;
import com.hzqing.admin.service.doc.IDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不需要登陆获取到的文档
 * @author hzqing
 * @date 2020-02-2 07:58
 */
@Api("首页展示")
@Slf4j
@RequestMapping("/api/wiki/show/docs")
@RestController
public class ShowDocController {
    @Autowired
    private IDocService docService;

    @ApiOperation(value = "公开展示的文档信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页展示数量", required = true, paramType = "path", dataType = "int"),
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<DocDto>> getPage(@PathVariable int num, @PathVariable int size, DocShowPage docShowPage){
        // 默认返回值
        RestResult<Page<DocDto>> result = RestResultFactory.getInstance().success();
        try {
            Doc doc = new Doc();
            doc.setSpaceId(docShowPage.getSpaceId());
            doc.setVisitLevel(DocVisitLevel.PUBLIC);
            Page<DocDto> docPage = docService.getPageBySpaceOrLevel(num,size,doc);

            result.setData(docPage);
        }catch (Exception e){
            log.error("ShowDocController.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id获取文档")
    @GetMapping("/{id}")
    public RestResult<Doc> getById(@PathVariable int id){
        RestResult<Doc> result = RestResultFactory.getInstance().success();
        try{

            Doc doc = docService.getById(id);

            // 判断该文章是否已经发布,如果没有发布，不能查看
            if (!doc.getVisitLevel().equals(DocVisitLevel.PUBLIC)){
                throw new UnauthorizedException(
                        RestResultCodeConstants.DOC_NO_PUBLIC.getCode(),
                        RestResultCodeConstants.DOC_NO_PUBLIC.getMsg()
                );
            }
            result.setData(doc);
        }catch (Exception e){
            log.error("ShowDocController.getById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

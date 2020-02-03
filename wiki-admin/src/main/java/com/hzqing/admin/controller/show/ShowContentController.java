package com.hzqing.admin.controller.show;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.exception.support.UnauthorizedException;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.converter.doc.ContentConverter;
import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.dto.doc.DocDto;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.model.entity.doc.Content;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.model.enums.doc.DocVisitLevel;
import com.hzqing.admin.model.params.doc.DocShowPage;
import com.hzqing.admin.model.vo.doc.ContentMinimalVO;
import com.hzqing.admin.service.doc.IContentService;
import com.hzqing.admin.service.doc.IDocService;
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

import java.util.List;

/**
 * 不需要登陆获取到的文档
 * @author hzqing
 * @date 2020-02-2 07:58
 */
@Api("首页展示")
@Slf4j
@RequestMapping("/api/wiki/show/contents")
@RestController
public class ShowContentController {
    @Autowired
    private IContentService contentService;

    @Autowired
    private ContentConverter contentConverter;


    @ApiOperation(value = "获取文档目录")
    @GetMapping("/all/{docId}")
    public RestResult<List<ContentMinimalVO>> getListAllByDocId(@PathVariable Integer docId){
        RestResult<List<ContentMinimalVO>> result = RestResultFactory.getInstance().success();
        try {
            List<ContentDto> contents = contentService.getListAllByDocId(docId);
            List<ContentMinimalVO> contentMinimalVOS = contentConverter.listContentDtoToListMinimalVo(contents);
            result.setData(contentMinimalVOS);
        }catch (Exception e){
            log.error("ShowContentController.getListAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

//    @ApiOperation(value = "根据id获取文档")
//    @GetMapping("/{id}")
//    public RestResult<Doc> getById(@PathVariable int id){
//        RestResult<Doc> result = RestResultFactory.getInstance().success();
//        try{
//
//            Doc doc = docService.getById(id);
//
//            // 判断该文章是否已经发布,如果没有发布，不能查看
//            if (!doc.getVisitLevel().equals(DocVisitLevel.PUBLIC)){
//                throw new UnauthorizedException(
//                        RestResultCodeConstants.DOC_NO_PUBLIC.getCode(),
//                        RestResultCodeConstants.DOC_NO_PUBLIC.getMsg()
//                );
//            }
//            result.setData(doc);
//        }catch (Exception e){
//            log.error("ShowDocController.getById occur Exception: ", e);
//            ExceptionProcessUtils.wrapperHandlerException(result,e);
//        }
//        return result;
//    }
//

}

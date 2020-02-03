package com.hzqing.admin.controller.show;

import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.converter.doc.ContentConverter;
import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.entity.doc.Content;
import com.hzqing.admin.model.vo.doc.ContentMinimalVO;
import com.hzqing.admin.service.doc.IContentService;
import io.swagger.annotations.Api;
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

    @ApiOperation(value = "根据id获取指定文章")
    @GetMapping("/{id}")
    public RestResult<Content> getById(@PathVariable int id){
        RestResult<Content> result = RestResultFactory.getInstance().success();
        try{

            Content content = contentService.getById(id);
            result.setData(content);
        }catch (Exception e){
            log.error("ShowContentController.getById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

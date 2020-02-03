package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.entity.doc.Content;
import com.hzqing.admin.service.doc.IContentService;
import com.hzqing.admin.service.doc.IDocService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:44
 */
@Slf4j
@RestController
@RequestMapping("/api/wiki/contents")
public class ContentController extends BaseController {
    @Autowired
    private IContentService contentService;

    @Autowired
    private IDocService docService;

    @ApiOperation(value = "创建文章")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Content content){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {

            int id = contentService.create(content);
            result.setData(id);
        } catch (Exception e) {
            log.error("ContentController.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，更新文章")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody Content content){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            contentService.modifyById(content);
        } catch (Exception e) {
            log.error("ContentController.modifyById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }








    //////////////////


    /**
     * 根据Id获取信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage get(@PathVariable int id){
        Content content = contentService.get(id);
        return responseMessage(content);
    }


    @GetMapping("/all/{docId}")
    public ResponseMessage all(@PathVariable Integer docId){
        Content content = new Content();
        content.setDocId(docId);
        List<Content> contents = contentService.selectList(content);
        return responseMessage(contents);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Content content){
        int res = -1;
       // content = (Content) initAddOrUpdate(content);
        //新增
        if (content.getId() == null){
            res = contentService.insert(content);
        }else {
            content.setCreateBy(null);
            res = contentService.update(content);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable Integer id) {
        Content content = new Content();
        content.setParentId(id);
        List<Content> contents = contentService.selectList(content);
        // 表示有子节点，不能删除
        if (contents.size()>0) {
            return responseMessage(500,-1,"有子集文档，不能删除！");
        }
        int res = contentService.deletedById(id);
        return responseMessage(res);
    }
}

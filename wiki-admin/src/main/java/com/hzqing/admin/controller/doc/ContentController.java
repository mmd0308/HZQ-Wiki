package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Content;
import com.hzqing.admin.service.doc.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:44
 */
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {
    @Autowired
    private IContentService contentService;


    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Content content){
        startPage(pageNum,pageSize);
        List<Content> contents = contentService.selectList(content);
        return responseMessage(contents);
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
        content = (Content) initAddOrUpdate(content);
        if (content.getId() == null){ //新增
            res = contentService.insert(content);
        }else {
            content.setCreateBy(null);
            res = contentService.update(content);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
//        int res = docService.deletedByIds(ids);
        return responseMessage(4);
    }
}

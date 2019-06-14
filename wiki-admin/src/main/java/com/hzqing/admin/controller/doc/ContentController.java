package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Content;
import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.system.UserInfo;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.service.doc.IContentService;
import com.hzqing.admin.service.doc.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:44
 */
@RestController
@RequestMapping("/api/content")
public class ContentController extends BaseController {
    @Autowired
    private IContentService contentService;

    @Autowired
    private IDocService docService;

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
    public ResponseMessage deleted(@PathVariable Integer id) {
        Content content = new Content();
        content.setParentId(id);
        List<Content> contents = contentService.selectList(content);
        if (contents.size()>0) { // 表示有子节点，不能删除
            return responseMessage(500,-1,"有子集文档，不能删除！");
        }
        int res = contentService.deletedById(id);
        return responseMessage(res);
    }
}

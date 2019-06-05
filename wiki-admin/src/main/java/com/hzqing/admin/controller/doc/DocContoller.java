package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.dto.space.SpaceDto;
import com.hzqing.admin.service.doc.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:50
 */
@RestController
@RequestMapping("/doc")
public class DocContoller extends BaseController {

    @Autowired
    private IDocService docService;

    /**
     * 获取制定用户的空间
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param doc
     * @return
     */
    @GetMapping("/page/{userId}")
    public ResponseMessage showPage(@PathVariable int userId, int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        doc.setCreateBy(userId);
        List<DocDto> docDtos = docService.selectListByID(doc);
        return responseMessage(docDtos);
    }

    /**
     *  文档首页展示
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param doc
     * @return
     */
    @GetMapping("/dashboard/{userId}")
    public ResponseMessage dashboard(@PathVariable int userId, int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        doc.setCreateBy(userId);
        List<DocDto> docDtos = docService.selectListByUserIdAndVL(doc);
        return responseMessage(docDtos);
    }

    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        List<Doc> docs = docService.selectList(doc);
        return responseMessage(docs);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Doc doc){
        int res = -1;
        doc = (Doc) initAddOrUpdate(doc);
        if (doc.getId() == null){ //新增
            res = docService.insert(doc);
        }else {
            doc.setCreateBy(null);
            res = docService.update(doc);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted")
    public ResponseMessage deleted(String ids) {
//        int res = docService.deletedByIds(ids);
        return responseMessage(4);
    }
}

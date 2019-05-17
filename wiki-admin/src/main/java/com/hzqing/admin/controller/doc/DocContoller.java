package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Doc;
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

    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        List<Doc> docs = docService.selectList(doc);
        return responseMessage(docs);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Doc doc){
        int res = -1;
        doc.setCreateTime(LocalDateTime.now());
        doc.setUpdateTime(LocalDateTime.now());
        if ( doc.getId() ==0){ //新增
            res = docService.insert(doc);
        }else {
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

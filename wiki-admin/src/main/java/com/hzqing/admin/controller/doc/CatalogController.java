package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.TreeUtil;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Catalog;
import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.service.doc.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:44
 */
@RestController
@RequestMapping("/catalog")
public class CatalogController extends BaseController {
    @Autowired
    private ICatalogService catalogService;


    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Catalog catalog){
        startPage(pageNum,pageSize);
        List<Catalog> catalogs = catalogService.selectList(catalog);
        return responseMessage(catalogs);
    }

    @GetMapping("/tree/{docId}")
    public ResponseMessage page(@PathVariable Integer docId){
        Catalog catalog = new Catalog();
        catalog.setDocId(docId);
        List<Catalog> catalogs = catalogService.selectList(catalog);
        List<Catalog> trees = TreeUtil.build(catalogs, -1);
        return responseMessage(trees);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Catalog catalog){
        int res = -1;
        catalog = (Catalog) initAddOrUpdate(catalog);
        if (catalog.getId() == null){ //新增
            res = catalogService.insert(catalog);
        }else {
            catalog.setCreateBy(null);
            res = catalogService.update(catalog);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
//        int res = docService.deletedByIds(ids);
        return responseMessage(4);
    }
}

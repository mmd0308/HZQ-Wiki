package com.hzqing.admin.controller.space;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.service.doc.IDocService;
import com.hzqing.admin.service.space.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@RestController
@RequestMapping("/space")
public class SpaceController extends BaseController {

    @Autowired
    private ISpaceService spaceService;

    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Space space){
        startPage(pageNum,pageSize);
        List<Space> spaces = spaceService.selectList(space);
        return responseMessage(spaces);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Space space){
        int res = -1;
        space.setCreateTime(LocalDateTime.now());
        space.setUpdateTime(LocalDateTime.now());
        if ( space.getId() ==0){ //新增
            res = spaceService.insert(space);
        }else {
            res = spaceService.update(space);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = spaceService.deletedById(id);
        return responseMessage(res);
    }
}

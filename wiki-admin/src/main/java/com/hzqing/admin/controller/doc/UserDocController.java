package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.doc.UserDoc;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.doc.UserDocDto;
import com.hzqing.admin.dto.space.UserSpaceDto;
import com.hzqing.admin.service.doc.IUserDocService;
import com.hzqing.admin.service.space.IUserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@RestController
@RequestMapping("/udoc")
public class UserDocController extends BaseController {

    @Autowired
    private IUserDocService userDocService;


    @GetMapping("/page/{docId}")
    public ResponseMessage showPage(@PathVariable int docId, int pageNum, int pageSize, UserDoc userDoc){
        startPage(pageNum,pageSize);
        userDoc.setDocId(docId);
        List<UserDocDto> spaces = userDocService.selectList(userDoc);
        return responseMessage(spaces);
    }

    /**
     *  该空间不存在的用户
     * @param docId
     * @return
     */
    @GetMapping("/spaceNonUserAll/{docId}")
    public ResponseMessage spaceNonUserAll(@PathVariable int docId){
        List<User> spaces = userDocService.selectUserListBySID(docId);
        return responseMessage(spaces);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody UserDoc userDoc){
        int res = -1;
        userDoc.setCreateTime(LocalDateTime.now());
        if (userDoc.getId() ==0){ //新增
            res = userDocService.insert(userDoc);
        }else {
            res = userDocService.update(userDoc);
        }
        return responseMessage(res);
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = userDocService.deletedById(id);
        return responseMessage(res);
    }


}

package com.hzqing.admin.controller.space;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.space.UserSpaceDto;
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
@RequestMapping("/api/uspace")
public class UserSpaceController extends BaseController {

    @Autowired
    private IUserSpaceService userSpaceService;


    @GetMapping("/page/{spaceId}")
    public ResponseMessage showPage(@PathVariable int spaceId, int pageNum, int pageSize, UserSpace userSpace){
        startPage(pageNum,pageSize);
        userSpace.setSpaceId(spaceId);
        List<UserSpaceDto> spaces = userSpaceService.selectList(userSpace);
        return responseMessage(spaces);
    }

    /**
     *  该空间不存在的用户
     * @param spaceId
     * @return
     */
    @GetMapping("/spaceNonUserAll/{spaceId}")
    public ResponseMessage spaceNonUserAll(@PathVariable int spaceId){
        List<User> spaces = userSpaceService.selectUserListBySID(spaceId);
        return responseMessage(spaces);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody UserSpace userSpace){
        int res = -1;
        userSpace.setCreateTime(LocalDateTime.now());
        if (userSpace.getId() ==0){ //新增
            res = userSpaceService.insert(userSpace);
        }else {
            res = userSpaceService.update(userSpace);
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
        int res = userSpaceService.deletedById(id);
        return responseMessage(res);
    }


}

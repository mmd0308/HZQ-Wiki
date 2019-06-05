package com.hzqing.admin.controller.space;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.dto.space.UserSpaceDto;
import com.hzqing.admin.service.space.IUserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@RestController
@RequestMapping("/uspace")
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

}

package com.hzqing.admin.controller.space;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.converter.system.UserConverter;
import com.hzqing.admin.dto.space.UserSpaceDto;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.model.vo.system.UserMinimalVO;
import com.hzqing.admin.service.space.IUserSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Slf4j
@Api(tags = "用户和空间关系")
@RestController
@RequestMapping("/api/wiki/users/spaces")
public class UserSpaceController extends BaseController {

    @Autowired
    private IUserSpaceService userSpaceService;

    @Autowired
    private UserConverter userConverter;


    @GetMapping("/page/{spaceId}")
    public ResponseMessage showPage(@PathVariable int spaceId, int pageNum, int pageSize, UserSpace userSpace){
        startPage(pageNum,pageSize);
        userSpace.setSpaceId(spaceId);
        List<UserSpaceDto> spaces = userSpaceService.selectList(userSpace);
        return responseMessage(spaces);
    }

    /**
     * 获取该空间的所有人员
     * @param spaceId
     * @param userSpace
     * @return
     */
    @GetMapping("/all/{spaceId}")
    public ResponseMessage all(@PathVariable int spaceId, UserSpace userSpace){
        userSpace.setSpaceId(spaceId);
        List<UserSpaceDto> spaces = userSpaceService.selectList(userSpace);
        return responseMessage(spaces);
    }

    @ApiOperation("该空间不存的所有用户")
    @GetMapping("/all/non/{spaceId}")
    public RestResult<UserMinimalVO> getUserAllBySpaceId(@PathVariable Integer spaceId){
        RestResult result = RestResultFactory.getInstance().success();
        try{
            List<User> userList = userSpaceService.getUserAllBySpaceId(spaceId);
            List<UserMinimalVO> res = userConverter.userToMinimalVO(userList);
            result.setData(res);
        }catch (Exception e){
            log.error("UserSpaceController.getUserAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }

        return result;
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

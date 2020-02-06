package com.hzqing.admin.controller.space;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.converter.system.UserConverter;
import com.hzqing.admin.model.dto.space.UserSpaceDto;
import com.hzqing.admin.model.entity.doc.Doc;
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
        //List<UserSpaceDto> spaces = userSpaceService.selectList(userSpace);
        return responseMessage(null);
    }

    @ApiOperation("获取该空间的所有人员")
    @GetMapping("/all/{spaceId}")
    public RestResult<List<UserSpaceDto>> all(@PathVariable Integer spaceId){
        RestResult<List<UserSpaceDto>> result = RestResultFactory.getInstance().success();
        try{
            List<UserSpaceDto> spaces = userSpaceService.getListAllBySpaceId(spaceId);
            result.setData(spaces);
        }catch (Exception e) {
            log.error("UserSpaceController.all occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
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

    @ApiOperation(value = "创建用户和空间关系")
    @PostMapping
    public RestResult<Integer> create(@RequestBody UserSpace userSpace){

        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int id = userSpaceService.create(userSpace);
            result.setData(id);
        } catch (Exception e) {
            log.error("UserSpaceController.create occur Exception: ", e);
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


    @ApiOperation(value = "根据id，删除")
    @DeleteMapping("/{id}")
    public RestResult<Integer> removedById(@PathVariable Integer id) {
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int res = userSpaceService.removedById(id);
            result.setData(res);
        }catch (Exception e){
            log.error("UserSpaceController.removedById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


}

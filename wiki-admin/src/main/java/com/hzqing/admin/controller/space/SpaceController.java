package com.hzqing.admin.controller.space;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.dto.space.SpaceDto;
import com.hzqing.admin.model.entity.space.Space;
import com.hzqing.admin.service.space.ISpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Api(tags = "文档空间管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/spaces")
public class SpaceController extends BaseController {

    @Autowired
    private ISpaceService spaceService;


    @ApiOperation(value = "空间分页分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "页码",required = true,dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页显示的数量",required = true,dataType = "int")
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<SpaceDto>> getPage(@PathVariable int num, @PathVariable int size, SpaceDto spaceDto){
        RestResult<Page<SpaceDto>> result = RestResultFactory.getInstance().success();
        try{
            spaceDto.setUserId(UserAuthUtils.getUserId());
            Page<SpaceDto> datas = spaceService.getPage(num,size,spaceDto);
            result.setData(datas);
        }catch (Exception e){
            log.error("ShowSpaceController.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


    @ApiOperation(value = "创建空间")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Space space){

        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int id = spaceService.create(space);
            result.setData(id);
        } catch (Exception e) {
            log.error("SpaceController.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，更新空间")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody Space space){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            spaceService.modifyById(space);
        } catch (Exception e) {
            log.error("SpaceController.modifyById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，删除空间")
    @DeleteMapping("/{id}")
    public RestResult<Integer> removedById(@PathVariable int id) {
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
           // int res = spaceService.removedById(id);
            result.setData(0);
        }catch (Exception e){
            log.error("SpaceController.removedById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }
}

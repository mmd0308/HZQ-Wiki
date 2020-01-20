package com.hzqing.admin.controller.space;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.dto.space.SpaceDto;
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
@RequestMapping("/api/space")
public class SpaceController extends BaseController {

    @Autowired
    private ISpaceService spaceService;

    /**
     * 获取制定用户的空间
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param space
     * @return
     */
    @GetMapping("/page/{userId}")
    public ResponseMessage getPageByUserId(@PathVariable int userId, int pageNum, int pageSize, Space space){
        startPage(pageNum,pageSize);
        space.setCreateBy(userId);
        List<SpaceDto> spaces = spaceService.selectListByUserId(space);
        return responseMessage(spaces);
    }


    @ApiOperation(value = "空间分页分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "页码",required = true,dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页显示的数量",required = true,dataType = "int")
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<Space>> getPage(@PathVariable int num, @PathVariable int size, Space space){
        RestResult<Page<Space>> result = RestResultFactory.getInstance().success();
        try{
            Page<Space> datas = spaceService.getPage(num,size,space);
            result.setData(datas);
        }catch (Exception e){
            log.error("ShowSpaceController.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }

        return result;
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage get(@PathVariable int id){
        SpaceDto spaceDto = spaceService.get(id);
        return responseMessage(spaceDto);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Space space){
        int res = -1;
        space = (Space) initAddOrUpdate(space);
        if (space.getId() == null){ //新增
            res = spaceService.insert(space);
        }else {
            space.setCreateBy(null);
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

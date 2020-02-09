package com.hzqing.admin.controller.show;

import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.converter.space.SpaceConverter;
import com.hzqing.admin.model.entity.article.Tag;
import com.hzqing.admin.model.entity.space.Space;
import com.hzqing.admin.model.entity.space.UserSpace;
import com.hzqing.admin.model.enums.space.SpaceVisitLevel;
import com.hzqing.admin.model.vo.space.SpaceMinimalVO;
import com.hzqing.admin.service.space.ISpaceService;
import com.hzqing.admin.service.space.IUserSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2020-01-16 13:00
 */
@Api("空间展示")
@Slf4j
@RequestMapping("/api/wiki/show/spaces")
@RestController
public class ShowSpaceController {

    @Autowired
    private ISpaceService spaceService;

    @Autowired
    private IUserSpaceService userSpaceService;

    @Autowired
    @SuppressWarnings("all")
    private SpaceConverter spaceConverter;



    @ApiOperation(value = "获取所有的空间，如果没有登陆，获取所有公开的空间.如果登陆，获取所有公开空间及该用户参与的空间")
    @GetMapping("/all")
    public RestResult<List<SpaceMinimalVO>> getListAll(HttpServletRequest request){
        RestResult<List<SpaceMinimalVO>> result = RestResultFactory.getInstance().success();
        try {
            List<Space> spaces = spaceService.getListAll();
            // 没有登陆
            if (!UserAuthUtils.isLogin(request)){
                spaces.removeIf(space -> {
                    return space.getVisitLevel().equals(SpaceVisitLevel.PRIVATE);
                });
            }else{
                UserSpace userSpace = new UserSpace();
                userSpace.setUserId(UserAuthUtils.getUserId());
                List<UserSpace> userSpaceList = userSpaceService.getListAll(userSpace);
                List<Integer> spaceIds = new ArrayList<>(userSpaceList.size());
                userSpaceList.forEach(item -> {
                    spaceIds.add(item.getSpaceId());
                });
                spaces.removeIf(space -> {
                    return space.getVisitLevel().equals(SpaceVisitLevel.PRIVATE) && !spaceIds.contains(space.getId());
                });
            }
            result.setData(spaceConverter.listSpaceToListMinimal(spaces));
        }catch (Exception e){
            log.error("ShowSpaceController.getListAll occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

}

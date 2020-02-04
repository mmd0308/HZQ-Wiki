package com.hzqing.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.DateUtils;
import com.hzqing.admin.common.utils.FileUtil;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.dto.system.UserInfo;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.service.system.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author hzqing
 * @date 2019-05-17 09:29
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/users")
public class UserContoller extends BaseController {

    @Value("${hzq.fs.path}")
    protected String filePath;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @ApiOperation(value = "用户分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "页码",required = true, paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页展示的数量",required = true, paramType = "path",dataType = "int")
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<User>> getPage(@PathVariable int num, @PathVariable int size, User user){
        // 默认返回值
        RestResult<Page<User>> result = RestResultFactory.getInstance().success();
        try {
            Page<User> datas = userService.getPage(num,size,user);
            result.setData(datas);
        } catch (Exception e) {
            log.error("UserContoller.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }

        return  result;
    }

    @ApiOperation(value = "根据用户id，获取用户")
    @GetMapping("/{id}")
    public RestResult<User> getById(@PathVariable int id){
        RestResult<User> result = RestResultFactory.getInstance().success();
        try{
            User user = userService.getById(id);
            result.setData(user);
        }catch (Exception e){
            log.error("UserContoller.getById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }



    @ApiOperation("检查密码是否正确")
    @PostMapping("/checkPass")
    public RestResult<Boolean> checkPass(@RequestBody User user){
        RestResult result = RestResultFactory.getInstance().success();
        try{
            User res = userService.getById(user.getId());
            boolean matches = passwordEncoder.matches(user.getPassword(),res.getPassword());
            result.setData(matches);
        }catch (Exception e){
            log.error("UserContoller.checkPass occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }




    @ApiOperation(value = "创建用户")
    @PostMapping
    public RestResult<Integer> create(@RequestBody User user){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int id = userService.create(user);
            result.setData(id);
        } catch (Exception e) {
            log.error("UserContoller.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，修改用户")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody User user){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            userService.modifyById(user);
        } catch (Exception e) {
            log.error("UserContoller.modifyById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据用户id，删除用户")
    @DeleteMapping("/{id}")
    public  RestResult<Integer> removedById(@PathVariable int id) {
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int res = userService.removedById(id);
            result.setData(res);
        }catch (Exception e){
            log.error("UserContoller.removedById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }
    /**
     * 上传图片，返回图片路径
     * @param file
     * @return
     */
    @PostMapping("/uploadImages")
    public ResponseMessage uploadImages(MultipartFile file, HttpServletRequest request){
        String http = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String dataPaths =  DateUtils.getYearAndMonth() + "/";
        String resPath = "user/" + userInfo.getId() + "/images/" + dataPaths;
        String fileName =  UUID.randomUUID().toString() +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            FileUtil.uploadFile(file.getBytes(),filePath + resPath,fileName);
        } catch (Exception e) {
            System.out.println("UserContoller.uploadImages ------- 头像上传失败");
        }
        return new ResponseMessage().success(http + "/fs/"+resPath + fileName);
    }
}

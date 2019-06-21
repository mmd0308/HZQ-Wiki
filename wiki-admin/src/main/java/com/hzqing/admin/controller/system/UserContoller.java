package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.DateUtils;
import com.hzqing.admin.common.utils.FileUtil;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.domain.system.UserInfo;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author hzqing
 * @date 2019-05-17 09:29
 */
@RestController
@RequestMapping("/api/user")
public class UserContoller extends BaseController {

    @Value("${hzq.fs.path}")
    protected String filePath;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, User user){
        startPage(pageNum,pageSize);
        List<User> users = userService.selectList(user);
        return responseMessage(users);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage get(@PathVariable int id){
        User user = userService.get(id);
        return responseMessage(user);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody User user){
        user.setCreateBy(-1);
        user.setUpdateBy(-1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int res = userService.insert(user);
        return responseMessage(res);
    }

    /**
     * 检查密码是否正确
     * @param user
     * @return
     */
    @PostMapping("/checkPass")
    public ResponseMessage checkPass(@RequestBody User user){
        User oUser = userService.get(user.getId());
        boolean matches = passwordEncoder.matches(user.getPassword(),oUser.getPassword());
        return responseMessage(matches);
    }


    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody User user){
        int res = -1;
        user = (User) initAddOrUpdate(user);
        if ( user.getId() ==0){ //新增
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            res = userService.insert(user);
        }else {
            res = userService.update(user);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = userService.deletedById(id);
        return responseMessage(res);
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

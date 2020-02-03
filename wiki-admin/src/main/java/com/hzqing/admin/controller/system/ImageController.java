package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.DateUtils;
import com.hzqing.admin.common.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author hzqing
 * @date 2020-02-03 21:06
 */
@Api(tags = "图片管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/images")
public class ImageController {

    @Value("${hzq.fs.path}")
    protected String filePath;

    @ApiOperation(value = "图片上传接口")
    @PostMapping("/upload")
    public RestResult<String> uploadImages(MultipartFile file, String basePath, HttpServletRequest request){
        RestResult<String> result = RestResultFactory.getInstance().success();
        try{
            String http = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            String path = (basePath + "/images/" + DateUtils.getYearAndMonth()).replaceAll("/+" ,"/");

            String fileName =  UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            FileUtil.uploadFile(file.getBytes(),filePath + "/" +path,fileName);

            String res = ("/fs/" + path + "/"+fileName).replaceAll("/+","/");
            result.setData(http + res);
        }catch (Exception e){
            log.error("ImageController.uploadImages occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }

        return result;
    }
}

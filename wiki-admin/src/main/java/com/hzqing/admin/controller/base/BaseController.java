package com.hzqing.admin.controller.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.DateUtils;
import com.hzqing.admin.common.utils.FileUtil;
import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.model.dto.system.UserInfo;
import com.hzqing.admin.model.entity.base.SimpleBaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author hzqing
 * @date 2019-05-17 09:51
 */

public class BaseController {

    @Value("${hzq.fs.path}")
    protected String filePath;

    public BaseController() {
    }

    protected Object initBaseData(SimpleBaseEntity base){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        base.setCreateTime(LocalDateTime.now());
        base.setUpdateTime(LocalDateTime.now());
        base.setUpdateBy(userInfo.getId());
        base.setCreateBy(userInfo.getId());
        return base;
    }

    protected void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum == 0 ? 1 : pageNum, pageSize == 0 ? 10 : pageSize);
    }

    protected ResponseMessage responseMessage(List pageList) {
        return pageList.size() >= 0 ? ResponseMessage.successPage(pageList, (new PageInfo(pageList)).getTotal()) :ResponseMessage.error();
    }

    protected ResponseMessage responseMessage(Object data) {
        return data == null ? ResponseMessage.error() :ResponseMessage.success(data);
    }
    protected ResponseMessage responseMessage(int code,Object data,String message) {
        return new ResponseMessage(code, data,message,0);
    }

}

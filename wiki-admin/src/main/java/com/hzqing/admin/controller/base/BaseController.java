package com.hzqing.admin.controller.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.domain.system.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:51
 */

public class BaseController {

    public BaseController() {
    }


    protected Object initAddOrUpdate(Base base){
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

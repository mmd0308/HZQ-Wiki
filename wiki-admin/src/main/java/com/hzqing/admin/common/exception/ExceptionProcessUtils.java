package com.hzqing.admin.common.exception;


import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.result.BaseRestResult;

/**
 * @author hzqing
 * @date 2019-08-11 20:57
 */
public class ExceptionProcessUtils {

    /**
     * 出现异常，根据异常，设置错误返回码及提示信息
     * @param result
     * @param e
     */
    public static void wrapperHandlerException(BaseRestResult result, Exception e){
        try {
            ExceptionConvertUtils.convertException2Biz(result,e);
        } catch (Exception ex){
            result.setCode(RestResultCodeConstants.SYS_ERROR.getCode());
            result.setMsg(RestResultCodeConstants.SYS_ERROR.getMsg());
        }
    }
}

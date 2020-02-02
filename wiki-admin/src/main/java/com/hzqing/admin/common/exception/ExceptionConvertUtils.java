package com.hzqing.admin.common.exception;


import com.hzqing.admin.common.exception.support.ParamsValidateException;
import com.hzqing.admin.common.result.BaseRestResult;

/**
 * 主要用户异常转换
 * @author hzqing
 * @date 2019-08-11 20:00
 */
public class ExceptionConvertUtils {

    /**
     * 判断异常类型，进行参数转换
     * @param result
     * @param e
     * @return
     * @throws Exception
     */
    public static BaseRestResult convertException2Biz(BaseRestResult result, Exception e) throws Exception {
        if (!(e instanceof Exception)){ return null; }

        // 参数异常
        if (e instanceof ParamsValidateException){
            result.setCode(((ParamsValidateException) e).getCode());
            result.setMsg(((ParamsValidateException) e).getMsg());
        }if (e instanceof BaseException){
            result.setCode(((BaseException) e).getCode());
            result.setMsg(((BaseException) e).getMsg());
        } else if (e instanceof Exception) {
            // 该异常服务端处理不了，扔给服务调用者
            throw e;
        }

        return result;
    }
}

package com.hzqing.admin.common.result;


import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.result.support.PageRestResult;

/**
 * 创建响应结果对象工厂
 * @author hzqing
 * @date 2019-08-11 15:38
 */
public class RestResultFactory<T> {


    public RestResult success(){
        return success(null);
    }

    public RestResult success(T attributes){
        return success(RestResultCodeConstants.SUCCESS.getMsg(),
                attributes);
    }

    public RestResult success(String msg, T attributes){
        return build(RestResultCodeConstants.SUCCESS.getCode(),
                msg,
                attributes);
    }

    public RestResult error(){
        return build(RestResultCodeConstants.SYS_ERROR.getCode(),
                RestResultCodeConstants.SYS_ERROR.getMsg());
    }

    public RestResult error(String msg){
        return build(RestResultCodeConstants.SYS_ERROR.getCode(),
                msg);
    }

    /**
     * 构建只返回状态和提示信息
     * @param code
     * @param msg
     * @return
     */
    public RestResult build(String code, String msg){
        return build(code,msg,null);
    }

    /**
     * 构建返回数据
     * @param code 返回编码
     * @param msg   返回的提示信息
     * @param attributes 返回的数据
     * @return
     */
    public RestResult<T> build(String code, String msg, T attributes){
        return new RestResult(code,msg,attributes);
    }


    /**
     * 获取工程实例
     * @return
     */
    public static RestResultFactory getInstance() {
        return InnerResutl.INSTANCE;
    }

    /**
     * 私有构造参数，工厂采用单例
     */
    private RestResultFactory() {
    }

    static class InnerResutl{
        private static RestResultFactory INSTANCE = new RestResultFactory();
    }
}

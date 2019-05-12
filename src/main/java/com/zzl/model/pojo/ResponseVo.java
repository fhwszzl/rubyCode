/*
 * 文件名：ResponseVo.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ResponseVo.java
 * 修改人：bole
 * 修改时间：2016年3月29日
 * 修改内容：新增
 */
package com.zzl.model.pojo;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * @param <T>
 * 
 * <pre>
 * </pre>
 * 
 * @author bole
 */
public class ResponseVo<T> {

    /**
     * 是否成功.
     */
    private Boolean success;

    /**
     * 描述信息.
     */
    private String msg;

    /**
     * 结果.
     */
    private T result;

    /**
     * 
     * 构造函数.
     *
     */
    public ResponseVo() {

    }

    /**
     * 
     * 构造函数.
     * 
     * @param success
     *            是否成功
     * @param result
     *            结果
     */
    public ResponseVo(Boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    /**
     * 
     * 构造函数.
     * 
     * @param success
     *            是否成功
     * @param msg
     *            描述信息
     * @param result
     *            结果
     */
    public ResponseVo(Boolean success, String msg, T result) {
        this.success = success;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 设置success.
     * 
     * @return 返回success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 获取success.
     * 
     * @param success
     *            要设置的success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 设置msg.
     * 
     * @return 返回msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获取msg.
     * 
     * @param msg
     *            要设置的msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 设置result.
     * 
     * @return 返回result
     */
    public T getResult() {
        return result;
    }

    /**
     * 获取result.
     * 
     * @param result
     *            要设置的result
     */
    public void setResult(T result) {
        this.result = result;
    }
}

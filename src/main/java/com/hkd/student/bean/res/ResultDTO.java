package com.hkd.student.bean.res;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultDTO<T> {
    private String errCode;
    private String errMsg;
    private Boolean success=Boolean.TRUE;
    private T data;
    private Long total;
    private Map<String,Object> attributes = new HashMap<>();

    private ResultDTO(){

    }
    public void addAttr(String key,Object value){
        this.attributes.put(key,value);
    }
    public static<T> ResultDTO<T> buildSuccess(T t,Long total ){
        ResultDTO<T> resultDTO = buildSuccess(t);
        resultDTO.setTotal(total);
        return resultDTO;
    }

    public static<T> ResultDTO<T> buildEmptySuccess(){
        return new ResultDTO<T>() ;
    }

    public static<T> ResultDTO<T> buildSuccess(T t){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setData(t);
        return resultDTO;
    }

    public static<T> ResultDTO<T> buildFailure(String code,String errMsg){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setErrCode(code);
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static<T> ResultDTO<T> buildFailure(String errMsg){
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(false);
        return resultDTO;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Boolean getSuccess() {
        return success;
    }



    public Long getTotal() {
        return total;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}

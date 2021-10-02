package com.hkd.student.ex;

import com.hkd.student.bean.res.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExAdvice {
   /* @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO<String> handlEx(Exception e){
        return ResultDTO.buildFailure("系统出现问题!!!");
    }*/
}

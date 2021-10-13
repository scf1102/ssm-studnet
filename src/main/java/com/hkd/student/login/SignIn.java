package com.hkd.student.login;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.res.ResultDTO;

import javax.servlet.http.HttpSession;

public interface SignIn {

    default ResultDTO<String> login(LoginReq loginReq, HttpSession session) {
        //通用逻辑：登录
        return doLogin(loginReq, session);
    }

    ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session);

    String getHandleType();
}

package com.hkd.student.login.ctx;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.login.SignIn;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginCtx {

    @Resource
    //private SignIn signIn;
    /*无法识别注入哪一个signin @Qualifier
     * NoUniqueBeanDefinitionException:
     * List<SignIn>:spring会注入所有实现的SignIn的bean
     * */
    private List<SignIn> signIns;

    private Map<String,SignIn> map = new HashMap<>();

    @PostConstruct
    public void init(){
        signIns.forEach(signIn -> map.put(signIn.getHandleType(),signIn));
    }

    public ResultDTO<String> login(LoginReq loginReq, HttpSession session) {
        //通用逻辑：登录
        SignIn signIn = map.get(loginReq.getLoginType());
        if (signIn == null){
            throw new RuntimeException("登录类型不匹配");
        }
        return signIn.login(loginReq,session);
    }

}

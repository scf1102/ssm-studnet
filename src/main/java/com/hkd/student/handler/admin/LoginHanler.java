package com.hkd.student.handler.admin;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginHanler {

    @Autowired
    private StudentService studentService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO<String> login(@RequestBody LoginReq loginReq, HttpSession session){

        return ResultDTO.buildSuccess("");
    }
}

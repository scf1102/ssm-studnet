package com.hkd.student.login.impl;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.enums.LoginTypeEnum;
import com.hkd.student.login.SignIn;
import com.hkd.student.service.AdminService;
import com.hkd.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class StudentEmailSingImpl implements SignIn {

    @Resource
    private StudentService studentService;

    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)) {
            return ResultDTO.buildFailure("邮箱或验证码不能为空");
        }

        StudentDO param = new StudentDO();
        param.setEmail(loginName);
        StudentDTO dto = studentService.selectOne(param);
        if (dto == null) {
            return ResultDTO.buildFailure("邮箱不存在");
        }
        //假设手机号就是验证吗
        if (!loginName.equals(secretCode)) {
            return ResultDTO.buildFailure("验证码错误");
        }
        //学生信息存放到session
        session.setAttribute("student", dto);
        return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.EMAIL.getType();
    }
}

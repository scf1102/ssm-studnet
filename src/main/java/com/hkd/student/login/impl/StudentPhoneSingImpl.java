package com.hkd.student.login.impl;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.enums.LoginTypeEnum;
import com.hkd.student.login.SignIn;
import com.hkd.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class StudentPhoneSingImpl implements SignIn {

    @Resource
    private StudentService studentService;

    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)) {
            return ResultDTO.buildFailure("手机号或验证码不能为空");
        }
        //校验手机格式，发送的验证码
        StudentDO param = new StudentDO();
        param.setPhone(loginName);
        StudentDTO dto = studentService.selectOne(param);
        if (dto == null) {
            return ResultDTO.buildFailure("手机号不存在");
        }
        //假设手机号就是验证吗
        if (!loginName.equals(secretCode)) {
            return ResultDTO.buildFailure("验证码错误");
        }
        //学生信息存放到session
        session.setAttribute("student", dto);
        System.out.println(dto.getId());
        return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.PHONE.getType();
    }
}

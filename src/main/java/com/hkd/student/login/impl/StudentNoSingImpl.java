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
public class StudentNoSingImpl implements SignIn {

    @Resource
    private StudentService studentService;

    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)) {
            return ResultDTO.buildFailure("学生密码或者账号不能为空");
        }
        StudentDO param = new StudentDO();
        param.setNo(loginName);
        StudentDTO dto = studentService.selectOne(param);
        if (dto == null) {
            return ResultDTO.buildFailure("学生信息不存在");
        }
        if (!dto.getNo().equals(secretCode)) {
            return ResultDTO.buildFailure("学生密码登录密码错误");
        }
//            学生信息存放到session
        session.setAttribute("student", dto);
        return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.NO.getType();
    }
}

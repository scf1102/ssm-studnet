package com.hkd.student.login.impl;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.entity.AdminDo;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.enums.LoginTypeEnum;
import com.hkd.student.login.SignIn;
import com.hkd.student.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class AdminSingImpl implements SignIn {
    @Resource
    private AdminService adminService;

    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        //通用逻辑
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();

        if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)) {
            return ResultDTO.buildFailure("管理员密码或者账号不能为空");
        }
        AdminDo param = new AdminDo();
        param.setAdminName(loginName);
        param.setPwd(secretCode);
        ResultDTO<AdminDo> resultDTO = adminService.validateLogin(param);
        if (resultDTO.getSuccess()) {
            session.setAttribute("admin", resultDTO.getData());
            return ResultDTO.buildSuccess("登陆成功");
        }
        return ResultDTO.buildFailure(resultDTO.getErrMsg());
    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.ADMIN.getType();
    }
}

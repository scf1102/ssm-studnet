package com.hkd.student.handler.admin;

import com.hkd.student.bean.dto.LoginReq;
import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.AdminDo;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.enums.LoginTypeEnum;
import com.hkd.student.service.AdminService;
import com.hkd.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginHanler {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    //跳转到登录界面
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //跳转到登录界面
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO<String> login(@RequestBody LoginReq loginReq, HttpSession session){

        //判断登录类型是否在允许的范围内
        String loginType = loginReq.getLoginType();//登录类型
       // System.out.println(loginType);
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.getByType(loginType);
        if (loginTypeEnum == null){
            return ResultDTO.buildFailure("登录类型错误");
        }
        //通用逻辑
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if (LoginTypeEnum.ADMIN.equals(loginTypeEnum)){//管理员登录
            if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)){
                return ResultDTO.buildFailure("管理员密码或者账号不能为空");
            }
            AdminDo param = new AdminDo();
            param.setAdminName(loginName);
            param.setPwd(secretCode);
            ResultDTO<AdminDo> resultDTO = adminService.validateLogin(param);
            if (resultDTO.getSuccess()){
                session.setAttribute("admin",resultDTO.getData());
                return ResultDTO.buildSuccess("登陆成功");
            }
            return ResultDTO.buildFailure(resultDTO.getErrMsg());
        }else if (LoginTypeEnum.NO.equals(loginTypeEnum)){//学生使用学号登录
            if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)){
                return ResultDTO.buildFailure("学生密码或者账号不能为空");
            }
            StudentDO param = new StudentDO();
            param.setNo(loginName);
            StudentDTO dto = studentService.selectOne(param);
            if (dto == null){
                return ResultDTO.buildFailure("学生信息不存在");
            }
            if (!dto.getNo().equals(secretCode)){
                return ResultDTO.buildFailure("学生密码登录密码错误");
            }
//            学生信息存放到session
            session.setAttribute("student",dto);
            return ResultDTO.buildSuccess(String.valueOf(dto.getId()));

        }else if (LoginTypeEnum.PHONE.equals(loginTypeEnum)){//学生使用手机号登录
            if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)){
                return ResultDTO.buildFailure("手机号或验证码不能为空");
            }
            //校验手机格式，发送的验证码
            StudentDO param = new StudentDO();
            param.setPhone(loginName);
            StudentDTO dto = studentService.selectOne(param);
            if (dto == null){
                return ResultDTO.buildFailure("手机号不存在");
            }
            //假设手机号就是验证吗
            if (!loginName.equals(secretCode)){
                return ResultDTO.buildFailure("验证码错误");
            }
            //学生信息存放到session
            session.setAttribute("student",dto);
            System.out.println(dto.getId());
            return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
        }else { //使用邮箱登录
            if (!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode)){
                return ResultDTO.buildFailure("邮箱或验证码不能为空");
            }

            StudentDO param = new StudentDO();
            param.setEmail(loginName);
            StudentDTO dto = studentService.selectOne(param);
            if (dto == null){
                return ResultDTO.buildFailure("邮箱不存在");
            }
            //假设手机号就是验证吗
            if (!loginName.equals(secretCode)){
                return ResultDTO.buildFailure("验证码错误");
            }
            //学生信息存放到session
            session.setAttribute("student",dto);
            return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
        }
        //return ResultDTO.buildSuccess("ok");
    }
}

package com.hkd.student.service.impl;

import com.hkd.student.bean.entity.AdminDo;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.mapper.AdminMapper;
import com.hkd.student.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public ResultDTO<AdminDo> validateLogin(AdminDo param) {
        AdminDo adminDo = adminMapper.validateLogin(param.getAdminName());
        if (adminDo == null){
            return ResultDTO.buildFailure("管理员不存在");
        }
        if (!adminDo.getPwd().equals(param.getPwd())){
            return ResultDTO.buildFailure("管理员密码不正确");
        }
        return ResultDTO.buildSuccess(adminDo);
    }
}

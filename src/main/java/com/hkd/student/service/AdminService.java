package com.hkd.student.service;

import com.hkd.student.bean.entity.AdminDo;
import com.hkd.student.bean.res.ResultDTO;

public interface AdminService {

    ResultDTO<AdminDo> validateLogin(AdminDo adminDo);
}

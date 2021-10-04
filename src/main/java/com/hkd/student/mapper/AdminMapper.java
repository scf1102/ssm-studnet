package com.hkd.student.mapper;

import com.hkd.student.bean.entity.AdminDo;

public interface AdminMapper {

    AdminDo validateLogin(String loginName);
}

package com.hkd.student.interceptor;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.AdminDo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        StudentDTO studentDTO = (StudentDTO) request.getSession().getAttribute("student");
        if (studentDTO == null){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return false;
        }
        return true;
    }
}

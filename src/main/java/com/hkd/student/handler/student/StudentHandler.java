package com.hkd.student.handler.student;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.bean.res.TableDTO;
import com.hkd.student.handler.groups.Add;
import com.hkd.student.handler.groups.Update;
import com.hkd.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentHandler {
    @Resource
    private StudentService studentService;

    @GetMapping("/toUpdate")
    public String toUpdate(){
        return "student/update";
    }

}

package com.hkd.student.service;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;

import java.util.List;

public interface StudentService {
    ResultDTO<String> addStudent(StudentDTO studentDTO);

    ResultDTO<List<StudentDTO>> listStudentByPage(StudentDTO dto);

    StudentDTO selectOne(StudentDO studentDO);

    ResultDTO<String> updateStudent(StudentDTO studentDTO);
}

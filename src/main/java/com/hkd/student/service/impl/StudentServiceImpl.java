package com.hkd.student.service.impl;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.convert.StudentConvert;
import com.hkd.student.mapper.StudentMapper;
import com.hkd.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentConvert studentConvert;
    @Override
    public ResultDTO<String> addStudent(StudentDTO studentDTO) {
        //把dto转为do
        StudentDO studentDO = studentConvert.dto2do(studentDTO);

        int result = studentMapper.insert(studentDO);

        if (result == 1){
            return ResultDTO.buildSuccess("添加学生成功");
        }else {
            return ResultDTO.buildFailure("添加学生失败");
        }

    }

    @Override
    public ResultDTO<List<StudentDTO>> listStudentByPage(StudentDTO dto) {
        List<StudentDO> dos = studentMapper.listStudentByPage(dto);
        List<StudentDTO> dtos = studentConvert.dos2dtos(dos);
        Long total = studentMapper.selectCount(dto);
        return ResultDTO.buildSuccess(dtos,total);
    }
}
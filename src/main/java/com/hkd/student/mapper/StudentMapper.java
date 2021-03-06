package com.hkd.student.mapper;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;

import java.util.List;

public interface StudentMapper {

  public int insert(StudentDO studentDO);

  List<StudentDO> listStudentByPage(StudentDO dto);

  Long selectCount(StudentDO dto);

  StudentDO selectOne(StudentDO studentDO);

  int updateStudent(StudentDO studentDO);

    void deleteStudent(List<Long> idsToDelete);
}

package com.hkd.student.handler.admin;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.bean.res.TableDTO;
import com.hkd.student.handler.groups.Add;
import com.hkd.student.handler.groups.Update;
import com.hkd.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class StudentManageHandler {
    @Resource
    private StudentService studentService;
    //跳转到首页
    @GetMapping("/toStudentManage")
    public String toStudentManage(HttpServletRequest request){
        String pageNow = request.getParameter("pageNow");
        if (pageNow == "" || pageNow == null){
            request.setAttribute("pageNow",1);
        }else {
            request.setAttribute("pageNow",Integer.parseInt(pageNow));
        }
        return "admin/student/manage";
    }

    //跳转新增页面
    @GetMapping("/toStudentAdd")
    public String toStudentAdd(){
        return "admin/student/add";
    }

    //跳转新增页面
    @PostMapping("/addStudent")
    @ResponseBody
    public ResultDTO addStudent(@Validated(Add.class) StudentDTO dto){

       /* if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : fieldErrors){
                sb.append("属性").append(fe.getField()).append("校验不通过，原因：")
                        .append(fe.getDefaultMessage()).append("，当前传入的值是")
                        .append(fe.getRejectedValue()).append("|");
            }
            return ResultDTO.buildFailure(sb.toString());
        }*/
       try {
          return   studentService.addStudent(dto);
       }catch (Exception e){
           throw new RuntimeException("添加学生异常");
       }
       // return   studentService.addStudent(dto);
    }
    //分页查询学生
    @PostMapping("/listStudentByPage")
    @ResponseBody
    public ResultDTO<TableDTO<StudentDTO>> listStudentByPage(StudentDTO dto){
        try {
            TableDTO<StudentDTO> tableDTO = new TableDTO<>();
            ResultDTO<List<StudentDTO>> resultDTO = studentService.listStudentByPage(dto);
            tableDTO.setRows(resultDTO.getData());
            tableDTO.setTotalCount(resultDTO.getTotal());
            return ResultDTO.buildSuccess(tableDTO);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //修改学生页信息
    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam(value = "id",required = true) Long id
            , @RequestParam("pageNow") Integer pageNow, Model model){

        if (id != null){
            StudentDO studentDO = new StudentDO();
            studentDO.setId(id);
            StudentDTO dto=studentService.selectOne(studentDO);
            model.addAttribute("updateStudent",dto);
        }
        if (pageNow == null){
            pageNow = 1;
        }
        model.addAttribute("pageNow",pageNow);
        return "admin/student/update";
    }
    //修改学生信息
    @PostMapping("/updateStudent")
    @ResponseBody
    public ResultDTO<String> updateStudent(@Validated(Update.class) StudentDTO studentDTO){
        try {
            return studentService.updateStudent(studentDTO);
        }catch (Exception e){
            throw new RuntimeException("修改学生信息异常");
        }
    }

    //删除学生
    @PostMapping("/deleteStudentByIds")
    @ResponseBody
    public ResultDTO<String> deleteStudentByIds(@RequestBody StudentDTO studentDTO) {
        List<Long> idsToDelete = studentDTO.getIdsToDelete();
        if (idsToDelete == null || idsToDelete.isEmpty()) {
            return ResultDTO.buildFailure("学生ID为空");
        }
        try {
          return   studentService.deleteStudent(idsToDelete);
        } catch (Exception e) {

            throw new RuntimeException("删除学生信息失败");
        }

    }

}

package com.hkd.student.handler.student;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.bean.res.ResultDTO;
import com.hkd.student.bean.res.TableDTO;
import com.hkd.student.handler.groups.Add;
import com.hkd.student.handler.groups.Update;
import com.hkd.student.service.StudentService;
import com.hkd.student.util.BasePathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentHandler {
    @Resource
    private StudentService studentService;

    private static final String UP_DIR="resources/uploads";

    @GetMapping("/toUpdate")
    public String toUpdate(@RequestParam Long id,Model model){
        if (id != null){
            StudentDO studentDO = new StudentDO();
            studentDO.setId(id);
            StudentDTO studentDTO = studentService.selectOne(studentDO);
            model.addAttribute("dto",studentDTO);
        }

        return "student/update";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResultDTO<String> upload(@RequestParam("photoImgFile") MultipartFile multipartFile
    ,HttpServletRequest request){
        if (multipartFile.isEmpty()){
            return ResultDTO.buildFailure("未上传文件或上传的文件为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)){
            return ResultDTO.buildFailure("上传的文件名字为空");
        }
        //获取项目根目录/UP_DIR的绝对路径
        String realPath = request.getServletContext().getRealPath("/" + UP_DIR);
        //重命名文件夹
        String newFileName = reName(originalFilename);
        String absPathToStore=realPath+"/"+newFileName;
        //进行文件存储
        doStore(absPathToStore,multipartFile);
        //获取上文件被存储的可访问路径
        //http://localhost:8080/resources/uploads/1.jpg
        String addressPhotoPath=BasePathUtil.getBasePath(request)+UP_DIR+"/"+newFileName;

        return ResultDTO.buildSuccess(addressPhotoPath);
    }

    private void doStore(String absPathToStore, MultipartFile multipartFile) {
        File desFile = new File(absPathToStore);
        //如果父级目录不存在
        File parentFile = desFile.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdir();
        }
        try {
            multipartFile.transferTo(desFile);
        } catch (IOException e) {

            throw new RuntimeException("学生照片出错");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultDTO<String> update(@Validated(Update.class) StudentDTO studentDTO){
        try {
            return studentService.updateStudent(studentDTO);
        }catch (Exception e){
            throw new RuntimeException("修改学生信息异常");
        }

    }
    private String reName(String originalFilename) {
        //获取新文件名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return System.nanoTime()+suffix;
    }

}

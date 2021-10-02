package com.hkd.student.convert;

import com.hkd.student.bean.dto.StudentDTO;
import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.util.BasePathUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StudentConvert {

    //注入request
    @Resource
    private HttpServletRequest request;

    public abstract StudentDO dto2do(StudentDTO dto);

    public abstract StudentDTO do2dto(StudentDO studentDO);

    //图片路径转化 do -> dto
    @AfterMapping
    protected void afterDo2Dto(StudentDO studentDO, @MappingTarget StudentDTO studentDTO){
        String photoPath = studentDO.getPhotoPath();
        if (StringUtils.hasText(photoPath)){
            String addressPhotoPath = BasePathUtil.getBasePath(request) + photoPath;
            studentDTO.setAddressPhotoPath(addressPhotoPath);
        }
    }

    //图片路径转化 dto -> do
    @AfterMapping
    protected void afterDto2Do(StudentDTO studentDTO, @MappingTarget StudentDO studentDO){
        String addressPhotoPath = studentDTO.getAddressPhotoPath();
        if (StringUtils.hasText(addressPhotoPath)){
            String basePath = BasePathUtil.getBasePath(request);
            String photoPath = addressPhotoPath.substring(basePath.length());
            studentDO.setPhotoPath(photoPath);
        }
    }

    public abstract List<StudentDTO> dos2dtos(List<StudentDO> studentDos);
}

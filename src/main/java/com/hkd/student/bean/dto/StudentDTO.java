package com.hkd.student.bean.dto;

import com.hkd.student.bean.entity.StudentDO;
import com.hkd.student.constans.Constans;
import lombok.Data;

@Data
public class StudentDTO extends StudentDO {
    //当前是第几页
    private Integer pageNow;
    //每页展示几条数据
    private Integer pageSize;
    private Integer start;

    //图片可访问的路径
    private String addressPhotoPath;


    public Integer getStart(){
        if (getPageNow() == null){
            return null;
        }
        return (getPageNow() - 1) * getPageSize();
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize != null ? pageSize : Constans.PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getAddressPhotoPath() {
        return addressPhotoPath;
    }

    public void setAddressPhotoPath(String addressPhotoPath) {
        this.addressPhotoPath = addressPhotoPath;
    }
}

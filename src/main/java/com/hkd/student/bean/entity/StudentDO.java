package com.hkd.student.bean.entity;

import com.hkd.student.handler.groups.Add;
import com.hkd.student.handler.groups.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentDO {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotBlank(groups = {Add.class, Update.class})
    private String no;
    @NotBlank(groups = {Add.class, Update.class})
    private String realName;
    @NotBlank(groups = {Add.class, Update.class})
    private String birthDay;
    @NotBlank(groups = {Add.class, Update.class})
    private String phone;
    @NotBlank(groups = {Add.class, Update.class})
    private String email;
    private String photoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}

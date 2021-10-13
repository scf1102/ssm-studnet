<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生角色使用-修改学生界面</title>
    <style>
        .gj-datepicker-md [role="right-icon"]{
            top: 8px !important;
        }
        .photo-size{
            width: 50px;
        }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="../top.jsp"/>
        <div class="row" id="cont">
            <section class="col-sm-8 offset-sm-2">
                <form id="updateForm" novalidate>
                    <%--第一行--%>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>系统识别：</label>
                            <input type="text" class="form-control" name="id"
                                   value="" readonly>
                        </div>
                        <div class="form-group col-md-6">
                            <label>学号：</label>
                            <input type="text" class="form-control" name="no"
                                   value="" required>
                            <div class="invalid-feedback">学号不能为空</div>
                        </div>
                    </div>
                        <%--第二行--%>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>姓名：</label>
                                <input type="text" class="form-control" name="realName"
                                       value="" required>
                                <div class="invalid-feedback">姓名不能为空</div>
                            </div>
                            <div class="form-group col-md-6">
                                <label>生日：</label>
                                <input type="text" class="form-control" name="birthDay"
                                       id="birthDay" value="" required readonly>
                                <div class="invalid-feedback">生日不能为空</div>
                            </div>
                        </div>
                        <%--第三行--%>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>手机：</label>
                                <input type="text" class="form-control" name="phone" autocomplete="off"
                                       value="" required>
                                <div class="invalid-feedback">手机号不能为空</div>
                            </div>
                            <div class="form-group col-md-6">
                                <label>邮箱：</label>
                                <input type="email" class="form-control" name="email"
                                        value="" required >
                                <div class="invalid-feedback">请输入正确的邮箱格式</div>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="d-block">免冠照：</label>
                            <div class="img-show">
                                <img class="img-fluid photo-size"
                                     src="" onclick="" id="photoImg"/>
                            </div>
                        </div>
                        <div class="d-flex justify-content-around">
                            <input type="button" onclick="updateStudnet()" class="btn btn-primary" value="修改">
                            <<a href="<%=request.getContextPath()%>/toLogin" class="btn btn-primary" >返回</a>
                        </div>

                </form>
            </section>
        </div>
        <jsp:include page="../bottom.jsp"/>
    </div>
<script>
    $("#birthDay").datepicker({
        local:"zh-cn",
        format:"yyyy-mm-dd",
        weekStartDay:1 //周一到周日：1-7
    });

    function updateStudnet() {
        /*
         1:在form表单上添加Novalidate
         2：在form表单上写上校验规则
         3：表单提交时进行相应的校验
         */
        var validateResult = document.getElementById("updateForm").checkValidity();
        $("#updateForm").addClass("was-validated");
        if (!validateResult){
            return;
        }
        //提交数据到后台
        //console.log(formDataObj("#addForm"));
        //修改学生信息
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/admin/updateStudent',
            data:formDataObj("#updateForm"),
            success:function (res) {
                if (res.success){
                    location.href='<%=request.getContextPath()%>/admin/toStudentManage?pageNow=<%=request.getAttribute("pageNow")%>'
                }else{
                    $("#tipCont").text(res.errMsg);
                    $("#tipModal").modal("show");
                }
            }
        })


    }
</script>
</body>
</html>

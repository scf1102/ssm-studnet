<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理页面</title>
    <style>
        .gj-datepicker-md [role="right-icon"]{
            top: 8px !important;
        }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="../../top.jsp"/>
        <div class="row">
            <section class="col-sm-4 offset-sm-4">
                <form id="addForm" novalidate>
                    <div class="form-group">
                        <label for="no">
                            学号：
                        </label>
                        <input type="text" class="form-control" id="no" name="no" required/>
                        <div class="invalid-feedback">学号不能为空</div>
                    </div>
                    <div class="form-group">
                        <label for="realName">
                            姓名：
                        </label>
                        <input type="text" class="form-control" id="realName" name="realName" required/>
                        <div class="invalid-feedback">姓名不能为空</div>
                    </div>
                    <div class="form-group">
                        <label for="birthDay">
                            生日：
                        </label>
                        <input type="text" readonly class="form-control" id="birthDay" name="birthDay" required/>
                        <div class="invalid-feedback">生日不能为空</div>
                    </div>
                    <div class="form-group">
                        <label for="phone">
                            手机：
                        </label>
                        <input type="text" class="form-control" id="phone" name="phone" autocomplete="off" required/>
                        <div class="invalid-feedback">手机号不能为空</div>
                    </div>
                    <div class="form-group">
                        <label for="email">
                            邮箱：
                        </label>
                        <input type="email" class="form-control" id="email" name="email" required/>
                        <div class="invalid-feedback">请输入正确的邮箱格式</div>
                    </div>
                    <div class="d-flex justify-content-around">
                        <input type="button" onclick="doAddStudent()" class="btn btn-primary" value="修改">
                        <a href="#" class="btn btn-primary" onclick="history.back()">返回</a>
                    </div>
                </form>
            </section>
        </div>
        <jsp:include page="../../bottom.jsp"/>
    </div>
<script>
    $("#birthDay").datepicker({
        local:"zh-cn",
        format:"yyyy-mm-dd",
        weekStartDay:1 //周一到周日：1-7
    });

    function doAddStudent() {
        /*
         1:在form表单上添加Novalidate
         2：在form表单上写上校验规则
         3：表单提交时进行相应的校验
         */
        var validateResult = document.getElementById("addForm").checkValidity();
        $("#addForm").addClass("was-validated");
        if (!validateResult){
            return;
        }
        //提交数据到后台
        //console.log(formDataObj("#addForm"));



    }
</script>
</body>
</html>

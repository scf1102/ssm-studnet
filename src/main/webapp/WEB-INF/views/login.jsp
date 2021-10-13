<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理页面</title>
    <style>
        .photo-size{
            width: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="top.jsp"/>
        <div class="row">
            <section class="col-sm-4 offset-sm-4">
                <form id="loginForm">
                    <div class="form-group">
                        <label>用户名：</label>
                        <input type="text" name="loginName" class="form-control">
                    </div>

                    <div class="form-group">
                        <label>密码：</label>
                        <input type="password" name="secretCode" class="form-control">
                    </div>

                    <div class="form-group">
                        <label>管理员登录：</label>
                        <div class="form-check-inline form-check">
                            <input type="radio" name="loginType" checked
                                   class="form-check-input" id="admin" value="admin">
                            <label class="form-check-inline" for="admin">管理员</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>学生登录：</label>
                        <div class="form-check-inline form-check">
                            <input type="radio" name="loginType"
                                   class="form-check-input" id="studentNo" value="studentNo">
                            <label class="form-check-inline" for="studentNo">学号</label>
                        </div>
                        <div class="form-check-inline form-check">
                            <input type="radio" name="loginType"
                                   class="form-check-input" id="studentPhone" value="studentPhone">
                            <label class="form-check-inline" for="studentPhone">手机号</label>
                        </div>
                        <div class="form-check-inline form-check">
                            <input type="radio" name="loginType"
                                   class="form-check-input" id="studentEmail" value="studentEmail">
                            <label class="form-check-inline" for="studentEmail">邮箱</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <input id="loginBtn" type="button" class="btn btn-primary btn-block" value="登录">
                    </div>
                </form>
            </section>



        </div>
        <jsp:include page="bottom.jsp"/>
    </div>
    <script>

        $("#loginBtn").click(function () {
            $.ajax({
                type:"post",
                url:'<%=request.getContextPath()%>/login2',
                data:JSON.stringify(formDataObj("#loginForm")),
                contentType:"application/json",
                success:function (res) {
                    if (res.success){
                        //判断是学生还是管理员登录
                        var loginChecked=$(":radio:checked").val();
                        if ("admin" == loginChecked){
                            location.href='<%=request.getContextPath()%>/admin/toStudentManage';
                        }else{
                            location.href='<%=request.getContextPath()%>/student/toUpdate';
                        }
                    }else {
                        $("#tipCont").text(res.errMsg);
                        $("#tipModal").modal("show");
                    }
                }
            })
        });
    </script>
</body>
</html>

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
                                   value="${requestScope.dto.id}" readonly>
                        </div>
                        <div class="form-group col-md-6">
                            <label>学号：</label>
                            <input type="text" class="form-control" name="no"
                                   value="${dto.no}" required>
                            <div class="invalid-feedback">学号不能为空</div>
                        </div>
                    </div>
                        <%--第二行--%>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>姓名：</label>
                                <input type="text" class="form-control" name="realName"
                                       value="${dto.realName}" required>
                                <div class="invalid-feedback">姓名不能为空</div>
                            </div>
                            <div class="form-group col-md-6">
                                <label>生日：</label>
                                <input type="text" class="form-control" name="birthDay"
                                       id="birthDay" value="${dto.birthDay}" required readonly>
                                <div class="invalid-feedback">生日不能为空</div>
                            </div>
                        </div>
                        <%--第三行--%>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>手机：</label>
                                <input type="text" class="form-control" name="phone" autocomplete="off"
                                       value="${dto.phone}" required>
                                <div class="invalid-feedback">手机号不能为空</div>
                            </div>
                            <div class="form-group col-md-6">
                                <label>邮箱：</label>
                                <input type="email" class="form-control" name="email"
                                        value="${dto.email}" required >
                                <div class="invalid-feedback">请输入正确的邮箱格式</div>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="d-block">免冠照：</label>
                            <div class="img-show">
                                <img class="img-fluid photo-size"
                                     src="${dto.addressPhotoPath}" onclick="openFileSelectDia()" id="photoImg"/>
                                <input type="file" class="d-none" id="fileInput" onchange="fileSelect()">
                                <input type="hidden" name="addressPhotoPath"  id="addressPhotoPath">
                            </div>
                        </div>
                        <div class="d-flex justify-content-around">
                            <input type="button" onclick="updateStudnet()" class="btn btn-primary" value="修改">
                            <a href="<%=request.getContextPath()%>/toLogin" class="btn btn-primary" >返回</a>
                        </div>

                </form>
            </section>
        </div>
        <jsp:include page="../bottom.jsp"/>
    </div>
<script>

    function openFileSelectDia() {
        document.getElementById("fileInput").click();
    }
    //当文件被选择时会调用input(type=file)的onchange事件
    function fileSelect() {
        console.log(document.getElementById("fileInput").files);
        var fileObj=document.getElementById("fileInput").files[0];
        if ( typeof fileObj == "undefined" || fileObj.size <=0) {
            $("#tipCont").text("请选择要上传的图片");
            $("#tipModal").modal("show");
            return;
        }
        //构造formData：ajax提交文件时使用
        var formObj = new FormData();
        formObj.append("photoImgFile",fileObj);
        console.log(formObj);
        //ajax发送文件上传请求
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/student/upload',
            data:formObj,
            //上传文件时要设置为false
            contentType:false,
            //把data数据转为查询字符串(xx?a=1&b=2)
            processData:false,
            //避免浏览器缓存，去请求发送不成功
            cache:false,
            success:function (res) {
                if (res.success){
                    //图片上传的回显
                    $("#photoImg").attr("src",res.data);
                    //把文件上传的路径保存，提交到后台更新时上传到数据库
                    $("#addressPhotoPath").val(res.data);
                }else {
                    $("#tipCont").text(res.errMsg);
                    $("#tipModal").modal("show");
                }
            }
        })
    }

    //修改学生信息
    function updateStudnet() {
        $.ajax({
            type:"post",
            url:'<%=request.getContextPath()%>/student/update',
            data:formDataObj("#updateForm"),
            success:function (res) {
                if (res.success){
                    $("#tipCont").text(res.data);
                    $("#tipModal").modal("show");
                }else {
                    $("#tipCont").text(res.errMsg);
                    $("#tipModal").modal("show");
                }
            }
        })

    }



</script>
</body>
</html>

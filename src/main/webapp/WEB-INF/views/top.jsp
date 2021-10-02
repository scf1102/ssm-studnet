
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/gijgo/css/gijgo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css">
    <script src="<%=request.getContextPath()%>/resources/jquery/jquery-3.3.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/gijgo/js/gijgo.js"></script>
    <script src="<%=request.getContextPath()%>/resources/gijgo/js/messages/messages.zh-cn.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
    <title>top页面</title>
</head>
<body>
    <div class="row my-4">
        <div class="col-sm-10 pr-0">
            <img src="<%=request.getContextPath()%>/resources/imgs/logo.jpg" class="img-fluid">
           <%-- <span>学生管理</span>--%>
        </div>
        <div class="col-sm-2 d-flex justify-content-center align-items-center"
             style="background-color: #0062cc;">
            <a href="#" class="btn btn-outline-warning border-0">退出</a>
        </div>
        <%--提示框--%>
        <div class="modal fade"  data-backdrop="static"  tabindex="-1" id="tipModal">
            <div class="modal-dialog modal-dialog-centered modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >提示信息 </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="tipCont">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
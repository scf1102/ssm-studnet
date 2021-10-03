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
        <jsp:include page="../../top.jsp"/>
        <div class="row">
            <section class="row-sm-12 mb-2">
                <div class="d-flex justify-content-center align-items-center">
                    <a href="<%=request.getContextPath()%>/admin/toStudentAdd" class="btn btn-primary mr-3">
                        新增
                    </a>
                    <button href="" class="btn btn-primary mr-3" onclick="doDelete()">
                        删除
                    </button>
                    <form class="form-inline mb-0" id="searchForm">
                        <input type="text" class="form-control" name="realName" placeholder="请输入用户名">
                        <input type="hidden"  name="pageNow" id="pageNow" value="<%=request.getAttribute("pageNow")%>">
                        <input type="button" class="btn btn-primary ml-3" onclick="doSearch()" value="查询">
                    </form>
                </div>
            </section>
            <%--查询出的学生表格--%>
            <section class="col-sm-12">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>系统标识</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>出生日期</th>
                        <th>电话</th>
                        <th>邮箱</th>
                        <th>免冠照片</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                </table>
            </section>
            <section class="col-sm-12">
                <ul class="pagination">



                </ul>
            </section>
        </div>
        <jsp:include page="../../bottom.jsp"/>
    </div>
    <script>
        function loadPageTable() {
            $.ajax({
                type:"post",
                url:'<%=request.getContextPath()%>/admin/listStudentByPage',
                data:formDataObj("#searchForm"),
                success:function (result) {
                    if (result.success){
                        var tableResult = result.data;
                        showPageHtml(tableResult);
                        showTbodyHtml(tableResult);
                    }else {
                        $("#tipCont").text(result.errMsg);
                        $("#tipModal").modal("show");
                    }
                }
            });
        }
        function showTbodyHtml(tableResult) {
            var rows = tableResult.rows;
            var len  = rows.length;
            var tbodyHtml = '';
            for (var i=0;i<len;i++){
                var oneStudent = rows[i];
                tbodyHtml += '<tr>\n' +
            '                        <td>\n' +
            '                            <div class="form-check">\n' +
            '                                <input type="checkbox" class="form-check-input" name="idsToDelete" value="'+oneStudent["id"]+'"/>\n' +
            '                            </div>\n' +
            '                        </td>\n' +
            '                        <td>'+oneStudent["id"]+'</td>\n' +
            '                        <td>'+oneStudent["no"]+'</td>\n' +
            '                        <td>'+oneStudent["realName"]+'</td>\n' +
            '                        <td>'+oneStudent["birthDay"]+'</td>\n' +
            '                        <td>'+oneStudent["phone"]+'</td>\n' +
            '                        <td>'+oneStudent["email"]+'</td>\n' +
            '                        <td class="text-center">\n' +
            '                            <img class="photo-size" src="'+oneStudent["addressPhotoPath"]+'"/>\n' +
            '                        </td>\n' +
            '                        <td>\n' +
            '                            <a href="<%=request.getContextPath()%>/admin/updateStudent?id='+oneStudent["id"]+ '&pageNow='+ $("#pageNow").val()+' ">更新</a>\n' +
            '                        </td>\n' +
            '                    </tr>'
            }
            $("#tbody").html(tbodyHtml);
            console.log(tableResult);
        }
        function showPageHtml(tableResult) {
            console.log(tableResult);
            var pageHtml="";
            var pageNow=parseInt($("#pageNow").val());
            var pageCount=tableResult["pageCount"];
            if (pageNow != 1) {
                pageHtml +='<li class="page-item"><a class="page-link" href="#" onclick="goFirst()">首页</a></li>\n' +
                    '                    <li class="page-item"><a class="page-link" href="#" onclick="goPre()">上一页</a></li>';
            }
            if (pageNow != pageCount && pageCount != 0){
                pageHtml +='<li class="page-item"><a class="page-link" href="#" onclick="goNext()">下一页</a></li>\n' +
                    '                    <li class="page-item"><a class="page-link" href="#" onclick="goLast('+pageCount+')">尾页</a></li>';
            }
            pageHtml +='<li class="page-item"><span class="page-link">共'+pageCount+'页</span></li>\n' +
                '                    <li class="page-item"><span class="page-link">共'+tableResult["totalCount"]+'条</span></li>\n' +
                '                    <li class="page-item"><span class="page-link">当前是第'+pageNow+'页</span></li>';
            $(".pagination").html(pageHtml);
        }
        function doSearch() {
            $("#pageNow").val(1);
            loadPageTable();
        }
        //首页
        function goFirst(){
            $("#pageNow").val(1);
            loadPageTable();
        }
        //上一页
        function goPre(){
            var curPageNow = $("#pageNow").val();
            var pagePre = parseInt(curPageNow)-1;
            $("#pageNow").val(pagePre);
            loadPageTable();
        }
        //下一页
        function goNext(){
            var curPageNow = $("#pageNow").val();
            var pageNext = parseInt(curPageNow)+1;
            $("#pageNow").val(pageNext);
            loadPageTable();
        }
        //尾页
        function goLast(pageCount){
            $("#pageNow").val(pageCount);
            loadPageTable();
        }
        //删除
        function doDelete(){
            var checkedInputs=$("input[name=idsToDelete]:checked");
            if (!checkedInputs  || checkedInputs.length == 0){
                $("#tipCont").text("请选择要删除的行");
                $("#tipModal").modal("show");
                return ;
            }
            //获取到要删除的ID
            var idsToDelete=[];
            $.each(checkedInputs,function (i, ele) {
                idsToDelete.push($(ele).val());
            });
            //发送ajax请求
            $.ajax({
                type:"post",
                url:'<%=request.getContextPath()%>/admin/deleteStudentByIds',
                contentType:"application/json",
                data:JSON.stringify({"idsToDelete":idsToDelete}),
                success:function (res) {
                    if (res.success){
                        location.href='<%=request.getContextPath()%>/admin/toStudentManage';
                    }else{
                        $("#tipCont").text(res.errMsg);
                        $("#tipModal").modal("show");
                    }
                }
            })
        }

        //执行
        loadPageTable();
    </script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css"></link>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sys_accounts"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">网盘系统</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/new" class="btn"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                                ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>

                    <table class="table">
                        <thead>
                        <button class="btn btn-primary">上传文件</button>
                        <button class="btn btn-success" id="saveBtn" >新建文件夹</button>
                        <tr>
                            <th>#</th>
                            <th>名称</th>
                            <th>大小</th>
                            <th>创建时间</th>
                            <th>创建人</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${diskList}" var="disk">
                            <tr>
                                <td>#</td>
                                <td>${disk.name}</td>
                                <td>${disk.size}</td>
                                <td>${disk.createTime}</td>
                                <td>${disk.createName}</td>
                                <td>
                                    <a href="/pan/del/${disk.id}/del">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/jquery.twbsPagination.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            var fid=${fid};
            layer.prompt({title:"请输入文件夹名称"},function (text,index) {
                layer.close(index);
                $.post("/pan/new",{"fid":fid,name:text}).done(function () {

                }).error(function () {
                    layer.msg("服务器错误");

                })
            })
            
        })
        
    })
</script>
</body>
</html>

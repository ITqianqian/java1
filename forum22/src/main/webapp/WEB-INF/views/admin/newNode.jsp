<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2017/1/2
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/sweetalert.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<div class="container-fluid" style="margin-top:20px">
    <form id="addForm" action="">
        <legend>添加节点</legend>
        <label>节点名称</label>
        <input type="text" name="nodename">
    </form>
    <div class="form-actions">
        <button id="addBtn" class="btn btn-primary">保存</button>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/sweetalert.min.js"></script>
<script>
    $(function () {
        $("#addBtn").click(function () {
            $("#addForm").submit();
        });

        $("#addForm").validate({
            errorElement:"span",
            errorClass:"text-error",
            rules:{
                nodename:{
                    required:true,

                }
            },
            messages:{
                nodename:{
                    required:"请输入节点名称",

                }
            },
            submitHandler:function () {
                $.ajax({
                    url:"/admin/newnode",
                    type:'post',
                    data:$("#updateForm").serialize(),
                    success:function (json) {
                        if (json.state == "success"){
                            swal({title:"添加成功"},function () {
                                window.location.href = "/admin/node";
                            });
                        }else{
                            swal(json.message);
                        }

                    },error:function () {
                        swal("修改失败,服务器异常");
                    }
                });
            }
        })

    });


</script>
</body>
</html>



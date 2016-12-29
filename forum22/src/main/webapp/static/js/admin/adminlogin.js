$(function () {

   $("#loginForm").validate({
       errorElement:"span",
       errorClass:"text-error",
       rules:{
           username:{
               required:true
           },
           password:{
               required:true
           }
       },
       messages:{
           adminname:{
               required:"请输入账号"
           },
           password:{
               required:"请输入密码"
           }
       },
       submitHandler:function (form) {
           S.ajax({
               url:"/admin/login",
               type:"post",
               data:$(form).serialize(),
               beforeSend:function(){
                   $("#loginBtn").text("登录中...").attr("disabled","disabled");
               },
               success:function(data){
                   if(data.state == 'success') {
                           window.location.href = "/admin/home";
                   } else {
                       alert(data.message);
                   }
               },

           })

       }
   })

})
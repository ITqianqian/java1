$(function () {
   $("#type").change(function () {
       var value=$(this).val();
       if(value=="email"){
           $("#typename").text("电子邮件");
       }else if(value="phone"){
           $("#typename").text("电话号码");
       }

   });

   $("#btn").click(function () {
      $("#form").submit();
   });

   $("#form").validate({
      errorElement:"span",
      errorClass:"text-error",
       rules:{
          value:{
              required:true
          }
       },
       messages:{
           value:{
               required:"不能为空"
           }

       },
       submitHandler:function (form){
         $.ajax({
             url:"/foundpassword",
             type:"post",
             data:$(form).serialize(),
             beforeSend:function () {
                 $("#btn").text("提交中...").attr("disabled","disabled");

             },
             success:function (data) {
            if(data.state=="success"){
                var type=$("#type").val();
                if(type=="email"){
                    alert("请查收邮件进行操作");
                }else {
                    //TODO 电话的提示
                }
            }else{
                alert(data.message);
            }
             },
             error:function () {
                 alert("服务器异常")

             },
             complete:function () {
                 $("#btn").text("提交").removeAttr("disabled");

             }
         });


       }
   });
});

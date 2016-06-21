<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/miEstilo.css">
    <link rel="stylesheet" href="/css/jquery.mCustomScrollbar.min.css" />
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/chatSnippet.js"></script>
    <script src="/js/jquery.mCustomScrollbar.concat.min.js"></script>
</head>
<body>
<#include "/header.ftl">
<div class="container" style="margin-left: 30%;width: 40%">
    <div class="panel panel-default" >
        <div class="panel-body" >
            <h2>Chats activos</h2>
            <hr>
            <div id="body"></div>
        </div>
    </div>
</div>
<input type="hidden" id="usuario" value="${usuario.username}">
<input type="hidden" id="nombre" value="${usuario.nombre}">

</body>




<script src="js/chatAdminAutor.js" type="text/javascript"></script>
<script>
    (function($){
        $(window).on("load",function(){
            $(".msg_container_base").mCustomScrollbar();
        });
    })(jQuery);
</script>
</html>


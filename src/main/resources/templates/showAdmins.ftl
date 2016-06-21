<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
<#include "/header.ftl">
<div class="container">



            <div class = "panel panel-default" style="width: 40%; margin-left: 30%">
                <input type="hidden" value="${usuario.username}" name="nombre">
                <div class = "panel-body">
                    <h2 style="margin-auto">Administradores Disponibles</h2>
                    <hr>
                    <ul>

                    <#list administradores as a>
                        <li>
                                <a href="chatRoom/${a.username}/${usuario.username}"><h4>${a.nombre}</h4></a>
                       </li>
                    </#list>
                    </ul>
                </div>
            </div>

       \
</div>

</body>
</html>
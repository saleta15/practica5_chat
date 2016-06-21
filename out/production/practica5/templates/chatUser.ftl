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

        <h1>Redactar</h1>
        <form action="/procesarCrearArticulo/" method="POST">
            <div class = "panel panel-default">
                <div class = "panel-body">
                    <#list administradores as a>
                        <div class="col-lg-12">
                            <a href="/verArticulo/${articulo.id}"><h4>${a.nombre}</h4></a>
                            <hr>
                        </div>
                    </#list>
                </div>
            </div>

        </form>
</div>

</body>
</html>
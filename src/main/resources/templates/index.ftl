<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>

    <#include "/header.ftl">
    <div class="container" style="width:30%">
        <div class="panel panel-default" >

            <div class="panel-body" >
                <h2>Artículos</h2>
                <hr>
                <#list articulos as articulo>
                    <div class="col-lg-12">
                        <a href="/verArticulo/${articulo.id}"><h4>${articulo.titulo}</h4></a>
                        <p>${articulo.fecha}</p>
                        <p>${articulo.preview} </p>
                        <#list articulo.etiquetas as e>
                            <a href="/etiqueta/${e.etiqueta}/1"><button id="etiqueta" class="btn btn-primary">${e.etiqueta}</button></a>
                        </#list>
                        <hr>
                    </div>
                </#list>
            </div>

        </div>
        <ul class="pagination" >
        <#list 1..cantidadPaginas as  i>
            <#if filtrado>
                <li><a href="/etiqueta/${etiqueta}/${i}">${i}</a></li>
            <#else>
                <li><a href="/pagina/${i}">${i}</a></li>
            </#if>

        </#list>
        </ul>

</div>






    <!-- /.row -->






</body>
</html>
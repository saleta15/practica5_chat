<!DOCTYPE html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/miEstilo.css" rel="stylesheet">
</head>

<body>

<#include "/header.ftl">

<div class="container" style="width: 40%">
    <div class="panel panel-default" >

        <div class="panel-body" >
    <div class="row">

        <div class="col-lg-12">

            <h1>${articulo.titulo}</h1>


            <p class="lead">
                por <i>${articulo.autor.nombre}</i>
            </p>

            <p> ${articulo.fecha}</p>
            <#if usuario.administrador || usuario.username == articulo.autor.username>
                <a href="/editarArticulo/${articulo.id}">Editar</a>
            </#if>
            <hr>
            <p id="cuerpo">${articulo.cuerpo}</p>
            <hr>
            <br><br>
            <div class="well">
                <h4>¿Quieres decir algo?</h4>
                <form action="/procesarNuevoComentario/" METHOD="post">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" name="comentario"></textarea>
                    </div>
                    <input type="hidden" value="${articulo.id}" name="articulo">
                    <input type="hidden" value="${usuario.username}" name="username">
                    <button type="submit" class="btn btn-primary">Dejar comentario</button>
                </form>
            </div>
            <h4>Comentarios</h4>
            <hr>
            <#list articulo.comentarios as comentario>
                <div class="media">
                    <div class="media-body">

                        <h4 class="media-heading">${comentario.autor.nombre}</h4>

                    </div>
                </div>
                    <div class="row">
                        <div class="col-md-10">
                            ${comentario.comentario}
                        </div>
                        <#if usuario.administrador || usuario.username == articulo.autor.username>
                            <form method="post" action="/procesarBorrarComentario/">
                                <input type="hidden" name="comentario" value="${comentario.id}"></input>
                                <input type="hidden" name="articulo" value="${articulo.id}"></input>
                                <div class="col-md-2">
                                    <button class="btn btn-danger" >Borrar</button>
                                </div>
                            </form>
                        </#if>

                    </div>

                <hr>
            </#list>
        </div>
                </div >
            </div>
    </div>
    <!-- /.row -->





</body>

</html>

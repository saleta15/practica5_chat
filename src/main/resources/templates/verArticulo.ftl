<!DOCTYPE html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/css/miEstilo.css" rel="stylesheet">
</head>

<body>

<#include "/header.ftl">

<div class="container" style="width: 40%">
    <div class="panel panel-default" >

        <div class="panel-body" >
    <div class="row">

        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-9">
                    <h1>${articulo.titulo}</h1>
                </div>
                <div  class="col-lg-1" style="margin-top: 15px">
                    <#if articulo.valoracion gte 0 >
                     <button class="btn btn-success" style="font-size: 20px; border-radius: 100%">${articulo.valoracion}</button>
                    <#else>
                        <button class="btn btn-danger" style="font-size: 20px; border-radius: 100%">${articulo.valoracion}</button>
                    </#if>

                </div>
            <#if !articulo.estaValorado>
                <div class="col-lg-2" style="margin-top: 16px">
                    <form method="post" action="/megustaArticulo/">

                        <input type="hidden" name="usuario" value="${usuario.username}"></input>
                        <input type="hidden" name="articulo" value="${articulo.id}"></input>
                        <button type="submit" name="action" value="like" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> </button>
                        <button type="submit" name="action" value="dislike" class="btn btn-danger"><span class="glyphicon glyphicon-thumbs-down"></span> </button>

                    </form>
                </div>
            </#if>
            </div>


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
            <br>
            <br>
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
            <#list comentarios as comentario>
                <div class="media">
                    <div class="media-body">

                        <h4 class="media-heading">${comentario.autor.nombre}</h4>

                    </div>
                </div>
                    <div class="row">
                        <div class="col-md-9">
                            ${comentario.comentario}
                        </div>
                        <div class="col-md-1">
                            <#if comentario.valoracion gte 0 >
                                <button class="btn btn-success" style="font-size: 20px; border-radius: 100%">${comentario.valoracion}</button>
                            <#else>
                                <button class="btn btn-danger" style="font-size: 20px; border-radius: 100%">${comentario.valoracion}</button>
                            </#if>
                        </div>
                        <form method="post" action="/megusta/">
                            <div class="col-md-2">
                                <input type="hidden" name="usuario" value="${usuario.username}"></input>
                                <input type="hidden" name="comentario" value="${comentario.id}"></input>
                                <input type="hidden" name="articulo" value="${articulo.id}"></input>
                                <#if !comentario.estaValorado>
                                    <button type="submit" name="action" value="like" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> </button>
                                    <button type="submit" name="action" value="dislike" class="btn btn-danger"><span class="glyphicon glyphicon-thumbs-down"></span> </button>
                                </#if>

                            </div>
                        </form>

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

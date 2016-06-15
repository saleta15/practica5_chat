<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
<#include "/adminHeader.ftl">
<div class="container" id="contenedorCrearUsuario">

        <h1>Editar Usuario</h1>
        <form action="/procesarEditarUsuario/" method="POST">
            <div class = "panel panel-default">
                <div class = "panel-body">
                    <input type="hidden" value="${usuario.username}" class="form-control" id="username" name="username" required >
                    <input type="hidden" value="${usuario.administrador?string}" class="form-control" id="username" name="administrador" required >
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="nombre">Nombre</label>
                                <input type="text" value="${usuario.nombre}" class="form-control" id="nombre" name="nombre" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="password">Contraseña</label>
                                <input type="password" value="${usuario.password}" class="form-control" id="password" name="password" required>
                            </div>
                        </div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class ="col-md-9">
                            <div class="form-group">
                                <#if usuario.autor>
                                    <label class="checkbox-inline"><input type="checkbox" name="autor" value="true" checked><b>Puede redactar artículos</b></label>
                                <#else>
                                    <label class="checkbox-inline"><input type="checkbox" name="autor" value="true"><b>Puede redactar artículos</b></label>
                                </#if>

                            </div>
                        </div>
                    </div>
                    <br>
                    <button type="submit" name="action" id="btnEditarUsuario" value="editarUsuario" class="btn btn-primary">Editar</button>



                </div>
            </div>

        </form>
</div>

</body>
</html>
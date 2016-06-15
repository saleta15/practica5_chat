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

        <h1>Nuevo Usuario</h1>
        <form action="/procesarNuevoUsuario/" method="POST">
            <div class = "panel panel-default">
                <div class = "panel-body">
                    <input type="hidden" value="false" class="form-control" id="username" name="administrador" required >
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="username">Nombre de Usuario:</label>
                                <input type="text" class="form-control" id="username" name="username" required >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="password">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="nombre">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class ="col-md-9">
                            <div class="form-group">
                                <label class="checkbox-inline"><input type="checkbox" name="autor" value="true"><b>Puede redactar artículos</b></label>
                            </div>
                        </div>
                    </div>
                    <br>
                    <button type="submit" name="action" id="btnCrearUsuario" value="crearUsuario" class="btn btn-primary">Crear</button>

                </div>
            </div>

        </form>
</div>

</body>
</html>
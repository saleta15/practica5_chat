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
                    <div class="row">
                        <div class ="col-md-5">
                            <div class="form-group">
                                <label for="username">Título:</label>
                                <input type="text" class="form-control" id="titulo" name="titulo" required >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class ="col-md-5">
                            <div class="form-group">
                                <label for="etiquetas">Etiquetas</label>
                                <input type="text" class="form-control" id="etiquetas" name="etiquetas" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class ="col-md-12">
                            <div class="form-group">
                                <label for="cuerpo">Cuerpo:</label>
                                <textarea class="form-control" rows="10" id="cuerpo" name="cuerpo"></textarea>
                            </div>
                        </div>
                    </div>
                    <button type="submit" name="action" id="btnCrearArticulo" value="crearArticulo" class="btn btn-primary">Publicar</button>
                </div>
            </div>

        </form>
</div>

</body>
</html>
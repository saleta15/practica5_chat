<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
</head>
<body>
<div class="container" style="width: 20%; margin-top: 10%">
    <div class="panel panel-info">
        <div class="panel-heading">Iniciar Sesi&oacute;n</div>
        <div class="panel-body">
            <form action="autenticar/" method="POST" class="form-signin">
                <br>
                <label for="inputEmail">Nombre de Usuario</label>
                <input name="username" type="text" id="username" class="form-control"  required="" autofocus="">
                <br>
                <label for="inputEmail">Contraseña</label>
                <input name="password" type="password" id="password" class="form-control"  required="">
                <br><br>
                <button style="border-radius: 30px" class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            </form>
        </div>
    </div>

</div>

</body>
</html>
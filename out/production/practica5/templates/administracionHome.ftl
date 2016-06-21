<!DOCTYPE html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet">
</head>

<body>

<#include "/adminHeader.ftl">

<div class="container">
    <h1>Usuarios</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Username</th>
            <th>Nombre</th>
            <th>Administrador</th>
            <th>Autor</th>
        </tr>
        </thead>
        <tbody>
        <#list usuarios as u>
        <tr class="row-click" data-href="/administracion/editar/${u.username}">
                <td>${u.username}</td>
                <td>${u.nombre}</td>
                <td>${u.administrador?string}</td>
                <td>${u.autor?string}</td>
        </tr>
        </#list>

        </tbody>
    </table>

    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/miJs.js"></script>

</body>

</html>

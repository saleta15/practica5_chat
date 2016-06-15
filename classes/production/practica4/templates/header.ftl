
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Pr&aacute;ctica 3</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                <#if usuario.autor || usuario.administrador>
                    <li><a href="/redactarArticulo">Redactar</a></li>
                </#if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if usuario.administrador>
                        <li><a href="/administracion/">Administraci&oacute;n</a></li>
                    </#if>
                    <#if usuario.autor || usuario.administrador || !usuario.esInvitado>
                        <li>  <form action="/cerrarsesion/" method="POST" class="form-signin">
                                 <button style="border-radius: 30px; background-color:transparent; border: snow; margin-top: 13px;  " type="submit">Cerrar Sesi&oacute;n</button>
                            </form>
                        </li>
                    <#else>
                        <li><a href="/login">Iniciar Sesi&oacute;n</a></li>
                    </#if>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>
</body>
</html>
package Main;

import modelos.Articulo;
import modelos.Usuario;

import services.ArticuloServices;
import services.UsuarioServices;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;

/**
 * Created by Dell_2 on 6/2/2016.
 */
public class Filtro {
    public void aplicarFiltros(){

        before("/login",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario!=null){
                response.redirect("/");
            }
        });

        before("/administracion/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario == null ||usuario.getAdministrador()!=true){

                response.redirect("/");
            }
        });

        before("/administracion/editar/:valor",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario == null ||usuario.getAdministrador()!=true){
                response.redirect("/");
            }
        });

        before("/administracion/crearUsuario",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario == null ||usuario.getAdministrador()!=true){
                response.redirect("/");
            }
        });

        before("/redactarArticulo",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario!=null && usuario.getAdministrador())
                return;
            if(usuario == null ||!usuario.getAutor()){
                response.redirect("/");
            }
        });

        before("/editarArticulo/:articulo",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario!=null && usuario.getAdministrador())
                return;
            if(usuario == null ||!usuario.getAutor()){
                response.redirect("/");
                return;
            }
            int articuloId = Integer.parseInt(request.params("articulo"));
           Articulo articulo = ArticuloServices.getInstancia().find(articuloId);

            if (articulo == null ||
                    !articulo.getAutor().getUsername().equals(usuario.getUsername()))
                response.redirect("/");
        });


        before("procesarCrearArticulo/", (request, response) -> {
            if( request.queryParams("titulo") == null ||
                    request.queryParams("cuerpo") == null || request.queryParams("cuerpo").length() > 2000
                    || request.queryParams("etiquetas") == null ){
                halt(401, "Cuerpo demasiado largo");
            }});
        before("procesarEditarArticulo/", (request, response) -> {
            if( request.queryParams("titulo") == null ||
                    request.queryParams("cuerpo") == null || request.queryParams("cuerpo").length() > 2000
                    || request.queryParams("etiquetas") == null ){
                halt(401, "Cuerpo demasiado largo");
            }
        });


        before("procesarNuevoComentario/", (request, response) -> {
            if(request.queryParams("comentario") == null || request.queryParams("username") == null ||
                    request.queryParams("articulo") == null|| request.queryParams("comentario").length() > 500) {
                halt(401, "Comentario demasiado largo");
            }});
        before("procesarEditarUsuario/", (request, response) -> {
            if(request.queryParams("nombre") == null || request.queryParams("password") == null
                    || request.queryParams("username") == null){
                halt(401, "Formulario invalido");
            }});
        before("procesarNuevoUsuario/", (request, response) -> {
                if(request.queryParams("nombre") == null || request.queryParams("password") == null
                    || request.queryParams("username") == null){
                    halt(401, "Formulario invalido");

                }
                if(UsuarioServices.getInstancia().find(request.queryParams("username")) != null){
                    halt(401, "Este usuario ya existe!");
             }
        });


    }
}

package Main;

import freemarker.template.Configuration;
import modelos.*;
import org.eclipse.jetty.websocket.api.Session;
import services.ArticuloServices;
import services.LikeArticuloServices;
import services.LikeComentarioServices;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.*;

import static Main.Main.usuariosConectados;
import static spark.Spark.get;
import static spark.Spark.halt;
import static j2html.TagCreator.*;

public class ManejoTemplates {


    public void manejarTemplates() {

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(ManejoTemplates.class , "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        get("/", (request, response) -> {
           response.redirect("pagina/1");
            return "";
        });
        get("/chatRoom", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            if(u==null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setUsername("invitado");
                u.setEsInvitado(true);
            }
            attributes.put("usuario",u);
            return new ModelAndView(attributes, "admin-autorChatroom.ftl");
        }, freeMarkerEngine);

        get("/administradoresConectados", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAutor(false);
            u.setAdministrador(false);
            System.out.println(request.queryParams("nombre"));
            u.setUsername(request.queryParams("nombre"));
            u.setEsInvitado(true);

            attributes.put("administradores",this.buscarAdmins());
            attributes.put("usuario",u);
            return new ModelAndView(attributes, "showAdmins.ftl");
        }, freeMarkerEngine);

        get("/chatRoom/autor", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAutor(false);
            u.setAdministrador(false);
            System.out.println(request.queryParams("nombre"));
            u.setUsername(request.queryParams("nombre"));
            u.setEsInvitado(true);

            Usuario a = UsuarioServices.getInstancia().find(request.queryParams("autor"));
            if(a==null){
                response.redirect("/");
            }

            attributes.put("usuario",u);
            attributes.put("administrador",a);
            return new ModelAndView(attributes, "chatUser.ftl");
        }, freeMarkerEngine);

        get("/chatRoom/:admin/:nombre", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            if(u==null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setUsername(request.params("nombre"));
                u.setEsInvitado(true);
            }
            Usuario a = UsuarioServices.getInstancia().find(request.params("admin"));
            if(a==null){
                response.redirect("/");
            }
            attributes.put("administrador",a);
            attributes.put("usuario",u);
            return new ModelAndView(attributes, "chatUser.ftl");
        }, freeMarkerEngine);



        get("pagina/:pagina", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            if(u==null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setUsername("guest");
                u.setEsInvitado(true);
            }

            List<Articulo> articulos = ArticuloServices.getInstancia().findAll();
            int longitud = articulos.size();
            int cantidadPaginas = longitud/5;
            int paginaSolicitada = Integer.parseInt(request.params("pagina"));
            if(longitud%5!=0)
                cantidadPaginas++;

            if(paginaSolicitada >cantidadPaginas &&longitud!=0)
                halt(404,"Esta pagina no existe");
            int offset;
            if(paginaSolicitada == cantidadPaginas && longitud%5!=0)
                offset = longitud%5;
            else
                offset = 5;
            List<Articulo> articulosMostrados = new ArrayList<Articulo>();
            articulosMostrados = articulos.subList((paginaSolicitada-1)*5,
                        (paginaSolicitada-1)*5+offset );



            attributes.put("usuario", u);
            attributes.put("articulos", articulosMostrados);
            attributes.put("cantidadPaginas",cantidadPaginas);
            attributes.put("filtrado",false);

            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);


        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        get("/etiqueta/:etiqueta/:pagina", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");

            List<Articulo> articulos = this.getArticulosEtiqueta(request.params("etiqueta"));
            int longitud = articulos.size();
            int cantidadPaginas = longitud/5;
            int paginaSolicitada = Integer.parseInt(request.params("pagina"));
            if(longitud%5!=0)
                cantidadPaginas++;

            if(paginaSolicitada >cantidadPaginas && longitud!=0)
                halt(404,"Esta pagina no existe");
            int offset;
            if(paginaSolicitada == cantidadPaginas && longitud%5!=0)//Se solicitó cargar la ultima pagina
                offset = longitud%5;
            else
                offset = 5;
            List<Articulo> articulosMostrados = articulos.subList((paginaSolicitada-1)*5,
                    (paginaSolicitada-1)*5+offset );
            attributes.put("usuario", u);
            attributes.put("articulos", articulosMostrados);
            attributes.put("cantidadPaginas",cantidadPaginas);
            attributes.put("filtrado",true);
            attributes.put("etiqueta",request.params("etiqueta"));
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);


        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        get("/administracion/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");



            List<Usuario> usuarios = UsuarioServices.getInstancia().findAll();
            attributes.put("usuario", u);
            attributes.put("usuarios", usuarios);
            return new ModelAndView(attributes, "administracionHome.ftl");
        }, freeMarkerEngine);

        get("/administracion/crearUsuario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAdministrador(false);
            u.setAutor(false);
            attributes.put("usuario", u);
            return new ModelAndView(attributes, "crearUsuario.ftl");
        }, freeMarkerEngine);

        get("/administracion/editar/:usuario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario usuario = UsuarioServices.getInstancia().find(request.params("usuario"));
            if (usuario == null){
                response.redirect("../../");
            }
            attributes.put("usuario", usuario);
            return new ModelAndView(attributes, "editarUsuario.ftl");
        }, freeMarkerEngine);

        get("/redactarArticulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = new Usuario();
            u.setAdministrador(false);
            u.setAutor(false);
            attributes.put("usuario", u);
            return new ModelAndView(attributes, "crearArticulo.ftl");
        }, freeMarkerEngine);

        get("/verArticulo/:articulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            if(u == null){
                u = new Usuario();
                u.setAutor(false);
                u.setAdministrador(false);
                u.setEsInvitado(true);
                u.setUsername("guest");
            }
            Articulo a = ArticuloServices.getInstancia().find(Integer.parseInt(request.params("articulo")));
            LikeComentarioServices.getInstancia().prepararComentarios(a,u);
            List<Comentario> comentarios = new ArrayList<Comentario>(a.getComentarios());
            Comparator<Comentario> comparator = new Comparator<Comentario>() {
                public int compare(Comentario c1, Comentario c2) {
                    return c2.getId() - c1.getId();
                }
            };
            LikeArticuloServices.getInstancia().prepararArticulo(a,u);
            Collections.sort(comentarios,comparator);

            boolean autorOn = Main.usuariosConectados.get(a.getAutor().getUsername()) != null;
            attributes.put("usuario", u);
            attributes.put("articulo", a);
            attributes.put("comentarios",comentarios);
            attributes.put("autorOn",autorOn);
            return new ModelAndView(attributes, "verArticulo.ftl");
        }, freeMarkerEngine);

        get("/editarArticulo/:articulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Usuario u = request.session().attribute("usuario");
            Articulo a = ArticuloServices.getInstancia().find(Integer.parseInt(request.params("articulo")));

            attributes.put("usuario", u);
            attributes.put("articulo", a);
            attributes.put("etiquetas", a.getEtiquetasString());
            return new ModelAndView(attributes, "editarArticulo.ftl");
        }, freeMarkerEngine   );



    }

    private ArrayList<Articulo> getArticulosEtiqueta(String etiqueta){
        List<Articulo> articulos =  ArticuloServices.getInstancia().findAll();
        ArrayList<Articulo> art = new ArrayList<>();
        for(Articulo a: articulos){
            for(Etiqueta e: a.getEtiquetas()){

                if(e.getEtiqueta().equals(etiqueta)) {
                    art.add(a);
                    break;
                }
            }
        }
        return art;
    }

    private ArrayList<Usuario> buscarAdmins(){
        ArrayList<Usuario> administradores = new ArrayList<>();
        for (Map.Entry<String, Session> entry : Main.usuariosConectados.entrySet())
        {   System.out.print(entry.getKey());
            Usuario u = UsuarioServices.getInstancia().find(entry.getKey());

            if(u == null)
                continue;
            if(u.getAdministrador())
                administradores.add(u);

        }
        return administradores;
    }

//    public static void enviarMensajeAClientesConectados(String mensaje){
//        for(Session sesionConectada : usuariosConectados){
//            try {
//                System.out.print("daaamn daniel");
//                sesionConectada.getRemote().sendString(p(mensaje).withClass("rojo").render());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

package services;

import modelos.Articulo;
import modelos.Etiqueta;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by saleta on 5/30/2016.
 */
public class UsuarioServices extends GestionDb<Usuario> {

    private static UsuarioServices instancia;

    private UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    public boolean hayAdmin(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<Usuario> criteriaQuery = em.getCriteriaBuilder().createQuery(Usuario.class);
            Root<Usuario> usuarioRoot =  criteriaQuery.from(Usuario.class);
            criteriaQuery.select(usuarioRoot);
            criteriaQuery.where(usuarioRoot.get("administrador"));
            return em.createQuery(criteriaQuery).getResultList().size() > 0;
        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
}
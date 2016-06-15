package services;

import modelos.Articulo;
import modelos.Comentario;
import modelos.LikeComentario;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dell_2 on 6/9/2016.
 */
public class LikeComentarioServices extends GestionDb<LikeComentario>{
    private static LikeComentarioServices instancia;

    private LikeComentarioServices() {
        super(LikeComentario.class);
    }
    public static LikeComentarioServices getInstancia() {
        if (instancia == null) {
            instancia = new LikeComentarioServices();
        }
        return instancia;
    }

     public void prepararComentarios(Articulo articulo, Usuario usuario){
        for(Comentario c: articulo.getComentarios()){
            if( usuarioComento(c,usuario))
                c.setEstaValorado(true);
            setValoracionComentario(c);

        }
    }


    private void setValoracionComentario(Comentario comentario){
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb= em.getCriteriaBuilder();
            CriteriaQuery<LikeComentario> criteriaQuery = cb.createQuery(LikeComentario.class);
            Root<LikeComentario> lcRoot =  criteriaQuery.from(LikeComentario.class);
            criteriaQuery.select(lcRoot);
            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("comentario"),comentario.getId())),
                    cb.equal(lcRoot.get("esPositivo"),true));
            int positivos = em.createQuery(criteriaQuery).getResultList().size();
            criteriaQuery = cb.createQuery(LikeComentario.class);
            lcRoot =  criteriaQuery.from(LikeComentario.class);
            criteriaQuery.select(lcRoot);
            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("comentario"),comentario.getId())),
                    cb.equal(lcRoot.get("esPositivo"),false));
            int negativos = em.createQuery(criteriaQuery).getResultList().size();

            comentario.setValoracion(positivos-negativos);

        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }

    private boolean usuarioComento(Comentario comentario, Usuario usuario){
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb= em.getCriteriaBuilder();
            CriteriaQuery<LikeComentario> criteriaQuery = cb.createQuery(LikeComentario.class);
            Root<LikeComentario> lcRoot =  criteriaQuery.from(LikeComentario.class);
            criteriaQuery.select(lcRoot);

            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("usuario"),usuario)),cb.equal(lcRoot.get("comentario"),comentario));

            return em.createQuery(criteriaQuery).getResultList().size()>0;

        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
}

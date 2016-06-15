package services;

import modelos.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Dell_2 on 6/9/2016.
 */
public class LikeArticuloServices extends GestionDb<LikeArticulo>{
    private static LikeArticuloServices instancia;

    private LikeArticuloServices() {
        super(LikeArticulo.class);
    }
    public static LikeArticuloServices getInstancia() {
        if (instancia == null) {
            instancia = new LikeArticuloServices();
        }
        return instancia;
    }

    public void prepararArticulo(Articulo articulo, Usuario usuario){

            if( usuarioValoro(articulo,usuario))
                articulo.setEstaValorado(true);
        setValoracionArticulo(articulo);


    }

    private boolean usuarioValoro(Articulo articulo, Usuario usuario){
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb= em.getCriteriaBuilder();
            CriteriaQuery<LikeArticulo> criteriaQuery = cb.createQuery(LikeArticulo.class);
            Root<LikeArticulo> lcRoot =  criteriaQuery.from(LikeArticulo.class);
            criteriaQuery.select(lcRoot);

            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("usuario"),usuario)),cb.equal(lcRoot.get("articulo"),articulo));

            return em.createQuery(criteriaQuery).getResultList().size()>0;

        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }

    private void setValoracionArticulo(Articulo articulo){
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb= em.getCriteriaBuilder();
            CriteriaQuery<LikeArticulo> criteriaQuery = cb.createQuery(LikeArticulo.class);
            Root<LikeArticulo> lcRoot =  criteriaQuery.from(LikeArticulo.class);
            criteriaQuery.select(lcRoot);
            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("articulo"),articulo.getId())),
                    cb.equal(lcRoot.get("esPositivo"),true));
            int positivos = em.createQuery(criteriaQuery).getResultList().size();
            criteriaQuery = cb.createQuery(LikeArticulo.class);
            lcRoot =  criteriaQuery.from(LikeArticulo.class);
            criteriaQuery.select(lcRoot);
            criteriaQuery.where(cb.and(cb.equal(lcRoot.get("articulo"),articulo.getId())),
                    cb.equal(lcRoot.get("esPositivo"),false));
            int negativos = em.createQuery(criteriaQuery).getResultList().size();
            articulo.setValoracion(positivos-negativos);

        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
}

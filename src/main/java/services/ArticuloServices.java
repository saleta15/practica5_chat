package services;

import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dell_2 on 6/8/2016.
 */
public class ArticuloServices extends GestionDb<Articulo>{
    private static ArticuloServices instancia;

    private ArticuloServices() {
        super(Articulo.class);
    }
    public static ArticuloServices getInstancia() {
        if (instancia == null) {
            instancia = new ArticuloServices();
        }
        return instancia;
    }

    @Override
    public List<Articulo> findAll(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<Articulo> criteriaQuery = em.getCriteriaBuilder().createQuery(Articulo.class);
            Root<Articulo> a = criteriaQuery.from(Articulo.class);
            criteriaQuery.select(a);
            criteriaQuery.orderBy(em.getCriteriaBuilder().desc(a.get("fecha")));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
    @Override
    public void editar(Articulo entidad){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            for(Etiqueta e: entidad.getEtiquetas()){
                EtiquetaServices.getInstancia().eliminar(e);
            }
            entidad.setEtiquetas(null);
            em.merge(entidad);

            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }
}

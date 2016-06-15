package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by saleta on 5/30/2016.
 */
@Entity
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    @Column(columnDefinition = "VARCHAR(MAX)")
    private  String cuerpo;
    @Transient
    private String preview;
    private Integer likes;
    public String getPreview() {
        if (cuerpo.length() > 70){
            return cuerpo.substring(0,69);
        }
        return cuerpo;
    }



    public void setPreview(String preview) {
        this.preview = preview;
    }
    @ManyToOne
    private Usuario autor;//un autor puede tener muchos articulos
    private Date fecha;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "articulo")
    private Set<Comentario> comentarios;//Muchos comenterio puede tener un articulo

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "articulo")
    private Set<Etiqueta> etiquetas;//Muchas etiquetas puede tener un articulo

    @Transient
    private boolean estaValorado;

    public boolean isEstaValorado() {
        return estaValorado;
    }

    public void setEstaValorado(boolean estaValorado) {
        this.estaValorado = estaValorado;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    @Transient
    private Integer valoracion;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEtiquetasString(){
        String et = "";
        int i = 0;
        for (Etiqueta e : this.etiquetas){
            et+=e.getEtiqueta();
            if(i< etiquetas.size()-1)
                et+=",";
            i++;
        }
        return et;
    }
}

package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

/**
 * Created by saleta on 5/30/2016.
 */

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comentario;
    @ManyToOne
    private Usuario autor;
    private Integer likes;
    @ManyToOne
    private Articulo articulo;
    @Transient
    private boolean estaValorado;
    @Transient
    private Integer valoracion;

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Boolean getEstaValorado() {
        return estaValorado;
    }

    public void setEstaValorado(Boolean estaValorado) {
        this.estaValorado = estaValorado;
    }

    public boolean isEstaValorado() {
        return estaValorado;
    }

    public void setEstaValorado(boolean estaValorado) {
        this.estaValorado = estaValorado;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public Comentario(String comentario, Usuario autor, Articulo articulo) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public Comentario() {
    }
}

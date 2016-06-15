package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by saleta on 5/30/2016.
 */
@Entity
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String etiqueta;


    @ManyToOne
    private Articulo articulo;

    public Articulo getArticulo() {
        return articulo;
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

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public Etiqueta(){

    }
}


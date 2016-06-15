package modelos;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell_2 on 6/9/2016.
 */
@Entity
public class LikeComentario  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Comentario comentario;
    private boolean esPositivo;

    public boolean isEsPositivo() {
        return esPositivo;
    }

    public void setEsPositivo(boolean esPositivo) {
        this.esPositivo = esPositivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}

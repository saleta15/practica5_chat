package modelos;

/**
 * Created by saleta on 5/30/2016.
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Usuario implements Serializable {
    @Id
    private String username;
    private String password;
    private String nombre;
    private Boolean administrador;
    private Boolean autor;
    @Transient
    private Boolean esInvitado = false;

    @OneToMany(mappedBy = "autor") // La clase Clase es la dueña de la relación.

    @Transient
    private Set<Articulo> listaArticulos;

    public Boolean getEsInvitado() {
        return esInvitado;
    }

    public void setEsInvitado(Boolean esInvitado) {
        this.esInvitado = esInvitado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public Usuario(Boolean autor, Boolean administrador, String nombre, String password, String username) {
        this.autor = autor;
        this.administrador = administrador;
        this.nombre = nombre;
        this.password = password;
        this.username = username;

    }

    public Usuario() {
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public Boolean getAutor() {
        return autor;
    }

    public void setAutor(Boolean autor) {
        this.autor = autor;
    }
}

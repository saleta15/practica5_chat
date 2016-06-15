
package services;

/**
 * Created by Dell_2 on 5/30/2016.
 */

import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;
import modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtiquetaServices extends GestionDb<Etiqueta> {
    private static EtiquetaServices instancia;
    private EtiquetaServices() {
        super(Etiqueta.class);
    }
    public static EtiquetaServices getInstancia(){
        if(instancia==null){
            instancia = new EtiquetaServices();
        }
        return instancia;
    }

    public void crearListaEtiquetas(String etiquetas, Articulo articulo){
        for (String e : etiquetas.split(",")){
            Etiqueta nuevaEtiqueta = new Etiqueta();
            nuevaEtiqueta.setEtiqueta(e);
            nuevaEtiqueta.setArticulo(articulo);
            crear(nuevaEtiqueta);
        }
    }

}




package es.programia.info.excepciones;

import java.io.Serializable;
import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class GestionSolicitudesException extends Exception implements Serializable{

   private String[] params;

    public GestionSolicitudesException(String string, String ... params) {
        super(string);
        
        if (params != null) 
            this.params = params;
    }

    
   
   
   public GestionSolicitudesException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public String[] getParams() {
        return params;
    }
    
    
}

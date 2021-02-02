
package es.programia.info.web;

import es.programia.info.entidades.Interesado;
import es.programia.info.excepciones.GestionSolicitudesException;
import es.programia.info.servicios.InteresadoServiceLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "gestion")
@ViewScoped
public class GestionSolicitudesManagedBean implements Serializable{

    private Interesado interesado = new Interesado();
    
    private Collection<Interesado> misInteresados;
    
    @EJB private InteresadoServiceLocal servicio;
    
    private static Logger log= Logger.getLogger("GestionSolicitudBean");
            
    public GestionSolicitudesManagedBean() {
       
    }
    
    @PostConstruct
    public void inicializar(){
         this.misInteresados = servicio.getAllUsuarios();
     }
    
    public Collection<Interesado> getMisInteresados() {
        return misInteresados;
    }
         
    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

   
    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        GestionSolicitudesManagedBean.log = log;
    }
    
     public String buscar(){
         try {
            
            this.interesado = servicio.buscarInteresadoporCriterio(interesado.getNombre(), interesado.getApellidos(), interesado.getEmpresa());
             System.out.println(interesado);
            return null;
        } catch (GestionSolicitudesException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
     }
    
}

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
public class GestionSolicitudesManagedBean implements Serializable {

    private Interesado interesado = new Interesado();
    
    

    private Collection<Interesado> misInteresados;
    private boolean visible = false;
    private boolean editable = false;
    private boolean accionNuevo = false;
    private boolean accionModificar = false;

    @EJB
    private InteresadoServiceLocal servicio;

    private static Logger log = Logger.getLogger("GestionSolicitudBean");

    public GestionSolicitudesManagedBean() {

    }

    @PostConstruct
    public void inicializar() {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isAccionNuevo() {
        return accionNuevo;
    }

    public void setAccionNuevo(boolean accionNuevo) {
        this.accionNuevo = accionNuevo;
    }

    public boolean isAccionModificar() {
        return accionModificar;
    }

    public void setAccionModificar(boolean accionModificar) {
        this.accionModificar = accionModificar;
    }

    public String buscar() {
        try {

            this.interesado = servicio.buscarInteresadoporCriterio(interesado.getNombre(), interesado.getApellidos(), interesado.getEmpresa());
            System.out.println(interesado);
            visible = true;
            return null;
        } catch (GestionSolicitudesException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }

    public String modoNuevo() {
        visible = true;
        accionNuevo = true;
        editable = true;
        return null;
    }

    public String cancelar() {
        return "gestion-solicitudes";
    }

    public String grabar() {
        if (accionNuevo == true) {
            try {
                servicio.crearNuevoInteresado(interesado);
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage mns = new FacesMessage("Usuario dado de alta");
                ctx.addMessage(null, mns);

            } catch (Exception e) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(e.getMessage()));

            }
        } else if (accionModificar == true) {
            try {
                servicio.modificarInteresado(interesado);
                FacesContext ctx = FacesContext.getCurrentInstance();
                FacesMessage mns = new FacesMessage("Usuario modificado");
                ctx.addMessage(null, mns);

            } catch (Exception e) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(e.getMessage()));

            }
        }
        return null;
    }

    public String editar() {
        editable = true;
        accionModificar = true;
        return null;
    }

}

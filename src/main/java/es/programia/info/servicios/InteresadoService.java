package es.programia.info.servicios;

import es.programia.info.entidades.Interesado;
import es.programia.info.excepciones.GestionSolicitudesException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class InteresadoService implements InteresadoServiceLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Interesado buscarInteresadoporCriterio(String nombre, String apellidos, String empresa) throws GestionSolicitudesException {
        Query query = em.createNamedQuery("Interesado.findByNombreApellidoEmpresa");
        if (nombre == null){
            nombre = "%";
        } 
        if (apellidos == null){
            apellidos = "%";
        }
        if (empresa == null){
            apellidos = "%";
        }
        query.setParameter("nombre", nombre);
        query.setParameter("apellidos", apellidos);
        query.setParameter("empresa", empresa);
        
        query.setMaxResults(1);
        
        List<Interesado> interesados = query.getResultList();
        
        if(interesados == null || interesados.size()==0){
            throw new GestionSolicitudesException("info_busqueda_no_resultados");
        }
        return interesados.get(0);
    }

    

    @Override
    public void crearNuevoInteresado(Interesado interesado) throws GestionSolicitudesException {
        em.persist(interesado);
    }

    @Override
    public void modificarInteresado(Interesado interesado) throws GestionSolicitudesException {
        try {
            Interesado i = em.find(Interesado.class, interesado.getIdInteresado());
            if (i ==  null){
                GestionSolicitudesException("error_update_interesado_not_found");
            }
            em.merge(interesado);
            
        } catch (OptimisticLockException ole) {
            throw new GestionSolicitudesException("La persona interesada actual ya ha sido modificada "
                    + " por otra persona. \n Por favor vuelva a recuperarlo. ");
        }
    }

    private void GestionSolicitudesException(String error_update_interesado_not_found) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

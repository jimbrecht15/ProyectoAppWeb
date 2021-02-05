package es.programia.info.servicios;

import es.programia.info.entidades.Interesado;
import es.programia.info.excepciones.GestionSolicitudesException;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InteresadoService implements InteresadoServiceLocal {

    @PersistenceContext(unitName = "GestionSolicitudesInfoPU")
    private EntityManager em;
            
    @Override
    public Interesado buscarInteresadoporCriterio(String nombre, String apellidos, String empresa) throws GestionSolicitudesException {
        Query query = em.createNamedQuery("Interesado.findByNombreApellidoEmpresa");
        if (nombre == null || nombre.trim().length() == 0){
            nombre = "%";
        } 
        if (apellidos == null || apellidos.trim().length() == 0){
            apellidos = "%";
        }
        if (empresa == null || empresa.trim().length() == 0){
            empresa = "%";
        }
        query.setParameter("nombre", nombre);
        query.setParameter("apellidos", apellidos);
        query.setParameter("empresa", empresa);
        
        query.setMaxResults(1);
        String consulta = query.toString();
        List<Interesado> interesados = query.getResultList();
        
        if(interesados == null || interesados.size()==0){
                throw new GestionSolicitudesException("Error en la busqueda - No hay interesados para el criterio seleccionado");
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
                 throw new GestionSolicitudesException("Error en actualizar el interesado");
            }
            em.merge(interesado);
            
        } catch (OptimisticLockException ole) {
            throw new GestionSolicitudesException("La persona interesada actual ya ha sido modificada "
                    + " por otra persona. \n Por favor vuelva a recuperarlo. ");
        }
    }

    @Override
    public Collection<Interesado> getAllUsuarios() {
       
       Query query = em.createNamedQuery("Interesado.findAll");
        
        return query.getResultList();
    }
}

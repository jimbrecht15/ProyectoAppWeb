
package es.programia.info.servicios;

import es.programia.info.entidades.Interesado;
import es.programia.info.excepciones.GestionSolicitudesException;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@TransactionManagement(TransactionManagementType.CONTAINER)
public interface InteresadoServiceLocal {
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    Interesado buscarInteresadoporCriterio(String nombre, String apellido, String empresa) throws GestionSolicitudesException;
   
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void crearNuevoInteresado(Interesado interesado) throws GestionSolicitudesException;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void modificarInteresado (Interesado interesado) throws GestionSolicitudesException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "CATEGORIAS")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categoria.findByCategoria", query = "SELECT c FROM Categoria c WHERE c.categoria = :categoria")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA")
    private Long idCategoria;
    @Size(max = 55)
    @Column(name = "CATEGORIA")
    private String categoria;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
    //private List<SolicitudeInfo> solicitudeInfoList;

    public Categoria() {
    }

    public Categoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

//    public List<SolicitudeInfo> getSolicitudeInfoList() {
//        return solicitudeInfoList;
//    }

//    public void setSolicitudeInfoList(List<SolicitudeInfo> solicitudeInfoList) {
//        this.solicitudeInfoList = solicitudeInfoList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.programia.info.entidades.Categoria[ idCategoria=" + idCategoria + " ]";
    }
    
}

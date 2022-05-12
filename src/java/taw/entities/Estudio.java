/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xdmrg
 */
@Entity
@Table(name = "ESTUDIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e"),
    @NamedQuery(name = "Estudio.findById", query = "SELECT e FROM Estudio e WHERE e.id = :id"),
    @NamedQuery(name = "Estudio.findByNombre", query = "SELECT e FROM Estudio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estudio.findByTabla", query = "SELECT e FROM Estudio e WHERE e.tabla = :tabla"),
    @NamedQuery(name = "Estudio.findByOrdenar", query = "SELECT e FROM Estudio e WHERE e.ordenar = :ordenar"),
    @NamedQuery(name = "Estudio.findByAgrupar", query = "SELECT e FROM Estudio e WHERE e.agrupar = :agrupar"),
    @NamedQuery(name = "Estudio.findByOperacion", query = "SELECT e FROM Estudio e WHERE e.operacion = :operacion"),
    @NamedQuery(name = "Estudio.findByTipoOrden", query = "SELECT e FROM Estudio e WHERE e.tipoOrden = :tipoOrden"),
    @NamedQuery(name = "Estudio.findByNumElementos", query = "SELECT e FROM Estudio e WHERE e.numElementos = :numElementos")})
public class Estudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TABLA")
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDENAR")
    private int ordenar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGRUPAR")
    private int agrupar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPERACION")
    private int operacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_ORDEN")
    private int tipoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ELEMENTOS")
    private int numElementos;

    public Estudio() {
    }

    public Estudio(Integer id) {
        this.id = id;
    }

    public Estudio(Integer id, String nombre, String tabla, int ordenar, int agrupar, int operacion, int tipoOrden, int numElementos) {
        this.id = id;
        this.nombre = nombre;
        this.tabla = tabla;
        this.ordenar = ordenar;
        this.agrupar = agrupar;
        this.operacion = operacion;
        this.tipoOrden = tipoOrden;
        this.numElementos = numElementos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(int ordenar) {
        this.ordenar = ordenar;
    }

    public int getAgrupar() {
        return agrupar;
    }

    public void setAgrupar(int agrupar) {
        this.agrupar = agrupar;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public int getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(int tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public int getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entities.Estudio[ id=" + id + " ]";
    }
    
}

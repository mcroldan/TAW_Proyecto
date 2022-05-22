/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.entities;

import taw.dto.PujaDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Entity
@Table(name = "PUJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p")
    , @NamedQuery(name = "Puja.findById", query = "SELECT p FROM Puja p WHERE p.id = :id")
    , @NamedQuery(name = "Puja.findByFecha", query = "SELECT p FROM Puja p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Puja.findByAdjudicado", query = "SELECT p FROM Puja p WHERE p.adjudicado = :adjudicado")
    , @NamedQuery(name = "Puja.findByPrecio", query = "SELECT p FROM Puja p WHERE p.precio = :precio")})
public class Puja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADJUDICADO")
    private Boolean adjudicado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario comprador;

    public Puja() {
    }

    public Puja(Integer id) {
        this.id = id;
    }

    public Puja(Integer id, Date fecha, Boolean adjudicado, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.adjudicado = adjudicado;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAdjudicado() {
        return adjudicado;
    }

    public void setAdjudicado(Boolean adjudicado) {
        this.adjudicado = adjudicado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
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
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "taw.entities.Puja[ id=" + id + " ]";
    }

    public PujaDTO toDTO() {
        PujaDTO dto = new PujaDTO();
        dto.setId(this.getId());
        dto.setFecha(this.getFecha());
        dto.setAdjudicado(this.getAdjudicado());
        dto.setPrecio(this.getPrecio());
        dto.setProducto(this.getProducto().toDTO());
        dto.setComprador(this.getComprador().toDTO());
        
        return dto;
    }
    
}

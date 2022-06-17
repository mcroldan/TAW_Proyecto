package es.taw.taw_proyecto.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Puja {
    private Integer id;
    private Integer comprador;
    private Date fecha;
    private Boolean adjudicado;
    private Double precio;
    private Integer producto;
    private Producto productoByProducto;
    private Usuario usuarioByComprador;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPRADOR")
    public Integer getComprador() {
        return comprador;
    }

    public void setComprador(Integer comprador) {
        this.comprador = comprador;
    }

    @Basic
    @Column(name = "FECHA")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "ADJUDICADO")
    public Boolean getAdjudicado() {
        return adjudicado;
    }

    public void setAdjudicado(Boolean adjudicado) {
        this.adjudicado = adjudicado;
    }

    @Basic
    @Column(name = "PRECIO")
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "PRODUCTO")
    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Puja puja = (Puja) o;

        if (id != null ? !id.equals(puja.id) : puja.id != null) return false;
        if (comprador != null ? !comprador.equals(puja.comprador) : puja.comprador != null) return false;
        if (fecha != null ? !fecha.equals(puja.fecha) : puja.fecha != null) return false;
        if (adjudicado != null ? !adjudicado.equals(puja.adjudicado) : puja.adjudicado != null) return false;
        if (precio != null ? !precio.equals(puja.precio) : puja.precio != null) return false;
        if (producto != null ? !producto.equals(puja.producto) : puja.producto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (comprador != null ? comprador.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (adjudicado != null ? adjudicado.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID", nullable = false)
    public Producto getProductoByProducto() {
        return productoByProducto;
    }

    public void setProductoByProducto(Producto productoByProducto) {
        this.productoByProducto = productoByProducto;
    }

    @ManyToOne
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByComprador() {
        return usuarioByComprador;
    }

    public void setUsuarioByComprador(Usuario usuarioByComprador) {
        this.usuarioByComprador = usuarioByComprador;
    }
}

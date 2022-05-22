/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dto;

import java.util.Date;

/**
 *
 * @author PC
 */
public class PujaDTO {
    private Integer id;
    private Date fecha;
    private Boolean adjudicado;
    private double precio;
    private ProductoDTO producto;
    private UsuarioDTO comprador;

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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public UsuarioDTO getComprador() {
        return comprador;
    }

    public void setComprador(UsuarioDTO comprador) {
        this.comprador = comprador;
    }
    
    
}

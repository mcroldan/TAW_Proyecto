/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dto;

import java.util.Date;

/**
 *
 * @author xdmrg
 */
public class ProductoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private double preciosalida;
    private String urlFoto;
    private String marca;
    private Date fechaInicio;
    private CategoriaDTO categoria;
    private UsuarioDTO vendedor;
    
    public ProductoDTO(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPreciosalida() {
        return preciosalida;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public String getMarca() {
        return marca;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public UsuarioDTO getVendedor() {
        return vendedor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPreciosalida(double preciosalida) {
        this.preciosalida = preciosalida;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public void setVendedor(UsuarioDTO vendedor) {
        this.vendedor = vendedor;
    }
    
    
}

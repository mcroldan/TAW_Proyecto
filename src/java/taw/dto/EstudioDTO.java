/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dto;

/**
 *
 * @author Manuel Cristóbal Roldán Gómez
 */
public class EstudioDTO {
    private Integer id;
    private String nombre;
    private String tabla;
    private String ordenar;
    private String agrupar;
    private String operacion;
    private String tipoOrden;
    private Integer numElementos;
    
    public EstudioDTO(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTabla() {
        return tabla;
    }

    public String getOrdenar() {
        return ordenar;
    }

    public String getAgrupar() {
        return agrupar;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public Integer getNumElementos() {
        return numElementos;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public void setOrdenar(String ordenar) {
        this.ordenar = ordenar;
    }

    public void setAgrupar(String agrupar) {
        this.agrupar = agrupar;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public void setNumElementos(Integer numElementos) {
        this.numElementos = numElementos;
    }
    
    
}

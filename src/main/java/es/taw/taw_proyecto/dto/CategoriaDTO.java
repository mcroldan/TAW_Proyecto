/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.taw_proyecto.dto;

/**
 *
 * @author Manuel Cristóbal Roldán Gómez
 */
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    
    public CategoriaDTO(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

package es.taw.taw_proyecto.entity;

import es.taw.taw_proyecto.dto.EstudioDTO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Estudio {
    private Integer id;
    private String nombre;
    private String tabla;
    private String ordenar;
    private String agrupar;
    private String operacion;
    private String tipoOrden;
    private Integer numElementos;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "TABLA")
    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    @Basic
    @Column(name = "ORDENAR")
    public String getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(String ordenar) {
        this.ordenar = ordenar;
    }

    @Basic
    @Column(name = "AGRUPAR")
    public String getAgrupar() {
        return agrupar;
    }

    public void setAgrupar(String agrupar) {
        this.agrupar = agrupar;
    }

    @Basic
    @Column(name = "OPERACION")
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Basic
    @Column(name = "TIPO_ORDEN")
    public String getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    @Basic
    @Column(name = "NUM_ELEMENTOS")
    public Integer getNumElementos() {
        return numElementos;
    }

    public void setNumElementos(Integer numElementos) {
        this.numElementos = numElementos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudio estudio = (Estudio) o;
        return Objects.equals(id, estudio.id) && Objects.equals(nombre, estudio.nombre) && Objects.equals(tabla, estudio.tabla) && Objects.equals(ordenar, estudio.ordenar) && Objects.equals(agrupar, estudio.agrupar) && Objects.equals(operacion, estudio.operacion) && Objects.equals(tipoOrden, estudio.tipoOrden) && Objects.equals(numElementos, estudio.numElementos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tabla, ordenar, agrupar, operacion, tipoOrden, numElementos);
    }

    public EstudioDTO toDTO(){
        EstudioDTO dto = new EstudioDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setTabla(tabla);
        dto.setOrdenar(ordenar);
        dto.setAgrupar(agrupar);
        dto.setOperacion(operacion);
        dto.setTipoOrden(tipoOrden);
        dto.setNumElementos(numElementos);

        return dto;
    }
}

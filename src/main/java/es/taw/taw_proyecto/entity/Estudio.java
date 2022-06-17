package es.taw.taw_proyecto.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

        if (id != null ? !id.equals(estudio.id) : estudio.id != null) return false;
        if (nombre != null ? !nombre.equals(estudio.nombre) : estudio.nombre != null) return false;
        if (tabla != null ? !tabla.equals(estudio.tabla) : estudio.tabla != null) return false;
        if (ordenar != null ? !ordenar.equals(estudio.ordenar) : estudio.ordenar != null) return false;
        if (agrupar != null ? !agrupar.equals(estudio.agrupar) : estudio.agrupar != null) return false;
        if (operacion != null ? !operacion.equals(estudio.operacion) : estudio.operacion != null) return false;
        if (tipoOrden != null ? !tipoOrden.equals(estudio.tipoOrden) : estudio.tipoOrden != null) return false;
        if (numElementos != null ? !numElementos.equals(estudio.numElementos) : estudio.numElementos != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (tabla != null ? tabla.hashCode() : 0);
        result = 31 * result + (ordenar != null ? ordenar.hashCode() : 0);
        result = 31 * result + (agrupar != null ? agrupar.hashCode() : 0);
        result = 31 * result + (operacion != null ? operacion.hashCode() : 0);
        result = 31 * result + (tipoOrden != null ? tipoOrden.hashCode() : 0);
        result = 31 * result + (numElementos != null ? numElementos.hashCode() : 0);
        return result;
    }
}

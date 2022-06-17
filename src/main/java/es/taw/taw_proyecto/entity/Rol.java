package es.taw.taw_proyecto.entity;

import es.taw.taw_proyecto.dto.RolDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Rol {
    private Integer id;
    private String nombre;
    private Collection<Usuario> usuariosById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rol rol = (Rol) o;

        if (id != null ? !id.equals(rol.id) : rol.id != null) return false;
        if (nombre != null ? !nombre.equals(rol.nombre) : rol.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rolByRol")
    public Collection<Usuario> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<Usuario> usuariosById) {
        this.usuariosById = usuariosById;
    }

    public RolDTO toDTO() {
        RolDTO dto = new RolDTO();
        dto.setId(id);
        dto.setNombre(nombre);

        return dto;
    }
}

package es.taw.taw_proyecto.entity;

import es.taw.taw_proyecto.dto.CategoriaDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Categoria {
    private Integer id;
    private String nombre;
    private Collection<Producto> productosById;
    @ManyToMany(mappedBy = "categoriasPreferidas")
    private List<Usuario> usuariosCategoria;

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

        Categoria categoria = (Categoria) o;

        if (id != null ? !id.equals(categoria.id) : categoria.id != null) return false;
        if (nombre != null ? !nombre.equals(categoria.nombre) : categoria.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoriaByCategoria")
    public Collection<Producto> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<Producto> productosById) {
        this.productosById = productosById;
    }

    public List<Usuario> getUsuariosCategoria() {
        return usuariosCategoria;
    }

    public void setUsuariosCategoria(List<Usuario> usuariosCategoria) {
        this.usuariosCategoria = usuariosCategoria;
    }

    public CategoriaDTO toDTO(){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(id);
        dto.setNombre(nombre);

        return dto;
    }
}

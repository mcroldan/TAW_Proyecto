package es.taw.taw_proyecto.entity;

import es.taw.taw_proyecto.dto.FavoritosDTO;
import es.taw.taw_proyecto.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favoritos {
    private Integer id;
    private Integer usuario;
    private Integer producto;
    private Producto productoByProducto;
    private Usuario usuarioByUsuario;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USUARIO")
    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
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

        Favoritos favoritos = (Favoritos) o;

        if (id != null ? !id.equals(favoritos.id) : favoritos.id != null) return false;
        if (usuario != null ? !usuario.equals(favoritos.usuario) : favoritos.usuario != null) return false;
        if (producto != null ? !producto.equals(favoritos.producto) : favoritos.producto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
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
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }

    public FavoritosDTO toDTO() {
        FavoritosDTO dto = new FavoritosDTO();
        dto.setProducto(this.productoByProducto.toDTO());
        dto.setUsuario(this.usuarioByUsuario.toDTO());
        dto.setId(this.getId());

        return dto;
    }
}

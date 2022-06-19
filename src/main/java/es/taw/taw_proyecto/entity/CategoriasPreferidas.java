package es.taw.taw_proyecto.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIAS_PREFERIDAS", schema = "TAW_Proyecto", catalog = "")
public class CategoriasPreferidas {
    private Integer id;
    private Integer usuario;
    private Integer categoria;
    private Categoria categoriaByCategoria;
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
    @Column(name = "CATEGORIA")
    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriasPreferidas that = (CategoriasPreferidas) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID", nullable = false,
                // codigo de abajo puesto aqui para evitar el
                // Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Repeated column in mapping for entity: es.taw.taw_proyecto.entity.CategoriasPreferidas column: categoria (should be mapped with insert="false" update="false")
                insertable = false, updatable = false)
    public Categoria getCategoriaByCategoria() {
        return categoriaByCategoria;
    }

    public void setCategoriaByCategoria(Categoria categoriaByCategoria) {
        this.categoriaByCategoria = categoriaByCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false,
                // codigo de abajo puesto aqui para evitar el
                // Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Repeated column in mapping for entity: es.taw.taw_proyecto.entity.CategoriasPreferidas column: categoria (should be mapped with insert="false" update="false")
                insertable = false, updatable = false)
    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }
}

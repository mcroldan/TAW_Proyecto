package es.taw.taw_proyecto.entity;

import javax.persistence.*;

@Entity
@Table(name = "LISTAS_USUARIO", schema = "TAW_Proyecto", catalog = "")
public class ListasUsuario {
    private Integer id;
    private Integer usuario;
    private Integer lista;
    private Lista listaByLista;
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
    @Column(name = "LISTA")
    public Integer getLista() {
        return lista;
    }

    public void setLista(Integer lista) {
        this.lista = lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListasUsuario that = (ListasUsuario) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (lista != null ? !lista.equals(that.lista) : that.lista != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (lista != null ? lista.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "LISTA", referencedColumnName = "ID", nullable = false)
    public Lista getListaByLista() {
        return listaByLista;
    }

    public void setListaByLista(Lista listaByLista) {
        this.listaByLista = listaByLista;
    }

    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }
}

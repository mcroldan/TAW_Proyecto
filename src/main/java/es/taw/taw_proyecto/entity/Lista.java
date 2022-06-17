package es.taw.taw_proyecto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Lista {
    private Integer id;
    private Collection<ListasUsuario> listasUsuariosById;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lista lista = (Lista) o;

        if (id != null ? !id.equals(lista.id) : lista.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToMany(mappedBy = "listaByLista")
    public Collection<ListasUsuario> getListasUsuariosById() {
        return listasUsuariosById;
    }

    public void setListasUsuariosById(Collection<ListasUsuario> listasUsuariosById) {
        this.listasUsuariosById = listasUsuariosById;
    }
}

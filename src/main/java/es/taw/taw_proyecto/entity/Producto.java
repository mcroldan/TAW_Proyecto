package es.taw.taw_proyecto.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Producto {
    private Integer id;
    private Integer vendedor;
    private String titulo;
    private String descripcion;
    private Double preciosalida;
    private String urlFoto;
    private String marca;
    private Integer categoria;
    private Date fechainicio;
    private Collection<Favoritos> favoritosById;
    private Categoria categoriaByCategoria;
    private Usuario usuarioByVendedor;
    private Collection<Puja> pujasById;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VENDEDOR")
    public Integer getVendedor() {
        return vendedor;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedor = vendedor;
    }

    @Basic
    @Column(name = "TITULO")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "PRECIOSALIDA")
    public Double getPreciosalida() {
        return preciosalida;
    }

    public void setPreciosalida(Double preciosalida) {
        this.preciosalida = preciosalida;
    }

    @Basic
    @Column(name = "URL_FOTO")
    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Basic
    @Column(name = "MARCA")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "CATEGORIA")
    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    @Basic
    @Column(name = "FECHAINICIO")
    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (id != null ? !id.equals(producto.id) : producto.id != null) return false;
        if (vendedor != null ? !vendedor.equals(producto.vendedor) : producto.vendedor != null) return false;
        if (titulo != null ? !titulo.equals(producto.titulo) : producto.titulo != null) return false;
        if (descripcion != null ? !descripcion.equals(producto.descripcion) : producto.descripcion != null)
            return false;
        if (preciosalida != null ? !preciosalida.equals(producto.preciosalida) : producto.preciosalida != null)
            return false;
        if (urlFoto != null ? !urlFoto.equals(producto.urlFoto) : producto.urlFoto != null) return false;
        if (marca != null ? !marca.equals(producto.marca) : producto.marca != null) return false;
        if (categoria != null ? !categoria.equals(producto.categoria) : producto.categoria != null) return false;
        if (fechainicio != null ? !fechainicio.equals(producto.fechainicio) : producto.fechainicio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vendedor != null ? vendedor.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (preciosalida != null ? preciosalida.hashCode() : 0);
        result = 31 * result + (urlFoto != null ? urlFoto.hashCode() : 0);
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (fechainicio != null ? fechainicio.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productoByProducto")
    public Collection<Favoritos> getFavoritosById() {
        return favoritosById;
    }

    public void setFavoritosById(Collection<Favoritos> favoritosById) {
        this.favoritosById = favoritosById;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID")
    public Categoria getCategoriaByCategoria() {
        return categoriaByCategoria;
    }

    public void setCategoriaByCategoria(Categoria categoriaByCategoria) {
        this.categoriaByCategoria = categoriaByCategoria;
    }

    @ManyToOne
    @JoinColumn(name = "VENDEDOR", referencedColumnName = "ID", nullable = false)
    public Usuario getUsuarioByVendedor() {
        return usuarioByVendedor;
    }

    public void setUsuarioByVendedor(Usuario usuarioByVendedor) {
        this.usuarioByVendedor = usuarioByVendedor;
    }

    @OneToMany(mappedBy = "productoByProducto")
    public Collection<Puja> getPujasById() {
        return pujasById;
    }

    public void setPujasById(Collection<Puja> pujasById) {
        this.pujasById = pujasById;
    }
}

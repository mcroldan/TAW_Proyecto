package es.taw.taw_proyecto.entity;

import es.taw.taw_proyecto.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario {
    private Integer id;
    private String cp;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String pais;
    private String telefono;
    private String email;
    private Integer edad;
    private String sexo;
    private String username;
    private String password;
    private Integer rol;
    private Collection<Favoritos> favoritosById;
    private Collection<ListasUsuario> listasUsuariosById;
    private Collection<Producto> productosById;
    private Collection<Puja> pujasById;
    private Rol rolByRol;
    @ManyToMany
    @JoinTable(name = "CATEGORIAS_PREFERIDAS",
                joinColumns = @JoinColumn(name = "USUARIO",
                referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "CATEGORIA",
                referencedColumnName = "ID"))
    private List<Categoria> categoriasPreferidas;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CP")
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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
    @Column(name = "APELLIDOS")
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "DIRECCION")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "CIUDAD")
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "PAIS")
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Basic
    @Column(name = "TELEFONO")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "EDAD")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Basic
    @Column(name = "SEXO")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ROL")
    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (cp != null ? !cp.equals(usuario.cp) : usuario.cp != null) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(usuario.apellidos) : usuario.apellidos != null) return false;
        if (direccion != null ? !direccion.equals(usuario.direccion) : usuario.direccion != null) return false;
        if (ciudad != null ? !ciudad.equals(usuario.ciudad) : usuario.ciudad != null) return false;
        if (pais != null ? !pais.equals(usuario.pais) : usuario.pais != null) return false;
        if (telefono != null ? !telefono.equals(usuario.telefono) : usuario.telefono != null) return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (edad != null ? !edad.equals(usuario.edad) : usuario.edad != null) return false;
        if (sexo != null ? !sexo.equals(usuario.sexo) : usuario.sexo != null) return false;
        if (username != null ? !username.equals(usuario.username) : usuario.username != null) return false;
        if (password != null ? !password.equals(usuario.password) : usuario.password != null) return false;
        if (rol != null ? !rol.equals(usuario.rol) : usuario.rol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        result = 31 * result + (sexo != null ? sexo.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usuarioByUsuario")
    public Collection<Favoritos> getFavoritosById() {
        return favoritosById;
    }

    public void setFavoritosById(Collection<Favoritos> favoritosById) {
        this.favoritosById = favoritosById;
    }

    @OneToMany(mappedBy = "usuarioByUsuario")
    public Collection<ListasUsuario> getListasUsuariosById() {
        return listasUsuariosById;
    }

    public void setListasUsuariosById(Collection<ListasUsuario> listasUsuariosById) {
        this.listasUsuariosById = listasUsuariosById;
    }

    @OneToMany(mappedBy = "usuarioByVendedor")
    public Collection<Producto> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<Producto> productosById) {
        this.productosById = productosById;
    }

    @OneToMany(mappedBy = "usuarioByComprador")
    public Collection<Puja> getPujasById() {
        return pujasById;
    }

    public void setPujasById(Collection<Puja> pujasById) {
        this.pujasById = pujasById;
    }

    @ManyToOne
    @JoinColumn(name = "ROL", referencedColumnName = "ID", nullable = false)
    public Rol getRolByRol() {
        return rolByRol;
    }

    public void setRolByRol(Rol rolByRol) {
        this.rolByRol = rolByRol;
    }

    public List<Categoria> getCategoriasPreferidas() {
        return categoriasPreferidas;
    }

    public void setCategoriasPreferidas(List<Categoria> categoriasPreferidas) {
        this.categoriasPreferidas = categoriasPreferidas;
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(id);
        dto.setCp(cp);
        dto.setNombre(nombre);
        dto.setApellidos(apellidos);
        dto.setDireccion(direccion);
        dto.setCiudad(ciudad);
        dto.setPais(pais);
        dto.setEmail(email);
        dto.setEdad(edad);
        dto.setSexo(sexo.charAt(0));
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setRol(rolByRol.toDTO());


        return dto;
    }
}

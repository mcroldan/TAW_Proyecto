/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dto;

/**
 *
 * @author xdmrg
 */
public class UsuarioDTO {
    private Integer id;
    private String cp;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String pais;
    private String email;
    private String telefono;
    private int edad;
    private Character sexo;
    private String username;
    private String password;
    private RolDTO rol;

    public Integer getId() {
        return id;
    }

    public String getCp() {
        return cp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getEmail() {
        return email;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public Character getSexo() {
        return sexo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }
    
    
}

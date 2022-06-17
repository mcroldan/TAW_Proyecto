package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.RolRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Rol;
import es.taw.taw_proyecto.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    public UsuarioDTO comprobarUsuario(String username, String password){
        Usuario usuario = this.usuarioRepository.findUsuarioByUsernameAndPassword(username, password);
        if(usuario != null){
            return usuario.toDTO();
        }else{
            return null;
        }
    }

    public void nuevoUsuario(Model model) {
        Rol r = this.rolRepository.findByNombreIgnoreCase("Usuario");
        Usuario u = new Usuario();
        u.setRolByRol(r);
        model.addAttribute("usuario", u);
    }

    public void guardarUsuario(Usuario u){
        this.usuarioRepository.save(u);
    }
}

package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.RolRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Rol;
import es.taw.taw_proyecto.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

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

    public List<Usuario> findByName(String filtro){
        return this.usuarioRepository.findByName(filtro);
    }
    public Integer getLastId(){
        return this.usuarioRepository.getLastId();
    }

    private List<UsuarioDTO> toDTOList(List<Usuario> listaUsuarios){
        List<UsuarioDTO> res = new ArrayList<>();
        for(Usuario u : listaUsuarios)
            res.add(u.toDTO());
        return res;
    }
}

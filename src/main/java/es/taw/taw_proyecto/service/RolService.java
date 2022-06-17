package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.RolRepository;
import es.taw.taw_proyecto.entity.Rol;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private RolRepository rolRepository;

    public Rol comprobarRol(int rol){
        return this.rolRepository.findById(rol).orElse(null);
    }
    public Rol findByNombre(String N){
        return this.rolRepository.findByNombreIgnoreCase(N);
    }
}

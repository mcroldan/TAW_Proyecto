package es.taw.taw_proyecto.controller;

import es.taw.taw_proyecto.dao.EstudioRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.EstudioDTO;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Estudio;
import es.taw.taw_proyecto.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/analista")
public class AnalistaController {

    private EstudioService estudioService;

    public EstudioService getEstudioService() {
        return estudioService;
    }

    @Autowired
    public void setEstudioService(EstudioService discountCodeService) {
        this.estudioService = discountCodeService;
    }


    @GetMapping("/")
    public String getEstudioList(Model model){
        List<EstudioDTO> estudios = this.estudioService.listarEstudios("");
        model.addAttribute("estudios", estudios);
        return("/analista/Estudios");
    }

    @PostMapping("/filter")
    public String getEstudioListFilter(@RequestParam("filter") String filter,
                                       Model model){
        if(filter.equalsIgnoreCase("")) return("redirect:/analista/");
        List<EstudioDTO> estudios = this.estudioService.listarEstudios(filter);
        model.addAttribute("estudios", estudios);

        return("/analista/Estudios");
    }

    @GetMapping("/create")
    public String createNewEstudio(Model model){
        return("/analista/NuevoEstudio");
    }

    @PostMapping("/create")
    public String updateCreateGUI(@RequestParam("tabla") String tabla,
                                  Model model){
        model.addAttribute("tabla", tabla);
        String[] params;
        if(tabla.equalsIgnoreCase("Usuario")){
            params = new String[]{"id", "nombre", "apellidos", "cp", "direccion", "ciudad", "pais", "telefono", "email", "edad", "sexo", "username"};
        } else {
            params = new String[]{"id", "vendedor", "titulo", "descripcion", "preciosalida", "urlFoto", "marca", "categoria", "fechainicio", "PrecioActual"};
        }

        model.addAttribute("params", params);

        EstudioDTO estudio = new EstudioDTO();
        estudio.setId(-1);
        estudio.setTabla(tabla);
        model.addAttribute("estudio", estudio);
        return "/analista/NuevoEstudio";
    }

    @PostMapping("/save")
    public String saveEstudio(@ModelAttribute("estudio") EstudioDTO estudio){
        EstudioDTO e = this.estudioService.findById(estudio.getId());

        if(e != null){
            this.estudioService.modificarEstudio(e.getId(), estudio);
        } else {
            this.estudioService.crearEstudio(estudio);
        }
        return("redirect:/analista/");
    }

    @GetMapping("/remove/{id}")
    public String removeEstudio(@PathVariable("id") Integer id){
        this.estudioService.borrarEstudio(id);
        return("redirect:/analista/");
    }

    @GetMapping("/duplicate/{id}")
    public String duplicateEstudio(@PathVariable("id") Integer id){
        this.estudioService.duplicarEstudio(id);
        return("redirect:/analista/");
    }

    @GetMapping("/edit/{id}")
    public String editEstudio(@PathVariable("id") Integer id,
                              Model model){
        EstudioDTO estudioDTO = this.estudioService.findById(id);
        model.addAttribute("estudio", estudioDTO);
        model.addAttribute("tabla", estudioDTO.getTabla());

        String[] params;
        if(estudioDTO.getTabla().equalsIgnoreCase("Usuario")){
            params = new String[]{"id", "nombre", "apellidos", "cp", "direccion", "ciudad", "pais", "telefono", "email", "edad", "sexo", "username"};
        } else {
            params = new String[]{"id", "vendedor", "titulo", "descripcion", "preciosalida", "urlFoto", "marca", "categoria", "fechainicio", "PrecioActual"};
        }

        model.addAttribute("params", params);

        return("/analista/NuevoEstudio");
    }

    @GetMapping("/view/{id}")
    public String viewEstudio(@PathVariable("id") Integer id,
                              Model model){
        EstudioDTO estudioDTO = this.estudioService.findById(id);
        List resultado = this.estudioService.getQueryList(estudioDTO);
        model.addAttribute("estudio", estudioDTO);
        model.addAttribute("resultado", resultado);
        return("/analista/VerEstudio");
    }
}

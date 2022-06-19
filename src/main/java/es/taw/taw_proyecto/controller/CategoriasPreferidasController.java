package es.taw.taw_proyecto.controller;

import es.taw.taw_proyecto.dto.CategoriaDTO;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Usuario;
import es.taw.taw_proyecto.service.CategoriaService;
import es.taw.taw_proyecto.service.CategoriasPreferidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/categoriasPreferidas")
public class CategoriasPreferidasController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriasPreferidasService categoriasPreferidasService;

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void setCategoriasPreferidasService(CategoriasPreferidasService categoriasPreferidasService) {
        this.categoriasPreferidasService = categoriasPreferidasService;
    }

    @PostMapping("/mostrar")
    public String mostrarCategorias(Model model, HttpSession session){
        int userid = ((Usuario)session.getAttribute("usuario")).getId();
        List<CategoriaDTO> categorias = this.categoriaService.findByUserID(userid);
        model.addAttribute("categorias", categorias);
        return("/mostrar");
    }

    @GetMapping("/elegir")
    public String elegirCategoria(Model model, HttpSession session){
        int userid = ((UsuarioDTO)session.getAttribute("usuario")).getId();
        List<CategoriaDTO> categoriasDisponibles = this.categoriaService.findCategoriasDisponibles(userid);
        if(categoriasDisponibles == null)
            model.addAttribute("error", "No hay categorías disponibles para añadir");
        else
            model.addAttribute("categorias", categoriasDisponibles);
        return("elegir");
    }

    @PostMapping("/nueva")
    public String nuevaCategoriaPreferida(Model model, HttpSession session,
                                          @RequestParam("categoriaid") String categoriaid){
        int userid = ((UsuarioDTO)session.getAttribute("usuario")).getId();
        /*if(categoriaid == null){
            List<CategoriaDTO> categoriasDisponibles = this.categoriaService.findCategoriasDisponibles(userid);
            if(categoriasDisponibles == null)
                model.addAttribute("error", "No hay categorías disponibles para añadir");
            else
                model.addAttribute("categorias", categoriasDisponibles);
            return("anyadirCategoriaUsuario");
        }
        else{*/
        this.categoriasPreferidasService.crearRelacion(userid, Integer.valueOf(categoriaid));
        return("redirect:/categoriasPreferidas/mostrar");
    }

    @PostMapping("/borrar")
    public String borrarCategoriaPreferida(Model model, HttpSession session,
                                          @RequestParam("categoriaid") String categoriaid){
        int userid = ((UsuarioDTO)session.getAttribute("usuario")).getId();
        this.categoriasPreferidasService.borrarCategoriaPreferida(Integer.parseInt(categoriaid), userid);
        return("redirect:/categoriasPreferidas/mostrar");
    }

    @PostMapping("/mostrarProductos")
    public String mostrarProductos(){
        throw new UnsupportedOperationException();
    }
}

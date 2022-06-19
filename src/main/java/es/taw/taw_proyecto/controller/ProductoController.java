package es.taw.taw_proyecto.controller;

import es.taw.taw_proyecto.dto.CategoriaDTO;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.entity.Usuario;
import es.taw.taw_proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/mostrar")
    public String mostrarProductosDisponibles(Model model, HttpSession session){
        int userid = ((Usuario)session.getAttribute("usuario")).getId();
        List<ProductoDTO> productos = this.productoService.listarProductosDisponibles(userid);
        model.addAttribute("productos", productos);
        return("mostrar");
    }
    /*@PostMapping("/mostrar")
    public String mostrarCategoriasFiltroMarca(Model model, HttpSession session,
                                               @RequestParam("filtro") String filtroMarca){
        int userid = ((Usuario)session.getAttribute("usuario")).getId();
        List<CategoriaDTO> categorias = this.categoriaService.findByUserID(userid);
        model.addAttribute("categorias", categorias);
        return("categoriasPreferidas");
    }*/
}

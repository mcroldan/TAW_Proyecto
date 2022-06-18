package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.*;
import es.taw.taw_proyecto.dto.EstudioDTO;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Estudio;
import es.taw.taw_proyecto.entity.Producto;
import es.taw.taw_proyecto.entity.Usuario;
import es.taw.taw_proyecto.dao.EstudioRepository;
import es.taw.taw_proyecto.dao.ProductoRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.EstudioDTO;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.entity.Estudio;
import es.taw.taw_proyecto.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {

    private EstudioRepository estudioRepository;

    private UsuarioRepository usuarioRepository;

    private ProductoRepository productoRepository;

    @Autowired
    public void setEstudioRepository(EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    private List<EstudioDTO> listaEstudioADTO (List<Estudio> lista) {
        List<EstudioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Estudio estudio : lista) {
                listaDTO.add(estudio.toDTO());
            }
        }
        return listaDTO;
    }

    private List<ProductoDTO> listaProductoADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }

    private List<UsuarioDTO> listaUsuarioADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }

    public List<EstudioDTO> listarEstudios (String filtroNombre) {
        List<Estudio> lista = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            lista = this.estudioRepository.findAll();
        } else {
            lista = this.estudioRepository.findEstudioByName(filtroNombre);
        }

        return this.listaEstudioADTO(lista);
    }

    public void borrarEstudio(Integer id) {
        Estudio estudio = this.estudioRepository.findById(id).get();

        this.estudioRepository.delete(estudio);
    }

    private void rellenarEstudio(Estudio e, EstudioDTO estudioDTO){
        e.setId(estudioDTO.getId());
        e.setNombre(estudioDTO.getNombre());
        e.setTabla(estudioDTO.getTabla());
        if(!estudioDTO.getAgrupar().equalsIgnoreCase("-")){
            estudioDTO.setOrdenar(estudioDTO.getAgrupar());
        }
        e.setOrdenar(estudioDTO.getOrdenar());
        e.setAgrupar(estudioDTO.getAgrupar());
        if(!estudioDTO.getAgrupar().equalsIgnoreCase("") && estudioDTO.getOperacion().equalsIgnoreCase("")){
            estudioDTO.setOperacion("Cantidad");
        }
        e.setOperacion(estudioDTO.getOperacion());
        e.setTipoOrden(estudioDTO.getTipoOrden());
        e.setNumElementos(estudioDTO.getNumElementos());

    }

    public void crearEstudio(EstudioDTO estudioDTO){
        Estudio e = new Estudio();

        if(estudioDTO.getId() == null || estudioDTO.getId() < 0){
            estudioDTO.setId(this.estudioRepository.getLastId()+1);
        }

        this.rellenarEstudio(e, estudioDTO);
        System.out.println(e.getId());

        this.estudioRepository.save(e);
    }

    public void modificarEstudio(Integer id, EstudioDTO estudioDTO){
        Estudio e = this.estudioRepository.findById(id).get();

        this.rellenarEstudio(e, estudioDTO);

        this.estudioRepository.save(e);
    }

    public void duplicarEstudio(Integer id){
        EstudioDTO e = this.estudioRepository.findById(id).get().toDTO();
        e.setNombre(e.getNombre() + " - Copy");
        e.setId(-1);

        this.crearEstudio(e);
    }

    public EstudioDTO findById(Integer id){
        Estudio estudio = this.estudioRepository.findById(id).orElse(null);
        EstudioDTO estudioDTO = null;
        if(estudio != null) { estudioDTO = estudio.toDTO(); }
        return estudioDTO;
    }

    public List getQueryList(EstudioDTO e){
        long count = 1;

        if(e.getTabla().equalsIgnoreCase("Usuario")){
            count = this.usuarioRepository.count();
        } else if(e.getTabla().equalsIgnoreCase("Producto")){
            count = this.productoRepository.count();
        }

        List l;
        if(e.getAgrupar().equalsIgnoreCase("-")){
            l = this.estudioRepository.findAnalistaQueryNoGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden());
        } else {
            if(e.getOperacion().equalsIgnoreCase("Cantidad")) {
                l = this.estudioRepository.findAnalistaQueryGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar());
            } else {
                l = this.estudioRepository.findAnalistaQueryGroupPercentage(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar(), count);
            }
            return l; // En este paso, la lista es de objetos, no de entidades*/
        }
        if(e.getTabla().equalsIgnoreCase("Usuario")){
            return this.listaUsuarioADTO(l);
        } else {
            return this.listaProductoADTO(l);
        }
    }
}

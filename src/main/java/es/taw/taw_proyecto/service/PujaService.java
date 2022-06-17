package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.ProductoRepository;
import es.taw.taw_proyecto.dao.PujaRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.PujaDTO;
import es.taw.taw_proyecto.dto.PujaDTO;
import es.taw.taw_proyecto.entity.Puja;
import es.taw.taw_proyecto.entity.Puja;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PujaService {
    private PujaRepository pujaRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;


    public List<PujaDTO> findByUserID(int userid){
        return toDTOList(this.pujaRepository.findByUsuarioByComprador(userid));
    }
    public List<Puja> findByUserIDAndProductID(int userid, int productoid){
        return this.pujaRepository.findByCompradorAndProducto(userid, productoid);
    }
    public void nuevaPuja(String precio, int usuarioid, int productoid) {
        List<Puja> pujaAnt = this.pujaRepository.findByCompradorAndProducto(usuarioid, productoid);
        if(!pujaAnt.isEmpty()){
            this.pujaRepository.delete(pujaAnt.get(0));
        }
        Puja nuevaPuja = new Puja();
        nuevaPuja.setAdjudicado(false);
        nuevaPuja.setUsuarioByComprador(this.usuarioRepository.findById(usuarioid).orElse(null));
        nuevaPuja.setFecha((java.sql.Date) new Date());
        nuevaPuja.setPrecio(Double.parseDouble(precio));
        nuevaPuja.setProductoByProducto(this.productoRepository.findById(productoid).orElse(null));

        this.pujaRepository.save(nuevaPuja);
    }

    public void borrarPuja(Integer id) {
        Puja pujaABorrar = this.pujaRepository.findById(id).orElse(null);
        this.pujaRepository.delete(pujaABorrar);
    }

    private List<PujaDTO> toDTOList(List<Puja> listaPujas){
        List<PujaDTO> res = new ArrayList<>();
        for(Puja p : listaPujas)
            res.add(p.toDTO());
        return res;
    }
}

package es.taw.taw_proyecto.dao;

import java.util.List;

public interface EstudioCustomRepository {
    public List findAnalistaQueryNoGroup(String tabla, String orden, Integer num, String tipoOrden);

    public List findAnalistaQueryGroup(String tabla, String orden, Integer num, String tipoOrden, String grupo);

    public List findAnalistaQueryGroupPercentage(String tabla, String orden, Integer num, String tipoOrden, String grupo, Long count);

}

package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;


public interface ISeleccionServicio {

    public List<Seleccion> listar();

    public Seleccion obtener(int id);

    public List<Seleccion> buscar(String nombre);

    public Seleccion agregar(Seleccion pais);

    public Seleccion modificar(Seleccion pais);

    public boolean eliminar(int id);

}

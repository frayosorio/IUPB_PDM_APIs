package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface ICiudadServicio {

    public List<Ciudad> listar();

    public Ciudad obtener(int id);

    public List<Ciudad> buscar(String nombre);

    public Ciudad agregar(Ciudad ciudad);

    public Ciudad modificar(Ciudad ciudad);

    public boolean eliminar(int id);
}

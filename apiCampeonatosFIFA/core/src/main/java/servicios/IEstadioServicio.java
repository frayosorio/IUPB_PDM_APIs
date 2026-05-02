package campeonatosfifa.api.core.servicios;


import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface IEstadioServicio {

   public List<Estadio> listar();

    public Estadio obtener(int id);

    public List<Estadio> buscar(String nombre);

    public Estadio agregar(Estadio estadio);

    public Estadio modificar(Estadio estadio);

    public boolean eliminar(int id);
}

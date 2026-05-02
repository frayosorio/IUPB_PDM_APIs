package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface IFaseServicio {

   public List<Fase> listar();

    public Fase obtener(int id);

    public List<Fase> buscar(String nombre);

    public Fase agregar(Fase fase);

    public Fase modificar(Fase fase);

    public boolean eliminar(int id);
}
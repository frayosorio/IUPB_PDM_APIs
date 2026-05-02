package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface ICampeonatoServicio {

    public List<Campeonato> listar();

    public Campeonato obtener(int id);

    public List<Campeonato> buscar(String nombre);

    public Campeonato agregar(Campeonato campeonato);

    public Campeonato modificar(Campeonato campeonato);

    public boolean eliminar(int id);

    // ***** Paises del Campeonato *****

    public List<CampeonatoPais> listarPaises(int idCampeonato);

    public CampeonatoPais obtenerPais(int idCampeonato, int idPais);

    public CampeonatoPais agregarPais(CampeonatoPais campeonatoPais);

    public CampeonatoPais modificarPais(CampeonatoPais campeonatoPais);

    public boolean eliminarPais(int idCampeonato, int idPais);

}

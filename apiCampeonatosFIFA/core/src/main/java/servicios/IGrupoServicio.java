package campeonatosfifa.api.core.servicios;

import java.util.List;

import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.dominio.DTOs.*;

public interface IGrupoServicio {

    public List<Grupo> listar();

    public List<Grupo> listarCampeonato(int idCampeonato);

    public Grupo obtener(int id);

    public List<Grupo> buscar(String nombre);

    public Grupo agregar(Grupo grupo);

    public Grupo modificar(Grupo grupo);

    public boolean eliminar(int id);

    // ***** Paises del Grupo *****

    public List<GrupoPais> listarPaises(int idGrupo);

    public GrupoPais obtenerPais(int idGrupo, int idPais);

    public GrupoPais agregarPais(GrupoPais grupoPais);

    public GrupoPais modificarPais(GrupoPais grupoPais);

    public boolean eliminarPais(int idGrupo, int idPais);

    //***** Tabla de Posiciones *****

    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo);

    public void generarEncuentrosOctavos(int idGrupo1, int idGrupo2, int idFaseOctavos);

}

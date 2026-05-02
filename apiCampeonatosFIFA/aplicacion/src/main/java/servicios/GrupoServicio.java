package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import jakarta.persistence.EntityManager;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.dominio.DTOs.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class GrupoServicio implements IGrupoServicio {

    @Autowired
    private EntityManager em;

    private IGrupoRepositorio repositorio;
    private IGrupoPaisRepositorio repositorioPaises;
    private ISeleccionRepositorio repositorioSelecciones;
    private IFaseRepositorio repositorioFases;
    private ICampeonatoRepositorio repositorioCampeonatos;
    private IEncuentroRepositorio repositorioEncuentros;

    public GrupoServicio(IGrupoRepositorio repositorio, IGrupoPaisRepositorio repositorioPaises,
            ISeleccionRepositorio repositorioSelecciones, ICampeonatoRepositorio repositorioCampeonatos,
            IFaseRepositorio repositorioFases, IEncuentroRepositorio repositorioEncuentros) {
        this.repositorio = repositorio;
        this.repositorioPaises = repositorioPaises;
        this.repositorioSelecciones = repositorioSelecciones;
        this.repositorioFases = repositorioFases;
        this.repositorioCampeonatos = repositorioCampeonatos;
        this.repositorioEncuentros = repositorioEncuentros;
    }

    @Override
    public List<Grupo> listar() {
        return repositorio.findAll(Sort.by(
                Sort.Order.asc("campeonato.nombre"), // primero ordena por nombre del campeonato
                Sort.Order.asc("nombre") // luego por nombre del grupo
        ));
    }

    @Override
    public List<Grupo> listarCampeonato(int idCampeonato) {
        return repositorio.listarCampeonato(idCampeonato);
    }

    @Override
    public Grupo obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Grupo> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Grupo agregar(Grupo grupo) {
        grupo.setId(0);
        return repositorio.save(grupo);
    }

    @Override
    public Grupo modificar(Grupo grupo) {
        if (repositorio.findById(grupo.getId()).isEmpty())
            return null;
        return repositorio.save(grupo);
    }

    @Override
    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // ***** Paises del Grupo *****

    @Override
    public List<GrupoPais> listarPaises(int idGrupo) {
        return repositorioPaises.listarPaises(idGrupo);
    }

    @Override
    public GrupoPais obtenerPais(int idGrupo, int idPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(idGrupo, idPais);
        return repositorioPaises.findById(grupoPaisId).isEmpty() ? null : repositorioPaises.findById(grupoPaisId).get();
    }

    @Override
    public GrupoPais agregarPais(GrupoPais grupoPais) {
        return repositorioPaises.save(grupoPais);
    }

    @Override
    public GrupoPais modificarPais(GrupoPais grupoPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(
                grupoPais.getGrupo().getId(),
                grupoPais.getPais().getId());
        if (repositorioPaises.findById(grupoPaisId).isEmpty())
            return null;
        return repositorioPaises.save(grupoPais);
    }

    @Override
    public boolean eliminarPais(int idGrupo, int idPais) {
        GrupoPaisId grupoPaisId = new GrupoPaisId(idGrupo, idPais);
        try {
            repositorioPaises.deleteById(grupoPaisId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // ***** Tabla de Posiciones *****

    @Override
    public List<TablaPosicionesDto> listarTablaPosiciones(int idGrupo) {
        List<TablaPosicionesDto> tablaPosiciones = em
                .createNativeQuery(
                        "SELECT * FROM fobtenertablaposiciones(:idgrupotabla) ORDER BY Puntos DESC, GF - GC DESC",
                        TablaPosicionesDto.class)
                .setParameter("idgrupotabla", idGrupo)
                .getResultList();

        return tablaPosiciones;
    }

    @Override
    public void generarEncuentrosOctavos(int idGrupo1, int idGrupo2, int idFaseOctavos) {
        int idCampeonato = repositorio.findById(idGrupo1)
                .orElseThrow().getCampeonato().getId();

        List<TablaPosicionesDto> posiciones1 = listarTablaPosiciones(idGrupo1);
        List<TablaPosicionesDto> posiciones2 = listarTablaPosiciones(idGrupo2);

        if (posiciones1.size() < 2 || posiciones2.size() < 2) {
            throw new IllegalArgumentException("No hay suficientes países para generar los encuentros.");
        }

        int idSeleccionGrupo1Primera = posiciones1.get(0).getId(); // 1° grupo 1
        int idSeleccionGrupo1Segunda = posiciones1.get(1).getId(); // 2° grupo 1
        int idSeleccionGrupo2Primera = posiciones2.get(0).getId(); // 1° grupo 2
        int idSeleccionGrupo2Segunda = posiciones2.get(1).getId(); // 2° grupo 2

        Seleccion pais1 = repositorioSelecciones.findById(idSeleccionGrupo1Primera).orElseThrow();
        Seleccion pais2 = repositorioSelecciones.findById(idSeleccionGrupo2Segunda).orElseThrow();
        Seleccion pais3 = repositorioSelecciones.findById(idSeleccionGrupo2Primera).orElseThrow();
        Seleccion pais4 = repositorioSelecciones.findById(idSeleccionGrupo1Segunda).orElseThrow();

        Fase fase = repositorioFases.findById(idFaseOctavos).orElseThrow();
        Campeonato campeonato = repositorioCampeonatos.findById(idCampeonato).orElseThrow();

        Encuentro encuentro1 = new Encuentro();
        encuentro1.setPais1(pais1);
        encuentro1.setPais2(pais2);
        encuentro1.setFase(fase);
        encuentro1.setCampeonato(campeonato);

        Encuentro encuentro2 = new Encuentro();
        encuentro2.setPais1(pais3);
        encuentro2.setPais2(pais4);
        encuentro2.setFase(fase);
        encuentro2.setCampeonato(campeonato);

        repositorioEncuentros.save(encuentro1);
        repositorioEncuentros.save(encuentro2);
    }

}

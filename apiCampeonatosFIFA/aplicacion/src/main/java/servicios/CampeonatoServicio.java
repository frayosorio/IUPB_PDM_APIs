package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class CampeonatoServicio implements ICampeonatoServicio {

    @Autowired
    private ICampeonatoRepositorio repositorio;
    @Autowired
    private ICampeonatoPaisRepositorio repositorioPaises;

    @Override
    public List<Campeonato> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public Campeonato obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Campeonato> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Campeonato agregar(Campeonato campeonato) {
        campeonato.setId(0);
        return repositorio.save(campeonato);
    }

    @Override
    public Campeonato modificar(Campeonato campeonato) {
        if (repositorio.findById(campeonato.getId()).isEmpty())
            return null;
        return repositorio.save(campeonato);
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

    // ***** Paises del Campeonato *****

    @Override
    public List<CampeonatoPais> listarPaises(int idCampeonato) {
        return repositorioPaises.listarPaises(idCampeonato);
    }

    @Override
    public CampeonatoPais obtenerPais(int idCampeonato, int idPais) {
        CampeonatoPaisId campeonatoPaisId = new CampeonatoPaisId(idCampeonato, idPais);
        return repositorioPaises.findById(campeonatoPaisId).isEmpty() ? null : repositorioPaises.findById(campeonatoPaisId).get();
    }

    @Override
    public CampeonatoPais agregarPais(CampeonatoPais campeonatoPais) {
        return repositorioPaises.save(campeonatoPais);
    }

    @Override
    public CampeonatoPais modificarPais(CampeonatoPais campeonatoPais) {
        CampeonatoPaisId campeonatoPaisId = new CampeonatoPaisId(
                campeonatoPais.getCampeonato().getId(),
                campeonatoPais.getPais().getId());
        if (repositorioPaises.findById(campeonatoPaisId).isEmpty())
            return null;
        return repositorioPaises.save(campeonatoPais);
    }

    @Override
    public boolean eliminarPais(int idCampeonato, int idPais) {
        CampeonatoPaisId campeonatoPaisId = new CampeonatoPaisId(idCampeonato, idPais);
        try {
            repositorioPaises.deleteById(campeonatoPaisId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

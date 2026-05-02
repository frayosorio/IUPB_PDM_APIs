package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class EncuentroServicio implements IEncuentroServicio {

    private IEncuentroRepositorio repositorio;

    public EncuentroServicio(IEncuentroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Encuentro> listarCampeonato(int idCampeonato) {
        return repositorio.listarCampeonato(idCampeonato);
    }

    @Override
    public List<Encuentro> listarCampeonatoFase(int idCampeonato, int idFase) {
        return repositorio.listarCampeonatoFase(idCampeonato, idFase);
    }

    @Override
    public List<Encuentro> listarGrupo(int idGrupo) {
        return repositorio.listarGrupo(idGrupo);
    }

    @Override
    public Encuentro obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public Encuentro agregar(Encuentro encuentro) {
        encuentro.setId(0);
        return repositorio.save(encuentro);
    }

    @Override
    public Encuentro modificar(Encuentro encuentro) {
        if (repositorio.findById(encuentro.getId()).isEmpty())
            return null;
        return repositorio.save(encuentro);
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

}

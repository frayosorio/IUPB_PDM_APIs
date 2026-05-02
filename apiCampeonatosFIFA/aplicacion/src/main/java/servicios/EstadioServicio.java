package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class EstadioServicio implements IEstadioServicio {

    private IEstadioRepositorio repositorio;

    public EstadioServicio(IEstadioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Estadio> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public Estadio obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Estadio> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Estadio agregar(Estadio estadio) {
        estadio.setId(0);
        return repositorio.save(estadio);
    }

    @Override
    public Estadio modificar(Estadio estadio) {
        if (repositorio.findById(estadio.getId()).isEmpty())
            return null;
        return repositorio.save(estadio);
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

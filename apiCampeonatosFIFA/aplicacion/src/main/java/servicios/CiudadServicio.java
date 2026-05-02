package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class CiudadServicio implements ICiudadServicio {

    private ICiudadRepositorio repositorio;

    public CiudadServicio(ICiudadRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Ciudad> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public Ciudad obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Ciudad> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Ciudad agregar(Ciudad ciudad) {
        ciudad.setId(0);
        return repositorio.save(ciudad);
    }

    @Override
    public Ciudad modificar(Ciudad ciudad) {
        if (repositorio.findById(ciudad.getId()).isEmpty())
            return null;
        return repositorio.save(ciudad);
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

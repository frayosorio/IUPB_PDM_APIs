package campeonatosfifa.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import campeonatosfifa.api.core.servicios.*;
import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.infraestructura.repositorios.*;

@Service
public class FaseServicio implements IFaseServicio {

    private IFaseRepositorio repositorio;

    public FaseServicio(IFaseRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Fase> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    @Override
    public Fase obtener(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    @Override
    public List<Fase> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Fase agregar(Fase fase) {
        fase.setId(0);
        return repositorio.save(fase);
    }

    @Override
    public Fase modificar(Fase fase) {
        if (repositorio.findById(fase.getId()).isEmpty())
            return null;
        return repositorio.save(fase);
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

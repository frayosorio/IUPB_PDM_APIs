package campeonatosfifa.api.presentacion.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.core.servicios.*;

@RestController
@RequestMapping("/api/encuentros")
public class EncuentroControlador {

    private IEncuentroServicio servicio;

    public EncuentroControlador(IEncuentroServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/listarcampeonato/{idCampeonato}", method = RequestMethod.GET)
    public List<Encuentro> listarCampeonato(@PathVariable int idCampeonato) {
        return servicio.listarCampeonato(idCampeonato);
    }

    @RequestMapping(value = "/listarcampeonatofase/{idCampeonato}/{idFase}", method = RequestMethod.GET)
    public List<Encuentro> listarCampeonatoFase(@PathVariable int idCampeonato, @PathVariable int idFase) {
        return servicio.listarCampeonatoFase(idCampeonato, idFase);
    }

    @RequestMapping(value = "/listargrupo/{idGrupo}", method = RequestMethod.GET)
    public List<Encuentro> listarGrupo(@PathVariable int idGrupo) {
        return servicio.listarGrupo(idGrupo);
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Encuentro obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Encuentro agregar(@RequestBody Encuentro encuentro) {
        return servicio.agregar(encuentro);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Encuentro modificar(@RequestBody Encuentro encuentro) {
        return servicio.modificar(encuentro);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}

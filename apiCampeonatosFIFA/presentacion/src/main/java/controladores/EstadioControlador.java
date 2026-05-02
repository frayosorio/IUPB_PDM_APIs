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
@RequestMapping("/api/estadios")
public class EstadioControlador {

    private IEstadioServicio servicio;

    public EstadioControlador(IEstadioServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Estadio> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Estadio obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{nombre}", method = RequestMethod.GET)
    public List<Estadio> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Estadio agregar(@RequestBody Estadio estadio) {
        return servicio.agregar(estadio);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Estadio modificar(@RequestBody Estadio estadio) {
        return servicio.modificar(estadio);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}

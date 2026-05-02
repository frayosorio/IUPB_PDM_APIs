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
@RequestMapping("/api/ciudades")
public class CiudadControlador {

    private ICiudadServicio servicio;

    public CiudadControlador(ICiudadServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Ciudad> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Ciudad obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{nombre}", method = RequestMethod.GET)
    public List<Ciudad> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Ciudad agregar(@RequestBody Ciudad ciudad) {
        return servicio.agregar(ciudad);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Ciudad modificar(@RequestBody Ciudad ciudad) {
        return servicio.modificar(ciudad);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}

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
@RequestMapping("/api/campeonatos")
public class CampeonatoControlador {
    private ICampeonatoServicio servicio;

    public CampeonatoControlador(ICampeonatoServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Campeonato> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Campeonato obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{nombre}", method = RequestMethod.GET)
    public List<Campeonato> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Campeonato agregar(@RequestBody Campeonato pais) {
        return servicio.agregar(pais);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Campeonato modificar(@RequestBody Campeonato pais) {
        return servicio.modificar(pais);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

    // ***** Paises del Campeonato *****

    @RequestMapping(value = "listarpaises/{idCampeonato}", method = RequestMethod.GET)
    public List<CampeonatoPais> listarPaises(@PathVariable int idCampeonato) {
        return servicio.listarPaises(idCampeonato);
    }

    @RequestMapping(value = "obtenerpais/{idCampeonato}/{idPais}", method = RequestMethod.GET)
    public CampeonatoPais obtenerPais(@PathVariable int idCampeonato, @PathVariable int idPais) {
        return servicio.obtenerPais(idCampeonato, idPais);
    }

    @RequestMapping(value = "/agregarpais", method = RequestMethod.POST)
    public CampeonatoPais agregarPais(@RequestBody CampeonatoPais campeonatoPais) {
        return servicio.agregarPais(campeonatoPais);
    }

    @RequestMapping(value = "/modificarpais", method = RequestMethod.PUT)
    public CampeonatoPais modificarPais(@RequestBody CampeonatoPais campeonatoPais) {
        return servicio.modificarPais(campeonatoPais);
    }

    @RequestMapping(value = "eliminarpais/{idCampeonato}/{idPais}", method = RequestMethod.DELETE)
    public boolean eliminarPais(@PathVariable int idCampeonato, @PathVariable int idPais) {
        return servicio.eliminarPais(idCampeonato, idPais);
    }

}

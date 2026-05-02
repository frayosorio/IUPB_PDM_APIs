package campeonatosfifa.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import campeonatosfifa.api.dominio.entidades.*;
import campeonatosfifa.api.dominio.DTOs.*;
import campeonatosfifa.api.core.servicios.*;

@RestController
@RequestMapping("/api/grupos")
public class GrupoControlador {

    @Autowired
    private IGrupoServicio servicio;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Grupo> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/listarcampeonato/{idCampeonato}", method = RequestMethod.GET)
    public List<Grupo> listarCampeonato(@PathVariable int idCampeonato) {
        return servicio.listarCampeonato(idCampeonato);
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Grupo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{nombre}", method = RequestMethod.GET)
    public List<Grupo> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Grupo agregar(@RequestBody Grupo grupo) {
        return servicio.agregar(grupo);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Grupo modificar(@RequestBody Grupo grupo) {
        return servicio.modificar(grupo);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

    // ***** Paises del Grupo *****

    @RequestMapping(value = "listarpaises/{idGrupo}", method = RequestMethod.GET)
    public List<GrupoPais> listarPaises(@PathVariable int idGrupo) {
        return servicio.listarPaises(idGrupo);
    }

    @RequestMapping(value = "obtenerpais/{idGrupo}/{idPais}", method = RequestMethod.GET)
    public GrupoPais obtenerPais(@PathVariable int idGrupo, @PathVariable int idPais) {
        return servicio.obtenerPais(idGrupo, idPais);
    }

    @RequestMapping(value = "/agregarpais", method = RequestMethod.POST)
    public GrupoPais agregarPais(@RequestBody GrupoPais grupoPais) {
        return servicio.agregarPais(grupoPais);
    }

    @RequestMapping(value = "/modificarpais", method = RequestMethod.PUT)
    public GrupoPais modificarPais(@RequestBody GrupoPais grupoPais) {
        return servicio.modificarPais(grupoPais);
    }

    @RequestMapping(value = "eliminarpais/{idGrupo}/{idPais}", method = RequestMethod.DELETE)
    public boolean eliminarPais(@PathVariable int idGrupo, @PathVariable int idPais) {
        return servicio.eliminarPais(idGrupo, idPais);
    }

    // ***** Tabla de Posiciones *****

    @RequestMapping(value = "/posiciones/{idGrupo}", method = RequestMethod.GET)
    public List<TablaPosicionesDto> listarTablaPosiciones(@PathVariable int idGrupo) {
        return servicio.listarTablaPosiciones(idGrupo);
    }

    @RequestMapping(value = "/generarencuentros/{idGrupo1}/{idGrupo2}/{idFaseOctavos}", method = RequestMethod.GET)
    public void generarEncuentrosOctavos(@PathVariable int idGrupo1, @PathVariable int idGrupo2, @PathVariable int idFaseOctavos)  {
        servicio.generarEncuentrosOctavos(idGrupo1, idGrupo2, idFaseOctavos);
    }


}

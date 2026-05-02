package campeonatosfifa.api.core.servicios;

import java.util.List;
import campeonatosfifa.api.dominio.entidades.*;

public interface IEncuentroServicio {

        public List<Encuentro> listarCampeonato(int idCampeonato);

        public List<Encuentro> listarCampeonatoFase(int idCampeonato, int idFase);

        public List<Encuentro> listarGrupo(int idGrupo);

        public Encuentro obtener(int Id);

        public Encuentro agregar(Encuentro encuentro);

        public Encuentro modificar(Encuentro encuentro);

        public boolean eliminar(int Id);

}

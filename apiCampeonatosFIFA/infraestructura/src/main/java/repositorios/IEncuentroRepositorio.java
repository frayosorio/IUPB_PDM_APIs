package campeonatosfifa.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.dominio.entidades.*;

@Repository
public interface IEncuentroRepositorio extends JpaRepository<Encuentro, Integer> {

    @Query("SELECT e FROM Encuentro e  WHERE e.campeonato.id = ?1 ORDER BY e.fecha ASC")
    public List<Encuentro> listarCampeonato(int idCampeonato);

    @Query("SELECT e FROM Encuentro e  WHERE e.campeonato.id = ?1 AND e.fase.id= ?2 ORDER BY e.fecha ASC")
    public List<Encuentro> listarCampeonatoFase(int idCampeonato, int idFase);

    @Query("SELECT DISTINCT e FROM Encuentro e " +
            "JOIN Grupo g ON g.campeonato.id = e.campeonato.id " +
            "JOIN GrupoPais gp ON gp.grupo.id = g.id " +
            "WHERE e.fase.id = 1 " +
            "AND g.id = ?1 " +
            "AND (gp.pais.id = e.pais1.id OR gp.pais.id = e.pais2.id) " +
            "ORDER BY e.fecha ASC")
    public List<Encuentro> listarGrupo(int idGrupo);

}
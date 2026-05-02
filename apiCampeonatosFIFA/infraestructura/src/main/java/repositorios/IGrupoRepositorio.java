package campeonatosfifa.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.dominio.entidades.*;

@Repository
public interface IGrupoRepositorio extends JpaRepository<Grupo, Integer> {

    @Query("SELECT g FROM Grupo g WHERE g.nombre LIKE '%' || ?1 || '%' ORDER BY g.nombre ASC")
    public List<Grupo> buscar(String nombre);

    @Query("SELECT g FROM Grupo g " +
            "JOIN Campeonato c ON g.campeonato=c " +
            "WHERE c.id=?1 "+
            "ORDER BY g.nombre ASC")
    public List<Grupo> listarCampeonato(int id);
    

}
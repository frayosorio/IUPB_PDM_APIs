package campeonatosfifa.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.dominio.entidades.*;

@Repository
public interface IEstadioRepositorio extends JpaRepository<Estadio, Integer> {

    @Query("SELECT e FROM Estadio e WHERE e.nombre LIKE '%' || ?1 || '%' ORDER BY e.nombre ASC")
    public List<Estadio> buscar(String nombre);

}

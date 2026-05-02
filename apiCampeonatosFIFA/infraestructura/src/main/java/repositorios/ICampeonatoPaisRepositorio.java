package campeonatosfifa.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.dominio.entidades.*;

@Repository
public interface ICampeonatoPaisRepositorio extends JpaRepository<CampeonatoPais, CampeonatoPaisId> {

    @Query("SELECT gp FROM CampeonatoPais gp WHERE gp.campeonato.id=?1 ORDER BY gp.pais.nombre ASC")
    public List<CampeonatoPais> listarPaises(int idCampeonato);
}
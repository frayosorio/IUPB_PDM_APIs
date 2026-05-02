package campeonatosfifa.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import campeonatosfifa.api.dominio.entidades.*;

@Repository
public interface IGrupoPaisRepositorio extends JpaRepository<GrupoPais, GrupoPaisId> {

    @Query("SELECT gp FROM GrupoPais gp WHERE gp.grupo.id=?1 ORDER BY gp.pais.nombre ASC")
    public List<GrupoPais> listarPaises(int idGrupo);
}
package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@IdClass(CampeonatoPaisId.class)
@Table(name = "campeonatopais")
public class CampeonatoPais {

    @Id
    @ManyToOne
    @JoinColumn(name = "idcampeonato", referencedColumnName = "id", nullable = false)
    private Campeonato campeonato;

    @Id
    @ManyToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id", nullable = false)
    private Seleccion pais;

    public CampeonatoPais() {
    }

    public CampeonatoPais(Campeonato campeonato, Seleccion pais) {
        this.campeonato = campeonato;
        this.pais = pais;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }

}

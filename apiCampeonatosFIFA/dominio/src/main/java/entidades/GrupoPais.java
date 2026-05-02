package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@IdClass(GrupoPaisId.class)
@Table(name = "grupopais")
public class GrupoPais {

    @Id
    @ManyToOne
    @JoinColumn(name = "idgrupo", referencedColumnName = "id", nullable = false)
    private Grupo grupo;

    @Id
    @ManyToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id", nullable = false)
    private Seleccion pais;

    public GrupoPais() {
    }

    public GrupoPais(Grupo grupo, Seleccion pais) {
        this.grupo = grupo;
        this.pais = pais;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }
}

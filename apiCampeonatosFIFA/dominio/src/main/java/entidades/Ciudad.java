package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_ciudad")
    @SequenceGenerator(name = "secuencia_ciudad", sequenceName = "secuencia_ciudad", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "ciudad", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id", nullable = false)
    private Seleccion pais;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, Seleccion pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Seleccion getPais() {
        return pais;
    }

    public void setPais(Seleccion pais) {
        this.pais = pais;
    }
}


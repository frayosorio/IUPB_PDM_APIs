package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "campeonato")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_campeonato")
    @SequenceGenerator(name = "secuencia_campeonato", sequenceName = "secuencia_campeonato", allocationSize = 1)
    @Column(name = "id")
    private int id;
    
    @Column(name = "campeonato", length = 100, unique = true)
    private String nombre;
    @Column(name = "año")
    private int año;

    public Campeonato() {
    }

    public Campeonato(int id, String nombre, int año) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

}

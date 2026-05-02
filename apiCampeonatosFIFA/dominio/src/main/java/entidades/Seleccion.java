package campeonatosfifa.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class Seleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_pais")
    @SequenceGenerator(name = "secuencia_pais", sequenceName = "secuencia_pais", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "pais", length = 100, unique = true)
    private String nombre;
    @Column(name = "entidad", length = 100, unique = true)
    private String entidad;

    public Seleccion(int id, String nombre, String entidad) {
        this.id = id;
        this.nombre = nombre;
        this.entidad = entidad;
    }

    public Seleccion() {
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

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

}

package campeonatosfifa.api.dominio.entidades;

public class CampeonatoPaisId {
    private int campeonato;
    private int pais;

    public CampeonatoPaisId() {
    }

    public CampeonatoPaisId(int campeonato, int pais) {
        this.campeonato = campeonato;
        this.pais = pais;
    }

    public int getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(int campeonato) {
        this.campeonato = campeonato;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

}

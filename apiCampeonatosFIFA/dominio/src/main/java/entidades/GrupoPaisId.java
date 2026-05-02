package campeonatosfifa.api.dominio.entidades;

public class GrupoPaisId {
    private int grupo;
    private int pais;

    
    public GrupoPaisId() {
    }

    public GrupoPaisId(int grupo, int pais) {
        this.grupo = grupo;
        this.pais = pais;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

}

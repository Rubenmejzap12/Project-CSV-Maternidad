package rubenmejias.csvproject;

public class MostrarDatos {
    private String pais;
    private String Codpais;
    private String Anno;
    private String muertesMaternidad;

    public MostrarDatos() {
    }

    
    
    public MostrarDatos(String pais, String Codpais, String Anno, String muertesMaternidad) {
        this.pais = pais;
        this.Codpais = Codpais;
        this.Anno = Anno;
        this.muertesMaternidad = muertesMaternidad;
    }

    
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodpais() {
        return Codpais;
    }

    public void setCodpais(String Codpais) {
        this.Codpais = Codpais;
    }

    public String getAnno() {
        return Anno;
    }

    public void setAnno(String Anno) {
        this.Anno = Anno;
    }

    public String getMuertesMaternidad() {
        return muertesMaternidad;
    }

    public void setMuertesMaternidad(String suicidios) {
        this.muertesMaternidad = suicidios;
    }
    @Override
    public String toString(){
        String r = "";
        r += "Pais: " + pais + "\n";
        return r;
    }
    public String AnnoToString(){
       String r = "Años: " + Anno;
       return r;
    }
    
}

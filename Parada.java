public class Parada {
    private String parada;
    private String linea;
    private String zona;

    public Parada(String parada, String linea, String zona) {
        this.parada = parada;
        this.zona = zona;
        this.linea = linea;
    }

    public String getParada() {
        return parada;
    }

    public void setParada(String parada) {
        this.parada = parada;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Parada: " + parada+ " Linea: " + linea + " Zona:" + zona;

    }
}

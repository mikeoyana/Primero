public class Partido {
    private int nJornada;
    private String fecha;
    private String local;
    private int golesLocal;
    private int golesVisitante;
    private String visitante;


    public Partido(int njornada, String fecha, String local,int golesLocal, int golesVisitante, String visitante){
        this.local = local;
        this.visitante = visitante;
        this.nJornada = njornada;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int getnJornada() {
        return nJornada;
    }

    public String getFecha() {
        return fecha;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public String getLocal() {
        return local;
    }


    public String getVisitante() {
        return visitante;
    }

    public String toStringQuiniela(){
        return (this.golesLocal>this.golesVisitante ? "1" : this.golesLocal<this.golesVisitante ? "2" : "x");
    }
    @Override
    public String toString() {
        return "Jornada:"+getnJornada()+" Local: "+getLocal()+" Visitante: "+getVisitante();
    }
}

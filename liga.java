import java.io.File;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        File f = new File("2019_2020.csv");
        ArrayList<Partido> liga = new ArrayList<Partido>();
        Map<String,Integer> puntos = new HashMap<String,Integer>();
        Map<String,Integer> golesF = new HashMap<String,Integer>();
        Map<String,Integer> golesC = new HashMap<String,Integer>();

        try {

            Scanner lectorCSV = new Scanner(f).useDelimiter(",|\n");
            lectorCSV.nextLine();


            while(lectorCSV.hasNext()){
                int jornada = lectorCSV.nextInt();
                String fecha = lectorCSV.next();
                String local = lectorCSV.next();
                int golesLocal = lectorCSV.nextInt();
                int golesVisitante = lectorCSV.nextInt();
                String visitante = lectorCSV.next().replaceAll("\r","");

                if(!puntos.containsKey(local)){
                    puntos.put(local,0);
                    golesF.put(local,0);
                    golesC.put(local,0);
                }

                if(!puntos.containsKey(visitante)){
                    puntos.put(visitante,0);
                    golesF.put(visitante,0);
                    golesC.put(visitante,0);
                }

                golesF.put(local,golesF.get(local)+golesLocal);
                golesF.put(visitante,golesF.get(visitante)+golesVisitante);
                golesC.put(local,golesC.get(local)+golesVisitante);
                golesC.put(visitante,golesC.get(visitante)+golesLocal);

                if(golesLocal>golesVisitante){
                    puntos.put(local,puntos.get(local) + 3);
                } else if (golesLocal<golesVisitante){
                    puntos.put(visitante,puntos.get(visitante)+3);
                } else {
                    puntos.put(local,puntos.get(local)+1);
                    puntos.put(visitante,puntos.get(visitante)+1);
                }

                liga.add(new Partido(jornada,fecha,local,golesLocal,golesVisitante,visitante));

            }

            mostrarClasifiacion(puntos,golesF,golesC);


            int victoriaLocal = 0;
            int victoriaVisitante = 0;
            int empate = 0;

            for (Partido partido : liga) {

                if(partido.toStringQuiniela().equalsIgnoreCase("1")){
                    victoriaLocal++;
                } else if(partido.toStringQuiniela().equalsIgnoreCase("2")){
                    victoriaVisitante++;
                } else {
                    empate++;
                }
            }

            Partido masGoles = masGoles(liga);
            Partido masDiferencia = masDiferencia(liga);

            System.out.println("El partido con más goles ha sido: "+masGoles);
            System.out.println("Con un total de "+( masGoles.getGolesVisitante()+masGoles.getGolesLocal())+" goles.");

            System.out.println("El partido ganado por un mayor margen ha sido: "+masDiferencia);
            System.out.println("Con una diferencia de "+(Math.abs(masDiferencia.getGolesLocal()-masDiferencia.getGolesVisitante()))+" goles.");


            System.out.println("Victorias local: "+victoriaLocal+" Victorias visitante: "+victoriaVisitante+" Empates: "+empate);

        } catch(Exception e){

            System.out.println("Ha ocurrido un error");
            e.printStackTrace();
        }
    }


    public static void mostrarClasifiacion(Map<String, Integer> puntos, Map<String, Integer> golesF, Map<String, Integer> golesC){
        while(!puntos.isEmpty()){
            int maxPuntos = 0;
            int maxgA = 0;
            int maxgF = 0;
            String equipoMax = "";

            Iterator<String> iterador = puntos.keySet().iterator();

            while(iterador.hasNext()){
                String equipo = iterador.next();

                if (puntos.get(equipo) > maxPuntos ||
                        puntos.get(equipo) == maxPuntos && golesF.get(equipo)-golesC.get(equipo) > maxgA ||
                        puntos.get(equipo) == maxPuntos && golesF.get(equipo)-golesC.get(equipo) == maxgA && golesF.get(equipo) > maxgF ){
                    maxPuntos = puntos.get(equipo);
                    maxgA = golesF.get(equipo) - golesC.get(equipo);
                    maxgF = golesF.get(equipo);
                    equipoMax = equipo;
                }
            }

            System.out.println(equipoMax+" "+puntos.get(equipoMax)+"pts "+golesF.get(equipoMax)+"gF "+golesC.get(equipoMax)+"gC");
            puntos.remove(equipoMax);
            golesF.remove(equipoMax);
            golesC.remove(equipoMax);
        }
    }

    public static Partido masGoles(ArrayList<Partido> liga){
        int sumaGoles = 0;
        Partido masGoles = null;
        for(Partido p: liga){
            if(p.getGolesLocal()+p.getGolesVisitante()>sumaGoles){
                sumaGoles = p.getGolesLocal()+p.getGolesVisitante();
                masGoles = p;
            }
        }

        return masGoles;
    }

    public static Partido masDiferencia(ArrayList<Partido> liga){
        int maxDiferencia = 0;
        Partido masDiferencia = null;

        for(Partido p: liga){
            if(Math.abs(p.getGolesLocal()-p.getGolesVisitante()) > maxDiferencia){
                maxDiferencia = Math.abs(p.getGolesLocal()-p.getGolesVisitante());
                masDiferencia = p;
            }
        }
        return masDiferencia;
    }

}

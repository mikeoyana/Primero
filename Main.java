import java.io.File;
import java.sql.SQLOutput;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<Parada> metroMadrid = new ArrayList<>();
        HashMap<String,Float> precios = new HashMap<>();
        Scanner sc;
        try{
             sc = new Scanner(new File("paradas.csv"),"utf-8").useDelimiter(",|\n");

            sc.nextLine();
            while(sc.hasNext()){

                String parada = sc.next();
                String linea = sc.next();
                String zona = sc.next().replaceAll("\r","");

                metroMadrid.add(new Parada(parada,linea,zona));

            }
            sc.close();

            sc = new Scanner(new File("precios.txt"));

            while(sc.hasNext()){
                String origen = sc.next();
                String destino = sc.next();
                String origenDestino = origen+"-"+destino;
                float precio = sc.nextFloat();


                precios.put(origenDestino,precio);

            }
            sc.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        Parada minNombre = nombreMascorto(metroMadrid);
        System.out.println("Parada con nombre más corto: "+minNombre.getParada()+" Longitud: "+minNombre.getParada().length());

        Parada maxNombre = nombreMasLargo(metroMadrid);
        System.out.println("Parada con nombre más largo: "+maxNombre.getParada()+" Longitud: "+maxNombre.getParada().length());

         paradaMaxLineas(metroMadrid);
         masMenosParadas(metroMadrid);
         paradasPorLinea(metroMadrid);
         paradasPorZona(metroMadrid);


        System.out.println(precios);

        transbordos(metroMadrid);



    }

    private static Parada  nombreMascorto(ArrayList<Parada> metroMadrid){
        int minNombre = metroMadrid.get(0).getParada().length();
        Parada minParada = null;

        for (Parada p : metroMadrid){
            if (p.getParada().length() < minNombre){
                minNombre = p.getParada().length();
                minParada = p;
            }
        }
        return minParada;
    }

    private static Parada nombreMasLargo(ArrayList<Parada> metroMadrid){
        int maxNombre = 0;
        Parada maxParada = null;

        for (Parada p : metroMadrid){
            if (p.getParada().length() > maxNombre){
                maxNombre = p.getParada().length();
                maxParada = p;
            }
        }
        return maxParada;
    }

    private static void paradaMaxLineas(ArrayList<Parada> metroMadrid){
        int maxLineas = 0;
        Parada paradaMax = null;

        for(Parada p: metroMadrid){
            String nombreParada = p.getParada();
            int contadorLinea = 0; //Estoy dudando si los contadores tienen que empezar en uno pero lo tienes iniciado en 0
            for (Parada p2:metroMadrid){
                if(p2.getParada().equals(nombreParada)){
                    contadorLinea++;
                }
            }
            if (contadorLinea>maxLineas){
                maxLineas = contadorLinea;
                paradaMax = p;
            }
        }

        System.out.println("Parada con más lineas: "+paradaMax.getParada()+" Con "+maxLineas+" lineas");
    }

    private static void masMenosParadas(ArrayList<Parada> metroMadrid){
        int maxParadas = 0;
        int minParadas = 1000;
        Parada paradaMax = null;
        Parada paradaMin = null;

        for(Parada p: metroMadrid){
            String linea = p.getLinea();
            int contadorParadas = 0;
            for (Parada p2:metroMadrid){
                if(p2.getLinea().equals(linea)){
                    contadorParadas++;
                }
            }
            if (contadorParadas>maxParadas){
                maxParadas = contadorParadas;
                paradaMax = p;
            } else if(contadorParadas<minParadas){
                minParadas = contadorParadas;
                paradaMin = p;
            }
        }
        System.out.println("Linea con más paradas: "+paradaMax.getLinea()+" con "+maxParadas+" paradas");
        System.out.println("Linea con menos paradas: "+paradaMin.getLinea()+" con "+minParadas+" paradas");
    }

    private static void paradasPorLinea(ArrayList<Parada> metroMadrid){
        HashMap<String,Integer> lineas = new HashMap<>();

        for (Parada p: metroMadrid){
            if(!lineas.containsKey(p.getLinea())){
                lineas.put(p.getLinea(),0);
            }

            lineas.put(p.getLinea(), lineas.get(p.getLinea())+1);
        }

        String [] claves = lineas.keySet().toArray(new String[0]);


        for (String clave : claves) {
            System.out.println("Linea: " + clave + " con " + lineas.get(clave) + " paradas");
        }
    }

    private static void paradasPorZona(ArrayList<Parada> metroMadrid){
        HashMap<String, String> zonas = new HashMap<>();

        for(Parada p: metroMadrid){

            if(!zonas.containsKey(p.getParada())){
                zonas.put(p.getParada(),p.getZona());
            }

        }

        for(Parada p : metroMadrid) {
            int contadorParadas = 0;
            String zona = zonas.get(p.getParada());
            String[] claves = zonas.keySet().toArray(new String[0]);

            for (String clave : claves) {
                if (zona!=null && zona.equals(zonas.get(clave))) {
                    contadorParadas++;

                    zonas.remove(clave);
                }
            }
            if(zona != null) {
                System.out.println("Zona " + zona + " con " + contadorParadas + " paradas");
            }

        }

    }

    private static void transbordos(ArrayList<Parada> metroMadrid){
        String [][] trayectos = {
                {"Oporto", "Urgel"},
                {"Oporto", "Sol"},
                {"Jarama", "Pradillo"},
                {"Cantabria", "Barajas"},
                {"Abrantes", "Pradillo"},
                {"Cantabria", "Alsacia"}
        };


        for (int i = 0; i < trayectos.length; i++) {
            int contadorTransbordos = 0;
           HashSet<String> lineasOrigen = new HashSet<>();
           HashSet<String> paradasOrigen = new HashSet<>();

           for (Parada p: metroMadrid){
               if (p.getParada().equals(trayectos[i][0])){
                   lineasOrigen.add(p.getLinea());
               }


           }




        }
    }


}

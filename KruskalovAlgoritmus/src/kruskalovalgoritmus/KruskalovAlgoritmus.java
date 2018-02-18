package kruskalovalgoritmus;

import java.io.IOException;

public class KruskalovAlgoritmus {

    public static void main(String[] args) throws IOException {
        NacitavacZoSuboru nacitavac = new NacitavacZoSuboru();

        int[] vrcholy = null;
        int[][] hrany = null;
        try {
            vrcholy = nacitavac.nacitajVrcholy("graf1-vrcholy");
            hrany = nacitavac.nacitajHrany("graf1-hrany");
        } catch (SuborNeexistujeException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        
        Graf graf = new Graf(vrcholy, hrany);
        
        graf.vypisVrcholy();
        
        graf.vypisHrany();
        
        graf.vypisHranyNajlacnejsejKostry();
    }

}

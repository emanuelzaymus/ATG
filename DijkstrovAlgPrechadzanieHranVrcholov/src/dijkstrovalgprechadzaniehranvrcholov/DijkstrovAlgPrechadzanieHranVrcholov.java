package dijkstrovalgprechadzaniehranvrcholov;

import java.io.IOException;

public class DijkstrovAlgPrechadzanieHranVrcholov {

    private static int[] vrcholy;
    private static int[][] hrany;

    public static void main(String[] args) throws IOException {

        NacitavacZoSuboru nacitavac = new NacitavacZoSuboru();

        try {
            vrcholy = nacitavac.nacitajVrcholy("graf1-vrcholy");
            hrany = nacitavac.nacitajHrany("graf1-hrany");          
        } catch (SuborNeexistujeException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        Graf graf = new Graf(vrcholy, hrany);

        graf.vypisVrcholy();

        graf.vypisNajkratsiuCestuPodlaDA(6, 5);
    }

}

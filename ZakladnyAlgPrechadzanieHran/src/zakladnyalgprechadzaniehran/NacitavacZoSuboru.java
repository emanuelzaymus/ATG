package zakladnyalgprechadzaniehran;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class NacitavacZoSuboru {

    int[] nacitajVrcholy(String nazovSuboru) throws IOException, SuborNeexitujeExceprion {
        ArrayList<Integer> cisla = this.citajZoSuboru(nazovSuboru);

        int[] ret = new int[cisla.size()];
        for (int i = 0; i < cisla.size(); i++) {
            ret[i] = cisla.get(i);
        }
        return ret;
    }

    int[][] nacitajHrany(String nazovSuboru) throws IOException, SuborNeexitujeExceprion {
        ArrayList<Integer> cisla = this.citajZoSuboru(nazovSuboru);

        int[][] ret = new int[cisla.size() / 3][3];
        for (int i = 0; i < cisla.size(); i++) {
            ret[i / 3][i % 3] = cisla.get(i);
        }
        return ret;
    }

    private ArrayList<Integer> citajZoSuboru(String nazovSuboru) throws IOException, SuborNeexitujeExceprion {
        File subor = new File(nazovSuboru + ".txt");
        if (!subor.exists()) {
            throw new SuborNeexitujeExceprion(String.format("Subor %s.txt sa nenasiel", nazovSuboru));
        }

        ArrayList<Integer> cisla = new ArrayList<>();
        Scanner sc = new Scanner(subor);
        while (sc.hasNextInt()) {
            cisla.add(sc.nextInt());
        }
        sc.close();

        return cisla;
    }

}

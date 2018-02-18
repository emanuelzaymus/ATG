
import java.util.ArrayList;

public class ZistenieSuvislostiGrafu {

    private static int[][] matica;

    public static void main(String[] args) {
        matica = new int[][]{
            {},
            {0, 0, 5, 0, 0},
            {0, 5, 0, 5, 0},
            {0, 0, 6, 0, 5},
            {0, 0, 0, 8, 0}};

        vypisMaticu(matica);

        if (jeMaticaSuvislimGrafom(matica)) {
            System.out.println("Graf JE suvisli.");
        } else {
            System.out.println("Graf NIEJE suvisli.");
        }

    }

    private static boolean jeMaticaSuvislimGrafom(int[][] matica) {
        int[][] zjednodusenaMatica = zjednodusMaticuNa0a1(matica);

        ArrayList<int[][]> umocneneMatice = new ArrayList<>();

        for (int i = 2; i <= zjednodusenaMatica.length - 2; i++) {
            int[][] umocnenaMatica = umocniMaticu(zjednodusenaMatica, i);
            umocneneMatice.add(umocnenaMatica);
        }

        int[][] vyslednaMatica = zjednodusenaMatica;
        for (int[][] aktualna : umocneneMatice) {
            for (int i = 1; i < vyslednaMatica.length; i++) {
                for (int j = 1; j < vyslednaMatica[i].length; j++) {
                    vyslednaMatica[i][j] += aktualna[i][j];
                }
            }
        }

        for (int i = 1; i < vyslednaMatica.length; i++) {
            for (int j = 1; j < vyslednaMatica[i].length; j++) {
                if (vyslednaMatica[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void vypisMaticu(int[][] matica) {
        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                System.out.print(String.format("%3d", matica[i][j]));
            }
            System.out.println("");
        }
    }

    private static int[][] umocniMaticu(int[][] matica, int exponent) {
        int[][] vynasobenaMatica = matica;

        for (int j = 0; j < exponent - 1; j++) {
            vynasobenaMatica = vynasobMatice(vynasobenaMatica, matica);
        }
        return vynasobenaMatica;
    }

    private static int[][] vynasobMatice(int[][] matica1, int[][] matica2) {
        int[][] ret = new int[matica1.length][matica2[1].length];

        for (int i = 1; i < matica1.length; i++) {
            for (int j = 1; j < matica2[i].length; j++) {
                for (int k = 1; k < matica2.length; k++) {
                    ret[i][j] += matica1[i][k] * matica2[k][j];
                }
            }
        }
        return ret;
    }

    private static int[][] zjednodusMaticuNa0a1(int[][] matica) {
        int[][] ret = new int[matica.length][matica[1].length];

        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                if (matica[i][j] != 0) {
                    ret[i][j] = 1;
                }
            }
        }
        return ret;
    }

}

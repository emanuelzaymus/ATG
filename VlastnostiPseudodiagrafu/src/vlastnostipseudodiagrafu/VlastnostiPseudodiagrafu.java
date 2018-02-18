package vlastnostipseudodiagrafu;

public class VlastnostiPseudodiagrafu {

    private static int[][] matica;

    public static void main(String[] args) {
        matica = new int[][]{
            {},
            {0, 0, 10, 0, 1},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0}};

        vypisMaticu(matica);

        if (vhodnaMatica(matica)) {
            vypisVlastnostiMatice(matica);
        } else {
            System.out.println("Graf nie je vhodny.");
        }

    }

    private static void vypisVlastnostiMatice(int[][] matica) {
        System.out.println("Graf je:");
        if (jeReflexivna(matica)) {
            System.out.println("- reflexivny");
        }
        if (jeSymetricka(matica)) {
            System.out.println("- symetricky");
        }
        if (jeAntisymetricka(matica)) {
            System.out.println("- antisymetricky");
        }
        if (jeTranzitivna(matica)) {
            System.out.println("- tranzitivny");
        }
    }

    private static void vypisMaticu(int[][] matica) {
        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                System.out.print(String.format("%3d", matica[i][j]));
            }
            System.out.println("");
        }
    }

    private static boolean vhodnaMatica(int[][] matica) {
        int pocetRiadkov = matica.length;
        for (int i = 1; i < pocetRiadkov; i++) {
            if (matica[i].length != pocetRiadkov) {
                return false;
            }
        }

        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                if (matica[i][j] >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean jeReflexivna(int[][] matica) {
        for (int i = 1; i < matica.length; i++) {
            if (matica[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean jeSymetricka(int[][] matica) {
        for (int i = 2; i < matica.length; i++) {
            for (int j = 1; j < i; j++) {
                if (matica[j][i] != matica[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean jeAntisymetricka(int[][] matica) {
        for (int i = 2; i < matica.length; i++) {
            for (int j = 1; j < i; j++) {
                if (matica[j][i] + matica[i][j] >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean jeTranzitivna(int[][] matica) {
        int[][] maticaNaDruhu = new int[matica.length][matica[1].length];

        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                for (int k = 1; k < matica.length; k++) {
                    maticaNaDruhu[i][j] += matica[i][k] * matica[k][j];
                }
            }
        }

        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                if (maticaNaDruhu[i][j] != 0 && matica[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}

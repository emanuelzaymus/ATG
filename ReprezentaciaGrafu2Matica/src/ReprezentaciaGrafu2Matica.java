
public class ReprezentaciaGrafu2Matica {

    private static int[][] matica;

    public static void main(String[] args) {

        matica = new int[][]{
            {},
            {0, 0, 3, 7, 0, 0},
            {0, 5, 0, 0, 0, 0},
            {0, 0, 1, 0, 9, 2},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}};

        vypisMaticu(matica);
        vypisHrany(matica);

        pridajVrchol();
        pridajHranu(6, 5, 10);

        vypisMaticu(matica);
        vypisHrany(matica);
    }

    private static void vypisMaticu(int[][] matica) {
        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                System.out.print(String.format("%3d", matica[i][j]));
            }
            System.out.println("");
        }
    }

    private static void vypisHrany(int[][] matica) {
        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                if (matica[i][j] != 0) {
                    System.out.println(i + " --- " + j + "  (" + matica[i][j] + ")");
                }
            }
        }
    }

    private static void pridajHranu(int zaciatok, int konice, int hodnota) {
        matica[zaciatok][konice] = hodnota;
    }

    private static void pridajVrchol() {
        int[][] novaMatica = new int[matica.length + 1][matica.length + 1];
        for (int i = 1; i < matica.length; i++) {
            for (int j = 1; j < matica[i].length; j++) {
                novaMatica[i][j] = matica[i][j];
            }
        }
        matica = novaMatica;
    }

}

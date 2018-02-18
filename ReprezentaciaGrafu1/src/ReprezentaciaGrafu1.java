
public class ReprezentaciaGrafu1 {

    private static int[] vrcholy;
    private static int[][] hrany;

    public static void main(String[] args) {
        vrcholy = new int[]{0, 1, 2, 3, 4, 5};   //5 prvkov

        hrany = new int[][]{{1, 3, 4}, {2, 3, 9}, {2, 5, 7}, {3, 4, 1}, {1, 2, 5}};

        vypisHrayObojstranne(hrany);

    }

    private static int[][] usporiadajHrany(int[][] hrany) {
        int p0;
        int p1;
        int p2;
        boolean bolaVymena;
        do {
            bolaVymena = false;

            for (int i = 0; i < hrany.length - 1; i++) {
                if (hrany[i][0] > hrany[i + 1][0] || hrany[i][0] == hrany[i + 1][0] && hrany[i][1] > hrany[i + 1][1]) {
                    p0 = hrany[i][0];
                    p1 = hrany[i][1];
                    p2 = hrany[i][2];

                    hrany[i][0] = hrany[i + 1][0];
                    hrany[i][1] = hrany[i + 1][1];
                    hrany[i][2] = hrany[i + 1][2];

                    hrany[i + 1][0] = p0;
                    hrany[i + 1][1] = p1;
                    hrany[i + 1][2] = p2;

                    bolaVymena = true;
                }
            }
        } while (bolaVymena);
        return hrany;
    }

    private static void vypisHrany(int[][] hrany) {
        for (int i = 0; i < hrany.length; i++) {
            System.out.println(hrany[i][0] + " --- " + hrany[i][1] + "  (" + hrany[i][2] + ")");
        }
    }

    private static void vypisHrayObojstranne(int[][] hrany) {
        int[][] obojstranneHrany = new int[hrany.length * 2][3];

        int j = 0;
        for (int i = 0; i < hrany.length; i++) {
            obojstranneHrany[j][0] = hrany[i][0];
            obojstranneHrany[j][1] = hrany[i][1];
            obojstranneHrany[j][2] = hrany[i][2];
            j++;
            obojstranneHrany[j][0] = hrany[i][1];
            obojstranneHrany[j][1] = hrany[i][0];
            obojstranneHrany[j][2] = hrany[i][2];
            j++;
        }

        obojstranneHrany = usporiadajHrany(obojstranneHrany);

        vypisHrany(obojstranneHrany);
    }

}

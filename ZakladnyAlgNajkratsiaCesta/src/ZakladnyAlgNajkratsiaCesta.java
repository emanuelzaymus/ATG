
public class ZakladnyAlgNajkratsiaCesta {

    private static Graf graf;

    public static void main(String[] args) {
//        int[] vrcholy = new int[]{
//            1, 2, 3, 4
//        };
//        int[][] hrany = new int[][]{
//            {1, 2, 10}, {2, 3, 20}, {3, 1, 40}, {1, 4, 20}  //sketchy
//        };

//        int[] vrcholy = new int[]{
//            1, 2, 3, 4, 0
//        };
//        int[][] hrany = new int[][]{
//            {0, 1, 20}, {0, 2, 50}, {1, 2, 20}, {2, 3, 10}, {4, 1, 30}, {3, 4, 20}
//        };
//
        int[] vrcholy = new int[]{
            1, 2, 3, 4, 5, 6
        };
        int[][] hrany = new int[][]{
            {1, 3, 30}, {2, 4, 30}, {3, 2, 10}, {3, 5, 60}, {4, 3, 80}, {4, 6, 20}, {5, 1, 30}, {5, 2, 90}, {5, 6, 150}
        };

        graf = new Graf(vrcholy, hrany);

        graf.vypisVrcholy();

        graf.vypisNajkratsiuCestu(5, 6);

    }

}

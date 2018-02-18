
public class TarryhoAlgoritmus {

    private static Graf graf;

    public static void main(String[] args) {
//        int[] vrcholy = new int[]{
//            1, 2, 3, 4
//        };
//        int[][] hrany = new int[][]{
//            {1, 2}, {2, 3}, {3, 1}, {1, 4}  //sketchy
//        };

        int[] vrcholy = new int[]{
            1, 2, 3, 4, 0
        };
        int[][] hrany = new int[][]{
            {0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}, {4, 1}
        };

        graf = new Graf(vrcholy, hrany);

        graf.vypisVrcholy();

        graf.vypisTarryhoSled(0);

    }

}

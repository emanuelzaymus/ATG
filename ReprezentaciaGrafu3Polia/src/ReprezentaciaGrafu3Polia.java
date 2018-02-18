
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class ReprezentaciaGrafu3Polia {

    private static ArrayList<ArrayList<Integer>> polia;

    public static void main(String[] args) {
        polia = new ArrayList<>();

        polia.add(new ArrayList<>(asList(2, 3)));
        polia.add(new ArrayList<>(asList(1, 3, 5)));
        polia.add(new ArrayList<>(asList(2, 1, 4)));
        polia.add(new ArrayList<>(asList(3)));
        polia.add(new ArrayList<>(asList(2)));

        vypisPole(polia);
        vypisHrany(polia);

        pridajVrchol(new ArrayList<Integer>(asList(1, 1, 2, 2)));
        pridajHranu(3, 5);

        vypisPole(polia);
        vypisHrany(polia);

    }

    private static void vypisPole(ArrayList<ArrayList<Integer>> polia) {
        int i = 1;
        for (ArrayList<Integer> arrayList : polia) {
            System.out.print("V(" + i + ")  ");
            for (Integer integer : arrayList) {
                System.out.print(integer + "  ");
            }
            System.out.println("");
            i++;
        }
        System.out.println("");
    }

    private static void vypisHrany(ArrayList<ArrayList<Integer>> polia) {
        int i = 1;
        for (ArrayList<Integer> arrayList : polia) {
            for (Integer integer : arrayList) {
                System.out.println(i + " --- " + integer);
            }
            i++;
        }
        System.out.println("");
    }

    private static void pridajHranu(int zaciatok, int koniec) {
        polia.get(zaciatok).add(koniec);
    }

    private static void pridajVrchol(ArrayList<Integer> arrayList) {
        polia.add(arrayList);
    }

}

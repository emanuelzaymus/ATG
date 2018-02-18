
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashMap;

public class TarryhoAlgJednoduchsie {

    private static ArrayList<ArrayList<Integer>> graf;

    public static void main(String[] args) {
        graf = new ArrayList<>();

        graf.add(new ArrayList<>(asList(1, 2)));      // 0
        graf.add(new ArrayList<>(asList(0, 2, 4)));   // 1
        graf.add(new ArrayList<>(asList(0, 1, 3)));   // 2
        graf.add(new ArrayList<>(asList(2, 4)));      // 3
        graf.add(new ArrayList<>(asList(1, 3)));      // 4
//
//        graf.add(new ArrayList<>(asList(4, 5)));         // 0  // 7
//        graf.add(new ArrayList<>(asList(2, 3)));         // 1
//        graf.add(new ArrayList<>(asList(1, 3)));         // 2
//        graf.add(new ArrayList<>(asList(1, 2, 4)));      // 3
//        graf.add(new ArrayList<>(asList(0, 3, 5)));      // 4
//        graf.add(new ArrayList<>(asList(0, 4, 6)));      // 5
//        graf.add(new ArrayList<>(asList(5)));            // 6

        ArrayList<Integer> tarryhoSled = new ArrayList<>();
        
        if (spravnyGraf(graf)) {
            tarryhoSled = spravTarryhoSled(graf, 0);
        } else {
            System.out.println("Chybny graf.");
        }

        vypisTarryhoSled(tarryhoSled);

        if (spravnyGraf(graf)) {
            if (jeGrafSpojity(tarryhoSled)) {
                System.out.println("Graf JE spojity.");
            } else {
                System.out.println("Graf NIE JE spojity.");
            }
        }

    }

    private static ArrayList<Integer> spravTarryhoSled(ArrayList<ArrayList<Integer>> graf, int zaciatocny) {
        ArrayList<Integer> tarryhoSled = new ArrayList<Integer>();
        HashMap<Integer, ArrayList<Integer>> pouziteHrany = new HashMap<>();
        int[] hranyPrvehoPrichodu = new int[graf.size()];

        int aktualnyVrchol = zaciatocny;
        tarryhoSled.add(aktualnyVrchol);
        pouziteHrany.put(aktualnyVrchol, new ArrayList<Integer>());

        int i = 0;

        int vrchol = 0;
        boolean mozeIstHranou1Prichodu = false;
        boolean koniec = false;
        do {
            if (i < graf.get(aktualnyVrchol).size()) {
                vrchol = graf.get(aktualnyVrchol).get(i);
            } else {
                i = 0;
                vrchol = graf.get(aktualnyVrchol).get(0);
                mozeIstHranou1Prichodu = true;
            }

            if (jeNovyVrchol(vrchol, tarryhoSled)) {
                tarryhoSled.add(vrchol);//dup
                pouziteHrany.get(aktualnyVrchol).add(vrchol);//dup
                hranyPrvehoPrichodu[vrchol] = aktualnyVrchol;
                pouziteHrany.put(vrchol, new ArrayList<Integer>());

                aktualnyVrchol = vrchol;//dup
                i = 0;//dup
            } else if (hranyPrvehoPrichodu[aktualnyVrchol] != vrchol && !pouziteHrany.get(aktualnyVrchol).contains(vrchol)
                    || mozeIstHranou1Prichodu && !pouziteHrany.get(aktualnyVrchol).contains(vrchol)) {
                tarryhoSled.add(vrchol);//dup
                pouziteHrany.get(aktualnyVrchol).add(vrchol);//dup

                aktualnyVrchol = vrchol;//dup
                i = 0;//dup

                if (mozeIstHranou1Prichodu && aktualnyVrchol == zaciatocny) {
                    koniec = true;
                }
                mozeIstHranou1Prichodu = false;
            } else {
                i++;
            }
        } while (!koniec);

        return tarryhoSled;
    }

    private static boolean jeNovyVrchol(Integer aktualnyVrchol, ArrayList<Integer> tarryhoSled) {
        for (Integer vrchol : tarryhoSled) {
            if (aktualnyVrchol.equals(vrchol)) {
                return false;
            }
        }
        return true;
    }

    private static void vypisTarryhoSled(ArrayList<Integer> tarryhoSled) {
        if (tarryhoSled == null) {
            System.out.println("Sled neexistuje.");
            return;
        }

        System.out.print("Tarryho sled: (");
        for (int i = 0; i < tarryhoSled.size() - 1; i++) {
            System.out.print(tarryhoSled.get(i) + ", {" + tarryhoSled.get(i) + ", "
                    + tarryhoSled.get(i + 1) + "}, ");
        }
        System.out.println(tarryhoSled.get(tarryhoSled.size() - 1) + ")");

        System.out.print(tarryhoSled.get(0));
        for (int i = 1; i < tarryhoSled.size(); i++) {
            System.out.print(" - " + tarryhoSled.get(i));
        }
        System.out.println("");
    }

    private static boolean spravnyGraf(ArrayList<ArrayList<Integer>> graf) {
        boolean maDvojicu = true;
        for (int vrchol = 0; vrchol < graf.size(); vrchol++) {
            for (Integer porovnavanyPrvok : graf.get(vrchol)) {
                for (Integer prvok : graf.get(porovnavanyPrvok)) {
                    if (prvok == vrchol) {
                        maDvojicu = true;
                        break;
                    } else {
                        maDvojicu = false;
                    }
                }
                if (!maDvojicu) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean jeGrafSpojity(ArrayList<Integer> tarryhoSled) {
        if (tarryhoSled == null) {
            return false;
        }

        ArrayList<Integer> vrcholy = new ArrayList<>();
        for (Integer vrchol : tarryhoSled) {
            if (!vrcholy.contains(vrchol)) {
                vrcholy.add(vrchol);
            }
        }
        if (vrcholy.size() != graf.size()) {
            return false;
        }

        for (int i = 0; i < tarryhoSled.size() - 1; i++) {
            if (!graf.get(tarryhoSled.get(i)).contains(tarryhoSled.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

}

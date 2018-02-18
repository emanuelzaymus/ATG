
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ValencnaPostupnost {

    private static int pocetPrvkov;

    public static void main(String[] args) {
        //ArrayList<Integer> postupnost = nacitaniePostupnosti();
        ArrayList<Integer> postupnost = new ArrayList<>(Arrays.asList(4, 4, 3, 2, 1, 2));
        pocetPrvkov = postupnost.size();

        vypisPostupnost(postupnost);
        if (!jeSpravnyVstup(postupnost)) {
            vypisVysledok(false);
            return;
        }

        vypisVysledok(jeValencnouPostupnostou(postupnost));
    }

    private static boolean jeValencnouPostupnostou(ArrayList<Integer> postupnost) {
        boolean zaporne = false;
        boolean sameNuly = false;

        while (!zaporne && !sameNuly) {

            postupnost = usporiadajZostupne(postupnost);
            vypisPostupnost(postupnost);
            System.out.println();

            Integer pocet = postupnost.get(0);
            postupnost.remove(0);

            for (int i = 0; i < pocet; i++) {
                postupnost.set(i, postupnost.get(i) - 1);
            }

            sameNuly = true;
            for (Integer aktualny : postupnost) {
                if (aktualny < 0) {
                    zaporne = true;
                    break;
                }
                if (aktualny != 0) {
                    sameNuly = false;
                }
            }
            vypisPostupnost(postupnost);
        }
        return !zaporne;
    }

    private static void vypisPostupnost(ArrayList<Integer> postupnost) {
        for (int i = 0; i < pocetPrvkov - postupnost.size(); i++) {
            System.out.print("    ");
        }
        for (Integer prvok : postupnost) {
            System.out.print(String.format("%4s", prvok));
        }
        System.out.println("");
    }

    private static ArrayList<Integer> usporiadajZostupne(ArrayList<Integer> postupnost) {
        Integer pomocna;
        boolean bolaVymena;
        do {
            bolaVymena = false;

            for (int i = 0; i < postupnost.size() - 1; i++) {
                if (postupnost.get(i) < postupnost.get(i + 1)) {
                    pomocna = postupnost.get(i);
                    postupnost.set(i, postupnost.get(i + 1));
                    postupnost.set(i + 1, pomocna);

                    bolaVymena = true;
                }
            }
        } while (bolaVymena);

        return postupnost;
    }

    private static boolean jeSpravnyVstup(ArrayList<Integer> postupnost) {
        int sucet = 0;
        int najvacsiPrvok = 0;
        for (Integer prvok : postupnost) {
            sucet += prvok;
            if (najvacsiPrvok < prvok) {
                najvacsiPrvok = prvok;
            }
        }
        return sucet % 2 == 0 && najvacsiPrvok < postupnost.size();
    }

    private static void vypisVysledok(boolean spravne) {
        if (spravne) {
            System.out.println("\nPostupnost JE valencnou postupnostou grafu\n");
        } else {
            System.out.println("\nPostupnost NIE JE valencnou postupnostou grafu\n");
        }
    }

    private static ArrayList<Integer> nacitaniePostupnosti() {
        System.out.print("Zadaj pocet prvkov postupnosti: ");
        Scanner sc = new Scanner(System.in);
        pocetPrvkov = sc.nextInt();

        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < pocetPrvkov; i++) {
            System.out.print(i + 1 + ". prvok: ");
            ret.add(sc.nextInt());
        }
        System.out.println();
        return ret;
    }
}

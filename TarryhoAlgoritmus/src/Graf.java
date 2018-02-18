
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Graf {

    private final HashMap<Integer, Vrchol> vrcholy;
    private final ArrayList<Hrana> hrany;

    Graf(int[] vrcholy, int[][] hrany) {

        this.vrcholy = new HashMap<>();
        this.hrany = new ArrayList<>();

        for (int i : vrcholy) {
            this.vrcholy.put(i, new Vrchol(i));
        }

        for (int[] hrana : hrany) {
            this.hrany.add(new Hrana(this.vrcholy.get(hrana[0]), this.vrcholy.get(hrana[1])));
            this.hrany.add(new Hrana(this.vrcholy.get(hrana[1]), this.vrcholy.get(hrana[0])));
        }

    }

    void vypisVrcholy() {
        for (Map.Entry<Integer, Vrchol> entry : this.vrcholy.entrySet()) {
            System.out.println("V(" + entry.getKey() + ")   " + entry.getValue().dajIncidentneHrany());
        }
        System.out.println("");
    }

    void vypisTarryhoSled(int vrchol) {
        ArrayList<Vrchol> tarryhoSled = this.spravTarryhoSled(vrchol);

        if (tarryhoSled == null) {
            System.out.println("Graf neobsahuje zaciatocny vrchol.");
            return;
        }
        System.out.println("Tarryho sled:");
        System.out.print(tarryhoSled.get(0).getNazov());
        for (int i = 1; i < tarryhoSled.size(); i++) {
            System.out.print(" - " + tarryhoSled.get(i).getNazov());
        }
        System.out.println("");
    }

    private ArrayList<Vrchol> spravTarryhoSled(int zaciatocnyVrchol) {
        if (!this.vrcholy.containsKey(zaciatocnyVrchol)) {
            return null;
        }
        ArrayList<Vrchol> tarryhoSled = new ArrayList<>();

        Vrchol aktualnyVrchol = this.vrcholy.get(zaciatocnyVrchol);
        tarryhoSled.add(aktualnyVrchol);

        int i = 0;

        Hrana hrana;
        boolean mozeIstHranou1Prichodu = false;
        boolean koniec = false;
        do {
            if (i >= aktualnyVrchol.dajPocetHran()) {
                i = 0;
                mozeIstHranou1Prichodu = true;
            }
            hrana = aktualnyVrchol.dajHranu(i);

            if (!hrana.isPouzita() && !hrana.isHranaPrvehoPrichodu()
                    || !hrana.isPouzita() && mozeIstHranou1Prichodu) {
                aktualnyVrchol = hrana.getKoncovy();
                if (!tarryhoSled.contains(aktualnyVrchol) || aktualnyVrchol.equals(this.vrcholy.get(zaciatocnyVrchol))) {
                    hrana.oznacAkoHranuPrvehoPrichodu();
                }
                tarryhoSled.add(aktualnyVrchol);
                hrana.pouzi();
                i = 0;
                if (mozeIstHranou1Prichodu && aktualnyVrchol == this.vrcholy.get(zaciatocnyVrchol)) {  // TODO vypusta hrany ked je viac hran zo zaciatocneho vrchola
                    koniec = true;
                }
                mozeIstHranou1Prichodu = false;
            } else {
                i++;
            }

        } while (!koniec);

        return tarryhoSled;
    }

}


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
            this.hrany.add(new Hrana(this.vrcholy.get(hrana[0]), this.vrcholy.get(hrana[1]), hrana[2]));
//            this.hrany.add(new Hrana(this.vrcholy.get(hrana[1]), this.vrcholy.get(hrana[0]), hrana[2]));
        }

    }

    void vypisVrcholy() {
        for (Map.Entry<Integer, Vrchol> entry : this.vrcholy.entrySet()) {
            System.out.println("V(" + entry.getKey() + ")   " + entry.getValue().dajIncidentneHrany());
        }
        System.out.println("");
    }

    void vypisNajkratsiuCestu(int start, int ciel) {
        ArrayList<Vrchol> najkratsiaCesta = this.dajNajkratsiuCestu(start, ciel);

        if (najkratsiaCesta == null) {
            System.out.println("Cesta z vrcholu " + start + " do vrcholu " + ciel + " neexistuje.");
            return;
        }

        System.out.println("Najkratsia cesta z vrcholu " + start + " do vrcholu " + ciel + ":");
        System.out.print(najkratsiaCesta.get(najkratsiaCesta.size() - 1).getNazov());
        for (int i = najkratsiaCesta.size() - 2; i >= 0; i--) {
            System.out.print(" - " + najkratsiaCesta.get(i).getNazov());
        }
        System.out.println("");
    }

    private ArrayList<Vrchol> dajNajkratsiuCestu(int start, int ciel) {
        if (!this.vrcholy.containsKey(start) && !this.vrcholy.containsKey(ciel) || this.vrcholy.get(start).dajPocetHran() == 0 || this.vrcholy.get(ciel).dajPocetHran() == 0) {
            return null;
        }
        Vrchol aktualny = this.vrcholy.get(start);
        Vrchol zaciatocny = this.vrcholy.get(start);
        Vrchol vrchol;
        Hrana hrana;
        aktualny.setHornyOdhad(0);

        int i = 0;

        do {
            if (i < aktualny.dajPocetHran()) {
                hrana = aktualny.dajHranu(i);
                vrchol = hrana.getKoncovy();
                if (vrchol.getHornyOdhad() > aktualny.getHornyOdhad() + hrana.getHodnota()) {
                    vrchol.setHornyOdhad(aktualny.getHornyOdhad() + hrana.getHodnota());
                    vrchol.setPredposlednyVrchol(aktualny);
                    aktualny = vrchol;
                    i = 0;
                } else {
                    i++;
                }
            } else {
                aktualny = aktualny.getPredposlednyVrchol();
                i = 0;
            }
        } while (aktualny != null);

        ArrayList<Vrchol> najkratsiaCesta = new ArrayList<>();
        vrchol = this.vrcholy.get(ciel);
        while (zaciatocny != vrchol) {
            najkratsiaCesta.add(vrchol);
            vrchol = vrchol.getPredposlednyVrchol();
        }
        najkratsiaCesta.add(vrchol);

        return najkratsiaCesta;
    }

}

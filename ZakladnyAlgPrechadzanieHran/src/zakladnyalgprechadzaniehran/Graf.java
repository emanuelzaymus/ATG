package zakladnyalgprechadzaniehran;

import java.util.ArrayList;
import java.util.HashMap;

class Graf {

    private final HashMap<Integer, Vrchol> vrcholy;
    private final ArrayList<Hrana> hrany;
    private int sucetHodnotHran;

    Graf(int[] vrcholy, int[][] hrany) {

        this.vrcholy = new HashMap<>();
        this.hrany = new ArrayList<>();

        for (int i : vrcholy) {
            this.vrcholy.put(i, new Vrchol(i));
        }

        this.sucetHodnotHran = 0;
        for (int[] hrana : hrany) {
            this.hrany.add(new Hrana(this.vrcholy.get(hrana[0]), this.vrcholy.get(hrana[1]), hrana[2]));
//            this.hrany.add(new Hrana(this.vrcholy.get(hrana[1]), this.vrcholy.get(hrana[0]), hrana[2]));
            this.sucetHodnotHran += hrana[2];
        }

        for (Vrchol vrchol : this.vrcholy.values()) {
            vrchol.setHornyOdhad(this.sucetHodnotHran++);
        }

    }

    void vypisVrcholy() {
        for (Vrchol v : this.vrcholy.values()) {
            System.out.println(v);
        }
        System.out.println("");
    }

    void vypisNajkratsiuCestu(int start, int ciel) {
        if (start == ciel) {
            System.out.println(start);
            return;
        }
        
        this.vykonajZakladnyAlgoritmus(start);
        
        ArrayList<Vrchol> najrychlejsiaCesta = this.dajNajkratsiuCestu(start, ciel);
        if (najrychlejsiaCesta == null) {
            System.out.format("Cesta z %d do %d neexistuje.%n", start, ciel);
            return;
        }

        System.out.format("%d(/)", najrychlejsiaCesta.get(najrychlejsiaCesta.size() - 1).getNazov());
        Vrchol v;
        for (int j = najrychlejsiaCesta.size() - 2; j >= 0; j--) {
            v = najrychlejsiaCesta.get(j);
            System.out.format(" - %d(%d)", v.getNazov(), v.getHornyOdhad());
        }
        System.out.println("");
    }

    private void vykonajZakladnyAlgoritmus(int start) {
        this.vrcholy.get(start).setHornyOdhad(0);

        boolean bolaZmena;
        Vrchol koncovy;
        Vrchol zaciatocny;
        do {
            bolaZmena = false;
            for (Hrana hrana : this.hrany) {
                koncovy = hrana.getKoncovy();
                zaciatocny = hrana.getZaciatocny();
                if (koncovy.getHornyOdhad() > zaciatocny.getHornyOdhad() + hrana.getHodnota()) {
                    koncovy.setHornyOdhad(zaciatocny.getHornyOdhad() + hrana.getHodnota());
                    koncovy.setPredposlednyVrchol(zaciatocny);
                    bolaZmena = true;
                }
            }
        } while (bolaZmena);
    }

    private ArrayList<Vrchol> dajNajkratsiuCestu(int start, int ciel) {
        ArrayList<Vrchol> najrychlejsiaCesta = new ArrayList<>();
        najrychlejsiaCesta.add(this.vrcholy.get(ciel));

        int i = 0;
        Vrchol startovaci = this.vrcholy.get(start);
        Vrchol naPridanie;
        do {
            naPridanie = najrychlejsiaCesta.get(i).getPredposlednyVrchol();
            if (naPridanie == null) {
                return null;
            }
            najrychlejsiaCesta.add(naPridanie);
            i++;
        } while (naPridanie != startovaci);
        return najrychlejsiaCesta;
    }

}

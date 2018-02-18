package dijkstrovalgprechadzaniehranvrcholov;

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
            this.hrany.add(new Hrana(this.vrcholy.get(hrana[1]), this.vrcholy.get(hrana[0]), hrana[2]));
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

    void vypisNajkratsiuCestuPodlaDA(int start, int ciel) {
        if (start == ciel) {
            System.out.println(start);
            return;
        }

        this.vykonajDijkstrovAlgoritmus(start);

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

    private void vykonajDijkstrovAlgoritmus(int start) {
        this.vrcholy.get(start).setHornyOdhad(0);

        Vrchol vrchol;
        Vrchol koncovy;
        Hrana hrana;
        while (!this.vsetkyVrcholySuOznacene()) {
            vrchol = this.dajVrcholSNajmensimOdhadom();
            vrchol.oznac();
            for (int i = 0; i < vrchol.dajPocetHran(); i++) {
                hrana = vrchol.dajHranu(i);
                koncovy = hrana.getKoncovy();
                if (!koncovy.isOznaceny() && koncovy.getHornyOdhad() > vrchol.getHornyOdhad() + hrana.getHodnota()) {
                    koncovy.setHornyOdhad(vrchol.getHornyOdhad() + hrana.getHodnota());
                    koncovy.setPredposlednyVrchol(vrchol);
                }
            }
        }
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

    private boolean vsetkyVrcholySuOznacene() {
        for (Vrchol v : this.vrcholy.values()) {
            if (!v.isOznaceny()) {
                return false;
            }
        }
        return true;
    }

    private Vrchol dajVrcholSNajmensimOdhadom() {
        Vrchol ret = null;
        int najmensiHornyOdhad = this.sucetHodnotHran++;

        for (Vrchol v : this.vrcholy.values()) {
            if (v.getHornyOdhad() < najmensiHornyOdhad && !v.isOznaceny()) {
                najmensiHornyOdhad = v.getHornyOdhad();
                ret = v;
            }
        }
        return ret;
    }

}

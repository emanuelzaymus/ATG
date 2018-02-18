package dijkstrovalgoritmus;

import java.util.ArrayList;
import java.util.HashMap;

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
        for (Vrchol v : this.vrcholy.values()) {
            System.out.println(v);
        }
    }

    void vypisNajkratsiuCestuPodlaDA(int start, int ciel) {
        ArrayList<Vrchol> najkratsiaCesta = this.dajNajkratsiuCestuPodlaDijkstrovhoAlg(start, ciel);

        if (najkratsiaCesta == null) {
            System.out.println("Cesta z vrcholu " + start + " do vrcholu " + ciel + " neexistuje.");
            return;
        }

        System.out.println("Podla Dijkstrovho algoritmu je najkratsia cesta z vrcholu " + start + " do vrcholu " + ciel + ":");
        System.out.print(najkratsiaCesta.get(najkratsiaCesta.size() - 1).getNazov());
        for (int i = najkratsiaCesta.size() - 2; i >= 0; i--) {
            System.out.print(" - " + najkratsiaCesta.get(i).getNazov());
        }
        System.out.println("");
    }

    private ArrayList<Vrchol> dajNajkratsiuCestuPodlaDijkstrovhoAlg(int start, int ciel) {
        if (!this.vrcholy.containsKey(start) && !this.vrcholy.containsKey(ciel) || this.vrcholy.get(start).dajPocetHran() == 0) {
            return null;
        }
        Vrchol aktualny = this.vrcholy.get(start);
        Vrchol vrchol;
        Hrana hrana;

        aktualny.setHornyOdhad(0);
        aktualny.navstiv();

        int i = 0;
        int najkratsiaDlzkaHrany = Integer.MAX_VALUE;
        Vrchol najblizsiVrchol = null;
        boolean koniec = false;

        do {
            if (i < aktualny.dajPocetHran()) {
                hrana = aktualny.dajHranu(i);
                vrchol = hrana.getKoncovy();
                if (hrana.getHodnota() < najkratsiaDlzkaHrany && !vrchol.isNavstiveny()) {
                    najkratsiaDlzkaHrany = hrana.getHodnota();
                    najblizsiVrchol = vrchol;
                }
                if (vrchol.getHornyOdhad() > aktualny.getHornyOdhad() + hrana.getHodnota()) {
                    vrchol.setHornyOdhad(aktualny.getHornyOdhad() + hrana.getHodnota());
                    vrchol.setPredposlednyVrchol(aktualny);
                }
                i++;
            } else {
                if (aktualny != najblizsiVrchol) {
                    aktualny = najblizsiVrchol; // moze byt null
                } else {
                    vrchol = this.dajNenavstivenyVrcholSNajmensimOdhadom();
                    if (vrchol != null) {
                        aktualny = vrchol; // moze byt null
                    } else {
                        koniec = true;
                    }
                }
                aktualny.navstiv();
                najkratsiaDlzkaHrany = Integer.MAX_VALUE;
                i = 0;
            }
        } while (!koniec);

        return this.zostavNajkratsiuCestu(start, ciel);
    }

    private Vrchol dajNenavstivenyVrcholSNajmensimOdhadom() {
        int najmensiHornyOdhad = Integer.MAX_VALUE;
        Vrchol vrchol = null;
        for (Vrchol v : this.vrcholy.values()) {
            if (!v.isNavstiveny()) {
                if (v.getHornyOdhad() < najmensiHornyOdhad) {
                    najmensiHornyOdhad = v.getHornyOdhad();
                    vrchol = v;
                }
            }
        }
        return vrchol;
    }

    private ArrayList<Vrchol> zostavNajkratsiuCestu(int start, int ciel) {
        ArrayList<Vrchol> najkratsiaCesta = new ArrayList<>();
        Vrchol zaciatocny = this.vrcholy.get(start);
        Vrchol vrchol = this.vrcholy.get(ciel);
        if (vrchol.getPredposlednyVrchol() == null) {
            return null;
        }
        
        while (zaciatocny != vrchol) {
            najkratsiaCesta.add(vrchol);
            vrchol = vrchol.getPredposlednyVrchol();
        }
        najkratsiaCesta.add(vrchol);
        return najkratsiaCesta;
    }

}

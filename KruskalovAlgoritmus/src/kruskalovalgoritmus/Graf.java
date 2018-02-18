package kruskalovalgoritmus;

import java.util.ArrayList;
import java.util.Collection;
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

    }

    void vypisVrcholy() {
        for (Vrchol v : this.vrcholy.values()) {
            System.out.println(v);
        }
        System.out.println("");
    }

    void vypisHranyNajlacnejsejKostry() {
        if (!this.jeSpojity()) {
            System.out.println("\nGraf nie je spojity.");
            return;
        }

        ArrayList<Hrana> najlacnejsiaKostra = this.getNajlacnejsiaKostra();
        System.out.println("\nHrany najlacnejsej kostry:");

        for (Hrana hrana : najlacnejsiaKostra) {
            System.out.format("%d - %d (%d)%n", hrana.getZaciatocny().getNazov(), hrana.getKoncovy().getNazov(), hrana.getHodnota());
        }
    }

    private void zoradHranyVzostupne() {
        boolean bolaZmena;
        Hrana pomocna;
        do {
            bolaZmena = false;
            for (int i = 0; i < this.hrany.size() - 1; i++) {
                if (this.hrany.get(i).getHodnota() > this.hrany.get(i + 1).getHodnota()) {
                    pomocna = this.hrany.get(i);
                    this.hrany.set(i, this.hrany.get(i + 1));
                    this.hrany.set(i + 1, pomocna);
                    bolaZmena = true;
                }
            }
        } while (bolaZmena);

    }

    private ArrayList<Hrana> getNajlacnejsiaKostra() {
        this.zoradHranyVzostupne();
        ArrayList<Hrana> ret = new ArrayList<>();

        int i = 0;
        Hrana h;
        do {
            h = this.hrany.get(i);
            int znackaZaciatocneho = h.getZaciatocny().getZnacka();
            int znackaKoncoveho = h.getKoncovy().getZnacka();
            if (znackaZaciatocneho != znackaKoncoveho) {
                this.zmenZnacky(znackaZaciatocneho, znackaKoncoveho);
                ret.add(h);
            }
            i++;
        } while (ret.size() != this.vrcholy.size() - 1);

        return ret;
    }

    private void zmenZnacky(int z, int na) {
        Collection<Vrchol> v = this.vrcholy.values();
        for (Vrchol vrchol : v) {
            if (vrchol.getZnacka() == z) {
                vrchol.setZnacka(na);
            }
        }
    }

    void vypisHrany() {
        for (Hrana hrana : this.hrany) {
            System.out.format("%d - %d (%d)%n", hrana.getZaciatocny().getNazov(), hrana.getKoncovy().getNazov(), hrana.getHodnota());
        }
    }

    private boolean jeSpojity() {
        HashMap<Integer, Vrchol> vr = (HashMap<Integer, Vrchol>) this.vrcholy.clone();
        Collection<Vrchol> v = vr.values();

        for (Hrana hrana : this.hrany) {
            if (v.contains(hrana.getZaciatocny())) {
                v.remove(hrana.getZaciatocny());
            }
            if (v.contains(hrana.getKoncovy())) {
                v.remove(hrana.getKoncovy());
            }
        }

        return v.isEmpty();
    }

}

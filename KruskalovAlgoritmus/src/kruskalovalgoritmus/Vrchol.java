package kruskalovalgoritmus;

import java.util.ArrayList;

class Vrchol {

    private final int nazov;
    private ArrayList<Hrana> hrany;

    private int znacka;

    Vrchol(int nazov) {
        this.nazov = nazov;
        this.hrany = new ArrayList<>();

        this.znacka = nazov;
    }

    public int getNazov() {
        return this.nazov;
    }

    void pridajHranu(Hrana hrana) {
        this.hrany.add(hrana);
    }

    public int getZnacka() {
        return this.znacka;
    }

    public void setZnacka(int znacka) {
        this.znacka = this.znacka;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder(String.format("V(%d)   ", this.nazov));
        for (Hrana hrana : this.hrany) {
            ret.append(String.format("%d (%d)  ", hrana.getKoncovy().getNazov(), hrana.getHodnota()));
        }
        return ret.toString();
    }

}

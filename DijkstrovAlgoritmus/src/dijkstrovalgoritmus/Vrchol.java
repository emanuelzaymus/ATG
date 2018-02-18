package dijkstrovalgoritmus;

import java.util.ArrayList;

class Vrchol {

    private final int nazov;
    private ArrayList<Hrana> hrany;

    private int hornyOdhad;
    private Vrchol predposlednyVrchol;

    private boolean navstiveny;

    Vrchol(int nazov) {
        this.nazov = nazov;
        this.hrany = new ArrayList<>();

        this.hornyOdhad = Integer.MAX_VALUE;
        this.predposlednyVrchol = null;

        this.navstiveny = false;
    }

    public int getNazov() {
        return this.nazov;
    }

    void pridajHranu(Hrana hrana) {
        this.hrany.add(hrana);
    }

    public Hrana dajHranu(int i) {
        return this.hrany.get(i);
    }

    public int dajPocetHran() {
        return this.hrany.size();
    }

    public int getHornyOdhad() {
        return this.hornyOdhad;
    }

    public Vrchol getPredposlednyVrchol() {
        return this.predposlednyVrchol;
    }

    public void setHornyOdhad(int hornyOdhad) {
        this.hornyOdhad = hornyOdhad;
    }

    public void setPredposlednyVrchol(Vrchol predposlednyVrchol) {
        this.predposlednyVrchol = predposlednyVrchol;
    }

    public String toString() {
        String ret = "V(" + this.nazov + ")" + "   ";
        for (Hrana hrana : this.hrany) {
            ret += hrana.getKoncovy().getNazov() + " (" + hrana.getHodnota() + ")  ";
        }
        return ret;
    }

//    Hrana dajHranuSmerujucuDo(Vrchol cielovy) {
//        for (Hrana hrana : this.hrany) {
//            if (hrana.getKoncovy().equals(cielovy)) {
//                return hrana;
//            }
//        }
//        return null;
//    }

    public boolean isNavstiveny() {
        return this.navstiveny;
    }

    public void navstiv() {
        this.navstiveny = true;
    }
}

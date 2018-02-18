
import java.util.ArrayList;

class Vrchol {

    private final int nazov;
    private ArrayList<Hrana> hrany;

    private int hornyOdhad;
    private Vrchol predposlednyVrchol;

    Vrchol(int nazov) {
        this.nazov = nazov;
        this.hrany = new ArrayList<>();

        this.hornyOdhad = Integer.MAX_VALUE;
        this.predposlednyVrchol = null;
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

    String dajIncidentneHrany() {
        String ret = "";
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

}

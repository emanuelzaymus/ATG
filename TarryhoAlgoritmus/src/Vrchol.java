
import java.util.ArrayList;

class Vrchol {

    private final int nazov;
    private ArrayList<Hrana> hrany;

    Vrchol(int nazov) {
        this.nazov = nazov;
        this.hrany = new ArrayList<>();
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

    String dajIncidentneHrany() {
        String ret = "";
        for (Hrana hrana : this.hrany) {
            ret += hrana.getKoncovy().getNazov() + "  ";
        }
        return ret;
    }

    Hrana dajHranuSmerujucuDo(Vrchol cielovy) {
        for (Hrana hrana : this.hrany) {
            if (hrana.getKoncovy().equals(cielovy)) {
                return hrana;
            }
        }
        return null;
    }

}

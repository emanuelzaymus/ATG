
class Hrana {

    private final Vrchol zaciatocny;
    private final Vrchol koncovy;
//    private int hodnota;
    private boolean pouzita;
    private boolean hranaPrvehoPrichodu;

    Hrana(Vrchol zaciatocny, Vrchol koncovy) {
        this.zaciatocny = zaciatocny;
        this.koncovy = koncovy;
//        this.hodnota = 1;
        this.pouzita = false;
        this.hranaPrvehoPrichodu = false;

        this.zaciatocny.pridajHranu(this);
    }

//    public Vrchol getZaciatocny() {
//        return this.zaciatocny;
//    }
//
    public Vrchol getKoncovy() {
        return this.koncovy;
    }

    public boolean isPouzita() {
        return this.pouzita;
    }

    void pouzi() {
        this.pouzita = true;
    }

    public boolean isHranaPrvehoPrichodu() {
        return this.hranaPrvehoPrichodu;
    }

    void oznacAkoHranuPrvehoPrichodu() {
        this.hranaPrvehoPrichodu = true;
        Hrana h = this.koncovy.dajHranuSmerujucuDo(this.zaciatocny);
        h.hranaPrvehoPrichodu = true;
    }

}

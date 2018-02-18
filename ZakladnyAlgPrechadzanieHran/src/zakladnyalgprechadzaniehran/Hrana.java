package zakladnyalgprechadzaniehran;

class Hrana {

    private final Vrchol zaciatocny;
    private final Vrchol koncovy;
    private int hodnota;

    Hrana(Vrchol zaciatocny, Vrchol koncovy, int hodnota) {
        this.zaciatocny = zaciatocny;
        this.koncovy = koncovy;
        this.hodnota = hodnota;

        this.zaciatocny.pridajHranu(this);
    }

    public Vrchol getZaciatocny() {
        return this.zaciatocny;
    }

    public int getHodnota() {
        return this.hodnota;
    }

    public Vrchol getKoncovy() {
        return this.koncovy;
    }

}

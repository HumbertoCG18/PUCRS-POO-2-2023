package T3_SCT_2023;
abstract class CarroFerroviario {
    private int id;
    private Trem trem;

    public CarroFerroviario(int id, Trem trem) {
        this.id = id;
        this.trem = trem;
    }

    public int getId() {
        return id;
    }

    public Trem getTrem() {
        return trem;
    }

    @Override
    public String toString() {
        return "id=" + id + ", trem=" + trem;
    }

    @Override
    public boolean equals(Object outro) {
        if (!(outro instanceof CarroFerroviario)) {
            return false;
        }
        CarroFerroviario cf = (CarroFerroviario) outro;
        return this.id == cf.getId();
    }
}
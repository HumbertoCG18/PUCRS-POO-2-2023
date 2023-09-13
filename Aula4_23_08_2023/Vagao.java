public abstract class Vagao extends CarroFerroviario {
    public Vagao(int id, Trem trem) {
        super(id, trem);
    }

    @Override
    public String toString() {
        return "Vagao: " + super.toString();
    }
}

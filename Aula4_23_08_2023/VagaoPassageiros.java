public class VagaoPassageiros extends Vagao {
    private int nroAssentos;

    public VagaoPassageiros(int id, Trem trem,int nroAssentos) {
        super(id, trem);
        this.nroAssentos = nroAssentos;
    }

    public int getNroAssentos() {
        return nroAssentos;
    }

    @Override
    public String toString(){
        return super.toString()+", nro de assentos: "+this.getNroAssentos();
    }

}

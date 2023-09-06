public class VagaoCargaRefrigerado extends VagaoCarga{
    private int temperaturaMinima;

    public VagaoCargaRefrigerado(int id, Trem trem, int capacidadeCarga, int temperaturaMinima) {
        super(id, trem, capacidadeCarga);
        this.temperaturaMinima = temperaturaMinima;
    }

    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }
    
    @Override
    public String toString(){
        return super.toString()+", temperatura minuma: "+this.getTemperaturaMinima();
    }
    
}

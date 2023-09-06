public class VagaoCarga extends CarroFerroviario{

    public VagaoCarga(int id,Trem trem,int capacidadeCarga){
        super(id,trem,capacidadeCarga);
    }

    @Override
    public String toString(){
        return "Vagao carga: "+super.toString();
    }
    
}

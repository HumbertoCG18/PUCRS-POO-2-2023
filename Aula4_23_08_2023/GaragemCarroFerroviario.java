import java.util.ArrayList;

public class GaragemCarroFerroviario {
    private ArrayList<CarroFerroviario> carros;
    
    public GaragemCarroFerroviario(){
        carros = new ArrayList<>();
    }

    public void estaciona(CarroFerroviario cv){
        carros.add(cv);
    }

    public int qtdade(){
        return carros.size();
    }

    public CarroFerroviario get(int i){
        return carros.get(i);
    }

    public int qtdadeVagaoPassageiro(){
        int c = 0;
        for(CarroFerroviario cf:carros){
            if (cf instanceof VagaoPassageiros){
                c++;
            }
        }
        return c;
    }

    public Locomotiva retornaLocomotivaLivre(){
        Locomotiva aux = null;
        for(CarroFerroviario cf:carros){
            if (cf instanceof Locomotiva){
                aux = (Locomotiva)cf;
                carros.remove(cf);
                break;
            }
        }
        return aux;
    }
}

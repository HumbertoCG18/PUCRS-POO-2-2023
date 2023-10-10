package T3_SCT_2023;
import java.util.ArrayList;

public class GaragemCarroFerroviario {
    private ArrayList<CarroFerroviario> carros;

    public GaragemCarroFerroviario() {
        carros = new ArrayList<>();
    }

    public void estaciona(CarroFerroviario cv) {
        carros.add(cv);
    }

    public int qtdade() {
        return carros.size();
    }

    public CarroFerroviario get(int i) {
        return carros.get(i);
    }

    public CarroFerroviario retira(int id) {
        for (CarroFerroviario cf : carros) {
            if (cf.getId() == id) {
                carros.remove(cf);
                return cf;
            }
        }
        return null;
    }
}

package Aula4_04_09_2023;
import java.util.ArrayList;

public class Trem {
    private ArrayList<CarroFerroviario> carros;
    private int identificador;

    public Trem(int identificador) {
        this.identificador = identificador;
        carros = new ArrayList<>();
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getQtdadeCarros() {
        return carros.size();
    }

    public CarroFerroviario getCarro(int id) {
        for (CarroFerroviario cf : carros) {
            if (cf.getId() == id) {
                return cf;
            }
        }
        return null;
    }

    public ArrayList<Locomotiva> getTodasLocomotivas() {
        ArrayList<Locomotiva> lstAux = new ArrayList<>();
        for (CarroFerroviario cf : carros) {
            if (cf instanceof Locomotiva) {
                lstAux.add((Locomotiva) cf);
            }
        }
        return lstAux;
    }

    public ArrayList<Vagao> getTodosVagoes() {
        ArrayList<Vagao> lstAux = new ArrayList<>();
        for (CarroFerroviario cf : carros) {
            if (cf instanceof Vagao) {
                lstAux.add((Vagao) cf);
            }
        }
        return lstAux;
    }

    // Métodos para engatar e desengatar locomotivas e vagões podem ser adicionados aqui
    // Exemplo:
    // public boolean engataLocomotiva(Locomotiva locomotiva) {
    //     return false;
    // }

    // public boolean engataVagao(Vagao vagao) {
    //     return false;
    // }

    // public Locomotiva desengataLocomotiva() {
    //     return null;
    // }

    // public Vagao desengataVagao() {
    //     return null;
    // }
}
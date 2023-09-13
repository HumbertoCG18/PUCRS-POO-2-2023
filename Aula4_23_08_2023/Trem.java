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
		CarroFerroviario aux = null;
		for (CarroFerroviario cf : carros) {
			if (cf.getId() == id) {
				return cf;
			}
		}
		return aux;
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

	private int maxVagoesNaComposicao() {
		int qtdadeMaxVagoes = 0;
		int qtdadeLocomotivas = 0;
		for (CarroFerroviario cf : carros) {
			if (cf instanceof Locomotiva) {
				Locomotiva lAux = (Locomotiva) cf;
				qtdadeMaxVagoes += lAux.getNumeroMaximoVagoes();
				qtdadeLocomotivas++;
			}
		}
		return (int) (qtdadeMaxVagoes * (1 - (0.1 * qtdadeLocomotivas)));
	}

	private double pesoMaxNaComposicao() {
		int pesoMaximo = 0;
		int qtdadeLocomotivas = 0;
		for (CarroFerroviario cf : carros) {
			if (cf instanceof Locomotiva) {
				Locomotiva lAux = (Locomotiva) cf;
				pesoMaximo += lAux.getCapacidadeCarga();
				qtdadeLocomotivas++;
			}
		}
		return (int) (pesoMaximo * (1 - (0.1 * qtdadeLocomotivas)));
	}

	private double pesoAtualDaComposicao() {
		double peso = 0.0;
		for (Vagao v : getTodosVagoes()) {
			if (v instanceof VagaoCarga) {
				peso += ((VagaoCarga) v).getCapacidadeCarga();
			}
			if (v instanceof VagaoPassageiros) {
				peso += ((VagaoPassageiros) v).getNroAssentos() * 120;
			}
		}
		return peso;
	}

	/* Uma alternativa aos metodos abaixo seria ter apenas um método engata
	   e outro desengata recebendo CarroFerroviario por parametro. Neste caso,
	   porem, seria necessario testar se é um vagao ou uma locomotiva que 
	   estao sendo engatados
    */
	public boolean engataLocomotiva(Locomotiva locomotiva) {
		return false;
	}

	public boolean engataVagao(Vagao vagao) {
		return false;
	}

	public Locomotiva desengataLocomotiva() {
		return null;
	}

	public Vagao desengataVagao() {
		return null;
	}
}

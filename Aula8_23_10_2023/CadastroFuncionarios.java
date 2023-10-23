import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CadastroFuncionarios {
	public static final int TAM = 10;
	private List<Funcionario> lstf;

	public CadastroFuncionarios() {
		lstf = new ArrayList<>(TAM);

		Random r = new Random();

		for (int i = 0; i < TAM; i++) {
			int matricula = r.nextInt(300) + 100;
			String nome = "Fulano" + i;
			boolean insalubridade = r.nextBoolean();
			int nroDep = r.nextInt(3);
			double salBase = (r.nextDouble() * 15000) + 500;
			Funcionario f = new Funcionario(matricula, nome, salBase, nroDep, insalubridade);
			lstf.add(f);
		}

		lstf.add(new Funcionario(180,"Zezinho Especial",5000,3,true));
	}

	// 1a: aplicar uma operação sobre todos os Funcionarios da lista
	public void forEach(Consumer<Funcionario> oper) {
		for (Funcionario f:lstf){
			oper.accept(f);
		}
	}

	// 1b
	public long quantidadeFuncionariosCondicao(Predicate<Funcionario> condicao){
		long quantidade = 0L;

		for(Funcionario f: lstf){
			if(condicao.test(f)){
				quantidade ++;
			}
		}
		return quantidade;

	}

	//1c
	public double somatorioCondicao(Predicate<Funcionario> condicao, Function<Funcionario,Double> operacao){
		double sum = 0.0;

		for(Funcionario f: lstf){
			if(condicao.test(f)){
				sum += operacao.apply(f);
			}
		}
		return sum;

	}

	//1d
	public void alteraCondicao(Predicate <Funcionario> condicao, Consumer <Funcionario> metodo){
		for (Funcionario f: lstf){
			if(condicao.test(f)){
				metodo.accept(f);
			}
		}
	}

	//1e
	public List<String> getCamposCondicao(Predicate<Funcionario> condicao,Function<Funcionario,String> oper){
		List<String> resp = new LinkedList<>();
		for(Funcionario f:lstf){
			if (condicao.test(f)){
				String aux = oper.apply(f);
				resp.add(aux);
			}
		}
		return resp;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		lstf.forEach(f->str.append(f.toString()+"\n"));
		return str.toString();
	}
}

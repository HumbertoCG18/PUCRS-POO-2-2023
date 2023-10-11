import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CadastroFuncionarios {
	public static final int TAM = 5;
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

		lstf.add(new Funcionario(180,"Zezinho Especial",5000,3,false));
	}

	// 1a: aplicar uma operação sobre todos os Funcionarios da lista
	public void forEach(Consumer<Funcionario> oper) {
		for(Funcionario func:lstf){
			oper.accept(func);
		}
	}

	// 1b
	public long quantidadeFuncionariosCondicao(Predicate<Funcionario> condicao){
		long cont = 0L;
		for(Funcionario func:lstf){
			if (condicao.test(func)){
				cont++;
			}
		}
		return cont;
	}

	//1c
	public double somatorioCondicao(/* ??? */){
		return 0.0;
	}

	//1d
	public void alteraCondicao(/* ??? */){

	}

	//1e
	public List<String> getCamposCondicao(/* ??? */){
		return new LinkedList<>();
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		lstf.forEach(f->str.append(f.toString()+"\n"));
		return str.toString();
	}
}

package Aula6_16_10_2023.BaseLambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

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

    // 1a: Aplicar uma operação sobre todos os Funcionários da lista
    public void forEach(Consumer<Funcionario> operacao) {
        lstf.forEach(operacao);
    }

    // 1b: Retornar a quantidade de funcionários que atendem uma condição
    public long quantidadeFuncionariosCondicao(Predicate<Funcionario> condicao) {
        return lstf.stream().filter(condicao).count();
    }

    // 1c: Retornar o somatório de um valor de todos os funcionários que atendam uma condição
    public double somatorioCondicao(Predicate<Funcionario> condicao, ToDoubleFunction<Funcionario> extrator) {
        return lstf.stream().filter(condicao).mapToDouble(extrator).sum();
    }

    // 1d: Aplicar uma atualização em todos os funcionários que atendam uma condição
    public void alteraCondicao(Predicate<Funcionario> condicao, Consumer<Funcionario> operacao) {
        lstf.stream().filter(condicao).forEach(operacao);
    }

    // 1e: Retornar uma lista com os dados que interessem de todos os funcionários que atendem uma condição
    public List<String> getCamposCondicao(Predicate<Funcionario> condicao, Function<Funcionario, String> extrator) {
        return lstf.stream()
            .filter(condicao)
            .map(extrator)
            .collect(Collectors.toList());
    }

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		lstf.forEach(f->str.append(f.toString()+"\n"));
		return str.toString();
	}
}

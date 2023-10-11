package Aula6_16_10_2023.BaseAgregrados;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
// import java.util.stream.Collectors;

public class CadastroFuncionarios {
	public static final int TAM = 5;
	private List<Funcionario> lstf;

	public CadastroFuncionarios() {
		lstf = new LinkedList<>();

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

    public List<Funcionario> getFuncionarios() {
        return new ArrayList<>(lstf);
    }

    // 1a
    public List<Funcionario> getInsalubridadeDependentes() {
        List<Funcionario> result = new ArrayList<>();
        for (Funcionario f : lstf) {
            if (f.getInsalubridade() && f.getNroDependentes() > 0) {
                result.add(f);
            }
        }
        return result;
    }

    // 1b
    public long quantidadeFuncionariosComDependentes() {
        long count = 0;
        for (Funcionario f : lstf) {
            if (f.getNroDependentes() > 0) {
                count++;
            }
        }
        return count;
    }

    // 1c
    public double somatorioSalarioBruto() {
        double soma = 0;
        for (Funcionario f : lstf) {
            if (f.getSalarioBase() > 5000) {
                soma += f.getSalarioBruto();
            }
        }
        return soma;
    }

    // 1d
    public void aumentaSalarioInsalubres() {
        for (Funcionario f : lstf) {
            if (f.getInsalubridade()) {
                f.aumentaSalBase(1.2); // Aumento de 20%
            }
        }
    }

    // 1e
    public List<String> getNomeMatriculaSalarioBrutoMaiorQueBase() {
        List<String> result = new ArrayList<>();
        for (Funcionario f : lstf) {
            if (f.getSalarioBruto() > 1.1 * f.getSalarioBase()) {
                result.add(f.getNome() + " - Matrícula: " + f.getMatricula());
            }
        }
        return result;
    }

    // 1f
    public double mediaSalarialDosQueNaoTemInsalubridade() {
        double soma = 0;
        int count = 0;
        for (Funcionario f : lstf) {
            if (!f.getInsalubridade()) {
                soma += f.getSalarioBase();
                count++;
            }
        }
        if (count > 0) {
            return soma / count;
        } else {
            return 0;
        }
    }

    // 1g
    public List<String> nomesDosQueTemMatriculaMenorQue500() {
        List<String> result = new ArrayList<>();
        for (Funcionario f : lstf) {
            if (f.getMatricula() < 500) {
                String[] partesNome = f.getNome().split(" ");
                result.add(partesNome[0]);
            }
        }
        return result;
    }

    // 1h
    public double getSalarioLiquido(int matricula) {
        for (Funcionario f : lstf) {
            if (f.getMatricula() == matricula) {
                return f.getSalarioLiquido();
            }
        }
        return -1;
    }
	
	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		lstf.forEach(f->str.append(f.toString()+"\n"));
		return str.toString();
	}
}

package Aula6_10_11_2023.BaseLambdas;

import java.util.List;

/*
! 1)	Exercícios sobre funcoes lambda
ToDo: a.	Implementar o método void foreach(Consumer<Funcionario) e usar ele para imprimir a lista de funcionarios
ToDo: b.	Retornar a quantidade de funcionários que atendem uma condição.
ToDo: c.	Retornar o somatório de um valor de todos os funcionários que atendam uma condição.
ToDo: d.	Aplicar uma atualização em todos os funcionários que atendam uma condição.
ToDo: e.	Retornar uma lista com os dados que interessem de todos os funcionários que atendem uma condição.
*/
public class App {
    public static void main(String args[]) {
        CadastroFuncionarios cf = new CadastroFuncionarios();

        System.out.println("\nLista original:");
        cf.forEach(f -> System.out.println(f.getNome()));

        System.out.println("\nQuantidade de funcionários com dependentes:" +
                cf.quantidadeFuncionariosCondicao(f -> f.getNroDependentes() > 0));

        System.out.println("\nSomatório dos salários brutos: " +
                cf.somatorioCondicao(f -> f.getSalarioBruto() > 0, Funcionario::getSalarioBruto));
        
        // Exemplo de aplicação de aumento de 20% para insalubres
        cf.alteraCondicao(f -> f.getInsalubridade(), f -> f.aumentaSalBase(1.2));

        System.out.println("\nNome e matrícula dos que recebem mais de 2500:");
        List<String> dadosInteressantes = cf.getCamposCondicao(f -> f.getSalarioBruto() > 2500,
                f -> f.getNome() + " - Matrícula: " + f.getMatricula());
        dadosInteressantes.forEach(System.out::println);
    }
}

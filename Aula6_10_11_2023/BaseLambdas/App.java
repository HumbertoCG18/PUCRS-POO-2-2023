/*
1)	Exercícios sobre funcoes lambda
a.	Implementar o método void foreach(Consumer<Funcionario) e usar ele para imprimir a lista de funcionarios
b.	Retornar a quantidade de funcionários que atendem uma condição.
c.	Retornar o somatório de um valor de todos os funcionários que atendam uma condição.
d.	Aplicar uma atualização em todos os funcionários que atendam uma condição.
e.	Retornar uma lista com os dados que interessem de todos os funcionários que atendem uma condição.
*/
public class App {
  public static void main(String args[]) {
    CadastroFuncionarios cf = new CadastroFuncionarios();

    System.out.println("\nLista original:");
    cf.forEach(f -> System.out.println(f.getNome()));

    System.out.println("\nQuantidade de funcionarios com dependentes:" +
      cf.quantidadeFuncionariosCondicao(f->f.getNroDependentes() > 0));

    System.out.println("\nSomatorio dos salarios brutos:");
    
    System.out.println("\nCorrige salario dos insalubres em 20%:");

    System.out.println("\nNome e matricula dos que recebem mais de 2500 :");
  }
}
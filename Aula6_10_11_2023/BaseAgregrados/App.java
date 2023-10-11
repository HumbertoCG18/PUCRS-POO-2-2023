/*
1)	Altere a classe CadastroFuncionarios de maneira que a mesma disponha de métodos para:
a.	Retornar uma lista com todos os funcionários que recebem insalubridade e tem dependentes.
b.	Retornar a quantidade de funcionários que tem dependentes.
c.	Retornar o somatório do valor do salário bruto de todos os funcionários que ganham mais de 5000.
d.	Aplicar uma taxa de aumento (ex: 1.2 para 20%) em todos os funcionários que recebem insalubridade.
e.	Retornar uma lista de strings com nome e a matricula de todos os funcionários cujo salário bruto é mais de 10% maior que o salário base.
f.	Retornar a média salarial dos funcionários que não recebem insalubridade.
g.	Retornar a lista dos primeiros nomes dos funcionários cujos números de matrícula são menores que 500.
h.	Retornar o salário líquido de um funcionário específico ou -1 se o funcionário não for encontrado.
 
2)	Escreva uma aplicação que cria uma instância de cadastro de funcionários e imprime todas as consultas do exercício 1.

*/
public class App {
    public static void main(String args[]){
        CadastroFuncionarios cf = new CadastroFuncionarios();
   
        System.out.println("\nLista original:");
        cf.getFuncionarios().forEach(f->System.out.println(f));

        System.out.println("\nLista dos que tem insalubridade e dependentes:");
        cf.getInsalubridadeDependentes().forEach(f->System.out.println(f));

        System.out.println("\nQuantidade de funcionarios com dependentes:" +
                           cf.quantidadeFuncionariosComDependentes());
  
        System.out.println("\nSomatorio dos salarios brutos:" + cf.somatorioSalarioBruto());

        System.out.println("\nCorrige salario dos insalubres em 20%:");
        cf.aumentaSalarioInsalubres();
        cf.getFuncionarios().forEach(f->System.out.println(f.getSalarioBase()));

        System.out.println("\nNome e matricula dos que tem o salario bruto maior que 10% do base :");
        cf.getNomeMatriculaSalarioBrutoMaiorQueBase().forEach(f->System.out.println(f));

        System.out.println("\nMedia salarial dos que não recebem insalubridade: "+cf.mediaSalarialDosQueNaoTemInsalubridade());

        System.out.println("\nNomes dos que tem numero de matricula < 500: ");
        cf.nomesDosQueTemMatriculaMenorQue500().forEach(System.out::println);

        System.out.println("\nSalario liquido do funcionario de codigo 180: "+cf.getSalarioLiquido(180));
        System.out.println("\nSalario liquido do funcionario de codigo 30: "+cf.getSalarioLiquido(30));
      }
}
import java.util.List;
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
    cf.forEach(f -> System.out.println(f));

    System.out.println("\nQuantidade de funcionarios com dependentes:" +
    cf.quantidadeFuncionariosCondicao(f->f.getNroDependentes() > 0));
      //cf.quantidadeFuncionariosCondicao(x->x.getInsalubridade()));

    System.out.print("\nSomatorio dos salarios brutos maiores que 5000: ");
    double somat = cf.somatorioCondicao(f->f.getSalarioBruto()>5000, f->f.getSalarioBruto());
    System.out.println(somat);
    
    System.out.println("\nCorrige salario dos insalubres em 20%:");
    cf.alteraCondicao(f->f.getInsalubridade(), f->f.aumentaSalBase(1.2));

    System.out.println("\nNome e matricula dos que recebem mais de 2500 :");
    List<String> resp = cf.getCamposCondicao(f->f.getSalarioLiquido()>2500, f->f.getNome()+":"+f.getMatricula());
    System.out.println(resp);
    System.out.println();
    System.out.println("Lista final: ");
    cf.forEach(f -> System.out.println(f));
  }
}
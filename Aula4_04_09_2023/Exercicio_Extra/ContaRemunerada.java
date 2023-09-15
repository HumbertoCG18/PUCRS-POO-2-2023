package Aula4_04_09_2023.Exercicio_Extra;


public class ContaRemunerada extends ContaCorrente{
    private double taxaRemuneracao;

    public ContaRemunerada(String id, double taxa){
        super(id);
        this.taxaRemuneracao = taxa;
    }

    @Override
    public boolean deposito(double valor){
        valor = valor * taxaRemuneracao;
        return super.deposito(valor);
    }
}
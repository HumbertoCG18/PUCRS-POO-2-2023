package Aula4_04_09_2023.Exercicio_Extra;

public class ContaTotDep extends ContaCorrente{
    private double totalDep;

    public ContaTotDep(String id){
        super(id);
        totalDep = 0;
    }

    public double getTotDep(){
        return totalDep;
    }

    @Override
    public boolean deposito(double valor){
        if (super.deposito(valor)){
            totalDep += valor;
            return true;
        }else{
            return false;
        }
    }
}
package Aula5_04_09_2023;


public class ContaLimite extends ContaCorrente{
    private static final double taxaJuros = 0.03;
    private double limite;
    private double jurosAcumulado;

    public ContaLimite(String id,double limite){
        super(id);
        this.limite = limite;
        deposito(limite);
        jurosAcumulado = 0.0;
    }

    public double getLimite(){
        return limite;
    }

    public double getJurosAcumulado(){
        return jurosAcumulado;
    }

    @Override
    public double getSaldo(){
        return super.getSaldo() - this.getLimite();
    }

    public boolean quitaJuros(){
        if (getSaldo() - jurosAcumulado < limite){
            return false;
        }else{
            retirada(jurosAcumulado);
            jurosAcumulado = 0.0;
            return true;
        }
    }

    @Override
    public boolean retirada(double valor){
        if (getSaldo()-valor > 0 && getSaldo() - valor < limite){
            double juros = Math.abs(getSaldo()-valor-limite)*taxaJuros;
            jurosAcumulado += juros;
        }
        return super.retirada(valor);
    }
}

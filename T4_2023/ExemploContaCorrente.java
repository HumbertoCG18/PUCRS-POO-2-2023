package T4_2023;

public class ExemploContaCorrente {
    public static void main(String[] args) {
        try {
            ContaCorrente Conta;
            Conta = new ContaCorrente(-100); 
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        ContaCorrente conta = null;
        try {
            conta = new ContaCorrente(1000);
            conta.deposito(-200); 
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }

        try {
            conta.retirada(1500); 
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}


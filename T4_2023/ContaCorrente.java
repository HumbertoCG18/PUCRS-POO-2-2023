package T4_2023;

public class ContaCorrente {
    private double saldo;

    public ContaCorrente(double saldoInicial) throws SaldoInsuficienteException {
        if (saldoInicial < 0) {
            throw new SaldoInsuficienteException("Saldo inicial não pode ser negativo");
        }
        this.saldo = saldoInicial;
    }

    public void deposito(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor do depósito deve ser maior que zero");
        }
        this.saldo += valor;
    }

    public void retirada(double valor) throws ValorInvalidoException, SaldoInsuficienteException {
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor da retirada deve ser maior que zero");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para efetuar a retirada");
        }
        this.saldo -= valor;
    }

    public double getSaldo() {
        return this.saldo;
    }
}


package Aula6_16_10_2023;

class Produto<T>{
    private String nome;
    private T preco;
    
    public Produto(String nome, T preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public T getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", preco=" + preco + "]";// + preco.getClass().getName();
    }
}
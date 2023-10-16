package Aula5_11_10_2023.CaixaSupermercado;

public class Produto implements Comparable<Produto>{
    private int codigo;
    private String descricao;
    private double preco;
    
    public Produto(int cod,String descr,double pr){
        codigo = cod;
        descricao = descr;
        preco = pr;
    }
    
    public int getCodigo(){
        return(codigo);
    }
    
    public String getDescricao(){
        return(descricao);
    }
    
    public double getPreco(){
       return(preco);
    }
    
    @Override
    public int compareTo(Produto outro){
        return(this.getCodigo() - outro.getCodigo());
    }
    
    @Override
    public String toString(){
        return(getCodigo()+":"+getDescricao()+":R$ "+getPreco());
    }
}


package Aula5_11_10_2023.CaixaSupermercado;

import java.util.*;

public class ReciboCompra implements Iterable<Produto>{
    private List<Produto> itens;
    
    public ReciboCompra(){
        itens = new LinkedList<>();
    }
    
    public void add(Produto p){
        itens.add(p);
    }
    
    @Override
    public Iterator<Produto> iterator(){
        return(itens.iterator());
    }

    public double getTotal(){
        double total = 0;
        for(Produto p:itens){
            total += p.getPreco();
        }
        return total;
    }
    
    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder();
       for(Produto prod:itens){
           sb.append(prod).append("\n");
       }
       sb.append("Total: R$ "+getTotal());
       return(sb.toString());
    }
}

package Aula5_11_10_2023.CaixaSupermercado;

public class App{
    public static void main(String args[]){
        CadProdutos cp = new CadProdutos();
        
        // Carregar os produtos em memoria
        cp.add(new Produto(100,"Banana",2.5));
        cp.add(new Produto(35,"Laranja",3.8));
        cp.add(new Produto(212,"Melancia",1.9));
        cp.add(new Produto(75,"Uva",3.2));
        cp.add(new Produto(82,"Abacaxi",3.3));
        cp.add(new Produto(22,"Bergamota",0.8));
        
        // Simula uma compra
        ReciboCompra rc = new ReciboCompra();
        Produto p1 = cp.getProduto(35);
        rc.add(p1);
        Produto p2 = cp.getProduto(100);
        rc.add(p2);
        rc.add(cp.getProduto(82));
        rc.add(cp.getProduto(75));
        
        // Imprime a nota
        System.out.println(rc);
    }
}

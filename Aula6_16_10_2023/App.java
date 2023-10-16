package Aula6_16_10_2023;

public class App {
    public static void main(String[] args) throws Exception {
        /* 
        Produto<Integer> p1 = new Produto<>("Geladeira",2000);
        Produto<Double> p2 = new Produto<>("Fogao", 1380.7);

        System.out.println(p1);
        System.out.println(p2);
        */
        MinhaLista<Integer> l1 = new MinhaLista<>();
        l1.add(30);
        l1.add(15);
        l1.add(5);
        l1.add(18);
        l1.add(22);
        System.out.println(l1);

        int soma = 0;
        /* 
        Iterator<Integer> it = l1.getIterator();
        while(it.hasNext()){
            soma += it.next();
        }
        */
        for(Integer i:l1){
            soma += i;
        }
        System.out.println("Somatorio: "+soma);

        MinhaLista<Produto<Integer>> l2 = new MinhaLista<>();
        l2.add(new Produto<Integer>("Geladeira",2000));
        l2.add(new Produto<Integer>("Fogao",1000));
        System.out.println(l2);

    }
}

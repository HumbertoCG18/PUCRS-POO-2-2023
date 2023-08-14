package Aula2_14082023;

public class App{
    public static void main(String args[]) throws Exception{
        
        Carta C1 = new Carta(Naipe.OUROS, Valor.DOIS);
        Carta C2 = new Carta(Naipe.ESPADAS, Valor.DAMA);

        Deck d = new Deck();
        d.insere(C1);
        d.insere(C2);

        Carta aux = d.removeCarta();

        System.out.println(C1.toString());
    }
}
package Aula2_14082023;

public class Deck {
    public final int TAMANHO = 104;
    private Carta[] cartas;
    private int proxLivre;

    public int qtdadeDeCartas(){
        return proxLivre;
    }

    public Deck(){
        cartas = new Carta[TAMANHO];
        proxLivre = 0;
    }

    public boolean insere (Carta carta){
        if(proxLivre < TAMANHO -1){
        cartas[proxLivre] = carta;
        proxLivre++;
        return true;
        }
        return false;
    
    }

    public Carta removeCarta(){
        if (proxLivre == 0){
            return null;
        }
        Carta deCima = cartas[0];
        for (int i=0;i<proxLivre-1;i++){
            cartas[i] = cartas[i+1];
        }
        proxLivre--;
        return deCima;
    }

        
}

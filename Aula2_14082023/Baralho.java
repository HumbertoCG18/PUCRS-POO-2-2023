public class Baralho {
    private Deck cartas;

    public Baralho(){
        cartas = new Deck();
        for(Naipe naipe:Naipe.values()){
            for(Valor valor:Valor.values()){
                Carta carta = new Carta(naipe,valor);
                cartas.insereEmbaixo(carta);
            }
        }
    }

    public int qtdadeCartas(){
        return cartas.qtdadeCartas();
    }

    public boolean vazio(){
        return cartas.vazio();
    }

    public Carta retiraDeCima(){
        return cartas.retiraDeCima();
    }

    public void embaralha(){
        cartas.embaralha();
    }
}

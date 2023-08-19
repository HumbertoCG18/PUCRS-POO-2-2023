package Aula2_14082023;

public class App {
    public static void main(String[] args) throws Exception {
        // Cria o baralho e embaralha
        Baralho baralho = new Baralho();
        baralho.embaralha();
        // Cria os decks dos jogadores
        Deck jogador1 = new Deck();
        Deck jogador2 = new Deck();
        // Distribui todas as cartas entre os dois jogadore
        while(!baralho.vazio()){
            jogador1.insereEmbaixo(baralho.retiraDeCima());
            jogador2.insereEmbaixo(baralho.retiraDeCima());
        }
        // Loop do jogo
        boolean acabou = false;
        int rodada = 1;
        while(!acabou){
            // Pega uma carta de cada jogador
            Carta cj1 = jogador1.retiraDeCima();
            Carta cj2 = jogador2.retiraDeCima();
            System.out.println("Rodada: "+rodada);
            System.out.println("Carta do jogador 1: "+cj1.toString());
            System.out.println("Carta do jogador 2: "+cj2.toString());
            // Se a carta do jogador1 é maior, ele fica com todas
            if (cj1.eMaior(cj2)){
                jogador1.insereEmbaixo(cj1);
                jogador1.insereEmbaixo(cj2);
                System.out.println("Jogador 1 ganhou a rodada");
            }else{
                jogador2.insereEmbaixo(cj2);
                jogador2.insereEmbaixo(cj1);
                System.out.println("Jogador 2 ganhou a rodada");
            }
            // Verifica se acabou   
            if (jogador1.vazio() || jogador2.vazio()){
                acabou = true;
            }
            // Incrementa a rodada
            rodada++;
        }
        System.out.println("Fim");
    }
}

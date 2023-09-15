package T1_Baralho_2023;
import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite o nome do jogador 1:");
            String nomeJogador1 = scanner.nextLine();
            
            System.out.println("Digite o nome do jogador 2:");
            String nomeJogador2 = scanner.nextLine();

            
            // Cria o baralho e embaralha
            Baralho baralho = new Baralho();
            baralho.embaralha();
            // Cria os decks dos jogadores
            Deck jogador1 = new Deck();
            Deck jogador2 = new Deck();
            // Distribui todas as cartas entre os dois jogadores
            while(!baralho.vazio()){
                jogador1.insereEmbaixo(baralho.retiraDeCima());
                jogador2.insereEmbaixo(baralho.retiraDeCima());
            }
            
            // Inicializa o gerador de números aleatórios
            Random random = new Random();

            // Loop do jogo
            boolean acabou = false;
            int rodada = 1;
            while(!acabou){
                // Pega uma carta de cada jogador
                Carta cj1 = jogador1.retiraDeCima();
                Carta cj2 = jogador2.retiraDeCima();
                System.out.println("Rodada: "+rodada);
                System.out.println("Carta do "+ nomeJogador1 + " :" +cj1.toString());
                System.out.println("Carta do "+ nomeJogador2 + " :" +cj2.toString());
                
                // Lidar com empate
                while (cj1.getValor() == cj2.getValor()) {
                    System.out.println("Empate! As cartas serão acumuladas.");
                    
                    if (jogador1.vazio() || jogador2.vazio()){
                        acabou = true;
                        break;
                    }
                    
                    // Pega novas cartas para desempatar
                    cj1 = jogador1.retiraDeCima();
                    cj2 = jogador2.retiraDeCima();
                    
                    System.out.println("Nova Carta do "+ nomeJogador1 + " :" +cj1.toString());
                    System.out.println("Nova Carta do "+ nomeJogador2 + " :" +cj2.toString());
                }

                // Se a carta do jogador1 é maior, ele fica com todas
                if (cj1.eMaior(cj2)){
                    // Sorteia a ordem das cartas antes de incorporar ao deck
                    if (random.nextBoolean()) {
                        jogador1.insereEmbaixo(cj1);
                        jogador1.insereEmbaixo(cj2);
                    } else {
                        jogador1.insereEmbaixo(cj2);
                        jogador1.insereEmbaixo(cj1);
                    }
                    System.out.println("Jogador " + nomeJogador1 + " ganhou a rodada");
                }else{
                    // Sorteia a ordem das cartas antes de incorporar ao deck
                    if (random.nextBoolean()) {
                        jogador2.insereEmbaixo(cj2);
                        jogador2.insereEmbaixo(cj1);
                    } else {
                        jogador2.insereEmbaixo(cj1);
                        jogador2.insereEmbaixo(cj2);
                    }
                    System.out.println("Jogador " + nomeJogador2 + " ganhou a rodada");
                }
                
                // Verifica se acabou
                if (jogador1.vazio() || jogador2.vazio()){
                    acabou = true;
                    if (jogador1.vazio()) {
                        System.out.println("Jogador " + nomeJogador2 + " ganhou o jogo!");
                    } else {
                        System.out.println("Jogador " + nomeJogador1 + " ganhou o jogo!");
                    }
                }
                // Incrementa a rodada
                rodada++;
            }
        }
        
        System.out.println("Fim");
    }
}

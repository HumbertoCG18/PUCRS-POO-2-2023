package Aula7_18_10_2023;

/*
 * Roteiro:
 * - Criar interface criterioPesquisa
 * - Criar critérios específicos (livres, por peso)
 * - Mostrar com classes aninhadas anônimas
 * - Mostrar com lambdas
 * - Mostrar com lambdas em variáveis (ou listas)
 * - Mostrar com Predicate
 */
public class App {
    public static void main(String args[]){
        CadastroLocomotivas cad = new CadastroLocomotivas();
        cad.carrega();
        for(Locomotiva l:cad.livres()){
            System.out.println(l);
        }
    }
}

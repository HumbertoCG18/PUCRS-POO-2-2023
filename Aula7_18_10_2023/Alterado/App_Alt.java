
/*
 * Roteiro:
 * - Criar interface criterioPesquisa
 * - Criar critérios específicos (livres, por peso)
 * - Mostrar com classes aninhadas anônimas
 * - Mostrar com lambdas
 * - Mostrar com lambdas em variáveis (ou listas)
 * - Mostrar com Predicate
 */

import java.util.Collection;

public class App_Alt {
    public static void main(String args[]){
        CadastroLocomotivas cad = new CadastroLocomotivas();
        cad.carrega();

        //Collection<Locomotiva> resp = cad.getLocomotivas(new LocomotivaNaoLivre());

        /* 
        Collection<Locomotiva> resp = cad.getLocomotivas(new Condicao<Locomotiva>(){
            @Override
            public boolean seAplica(Locomotiva l) {
                if (l.getComposicao() == -1 && l.getPesoMaximo() > 500){
                    return true;
                }else{
                    return false;
                }
            }
        });
        */

        Condicao<Locomotiva> c1 = l->l.getComposicao() == -1;
        Collection<Locomotiva> resp = cad.getLocomotivas(c1);

        /* 
        Collection<Locomotiva> resp = cad.getLocomotivas(l->{
            int x = 10;
            boolean aux = l.getComposicao() == -1 && l.getQtdadeMaxVagoes() < x;
            return aux;
        });
        */


        System.out.println("Resposta: ");
        for(Locomotiva l:resp){
            System.out.println(l);
        }

    }
}

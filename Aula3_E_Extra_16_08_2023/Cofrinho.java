public class Cofrinho{
    private final int CAPACIDADE = 100;
    private Moeda moedas[];
    private int proxLivre;

    public Cofrinho(){
        moedas = new Moeda[CAPACIDADE];
        proxLivre = 0;
    }

    public boolean insere(Moeda moeda){
        if (proxLivre == CAPACIDADE){
            return false;
        }
        moedas[proxLivre] = moeda;
        proxLivre++;
        return true;
    }

    public Moeda retira(){
        if (proxLivre == 0){
            return null;
        }else{
            proxLivre--;
            return moedas[proxLivre];
        }
    }


    public int getQtdadeMoedas(){
        return proxLivre;
    }

    public int getQtdadeMoedasTipo(NomeMoeda nomeMoeda){
        int cont = 0;        
        for(int i=0;i<proxLivre;i++){
            if (moedas[i].getNomeMoeda() == nomeMoeda){
                cont++;
            }
        }
        return cont;
    }

    public int getValorTotalCentavos(){
        int valorTotal = 0;        
        for(int i=0;i<proxLivre;i++){
            valorTotal = valorTotal + moedas[i].getValorCentavos();
        }    
        return valorTotal;
    }

    public double getValorTotalReais(){
        double valorTotal = 0;        
        for(int i=0;i<proxLivre;i++){
            valorTotal = valorTotal + moedas[i].getValorReais();
        }
        return valorTotal;
    }
}
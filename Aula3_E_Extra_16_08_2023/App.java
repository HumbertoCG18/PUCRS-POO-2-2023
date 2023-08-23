public class App {
    public static void main(String args[]){
        Cofrinho c = new Cofrinho();

        c.insere(new Moeda(NomeMoeda.UmReal));
        c.insere(new Moeda(NomeMoeda.Cinquenta));
        c.insere(new Moeda(NomeMoeda.VinteCinco));
        c.insere(new Moeda(NomeMoeda.UmReal));
        c.insere(new Moeda(NomeMoeda.Cinco));
        c.insere(new Moeda(NomeMoeda.Cinquenta));
        c.insere(new Moeda(NomeMoeda.UmReal));
        c.insere(new Moeda(NomeMoeda.Dez));
        c.insere(new Moeda(NomeMoeda.Um));
        c.insere(new Moeda(NomeMoeda.VinteCinco));
        c.insere(new Moeda(NomeMoeda.UmReal));

        System.out.println("Quantidade de moedas inseridas: "+c.getQtdadeMoedas());
        System.out.println("Quantidade de moedas de 1 real: "+c.getQtdadeMoedasTipo(NomeMoeda.UmReal));
        System.out.println("Quantidade de moedas de 50 centavos: "+c.getQtdadeMoedasTipo(NomeMoeda.Cinquenta));
        System.out.println("Valor total em centavos: "+c.getValorTotalCentavos());
        System.out.println("Valor total em reais: "+c.getValorTotalReais());
        System.out.println("Moeda retirada: "+c.retira());
        System.out.println("Moeda retirada: "+c.retira());
        System.out.println("Valor total em centavos: "+c.getValorTotalCentavos());

        NomeMoeda[] tipos = NomeMoeda.values();
        for(int i=0;i<tipos.length;i++){
            int q = c.getQtdadeMoedasTipo(tipos[i]);
            System.out.println("Quantidade de moedas de "+tipos[i].name()+" centavos: "+q);
        }

    }
}
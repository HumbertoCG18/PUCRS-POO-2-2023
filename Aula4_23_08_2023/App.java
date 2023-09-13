public class App {
    public static void main(String[] args) throws Exception {
        GaragemCarroFerroviario garLoc = new GaragemCarroFerroviario();
        GaragemCarroFerroviario garVag = new GaragemCarroFerroviario();

        Locomotiva l = new Locomotiva(120, null, 10000, 20);
        garLoc.estaciona(l);
        garVag.estaciona(new VagaoCarga(10,null,5000));
        garVag.estaciona(new VagaoCargaRefrigerado(35,null, 6000, -5));
        garVag.estaciona(new VagaoPassageiros(67,null, 60));

        System.out.println("Locomotivas: ");
        for(int i=0;i<garLoc.qtdade();i++){
            System.out.println(garLoc.get(i));
        }
        System.out.println("Vagoes: ");
        for(int i=0;i<garVag.qtdade();i++){
            System.out.println(garVag.get(i));
        }

        //CarroFerroviario c1 = new VagaoCarga(10, null, 1000);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        GaragemCarroFerroviario gcv = new GaragemCarroFerroviario();

        Locomotiva l = new Locomotiva(120, null, 10000, 20);
        gcv.estaciona(l);
        gcv.estaciona(new VagaoCarga(10,null,5000));
        gcv.estaciona(new VagaoCargaRefrigerado(35,null, 6000, -5));
        gcv.estaciona(new VagaoPassageiros(67,null, 60));

        for(int i=0;i<gcv.qtdade();i++){
            System.out.println(gcv.get(i).toString());
        }
    }
}

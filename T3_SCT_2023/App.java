package T3_SCT_2023;
public class App {
    public static void main(String[] args) throws Exception {
        GaragemCarroFerroviario garagem = new GaragemCarroFerroviario();

        Locomotiva l = new Locomotiva(120, null, 10000, 20);
        CarroFerroviario c1 = new VagaoCarga(10, null, 1000);
        CarroFerroviario c2 = new VagaoCarga(11, null, 5000);
        CarroFerroviario c3 = new VagaoCargaRefrigerado(12, null, 6000, -5);
        CarroFerroviario c4 = new VagaoPassageiros(13, null, 60);

        garagem.estaciona(l);
        garagem.estaciona(c1);
        garagem.estaciona(c2);
        garagem.estaciona(c3);
        garagem.estaciona(c4);

        System.out.println("Carros Ferroviários: ");
        for (int i = 0; i < garagem.qtdade(); i++) {
            System.out.println(garagem.get(i));
        }

        // Exemplo de como adicionar passageiros a um Vagão de Passageiros
        if (c4 instanceof VagaoPassageiros) {
            VagaoPassageiros vagaoPassageiros = (VagaoPassageiros) c4;
            vagaoPassageiros.adicionarPassageiro(5);
            System.out.println("Número de passageiros no Vagão de Passageiros: " + vagaoPassageiros.getNumeroPassageiros());
        }

        // Exemplo de como adicionar carga refrigerada a um Vagão de Carga Refrigerado
        if (c3 instanceof VagaoCargaRefrigerado) {
            VagaoCargaRefrigerado vagaoRefrigerado = (VagaoCargaRefrigerado) c3;
            vagaoRefrigerado.adicionarCargaRefrigerada(1000);
            System.out.println("Carga refrigerada no Vagão de Carga Refrigerado: " + vagaoRefrigerado.getCargaRefrigerada());
        }

        // Exemplo de como calcular a carga total do trem
        double cargaTotal = 0.0;
        for (int i = 0; i < garagem.qtdade(); i++) {
            CarroFerroviario carro = garagem.get(i);
            if (carro instanceof VagaoCarga) {
                VagaoCarga vagaoCarga = (VagaoCarga) carro;
                cargaTotal += vagaoCarga.getCapacidadeCarga();
            } else if (carro instanceof VagaoPassageiros) {
                VagaoPassageiros vagaoPassageiros = (VagaoPassageiros) carro;
                cargaTotal += vagaoPassageiros.getNumeroPassageiros() * 120; // Assumindo peso médio por passageiro
            }
        }
        System.out.println("Carga Total do Trem: " + cargaTotal);
    }
}

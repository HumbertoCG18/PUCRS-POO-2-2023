public class App {
    public static boolean aceitavel(CarroFerroviario x){
        if (x.getCapacidadeCarga() > 5000){
            if (x.getId() > 30){
                if (x instanceof VagaoPassageiros){
                    VagaoPassageiros vpaux = (VagaoPassageiros)x;
                    if (vpaux.getNroAssentos() >= 50){
                        return true;
                    }else{
                        return false;
                    }
                }
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    } 

    public static void main(String[] args) throws Exception {
        //CarroFerroviario cf = new CarroFerroviario(20, null, 1000);
        //System.out.println(cf.toString());

        Locomotiva l = new Locomotiva(120, null, 10000, 20);
        System.out.println(l.toString());

        VagaoCarga vg = new VagaoCarga(10,null,5000);
        System.out.println(vg.toString());

        VagaoCargaRefrigerado vcr = new VagaoCargaRefrigerado(35,null, 6000, -5);
        System.out.println(vcr.toString());

        VagaoPassageiros vp = new VagaoPassageiros(67,null, 60);
        System.out.println(vp.toString());

        /* 
        boolean aux = vp instanceof VagaoPassageiros;
        System.out.println(aux);
        System.out.println(vp instanceof CarroFerroviario);
        */
        System.out.println(aceitavel(vg));
        System.out.println(aceitavel(vcr));
        System.out.println(aceitavel(vp));
        System.out.println(aceitavel(l));
    }
}

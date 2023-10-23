public class LocomotivasLivres implements Condicao<Locomotiva>{
    public boolean seAplica(Locomotiva l){
        if (l.getComposicao() == -1){
            return true;
        }else{
            return false;
        }
    }
}

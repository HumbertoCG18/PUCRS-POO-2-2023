class LocomotivaNaoLivre implements Condicao<Locomotiva>{
    @Override
    public boolean seAplica(Locomotiva l){
        if (l.getComposicao() != -1){
            return true;
        }else{
            return false;
        }
    }
}
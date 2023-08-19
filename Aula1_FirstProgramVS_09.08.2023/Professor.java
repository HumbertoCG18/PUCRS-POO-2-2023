package Aula1_FirstProgramVS;

public class Professor {
    private String nome;    
    private int anosExperiencia;

    public Professor(String nome, int anosExperiencia) {
        this.nome = nome;
        this.anosExperiencia = anosExperiencia;
    }

    public String getNome() {
        return nome;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public String classifica(){
        // Nova sintaxe do Java 14-15 em diante
        return switch(anosExperiencia){
            case 0,1,2,3,4,5 -> "Assistente";
            case 6,7,8,9,10 -> "Adjunto";
            default -> "Titular";
        };
    }

    @Override
    public String toString() {
        return "Professor: " + nome + ", anos de experiencia:" + anosExperiencia;
    }
}
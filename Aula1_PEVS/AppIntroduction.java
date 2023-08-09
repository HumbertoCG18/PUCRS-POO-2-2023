package Aula1_PEVS;

public class AppIntroduction {
    public static void main(String[] args) throws Exception {
        System.out.println("\nHi!");
        String version = System.getProperty("java.version");
        System.out.println("Running Java Version "+version+"\n");
    }
}

/* 
 ! O objetivo do próximo exercício é partir de um programa que tem apenas uma classe e evoluir o mesmo para um projeto com duas classes.
 
 * Usando o VSCode no GitPod ou não você pode compilar e executar usando a opção “run” do IDE. Exercite também a “compilação manual” direto pela linha de comando. Isso é importante para aquelas situações em que não houver IDE disponível ou as configurações da IDE estiverem com problemas.
 
 * · Para compilar: (assumindo que os arquivos fontes estão na pasta “src” e que os “.class” devem ser gerados na pasta “bin"). Use o comando: javac ./src/*.java -d ./bin
 
 * · Para executar: (assumindo que os “.class” estão na pasta “bin” e que o arquivo que contém o “main” é o “App.class”: java –cp ./bin App 
*/


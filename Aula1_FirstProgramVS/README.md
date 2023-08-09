<center> <h1>Roteiro para primeiro exemplo no VSCode</h1> </center>

O objetivo deste roteiro é mostrar o processo de criação de projetos, compilação e execução de programas no Visual Studio Code.

O código preparatório visa apenas identificar se o processo de criação, compilação e execução de um programa está dominado:

[Preparatório](https://gist.github.com/bcopstein/779c87c93026bbc59d9b89eec6ca928c)

```java
// AppIntroduction.java

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\nHi!");
        String version = System.getProperty("java.version");
        System.out.println("Running Java Version "+version+"\n");
    }
}

```

---

Ver mais propriedades em: [The Java](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)

<p>O objetivo do próximo exercício é partir de um programa que tem apenas uma classe e evoluir o mesmo para um projeto com duas classes.<p>

---

<center> <br> <h1>Exercício 1</h1></center>

Usando o VSCode no GitPod ou não você pode compilar e executar usando a opção “run” do IDE. Exercite também a “compilação manual” direto pela linha de comando. Isso é importante para aquelas situações em que não houver IDE disponível ou as configurações da IDE estiverem com problemas.

    · Para compilar: (assumindo que os arquivos fontes estão na pasta “src” e que os “.class” devem ser gerados na pasta “bin"). Use o comando: javac ./src/*.java -d ./bin

    · Para executar: (assumindo que os “.class” estão na pasta “bin” e que o arquivo que contém o “main” é o “App.class”: java –cp ./bin Ap

---

<center><br><h1> Códigos</h1></center>

## Parte 1

```java
// AppP1.java
import java.util.Scanner;

public class App{
    public static void main(String args[]){ 
      Scanner s = new Scanner(System.in);
      System.out.println("Nome do professor?");
      String nome  = s.next();
      if (nome.equals("Bernardo")){ 
        System.out.println("Show!");
      }else{
        System.out.println("Nao conheco");
      }
      s.close();
    }
  }
```

## Parte 2

```java
// App.java
import java.util.Scanner;

public class App{
    public static void main(String args[]){ 
      Scanner s = new Scanner(System.in);
      System.out.println("Nome do professor?");
      String nome  = s.next();
      System.out.println("Quantos anos de experiencia ele tem?");
      int experiencia  = s.nextInt();
      Professor p = new Professor(nome,experiencia);
      System.out.println(p.toString());
      System.out.println("Categoria:"+p.classifica());
      s.close();
    }
  }
```

---

```java
// Professor.java
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

```

package Aula1_FirstProgramVS;
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
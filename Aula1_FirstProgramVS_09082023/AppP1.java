package Aula1_FirstProgramVS;

import java.util.Scanner;

public class AppP1{
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
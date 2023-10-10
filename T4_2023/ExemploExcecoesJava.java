package T4_2023;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExemploExcecoesJava {
    public static void main(String[] args) {
        // Exceção de índice de arranjo
        int[] arr = new int[5];
        try {
            int valor = arr[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice fora do limite do array");
        }

        // Exceção de divisão por zero
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Divisão por zero");
        }

        // Exceção de coerção de tipo
        try {
            Object obj = "string";
            Integer num = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("Erro de coerção de tipo");
        }

        // Exceção de entrada e saída
        try {
            FileReader arquivo = new FileReader("arquivo.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
}


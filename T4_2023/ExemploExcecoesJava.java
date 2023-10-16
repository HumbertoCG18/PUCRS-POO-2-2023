package T4_2023;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExemploExcecoesJava {
    public static void main(String[] args) {
        // Exceção de índice de arranjo
        int[] arr = new int[5];
        try {
            double valor;
            valor = arr[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice fora do limite do array");
        }

        // Exceção de divisão por zero
        try {
            int resultado;
            resultado = 10 / 0;
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
            try (FileReader arquivo = new FileReader("arquivo.txt")) {
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
}


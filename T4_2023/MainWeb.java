package T4_2023;

import java.io.IOException;
import java.net.MalformedURLException;

public class MainWeb {
    public static void main(String[] args) {
        try {
            WebPageReader reader = new WebPageReader("http://java.sun.com//index.html");

            while (!reader.isDone()) {
                String input = reader.readLine();
                if (input != null) {
                    System.out.println(input);
                }
            }

            reader.close();
        } catch (MalformedURLException e) {
            System.out.println("Endereço mal formado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        }
    }
}

//Este exemplo lê e imprime as linhas da página web fornecida. Ele lida com as exceções MalformedURLException e IOException que podem ocorrer durante o processo.
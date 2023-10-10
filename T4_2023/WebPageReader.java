package T4_2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageReader {
    private BufferedReader reader;
    private boolean done = false;

    public WebPageReader(String endereco) throws MalformedURLException, IOException {
        URL url = new URL(endereco);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
    }

    public String readLine() throws IOException {
        String input = reader.readLine();
        if (input == null) {
            done = true;
        }
        return input;
    }

    public boolean isDone() {
        return done;
    }

    public void close() throws IOException {
        reader.close();
    }
}


/** 
A classe WebPageReader possui um BufferedReader para ler as linhas da página web e uma flag done para indicar se a leitura chegou ao final.

O construtor recebe o endereço da página web e inicializa o BufferedReader. Ele pode lançar MalformedURLException se o endereço estiver mal formado e IOException se ocorrer um erro de entrada/saída durante a leitura.

O método readLine lê a próxima linha da página web. Ele pode lançar IOException se ocorrer um erro de entrada/saída durante a leitura.

O método isDone retorna se a leitura chegou ao final.

O método close fecha o BufferedReader. Ele pode lançar IOException se ocorrer um erro de entrada/saída ao fechar o BufferedReader

**/

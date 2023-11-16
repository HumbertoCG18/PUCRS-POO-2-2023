import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String cpf;
    private String nome;
    private String email;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<Cliente> lerClientesDoArquivo(String caminhoArquivo) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    String email = partes[0].trim();
                    String senha = partes[1].trim();
                    String nome = partes[2].trim();
                    String cpf = partes[3].trim();

                    // Considerando que a classe Cliente possui um método setSenha
                    // Adapte conforme necessário se a senha não estiver presente no arquivo
                    Cliente cliente = new Cliente(email, nome, cpf);
                    cliente.setSenha(senha); // Supondo existir o método setSenha na classe Cliente
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Adicione aqui o método setSenha, se a classe Cliente não tiver um método assim
    public void setSenha(String senha) {
        // Lógica para setar a senha
    }
}

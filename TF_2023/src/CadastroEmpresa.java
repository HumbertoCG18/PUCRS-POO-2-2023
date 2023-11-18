package src;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroEmpresa {

    public static void cadastrarEmpresa(String email, String senha, String nome, String cnpj) {
        String caminhoArquivo = "./TF_2023/Empresa.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            // Adiciona uma nova linha ao arquivo com os dados da empresa
            writer.write(email + ";" + senha + ";" + nome + ";" + cnpj);
            writer.newLine();

            System.out.println("Empresa cadastrada com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        cadastrarEmpresa("empresa@email.com", "senha123", "Minha Empresa", "12345678901234");
    }
}

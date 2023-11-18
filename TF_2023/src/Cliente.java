package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cliente {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Map<Integer, Aplicativo> aplicativos;

    public Cliente(String cpf, String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.aplicativos = new HashMap<>();
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

    public void setSenha(String senha) {
       this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void vincularAplicativo(int idAplicativo, Aplicativo aplicativo) {
        aplicativos.put(idAplicativo, aplicativo);
    }

    public void removerAplicativo(int idAplicativo) {
        aplicativos.remove(idAplicativo);
    }

    public Map<Integer, Aplicativo> getAplicativos() {
        return aplicativos;
    }
    public static List<Cliente> lerClientesDoArquivo(String caminhoArquivo, Map<Integer, Aplicativo> mapaAplicativos) {
        List<Cliente> clientes = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    String email = partes[0].trim();
                    String senha = partes[1].trim();
                    String nome = partes[2].trim();
                    String cpf = partes[3].trim();
                    String[] idsAplicativos = partes[4].split(",");
    
                    Cliente cliente = new Cliente(cpf, nome, email);
                    cliente.setSenha(senha);
    
                    for (String idAplicativo : idsAplicativos) {
                        int id = Integer.parseInt(idAplicativo.trim());
                        Aplicativo aplicativo = mapaAplicativos.get(id);
                        cliente.vincularAplicativo(id, aplicativo);
                    }
    
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    
    public static void escreverClientesNoArquivo(String caminhoArquivo, List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Cliente cliente : clientes) {
                StringBuilder sb = new StringBuilder();
                sb.append(cliente.getEmail()).append(";")
                        .append(cliente.getSenha()).append(";")
                        .append(cliente.getNome()).append(";")
                        .append(cliente.getCpf()).append(";");

                List<Integer> idsAplicativos = new ArrayList<>(cliente.getAplicativos().keySet());
                for (int i = 0; i < idsAplicativos.size(); i++) {
                    sb.append(idsAplicativos.get(i));
                    if (i < idsAplicativos.size() - 1) {
                        sb.append(",");
                    }
                }

                sb.append("\n");
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

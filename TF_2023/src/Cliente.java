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

    public Map<Integer, Aplicativo> getAplicativos() {
        return aplicativos;
    }

    public void instalarAplicativo(Aplicativo aplicativo) {
        for (Map.Entry<Integer, Aplicativo> entry : aplicativos.entrySet()) {
            if (entry.getValue().equals(aplicativo)) {
                System.out.println("O aplicativo já está instalado para este cliente.");
                return;
            }
        }
        
        // Encontra o próximo ID disponível
        int nextId = aplicativos.isEmpty() ? 1 : aplicativos.size() + 1;
        aplicativos.put(nextId, aplicativo);
    }

    public void desinstalarAplicativo(Aplicativo aplicativo) {
        // Procura pelo aplicativo e remove-o se encontrado
        for (Map.Entry<Integer, Aplicativo> entry : aplicativos.entrySet()) {
            if (entry.getValue().equals(aplicativo)) {
                aplicativos.remove(entry.getKey());
                return;
            }
        }

        System.out.println("O aplicativo não está instalado para este cliente.");
    }


    public static List<Cliente> lerClientesDoArquivo(String caminhoArquivoCliente, Map<Integer, Aplicativo> mapaAplicativos) {
        List<Cliente> clientes = new ArrayList<>();
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoClientes = diretorioAtual + "/TF_2023/src/Cliente.txt";
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoClientes))) {
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
                        if (aplicativo != null) {
                            cliente.vincularAplicativo(id, aplicativo);
                        } else {
                            System.out.println("Aplicativo com ID " + id + " não encontrado.");
                        }
                    }
    
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    
    public static void escreverClientesNoArquivo(String caminhoArquivoCliente, List<Cliente> clientes) {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoClientes = diretorioAtual + "/TF_2023/src/Cliente.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoClientes))) {
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

    public static void removerClienteDoArquivo(String caminhoArquivoCliente, List<Cliente> clientes, String cpfCliente) {
        String diretorioAtual = System.getProperty("user.dir");
        String caminhoArquivoClientes = diretorioAtual + "/TF_2023/src/Cliente.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoClientes))) {
            for (Cliente cliente : clientes) {
                if (!cliente.getCpf().equals(cpfCliente)) {
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
                    writer.write(sb.toString());
                }
            }
            System.out.println("Cliente removido com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

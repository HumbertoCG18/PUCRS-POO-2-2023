        package src;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        public class Aplicativo {
            private int codigo;
            private String nomeAplicativo;
            private String sistemaOperacional;
            private int idAssinatura;
            private double valorMensal;


            private static List<Aplicativo> aplicativos = new ArrayList<>();

            public Aplicativo(int codigo, String nome, String sistemaOperacional, int idAssinatura, double valorMensal) {
                this.codigo = codigo;
                this.nomeAplicativo = nome;
                this.sistemaOperacional = sistemaOperacional;
                this.idAssinatura = idAssinatura;
                this.valorMensal = valorMensal;
            }


            public static Aplicativo getAplicativoPeloCodigo(int codigo) {
                for (Aplicativo app : aplicativos) {
                    if (app.getCodigo() == codigo) {
                        return app;
                    }
                }
                return null; // Retorna null se não encontrar o aplicativo com o código especificado
            }

            public static double calcularValorMensalPeloCodigo(int codigo) {
                Aplicativo app = getAplicativoPeloCodigo(codigo);
                if (app != null) {
                    return app.getValorMensal();
                }
                return 0.0; // Retorna 0.0 se não encontrar o aplicativo com o código especificado
            }

            public int getCodigo() {
                return codigo;
            }

            public void setCodigo(int codigo) {
                this.codigo = codigo;
            }

            public String getNome() {
                return nomeAplicativo;
            }

            public void setNome(String nomeAplicativo) {
                this.nomeAplicativo = nomeAplicativo;
            }

            public String getSistemaOperacional() {
                return sistemaOperacional;
            }

            public void setSistemaOperacional(String sistemaOperacional) {
                this.sistemaOperacional = sistemaOperacional;
            }

            public int getIdAssinatura() {
                return idAssinatura;
            }

            public void setIdAssinatura(int idAssinatura) {
                this.idAssinatura = idAssinatura;
            }

            public double getValorMensal() {
                return valorMensal;
            }

            public void setValorMensal(double valorMensal) {
                this.valorMensal = valorMensal;
            }

            public static void salvarAplicativosEmArquivo(List<Aplicativo> aplicativos, String caminhoArquivoAplicativo) {
                String diretorioAtual = System.getProperty("user.dir");
                String caminhoArquivoAplicativos = diretorioAtual + "/TF_2023/src/Aplicativos.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAplicativos))) {
                    for (Aplicativo aplicativo : aplicativos) {
                        writer.write(String.format("%d;%s;%s;%d%n%.f;",
                                aplicativo.getCodigo(),
                                aplicativo.getNome(),
                                aplicativo.getSistemaOperacional(),
                                aplicativo.getIdAssinatura(),
                                aplicativo.getValorMensal()));
                    }
                    System.out.println("Aplicativos salvos com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static void removerAplicativoDoArquivo(List<Aplicativo> aplicativos, int codigo, String caminhoArquivoAplicativo) {
                String diretorioAtual = System.getProperty("user.dir");
                String caminhoArquivoAplicativos = diretorioAtual + "/TF_2023/src/Aplicativos.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAplicativos))) {
                    for (Aplicativo aplicativo : aplicativos) {
                        if (aplicativo.getCodigo() != codigo) {
                            writer.write(String.format("%d;%s;%s;%d;%.2f%n",
                                    aplicativo.getCodigo(),
                                    aplicativo.getNome(),
                                    aplicativo.getSistemaOperacional(),
                                    aplicativo.getIdAssinatura(),
                                    aplicativo.getValorMensal()));
                        }
                    }
                    System.out.println("Aplicativo removido com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
            public static void main(String[] args) {
                List<Aplicativo> aplicativos = new ArrayList<>();
                String diretorioAtual = System.getProperty("user.dir");

                Aplicativo app1 = new Aplicativo(1, "App1", "Android", 1, 9.90);
                app1.setIdAssinatura(1); // Assinatura básica

                Aplicativo app2 = new Aplicativo(2, "App2", "iOS", 2, 18.90);
                app2.setIdAssinatura(2); // Assinatura VIP

                Aplicativo app3 = new Aplicativo(3, "App3", "Windows",3, 29.90);
                app3.setIdAssinatura(3); // Assinatura Premium

                aplicativos.add(app1);
                aplicativos.add(app2);
                aplicativos.add(app3);

                salvarAplicativosEmArquivo(aplicativos, diretorioAtual + "/TF_2023/src/Aplicativos.txt");
            }
        }

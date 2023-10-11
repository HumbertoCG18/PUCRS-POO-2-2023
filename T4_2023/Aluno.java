package T4_2023;

public class Aluno {
    private int matricula;
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    private String nome;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private int anoNascimento;

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Aluno(int umaMatricula, String umNome, int umAnoNascimento) throws IllegalArgumentException {
        if (umaMatricula <= 0) {
            throw new IllegalArgumentException("A matrícula deve ser um número positivo.");
        }

        if (umNome == null || umNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }

        int anoAtual = java.time.Year.now().getValue();
        if (umAnoNascimento <= anoAtual - 100 || umAnoNascimento >= anoAtual) {
            throw new IllegalArgumentException("O ano de nascimento deve estar dentro de um intervalo razoável.");
        }

        nome = umNome;
        matricula = umaMatricula;
        anoNascimento = umAnoNascimento;
    }
}

//Verificação da Matrícula: Verifica se a matrícula é um número positivo. Caso contrário, lança uma exceção do tipo IllegalArgumentException.

//Verificação do Nome: Verifica se o nome não é nulo ou vazio. Caso contrário, lança uma exceção do tipo IllegalArgumentException.

//Verificação do Ano de Nascimento: Verifica se o ano de nascimento está dentro de um intervalo razoável (100 anos antes do ano atual até o ano atual). Caso contrário, lança uma exceção do tipo IllegalArgumentException.
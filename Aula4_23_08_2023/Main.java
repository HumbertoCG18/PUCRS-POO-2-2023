import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Locomotiva {
    private int id;
    private int pesoMaximo;
    private int numMaxVagoes;
    private Trem tremAtual;

    public Locomotiva(int id, int pesoMaximo, int numMaxVagoes) {
        this.id = id;
        this.pesoMaximo = pesoMaximo;
        this.numMaxVagoes = numMaxVagoes;
        this.tremAtual = null;
    }

    public int getId() {
        return id;
    }

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public int getNumMaxVagoes() {
        return numMaxVagoes;
    }

    public Trem getTremAtual() {
        return tremAtual;
    }

    public void setTremAtual(Trem tremAtual) {
        this.tremAtual = tremAtual;
    }
}

class Vagao {
    private int id;
    private int capacidadeMaxima;
    private Trem tremAtual;

    public Vagao(int id, int capacidadeMaxima) {
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.tremAtual = null;
    }

    public int getId() {
        return id;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public Trem getTremAtual() {
        return tremAtual;
    }

    public void setTremAtual(Trem tremAtual) {
        this.tremAtual = tremAtual;
    }
}

class Trem {
    private int id;
    private List<Locomotiva> locomotivas;
    private List<Vagao> vagoes;

    public Trem(int id) {
        this.id = id;
        this.locomotivas = new ArrayList<>();
        this.vagoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Locomotiva> getLocomotivas() {
        return locomotivas;
    }

    public List<Vagao> getVagoes() {
        return vagoes;
    }

    public void addLocomotiva(Locomotiva locomotiva) {
        locomotivas.add(locomotiva);
        locomotiva.setTremAtual(this);
    }

    public void addVagao(Vagao vagao) {
        vagoes.add(vagao);
        vagao.setTremAtual(this);
    }

    public void removeLastElement() {
        if (!vagoes.isEmpty()) {
            Vagao vagaoRemovido = vagoes.remove(vagoes.size() - 1);
            vagaoRemovido.setTremAtual(null);
        } else if (!locomotivas.isEmpty()) {
            Locomotiva locomotivaRemovida = locomotivas.remove(locomotivas.size() - 1);
            locomotivaRemovida.setTremAtual(null);
        }
    }
}

public class Main {
    private static List<Locomotiva> locomotivasLivres = new ArrayList<>();
    private static List<Vagao> vagoesLivres = new ArrayList<>();
    private static List<Trem> trens = new ArrayList<>();
    private static int nextTremId = 1;

    public static void main(String[] args) {
        // Inicializa o programa com algumas locomotivas e vagoes livres
        locomotivasLivres.add(new Locomotiva(1, 50, 50));
        locomotivasLivres.add(new Locomotiva(2, 50, 50));
        vagoesLivres.add(new Vagao(1, 100));
        vagoesLivres.add(new Vagao(2, 100));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar um trem");
            System.out.println("2. Editar um trem");
            System.out.println("3. Listar todos os trens");
            System.out.println("4. Desfazer um trem");
            System.out.println("5. Fim");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    criarTrem();
                    break;
                case 2:
                    editarTrem();
                    break;
                case 3:
                    listarTrens();
                    break;
                case 4:
                    desfazerTrem();
                    break;
                case 5:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void criarTrem() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o identificador do trem: ");
            int tremId = nextTremId++;
            Trem trem = new Trem(tremId);

            System.out.println("Locomotivas livres:");
            listarLocomotivasLivres();
            System.out.print("Digite o identificador da primeira locomotiva: ");
            int primeiraLocomotivaId = scanner.nextInt();

            Locomotiva primeiraLocomotiva = getLocomotivaById(primeiraLocomotivaId);
            if (primeiraLocomotiva == null) {
                System.out.println("Locomotiva não encontrada.");
                return;
            }

            trem.addLocomotiva(primeiraLocomotiva);
            trens.add(trem);
        }
        System.out.println("Trem criado com sucesso!");
    }

    private static void editarTrem() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o identificador do trem a ser editado: ");
            int tremId = scanner.nextInt();

            Trem trem = getTremById(tremId);
            if (trem == null) {
                System.out.println("Trem não encontrado.");
                return;
            }

            int subChoice;
            do {
                System.out.println("Escolha uma opção para editar o trem " + tremId + ":");
                System.out.println("1. Inserir uma locomotiva");
                System.out.println("2. Inserir um vagão");
                System.out.println("3. Remover o último elemento do trem");
                System.out.println("4. Listar locomotivas livres");
                System.out.println("5. Listar vagões livres");
                System.out.println("6. Encerrar a edição do trem");
                subChoice = scanner.nextInt();

                switch (subChoice) {
                    case 1:
                        listarLocomotivasLivres();
                        System.out.print("Digite o identificador da locomotiva a ser inserida: ");
                        int locomotivaId = scanner.nextInt();
                        inserirLocomotiva(trem, locomotivaId);
                        break;
                    case 2:
                        listarVagoesLivres();
                        System.out.print("Digite o identificador do vagão a ser inserido: ");
                        int vagaoId = scanner.nextInt();
                        inserirVagao(trem, vagaoId);
                        break;
                    case 3:
                        trem.removeLastElement();
                        break;
                    case 4:
                        listarLocomotivasLivres();
                        break;
                    case 5:
                        listarVagoesLivres();
                        break;
                    case 6:
                        System.out.println("Edição do trem " + tremId + " encerrada.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (subChoice != 6);
        }
    }

    private static void listarTrens() {
        System.out.println("Trens no pátio:");
        for (Trem trem : trens) {
            System.out.println("Trem " + trem.getId() + " - Locomotivas: " + trem.getLocomotivas().size() +
                    " - Vagoes: " + trem.getVagoes().size());
        }
    }

    private static void desfazerTrem() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o identificador do trem a ser desfeito: ");
            int tremId = scanner.nextInt();

            Trem trem = getTremById(tremId);
            if (trem == null) {
                System.out.println("Trem não encontrado.");
                return;
            }

            for (Locomotiva locomotiva : trem.getLocomotivas()) {
                locomotiva.setTremAtual(null);
            }
            for (Vagao vagao : trem.getVagoes()) {
                vagao.setTremAtual(null);
            }

            trens.remove(trem);
            System.out.println("Trem " + tremId + " desfeito com sucesso.");
        }
    }

    private static void listarLocomotivasLivres() {
        System.out.println("Locomotivas livres:");
        for (Locomotiva locomotiva : locomotivasLivres) {
            System.out.println("Locomotiva " + locomotiva.getId() + " - Peso Máximo: " + locomotiva.getPesoMaximo() +
                    " ton - Vagões Máximos: " + locomotiva.getNumMaxVagoes());
        }
    }

    private static void listarVagoesLivres() {
        System.out.println("Vagões livres:");
        for (Vagao vagao : vagoesLivres) {
            System.out.println("Vagão " + vagao.getId() + " - Capacidade Máxima: " + vagao.getCapacidadeMaxima() + " ton");
        }
    }

    private static Locomotiva getLocomotivaById(int id) {
        for (Locomotiva locomotiva : locomotivasLivres) {
            if (locomotiva.getId() == id) {
                return locomotiva;
            }
        }
        return null;
    }

    private static Trem getTremById(int id) {
        for (Trem trem : trens) {
            if (trem.getId() == id) {
                return trem;
            }
        }
        return null;
    }

    private static void inserirLocomotiva(Trem trem, int locomotivaId) {
        Locomotiva locomotiva = getLocomotivaById(locomotivaId);
        if (locomotiva == null) {
            System.out.println("Locomotiva não encontrada.");
            return;
        }

        if (trem.getLocomotivas().isEmpty()) {
            trem.addLocomotiva(locomotiva);
        } else {
            int numLocomotivas = trem.getLocomotivas().size();
            if (numLocomotivas >= 2) {
                int capacidadeRestante = (int) (locomotiva.getPesoMaximo() * Math.pow(0.9, numLocomotivas - 1));
                if (capacidadeRestante < locomotiva.getPesoMaximo()) {
                    System.out.println("Não é possível adicionar mais locomotivas. Capacidade máxima atingida.");
                    return;
                }
            }
            trem.addLocomotiva(locomotiva);
        }
    }

    private static void inserirVagao(Trem trem, int vagaoId) {
        Vagao vagao = getVagaoById(vagaoId);
        if (vagao == null) {
            System.out.println("Vagão não encontrado.");
            return;
        }

        int capacidadeTrem = 0;
        for (Locomotiva locomotiva : trem.getLocomotivas()) {
            capacidadeTrem += locomotiva.getPesoMaximo();
        }

        if (capacidadeTrem + vagao.getCapacidadeMaxima() <= trem.getLocomotivas().get(0).getPesoMaximo()) {
            trem.addVagao(vagao);
        } else {
            System.out.println("Não é possível adicionar mais vagões. Capacidade máxima atingida.");
        }
    }

    private static Vagao getVagaoById(int id) {
        for (Vagao vagao : vagoesLivres) {
            if (vagao.getId() == id) {
                return vagao;
            }
        }
        return null;
    }
}

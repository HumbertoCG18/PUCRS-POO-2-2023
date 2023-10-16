package Aula5_11_10_2023.MatriculadosNaDiciplina;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        List<String> d1 = Arrays.asList("Ze", "Antonio", "Silvia", "Joao");
        List<String> d2 = Arrays.asList("Carlos", "Ze", "Neymar", "Silvia");
        List<String> d3 = Arrays.asList("Ze", "Amanda", "Neymar", "Luiza");

        System.out.println();
        System.out.println("Turma1: " + d1);
        System.out.println("Turma2: " + d2);
        System.out.println("Turma3: " + d3);

        // Uniao
        Set<String> todos = new TreeSet<>(d1);
        todos.addAll(d2);
        todos.addAll(d3);

        // Diferenca
        Set<String> soNaTurmaUm = new TreeSet<>(d1);
        soNaTurmaUm.removeAll(d2);
        soNaTurmaUm.removeAll(d3);

        // Interseccao
        Set<String> simultaneamenteD1D2 = new TreeSet<>(d1);
        simultaneamenteD1D2.retainAll(d2);

        System.out.println();
        System.out.println("Nas tres turmas: " + todos);
        System.out.println("So na turma 1: " + soNaTurmaUm);
        System.out.println("Simultaneamente nas turmas 1 e 2: " + simultaneamenteD1D2);
    }
}

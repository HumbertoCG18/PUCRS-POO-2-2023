package Aula5_11_10_2023.ListaSemRepeticao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class App {
    public static List<Integer> eliminaRepetidos(List<Integer> dados){
        return new ArrayList<Integer>(new HashSet<>(dados));    
    }

    public static void main(String[] args) throws Exception {
        List<Integer> lst = Arrays.asList(10,20,30,10,20,20);
        List<Integer> semRep = eliminaRepetidos(lst);
        System.out.println(semRep.toString());

        //Set<String> nomes = new HashSet<>();
        Set<String> nomes = new TreeSet<>();
        nomes.add("Sandra");
        nomes.add("Bernardo");
        nomes.add("Heloisa");
        nomes.add("Marina");
        nomes.add("Sandra");
        nomes.add("Heloisa");
        System.out.println(nomes);
        System.out.println(nomes.contains("Marina"));
    }
}

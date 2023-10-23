import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class CadastroLocomotivas {
	private ArrayList<Locomotiva> locomotivas;

	public CadastroLocomotivas() {
		locomotivas = new ArrayList<>();
	}

	public void cadastra(Locomotiva l) {
		locomotivas.add(l);
	}

	public int qtdade() {
		return locomotivas.size();
	}

	public Locomotiva getPorId(int id) {
		for (Locomotiva locomotiva : locomotivas) {
			if (locomotiva.getIdentificador() == id) {
				return locomotiva;
			}
		}
		return null;
	}

	// Retorna uma lista com as locomotivas livres
	// Como deixar a pesquisa mais genérica?
	// Collection<Locomotiva> busca(CriterioDeBusca criterio)
	Collection<Locomotiva> livres() {
		Collection<Locomotiva> lstLivres = new HashSet<>();
		for (Locomotiva l : locomotivas) {
			if (l.getComposicao() == -1) {
				lstLivres.add(l);
			}
		}
		return lstLivres;
	}

	Collection<Locomotiva> getLocomotivas(Condicao<Locomotiva> condicao) {
		Collection<Locomotiva> lstResp = new LinkedList<>();
		for (Locomotiva l : locomotivas) {
			if (condicao.seAplica(l)) {
				lstResp.add(l);
			}
		}
		return lstResp;
	}

	Collection<Locomotiva> todas() {
		return new ArrayList<>(locomotivas);
	}

	public void carrega() {
		String fName = "locomotivas.dat";
		String currDir = Paths.get("").toAbsolutePath().toString();
		String nameComplete = currDir + "/" + fName;
		Path path = Paths.get(nameComplete);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
			while (sc.hasNext()) {
				String linha = sc.nextLine();
				String dados[] = linha.split(",");
				int codigo = Integer.parseInt(dados[0]);
				double pesoMax = Double.parseDouble(dados[1]);
				int qtdadeVagoes = Integer.parseInt(dados[2]);
				int composicao = Integer.parseInt(dados[3]);
				Locomotiva locomotiva = new Locomotiva(codigo, pesoMax, qtdadeVagoes, composicao);
				locomotivas.add(locomotiva);
			}
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
	}
}

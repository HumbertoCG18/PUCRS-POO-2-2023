import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Comparator;

public class CadastroUsuarios implements{
    private List<Usuario> users;
    
    private void inicializa(String fName)throws IOException{
        File file = new File(fName);
        Scanner s = new Scanner(file);
    	String line = s.nextLine();
        while(s.hasNextLine()){
        	line = s.nextLine();
        	StringTokenizer st = new StringTokenizer(line,",");
        	int id = Integer.parseInt(st.nextToken());
        	String nome = st.nextToken();
        	String sobrenome = st.nextToken();
        	String email = st.nextToken();
        	String sexo = st.nextToken();
        	String ip = st.nextToken();
            Usuario u = new Usuario(id,nome,sobrenome,email,sexo,ip);
            users.add(u);
        }
        s.close();
    }
    
    public CadastroUsuarios()throws IOException{
    	users = new LinkedList<>();
    	inicializa("pessoas.txt");
    }

    // Letra a    
    @Override
    public Iterator<Usuario> iterator(){
    	return null;
    }
    
    public int quantidade(){
        return users.size();
    }
    
    // Letra b
    public int removePorIp(int nro){
        int cont = 0;
        return cont;
    }
    
    // Letra c
    public boolean insere(String nome,String sobrenome,Usuario novo){
        return false;
    }
    
    // Letra d 
    public void ordena(){
    }

    @Override
    public String toString(){
    	return users.toString();
    }
}

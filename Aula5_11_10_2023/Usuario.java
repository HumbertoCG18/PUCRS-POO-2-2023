package Aula5_11_10_2023;


public class Usuario implements Comparable<Usuario>{
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String sexo;
    private String ip;
    
	public Usuario(int id, String nome, String sobrenome, String email, String sexo, String ip) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.sexo = sexo;
		this.ip = ip;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", sexo="
				+ sexo + ", ip=" + ip + "]";
	}

	/*
	@Override
	public int compareTo(Usuario outro) {
		return this.getNome().compareTo(outro.getNome());
	}
	*/
	
	@Override
	public int compareTo(Usuario outro) {
		String esteNomeCompleto = this.getNome()+" "+this.getSobrenome();
		String outroNomeCompleto = outro.getNome()+" "+outro.getSobrenome();
		return esteNomeCompleto.compareTo(outroNomeCompleto);
	}
	
	/*
	@Override
	public int compareTo(Usuario outro) {
        if (outro.id > this.id) {
        	return 1;
        }else if (outro.id < this.id) {
        	return -1;
        }else return 0;
	}
	
	@Override
	public int compareTo(Usuario outro) {
        return outro.id - this.id;
        //return this.id - outro.id;
	}
	*/
}

package it.jac.corsojava.bean;

public class Badge {

	private String codice;
	private String nome;
	private String cognome;
	
	private long createdAt;
	
	public Badge() {
		
		System.out.println("costruttore di default");
		this.createdAt = System.currentTimeMillis();
	}
	
	public Badge(String codice) {
		
//		deve essere la prima riga utile
		this();
		this.codice = codice;

		System.out.println("costruttore [codice]");
	}
	
	public Badge(String codice, String nome, String cognome) {

//		deve essere la prima riga utile
		this(codice);
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "Badge [codice=" + codice + ", nome=" + nome + ", cognome=" + cognome + ", createdAt=" + createdAt + "]";
	}

}

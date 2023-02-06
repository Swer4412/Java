package it.jac.corsojava.bean;

public class Persona {

	String nome = "Mario";
	
	public void setNome(String nome) {
		
		this.nome = nome;
	}
	
	void stampaNomeUppercase() {
		
		System.out.println(nome.toUpperCase());
	}
	
	protected void stampaNomeLowercase() {
		
		System.out.println(nome.toLowerCase());
	}
	
	private void stampaNome() {
		
		System.out.println(nome);
	}
}

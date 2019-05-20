package model;

public class Pais {
	private String nome;
	private String coi;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCoi() {
		return coi;
	}

	public void setCoi(String coi) {
		this.coi = coi;
	}
	
	@Override
	public String toString() {
		return this.coi;
	}
}

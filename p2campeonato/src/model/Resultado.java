package model;

public class Resultado {	
private int id_atleta;
private String nome_atleta;
private String sexo_atleta;
private String coi;
private String id_prova;
private String marca;
private String data;

public int getId_atleta() {
	return id_atleta;
}
public void setId_atleta(int id_atleta) {
	this.id_atleta = id_atleta;
}
public String getNome_atleta() {
	return nome_atleta;
}
public void setNome_atleta(String nome_atleta) {
	this.nome_atleta = nome_atleta;
}
public String getSexo_atleta() {
	return sexo_atleta;
}
public void setSexo_atleta(String sexo_atleta) {
	this.sexo_atleta = sexo_atleta;
}
public String getCoi() {
	return coi;
}
public void setCoi(String coi) {
	this.coi = coi;
}
public String getId_prova() {
	return id_prova;
}
public void setId_prova(String id_prova) {
	this.id_prova = id_prova;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}

}

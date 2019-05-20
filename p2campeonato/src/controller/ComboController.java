package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Atleta;
import model.Pais;
import model.Prova;
import persistence.ComboDao;

public class ComboController {
	
	JComboBox<String> cBoxSexoAt;
	JComboBox<Pais> cBoxPaisAt;
	JComboBox<Atleta> cBoxAtleta;
	JComboBox<Prova> cBoxProvas;
	JComboBox<String> cBoxBateria;
	JComboBox<Prova> cBoxProvasResult;
	JComboBox<Prova> cBoxProvasRanking;
	
	public ComboController(JComboBox<String> cBoxSexoAt, JComboBox<Pais> cBoxPaisAt, JComboBox<Atleta> cBoxAtleta, 
						   JComboBox<Prova> cBoxProvas, JComboBox<String> cBoxBateria,JComboBox<Prova> cBoxProvasResult, JComboBox<Prova> cBoxProvasRanking) {
		this.cBoxAtleta = cBoxAtleta;
		this.cBoxPaisAt = cBoxPaisAt;
		this.cBoxProvas = cBoxProvas;
		this.cBoxSexoAt = cBoxSexoAt;
		this.cBoxBateria = cBoxBateria;
		this.cBoxProvasResult=cBoxProvasResult;
		this.cBoxProvasRanking=cBoxProvasRanking;
	}
	
	public void ListarPaises(){
		try {
			List<Pais> listaPaises = ComboDao.ConsultarPaises();
			if(cBoxPaisAt.getItemCount()>0){
				cBoxPaisAt.removeAllItems();
			}
			if(listaPaises!=null){
				for (Pais pais : listaPaises) {
					cBoxPaisAt.addItem(pais);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar a conexão!\nERRO: " 
			+ e.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void ListarProvas(){
		try {
			List<Prova> listaProvas = ComboDao.ConsultarProvas();
			if(cBoxProvas.getItemCount()>0){
				cBoxProvas.removeAllItems();
			}
			if(listaProvas!=null){
				for (Prova prova : listaProvas) {
					cBoxProvas.addItem(prova);
				}
			}
			
			
			if(cBoxProvasResult.getItemCount()>0){
				cBoxProvasResult.removeAllItems();
			}
			if(listaProvas!=null){
				for (Prova prova : listaProvas) {
					cBoxProvasResult.addItem(prova);
				}
			}
			
			if(cBoxProvasRanking.getItemCount()>0){
				cBoxProvasRanking.removeAllItems();
			}
			if(listaProvas!=null){
				for (Prova prova : listaProvas) {
					cBoxProvasRanking.addItem(prova);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar a conexão!\nERRO: " 
			+ e.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void ListarAtletas(){
		try {
			List<Atleta> listaAtletas = ComboDao.ConsultarAtletas();
			if(cBoxAtleta.getItemCount()>0){
				cBoxAtleta.removeAllItems();
			}
			if(listaAtletas!=null){
				for (Atleta atleta : listaAtletas) {
					cBoxAtleta.addItem(atleta);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao iniciar a conexão!\nERRO: " 
			+ e.getMessage(), "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void ListarSexoAt(){
		cBoxSexoAt.addItem("Masculino");
		cBoxSexoAt.addItem("Feminino");
	}
	
	public void ListarBateria(){
		cBoxBateria.addItem("1");
		cBoxBateria.addItem("2");
		cBoxBateria.addItem("3");
		cBoxBateria.addItem("4");
		cBoxBateria.addItem("5");
		cBoxBateria.addItem("6");
	}
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Atleta;
import model.Pais;
import model.Prova;
import persistence.AtletaDao;
import persistence.FaseDao;
import persistence.MarcaDao;
import persistence.PaisDao;
import persistence.ProvaDao;
import view.TabelaFases;
import view.TabelaRanking;

public class ControleCampeonato implements ActionListener{
	
	static JPanel panelResultado;
	static JPanel panelEvento;
	static JPanel panelMundial;
	static JTextField tfNomePais;
	static JTextField tfCoiPais;
	static JTextField tfNomeAt;
	static JTextField tfMarca;
	static JTextField tfNomeProva;
	static JTextField tfSexoProva;
	static JComboBox<String> cBoxBateria;
	static JComboBox<String> cBoxSexoAt;
	static JComboBox<Pais> cBoxPaisAt;
	static JComboBox<Atleta> cBoxAtleta;
	static JComboBox<Prova> cBoxProvas;
	static JComboBox<Prova> cBoxProvasResult;
	static JComboBox<Prova> cBoxProvasRanking;
	static JRadioButton rdbtnFaseInicial, rdbtnFaseFinal,  rdbtnTempo,  rdbtnDistancia;
	static JLabel lblBateria;
	
	public ControleCampeonato(JComboBox<String> cBoxSexoAt, JComboBox<Pais> cBoxPaisAt, JComboBox<Atleta> cBoxAtleta, 
							  JComboBox<Prova> cBoxProvas, JTextField tfNomePais, JTextField tfCoiPais, JTextField tfNomeAt,
							  JTextField tfMarca, JComboBox<String> cBoxBateria, JTextField tfNomeProva, JTextField tfSexoProva, 
							  JRadioButton rdbtnFaseInicial, JRadioButton rdbtnFaseFinal, 
							  JRadioButton rdbtnTempo, JRadioButton rdbtnDistancia, JLabel lblBateria, JComboBox<Prova> cBoxProvasResult, 
							  JComboBox<Prova> cBoxProvasRanking, JPanel panelResultado, JPanel panelEvento, JPanel panelMundial) {
		ControleCampeonato.cBoxAtleta = cBoxAtleta;
		ControleCampeonato.cBoxPaisAt = cBoxPaisAt;
		ControleCampeonato.cBoxProvas = cBoxProvas;
		ControleCampeonato.cBoxSexoAt = cBoxSexoAt;
		ControleCampeonato.cBoxProvasResult= cBoxProvasResult;
		ControleCampeonato.cBoxProvasRanking= cBoxProvasRanking;
		ControleCampeonato.cBoxBateria = cBoxBateria;
		ControleCampeonato.tfCoiPais = tfCoiPais;
		ControleCampeonato.tfMarca = tfMarca;
		ControleCampeonato.tfNomeAt = tfNomeAt;
		ControleCampeonato.tfNomePais = tfNomePais;
		ControleCampeonato.tfNomeProva = tfNomeProva;
		ControleCampeonato.tfSexoProva = tfSexoProva;
		ControleCampeonato.rdbtnDistancia = rdbtnDistancia;
		ControleCampeonato.rdbtnFaseFinal = rdbtnFaseFinal;
		ControleCampeonato.rdbtnFaseInicial = rdbtnFaseInicial;
		ControleCampeonato.rdbtnTempo = rdbtnTempo;
		ControleCampeonato.lblBateria = lblBateria;
		ControleCampeonato.panelResultado = panelResultado;
		ControleCampeonato.panelEvento = panelEvento;
		ControleCampeonato.panelMundial = panelMundial;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String cmd = event.getActionCommand();
		
		String fase;
		int bateria;
		
		
		if(rdbtnFaseFinal.isSelected()){
			fase = "final";
			if(rdbtnTempo.isSelected()){
				ListarTempoFinal();
			}else{
				ListarDistanciaFinal();
			}
		}else{
			fase = "inicial";
		}
		
		
		if(cmd.equals("AdicionarPais")){
			try {
				PaisDao.AdicionarPais(tfNomePais.getText(), tfCoiPais.getText());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro durante a inserção da prova", "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else if(cmd.equals("AdicionarProva")){
			try {
				ProvaDao.AdicionarProva(tfNomeProva.getText(), tfSexoProva.getText());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro durante a inserção da prova", "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else if(cmd.equals("AdicionarAtleta")){
			try {
				AtletaDao.AdicionarAtleta(tfNomeAt.getText(), cBoxPaisAt.getSelectedItem().toString(), cBoxSexoAt.getSelectedItem().toString());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro durante a inserção do atleta", "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else if(cmd.equals("EnviarMarca")){
			Atleta a = (Atleta) cBoxAtleta.getSelectedItem();
			Prova p = (Prova) cBoxProvas.getSelectedItem();
			
			if(rdbtnDistancia.isSelected()){
				bateria = Integer.parseInt(cBoxBateria.getSelectedItem().toString());
			}else{
				bateria = 0;
			}
			
			try {
				MarcaDao.AdicionarMarca(a.getId(), p.getId(), bateria, tfMarca.getText(), fase);
				
				tfMarca.setText(null);
			} catch (NumberFormatException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro durante a inserção do atleta", "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		}else if(cmd.equals("Busca_provaresult")){
			Prova p = (Prova) cBoxProvasResult.getSelectedItem();	
			TabelaFases.ChamarTblFase_Inicial(panelResultado, p.getId());	
			TabelaFases.ChamarTblFase_Final(panelResultado, p.getId());
			
		}else if(cmd.equals("BuscaRanking")){
			Prova p = (Prova) cBoxProvasRanking.getSelectedItem();
			TabelaRanking.ChamarTblEvento(panelEvento, p.getId());
			TabelaRanking.ChamarTblMundial(panelMundial, p.getId());
		}
		
		
		
		
		if(rdbtnDistancia.isSelected()){
			bateria = Integer.parseInt(cBoxBateria.getSelectedItem().toString());
			cBoxBateria.setVisible(true);
			lblBateria.setVisible(true);
		}else{
			bateria = 0;
			cBoxBateria.setVisible(false);
			lblBateria.setVisible(false);
		}
		
	}
	
	public static void ListarCombos(){
		ComboController comb = new ComboController(cBoxSexoAt, cBoxPaisAt, cBoxAtleta, cBoxProvas, cBoxBateria, cBoxProvasResult, cBoxProvasRanking);
		comb.ListarSexoAt();
		comb.ListarPaises();
		comb.ListarProvas();
		comb.ListarAtletas();
		comb.ListarBateria();
	}
	
	public static void ListarTempoFinal(){
		try {
			List<Atleta> listaAtletas = FaseDao.ConsultarTempoFinal();
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
	
	public static void ListarDistanciaFinal(){
		try {
			List<Atleta> listaAtletas = FaseDao.ConsultarDistanciaFinal();
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
	
}

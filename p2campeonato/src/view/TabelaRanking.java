package view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Resultado;
import persistence.FaseDao;

public class TabelaRanking {

	/**
	 * @param cBoxProvasResult 
	 * @wbp.parser.entryPoint
	 */
	public static void ChamarTblEvento(JPanel panelevento, int id) {
		try {
			List<Resultado> listaRanking = FaseDao.ConsultarRanking("evento", id);

			String[] coluna = { "ID ", "nome", "sexo", "coi", "marca", "data" };
			String dado[][] = new String[listaRanking.size()][6];

			for (int linha = 0; linha < listaRanking.size(); linha++) {
				dado[linha][0] = Integer.toString(listaRanking.get(linha).getId_atleta());
				dado[linha][1] = listaRanking.get(linha).getNome_atleta();
				dado[linha][2] = listaRanking.get(linha).getSexo_atleta();
				dado[linha][3] = listaRanking.get(linha).getCoi();
				dado[linha][4] = listaRanking.get(linha).getMarca();
				dado[linha][5] = listaRanking.get(linha).getData();
			}

			JTable tblmundial = new JTable(dado, coluna);
			tblmundial.setBounds(50, 50, 550, 470);


			panelevento.add(tblmundial);

			tblmundial.setPreferredScrollableViewportSize(tblmundial.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblmundial);
			scrollMain.add(tblmundial.getTableHeader());
			scrollMain.setBounds(50, 50, 550, 470);

			panelevento.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ChamarTblMundial(JPanel panelmundial, int id) {
		try {
			List<Resultado> listaRanking = FaseDao.ConsultarRanking("mundial", id);

			String[] coluna = { "ID ", "nome", "sexo", "coi", "marca", "data" };
			String dado[][] = new String[listaRanking.size()][6];

			for (int linha = 0; linha < listaRanking.size(); linha++) {
				dado[linha][0] = Integer.toString(listaRanking.get(linha).getId_atleta());
				dado[linha][1] = listaRanking.get(linha).getNome_atleta();
				dado[linha][2] = listaRanking.get(linha).getSexo_atleta();
				dado[linha][3] = listaRanking.get(linha).getCoi();
				dado[linha][4] = listaRanking.get(linha).getMarca();
				dado[linha][5] = listaRanking.get(linha).getData();
			}


			JTable tblmundial = new JTable(dado, coluna);
			tblmundial.setBounds(50, 50, 550, 470);

			panelmundial.add(tblmundial);

			tblmundial.setPreferredScrollableViewportSize(tblmundial.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblmundial);
			scrollMain.add(tblmundial.getTableHeader());
			scrollMain.setBounds(50, 50, 550, 470);

			panelmundial.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

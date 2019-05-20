package view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Atleta;
import model.Prova;
import persistence.ComboDao;

public class TabelaAtleta {

	public static void ChamarTblAtleta(JPanel panelAtleta) {
		try {
			List<Atleta> listaAtletas = ComboDao.ConsultarAtletas();

			String[] coluna = { "ID", "Nome", "Sexo"};
			String dado[][] = new String[listaAtletas.size()][3];

			for (int linha = 0; linha<listaAtletas.size() ; linha++) {
				dado[linha][0] = Integer.toString(listaAtletas.get(linha).getId());
				dado[linha][1] = listaAtletas.get(linha).getNome();
				dado[linha][2] = listaAtletas.get(linha).getCoi();
				dado[linha][2] = listaAtletas.get(linha).getSexo();
			}

			JTable tblpaises = new JTable(dado, coluna);
			tblpaises.setBounds(100, 80, 429, 470);
			panelAtleta.add(tblpaises);

			tblpaises.setPreferredScrollableViewportSize(tblpaises.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblpaises);
			scrollMain.add(tblpaises.getTableHeader());
			scrollMain.setBounds(300, 70, 500, 470);

			panelAtleta.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

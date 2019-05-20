package view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Pais;
import persistence.ComboDao;

public class TabelaPaises {

	public static void ChamarTblPaises(JPanel panelPais) {
		try {
			List<Pais> listaPaises = ComboDao.ConsultarPaises();

			String[] coluna = { "Nome", "Coi"};
			String dado[][] = new String[listaPaises.size()][3];

			for (int linha = 0; linha<listaPaises.size() ; linha++) {
				dado[linha][0] = listaPaises.get(linha).getNome();
				dado[linha][1] = listaPaises.get(linha).getCoi();
			}

			JTable tblpaises = new JTable(dado, coluna);
			tblpaises.setBounds(300, 80, 429, 470);
			panelPais.add(tblpaises);

			tblpaises.setPreferredScrollableViewportSize(tblpaises.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblpaises);
			scrollMain.add(tblpaises.getTableHeader());
			scrollMain.setBounds(300, 70, 500, 470);

			panelPais.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

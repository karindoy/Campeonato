package view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Prova;
import persistence.ComboDao;

public class TabelaProva {

	public static void ChamarTblProva(JPanel panelProva) {
		try {
			List<Prova> listaProvas = ComboDao.ConsultarProvas();

			String[] coluna = { "Nome", "Sexo"};
			String dado[][] = new String[listaProvas.size()][3];

			for (int linha = 0; linha<listaProvas.size() ; linha++) {
				dado[linha][0] = listaProvas.get(linha).getNome();
				dado[linha][1] = listaProvas.get(linha).getSexo();
			}

			JTable tblpaises = new JTable(dado, coluna);
			tblpaises.setBounds(300, 80, 429, 470);
			panelProva.add(tblpaises);

			tblpaises.setPreferredScrollableViewportSize(tblpaises.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblpaises);
			scrollMain.add(tblpaises.getTableHeader());
			scrollMain.setBounds(300, 70, 500, 470);

			panelProva.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

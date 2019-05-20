package view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Resultado;
import persistence.FaseDao;

public class TabelaFases {

	/**
	 * @param cBoxProvasResult 
	 * @wbp.parser.entryPoint
	 */
	public static void ChamarTblFase_Inicial(JPanel panelResultado, int id) {
		try {
	
			List<Resultado> listaResultado = FaseDao.ConsultarResult("inicial", id);

			String[] coluna = { "ID Atleta", "marca" };
			String dado[][] = new String[listaResultado.size()][2];

			for (int linha = 0; linha < listaResultado.size(); linha++) {
				dado[linha][0] = Integer.toString(listaResultado.get(linha).getId_atleta());
				dado[linha][1] = listaResultado.get(linha).getMarca();
			}

			JTable tblpaises = new JTable(dado, coluna);
			tblpaises.setBounds(50, 100, 350, 470);


			panelResultado.add(tblpaises);

			tblpaises.setPreferredScrollableViewportSize(tblpaises.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblpaises);
			scrollMain.add(tblpaises.getTableHeader());
			scrollMain.setBounds(50, 100, 350, 470);

			panelResultado.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ChamarTblFase_Final(JPanel panelResultado, int id) {
		try {
			List<Resultado> listaResultado = FaseDao.ConsultarResult("final", id);

			String[] coluna = { "ID Atleta", "marca" };
			String dado[][] = new String[listaResultado.size()][2];

			for (int linha = 0; linha < listaResultado.size(); linha++) {
				dado[linha][0] = Integer.toString(listaResultado.get(linha).getId_atleta());
				dado[linha][1] = listaResultado.get(linha).getMarca();
			}

			JTable tblFase_Final = new JTable(dado, coluna);
			tblFase_Final.setBounds(410, 100, 350, 470);

			panelResultado.add(tblFase_Final);

			tblFase_Final.setPreferredScrollableViewportSize(tblFase_Final.getPreferredSize());

			JScrollPane scrollMain = new JScrollPane(tblFase_Final);
			scrollMain.add(tblFase_Final.getTableHeader());
			scrollMain.setBounds(410, 100, 350, 470);

			panelResultado.add(scrollMain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class MarcaDao {
	public static void AdicionarMarca(int id_atleta, int id_prova, int bateria, String resultado, String fase) throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		
		String sql = "{CALL p_insere_fase(?,?,?,?,?)}";
		CallableStatement cs = c.conexao.prepareCall(sql);
		
		cs.setInt(1, id_atleta);
		cs.setInt(2, id_prova);
		cs.setInt(3, bateria);
		cs.setString(4, resultado);
		cs.setString(5, fase);
		cs.execute();
		
	}
}

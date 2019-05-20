package persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class AtletaDao {
	public static void AdicionarAtleta(String nome, String coi, String sexo) throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		
		String sql = "{CALL p_insere_atleta(?,?,?)}";
		CallableStatement cs = c.conexao.prepareCall(sql);
		
		cs.setString(1, nome);
		cs.setString(2, coi);
		cs.setString(3, sexo);
		cs.execute();
	}
}

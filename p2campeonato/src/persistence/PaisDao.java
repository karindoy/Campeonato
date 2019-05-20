package persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class PaisDao {
	public static void AdicionarPais(String nome, String coi) throws SQLException{
		Conexao c = new Conexao(); 
		c.conectar();
		
		String sql = "{CALL p_insere_paises(?,?)}";
		CallableStatement cs = c.conexao.prepareCall(sql);
		
		cs.setString(1, nome);
		cs.setString(2, coi);
		
		cs.execute();
	}
}

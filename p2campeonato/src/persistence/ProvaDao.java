package persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ProvaDao {
	public static void AdicionarProva(String nome, String sexo) throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		String sql = "{CALL p_insere_prova(?,?)}";
		CallableStatement cs =c.conexao.prepareCall(sql);
		
		cs.setString(1, nome);
		cs.setString(2, sexo);
		
		cs.execute();
	}
}

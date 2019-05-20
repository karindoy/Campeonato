package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atleta;
import model.Pais;
import model.Prova;

public class ComboDao {
	public static List<Pais> ConsultarPaises() throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		
		List<Pais> listaPaises = new ArrayList<Pais>();
		String sql = "SELECT nome, coi FROM paises";

		PreparedStatement ps = c.conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Pais p = new Pais();
			p.setNome(rs.getString("nome"));
			p.setCoi(rs.getString("coi"));
			listaPaises.add(p);
		}
		
		return listaPaises;
	}
	
	public static List<Prova> ConsultarProvas() throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		
		List<Prova> listaProvas = new ArrayList<Prova>();
		String sql = "SELECT id, nome, sexo FROM prova";
		
		PreparedStatement ps = c.conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Prova p = new Prova();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setSexo(rs.getString("sexo"));
			listaProvas.add(p);
		}
		return listaProvas;
	}
	
	
	public static List<Atleta> ConsultarAtletas() throws SQLException{
		Conexao c = new Conexao();
		c.conectar();
		
		List<Atleta> listaAtletas = new ArrayList<Atleta>();
		String sql = "SELECT id, nome, coi, sexo FROM atleta";
		
		PreparedStatement ps = c.conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Atleta a = new Atleta();
			a.setId(rs.getInt("id"));
			a.setNome(rs.getString("nome"));
			a.setCoi(rs.getString("coi"));
			a.setSexo(rs.getString("sexo"));
			listaAtletas.add(a);
		}
		
		return listaAtletas;
	}
	
}

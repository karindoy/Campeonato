package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atleta;
import model.Resultado;

public class FaseDao {

	public static List<Atleta> ConsultarTempoFinal() throws SQLException {
		Conexao c = new Conexao();
		c.conectar();

		List<Atleta> listaAtletas = new ArrayList<Atleta>();
		StringBuilder sql = new StringBuilder();
		sql.append("select top 8 id_atleta, nome_atleta, coi from fase_inicial ");
		sql.append("where tempo is not null ");
		sql.append("order by tempo asc");

		PreparedStatement ps = c.conexao.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Atleta a = new Atleta();
			a.setId(rs.getInt("id_atleta"));
			a.setNome(rs.getString("nome_atleta"));
			a.setCoi(rs.getString("coi"));
			listaAtletas.add(a);
		}

		return listaAtletas;
	}

	public static List<Atleta> ConsultarDistanciaFinal() throws SQLException {
		Conexao c = new Conexao();
		c.conectar();

		List<Atleta> listaAtletas = new ArrayList<Atleta>();
		StringBuilder sql = new StringBuilder();
		sql.append("select top 8 fi.id_atleta, fi.nome_atleta, fi.coi, res.distancia ");
		sql.append("from fase_inicial fi inner join resultado res ");
		sql.append("on fi.id_atleta = res.id_atleta and fi.id_prova = res.id_prova ");
		sql.append("where res.distancia is not null ");
		sql.append("order by res.distancia desc ");

		PreparedStatement ps = c.conexao.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Atleta a = new Atleta();
			a.setId(rs.getInt("id_atleta"));
			a.setNome(rs.getString("nome_atleta"));
			a.setCoi(rs.getString("coi"));
			listaAtletas.add(a);
		}

		return listaAtletas;
	}

	public static List<Resultado> ConsultarResult(String fase, int idprova) throws SQLException {
		Conexao c = new Conexao();
		c.conectar();

		List<Resultado> listaResultado = new ArrayList<Resultado>();
		String sql = "select * from f_resultado(?) where id_prova = ? order by marca ";

		PreparedStatement ps = c.conexao.prepareStatement(sql);
		ps.setString(1, fase);
		ps.setInt(2, idprova);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Resultado r = new Resultado();
			r.setId_atleta(rs.getInt("id_atleta"));
			r.setId_prova(rs.getString("id_prova"));
			r.setMarca(rs.getString("marca"));
			listaResultado.add(r);
		}
		return listaResultado;
	}

	public static List<Resultado> ConsultarRanking(String ranking, int idprova) throws SQLException {
		Conexao c = new Conexao();
		c.conectar();
		System.out.println(idprova);
		List<Resultado> listaResultado = new ArrayList<Resultado>();
		String sql = "select * from f_record(?) where id_prova = ? order by marca";

		PreparedStatement ps = c.conexao.prepareStatement(sql);
		ps.setString(1, ranking);
		ps.setInt(2, idprova);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Resultado r = new Resultado();
			r.setId_atleta(rs.getInt("id_atleta"));
			r.setNome_atleta(rs.getString("nome_atleta"));
			r.setSexo_atleta(rs.getString("sexo_atleta"));
			r.setCoi(rs.getString("coi"));
			r.setId_prova(rs.getString("id_prova"));
			r.setMarca(rs.getString("marca"));
			r.setData(rs.getString("data"));
			listaResultado.add(r);
		}
		return listaResultado;
	}
}

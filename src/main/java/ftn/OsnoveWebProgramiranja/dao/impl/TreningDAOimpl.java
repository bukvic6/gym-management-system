package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import ftn.OsnoveWebProgramiranja.dao.TreningDAO;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.TipKorisnika;
import ftn.OsnoveWebProgramiranja.model.TipTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;

@Repository
public class TreningDAOimpl implements TreningDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private class TreningRowCallBackHandler implements RowCallbackHandler{
		private Map<Long, Trening> treninzi = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException{
			int index = 1;
			Long id = resultSet.getLong(index++);
			String naziv = resultSet.getString(index++);
			String opis = resultSet.getString(index++);
			String cena = resultSet.getString(index++);
			String vrstaTr = resultSet.getString(index++);
			VrstaTreninga vrstaTreninga = VrstaTreninga.valueOf(vrstaTr);
			String nivoTr = resultSet.getString(index++);
			NivoTreninga nivoTreninga = NivoTreninga.valueOf(nivoTr);
			Integer trajanjeTreninga = resultSet.getInt(index++);
			Integer prosecnaOcena = resultSet.getInt(index++);
			
			
			Trening trening = treninzi.get(id);
			if(trening == null) {
				trening = new Trening(id,naziv,opis,cena,vrstaTreninga,nivoTreninga,trajanjeTreninga,prosecnaOcena);
				treninzi.put(trening.getId(), trening); // dodavanje u kolekciju

			}
			
			
		}
		public List<Trening> getTreninzi(){
			return new ArrayList<>(treninzi.values());
			
		}
	}
	@Override
	public List<Trening> findAll() {
		String sql = "select * from treninzi";
		TreningRowCallBackHandler rowCallbackHandler = new TreningRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getTreninzi();
	}
	@Transactional
	@Override
	public int save(Trening trening) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO treninzi (naziv, opis, cena, vrstaTr,nivoTr,trajanjeTreninga,prosecnaOcena) VALUES (?, ?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, trening.getNaziv());
				preparedStatement.setString(index++, trening.getOpis());
				preparedStatement.setString(index++, trening.getCena());
				preparedStatement.setString(index++, trening.getVrstaTreninga().toString());
				preparedStatement.setString(index++, trening.getNivoTreninga().toString());
				preparedStatement.setInt(index++, trening.getTrajanjeTreninga());
				preparedStatement.setInt(index++, trening.getProsecnaOcena());

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	


	
}

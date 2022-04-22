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

import ftn.OsnoveWebProgramiranja.dao.SalaDAO;
import ftn.OsnoveWebProgramiranja.model.Sala;

@Repository
public class SalaDAOimpl implements SalaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private class SalaRowCallBackHandler implements RowCallbackHandler{

		private Map<Long, Sala> sale = new LinkedHashMap<>();
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			Integer kapacitet = rs.getInt(index++);
			
			Sala sala = sale.get(id);
			if(sala == null) {
				sala = new Sala(id,kapacitet);
				sale.put(sala.getId(), sala);
			}
			
		}
		public List<Sala> getSale(){
			return new ArrayList<>(sale.values());
		}
		
	}
	@Override
	public List<Sala> findAll(){
		String sql = "select * from sala";
		SalaRowCallBackHandler rowCallbackHandler = new SalaRowCallBackHandler();
		jdbcTemplate.query(sql,rowCallbackHandler);
		
		return rowCallbackHandler.getSale();
		
	}
	@Transactional
	@Override
	public int save(Sala sala) {
	PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO sala (kapacitet) VALUES (?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setInt(index++,sala.getKapacitet());
				return preparedStatement;

	}
	
	};
	GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
	return uspeh?1:0;
}}

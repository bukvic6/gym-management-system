package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.OsnoveWebProgramiranja.dao.TipTreningaDAO;
import ftn.OsnoveWebProgramiranja.dao.TreningDAO;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.TipTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.SalaService;

@Repository
public class TipTreningaDAOimpl implements TipTreningaDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TreningDAO treningDAO;
	
	@Autowired
	private SalaService salaService;
	

    private class TerminRowCallHandler implements RowCallbackHandler{
		private Map<Long, TipTreninga>tipoviTermina = new LinkedHashMap<>();
		
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int index = 1;
		Long id = rs.getLong(index++);
		Long idtrening = rs.getLong(index++);
		Trening trening = treningDAO.findOne(idtrening);

		String ime = rs.getString(index++);
		String opis = rs.getString(index++);
		
		TipTreninga tipTreninga = tipoviTermina.get(id);
		if(tipTreninga == null) {
			tipTreninga = new TipTreninga(id,trening,ime,opis);
			tipoviTermina.put(tipTreninga.getId(),tipTreninga);
		}
		
		
		
		
	}

	public List<TipTreninga> getTipTreninga() {
		return new ArrayList<>(tipoviTermina.values());
	}	
    }


	@Override
	public List<TipTreninga> findAll(Long id) {
		String sql = "SELECT * from tipTreninga WHERE treningId = ?";
		TerminRowCallHandler rowCallbackHandler = new TerminRowCallHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getTipTreninga();
	}
	

}

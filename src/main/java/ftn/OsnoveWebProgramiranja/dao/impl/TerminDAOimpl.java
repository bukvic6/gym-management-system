package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.OsnoveWebProgramiranja.dao.TerminDAO;
import ftn.OsnoveWebProgramiranja.dao.TreningDAO;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.SalaService;

@Repository
public class TerminDAOimpl implements TerminDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TreningDAO treningDAO;
	
	@Autowired
	private SalaService salaService;
	
	private class TerminRowCallHandler implements RowCallbackHandler{
		private Map<Long, TerminTreninga>termini = new LinkedHashMap<>();
		

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			Trening trening = treningDAO.findOne(id);

			Long ids = rs.getLong(index++);
			LocalDateTime vreme = rs.getTimestamp(index++).toLocalDateTime();
			Sala sala = salaService.findOne(ids);
			
			TerminTreninga termin = termini.get(id);
			if(termin == null) {
				termin = new TerminTreninga(trening,sala,vreme);
				termini.put(termin.getTreningId().getId(),termin);
			}
			
			
			
			
		}

		public List<TerminTreninga> getTermini() {
			return new ArrayList<>(termini.values());
		}
		
	}

	@Override
	public int save(TerminTreninga termin) {
		String sql = "INSERT INTO terminTreninga (treningId, salaId, vreme) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql,termin.getTreningId().getId(),termin.getSalaId().getId(), termin.getDatum());
	}
	
	@Override
	public List<TerminTreninga> findAll(Long id) {
		String sql = "select * from terminTreninga where treningId = ?";
		TerminRowCallHandler rowCallbackHandler = new TerminRowCallHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		
		return rowCallbackHandler.getTermini();
	}
	

}

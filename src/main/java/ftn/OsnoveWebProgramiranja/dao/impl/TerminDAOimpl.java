package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.OsnoveWebProgramiranja.dao.TerminDAO;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;

@Repository
public class TerminDAOimpl implements TerminDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class TerminRowCallHandler implements RowCallbackHandler{

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
			Long idTrening = rs.getLong(index++);
			String naziv = rs.getString(index++);
			
			Trening trening = new Trening(idTrening, naziv);
			
			Long idSala = rs.getLong(index++);
			Integer kapacitet = rs.getInt(index++);
			
			Sala sala = new Sala(idSala,kapacitet);
			
			LocalDate vreme = rs.getTimestamp(index++).toLocalDateTime().toLocalDate();

			
			
			TerminTreninga termin = new TerminTreninga(trening, sala, vreme);
			
			
		}
		
	}
	

}

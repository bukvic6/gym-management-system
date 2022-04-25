package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.OsnoveWebProgramiranja.dao.TerminDAO;
import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;

@Repository
public class TerminDAOimpl implements TerminDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class TerminRowCallHandler implements RowCallbackHandler{

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			String naziv = rs.getString(index++);
			String opis = rs.getString(index++);
			String cena = rs.getString(index++);
			String vrstaTr = rs.getString(index++);
			VrstaTreninga vrstaTreninga = VrstaTreninga.valueOf(vrstaTr);
			String nivoTr = rs.getString(index++);
			NivoTreninga nivoTreninga = NivoTreninga.valueOf(nivoTr);
			Integer trajanjeTreninga = rs.getInt(index++);
			Integer prosecnaOcena = rs.getInt(index++);
			String trener = rs.getString(index++);

			Trening trening = new Trening(id,naziv,opis,cena,vrstaTreninga,nivoTreninga,trajanjeTreninga,prosecnaOcena, trener);
		
			Long ids = rs.getLong(index++);
			Integer kapacitet = rs.getInt(index++);
			Sala sala = new Sala(ids, kapacitet);
			LocalDateTime vreme = rs.getTimestamp(index++).toLocalDateTime();
	

				
			TerminTreninga termin = new TerminTreninga(trening, sala, vreme);
			
			
		}
		
	}

	@Override
	public int save(TerminTreninga termin) {
		String sql = "INSERT INTO terminTreninga (treningId, salaId, vreme) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql,termin.getTreningId().getId(),termin.getSalaId().getId(), termin.getDatum());
	}
	

}

package ftn.OsnoveWebProgramiranja.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ftn.OsnoveWebProgramiranja.dao.KorisnikDAO;
import ftn.OsnoveWebProgramiranja.dao.KorpaDAO;
import ftn.OsnoveWebProgramiranja.dao.TerminDAO;
import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;

@Repository
public class KorpaDAOImpl implements KorpaDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private TerminDAO terminDAO;
	
	private class KorpaRowCallHandler implements RowCallbackHandler{
		private Map<Long, KorisnickaKorpa>korKorpa = new LinkedHashMap<>();
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		int index = 1;
		Long id = rs.getLong(index++);
		Long idKorisnika = rs.getLong(index++);
		Korisnik korisnik = korisnikDAO.findOne(idKorisnika);
		
		Long idTermina = rs.getLong(index++);
		TerminTreninga termin = terminDAO.findOne(idTermina);
		
		KorisnickaKorpa korpa = korKorpa.get(id);
		if(korpa == null) {
			korpa = new KorisnickaKorpa(id,korisnik,termin);
			korKorpa.put(korpa.getId(),korpa);
		}
		
	  }
	public List<KorisnickaKorpa>getKorisnickaKorpa(){
		return new ArrayList<>(korKorpa.values());
	   }
	}
	

	@Override
	public int save(KorisnickaKorpa korpa) {
		String sql = "Insert into korisnickaKorpa (korisnikId,terminId) VALUES(?,?)";
		return jdbcTemplate.update(sql, korpa.getKorisnikId().getId(), korpa.getTerminId().getId());
		
	}


	@Override
	public List<KorisnickaKorpa> findKorpa(Long id) {
		String sql = "select * from korisnickaKorpa where korisnikId = ?";
		KorpaRowCallHandler rowCallbackHandler = new KorpaRowCallHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		
		return rowCallbackHandler.getKorisnickaKorpa();
	}


	@Override
	public KorisnickaKorpa findOne(Long id) {
		String sql = 
				"SELECT * from korisnickaKorpa WHERE id = ?";

		KorpaRowCallHandler rowCallbackHandler = new KorpaRowCallHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		
		return rowCallbackHandler.getKorisnickaKorpa().get(0);
		
	}
	@Override
	public int deleteZakazano(Long id) {
		String sql = "delete from korisnickaKorpa where id = ?";
	
		return jdbcTemplate.update(sql, id);
	}
	@Override
	public KorisnickaKorpa sum(Long id) {
		String sql = "SELECT sum(tr.cena) from korisnickaKorpa k left join terminTreninga t on k.terminId = t.id left join treninzi tr on t.treningId = tr.id where k.korisnikId = ?";
			

				KorpaRowCallHandler rowCallbackHandler = new KorpaRowCallHandler();
				jdbcTemplate.query(sql, rowCallbackHandler, id);

				
				return rowCallbackHandler.getKorisnickaKorpa().get(0);
	}

}

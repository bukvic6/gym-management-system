package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;


import ftn.OsnoveWebProgramiranja.dao.KomentarDAO;
import ftn.OsnoveWebProgramiranja.model.Komentar;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.Status;
import ftn.OsnoveWebProgramiranja.model.TipKorisnika;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;


@Repository
public class KomentarDAOimpl implements KomentarDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class KomentarRowHandler implements RowCallbackHandler{
		private Map<Long, Komentar> komentari = new LinkedHashMap<>();

		@Override
		public void processRow(ResultSet rs) throws SQLException{
			int index = 1;
			Long id = rs.getLong(index++);
			String textKomentara = rs.getString(index++);
			Integer ocena = rs.getInt(index++);
			LocalDate datum = rs.getTimestamp(index++).toLocalDateTime().toLocalDate();
			String statusKom =rs.getString(index++);
			Status status = Status.valueOf(statusKom);
			
			
			Long idKorisnika = rs.getLong(index++);
			String korisnickoIme = rs.getString(index++);
			String ime = rs.getString(index++);
			String prezime = rs.getString(index++);
			String email = rs.getString(index++);
			String lozinka = rs.getString(index++);
			String datRodj = rs.getString(index++);
			
			String brojTelefona = rs.getString(index++);
			LocalDate vremeRegistracije = rs.getTimestamp(index++).toLocalDateTime().toLocalDate();
			
			String tip = rs.getString(index++);
			TipKorisnika tipKorisnika = TipKorisnika.valueOf(tip);
			String adresa = rs.getString(index++);
			boolean aktivan = rs.getBoolean(index++);
			Korisnik autor = new Korisnik(idKorisnika,korisnickoIme,ime,prezime,email,lozinka,datRodj,brojTelefona,vremeRegistracije,tipKorisnika,adresa,aktivan);
			
		
			Long idtre = rs.getLong(index++);
//			String naziv = rs.getString(index++);
//			String opis = rs.getString(index++);
//			String cena = rs.getString(index++);
//			String vrstaTr = rs.getString(index++);
//			VrstaTreninga vrstaTreninga = VrstaTreninga.valueOf(vrstaTr);
//			String nivoTr = rs.getString(index++);
//			NivoTreninga nivoTreninga = NivoTreninga.valueOf(nivoTr);
//			Integer trajanjeTreninga = rs.getInt(index++);
//			Integer prosecnaOcena = rs.getInt(index++);
//			String trener = rs.getString(index++);

			Trening trening = new Trening(idtre);
		
			
			boolean anoniman = rs.getBoolean(index++);
			
			Komentar komentar = komentari.get(id);
			if(komentar == null) {
			
			komentar = new Komentar(id,textKomentara,ocena,datum,status,autor,trening,anoniman);
			komentari.put(komentar.getId(), komentar);

		}
	}
		public List<Komentar> getKomentari(){
			return new ArrayList<>(komentari.values());
			
		}

		
	}
	
	@Override
	public List<Komentar> findAll(){
		String sql = "select * from komentari";	
		KomentarRowHandler rowCallbackHandler = new KomentarRowHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getKomentari();
	}
	@Override
	public int save(Komentar komentar) {
		String sql = "insert into komentari (textKomentara, ocena, datum, statusKomentara, autor, trening, anoniman) values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, komentar.getTextKomentara(), komentar.getOcena(),
				komentar.getDatum(), komentar.getStatus().toString(), komentar.getAutor().getId()
				,komentar.getTrening().getId(),komentar.isAnoniman());
	}

}

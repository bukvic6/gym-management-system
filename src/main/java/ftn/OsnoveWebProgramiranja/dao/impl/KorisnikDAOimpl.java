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

import ftn.OsnoveWebProgramiranja.dao.KorisnikDAO;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.TipKorisnika;


@Repository
public class KorisnikDAOimpl implements KorisnikDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private class KorisnikRowCallBackHandler implements RowCallbackHandler{
		private Map<Long, Korisnik> korisnici = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException{
			int index = 1;
			Long id = resultSet.getLong(index++);
			String korisnickoIme = resultSet.getString(index++);
			String ime = resultSet.getString(index++);
			String prezime = resultSet.getString(index++);
			String email = resultSet.getString(index++);
			String lozinka = resultSet.getString(index++);
			String datRodj = resultSet.getString(index++);
			
			String brojTelefona = resultSet.getString(index++);
			String vremeRegistracije = resultSet.getString(index++);
			String tip = resultSet.getString(index++);
			TipKorisnika tipKorisnika = TipKorisnika.valueOf(tip);
			String adresa = resultSet.getString(index++);
			
			
			Korisnik korisnik = korisnici.get(id);
			if(korisnik == null) {
				korisnik = new Korisnik(id,korisnickoIme,ime,prezime,email,lozinka,
						datRodj,adresa,brojTelefona,vremeRegistracije,tipKorisnika);
				korisnici.put(korisnik.getId(), korisnik); // dodavanje u kolekciju

			}
			
			
		}
		public List<Korisnik> getKorisnici(){
			return new ArrayList<>(korisnici.values());
			
		}

		
	}

	@Override
	public Korisnik findOne(String email, String sifra) {
		String sql = 
				"SELECT * from korisnici  " + 
						"WHERE email = ? AND " +
						"lozinka = ? ";

		KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, email, sifra);

		if(rowCallbackHandler.getKorisnici().size() == 0) {
			return null;
		}
		
		return rowCallbackHandler.getKorisnici().get(0);
	}

	@Override
	public List<Korisnik> findAll() {
		String sql = "select * from korisnici";
		KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getKorisnici();
	}
	@Transactional
	@Override
	public int save(Korisnik korisnik) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO korisnici (korisnickoIme, ime, prezime, email,lozinka,datRodj,brojTelefona,vremeRegistracije,tipKorisnika,adresa) VALUES (?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, korisnik.getKorisnickoIme());
				preparedStatement.setString(index++, korisnik.getIme());
				preparedStatement.setString(index++, korisnik.getPrezime());
				preparedStatement.setString(index++, korisnik.getEmail());
				preparedStatement.setString(index++, korisnik.getLozinka());
				preparedStatement.setString(index++, korisnik.getDatRodj());
				preparedStatement.setString(index++, korisnik.getBrojTelefona());
				preparedStatement.setString(index++, korisnik.getVremeRegistracije());
				preparedStatement.setString(index++, korisnik.getTipKorisnika().toString());
				preparedStatement.setString(index++, korisnik.getAdresa());
				
				

				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	






}

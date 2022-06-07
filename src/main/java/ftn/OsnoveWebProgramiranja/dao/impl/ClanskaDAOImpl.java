package ftn.OsnoveWebProgramiranja.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.OsnoveWebProgramiranja.dao.ClanskaDAO;
import ftn.OsnoveWebProgramiranja.dao.KorisnikDAO;
import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.Status;
import ftn.OsnoveWebProgramiranja.model.StatusClanske;


@Repository
public class ClanskaDAOImpl implements ClanskaDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	
	private class ClanskaRowCallBackHandler implements RowCallbackHandler{
		private Map<Long, ClanskaKarta> clanskeKarte = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
			Long id = rs.getLong(index++);
			Long idKorisnika = rs.getLong(index++);
			Korisnik korisnik = korisnikDAO.findOne(idKorisnika);
			
			Integer popust = rs.getInt(index++);
			Integer poeni = rs.getInt(index++);
			String statusClanske =rs.getString(index++);
			StatusClanske status = StatusClanske.valueOf(statusClanske);	
			
			ClanskaKarta clanska = clanskeKarte.get(id);
			if(clanska == null) {
				clanska = new ClanskaKarta(id,korisnik,popust,poeni,status);
				clanskeKarte.put(clanska.getId(), clanska);


			}
			
		}
		public List<ClanskaKarta> getClanskeKarte(){
			return new ArrayList<>(clanskeKarte.values());
		}
		
	}


	@Override
	public int save(ClanskaKarta clanska) {
		String sql = "INSERT INTO clanskeKarte (korisnikId, popust, bodovi, statusClanske) VALUES (?, ?,?, ?)";
		return jdbcTemplate.update(sql,clanska.getKorisnikId().getId(),clanska.getPopust(),clanska.getPoeni(), clanska.getStatus().toString());
	}


	@Override
	public List<ClanskaKarta> findAll() {
		String sql = "select * from clanskeKarte where status = 'CEKANJE'";
		ClanskaRowCallBackHandler rowCallbackHandler = new ClanskaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getClanskeKarte();
		
	}
	@Override
	public ClanskaKarta findOne(Long id) {
		String sql = "select * from clanskeKarte where id = ?";
		
		ClanskaRowCallBackHandler backHandler = new ClanskaRowCallBackHandler();
		jdbcTemplate.query(sql, backHandler, id);
		
		return backHandler.getClanskeKarte().get(0);
	}
	@Override
	public int delete(Long id) {
		String sql = "delete from clanskeKarte where id = ?";
		return jdbcTemplate.update(sql, id);
	}


	@Override
	public int odobri(Long id) {
		String sql = "update clanskeKarte set statusClanske = 'ODOBREN' where id = ?";
		
		return jdbcTemplate.update(sql,id);
		
	}

}

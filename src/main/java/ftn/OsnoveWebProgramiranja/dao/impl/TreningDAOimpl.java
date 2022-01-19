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
			String nazivTipa = resultSet.getString(index++);
			String opisTipa = resultSet.getString(index++);
			String cena = resultSet.getString(index++);
			String vrstaTr = resultSet.getString(index++);
			VrstaTreninga vrstaTreninga = VrstaTreninga.valueOf(vrstaTr);
			String nivoTr = resultSet.getString(index++);
			NivoTreninga nivoTreninga = NivoTreninga.valueOf(nivoTr);
			Long trajanjeTreninga = resultSet.getLong(index++);
			Long prosecnaOcena = resultSet.getLong(index++);
			
			TipTreninga tipTreninga = new TipTreninga(nazivTipa, opisTipa);

			
			
			Trening trening = treninzi.get(id);
			if(trening == null) {
				trening = new Trening(id,naziv,opis,tipTreninga,cena,vrstaTreninga,nivoTreninga,trajanjeTreninga,prosecnaOcena);
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
	
	


	
}

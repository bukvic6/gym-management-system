package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.KomentarDAO;
import ftn.OsnoveWebProgramiranja.model.Komentar;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.service.KomentarService;

@Service
public class DatabaseKomentarServiceImpl implements KomentarService {

	@Autowired
	private KomentarDAO komentarDAO;
	
	@Override
	public Komentar save(Komentar komentar) {
		komentarDAO.save(komentar);
		return komentar;
	}
	
	@Override
	public List<Komentar> findAll(Long id) {
		return komentarDAO.findAll(id);
	}
	
	@Override
	public List<Komentar>findAll(){
		return komentarDAO.findAll();
	}

	@Override
	public Komentar findOne(Long id) {
		return komentarDAO.findOne(id);
	}

	/*
	 * @Override public int delete(Long id) { return komentarDAO.delete(id);
	 * 
	 * }
	 */
	@Override
	public Komentar delete(Long id) {
		Komentar komentar = komentarDAO.findOne(id);
		if(komentar != null) {
			komentarDAO.delete(id);
		}
		return komentar;
		
	}
	@Override
	public Komentar odobri(Long id) {
		Komentar komentar = komentarDAO.findOne(id);
		if(komentar != null) {
			komentarDAO.odobri(id);
		}
		return komentar;
		
	}

}

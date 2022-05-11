package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.KomentarDAO;
import ftn.OsnoveWebProgramiranja.model.Komentar;
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

}

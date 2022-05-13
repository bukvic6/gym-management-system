package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Komentar;
import ftn.OsnoveWebProgramiranja.model.Trening;

public interface KomentarDAO {

	int save(Komentar komentar);

	List<Komentar> findAll(Long id);
	
	List<Komentar> findAll();


}

package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Komentar;

public interface KomentarService {

	Komentar save(Komentar komentar);

	List<Komentar> findAll(Long id);
	
	List<Komentar> findAll();

	Komentar findOne(Long id);

	Komentar delete(Long id);
	
	Komentar odobri(Long id);

}

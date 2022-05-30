package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Komentar;

public interface KomentarDAO {

	int save(Komentar komentar);

	List<Komentar> findAll(Long id);
	
	List<Komentar> findAll();

	Komentar findOne(Long id);

	int delete(Long id);

	int odobri(Long id);




}

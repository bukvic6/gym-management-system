package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Komentar;

public interface KomentarDAO {

	int save(Komentar komentar);

	public List<Komentar> findAll();

}

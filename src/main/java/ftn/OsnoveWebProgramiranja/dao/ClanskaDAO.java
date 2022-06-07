package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;

public interface ClanskaDAO {
	int save(ClanskaKarta clanska);

	List<ClanskaKarta> findAll();

	int delete(Long id);

	ClanskaKarta findOne(Long id);

	int odobri(Long id);


}

package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Trening;

public interface TreningDAO {

	List<Trening> findAll();


	public int save(Trening trening);

	Trening findOne(Long treningId);




	Float sum(Long id);

}

package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;
import ftn.OsnoveWebProgramiranja.model.Trening;


public interface TreningService {

	List<Trening> findAll();

	Trening save(Trening trening);

	Trening findOne(Long treningId);
	Float sum(Long id);

}

package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Trening;


public interface TreningService {

	List<Trening> findAll();

	Trening save(Trening trening);

}

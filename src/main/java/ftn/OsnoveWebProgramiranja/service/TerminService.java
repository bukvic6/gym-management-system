package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.TerminTreninga;

public interface TerminService {
	
	TerminTreninga save(TerminTreninga termin);

	List<TerminTreninga> findAll(Long id);

	TerminTreninga findOne(Long id);

	List<TerminTreninga> checkifExist(Long id);

}

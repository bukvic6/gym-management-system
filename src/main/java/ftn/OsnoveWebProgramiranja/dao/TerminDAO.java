package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.TerminTreninga;

public interface TerminDAO {

	int save(TerminTreninga termin);

	List<TerminTreninga> findAll(Long id);

	TerminTreninga findOne(Long id);

	List<TerminTreninga> checkIfExist(Long id);

}

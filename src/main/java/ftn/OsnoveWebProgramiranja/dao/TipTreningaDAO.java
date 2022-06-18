package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.TipTreninga;

public interface TipTreningaDAO {
	
	List<TipTreninga> findAll(Long id);

}

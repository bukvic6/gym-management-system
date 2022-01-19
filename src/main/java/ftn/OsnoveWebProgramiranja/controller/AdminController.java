package ftn.OsnoveWebProgramiranja.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Controller
@RequestMapping(value = "/knjige")
public class AdminController implements ServletContextAware{


	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TreningService treningService;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
//	@GetMapping
//	public ModelAndView index() {
//		List<Korisnik>korisnici = korisnikService.findAll();	
//		// podaci sa nazivom template-a
//		ModelAndView rezultat = new ModelAndView("korisnici"); // naziv template-a
//		rezultat.addObject("korisnici", korisnici); // podatak koji se šalje template-u
//
//		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
//	}

	@GetMapping
	public ModelAndView indexdd() {
		List<Trening> treninzi = treningService.findAll();	
		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("treninzi"); // naziv template-a
		rezultat.addObject("treninzi", treninzi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	

}

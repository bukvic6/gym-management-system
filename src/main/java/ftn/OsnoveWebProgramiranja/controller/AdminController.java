package ftn.OsnoveWebProgramiranja.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Controller
@RequestMapping(value = "/treninzi")
public class AdminController implements ServletContextAware{


	@Autowired
	private ServletContext servletContext;
	private String bURL;
	

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


	@GetMapping
	public ModelAndView indexdd() {
		List<Trening> treninzi = treningService.findAll();	
		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("treninzi"); // naziv template-a
		rezultat.addObject("treninzi", treninzi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
	@GetMapping(value="/add")
	public String create(HttpServletResponse response){
		return "dodajTrening"; // stranica za dodavanje knjige
	}

	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String opis, 
			@RequestParam String cena,@RequestParam String vrstaTreninga,@RequestParam String nivoTreninga, @RequestParam int trajanjeTreninga, @RequestParam int prosecnaOcena,
			HttpServletResponse response) throws IOException {	
		NivoTreninga nivotr = NivoTreninga.valueOf(nivoTreninga);
		VrstaTreninga vrstatr = VrstaTreninga.valueOf(vrstaTreninga);
		Trening trening = new Trening(naziv, opis, cena,nivotr,vrstatr,trajanjeTreninga,prosecnaOcena);
		Trening saved = treningService.save(trening);
		response.sendRedirect(bURL+"treninzi");
	}

}

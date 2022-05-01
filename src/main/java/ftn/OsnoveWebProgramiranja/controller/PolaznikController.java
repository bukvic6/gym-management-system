package ftn.OsnoveWebProgramiranja.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.OsnoveWebProgramiranja.model.Komentar;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.Status;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.KomentarService;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;
import ftn.OsnoveWebProgramiranja.service.TerminService;
import ftn.OsnoveWebProgramiranja.service.TreningService;



@Controller
@RequestMapping(value = "/korisnik")
public class PolaznikController implements ServletContextAware{
	
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private TreningService treningService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	@Autowired 
	private KomentarService komentarService;
	

	
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
	
	@GetMapping
	public ModelAndView index() {
		List<Trening> treninzi = treningService.findAll();

		ModelAndView rezultat = new ModelAndView("korisnik");
		rezultat.addObject("treninzi", treninzi);

		return rezultat;
	}
	@GetMapping(value = "/profil")
	public ModelAndView profil(HttpSession session) {
		Korisnik ulogovani = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Korisnik korisnik = korisnikService.findOne(ulogovani.getId());

		ModelAndView rezultat = new ModelAndView("profil");
		rezultat.addObject("korisnik", korisnik);

		return rezultat;
	}
	
	@GetMapping(value ="/details")
	@ResponseBody
	public ModelAndView details(@RequestParam Long id, HttpServletResponse httpServletResponse) {
		Trening trening = treningService.findOne(id);
		List<Komentar> komentari = komentarService.findAll();
		
		ModelAndView rezultat = new ModelAndView("trening");
		rezultat.addObject("trening",trening);
		rezultat.addObject("komentar",komentari);
		return rezultat;
	}

	
	@PostMapping(value = "/addKomentar")
	public void create(@RequestParam int ocena,@RequestParam String textKomentara,@RequestParam Long id, @RequestParam(required=false) boolean anoniman,HttpServletResponse response,HttpSession session) throws IOException {
		LocalDate datum = LocalDate.now();
		Korisnik ulogovani = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		

		Status status = Status.CEKANJE;
		Trening trening = treningService.findOne(id);
		Komentar komentar = new Komentar(textKomentara,ocena,datum,status,ulogovani,trening, anoniman);
		komentarService.save(komentar);
		response.sendRedirect(bURL + "korisnik");
	}
	

	
	
	

}

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

import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Controller
@RequestMapping(value = "/treninzi")
public class PolaznikController implements ServletContextAware{
	
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private TreningService treningService;
	
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
		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("treninzi"); // naziv template-a
		rezultat.addObject("treninzi", treninzi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
	
	

}

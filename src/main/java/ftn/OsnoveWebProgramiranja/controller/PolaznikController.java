package ftn.OsnoveWebProgramiranja.controller;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;


@Controller
@RequestMapping(value = "/korisnici")
public class PolaznikController implements ServletContextAware{
	
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	

	
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	

	
	
	

}

package sra1.jee.projet.drive.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
	
	@GetMapping(path="/page.html")
	public String home() {
		return "home";
	}

}

package blog.root.controll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(String error,String logout,Model model) {
		
		log.info("error : "+ error);
		log.info("logout : "+logout);
		
		if(error != null) {
			model.addAttribute("error", "login Error Check Your Account");
			
		}
		
		if(logout != null) {
			model.addAttribute("logout", "logout!!");
		}
		
		return "/login";
	}
	
	@PostMapping("/login" )
	public String postLogin() {
		return "main";
	}
	
	
	@GetMapping("/logout")
	public void logoutGet() {
		log.info("custom logout");
	}
}

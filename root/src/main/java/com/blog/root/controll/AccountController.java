package blog.root.controll;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.root.service.RootService;
import blog.root.service.UserService;
import blog.root.service.User_authService;

@Controller
public class AccountController {

	@Inject
	private RootService root;
	
	@Inject
	private User_authService user_auth;
	
	@Inject
	private UserService user;
	
	@GetMapping("/account")
	public String account(Model model) {
		return "/account";
	}
	
	@PutMapping("/account/root")
	@ResponseBody
	public int root( String pwd, int user_number) {
		
		try {
				if(root.lootlogin(pwd) !=0) {
					user_auth.userauth_update_admin(user_number);
				}
				return 1;
		} catch (Exception e) {;;	}
		
		return 0;
	}
}

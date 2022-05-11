package blog.root.controll;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.root.service.UserService;
import blog.root.service.User_authService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SingupController {

	@Inject
	private UserService user;
	@Inject
	private User_authService user_auth;
	
	@GetMapping(value = "/singup")
	public String singup() {
		return "singup";
	}
	
	@PostMapping("/singup")
	@ResponseBody
	public int singupgo(String id,String pw,String email,String nickname) {
		log.info("id : "+id+" pw: "+pw +" email : "+ email +" nickname : "+ nickname);
		try {
			user.Singup(id, pw, email, nickname);
			user_auth.userauth_insert(user.getUser_number(id));
			return 1;
		} catch (Exception e) {;;	}
		
		return 0;
	}
	
	@PostMapping("/singup/checkEmail")
	@ResponseBody
	public int email(String email) {
		
		try {
			if(user.emailCheck(email) != null ) {return 1;}
		}catch (Exception e) {;;}
		
		
		return 0;
	}
	
	@PostMapping("/singup/checknickname")
	@ResponseBody
	public int nickname(String nickname) {
		
		try {
			if(user.nicknameCheck(nickname) != null ) {return 1;}
		}catch (Exception e) {;;}
		
		
		return 0;
	}
	
	
	@PostMapping("/singup/checkid")
	@ResponseBody
	public int id(String id) {
		
		try {
			if(user.idCheck(id) != null ) {return 1;}
		}catch (Exception e) {;;}
		
		
		return 0;
	}
}

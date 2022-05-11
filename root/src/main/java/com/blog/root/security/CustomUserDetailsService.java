package blog.root.security;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import blog.root.model.CustomUser;
import blog.root.model.UserDTO;
import blog.root.service.Login_HistoryService;
import blog.root.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	
	@Inject
	private UserService user;
	
	@Inject
	private Login_HistoryService login;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(" load user by UserName : "+username);
		
		UserDTO dto = user.read(username);
		
		try {
			
			login.loginTimeCheck(user.getUser_number(dto.getId()));
		}
		catch (Exception e) {e.printStackTrace();}
		
		return dto == null ? null : new CustomUser(dto);
	}

}

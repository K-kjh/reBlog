package blog.root.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import blog.root.model.UserDTO;

public class CustomUserDetailsService implements UserDetailsService{

	@Inject
	private UserDTO userDTO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return null;
	}

}

package blog.root.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import blog.root.mapper.User_authMapper;

@Service
public class User_authServiceImpl implements User_authService{

	@Inject
	private User_authMapper mapper;
	
	@Override
	public void userauth_insert(int user_number) {
		
		mapper.userauth_insert(user_number);
	}

	@Override
	public void userauth_update_admin(int user_number) {
		
		mapper.userauth_update_admin(user_number);
	}
	

}

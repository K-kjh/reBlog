package blog.root.service;

import javax.inject.Inject;

import org.apache.ibatis.mapping.ResultMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blog.root.mapper.UserMapper;
import blog.root.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserMapper mapper;
	
	@Inject
	private PasswordEncoder pwencoder;
	
	@Override
	public int Singup(String id, String pw, String email, String nickname) throws Exception {
		return mapper.Singup(id, pwencoder.encode(pw), email, nickname);
	}

	@Override
	public int Login(String id, String pw) throws Exception {
		// TODO Auto-generated method stub
		return mapper.Login(id,pw);
	}

	@Override
	public String nicknameCheck(String nickname) throws Exception {
		// TODO Auto-generated method stub
		return mapper.nicknameCheck(nickname);
	}

	@Override
	public String idCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.idCheck(id);
	}

	@Override
	public String emailCheck(String email) throws Exception {
		return mapper.emailCheck(email);
	}

	@Override
	public int getUser_number(String id) {
		return mapper.getUser_number(id);
	}

	@Override
	public UserDTO read(String id) {
		
		return mapper.read(id);
	}

}

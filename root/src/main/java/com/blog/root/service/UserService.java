package blog.root.service;

import org.apache.ibatis.mapping.ResultMap;

import blog.root.model.UserDTO;

public interface UserService {
	public int Singup(String id, String pw, String email, String nickname) throws Exception;

	public int Login(String id, String pw) throws Exception;
	
	public int getUser_number(String id);

	public String nicknameCheck(String nickname) throws Exception;

	public String idCheck(String id) throws Exception;

	public String emailCheck(String email) throws Exception;
	
	public UserDTO read(String id);
}

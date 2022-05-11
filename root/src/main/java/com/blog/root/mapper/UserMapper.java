package blog.root.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultMap;

import blog.root.model.UserDTO;

public interface UserMapper {

	@Insert("insert into user(id,pw,email,nickname) values(#{id},#{pw},#{email},#{nickname})")
	public int Singup(@Param("id") String id, @Param("pw") String pw, @Param("email") String email,
			@Param("nickname") String nickname) throws Exception;
	// 회원 가입

	@Select("select user_number from user where id=#{id} AND pw =#{pw}")
	public int Login(@Param("id") String id, @Param("pw") String pw) throws Exception;
	// 로그인
	
	@Select("select user_number from user where id=#{id}")
	public int getUser_number(@Param("id")String id) ;

	@Select("select nickname from user where nickname=#{nickname}")
	public String nicknameCheck(@Param("nickname") String nickname) throws Exception;
	// 닉네임 조회

	@Select("select id from user where id=#{id}")
	public String idCheck(String id) throws Exception;
	// 아이디 조회

	@Select("select email from user where email= #{email} ")
	public String emailCheck(@Param("email")String email) throws Exception;
	// 이메일 조회
	
	public UserDTO read(String id);

}

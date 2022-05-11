package blog.root.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface User_authMapper {

	
	@Insert("insert into user_auth(user_number,auth) values(#{user_number},'ROLE_MEMBER')")
	public void userauth_insert(@Param("user_number")int user_number);
	

	@Update("update user_auth set auth = 'ROLE_ADMIN' where user_number =#{user_number}")
	public void userauth_update_admin(@Param("user_number")int user_number);
	

}

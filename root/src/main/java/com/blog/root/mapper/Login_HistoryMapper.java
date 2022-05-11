package blog.root.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Login_HistoryMapper {

	@Insert("insert into login_history(user_number) values(#{user_number})")
	public void loginTimeCheck(@Param("user_number") int user_number) throws Exception;

}

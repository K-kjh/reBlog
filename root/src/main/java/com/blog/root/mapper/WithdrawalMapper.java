package blog.root.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface WithdrawalMapper {

	@Insert("insert into withdrawal(email,id) values(#{email},#{id})")
	public int widthdrawaladd(@Param("email") String email, @Param("id") String id) throws Exception;

}

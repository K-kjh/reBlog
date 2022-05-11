package blog.root.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

public interface RootMapper {

	@Select("select pwd=sha2(#{pwd},256) from root")
	public int lootlogin(@PathVariable("pwd") String pwd) throws Exception;
}

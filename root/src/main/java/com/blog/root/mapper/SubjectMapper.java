package blog.root.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import blog.root.model.SubjectVO;

public interface SubjectMapper {

	@Select("select board_sub_type from subject")
	@ResultType(SubjectVO.class)
	public List<SubjectVO> SubjectName() throws Exception;
	// 게시물 분류 네임을 가져옴

	@Select("select * from subject")
	@ResultType(SubjectVO.class)
	public List<SubjectVO> AllSubject() throws Exception;
	// 게시물 분류 네임,넘버을 가져옴
}

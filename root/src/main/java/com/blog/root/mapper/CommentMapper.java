package blog.root.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import blog.root.model.CommentDTO;

public interface CommentMapper {

//	@Select("select comment.comment_date ,comment.comment_number,comment.contents,user.nickname,user_auth.auth " + 
//			"from comment " + 
//			"right outer join user " + 
//			"	on comment.user_number = user.user_number " + 
//			"right outer join user_auth " + 
//			"	on user.user_number = user_auth.user_number " + 
//			"where board_number =#{board_number} ")
	public List<CommentDTO> selectCommentList(@Param("board_number") int board_number) throws Exception;
	// 게시물내 댓글 가져옴

	@Insert("insert into comment(board_number,contents,user_number) values(#{board_number},#{contents},#{user_number})")
	public int insertComment(@Param("board_number") int board_number, @Param("contents") String contents,
			 @Param("user_number") int user_number) throws Exception;
	// 댓글 작성

	@Delete("delete from comment where board_number = #{board_number}")
	public void deleteAllComment(@Param("board_number") int board_number) throws Exception;
	// 특정 게시글 댓글 전체 삭제

	@Delete("delete from comment where board_number =#{board_number} and comment_number =#{comment_number}")
	public void deleteTargetComment(@Param("board_number") int board_number,@Param("comment_number") int comment_number) throws Exception;

}

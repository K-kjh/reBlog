package blog.root.service;

import java.util.List;


import blog.root.model.CommentDTO;

public interface CommentService {
	public List<CommentDTO> selectCommentList(int board_number) throws Exception;

	public int insertComment(int board_number, String contents, int user_number) throws Exception;

	public String solve(String str);

	public void deleteAllComment(int board_number) throws Exception;
	public void deleteTargetComment( int board_number, int comment_number) throws Exception;

}

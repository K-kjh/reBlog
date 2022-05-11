package blog.root.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import blog.root.mapper.CommentMapper;
import blog.root.model.CommentDTO;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentMapper mapper;

	@Override
	public List<CommentDTO> selectCommentList(int board_number) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCommentList(board_number);
	}

	@Override
	public int insertComment(int board_number, String contents, int user_number) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertComment(board_number, contents, user_number);
	}

	public String solve(String str) {

		int count = 0;
		String comment = "";
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == '\n') {
				count = 0;
			}

			if (count >= 95) {
				comment += '\n';
				count = 0;
			} else {
				comment += str.charAt(i);
			}
			count++;
		}

		return comment;

	}

	@Override
	public void deleteAllComment(int board_number) throws Exception {
		mapper.deleteAllComment(board_number);
	}

	@Override
	public void deleteTargetComment(int board_number, int comment_number) throws Exception {
		mapper.deleteTargetComment(board_number, comment_number);
	}

}

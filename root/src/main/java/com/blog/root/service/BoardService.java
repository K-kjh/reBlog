package blog.root.service;

import java.util.ArrayList;
import java.util.List;

import blog.root.model.BoardDTO;
import blog.root.model.BoardVO;

public interface BoardService {
	public int paging_type_max(int board_type) throws Exception;

	public List<BoardVO> subtypeBoardList(int subject_type, int paging_number) throws Exception;

	public int paging_max();

	public int Board_create(String board_title, int board_type, String board_contents, int user_number);

	public int BoardCount(int board_number) throws Exception;

	public BoardDTO selectBoardList(int board_number) throws Exception;

	public List<BoardVO> mainBoardList(int paging_number) throws Exception;

	public int boardUpdate(String board_contents, String board_title, int board_type, int board_number, int user_number)
			throws Exception;

	public int boardDelete(int board_number, int user_number) throws Exception;

	public ArrayList<String> GetContentsImageSrc(String src);
}

package blog.root.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import blog.root.model.BoardDTO;
import blog.root.model.BoardVO;

public interface BoardMapper {

	@Delete("delete from board where board_number =#{board_number} && user_number =#{user_number}")
	public int boardDelete(@Param("board_number") int board_number, @Param("user_number") int user_number)throws Exception;
	// 게시판 삭제

	@Update("update board set board_contents = #{board_contents} , board_title=#{board_title} , board_type=#{board_type}"
			+ " where board_number = #{board_number} AND user_number =#{user_number}")
	public int boardUpdate(@Param("board_contents") String board_contents, @Param("board_title") String board_title,
			@Param("board_type") int board_type, @Param("board_number") int board_number,
			@Param("user_number") int user_number) throws Exception;
	// 게시판 업데이트

	@Select("select board_number,board_title,board_date,board_count,user.nickname , board.board_type "
			+ "from board  right outer join user "
			+ "on board.user_number = user.user_number where board.board_type=#{subject_type} "
			+ "order by board_number desc limit #{paging_number},13")
	public List<BoardVO> subtypeBoardList(@Param("subject_type") int subject_type,
			@Param("paging_number") int paging_number) throws Exception;
	// 타입별 게시판 구별함

	@Select("select count(board_number) from board ")
	public int paging_max();
	// 게시물 최대값을 구해옴

	@Select("select count(board_type) from board  where board_type=#{board_type}")
	public int paging_type_max(@Param("board_type") int board_type) throws Exception;
	// 타입별 페이징 최대페이지

	@Insert("insert into board(board_title,board_type,board_contents,user_number) values(#{board_title},#{board_type},#{board_contents},#{user_number})")
	public int Board_create(@Param("board_title") String board_title, @Param("board_type") int board_type,
			@Param("board_contents") String board_contents, @Param("user_number") int user_number);
	// 게시물 등록 하는 컬럼

	@Update("update board set board_count = board_count+1 where board_number =#{board_number}")
	public int BoardCount(int board_number) throws Exception;
	// 게시물 클릭시 조회수 올리는 컬럼

	@Select("select * from board where board_number = #{board_number}")
	public BoardDTO selectBoardList(int board_number) throws Exception;
	// 게시판 하나의 내부 내용 전부

	@Select("select board_number,board_title,board_date,board_count,user.nickname "
			+ " from board join user on board.user_number = user.user_number "
			+ " order by board_number desc limit #{paging_number},13")
	public List<BoardVO> mainBoardList(@Param("paging_number") int paging_number) throws Exception;
	// 게시판 내용,게시판 타입 뺴고 전부
}

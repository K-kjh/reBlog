package blog.root.controll;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.root.model.BoardDTO;
import blog.root.model.CommentDTO;
import blog.root.model.SubjectVO;
import blog.root.service.BoardService;
import blog.root.service.CommentService;
import blog.root.service.ImageService;
import blog.root.service.SubjectService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Inject
	private SubjectService subjectService;

	@Inject
	private BoardService boardService;

	@Inject
	private CommentService commentService;

	@Inject
	private ImageService imageservice;

	@GetMapping("/board")
	public void board(Model model) {

	}

	@GetMapping("/board/{board_number}/updatePage") // location.href 로 이동함
	public String board_update(@PathVariable int board_number, Model model) throws Exception {

		List<SubjectVO> subList = subjectService.AllSubject();
		BoardDTO boardDTO = boardService.selectBoardList(board_number);

		model.addAttribute("subList", subList);

		model.addAttribute("update_board_number", board_number);
		model.addAttribute("update_board_title", boardDTO.getBoard_title());
		model.addAttribute("update_board_contents", boardDTO.getBoard_contents());

		return "/addboard";
	}

	@GetMapping("/board/addboard")
	public String board_create(Model model) throws Exception {

		List<SubjectVO> subList = subjectService.AllSubject();

		model.addAttribute("subList", subList);

		return "/addboard";
	}

	@PostMapping(value = "/board/addboard/create")
	@ResponseBody
	public int boardCreate(String board_title, String board_contents, int board_type,int user_number) {
		log.info("user_number"+user_number+" title : " + board_title + "\ncontents : " + board_contents + " , \nboard_type" + board_type);
		board_title.replace("\t", "");
		board_contents = board_contents.replace("&gt;", ">");
		board_contents = board_contents.replace("&lt;", "<");
		board_contents = board_contents.replace("&amp;", "&");

		board_contents = imageservice.filemoveList(boardService.GetContentsImageSrc(board_contents), board_contents);
		if(user_number ==0 ) {
			return 0;
		}
		return boardService.Board_create(board_title, board_type, board_contents, user_number);

	}

	@PutMapping(value = "/board/{board_number}/update")
	@ResponseBody
	public int boardUpdate(@PathVariable int board_number, String board_title, String board_contents, int board_type,int user_number) {
		String str;
		log.info("title" + board_title);
		log.info("contents" + board_contents);

		str = board_contents.replace("<div>", "</br>");
		str = str.replace("<br>", "");
		str = str.replace("</div>", "");

		str = str.replace("&gt;", ">");
		str = str.replace("&lt;", "<");
		str = str.replace("&amp;", "&");

		log.info("contents" + board_contents);
		board_contents = str;

		try {

			return boardService.boardUpdate(board_contents, board_title, board_type, board_number, user_number);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return 0;

	}

	@DeleteMapping(value = "/board/{board_number}/delete")
	@ResponseBody
	public int boardDelete(@PathVariable int board_number,int user_number) {
		log.info("board_delete ::------------------" + board_number+", user_number: ---------"+user_number);

		try {
			commentService.deleteAllComment(board_number);
			
			int retu=boardService.boardDelete(board_number, user_number);
			log.info("1-"+retu);
			return retu;
		} catch (Exception e) {
			log.info("delete-------------------- no nono no non o");
		}
		

		return 0;
	}

	@PutMapping(value = "/board/count")
	@ResponseBody
	public void boardCount(int board_count, HttpServletRequest req, Model model) {
		try {

			if (board_count != 0) {
				log.info("board_count : " + board_count);
			} else {
				throw new Exception();
			}
			int board_number = board_count;

			if (boardService.BoardCount(board_number) == 1) {
				log.info("조회수 올리기 성공");
			} else {
				log.info("조회수 올리기 실패");
			}
		} catch (Exception e) {
			log.info("조회수 컨트롤 오류");
		}

	}

	@GetMapping("/board/{board_number}")
	public String board_number(@PathVariable int board_number, Model model) {

		BoardDTO boardDTO;
		try {
			boardDTO = boardService.selectBoardList(board_number);

			List<CommentDTO> commentDTO = commentService.selectCommentList(board_number);

			model.addAttribute("board_number", board_number);
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("commentDTO", commentDTO);
		} catch (Exception e) {

		}
		return "board";
	}

}

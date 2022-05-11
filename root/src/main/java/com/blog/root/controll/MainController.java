package blog.root.controll;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.root.model.BoardVO;
import blog.root.model.SubjectVO;
import blog.root.service.BoardService;
import blog.root.service.RootService;
import blog.root.service.SubjectService;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class MainController {
	

	@Inject
	private SubjectService subjectService;

	@Inject
	private BoardService boardService;

	@Inject
	private RootService rootService;

	// page
	// -------------------------------------------------------------------------------------------------------
	// start

	@GetMapping("/sub/{subject_type}")
	public String pageup(@PathVariable int subject_type, Model model, HttpServletResponse res) throws Exception {

		if (subject_type == 0) {
			res.sendRedirect("/");
		}

		int board_number_max = 0;

		try {
			int board_number_max_is = boardService.paging_type_max(subject_type);
			board_number_max = board_number_max_is;
		} catch (Exception e) {
		}

		// 게시물 최대값

		int page_max = board_number_max / 13;

		// 페이지 최대값
		log.info("page sub : " + page_max + "page_m");

		List<SubjectVO> subList = subjectService.AllSubject();
		List<BoardVO> boardList = boardService.subtypeBoardList(subject_type, 0);

		model.addAttribute("subject_type", subject_type);
		model.addAttribute("page_max", page_max);
		model.addAttribute("subList", subList);
		model.addAttribute("boardList", boardList);

		return "main";
	}

	@GetMapping("/sub/{subject_type}/page/{page}")
	public String subpage(@PathVariable int subject_type, @PathVariable int page, Model model, HttpServletResponse res)
			throws Exception {
		int board_number_max = 0;

		try {
			int board_number_max_is = boardService.paging_type_max(subject_type);
			board_number_max = board_number_max_is;
		} catch (Exception e) {
			log.info("page:ere");
		}

		// 게시물 최대값
		int page_max = board_number_max / 13;

		List<SubjectVO> subList = subjectService.AllSubject();
		List<BoardVO> boardList = boardService.subtypeBoardList(subject_type, page * 13);

		model.addAttribute("subject_type", subject_type);
		model.addAttribute("page_max", page_max);
		model.addAttribute("subList", subList);
		model.addAttribute("boardList", boardList);

		return "main";
	}

	@GetMapping("/page/{page}")
	public String mainpage(@PathVariable int page, Model model) throws Exception {
		log.info("page ----------------------------------------------------------...");
		int board_number_max = boardService.paging_max();
		// 게시물 최대값

		int page_max = board_number_max / 13;
		log.info("page_max" + page_max + " , " + board_number_max);
		// 페이지 최대값

		if (page >= page_max) {
			// 끝물 페이지는 값을 전달하지 못함 만약 0, 1, 2 ,3
			log.info("-");

		}

		List<SubjectVO> subList = subjectService.AllSubject();
		List<BoardVO> boardList = boardService.mainBoardList((page * 13));
		model.addAttribute("page", page);
		model.addAttribute("page_max", page_max);
		model.addAttribute("subList", subList);
		model.addAttribute("boardList", boardList);
		return "main";

	}

	// page
	// ------------------------------------------------------------------------------------------------------
	// end
	@PostMapping("/root")
	public void rootlogin(String pwd, HttpSession session, HttpServletResponse res) throws Exception {
		log.info("pwd. :" + pwd);

		try {
			if (rootService.lootlogin(pwd) == 1) {
				session.setAttribute("root", true);

			}
		} catch (Exception e) {

		}
		res.sendRedirect("/");
	}

	@GetMapping("/root/logout")
	public void rootlogout(HttpSession session, HttpServletResponse res) throws Exception {
		session.removeAttribute("root");
		res.sendRedirect("/");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model, HttpSession session) throws Exception {

		int board_number_max = boardService.paging_max();
		// 게시물 최대값

		int page_m = board_number_max / 13;
		int page_max = board_number_max % 13 > 0 ? 1 : 0;
		page_max += page_m;
		// 페이지 최대값
		// 왼쪽 이 13씩 올라감
		log.info("magin re load");
		
		
		List<SubjectVO> subList = subjectService.AllSubject();
		List<BoardVO> boardList = boardService.mainBoardList(0);
		
		model.addAttribute("page", 0);
		model.addAttribute("page_max", page_max - 1);
		model.addAttribute("subList", subList);
		model.addAttribute("boardList", boardList);

		return "main";
	}

}

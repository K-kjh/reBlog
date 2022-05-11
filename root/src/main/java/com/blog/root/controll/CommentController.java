package blog.root.controll;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.root.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {

	@Inject
	private CommentService commentmapper;

	@PostMapping("/board/{board_number}/comment")
	@ResponseBody
	public int commentInput(@PathVariable int board_number, String commentInput,int user_number) throws Exception {
		log.info(":"+user_number);
		
		commentInput = commentmapper.solve(commentInput);
		commentmapper.insertComment(board_number, commentInput, user_number);
		
		return 1;
	}
	
	@PostMapping("/board/{board_number}/comment/{comment_number}")
	@ResponseBody
	public int commentDelete(@PathVariable int board_number,@PathVariable int comment_number) {
		try {
			log.info("board : "+ board_number+",, comment_number :: "+comment_number);
			commentmapper.deleteTargetComment(board_number, comment_number);
			return 1;
		} catch (Exception e) {
			log.info("error---");
			e.printStackTrace();
			return 0;
		}
	}

}

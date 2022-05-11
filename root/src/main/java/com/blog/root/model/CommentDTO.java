package blog.root.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class CommentDTO {
	private List<UserDTO> userList;
	private List<User_authDTO> user_authList;
	
	private int board_number; // 변경 comment_number(PK) -> board_number(PK)
	private int comment_number;
	private String contents;
	private Timestamp comment_date;
	private int user_number;

}

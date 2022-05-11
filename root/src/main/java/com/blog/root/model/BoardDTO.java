package blog.root.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class BoardDTO {
	private List<UserDTO> userList;


	private int board_number;
	private String board_title;
	private int board_type;
	private String board_contents;
	private Timestamp board_date;
	private int board_count;
	private int user_number;

}
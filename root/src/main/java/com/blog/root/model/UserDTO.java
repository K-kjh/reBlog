package blog.root.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO  {
	private int user_number; // PK
	private String id;
	private String pw;
	private String email;
	private Timestamp id_create_date;
	private String nickname;
	private int enabled;
	private List<User_authDTO> authList;
}

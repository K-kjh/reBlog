package blog.root.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Login_HistoryDTO {
	private int user_number;
	private Timestamp login_date;

}

package blog.root.model;

import lombok.Data;

@Data
public class MailDTO {
	
	private final int Certification;
	private String email;
	private String name;

	public MailDTO() {
		Certification = (int) (Math.random()*99999)+100000;
	}
}

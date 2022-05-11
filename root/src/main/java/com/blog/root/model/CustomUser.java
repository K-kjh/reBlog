package blog.root.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User{

	private static final long serialVersionUID = 1L;
	
	private UserDTO userdto;
	private String nickname;
	private int user_number;
	private Timestamp id_create_date;
	private String auth;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(UserDTO dto) {
		super(dto.getId(),dto.getPw(),dto.getAuthList().stream().
				map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.userdto=dto;
		this.nickname = dto.getNickname();
		this.user_number=dto.getUser_number();
		this.id_create_date=dto.getId_create_date();
		this.auth = dto.getAuthList().get(0).getAuth();
		
	}

}

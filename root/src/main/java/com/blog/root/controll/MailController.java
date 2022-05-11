package blog.root.controll;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.root.model.MailDTO;
import blog.root.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MailController {
	
	@Inject
	 MailSenderService mail;
	
	 MailDTO mailDTO=null;
	
	@PostMapping("/singup/emailsend")
	@ResponseBody
	public void emailsend(String email) {
		
		log.info(" mail : "+email);
		MailDTO maildto = new MailDTO();
		maildto.setEmail(email);
		
		mail.send(maildto);
		
		mailDTO=maildto;
		log.info("인증 : "+mailDTO.getCertification());
	
	}
	
	@PostMapping("/singup/emailCertifi")
	@ResponseBody
	public int emailCertifi(int certificationNumber) {
		log.info("인증 번호 입렭 : "+certificationNumber);
		if(mailDTO != null) {
			log.info("인증 번호 :"+mailDTO.getCertification());
			if(mailDTO.getCertification() == certificationNumber) {
				return 1;
			}else {
				
			}
			
		}
		return 0;
	}

}

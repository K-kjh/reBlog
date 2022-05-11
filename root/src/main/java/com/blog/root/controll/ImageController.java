package blog.root.controll;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import blog.root.service.ImageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageController {

	@Inject
	public ImageService service;

	@Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시 정각 실행 처음봄 초 분 시 일 월 요일 연도=생략가능
	private void ImageTimeDelte() {
		service.todayImageTempDelete();
	}

	@PostMapping(value = "/uploads")
	@ResponseBody
	public String uploads(MultipartFile[] uploadFile, String Time) {
		log.info("time : " + Time);
		log.info(" update ajax post ...... ");

		for (MultipartFile multipartFile : uploadFile) {

			log.info("-------------------------------1");

			log.info("upload file name " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			String currDir = ImageController.class.getResource(".").getPath();
			String path = service.projectImageStr(currDir) + "imageTemp/";

			UUID uuid = UUID.randomUUID();
			uploadFileName = Time + "_" + uuid.toString() + service.imgType(uploadFileName);

			String uploadImageFile = path + uploadFileName;

			log.info("origName : " + multipartFile);
			log.info("currDir : " + currDir);
			log.info("path : " + path);
			log.info("uploadImage FIle Path :::" + uploadImageFile);

			try {

				multipartFile.transferTo(new File(uploadImageFile));

			} catch (Exception e) {
				e.getMessage();
				log.info("error");
				log.error(e.getMessage());

			}
			return uploadFileName;
		}
		return null;

	}

}

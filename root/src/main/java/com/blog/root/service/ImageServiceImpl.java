package blog.root.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kang
 *
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final static String originFileSrc = "/home/kang/app/WorkJava/KangBlog/src/main/webapp/resources/image/";
	private final static String tempFileSrc = originFileSrc + "imageTemp/";

//	public static void maintest() {
//		
//		String fileName="1585137540369_d9e9ab15-c1f0-4dfa-aaeb-468be76aa444.png";
//		//todayImageTempDelete(); 			// 파일 이미지 임시 경로 에서 하루 지난걸 없앰 function -> todayImageTempDelete
//		//fileMove(fileName); // imageTemp ->image 이동 //						-> fileMove
//
//	}
//	
//	
	/**
	 * 임시 파일 이 시간이 지난 파일은 자동적 매일 0시 마다 3일 이상 차이가 난다면 자동적 삭제 part.1
	 * 
	 * @Date 2020 03 26
	 */
	public void todayImageTempDelete() {

		File file = new File(tempFileSrc);

		if (!file.exists()) {
			log.info("파일 없음 ");
		}

		if (file.isDirectory()) {
			File[] fileList = file.listFiles();

			for (File tFile : fileList) {

				if (!tFile.isDirectory()) {
					fileDateDelete(tFile.getName());
				}
			}

		} // if end

	}

	/**
	 * 해당파일이 3일 이 @PutMapping(value="/board/{board_number}/update")
	 * 
	 * @ResponseBody public int boardUpdate(@PathVariable int board_number, String
	 *               board_title,String board_contents ,int board_type) { String
	 *               str; log.info("title"+board_title);
	 *               log.info("contents"+board_contents);
	 * 
	 *               str=board_contents.replace("<div>", "<br/>
	 *               "); str=str.replace("<br>
	 *               ", ""); str=str.replace("</div>", "");
	 * 
	 * 
	 *               log.info("contents"+board_contents); board_contents=str;
	 * 
	 *               try {
	 * 
	 *               return boardService. boardUpdate(board_contents, board_title,
	 *               board_type, board_number);
	 * 
	 *               } catch (Exception e) {
	 * 
	 *               e.printStackTrace(); }
	 * 
	 *               return 0;
	 * 
	 *               }상이 된다면 삭제 part.2
	 * @param name
	 * @Date 2020 03 26
	 */
	private static void fileDateDelete(String name) {

		File file = new File(tempFileSrc + name);

		if (file.exists()) {

			Date d = new Date();

			long DTIME = d.getTime() / 1000 / 60 / 60 / 24;
			long dateTime = srcTimeNumber(name) / 1000 / 60 / 60 / 24;

			if (DTIME - dateTime > 2) {
				if (fileDelte(file)) {
					log.info("파일 삭제");
				} else {
					log.info("파일 삭제 실패 --- ");
				}
			}
		}
	}

	/**
	 * 이미지 파일 이름 숫자 가져오기
	 * 
	 * @param fileOriginName
	 * @return
	 * @Date 2020 03 26
	 */
	private static long srcTimeNumber(String fileOriginName) {
		long number = 0;
		for (int i = 0; i < fileOriginName.length(); i++) {

			if (fileOriginName.charAt(i) == '_') {
				break;
			} else {
				number *= 10;
			}
			number += fileOriginName.charAt(i) - '0';

		}
		return number;
	}

	/**
	 * 파일 지우기
	 * 
	 * @param file
	 * @return
	 * @Date 2020 03 26
	 */
	private static boolean fileDelte(File file) {
		if (file.exists()) {
			if (file.delete()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 임시 파일 리스트들을 이동시킴 와 동싱 내용을 수정함
	 *
	 * @Date 2020 03 26
	 */
	public String filemoveList(ArrayList<String> list, String src) {
		for (int i = 0; i < list.size(); i++) {
			fileMove(list.get(i));
		}
		return src = src.replace("<img src=\"/img/imageTemp/", "<img src=\"/img/");
	}

	/**
	 * 파일 이동
	 * 
	 * @param fileName
	 * 
	 * @Date 2020 03 26
	 */
	private static void fileMove(String fileName) {

		File orifile = new File(tempFileSrc + fileName);
		File copfile = new File(originFileSrc + fileName);

		try {
			FileInputStream fi = new FileInputStream(orifile);
			FileOutputStream fo = new FileOutputStream(copfile);

			int fileByte = 0;

			while ((fileByte = fi.read()) != -1) {
				fo.write(fileByte);
			}

			fi.close();
			fo.close();

			if (fileDelte(orifile) == true) {
				log.info("파일 이동 완료 ");
			} else {
				log.info("파일 이동 실패");
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String imgType(String str) {
		String type = "";
		boolean ty = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.' || ty == true) {
				type += str.charAt(i);
				ty = true;
			}
		}
		return ty == true ? type : null;
	}

	public String projectImageStr(String str) {
		String ser = ".metadata";
		String proj = "KangBlog";

		String imagestr = "/";

		boolean s = false;
		boolean sis = false;
		for (int i = 0; i < str.length() - 1; i++) {

			if (str.charAt(i) == '/') {
				for (int j = i + 1; j < str.length(); j++) {
					if (s == false && ser.charAt(0) == str.charAt(j)) {
						if (stris(j, str, ser) == false) {
							s = true;
							break;
						}
					}
					if (s == true && proj.charAt(0) == str.charAt(j)) {
						if (stris(j, str, proj) == false) {
							s = false;
							sis = true;
						}
					}
					if (s == false) {
						imagestr += str.charAt(j);
						if (str.charAt(j) == '/') {
							break;
						}
					}

				}
			}
			if (sis == true) {
				break;
			}
		} // for -end
		imagestr += "src/main/webapp/resources/image/";
		System.out.println(imagestr);

		return imagestr;

	}

	private static boolean stris(int j, String str, String str2) {

		for (int z = 0; z < str2.length(); z++) {
			if (str2.charAt(z) != str.charAt(j + z)) {
				return true;
			}
		}
		return false;
	}

}

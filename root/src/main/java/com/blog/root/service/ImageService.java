package blog.root.service;

import java.io.File;
import java.util.ArrayList;

public interface ImageService {

	public String imgType(String str);

	public String projectImageStr(String str);

	// 2020 03 26 add
	public void todayImageTempDelete();

	public String filemoveList(ArrayList<String> list, String src);
}

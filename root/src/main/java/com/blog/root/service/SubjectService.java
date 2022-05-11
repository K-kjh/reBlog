package blog.root.service;

import java.util.List;

import blog.root.model.SubjectVO;

public interface SubjectService {
	public abstract List<SubjectVO> SubjectName() throws Exception;

	public List<SubjectVO> AllSubject() throws Exception;
}

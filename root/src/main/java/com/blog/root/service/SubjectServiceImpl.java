package blog.root.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import blog.root.mapper.SubjectMapper;
import blog.root.model.SubjectVO;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Inject
	private SubjectMapper mapper;

	@Override
	public List<SubjectVO> SubjectName() throws Exception {

		return mapper.SubjectName();
	}

	@Override
	public List<SubjectVO> AllSubject() throws Exception {

		return mapper.AllSubject();
	}

}

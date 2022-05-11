package blog.root.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import blog.root.mapper.RootMapper;

@Service
public class RootServiceImpl implements RootService {

	@Inject
	private RootMapper rootmapper;

	@Override
	public int lootlogin(String pwd) throws Exception {
		return rootmapper.lootlogin(pwd);
	}

}

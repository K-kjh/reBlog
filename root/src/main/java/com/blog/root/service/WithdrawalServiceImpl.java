package blog.root.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import blog.root.mapper.WithdrawalMapper;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

	@Inject
	private WithdrawalMapper mapper;

	@Override
	public int widthdrawaladd(String email, String id) throws Exception {

		return mapper.widthdrawaladd(email, id);
	}

}

package com.jeecg.p3.aboutActivity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.aboutActivity.service.AwardApproveUserService;
import com.jeecg.p3.aboutActivity.entity.AwardApproveUser;
import com.jeecg.p3.aboutActivity.dao.AwardApproveUserDao;

@Service("awardApproveUserService")
public class AwardApproveUserServiceImpl implements AwardApproveUserService {
	@Resource
	private AwardApproveUserDao awardApproveUserDao;

	@Override
	public void doAdd(AwardApproveUser awardApproveUser) {
		awardApproveUserDao.add(awardApproveUser);
	}

	@Override
	public void doEdit(AwardApproveUser awardApproveUser) {
		awardApproveUserDao.update(awardApproveUser);
	}

	@Override
	public void doDelete(String id) {
		awardApproveUserDao.delete(id);
	}

	@Override
	public AwardApproveUser queryById(String id) {
		AwardApproveUser awardApproveUser  = awardApproveUserDao.get(id);
		return awardApproveUser;
	}

	@Override
	public PageList<AwardApproveUser> queryPageList(
		PageQuery<AwardApproveUser> pageQuery) {
		PageList<AwardApproveUser> result = new PageList<AwardApproveUser>();
		Integer itemCount = awardApproveUserDao.count(pageQuery);
		List<AwardApproveUser> list = awardApproveUserDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Integer count(PageQuery<AwardApproveUser> pageQuery) {
		// TODO Auto-generated method stub
		Integer itemCount = awardApproveUserDao.count(pageQuery);
		return itemCount;
	}
	
}

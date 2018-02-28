package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.houseaOnline.service.XfFollowService;
import com.jeecg.p3.houseaOnline.entity.XfFollow;
import com.jeecg.p3.houseaOnline.dao.XfFollowDao;

@Service("xfFollowService")
public class XfFollowServiceImpl implements XfFollowService {
	@Resource
	private XfFollowDao xfFollowDao;

	@Override
	public void doAdd(XfFollow xfFollow) {
		xfFollowDao.add(xfFollow);
	}

	@Override
	public void doEdit(XfFollow xfFollow) {
		xfFollowDao.update(xfFollow);
	}

	@Override
	public void doDelete(String id) {
		xfFollowDao.delete(id);
	}

	@Override
	public XfFollow queryById(String id) {
		XfFollow xfFollow  = xfFollowDao.get(id);
		return xfFollow;
	}

	@Override
	public PageList<XfFollow> queryPageList(
		PageQuery<XfFollow> pageQuery) {
		PageList<XfFollow> result = new PageList<XfFollow>();
		Integer itemCount = xfFollowDao.count(pageQuery);
		List<XfFollow> list = xfFollowDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Integer count(PageQuery<XfFollow> pageQuery) {
		// TODO Auto-generated method stub
		return xfFollowDao.count(pageQuery);
	}

	@Override
	public void deleteByridAndUser(XfFollow xffollow) {
		// TODO Auto-generated method stub
		xfFollowDao.deleteByridAndUser(xffollow);
	}
	
}

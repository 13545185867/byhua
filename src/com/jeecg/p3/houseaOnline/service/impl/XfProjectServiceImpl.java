package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfProjectService;
import com.jeecg.p3.houseaOnline.entity.XfProject;
import com.jeecg.p3.houseaOnline.dao.XfProjectDao;

@Service("xfProjectService")
public class XfProjectServiceImpl implements XfProjectService {
	@Resource
	private XfProjectDao xfProjectDao;

	@Override
	public void doAdd(XfProject xfProject) {
		xfProjectDao.add(xfProject);
	}

	@Override
	public void doEdit(XfProject xfProject) {
		xfProjectDao.update(xfProject);
	}

	@Override
	public void doDelete(String id) {
		xfProjectDao.delete(id);
	}

	@Override
	public XfProject queryById(String id) {
		XfProject xfProject  = xfProjectDao.get(id);
		return xfProject;
	}

	@Override
	public PageList<XfProject> queryPageList(
		PageQuery<XfProject> pageQuery) {
		PageList<XfProject> result = new PageList<XfProject>();
		Integer itemCount = xfProjectDao.count(pageQuery);
		List<XfProject> list = xfProjectDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}

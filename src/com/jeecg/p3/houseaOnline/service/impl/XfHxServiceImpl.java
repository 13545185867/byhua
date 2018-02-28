package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfHxService;
import com.jeecg.p3.houseaOnline.entity.XfHx;
import com.jeecg.p3.houseaOnline.dao.XfHxDao;

@Service("xfHxService")
public class XfHxServiceImpl implements XfHxService {
	@Resource
	private XfHxDao xfHxDao;

	@Override
	public void doAdd(XfHx xfHx) {
		xfHxDao.add(xfHx);
	}

	@Override
	public void doEdit(XfHx xfHx) {
		xfHxDao.update(xfHx);
	}

	@Override
	public void doDelete(String id) {
		xfHxDao.delete(id);
	}

	@Override
	public XfHx queryById(String id) {
		XfHx xfHx  = xfHxDao.get(id);
		return xfHx;
	}

	@Override
	public PageList<XfHx> queryPageList(
		PageQuery<XfHx> pageQuery) {
		PageList<XfHx> result = new PageList<XfHx>();
		Integer itemCount = xfHxDao.count(pageQuery);
		List<XfHx> list = xfHxDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<XfHx> queryAllByPid(XfHx xfhx) {
		// TODO Auto-generated method stub
		return xfHxDao.queryAllByPid(xfhx);
	}
	
}

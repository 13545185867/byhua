package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfHouseService;
import com.jeecg.p3.houseaOnline.entity.XfHouse;
import com.jeecg.p3.houseaOnline.dao.XfHouseDao;

@Service("xfHouseService")
public class XfHouseServiceImpl implements XfHouseService {
	@Resource
	private XfHouseDao xfHouseDao;

	@Override
	public void doAdd(XfHouse xfHouse) {
		xfHouseDao.add(xfHouse);
	}

	@Override
	public void doEdit(XfHouse xfHouse) {
		xfHouseDao.update(xfHouse);
	}

	@Override
	public void doDelete(String id) {
		xfHouseDao.delete(id);
	}

	@Override
	public XfHouse queryById(String id) {
		XfHouse xfHouse  = xfHouseDao.get(id);
		return xfHouse;
	}

	@Override
	public PageList<XfHouse> queryPageList(
		PageQuery<XfHouse> pageQuery) {
		PageList<XfHouse> result = new PageList<XfHouse>();
		Integer itemCount = xfHouseDao.count(pageQuery);
		List<XfHouse> list = xfHouseDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public XfHouse getAllbyId(XfHouse xf) {
		// TODO Auto-generated method stub
		return xfHouseDao.getAllbyId(xf);
	}

	@Override
	public List<XfHouse> queryByCondit(XfHouse xf) {
		// TODO Auto-generated method stub
		return xfHouseDao.queryByCondit(xf);
	}
	
}

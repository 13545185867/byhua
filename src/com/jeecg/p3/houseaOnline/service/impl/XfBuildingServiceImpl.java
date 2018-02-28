package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfBuildingService;
import com.jeecg.p3.houseaOnline.entity.XfBuilding;
import com.jeecg.p3.houseaOnline.dao.XfBuildingDao;

@Service("xfBuildingService")
public class XfBuildingServiceImpl implements XfBuildingService {
	@Resource
	private XfBuildingDao xfBuildingDao;

	@Override
	public void doAdd(XfBuilding xfBuilding) {
		xfBuildingDao.add(xfBuilding);
	}

	@Override
	public void doEdit(XfBuilding xfBuilding) {
		xfBuildingDao.update(xfBuilding);
	}

	@Override
	public void doDelete(String id) {
		xfBuildingDao.delete(id);
	}

	@Override
	public XfBuilding queryById(String id) {
		XfBuilding xfBuilding  = xfBuildingDao.get(id);
		return xfBuilding;
	}

	@Override
	public PageList<XfBuilding> queryPageList(
		PageQuery<XfBuilding> pageQuery) {
		PageList<XfBuilding> result = new PageList<XfBuilding>();
		Integer itemCount = xfBuildingDao.count(pageQuery);
		List<XfBuilding> list = xfBuildingDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<XfBuilding> queryByCondit(XfBuilding xfbuild) {
		// TODO Auto-generated method stub
		return xfBuildingDao.queryByCondit(xfbuild);
	}
	
}

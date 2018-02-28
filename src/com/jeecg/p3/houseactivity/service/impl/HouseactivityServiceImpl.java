package com.jeecg.p3.houseactivity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseactivity.service.HouseactivityService;
import com.jeecg.p3.houseactivity.entity.Houseactivity;
import com.jeecg.p3.houseactivity.dao.HouseactivityDao;

@Service("houseactivityService")
public class HouseactivityServiceImpl implements HouseactivityService {
	@Resource
	private HouseactivityDao houseactivityDao;

	@Override
	public void doAdd(Houseactivity houseactivity) {
		houseactivityDao.add(houseactivity);
	}

	@Override
	public void doEdit(Houseactivity houseactivity) {
		houseactivityDao.update(houseactivity);
	}

	@Override
	public void doDelete(String id) {
		houseactivityDao.delete(id);
	}

	@Override
	public Houseactivity queryById(String id) {
		Houseactivity houseactivity  = houseactivityDao.get(id);
		return houseactivity;
	}

	@Override
	public PageList<Houseactivity> queryPageList(
		PageQuery<Houseactivity> pageQuery) {
		PageList<Houseactivity> result = new PageList<Houseactivity>();
		Integer itemCount = houseactivityDao.count(pageQuery);
		List<Houseactivity> list = houseactivityDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<Houseactivity> queryList(Houseactivity house) {
		// TODO Auto-generated method stub
		return houseactivityDao.queryList(house);
	}
	
}

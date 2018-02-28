package com.jeecg.p3.bigwheel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.bigwheel.service.ActivityBigwheelAwardService;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;
import com.jeecg.p3.bigwheel.dao.ActivityBigwheelAwardDao;

@Service("activityBigwheelAwardService")
public class ActivityBigwheelAwardServiceImpl implements ActivityBigwheelAwardService {
	@Resource
	private ActivityBigwheelAwardDao activityBigwheelAwardDao;

	@Override
	public void doAdd(ActivityBigwheelAward activityBigwheelAward) {
		activityBigwheelAwardDao.add(activityBigwheelAward);
	}

	@Override
	public void doEdit(ActivityBigwheelAward activityBigwheelAward) {
		activityBigwheelAwardDao.update(activityBigwheelAward);
	}

	@Override
	public void doDelete(Integer id) {
		activityBigwheelAwardDao.delete(id);
	}

	@Override
	public ActivityBigwheelAward queryById(String id) {
		ActivityBigwheelAward activityBigwheelAward  = activityBigwheelAwardDao.get(id);
		return activityBigwheelAward;
	}

	@Override
	public PageList<ActivityBigwheelAward> queryPageList(
		PageQuery<ActivityBigwheelAward> pageQuery) {
		PageList<ActivityBigwheelAward> result = new PageList<ActivityBigwheelAward>();
		Integer itemCount = activityBigwheelAwardDao.count(pageQuery);
		List<ActivityBigwheelAward> list = activityBigwheelAwardDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<ActivityBigwheelAward> queryAwardList(PageQuery<ActivityBigwheelAward> pageQuery) {
		// TODO Auto-generated method stub
		return activityBigwheelAwardDao.queryAwardList(pageQuery);
	}

	@Override
	public Integer getAwardSUM(ActivityBigwheelAward aba) {
		// TODO Auto-generated method stub
		return activityBigwheelAwardDao.getAwardSUM(aba);
	}

	@Override
	public List<ActivityBigwheelAward> queryAwardList(Integer activityid) {
		// TODO Auto-generated method stub
		return activityBigwheelAwardDao.queryAwardList(activityid);
	}
	
}

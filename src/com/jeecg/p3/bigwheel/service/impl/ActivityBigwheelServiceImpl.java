package com.jeecg.p3.bigwheel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.bigwheel.service.ActivityBigwheelService;
import com.jeecg.p3.bigwheel.entity.ActRuntime;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelConfigPlayRule;
import com.jeecg.p3.bigwheel.dao.ActivityBigwheelDao;

@Service("activityBigwheelService")
public class ActivityBigwheelServiceImpl implements ActivityBigwheelService {
	@Resource
	private ActivityBigwheelDao activityBigwheelDao;

	@Override
	public void doAdd(ActivityBigwheel activityBigwheel) {
		activityBigwheelDao.add(activityBigwheel);
	}

	@Override
	public void doEdit(ActivityBigwheel activityBigwheel) {
		activityBigwheelDao.update(activityBigwheel);
	}

	@Override
	public void doDelete(String id) {
		activityBigwheelDao.delete(id);
	}

	@Override
	public ActivityBigwheel queryById(String id) {
		ActivityBigwheel activityBigwheel  = activityBigwheelDao.get(id);
		return activityBigwheel;
	}

	@Override
	public PageList<ActivityBigwheel> queryPageList(
		PageQuery<ActivityBigwheel> pageQuery) {
		PageList<ActivityBigwheel> result = new PageList<ActivityBigwheel>();
		Integer itemCount = activityBigwheelDao.count(pageQuery);
		List<ActivityBigwheel> list = activityBigwheelDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public ActivityBigwheel getByIdAndUserid(Integer id, String userid) {
		// TODO Auto-generated method stub
		return activityBigwheelDao.getByIdAndUserid(id,userid);
	}

	@Override
	public List<ActRuntime> getActRuntime() {
		// TODO Auto-generated method stub
		return activityBigwheelDao.getActRuntime();
	}

	@Override
	public ActivityBigwheelConfigPlayRule getBigwheelAll(Integer id) {
		// TODO Auto-generated method stub
		return activityBigwheelDao.getBigwheelAll(id);
	}
	
}

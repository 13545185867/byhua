package com.jeecg.p3.bigwheel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.bigwheel.service.ActivityBigwheelPlayruleService;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.dao.ActivityBigwheelPlayruleDao;

@Service("activityBigwheelPlayruleService")
public class ActivityBigwheelPlayruleServiceImpl implements ActivityBigwheelPlayruleService {
	@Resource
	private ActivityBigwheelPlayruleDao activityBigwheelPlayruleDao;

	@Override
	public void doAdd(ActivityBigwheelPlayrule activityBigwheelPlayrule) {
		activityBigwheelPlayruleDao.add(activityBigwheelPlayrule);
	}

	@Override
	public void doEdit(ActivityBigwheelPlayrule activityBigwheelPlayrule) {
		activityBigwheelPlayruleDao.update(activityBigwheelPlayrule);
	}

	@Override
	public void doDelete(Integer id) {
		activityBigwheelPlayruleDao.delete(id);
	}

	@Override
	public ActivityBigwheelPlayrule queryById(String id) {
		ActivityBigwheelPlayrule activityBigwheelPlayrule  = activityBigwheelPlayruleDao.get(id);
		return activityBigwheelPlayrule;
	}

	@Override
	public PageList<ActivityBigwheelPlayrule> queryPageList(
		PageQuery<ActivityBigwheelPlayrule> pageQuery) {
		PageList<ActivityBigwheelPlayrule> result = new PageList<ActivityBigwheelPlayrule>();
		Integer itemCount = activityBigwheelPlayruleDao.count(pageQuery);
		List<ActivityBigwheelPlayrule> list = activityBigwheelPlayruleDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<ActivityBigwheelPlayrule> queryBYCol(
			PageQuery<ActivityBigwheelPlayrule> abp) {
		// TODO Auto-generated method stub
		return activityBigwheelPlayruleDao.queryBYCol(abp);
	}

	
}

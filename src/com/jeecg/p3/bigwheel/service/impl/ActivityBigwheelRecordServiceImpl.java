package com.jeecg.p3.bigwheel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.bigwheel.service.ActivityBigwheelRecordService;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;
import com.jeecg.p3.bigwheel.dao.ActivityBigwheelRecordDao;

@Service("activityBigwheelRecordService")
public class ActivityBigwheelRecordServiceImpl implements ActivityBigwheelRecordService {
	@Resource
	private ActivityBigwheelRecordDao activityBigwheelRecordDao;

	@Override
	public void doAdd(ActivityBigwheelRecord activityBigwheelRecord) {
		activityBigwheelRecordDao.add(activityBigwheelRecord);
	}

	@Override
	public void doEdit(ActivityBigwheelRecord activityBigwheelRecord) {
		activityBigwheelRecordDao.update(activityBigwheelRecord);
	}

	@Override
	public void doDelete(Integer id) {
		activityBigwheelRecordDao.delete(id);
	}

	@Override
	public ActivityBigwheelRecord queryById(String id) {
		ActivityBigwheelRecord activityBigwheelRecord  = activityBigwheelRecordDao.get(id);
		return activityBigwheelRecord;
	}

	@Override
	public PageList<ActivityBigwheelRecord> queryPageList(
		PageQuery<ActivityBigwheelRecord> pageQuery) {
		PageList<ActivityBigwheelRecord> result = new PageList<ActivityBigwheelRecord>();
		Integer itemCount = activityBigwheelRecordDao.count(pageQuery);
		List<ActivityBigwheelRecord> list = activityBigwheelRecordDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<ActivityBigwheelRecord> selectByOpenIDAndDate(
			ActivityBigwheelRecord abr) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.selectByOpenIDAndDate(abr);
	}

	@Override
	public Integer count(PageQuery<ActivityBigwheelRecord> pageQuery) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.count(pageQuery);
	}

	@Override
	public List<ActivityBigwheelRecord> getAwardList(PageQuery<ActivityBigwheelRecord> abr) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.getAwardList(abr);
	}


	@Override
	public Integer getAwardCount(ActivityBigwheelRecord pageQuery) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.getAwardCount(pageQuery);
	}

	@Override
	public Integer helpcount(ActivityBigwheelRecord pageQuery) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.helpcount(pageQuery);
	}

	@Override
	public List<UserAwardRecord> getUserAwardRecord(PageQuery<ActivityBigwheelRecord> pageQuery) {
		// TODO Auto-generated method stub
		return activityBigwheelRecordDao.getUserAwardRecord(pageQuery);
	}
	
	
	
}

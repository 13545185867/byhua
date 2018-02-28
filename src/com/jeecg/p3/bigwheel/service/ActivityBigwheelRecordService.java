package com.jeecg.p3.bigwheel.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;

/**
 * 描述：</b>ActivityBigwheelRecordService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时18分28秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelRecordService {
	
	
	public void doAdd(ActivityBigwheelRecord activityBigwheelRecord);
	
	public void doEdit(ActivityBigwheelRecord activityBigwheelRecord);
	
	public void doDelete(Integer id);
	
	public ActivityBigwheelRecord queryById(String id);
	
	public PageList<ActivityBigwheelRecord> queryPageList(PageQuery<ActivityBigwheelRecord> pageQuery);
	
	public List<ActivityBigwheelRecord> selectByOpenIDAndDate(ActivityBigwheelRecord abr);
	
	public Integer count(PageQuery<ActivityBigwheelRecord>  pageQuery);
	
	public List<ActivityBigwheelRecord> getAwardList(PageQuery<ActivityBigwheelRecord> abr);
	
	public List<UserAwardRecord> getUserAwardRecord(PageQuery<ActivityBigwheelRecord> pageQuery);
	
	public Integer getAwardCount(ActivityBigwheelRecord pageQuery);
	
	public Integer helpcount( ActivityBigwheelRecord  pageQuery);
}


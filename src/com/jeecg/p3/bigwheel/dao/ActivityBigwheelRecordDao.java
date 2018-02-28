package com.jeecg.p3.bigwheel.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;

/**
 * 描述：</b>ActivityBigwheelRecordDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时18分28秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelRecordDao extends GenericDao<ActivityBigwheelRecord>{
	
	public Integer count(PageQuery<ActivityBigwheelRecord> pageQuery);
	
	public List<ActivityBigwheelRecord> queryPageList(PageQuery<ActivityBigwheelRecord> pageQuery,Integer itemCount);
	
	public List<ActivityBigwheelRecord> selectByOpenIDAndDate(ActivityBigwheelRecord abr);
	
	public Integer count( ActivityBigwheelRecord  pageQuery);
	
	public List<ActivityBigwheelRecord> getAwardList(PageQuery<ActivityBigwheelRecord> abr);
	
	public List<UserAwardRecord> getUserAwardRecord(PageQuery<ActivityBigwheelRecord> pageQuery);
	
	public Integer getAwardCount(ActivityBigwheelRecord pageQuery);
	
	public Integer helpcount( ActivityBigwheelRecord  pageQuery);
	
}


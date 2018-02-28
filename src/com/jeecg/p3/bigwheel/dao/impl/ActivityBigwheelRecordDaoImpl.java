package com.jeecg.p3.bigwheel.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.bigwheel.dao.ActivityBigwheelRecordDao;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;

/**
 * 描述：</b>ActivityBigwheelRecordDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时18分28秒 星期三 
 * @version:1.0
 */
@Repository("activityBigwheelRecordDao")
public class ActivityBigwheelRecordDaoImpl extends GenericDaoDefault<ActivityBigwheelRecord> implements ActivityBigwheelRecordDao{

	@Override
	public Integer count(PageQuery<ActivityBigwheelRecord> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelRecord> queryPageList(
			PageQuery<ActivityBigwheelRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityBigwheelRecord> wrapper = new PageQueryWrapper<ActivityBigwheelRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityBigwheelRecord>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelRecord> selectByOpenIDAndDate(
			ActivityBigwheelRecord abr) {
		// TODO Auto-generated method stub
		return (List<ActivityBigwheelRecord>)super.query("selectByOpenIDAndDate",abr);
	}

	@Override
	public Integer count(ActivityBigwheelRecord pageQuery) {
		// TODO Auto-generated method stub
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelRecord> getAwardList(PageQuery<ActivityBigwheelRecord> abr) {
		// TODO Auto-generated method stub
		return (List<ActivityBigwheelRecord>)super.query("getAwardList",abr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAwardRecord> getUserAwardRecord(PageQuery<ActivityBigwheelRecord> pageQuery) {
		// TODO Auto-generated method stub
		return (List<UserAwardRecord>)super.query("getUserAwardRecord",pageQuery);
	}


	@Override
	public Integer getAwardCount(ActivityBigwheelRecord pageQuery) {
		// TODO Auto-generated method stub
		return (Integer) super.queryOne("getAwardCount",pageQuery);
	}

	@Override
	public Integer helpcount(ActivityBigwheelRecord pageQuery) {
		// TODO Auto-generated method stub
		return (Integer) super.queryOne("helpcount",pageQuery);
	}

}


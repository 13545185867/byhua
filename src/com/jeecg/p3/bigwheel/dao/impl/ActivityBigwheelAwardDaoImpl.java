package com.jeecg.p3.bigwheel.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.bigwheel.dao.ActivityBigwheelAwardDao;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;

/**
 * 描述：</b>ActivityBigwheelAwardDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时21分57秒 星期三 
 * @version:1.0
 */
@Repository("activityBigwheelAwardDao")
public class ActivityBigwheelAwardDaoImpl extends GenericDaoDefault<ActivityBigwheelAward> implements ActivityBigwheelAwardDao{

	@Override
	public Integer count(PageQuery<ActivityBigwheelAward> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelAward> queryPageList(
			PageQuery<ActivityBigwheelAward> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityBigwheelAward> wrapper = new PageQueryWrapper<ActivityBigwheelAward>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityBigwheelAward>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelAward> queryAwardList(PageQuery<ActivityBigwheelAward> pageQuery) {
		// TODO Auto-generated method stub
		return (List<ActivityBigwheelAward>)super.query("queryAwardList",pageQuery);
	}

	@Override
	public Integer getAwardSUM(ActivityBigwheelAward aba) {
		// TODO Auto-generated method stub
		return (Integer) super.queryOne("getAwardSUM",aba);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelAward> queryAwardList(Integer activityid) {
		// TODO Auto-generated method stub
		return (List<ActivityBigwheelAward>)super.query("queryAwardLeft",activityid);
	}


}


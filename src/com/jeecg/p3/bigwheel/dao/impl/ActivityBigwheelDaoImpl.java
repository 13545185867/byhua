package com.jeecg.p3.bigwheel.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.bigwheel.dao.ActivityBigwheelDao;
import com.jeecg.p3.bigwheel.entity.ActRuntime;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelConfigPlayRule;

/**
 * 描述：</b>ActivityBigwheelDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
@Repository("activityBigwheelDao")
public class ActivityBigwheelDaoImpl extends GenericDaoDefault<ActivityBigwheel> implements ActivityBigwheelDao{

	@Override
	public Integer count(PageQuery<ActivityBigwheel> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheel> queryPageList(
			PageQuery<ActivityBigwheel> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityBigwheel> wrapper = new PageQueryWrapper<ActivityBigwheel>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityBigwheel>)super.query("queryPageList",wrapper);
	}

	@Override
	public ActivityBigwheel getByIdAndUserid(Integer id, String userid) {
		// TODO Auto-generated method stub
		Map map = new HashMap();  
		map.put("userid", userid);  
		map.put("id", id); 
		return (ActivityBigwheel)super.queryOne("getByIdAndUserid",map);
	}

	@Override
	public List<ActRuntime> getActRuntime() {
		// TODO Auto-generated method stub
		return (List<ActRuntime>)super.query("getActRuntime");
	}

	@Override
	public ActivityBigwheelConfigPlayRule getBigwheelAll(Integer id) {
		// TODO Auto-generated method stub
		return (ActivityBigwheelConfigPlayRule)super.queryOne("getBigwheelAll",id);
	}


}


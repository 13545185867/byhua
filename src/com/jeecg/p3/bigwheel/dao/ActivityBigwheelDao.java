package com.jeecg.p3.bigwheel.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.bigwheel.entity.ActRuntime;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelConfigPlayRule;

/**
 * 描述：</b>ActivityBigwheelDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelDao extends GenericDao<ActivityBigwheel>{
	
	public Integer count(PageQuery<ActivityBigwheel> pageQuery);
	
	public List<ActivityBigwheel> queryPageList(PageQuery<ActivityBigwheel> pageQuery,Integer itemCount);
	
	public ActivityBigwheel getByIdAndUserid(Integer id , String userid);
	
	public List<ActRuntime> getActRuntime();
	
	public ActivityBigwheelConfigPlayRule getBigwheelAll(Integer id);
	
}


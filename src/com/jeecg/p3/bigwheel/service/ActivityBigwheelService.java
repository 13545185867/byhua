package com.jeecg.p3.bigwheel.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.bigwheel.entity.ActRuntime;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelConfigPlayRule;

/**
 * 描述：</b>ActivityBigwheelService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelService {
	
	
	public void doAdd(ActivityBigwheel activityBigwheel);
	
	public void doEdit(ActivityBigwheel activityBigwheel);
	
	public void doDelete(String id);
	
	public ActivityBigwheel queryById(String id);
	
	public PageList<ActivityBigwheel> queryPageList(PageQuery<ActivityBigwheel> pageQuery);
	
	public ActivityBigwheel getByIdAndUserid(Integer id , String userid);
	
	public List<ActRuntime> getActRuntime();
	
	public ActivityBigwheelConfigPlayRule getBigwheelAll(Integer id);
	

}


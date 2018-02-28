package com.jeecg.p3.aboutActivity.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.aboutActivity.entity.ActivityConfig;

/**
 * 描述：</b>ActivityConfigService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分56秒 星期三 
 * @version:1.0
 */
public interface ActivityConfigService {
	
	
	public void doAdd(ActivityConfig activityConfig);
	
	public void doEdit(ActivityConfig activityConfig);
	
	public void doDelete(Integer id);
	public   ActivityConfig   queryOne( Integer activityId) ;
	
	public ActivityConfig queryById(String id);
	
	public PageList<ActivityConfig> queryPageList(PageQuery<ActivityConfig> pageQuery);
	
	public boolean isVip(String modelid ,String currentuser);
}


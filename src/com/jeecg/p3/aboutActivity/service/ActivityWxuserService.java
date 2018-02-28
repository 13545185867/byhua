package com.jeecg.p3.aboutActivity.service;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;

/**
 * 描述：</b>ActivityWxuserService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分22秒 星期三 
 * @version:1.0
 */
public interface ActivityWxuserService {
	
	
	public void doAdd(ActivityWxuser activityWxuser);
	
	public void doEdit(ActivityWxuser activityWxuser);
	
	public void doDelete(String id);
	
	public ActivityWxuser queryById(String id);
	
	public PageList<ActivityWxuser> queryPageList(PageQuery<ActivityWxuser> pageQuery);
	
	public void updateByopenid(ActivityWxuser  pageQuery);
	
	 public  String getRequestUrlWithParams(HttpServletRequest request);
	 
		public Integer count(PageQuery<ActivityWxuser> pageQuery);
		
		public ActivityWxuser getByOpenId(String  pageQuery);
}


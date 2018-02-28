package com.jeecg.p3.aboutActivity.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;

/**
 * 描述：</b>ActivityWxuserDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分22秒 星期三 
 * @version:1.0
 */
public interface ActivityWxuserDao extends GenericDao<ActivityWxuser>{
	
	public Integer count(PageQuery<ActivityWxuser> pageQuery);
	
	public List<ActivityWxuser> queryPageList(PageQuery<ActivityWxuser> pageQuery,Integer itemCount);
	
	public void updateByopenid(ActivityWxuser  pageQuery);
	
	public ActivityWxuser getByOpenId(String  pageQuery);
	
}


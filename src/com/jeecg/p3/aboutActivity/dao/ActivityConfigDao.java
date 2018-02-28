package com.jeecg.p3.aboutActivity.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.aboutActivity.entity.ActivityConfig;

/**
 * 描述：</b>ActivityConfigDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分56秒 星期三 
 * @version:1.0
 */
public interface ActivityConfigDao extends GenericDao<ActivityConfig>{
	
	public Integer count(PageQuery<ActivityConfig> pageQuery);
	
	public List<ActivityConfig> queryPageList(PageQuery<ActivityConfig> pageQuery,Integer itemCount);
	
	public   ActivityConfig queryOne( Integer id);
	
}


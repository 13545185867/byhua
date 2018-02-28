package com.jeecg.p3.aboutActivity.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.aboutActivity.dao.ActivityConfigDao;
import com.jeecg.p3.aboutActivity.entity.ActivityConfig;

/**
 * 描述：</b>ActivityConfigDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分56秒 星期三 
 * @version:1.0
 */
@Repository("activityConfigDao")
public class ActivityConfigDaoImpl extends GenericDaoDefault<ActivityConfig> implements ActivityConfigDao{

	@Override
	public Integer count(PageQuery<ActivityConfig> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityConfig> queryPageList(
			PageQuery<ActivityConfig> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityConfig> wrapper = new PageQueryWrapper<ActivityConfig>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityConfig>)super.query("queryPageList",wrapper);
	}

	@Override
	public   ActivityConfig   queryOne( Integer activityId) {
		// TODO Auto-generated method stub
		return ( ActivityConfig  )super.queryOne("queryOne",activityId);
	}


}


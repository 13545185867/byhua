package com.jeecg.p3.aboutActivity.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.aboutActivity.dao.ActivityWxuserDao;
import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;

/**
 * 描述：</b>ActivityWxuserDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时27分22秒 星期三 
 * @version:1.0
 */
@Repository("activityWxuserDao")
public class ActivityWxuserDaoImpl extends GenericDaoDefault<ActivityWxuser> implements ActivityWxuserDao{

	@Override
	public Integer count(PageQuery<ActivityWxuser> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityWxuser> queryPageList(
			PageQuery<ActivityWxuser> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityWxuser> wrapper = new PageQueryWrapper<ActivityWxuser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityWxuser>)super.query("queryPageList",wrapper);
	}

	@Override
	public void updateByopenid(ActivityWxuser pageQuery) {
		// TODO Auto-generated method stub
		super.queryOne("updateByopenid",pageQuery);
	}

	@Override
	public ActivityWxuser getByOpenId(String pageQuery) {
		// TODO Auto-generated method stub
		return 		(ActivityWxuser) super.queryOne("getByOpenId",pageQuery);
	}



}


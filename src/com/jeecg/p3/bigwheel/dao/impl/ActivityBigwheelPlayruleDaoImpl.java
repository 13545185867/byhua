package com.jeecg.p3.bigwheel.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.bigwheel.dao.ActivityBigwheelPlayruleDao;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;

/**
 * 描述：</b>ActivityBigwheelPlayruleDaoImpl<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时24分30秒 星期三 
 * @version:1.0
 */
@Repository("activityBigwheelPlayruleDao")
public class ActivityBigwheelPlayruleDaoImpl extends GenericDaoDefault<ActivityBigwheelPlayrule> implements ActivityBigwheelPlayruleDao{

	@Override
	public Integer count(PageQuery<ActivityBigwheelPlayrule> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelPlayrule> queryPageList(
			PageQuery<ActivityBigwheelPlayrule> pageQuery,Integer itemCount) {
		PageQueryWrapper<ActivityBigwheelPlayrule> wrapper = new PageQueryWrapper<ActivityBigwheelPlayrule>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<ActivityBigwheelPlayrule>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBigwheelPlayrule> queryBYCol(
			PageQuery<ActivityBigwheelPlayrule> abp) {
		// TODO Auto-generated method stub
		return (List<ActivityBigwheelPlayrule>)super.query("queryBYCol",abp);
	}


}


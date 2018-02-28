package com.jeecg.p3.houseactivity.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.houseactivity.dao.HouseactivityDao;
import com.jeecg.p3.houseactivity.entity.Houseactivity;

/**
 * 描述：</b>HouseactivityDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月11日 14时16分48秒 星期六 
 * @version:1.0
 */
@Repository("houseactivityDao")
public class HouseactivityDaoImpl extends GenericDaoDefault<Houseactivity> implements HouseactivityDao{

	@Override
	public Integer count(PageQuery<Houseactivity> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Houseactivity> queryPageList(
			PageQuery<Houseactivity> pageQuery,Integer itemCount) {
		PageQueryWrapper<Houseactivity> wrapper = new PageQueryWrapper<Houseactivity>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<Houseactivity>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Houseactivity> queryList(Houseactivity house) {
		// TODO Auto-generated method stub
		return (List<Houseactivity>)super.query("queryList",house);
	}


}


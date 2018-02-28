package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.houseaOnline.dao.XfHxDao;
import com.jeecg.p3.houseaOnline.entity.XfHx;

/**
 * 描述：</b>XfHxDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月19日 14时17分34秒 星期日 
 * @version:1.0
 */
@Repository("xfHxDao")
public class XfHxDaoImpl extends GenericDaoDefault<XfHx> implements XfHxDao{

	@Override
	public Integer count(PageQuery<XfHx> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfHx> queryPageList(
			PageQuery<XfHx> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfHx> wrapper = new PageQueryWrapper<XfHx>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfHx>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfHx> queryAllByPid(XfHx xfhx) {
		// TODO Auto-generated method stub
		return (List<XfHx>)super.query("queryAllByPid",xfhx);
	}


}


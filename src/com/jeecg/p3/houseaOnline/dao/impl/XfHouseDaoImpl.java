package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.houseaOnline.dao.XfHouseDao;
import com.jeecg.p3.houseaOnline.entity.XfHouse;

/**
 * 描述：</b>XfHouseDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月19日 18时12分05秒 星期日 
 * @version:1.0
 */
@Repository("xfHouseDao")
public class XfHouseDaoImpl extends GenericDaoDefault<XfHouse> implements XfHouseDao{

	@Override
	public Integer count(PageQuery<XfHouse> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfHouse> queryPageList(
			PageQuery<XfHouse> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfHouse> wrapper = new PageQueryWrapper<XfHouse>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfHouse>)super.query("queryPageList",wrapper);
	}

	@Override
	public XfHouse getAllbyId(XfHouse xf) {
		// TODO Auto-generated method stub
		return (XfHouse) super.queryOne("getAllbyId",xf);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfHouse> queryByCondit(XfHouse xf) {
		// TODO Auto-generated method stub
		return (List<XfHouse>)super.query("queryByCondit",xf);
	}


}


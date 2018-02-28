package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.houseaOnline.dao.XfBuildingDao;
import com.jeecg.p3.houseaOnline.entity.XfBuilding;

/**
 * 描述：</b>XfBuildingDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月19日 15时36分14秒 星期日 
 * @version:1.0
 */
@Repository("xfBuildingDao")
public class XfBuildingDaoImpl extends GenericDaoDefault<XfBuilding> implements XfBuildingDao{

	@Override
	public Integer count(PageQuery<XfBuilding> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfBuilding> queryPageList(
			PageQuery<XfBuilding> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfBuilding> wrapper = new PageQueryWrapper<XfBuilding>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfBuilding>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfBuilding> queryByCondit(XfBuilding xfbuild) {
		// TODO Auto-generated method stub
		return (List<XfBuilding>)super.query("queryByCondit",xfbuild);
	}


}


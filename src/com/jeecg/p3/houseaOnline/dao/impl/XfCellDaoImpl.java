package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.houseaOnline.dao.XfCellDao;
import com.jeecg.p3.houseaOnline.entity.XfCell;

/**
 * 描述：</b>XfCellDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月19日 16时52分42秒 星期日 
 * @version:1.0
 */
@Repository("xfCellDao")
public class XfCellDaoImpl extends GenericDaoDefault<XfCell> implements XfCellDao{

	@Override
	public Integer count(PageQuery<XfCell> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfCell> queryPageList(
			PageQuery<XfCell> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfCell> wrapper = new PageQueryWrapper<XfCell>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfCell>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfCell> queryByCondit(XfCell sfcell) {
		// TODO Auto-generated method stub
		return (List<XfCell>)super.query("queryByCondit",sfcell);
	}


}


package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.houseaOnline.dao.XfOrderDao;
import com.jeecg.p3.houseaOnline.entity.XfOrder;

/**
 * 描述：</b>XfOrderDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月23日 16时32分16秒 星期四 
 * @version:1.0
 */
@Repository("xfOrderDao")
public class XfOrderDaoImpl extends GenericDaoDefault<XfOrder> implements XfOrderDao{

	@Override
	public Integer count(PageQuery<XfOrder> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfOrder> queryPageList(
			PageQuery<XfOrder> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfOrder> wrapper = new PageQueryWrapper<XfOrder>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfOrder>)super.query("queryPageList",wrapper);
	}


}


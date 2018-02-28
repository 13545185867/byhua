package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfOrder;

/**
 * 描述：</b>XfOrderDao<br>
 * @author：chao.hua
 * @since：2017年11月23日 16时32分16秒 星期四 
 * @version:1.0
 */
public interface XfOrderDao extends GenericDao<XfOrder>{
	
	public Integer count(PageQuery<XfOrder> pageQuery);
	
	public List<XfOrder> queryPageList(PageQuery<XfOrder> pageQuery,Integer itemCount);
	
}


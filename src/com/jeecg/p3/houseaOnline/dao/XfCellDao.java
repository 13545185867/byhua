package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfCell;

/**
 * 描述：</b>XfCellDao<br>
 * @author：chao.hua
 * @since：2017年11月19日 16时52分42秒 星期日 
 * @version:1.0
 */
public interface XfCellDao extends GenericDao<XfCell>{
	
	public Integer count(PageQuery<XfCell> pageQuery);
	
	public List<XfCell> queryPageList(PageQuery<XfCell> pageQuery,Integer itemCount);
	
	public List<XfCell> queryByCondit(XfCell sfcell);
}


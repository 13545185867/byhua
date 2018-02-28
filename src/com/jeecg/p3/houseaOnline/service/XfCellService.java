package com.jeecg.p3.houseaOnline.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseaOnline.entity.XfCell;

/**
 * 描述：</b>XfCellService<br>
 * @author：chao.hua
 * @since：2017年11月19日 16时52分42秒 星期日 
 * @version:1.0
 */
public interface XfCellService {
	
	
	public void doAdd(XfCell xfCell);
	
	public void doEdit(XfCell xfCell);
	
	public void doDelete(String id);
	
	public XfCell queryById(String id);
	
	public PageList<XfCell> queryPageList(PageQuery<XfCell> pageQuery);
	
	public List<XfCell> queryByCondit(XfCell sfcell);
}


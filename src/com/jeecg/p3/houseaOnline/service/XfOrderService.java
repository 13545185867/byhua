package com.jeecg.p3.houseaOnline.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.houseaOnline.entity.XfOrder;

/**
 * 描述：</b>XfOrderService<br>
 * @author：chao.hua
 * @since：2017年11月23日 16时32分16秒 星期四 
 * @version:1.0
 */
public interface XfOrderService {
	
	
	public void doAdd(XfOrder xfOrder);
	
	public void doEdit(XfOrder xfOrder);
	
	public void doDelete(String id);
	
	public XfOrder queryById(String id);
	
	public PageList<XfOrder> queryPageList(PageQuery<XfOrder> pageQuery);
}


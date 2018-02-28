package com.jeecg.p3.houseaOnline.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseaOnline.entity.XfHx;

/**
 * 描述：</b>XfHxService<br>
 * @author：chao.hua
 * @since：2017年11月19日 14时17分34秒 星期日 
 * @version:1.0
 */
public interface XfHxService {
	
	
	public void doAdd(XfHx xfHx);
	
	public void doEdit(XfHx xfHx);
	
	public void doDelete(String id);
	
	public XfHx queryById(String id);
	
	public PageList<XfHx> queryPageList(PageQuery<XfHx> pageQuery);
	
	public List<XfHx> queryAllByPid(XfHx xfhx);
}


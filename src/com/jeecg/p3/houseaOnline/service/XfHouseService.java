package com.jeecg.p3.houseaOnline.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseaOnline.entity.XfHouse;

/**
 * 描述：</b>XfHouseService<br>
 * @author：chao.hua
 * @since：2017年11月19日 18时12分05秒 星期日 
 * @version:1.0
 */
public interface XfHouseService {
	
	
	public void doAdd(XfHouse xfHouse);
	
	public void doEdit(XfHouse xfHouse);
	
	public void doDelete(String id);
	
	public XfHouse queryById(String id);
	
	public PageList<XfHouse> queryPageList(PageQuery<XfHouse> pageQuery);
	
	public XfHouse getAllbyId(XfHouse xf);
	
	public List<XfHouse> queryByCondit(XfHouse xf);
}


package com.jeecg.p3.houseaOnline.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseaOnline.entity.XfBuilding;

/**
 * 描述：</b>XfBuildingService<br>
 * @author：chao.hua
 * @since：2017年11月19日 15时36分14秒 星期日 
 * @version:1.0
 */
public interface XfBuildingService {
	
	
	public void doAdd(XfBuilding xfBuilding);
	
	public void doEdit(XfBuilding xfBuilding);
	
	public void doDelete(String id);
	
	public XfBuilding queryById(String id);
	
	public PageList<XfBuilding> queryPageList(PageQuery<XfBuilding> pageQuery);
	
	public List<XfBuilding> queryByCondit(XfBuilding xfbuild);
}


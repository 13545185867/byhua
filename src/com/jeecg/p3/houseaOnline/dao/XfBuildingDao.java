package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfBuilding;

/**
 * 描述：</b>XfBuildingDao<br>
 * @author：chao.hua
 * @since：2017年11月19日 15时36分14秒 星期日 
 * @version:1.0
 */
public interface XfBuildingDao extends GenericDao<XfBuilding>{
	
	public Integer count(PageQuery<XfBuilding> pageQuery);
	
	public List<XfBuilding> queryPageList(PageQuery<XfBuilding> pageQuery,Integer itemCount);
	
	public List<XfBuilding> queryByCondit(XfBuilding xfbuild);
	
}


package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfHouse;

/**
 * 描述：</b>XfHouseDao<br>
 * @author：chao.hua
 * @since：2017年11月19日 18时12分05秒 星期日 
 * @version:1.0
 */
public interface XfHouseDao extends GenericDao<XfHouse>{
	
	public Integer count(PageQuery<XfHouse> pageQuery);
	
	public List<XfHouse> queryPageList(PageQuery<XfHouse> pageQuery,Integer itemCount);
	
	public XfHouse getAllbyId(XfHouse xf);
	
	public List<XfHouse> queryByCondit(XfHouse xf);
	
}


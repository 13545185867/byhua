package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfHx;

/**
 * 描述：</b>XfHxDao<br>
 * @author：chao.hua
 * @since：2017年11月19日 14时17分34秒 星期日 
 * @version:1.0
 */
public interface XfHxDao extends GenericDao<XfHx>{
	
	public Integer count(PageQuery<XfHx> pageQuery);
	
	public List<XfHx> queryPageList(PageQuery<XfHx> pageQuery,Integer itemCount);
	
	public List<XfHx> queryAllByPid(XfHx xfhx);
	
}


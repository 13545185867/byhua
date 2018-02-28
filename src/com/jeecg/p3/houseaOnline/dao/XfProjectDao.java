package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfProject;

/**
 * 描述：</b>XfProjectDao<br>
 * @author：chao.hua
 * @since：2017年11月17日 12时55分27秒 星期五 
 * @version:1.0
 */
public interface XfProjectDao extends GenericDao<XfProject>{
	
	public Integer count(PageQuery<XfProject> pageQuery);
	
	public List<XfProject> queryPageList(PageQuery<XfProject> pageQuery,Integer itemCount);
	
}


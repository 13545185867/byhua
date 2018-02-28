package com.jeecg.p3.houseaOnline.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseaOnline.entity.XfFollow;

/**
 * 描述：</b>XfFollowDao<br>
 * @author：chao.hua
 * @since：2017年11月25日 15时07分14秒 星期六 
 * @version:1.0
 */
public interface XfFollowDao extends GenericDao<XfFollow>{
	
	public Integer count(PageQuery<XfFollow> pageQuery);
	
	public List<XfFollow> queryPageList(PageQuery<XfFollow> pageQuery,Integer itemCount);
	
	public void deleteByridAndUser(XfFollow xffollow);
	
}


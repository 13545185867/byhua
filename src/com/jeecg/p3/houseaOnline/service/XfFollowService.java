package com.jeecg.p3.houseaOnline.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseaOnline.entity.XfFollow;

/**
 * 描述：</b>XfFollowService<br>
 * @author：chao.hua
 * @since：2017年11月25日 15时07分14秒 星期六 
 * @version:1.0
 */
public interface XfFollowService {
	
	
	public void doAdd(XfFollow xfFollow);
	
	public void doEdit(XfFollow xfFollow);
	
	public void doDelete(String id);
	
	public XfFollow queryById(String id);
	
	public PageList<XfFollow> queryPageList(PageQuery<XfFollow> pageQuery);
	
	public Integer count(PageQuery<XfFollow> pageQuery);
	
	public void deleteByridAndUser(XfFollow xffollow);
}


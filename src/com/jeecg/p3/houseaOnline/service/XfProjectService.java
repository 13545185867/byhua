package com.jeecg.p3.houseaOnline.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.houseaOnline.entity.XfProject;

/**
 * 描述：</b>XfProjectService<br>
 * @author：chao.hua
 * @since：2017年11月17日 12时55分27秒 星期五 
 * @version:1.0
 */
public interface XfProjectService {
	
	
	public void doAdd(XfProject xfProject);
	
	public void doEdit(XfProject xfProject);
	
	public void doDelete(String id);
	
	public XfProject queryById(String id);
	
	public PageList<XfProject> queryPageList(PageQuery<XfProject> pageQuery);
}


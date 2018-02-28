package com.jeecg.p3.toupiao.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoAConfig;

/**
 * 描述：</b>WxActToupiaoService<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxActToupiaoService {
	
	
	public void doAdd(WxActToupiao wxActToupiao);
	
	public void doEdit(WxActToupiao wxActToupiao);
	
	public void doDelete(String id);
	
	public WxActToupiao queryById(String id);
	
	public PageList<WxActToupiao> queryPageList(PageQuery<WxActToupiao> pageQuery);
	
	public WxActToupiaoAConfig queryByActId(String actid);
}


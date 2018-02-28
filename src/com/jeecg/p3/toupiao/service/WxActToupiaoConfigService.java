package com.jeecg.p3.toupiao.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.toupiao.entity.WxActToupiaoConfig;

/**
 * 描述：</b>WxActToupiaoConfigService<br>
 * @author：chao.hua
 * @since：2017年06月22日 17时16分49秒 星期四 
 * @version:1.0
 */
public interface WxActToupiaoConfigService {
	
	
	public void doAdd(WxActToupiaoConfig wxActToupiaoConfig);
	
	public void doEdit(WxActToupiaoConfig wxActToupiaoConfig);
	
	public void doDelete(String id);
	
	public WxActToupiaoConfig queryById(String id);
	
	public PageList<WxActToupiaoConfig> queryPageList(PageQuery<WxActToupiaoConfig> pageQuery);
	
	public  WxActToupiaoConfig  getByActId(String actid) ;
	
	public void updateByActId(WxActToupiaoConfig wxtc);
	
	public void deleteByActId(String actid) ;
}


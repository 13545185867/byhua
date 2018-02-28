package com.jeecg.p3.toupiao.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;

/**
 * 描述：</b>WxActToupiaoOptionService<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxActToupiaoOptionService {
	
	
	public void doAdd(WxActToupiaoOption wxActToupiaoOption);
	
	public void doEdit(WxActToupiaoOption wxActToupiaoOption);
	
	public void updateVoteCount(String id);
	
	public void doDelete(String id);
	
	public WxActToupiaoOption queryById(String id);
	
	public PageList<WxActToupiaoOption> queryPageList(PageQuery<WxActToupiaoOption> pageQuery);
	
	public java.lang.Integer getSumByActId(String actId);
	
	public List<WxActToupiaoOption> getVoteOrder(String actId);
}


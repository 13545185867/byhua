package com.jeecg.p3.toupiao.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;

/**
 * 描述：</b>WxCatToupiaoRecordService<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxCatToupiaoRecordService {
	
	
	public void doAdd(WxCatToupiaoRecord wxCatToupiaoRecord);
	
	public void doEdit(WxCatToupiaoRecord wxCatToupiaoRecord);
	
	public void doDelete(String id);
	
	public WxCatToupiaoRecord queryById(String id);
	
	public PageList<WxCatToupiaoRecord> queryPageList(PageQuery<WxCatToupiaoRecord> pageQuery);
	
	public List<WxCatToupiaoRecord> getByOpenIDATime(WxCatToupiaoRecord wxtr) ;
}


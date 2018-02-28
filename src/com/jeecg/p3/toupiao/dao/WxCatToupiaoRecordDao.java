package com.jeecg.p3.toupiao.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;

/**
 * 描述：</b>WxCatToupiaoRecordDao<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxCatToupiaoRecordDao extends GenericDao<WxCatToupiaoRecord>{
	
	public Integer count(PageQuery<WxCatToupiaoRecord> pageQuery);
	
	public List<WxCatToupiaoRecord> queryPageList(PageQuery<WxCatToupiaoRecord> pageQuery,Integer itemCount);
	
	public List<WxCatToupiaoRecord> getByOpenIDATime(WxCatToupiaoRecord wxtr);
	
}


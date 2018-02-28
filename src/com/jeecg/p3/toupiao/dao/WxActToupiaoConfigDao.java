package com.jeecg.p3.toupiao.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.toupiao.entity.WxActToupiaoConfig;

/**
 * 描述：</b>WxActToupiaoConfigDao<br>
 * @author：chao.hua
 * @since：2017年06月22日 17时16分49秒 星期四 
 * @version:1.0
 */
public interface WxActToupiaoConfigDao extends GenericDao<WxActToupiaoConfig>{
	
	public Integer count(PageQuery<WxActToupiaoConfig> pageQuery);
	
	public List<WxActToupiaoConfig> queryPageList(PageQuery<WxActToupiaoConfig> pageQuery,Integer itemCount);
	
	public  WxActToupiaoConfig  getByActId(String actid) ;
	
	public void updateByActId(WxActToupiaoConfig wxtc);
	
	public void deleteByActId(String actid) ;
	
}


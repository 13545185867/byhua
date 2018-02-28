package com.jeecg.p3.toupiao.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoAConfig;

/**
 * 描述：</b>WxActToupiaoDao<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxActToupiaoDao extends GenericDao<WxActToupiao>{
	
	public Integer count(PageQuery<WxActToupiao> pageQuery);
	
	public List<WxActToupiao> queryPageList(PageQuery<WxActToupiao> pageQuery,Integer itemCount);
	
	public WxActToupiaoAConfig queryByActId(String actid);
	
}


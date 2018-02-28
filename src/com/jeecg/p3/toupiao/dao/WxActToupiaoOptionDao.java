package com.jeecg.p3.toupiao.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;

/**
 * 描述：</b>WxActToupiaoOptionDao<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public interface WxActToupiaoOptionDao extends GenericDao<WxActToupiaoOption>{
	
	public Integer count(PageQuery<WxActToupiaoOption> pageQuery);
	
	public List<WxActToupiaoOption> queryPageList(PageQuery<WxActToupiaoOption> pageQuery,Integer itemCount);
	
	public Integer getSumByActId(String actId);
	
	public void updateVoteCount(String actId);
	
	public  List<WxActToupiaoOption> getVoteOrder(String actId);
	
}


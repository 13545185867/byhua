package com.jeecg.p3.toupiao.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.toupiao.dao.WxActToupiaoDao;
import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoAConfig;

/**
 * 描述：</b>WxActToupiaoDaoImpl<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Repository("wxActToupiaoDao")
public class WxActToupiaoDaoImpl extends GenericDaoDefault<WxActToupiao> implements WxActToupiaoDao{

	@Override
	public Integer count(PageQuery<WxActToupiao> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActToupiao> queryPageList(
			PageQuery<WxActToupiao> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActToupiao> wrapper = new PageQueryWrapper<WxActToupiao>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActToupiao>)super.query("queryPageList",wrapper);
	}

	@Override
	public WxActToupiaoAConfig queryByActId(String actid) {
		// TODO Auto-generated method stub
		return (WxActToupiaoAConfig) super.queryOne("getToupiaoAndConfig",actid);
	}


}


package com.jeecg.p3.toupiao.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.toupiao.dao.WxActToupiaoOptionDao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;

/**
 * 描述：</b>WxActToupiaoOptionDaoImpl<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Repository("wxActToupiaoOptionDao")
public class WxActToupiaoOptionDaoImpl extends GenericDaoDefault<WxActToupiaoOption> implements WxActToupiaoOptionDao{

	@Override
	public Integer count(PageQuery<WxActToupiaoOption> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActToupiaoOption> queryPageList(
			PageQuery<WxActToupiaoOption> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActToupiaoOption> wrapper = new PageQueryWrapper<WxActToupiaoOption>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActToupiaoOption>)super.query("queryPageList",wrapper);
	}

	@Override
	public Integer getSumByActId(String actId) {
		// TODO Auto-generated method stub
		return (Integer) super.queryOne("getSumByActId",actId);
	}

	@Override
	public void updateVoteCount(String actId) {
		// TODO Auto-generated method stub
		super.query("updateVoteCount",actId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActToupiaoOption> getVoteOrder(String actId) {
		// TODO Auto-generated method stub
		return (List<WxActToupiaoOption>)super.query("getVoteOrder",actId);
	}


}


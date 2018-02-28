package com.jeecg.p3.toupiao.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.toupiao.dao.WxActToupiaoConfigDao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoConfig;

/**
 * 描述：</b>WxActToupiaoConfigDaoImpl<br>
 * @author：chao.hua
 * @since：2017年06月22日 17时16分49秒 星期四 
 * @version:1.0
 */
@Repository("wxActToupiaoConfigDao")
public class WxActToupiaoConfigDaoImpl extends GenericDaoDefault<WxActToupiaoConfig> implements WxActToupiaoConfigDao{

	@Override
	public Integer count(PageQuery<WxActToupiaoConfig> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActToupiaoConfig> queryPageList(
			PageQuery<WxActToupiaoConfig> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActToupiaoConfig> wrapper = new PageQueryWrapper<WxActToupiaoConfig>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActToupiaoConfig>)super.query("queryPageList",wrapper);
	}

	@Override
	public WxActToupiaoConfig getByActId(String actid) {
		// TODO Auto-generated method stub
		return (WxActToupiaoConfig)super.queryOne("getByActId", actid);
	}

	@Override
	public void updateByActId(WxActToupiaoConfig wxtc) {
		// TODO Auto-generated method stub
		super.update("updateByActId", wxtc);
	}

	@Override
	public void deleteByActId(String actid) {
		// TODO Auto-generated method stub
		super.delete("deleteByActId", actid);
	}


}


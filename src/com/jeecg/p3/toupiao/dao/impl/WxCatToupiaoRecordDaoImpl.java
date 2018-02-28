package com.jeecg.p3.toupiao.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.toupiao.dao.WxCatToupiaoRecordDao;
import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;

/**
 * 描述：</b>WxCatToupiaoRecordDaoImpl<br>
 * @author：chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Repository("wxCatToupiaoRecordDao")
public class WxCatToupiaoRecordDaoImpl extends GenericDaoDefault<WxCatToupiaoRecord> implements WxCatToupiaoRecordDao{

	@Override
	public Integer count(PageQuery<WxCatToupiaoRecord> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxCatToupiaoRecord> queryPageList(
			PageQuery<WxCatToupiaoRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxCatToupiaoRecord> wrapper = new PageQueryWrapper<WxCatToupiaoRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxCatToupiaoRecord>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxCatToupiaoRecord> getByOpenIDATime(WxCatToupiaoRecord wxtr) {
		// TODO Auto-generated method stub
		return (List<WxCatToupiaoRecord>)super.query("getByOpenIDATime",wxtr);
	}


}


package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.houseaOnline.dao.XfFollowDao;
import com.jeecg.p3.houseaOnline.entity.XfFollow;

/**
 * 描述：</b>XfFollowDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月25日 15时07分14秒 星期六 
 * @version:1.0
 */
@Repository("xfFollowDao")
public class XfFollowDaoImpl extends GenericDaoDefault<XfFollow> implements XfFollowDao{

	@Override
	public Integer count(PageQuery<XfFollow> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfFollow> queryPageList(
			PageQuery<XfFollow> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfFollow> wrapper = new PageQueryWrapper<XfFollow>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfFollow>)super.query("queryPageList",wrapper);
	}

	@Override
	public void deleteByridAndUser(XfFollow xffollow) {
		// TODO Auto-generated method stub
		super.queryOne("deleteByridAndUser",xffollow);
	}


}


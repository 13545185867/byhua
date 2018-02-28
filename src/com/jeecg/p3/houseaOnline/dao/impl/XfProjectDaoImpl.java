package com.jeecg.p3.houseaOnline.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.houseaOnline.dao.XfProjectDao;
import com.jeecg.p3.houseaOnline.entity.XfProject;

/**
 * 描述：</b>XfProjectDaoImpl<br>
 * @author：chao.hua
 * @since：2017年11月17日 12时55分27秒 星期五 
 * @version:1.0
 */
@Repository("xfProjectDao")
public class XfProjectDaoImpl extends GenericDaoDefault<XfProject> implements XfProjectDao{

	@Override
	public Integer count(PageQuery<XfProject> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XfProject> queryPageList(
			PageQuery<XfProject> pageQuery,Integer itemCount) {
		PageQueryWrapper<XfProject> wrapper = new PageQueryWrapper<XfProject>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<XfProject>)super.query("queryPageList",wrapper);
	}


}


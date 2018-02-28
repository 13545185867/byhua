package com.jeecg.p3.aboutActivity.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.aboutActivity.dao.AwardApproveUserDao;
import com.jeecg.p3.aboutActivity.entity.AwardApproveUser;

/**
 * 描述：</b>AwardApproveUserDaoImpl<br>
 * @author：chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
@Repository("awardApproveUserDao")
public class AwardApproveUserDaoImpl extends GenericDaoDefault<AwardApproveUser> implements AwardApproveUserDao{

	@Override
	public Integer count(PageQuery<AwardApproveUser> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AwardApproveUser> queryPageList(
			PageQuery<AwardApproveUser> pageQuery,Integer itemCount) {
		PageQueryWrapper<AwardApproveUser> wrapper = new PageQueryWrapper<AwardApproveUser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<AwardApproveUser>)super.query("queryPageList",wrapper);
	}


}


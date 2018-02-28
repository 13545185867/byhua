package com.jeecg.p3.aboutActivity.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.aboutActivity.entity.AwardApproveUser;

/**
 * 描述：</b>AwardApproveUserDao<br>
 * @author：chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
public interface AwardApproveUserDao extends GenericDao<AwardApproveUser>{
	
	public Integer count(PageQuery<AwardApproveUser> pageQuery);
	
	public List<AwardApproveUser> queryPageList(PageQuery<AwardApproveUser> pageQuery,Integer itemCount);
	
}


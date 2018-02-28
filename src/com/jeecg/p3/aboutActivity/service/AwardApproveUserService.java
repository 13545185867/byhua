package com.jeecg.p3.aboutActivity.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.aboutActivity.entity.AwardApproveUser;

/**
 * 描述：</b>AwardApproveUserService<br>
 * @author：chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
public interface AwardApproveUserService {
	
	
	public void doAdd(AwardApproveUser awardApproveUser);
	
	public void doEdit(AwardApproveUser awardApproveUser);
	
	public void doDelete(String id);
	
	public AwardApproveUser queryById(String id);
	
	public PageList<AwardApproveUser> queryPageList(PageQuery<AwardApproveUser> pageQuery);
	
	public Integer count(PageQuery<AwardApproveUser> pageQuery);
}


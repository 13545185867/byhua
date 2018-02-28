package com.jeecg.p3.bigwheel.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;

/**
 * 描述：</b>ActivityBigwheelAwardService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时21分57秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelAwardService {
	
	
	public void doAdd(ActivityBigwheelAward activityBigwheelAward);
	
	public void doEdit(ActivityBigwheelAward activityBigwheelAward);
	
	public void doDelete(Integer id);
	
	public ActivityBigwheelAward queryById(String id);
	
	public PageList<ActivityBigwheelAward> queryPageList(PageQuery<ActivityBigwheelAward> pageQuery);
	
	public List<ActivityBigwheelAward> queryAwardList(PageQuery<ActivityBigwheelAward> pageQuery) ;
	
	public Integer getAwardSUM(ActivityBigwheelAward aba);
	
	public List<ActivityBigwheelAward> queryAwardList(Integer activityid);
}


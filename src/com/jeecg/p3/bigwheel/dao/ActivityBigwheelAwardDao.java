package com.jeecg.p3.bigwheel.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;

/**
 * 描述：</b>ActivityBigwheelAwardDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时21分57秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelAwardDao extends GenericDao<ActivityBigwheelAward>{
	
	public Integer count(PageQuery<ActivityBigwheelAward> pageQuery);
	
	public List<ActivityBigwheelAward> queryPageList(PageQuery<ActivityBigwheelAward> pageQuery,Integer itemCount);
	
	public List<ActivityBigwheelAward> queryAwardList(PageQuery<ActivityBigwheelAward> pageQuery);
	
	public Integer getAwardSUM(ActivityBigwheelAward aba);
	
	public List<ActivityBigwheelAward> queryAwardList(Integer activityid);
	
}


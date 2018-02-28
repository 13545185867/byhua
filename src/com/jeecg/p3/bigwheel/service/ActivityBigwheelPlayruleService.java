package com.jeecg.p3.bigwheel.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;

/**
 * 描述：</b>ActivityBigwheelPlayruleService<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时24分30秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelPlayruleService {
	
	
	public void doAdd(ActivityBigwheelPlayrule activityBigwheelPlayrule);
	
	public void doEdit(ActivityBigwheelPlayrule activityBigwheelPlayrule);
	
	public void doDelete(Integer id);
	
	public ActivityBigwheelPlayrule queryById(String id);
	
	public PageList<ActivityBigwheelPlayrule> queryPageList(PageQuery<ActivityBigwheelPlayrule> pageQuery);
	
	public List<ActivityBigwheelPlayrule> queryBYCol(PageQuery<ActivityBigwheelPlayrule> abp);
	


}


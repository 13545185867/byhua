package com.jeecg.p3.activity.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>Activity:会员活动<br>
 * @author chao.hua
 * @since：2017年09月11日 11时58分40秒 星期一 
 * @version:1.0
 */
public class Activity implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	
	private Integer viewcount;
	
	private Date activitytime;
	public Date getActivitytime() {
		return activitytime;
	}
	public void setActivitytime(Date activitytime) {
		this.activitytime = activitytime;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
}

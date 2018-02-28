package com.jeecg.p3.toupiao.entity;

import java.util.Date;
import java.math.BigDecimal;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActToupiao:<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public class WxActToupiao implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	
	private String attention;
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}


	private Date endEnterTime;
	
	public Date getEndEnterTime() {
		return endEnterTime;
	}
	public void setEndEnterTime(Date endEnterTime) {
		this.endEnterTime = endEnterTime;
	}
	
	private String userid;
	
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


	private String isActive;
}

package com.jeecg.p3.aboutActivity.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>ActivityConfig:<br>
 * @author chao.hua
 * @since：2017年08月02日 16时27分56秒 星期三 
 * @version:1.0
 */
public class ActivityConfig implements Entity<Integer> {
	private static final long serialVersionUID = 1L;
	
1为开启
1为自定义图片
1是为自定义
0为否
1为是
现场活动需现场参加
	
	
	private String participateLimit;
	
		return participateLimit;
	}
	public void setParticipateLimit(String participateLimit) {
		this.participateLimit = participateLimit;
	}
	/**
}

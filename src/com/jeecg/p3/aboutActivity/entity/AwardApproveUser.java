package com.jeecg.p3.aboutActivity.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>AwardApproveUser:<br>
 * @author chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
public class AwardApproveUser implements Entity<Integer> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	/**	 *活动id	 */	private String activityid;	/**	 *核销者微信openid	 */	private String openid;	/**	 *	 */	private String nickname;	/**	 *	 */	private String headpic;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getActivityid() {	    return this.activityid;	}	public void setActivityid(String activityid) {	    this.activityid=activityid;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getHeadpic() {	    return this.headpic;	}	public void setHeadpic(String headpic) {	    this.headpic=headpic;	}
}


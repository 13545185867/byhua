package com.jeecg.p3.bigwheel.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>ActivityBigwheelRecord:<br>
 * @author chao.hua
 * @since：2017年08月02日 16时18分28秒 星期三 
 * @version:1.0
 */
public class ActivityBigwheelRecord implements Entity<Integer> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	/**	 *	 */	private Integer activityid;	/**	 *	 */	private Integer awardid;	/**	 *	 */	private Date dotime;	/**	 *	 */	private String activityRecordcol;	/**	 *	 */	private String sncode;	/**	 *	 */	private String ip;	/**	 *-1,0未领取
1已领取待兑换
2已过期
3已使用
4已回收
5已回收
6已手动派发	 */	private String status;	/**	 *	 */	private String openid;	/**	 *为该OPENid的朋友助力	 */	private String helpid;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getActivityid() {	    return this.activityid;	}	public void setActivityid(Integer activityid) {	    this.activityid=activityid;	}	public Integer getAwardid() {	    return this.awardid;	}	public void setAwardid(Integer awardid) {	    this.awardid=awardid;	}	public Date getDotime() {	    return this.dotime;	}	public void setDotime(Date dotime) {	    this.dotime=dotime;	}	public String getActivityRecordcol() {	    return this.activityRecordcol;	}	public void setActivityRecordcol(String activityRecordcol) {	    this.activityRecordcol=activityRecordcol;	}	public String getSncode() {	    return this.sncode;	}	public void setSncode(String sncode) {	    this.sncode=sncode;	}	public String getIp() {	    return this.ip;	}	public void setIp(String ip) {	    this.ip=ip;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getHelpid() {	    return this.helpid;	}	public void setHelpid(String helpid) {	    this.helpid=helpid;	}
}


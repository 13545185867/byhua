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
public class UserAwardRecord implements Entity<Integer> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	
	
	private Integer awardtype;
	
	private Integer awardid;
	
	
	public Integer getAwardid() {
		return awardid;
	}
	public void setAwardid(Integer awardid) {
		this.awardid = awardid;
	}
	public String getSncode() {
		return sncode;
	}
	public void setSncode(String sncode) {
		this.sncode = sncode;
	}
	private String telphone;
	
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getAwardTypeId() {
		return awardTypeId;
	}
	public void setAwardTypeId(Integer awardTypeId) {
		this.awardTypeId = awardTypeId;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getTrophyName() {
		return trophyName;
	}
	public void setTrophyName(String trophyName) {
		this.trophyName = trophyName;
	}
	public Integer getAwardtype() {
	    return this.awardtype;
	}
	public void setAwardtype(Integer awardtype) {
	    this.awardtype=awardtype;
	}
	public Integer getAwardSendType() {
		return awardSendType;
	}
	public void setAwardSendType(Integer awardSendType) {
		this.awardSendType = awardSendType;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getAwardValue() {
		return awardValue;
	}
	public void setAwardValue(Integer awardValue) {
		this.awardValue = awardValue;
	}
	public String getSnCode() {
		return sncode;
	}
	public void setSnCode(String sncode) {
		this.sncode = sncode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getValidityStart() {
		return validityStart;
	}
	public void setValidityStart(Date validityStart) {
		this.validityStart = validityStart;
	}
	public Date getValidityStop() {
		return validityStop;
	}
	public void setValidityStop(Date validityStop) {
		this.validityStop = validityStop;
	}
	public Integer getAwardLevel() {
		return awardLevel;
	}
	public void setAwardLevel(Integer awardLevel) {
		this.awardLevel = awardLevel;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	private Integer platformId;	private Integer activityId;
	private String awardPic;
	public String getAwardPic() {
		return awardPic;
	}
	public void setAwardPic(String awardPic) {
		this.awardPic = awardPic;
	}
	private String openid;
	private String headPic;
	private String nickName;	private Integer awardTypeId;
	private String awardName;
	private String trophyName;
	private Integer awardSendType;
	private Integer score;
	private Integer awardValue;	private String sncode;
	private Date date;
	private String status;
	private Date validityStart;
	private Date validityStop;
	private Integer awardLevel;
	private String ip;
	private String checked;
}


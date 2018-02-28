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
		/**	 *投票活动ID	 */	private String id;	/**	 *投票主题	 */	private String actName;	/**	 *投票开始时间	 */	private Date beginTime;	/**	 *投票结束时间	 */	private Date endTime;	/**	 *投票创建时间	 */	private Date creatTime;	/**	 *投票介绍	 */	private String description;	/**	 *投票访问地址	 */
	
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
	}	private String hdurl;	/**	 *对应微信平台APP ID	 */	private String jwid;	/**	 *程序ID	 */	private String projectCode;	/**	 *投票首页宣传图	 */	private String banner;	/**	 *是否已删除 0为未删除 1为已删除	 */	private String isDelete;	/**	 *是否启动该活动,0不启动,1为启动	 */
	
	private String userid;
		public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


	private String isActive;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public Date getBeginTime() {	    return this.beginTime;	}	public void setBeginTime(Date beginTime) {	    this.beginTime=beginTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(Date creatTime) {	    this.creatTime=creatTime;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}	public String getBanner() {	    return this.banner;	}	public void setBanner(String banner) {	    this.banner=banner;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public String getIsActive() {	    return this.isActive;	}	public void setIsActive(String isActive) {	    this.isActive=isActive;	}
}


package com.jeecg.p3.bigwheel.entity;

import java.util.Date;
import java.math.BigDecimal;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>ActivityBigwheel:<br>
 * @author chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
public class ActivityBigwheel implements Entity<Integer> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;
	
	private String isBuy;	/**	 *活动名称	 */	private String title;	/**	 *	 */	private Date end;	/**	 *	 */	private Date start;	/**	 *是否显示参与人数0隐藏1为显示	 */	private String hideparticipant;	/**	 *活动规则	 */	private String ruletips;	/**	 *1为已删除
0为正常	 */	private Integer deleted;	/**	 *活动类型	 */	private String activitytype;	/**	 *所属公众号	 */	private String jwid;	/**	 *创建人	 */	private String userid;	/**	 *活动界面设计	 */	private String pagejsoncontent;	/**	 *	 */	private String ispublish;	/**	 *总中奖率	 */	private Integer globalprobability;	/**	 *是否均匀发放	 */	private Integer probabilitytype;	/**	 *获奖验证	 */	private String trophyvalidator;	/**	 *助力环节图片及布局	 */	private String qrcodeurl;	/**	 *	 */
	private Date creatDate;
	
	private Double everydayprob;
	
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	private String extendOperation;	public String getExtendOperation() {
		return extendOperation;
	}
	public void setExtendOperation(String extendOperation) {
		this.extendOperation = extendOperation;
	}
	private Integer awardsendtype;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public Date getEnd() {	    return this.end;	}	public void setEnd(Date end) {	    this.end=end;	}	public Date getStart() {	    return this.start;	}	public void setStart(Date start) {	    this.start=start;	}	public String getHideparticipant() {	    return this.hideparticipant;	}	public void setHideparticipant(String hideparticipant) {	    this.hideparticipant=hideparticipant;	}	public String getRuletips() {	    return this.ruletips;	}	public void setRuletips(String ruletips) {	    this.ruletips=ruletips;	}	public Integer getDeleted() {	    return this.deleted;	}	public void setDeleted(Integer deleted) {	    this.deleted=deleted;	}	public String getActivitytype() {	    return this.activitytype;	}	public void setActivitytype(String activitytype) {	    this.activitytype=activitytype;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getUserid() {	    return this.userid;	}	public void setUserid(String userid) {	    this.userid=userid;	}	public String getPagejsoncontent() {	    return this.pagejsoncontent;	}	public void setPagejsoncontent(String pagejsoncontent) {	    this.pagejsoncontent=pagejsoncontent;	}	 	public Integer getGlobalprobability() {	    return this.globalprobability;	}	public void setGlobalprobability(Integer globalprobability) {	    this.globalprobability=globalprobability;	}	public Integer getProbabilitytype() {	    return this.probabilitytype;	}	public void setProbabilitytype(Integer probabilitytype) {	    this.probabilitytype=probabilitytype;	}	public String getTrophyvalidator() {	    return this.trophyvalidator;	}	public void setTrophyvalidator(String trophyvalidator) {	    this.trophyvalidator=trophyvalidator;	}	public String getQrcodeurl() {	    return this.qrcodeurl;	}	public void setQrcodeurl(String qrcodeurl) {	    this.qrcodeurl=qrcodeurl;	}	public Integer getAwardsendtype() {	    return this.awardsendtype;	}	public void setAwardsendtype(Integer awardsendtype) {	    this.awardsendtype=awardsendtype;	}
	public String getIspublish() {
		return ispublish;
	}
	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}
	public String getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}
	public Double getEverydayprob() {
		return everydayprob;
	}
	public void setEverydayprob(Double everydayprob) {
		this.everydayprob = everydayprob;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}


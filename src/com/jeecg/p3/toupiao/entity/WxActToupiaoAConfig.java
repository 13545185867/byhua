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
public class WxActToupiaoAConfig implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *投票活动ID	 */	private String id;	/**	 *投票主题	 */	private String actName;	/**	 *投票开始时间	 */	private Date beginTime;	/**	 *投票结束时间	 */	private Date endTime;	/**	 *投票创建时间	 */	private Date creatTime;	/**	 *投票介绍	 */	private String description;
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

	/**	 *投票访问地址	 */	private String hdurl;	/**	 *对应微信平台APP ID	 */	private String jwid;	/**	 *程序ID	 */	private String projectCode;	/**	 *投票首页宣传图	 */	private String banner;	/**	 *是否已删除 0为未删除 1为已删除	 */	private String isDelete;	/**	 *是否启动该活动,0不启动,1为启动	 */	private String isActive;
	private String isEnter;
	public String getIsEnter() {
		return isEnter;
	}
	public void setIsEnter(String isEnter) {
		this.isEnter = isEnter;
	}	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public Date getBeginTime() {	    return this.beginTime;	}	public void setBeginTime(Date beginTime) {	    this.beginTime=beginTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(Date creatTime) {	    this.creatTime=creatTime;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}	public String getBanner() {	    return this.banner;	}	public void setBanner(String banner) {	    this.banner=banner;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public String getIsActive() {	    return this.isActive;	}	public void setIsActive(String isActive) {	    this.isActive=isActive;	}
	
	private String userid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 *是否允许多次投票 0为否 1为是
	 */
	private String isManyVtoe;
	/**
	 *每天投票次数
	 */
	private Integer verydayMaxVotecount;
	/**
	 *是否关注后投票，0为否 1为是
	 */
	private String foucsUserCanJoin;
	/**
	 *投票方式：单选、多选  0为单选 1为多选
	 */
	private String voteType;
	/**
	 *选项显示样式，1为每行一个 2为每行2个  3为每行三个
	 */
	private String optionViewStyle;
	/**
	 *选项称谓
	 */
	private String optionAddr;
	/**
	 *选项量词
	 */
	private String optionClassic;
	/**
	 *是否显示搜索 0为否 1为是
	 */
	private String isViewSearch;
	/**
	 *是否显示活动介绍 0为否 1为是
	 */
	private String isViewDescript;
	/**
	 *是否显示活动统计
	 */
	private String isViewTongji;
	/**
	 *是否显示选项票数
	 */
	private String checkVoteCount;
	/**
	 *活动ID
	 */
	private String pageViewStyle;
	
	private String actId;
	public String getActId() {
	    return this.actId;
	}
	public void setActId(String actId) {
	    this.actId=actId;
	}
	
	private String isViewPiaoshu;

	public String getIsManyVtoe() {
	    return this.isManyVtoe;
	}
	public void setIsManyVtoe(String isManyVtoe) {
	    this.isManyVtoe=isManyVtoe;
	}
	public Integer getVerydayMaxVotecount() {
	    return this.verydayMaxVotecount;
	}
	public void setVerydayMaxVotecount(Integer verydayMaxVotecount) {
	    this.verydayMaxVotecount=verydayMaxVotecount;
	}
	public String getFoucsUserCanJoin() {
	    return this.foucsUserCanJoin;
	}
	public void setFoucsUserCanJoin(String foucsUserCanJoin) {
	    this.foucsUserCanJoin=foucsUserCanJoin;
	}
	public String getVoteType() {
	    return this.voteType;
	}
	public void setVoteType(String voteType) {
	    this.voteType=voteType;
	}
	public String getOptionViewStyle() {
	    return this.optionViewStyle;
	}
	public void setOptionViewStyle(String optionViewStyle) {
	    this.optionViewStyle=optionViewStyle;
	}
	public String getOptionAddr() {
	    return this.optionAddr;
	}
	public void setOptionAddr(String optionAddr) {
	    this.optionAddr=optionAddr;
	}
	public String getOptionClassic() {
	    return this.optionClassic;
	}
	public void setOptionClassic(String optionClassic) {
	    this.optionClassic=optionClassic;
	}
	public String getIsViewSearch() {
	    return this.isViewSearch;
	}
	public void setIsViewSearch(String isViewSearch) {
	    this.isViewSearch=isViewSearch;
	}
	public String getIsViewDescript() {
	    return this.isViewDescript;
	}
	public void setIsViewDescript(String isViewDescript) {
	    this.isViewDescript=isViewDescript;
	}
	public String getIsViewTongji() {
	    return this.isViewTongji;
	}
	public void setIsViewTongji(String isViewTongji) {
	    this.isViewTongji=isViewTongji;
	}
	public String getIsViewPiaoshu() {
	    return this.isViewPiaoshu;
	}
	public void setIsViewPiaoshu(String isViewPiaoshu) {
	    this.isViewPiaoshu=isViewPiaoshu;
	}
	public String getCheckVoteCount() {
		return checkVoteCount;
	}
	public void setCheckVoteCount(String checkVoteCount) {
		this.checkVoteCount = checkVoteCount;
	}
	public String getPageViewStyle() {
		return pageViewStyle;
	}
	public void setPageViewStyle(String pageViewStyle) {
		this.pageViewStyle = pageViewStyle;
	}
}


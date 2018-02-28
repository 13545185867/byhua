package com.jeecg.p3.toupiao.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActToupiaoConfig:投票配置<br>
 * @author chao.hua
 * @since：2017年06月22日 17时16分49秒 星期四 
 * @version:1.0
 */
public class WxActToupiaoConfig implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *配置信息ID	 */	private String id;	/**	 *活动ID	 */	private String actId;	/**	 *是否允许多次投票 0为否 1为是	 */	private String isManyVtoe;	/**	 *每天投票次数	 */	private Integer verydayMaxVotecount;	/**	 *是否关注后投票，0为否 1为是	 */	private String foucsUserCanJoin;	/**	 *投票方式：单选、多选  0为单选 1为多选	 */	private String voteType;	/**	 *选项显示样式，1为每行一个 2为每行2个  3为每行三个	 */	private String optionViewStyle;	/**	 *选项称谓	 */	private String optionAddr;	/**	 *选项量词	 */	private String optionClassic;	/**	 *是否显示搜索 0为否 1为是	 */	private String isViewSearch;	/**	 *是否显示活动介绍 0为否 1为是	 */	private String isViewDescript;	/**	 *是否显示活动统计	 */	private String isViewTongji;
	
	private String isEnter;
	
	private String pageViewStyle;
		public String getIsEnter() {
		return isEnter;
	}
	public void setIsEnter(String isEnter) {
		this.isEnter = isEnter;
	}
	/**	 *是否显示选项票数	 */	private String isViewPiaoshu;
	
	private String checkVoteCount;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getIsManyVtoe() {	    return this.isManyVtoe;	}	public void setIsManyVtoe(String isManyVtoe) {	    this.isManyVtoe=isManyVtoe;	}	public Integer getVerydayMaxVotecount() {	    return this.verydayMaxVotecount;	}	public void setVerydayMaxVotecount(Integer verydayMaxVotecount) {	    this.verydayMaxVotecount=verydayMaxVotecount;	}	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public String getVoteType() {	    return this.voteType;	}	public void setVoteType(String voteType) {	    this.voteType=voteType;	}	public String getOptionViewStyle() {	    return this.optionViewStyle;	}	public void setOptionViewStyle(String optionViewStyle) {	    this.optionViewStyle=optionViewStyle;	}	public String getOptionAddr() {	    return this.optionAddr;	}	public void setOptionAddr(String optionAddr) {	    this.optionAddr=optionAddr;	}	public String getOptionClassic() {	    return this.optionClassic;	}	public void setOptionClassic(String optionClassic) {	    this.optionClassic=optionClassic;	}	public String getIsViewSearch() {	    return this.isViewSearch;	}	public void setIsViewSearch(String isViewSearch) {	    this.isViewSearch=isViewSearch;	}	public String getIsViewDescript() {	    return this.isViewDescript;	}	public void setIsViewDescript(String isViewDescript) {	    this.isViewDescript=isViewDescript;	}	public String getIsViewTongji() {	    return this.isViewTongji;	}	public void setIsViewTongji(String isViewTongji) {	    this.isViewTongji=isViewTongji;	}	public String getIsViewPiaoshu() {	    return this.isViewPiaoshu;	}	public void setIsViewPiaoshu(String isViewPiaoshu) {	    this.isViewPiaoshu=isViewPiaoshu;	}
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


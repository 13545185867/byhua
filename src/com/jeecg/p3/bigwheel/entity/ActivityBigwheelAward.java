package com.jeecg.p3.bigwheel.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>ActivityBigwheelAward:<br>
 * @author chao.hua
 * @since：2017年08月02日 16时21分57秒 星期三 
 * @version:1.0
 */
public class ActivityBigwheelAward implements Entity<Integer> ,Comparable<ActivityBigwheelAward> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	/**	 *	 */	private Integer activityid;
	
	private Integer probabilityType;
		public Integer getProbabilityType() {
		return probabilityType;
	}
	public void setProbabilityType(Integer probabilityType) {
		this.probabilityType = probabilityType;
	}
	/**	 *奖项名称	 */	private String awardname;	/**	 *奖品名称	 */	private String trophyname;	/**	 *奖品图片	 */	private String awardpic;	/**	 *奖品类型1:实物礼品（奖券）
2:微信红包
5抵扣券（奖券）
4优惠券（奖券）	 */	private Integer awardtype;	/**	 *对奖开始时间	 */	private Date validitystart;	/**	 *兑奖结束时间	 */	private Date validitystop;	/**	 *奖品数量	 */	private Integer awardrealnum;	/**	 *中奖详情页操作提示	 */	private String operationtip;	/**	 *0为系统生成券号
1为手动输入	 */	private Integer sngeneratetype;	/**	 *手动输入的券号	 */	private String sncodes;	/**	 *是否是微信卡券
0为否 
1为是	 */	private String iswxcard;	/**	 *副标题	 */	private String subhead;	/**	 *兑奖地址	 */	private String storeaddress;	/**	 *服务电话	 */	private String serviceTel;	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	/**	 *兑奖须知	 */	private String description;	/**	 *	 */	private String activityAwardcol;	/**	 *	 */	private String activityAwardcol1;	/**	 *	 */	private String activityAwardcol2;	/**	 *奖项等级	 */	private Integer awardlevel;	/**	 *	 */	private String activityAwardcol3;	/**	 *红包最小金额	 */	private String singlemin;	/**	 *红包最大金额	 */	private String singlemax;	/**	 *总金额	 */	private String totalcount;	/**	 *	 */	private Integer awardvirtualnum;
	
	private Double prob;	public Double getProb() {
		return prob;
	}
	public void setProb(Double prob) {
		this.prob = prob;
	}
	/**	 *	 */
	private Integer awardEveryDay;
	public Integer getAwardEveryDay() {
		return awardEveryDay;
	}
	public void setAwardEveryDay(Integer awardEveryDay) {
		this.awardEveryDay = awardEveryDay;
	}
	private Integer awardRemainNum;	public Integer getAwardRemainNum() {
		return awardRemainNum;
	}
	public void setAwardRemainNum(Integer awardRemainNum) {
		this.awardRemainNum = awardRemainNum;
	}
	private String deleted;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getActivityid() {	    return this.activityid;	}	public void setActivityid(Integer activityid) {	    this.activityid=activityid;	}	public String getAwardname() {	    return this.awardname;	}	public void setAwardname(String awardname) {	    this.awardname=awardname;	}	public String getTrophyname() {	    return this.trophyname;	}	public void setTrophyname(String trophyname) {	    this.trophyname=trophyname;	}	public String getAwardpic() {	    return this.awardpic;	}	public void setAwardpic(String awardpic) {	    this.awardpic=awardpic;	}	public Integer getAwardtype() {	    return this.awardtype;	}	public void setAwardtype(Integer awardtype) {	    this.awardtype=awardtype;	}	public Date getValiditystart() {	    return this.validitystart;	}	public void setValiditystart(Date validitystart) {	    this.validitystart=validitystart;	}	public Date getValiditystop() {	    return this.validitystop;	}	public void setValiditystop(Date validitystop) {	    this.validitystop=validitystop;	}	public Integer getAwardrealnum() {	    return this.awardrealnum;	}	public void setAwardrealnum(Integer awardrealnum) {	    this.awardrealnum=awardrealnum;	}	public String getOperationtip() {	    return this.operationtip;	}	public void setOperationtip(String operationtip) {	    this.operationtip=operationtip;	}	public Integer getSngeneratetype() {	    return this.sngeneratetype;	}	public void setSngeneratetype(Integer sngeneratetype) {	    this.sngeneratetype=sngeneratetype;	}	public String getSncodes() {	    return this.sncodes;	}	public void setSncodes(String sncodes) {	    this.sncodes=sncodes;	}	public String getIswxcard() {	    return this.iswxcard;	}	public void setIswxcard(String iswxcard) {	    this.iswxcard=iswxcard;	}	public String getSubhead() {	    return this.subhead;	}	public void setSubhead(String subhead) {	    this.subhead=subhead;	}	public String getStoreaddress() {	    return this.storeaddress;	}	public void setStoreaddress(String storeaddress) {	    this.storeaddress=storeaddress;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getActivityAwardcol() {	    return this.activityAwardcol;	}	public void setActivityAwardcol(String activityAwardcol) {	    this.activityAwardcol=activityAwardcol;	}	public String getActivityAwardcol1() {	    return this.activityAwardcol1;	}	public void setActivityAwardcol1(String activityAwardcol1) {	    this.activityAwardcol1=activityAwardcol1;	}	public String getActivityAwardcol2() {	    return this.activityAwardcol2;	}	public void setActivityAwardcol2(String activityAwardcol2) {	    this.activityAwardcol2=activityAwardcol2;	}	public Integer getAwardlevel() {	    return this.awardlevel;	}	public void setAwardlevel(Integer awardlevel) {	    this.awardlevel=awardlevel;	}	public String getActivityAwardcol3() {	    return this.activityAwardcol3;	}	public void setActivityAwardcol3(String activityAwardcol3) {	    this.activityAwardcol3=activityAwardcol3;	}	public String getSinglemin() {	    return this.singlemin;	}	public void setSinglemin(String singlemin) {	    this.singlemin=singlemin;	}	public String getSinglemax() {	    return this.singlemax;	}	public void setSinglemax(String singlemax) {	    this.singlemax=singlemax;	}	public String getTotalcount() {	    return this.totalcount;	}	public void setTotalcount(String totalcount) {	    this.totalcount=totalcount;	}	public Integer getAwardvirtualnum() {	    return this.awardvirtualnum;	}	public void setAwardvirtualnum(Integer awardvirtualnum) {	    this.awardvirtualnum=awardvirtualnum;	}	public String getDeleted() {	    return this.deleted;	}	public void setDeleted(String deleted) {	    this.deleted=deleted;	}
	@Override
	public int compareTo(ActivityBigwheelAward o) {
		// TODO Auto-generated method stub
		return this.prob.compareTo(o.getProb());
	}
}


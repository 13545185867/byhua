package com.jeecg.p3.bigwheel.entity;

import java.util.Date;

public class ActivityBigwheelConfigPlayRule {
		private Integer id;
		/**
		 *活动名称
		 */
		private Double everydayprob;
		public Double getEverydayprob() {
			return everydayprob;
		}
		public void setEverydayprob(Double everydayprob) {
			this.everydayprob = everydayprob;
		}
		private String title;
		private String isBuy;
		/**
		 *
		 */
		private Date end;
		/**
		 *
		 */
		private Date start;
		/**
		 *是否显示参与人数0隐藏1为显示
		 */
		private String hideparticipant;
		/**
		 *活动规则
		 */
		private String ruletips;
		/**
		 *1为已删除
	0为正常
		 */
		private Integer deleted;
		/**
		 *活动类型
		 */
		private String activitytype;
		/**
		 *所属公众号
		 */
		private String jwid;
		/**
		 *创建人
		 */
		private String userid;
		/**
		 *活动界面设计
		 
		private String pagejsoncontent;*/
		/**
		 *
		 */
		private String ispublish;
		/**
		 *总中奖率
		 */
		private Integer globalprobability;
		/**
		 *是否均匀发放
		 */
		private Integer probabilitytype;
		/**
		 *获奖验证
		 */
		private String trophyvalidator;
		/**
		 *助力环节图片及布局
		 */
		private String qrcodeurl;
		/**
		 *
		 */
		private Date creatDate;
		
		public Date getCreatDate() {
			return creatDate;
		}
		public void setCreatDate(Date creatDate) {
			this.creatDate = creatDate;
		}
/*		private String extendOperation;
		public String getExtendOperation() {
			return extendOperation;
		}
		public void setExtendOperation(String extendOperation) {
			this.extendOperation = extendOperation;
		}*/
		private Integer awardsendtype;
		public Integer getId() {
		    return this.id;
		}
		public void setId(Integer id) {
		    this.id=id;
		}
		public String getTitle() {
		    return this.title;
		}
		public void setTitle(String title) {
		    this.title=title;
		}
		public Date getEnd() {
		    return this.end;
		}
		public void setEnd(Date end) {
		    this.end=end;
		}
		public Date getStart() {
		    return this.start;
		}
		public void setStart(Date start) {
		    this.start=start;
		}
		public String getHideparticipant() {
		    return this.hideparticipant;
		}
		public void setHideparticipant(String hideparticipant) {
		    this.hideparticipant=hideparticipant;
		}
		public String getRuletips() {
		    return this.ruletips;
		}
		public void setRuletips(String ruletips) {
		    this.ruletips=ruletips;
		}
		public Integer getDeleted() {
		    return this.deleted;
		}
		public void setDeleted(Integer deleted) {
		    this.deleted=deleted;
		}
		public String getActivitytype() {
		    return this.activitytype;
		}
		public void setActivitytype(String activitytype) {
		    this.activitytype=activitytype;
		}

		public String getUserid() {
		    return this.userid;
		}
		public void setUserid(String userid) {
		    this.userid=userid;
		}
/*		public String getPagejsoncontent() {
		    return this.pagejsoncontent;
		}
		public void setPagejsoncontent(String pagejsoncontent) {
		    this.pagejsoncontent=pagejsoncontent;
		}*/
		 
		public Integer getGlobalprobability() {
		    return this.globalprobability;
		}
		public void setGlobalprobability(Integer globalprobability) {
		    this.globalprobability=globalprobability;
		}
		public Integer getProbabilitytype() {
		    return this.probabilitytype;
		}
		public void setProbabilitytype(Integer probabilitytype) {
		    this.probabilitytype=probabilitytype;
		}
		public String getTrophyvalidator() {
		    return this.trophyvalidator;
		}
		public void setTrophyvalidator(String trophyvalidator) {
		    this.trophyvalidator=trophyvalidator;
		}
		public String getQrcodeurl() {
		    return this.qrcodeurl;
		}
		public void setQrcodeurl(String qrcodeurl) {
		    this.qrcodeurl=qrcodeurl;
		}
		public Integer getAwardsendtype() {
		    return this.awardsendtype;
		}
		public void setAwardsendtype(Integer awardsendtype) {
		    this.awardsendtype=awardsendtype;
		}
		public String getIspublish() {
			return ispublish;
		}
		public void setIspublish(String ispublish) {
			this.ispublish = ispublish;
		}
		
		private String limitlottery;
		/**
		 *每个人最多参与次数
		 */
		private Integer totallotterycount;
		/**
		 *每天参与次数值 
		 */
		private Integer daylotterycount;
		/**
		 *每个人最多中奖次数
		 */
		private Integer singleuserawards;
		/**
		 *初始中奖百分比
		 */
		private String initialprobability;
		/**
		 *助力成功率
		 */
		private Integer percentage;
		/**
		 *助力成功人数
		 */
		private Integer helpnum;
		/**
		 *活动ID
		 */
		/**
		 *
		 */
		private String activityPlayrulecol;
		/**
		 *
		 */
		private String activityPlayrulecol1;
		/**
		 *
		 */
		private String activityPlayrulecol2;
		/**
		 *
		 */
		private String exchangelimit;
		/**
		 *
		 */
		private String participatelimit;
		/**
		 *
		 */
		private Integer gametime;
		/**
		 *
		 */
		private Integer gametimetype;
		public String getLimitlottery() {
		    return this.limitlottery;
		}
		public void setLimitlottery(String limitlottery) {
		    this.limitlottery=limitlottery;
		}
		public Integer getTotallotterycount() {
		    return this.totallotterycount;
		}
		public void setTotallotterycount(Integer totallotterycount) {
		    this.totallotterycount=totallotterycount;
		}
		public Integer getDaylotterycount() {
		    return this.daylotterycount;
		}
		public void setDaylotterycount(Integer daylotterycount) {
		    this.daylotterycount=daylotterycount;
		}
		public Integer getSingleuserawards() {
		    return this.singleuserawards;
		}
		public void setSingleuserawards(Integer singleuserawards) {
		    this.singleuserawards=singleuserawards;
		}
		public String getInitialprobability() {
		    return this.initialprobability;
		}
		public void setInitialprobability(String initialprobability) {
		    this.initialprobability=initialprobability;
		}
		public Integer getPercentage() {
		    return this.percentage;
		}
		public void setPercentage(Integer percentage) {
		    this.percentage=percentage;
		}
		public Integer getHelpnum() {
		    return this.helpnum;
		}
		public void setHelpnum(Integer helpnum) {
		    this.helpnum=helpnum;
		}
		public String getActivityPlayrulecol() {
		    return this.activityPlayrulecol;
		}
		public void setActivityPlayrulecol(String activityPlayrulecol) {
		    this.activityPlayrulecol=activityPlayrulecol;
		}
		public String getActivityPlayrulecol1() {
		    return this.activityPlayrulecol1;
		}
		public void setActivityPlayrulecol1(String activityPlayrulecol1) {
		    this.activityPlayrulecol1=activityPlayrulecol1;
		}
		public String getActivityPlayrulecol2() {
		    return this.activityPlayrulecol2;
		}
		public void setActivityPlayrulecol2(String activityPlayrulecol2) {
		    this.activityPlayrulecol2=activityPlayrulecol2;
		}
		public String getExchangelimit() {
		    return this.exchangelimit;
		}
		public void setExchangelimit(String exchangelimit) {
		    this.exchangelimit=exchangelimit;
		}
		public String getParticipatelimit() {
		    return this.participatelimit;
		}
		public void setParticipatelimit(String participatelimit) {
		    this.participatelimit=participatelimit;
		}
		public Integer getGametime() {
		    return this.gametime;
		}
		public void setGametime(Integer gametime) {
		    this.gametime=gametime;
		}
		public Integer getGametimetype() {
		    return this.gametimetype;
		}
		public void setGametimetype(Integer gametimetype) {
		    this.gametimetype=gametimetype;
		}
		private String organizer;
		/**
		 *打开前加载页0为系统默认，1为自定义
		 */
		private Integer usedefaultloadpage;
		/**
		 *主办单位url链接0为不开启
	1为开启
		 */
		private Integer jumplinkbtn;
		/**
		 *主办方URL
		 */
		private String organizerlink;
		/**
		 *显示版权0为显示系统默认 1为自定义
		 */
		private String usedefaultcopyright;
		/**
		 *分享图片0为系统默认
	1为自定义图片
		 */
		private Integer useshareico;
		/**
		 *分享小图标url
		 */
		private String shareimgpath;
		/**
		 *分享标题 0为默认
	1是为自定义
		 */
		private Integer usesharetitle;
		/**
		 *分享自定义标题
		 */
		private String sharetitletext;
		/**
		 *分享内容0为默认1为自定义
		 */
		private Integer usesharetxt;
		/**
		 *未得奖分享内容
		 */
		private String noawardtext;
		/**
		 *得奖分享内容
		 */
		private String awardtext;
		/**
		 *引导关注图链接
		 */
		private String wxlink;
		/**
		 *是否需要关注后参加0为不关注可参加1为否
		 */
		private String needfollow;

		/**
		 *只允许这些地能得奖或投票
		 */
		private String addresstext;
		/**
		 *
		 */
		private Integer activityid;
		/**
		 *
		 */
		private String activityConfigcol;
		/**
		 *
		 */
		private String activityConfigcol1;
		/**
		 *
		 */
		private String activityConfigcol2;
		/**
		 *自定义版权图
		 */
		private String customcopyrightpic;
		/**
		 *版权链接
		 */
		private String copyrightlink;
		/**
		 *是否是现场活动
	0为否
	1为是
	现场活动需现场参加
		 */
		private Integer iscurrent;
		/**
		 *
		 */
		private String organizerlogo;
		/**
		 *活动背景
		 */
		private String bgpic;
		/**
		 *
		 */
		private String needarea;
		/**
		 *性别验证
		 */
		private String needsex;
		/**
		 *true为开启好友协助false为不开启
		 */
		private String withhelp;
		/**
		 *
		 */
		private String city;
		/**
		 *
		 */
		private String province;
		/**
		 *
		 */
		private String hideorganizerlogo;
		/**
		 *
		 */
		
		private String showcopyright;
		
		private String participateLimit;
		
		public String getParticipateLimit() {
			return participateLimit;
		}
		public void setParticipateLimit(String participateLimit) {
			this.participateLimit = participateLimit;
		}
		/**
		 *
		 */
		private String showsupport;
		public String getOrganizer() {
		    return this.organizer;
		}
		public void setOrganizer(String organizer) {
		    this.organizer=organizer;
		}
		public Integer getUsedefaultloadpage() {
		    return this.usedefaultloadpage;
		}
		public void setUsedefaultloadpage(Integer usedefaultloadpage) {
		    this.usedefaultloadpage=usedefaultloadpage;
		}
		public Integer getJumplinkbtn() {
		    return this.jumplinkbtn;
		}
		public void setJumplinkbtn(Integer jumplinkbtn) {
		    this.jumplinkbtn=jumplinkbtn;
		}
		public String getOrganizerlink() {
		    return this.organizerlink;
		}
		public void setOrganizerlink(String organizerlink) {
		    this.organizerlink=organizerlink; 
		}
		public String getUsedefaultcopyright() {
		    return this.usedefaultcopyright;
		}
		public void setUsedefaultcopyright(String usedefaultcopyright) {
		    this.usedefaultcopyright=usedefaultcopyright;
		}
		public Integer getUseshareico() {
		    return this.useshareico;
		}
		public void setUseshareico(Integer useshareico) {
		    this.useshareico=useshareico;
		}
		public String getShareimgpath() {
		    return this.shareimgpath;
		}
		public void setShareimgpath(String shareimgpath) {
		    this.shareimgpath=shareimgpath;
		}
		public Integer getUsesharetitle() {
		    return this.usesharetitle;
		}
		public void setUsesharetitle(Integer usesharetitle) {
		    this.usesharetitle=usesharetitle;
		}
		public String getSharetitletext() {
		    return this.sharetitletext;
		}
		public void setSharetitletext(String sharetitletext) {
		    this.sharetitletext=sharetitletext;
		}
		public Integer getUsesharetxt() {
		    return this.usesharetxt;
		}
		public void setUsesharetxt(Integer usesharetxt) {
		    this.usesharetxt=usesharetxt;
		}
		public String getNoawardtext() {
		    return this.noawardtext;
		}
		public void setNoawardtext(String noawardtext) {
		    this.noawardtext=noawardtext;
		}
		public String getAwardtext() {
		    return this.awardtext;
		}
		public void setAwardtext(String awardtext) {
		    this.awardtext=awardtext;
		}
		public String getWxlink() {
		    return this.wxlink;
		}
		public void setWxlink(String wxlink) {
		    this.wxlink=wxlink;
		}
		public String getNeedfollow() {
		    return this.needfollow;
		}
		public void setNeedfollow(String needfollow) {
		    this.needfollow=needfollow;
		}

		public String getAddresstext() {
		    return this.addresstext;
		}
		public void setAddresstext(String addresstext) {
		    this.addresstext=addresstext;
		}
		public Integer getActivityid() {
		    return this.activityid;
		}
		public void setActivityid(Integer activityid) {
		    this.activityid=activityid;
		}
		public String getActivityConfigcol() {
		    return this.activityConfigcol;
		}
		public void setActivityConfigcol(String activityConfigcol) {
		    this.activityConfigcol=activityConfigcol;
		}
		public String getActivityConfigcol1() {
		    return this.activityConfigcol1;
		}
		public void setActivityConfigcol1(String activityConfigcol1) {
		    this.activityConfigcol1=activityConfigcol1;
		}
		public String getActivityConfigcol2() {
		    return this.activityConfigcol2;
		}
		public void setActivityConfigcol2(String activityConfigcol2) {
		    this.activityConfigcol2=activityConfigcol2;
		}
		public String getCustomcopyrightpic() {
		    return this.customcopyrightpic;
		}
		public void setCustomcopyrightpic(String customcopyrightpic) {
		    this.customcopyrightpic=customcopyrightpic;
		}
		public String getCopyrightlink() {
		    return this.copyrightlink;
		}
		public void setCopyrightlink(String copyrightlink) {
		    this.copyrightlink=copyrightlink;
		}
		public Integer getIscurrent() {
		    return this.iscurrent;
		}
		public void setIscurrent(Integer iscurrent) {
		    this.iscurrent=iscurrent;
		}
		public String getOrganizerlogo() {
		    return this.organizerlogo;
		}
		public void setOrganizerlogo(String organizerlogo) {
		    this.organizerlogo=organizerlogo;
		}
		public String getBgpic() {
		    return this.bgpic;
		}
		public void setBgpic(String bgpic) {
		    this.bgpic=bgpic;
		}
		public String getNeedarea() {
		    return this.needarea;
		}
		public void setNeedarea(String needarea) {
		    this.needarea=needarea;
		}
		public String getNeedsex() {
		    return this.needsex;
		}
		public void setNeedsex(String needsex) {
		    this.needsex=needsex;
		}
		public String getWithhelp() {
		    return this.withhelp;
		}
		public void setWithhelp(String withhelp) {
		    this.withhelp=withhelp;
		}
		public String getCity() {
		    return this.city;
		}
		public void setCity(String city) {
		    this.city=city;
		}
		public String getProvince() {
		    return this.province;
		}
		public void setProvince(String province) {
		    this.province=province;
		}
		public String getHideorganizerlogo() {
		    return this.hideorganizerlogo;
		}
		public void setHideorganizerlogo(String hideorganizerlogo) {
		    this.hideorganizerlogo=hideorganizerlogo;
		}
		public String getShowcopyright() {
		    return this.showcopyright;
		}
		public void setShowcopyright(String showcopyright) {
		    this.showcopyright=showcopyright;
		}
		public String getShowsupport() {
		    return this.showsupport;
		}
		public void setShowsupport(String showsupport) {
		    this.showsupport=showsupport;
		}
		public String getIsBuy() {
			return isBuy;
		}
		public void setIsBuy(String isBuy) {
			this.isBuy = isBuy;
		}
		public String getJwid() {
			return jwid;
		}
		public void setJwid(String jwid) {
			this.jwid = jwid;
		}
}

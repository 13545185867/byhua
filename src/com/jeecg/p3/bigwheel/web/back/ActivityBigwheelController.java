package com.jeecg.p3.bigwheel.web.back;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.cg.util.BaihuaUtil;
import org.jeecgframework.p3.cg.util.LotteryUtil;
import org.jeecgframework.p3.core.util.SystemTools;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.aboutActivity.entity.ActivityConfig;
import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;
import com.jeecg.p3.aboutActivity.service.ActivityConfigService;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelAwardService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelPlayruleService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelRecordService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelService;
import com.jeecg.p3.messageLog.service.SendMessageLogService;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.userservice.entity.Userservice;
import com.jeecg.p3.userservice.service.UserserviceService;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>ActivityBigwheelController<br>
 * @author chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/bigwheel/back/activityBigwheel/")
public class ActivityBigwheelController extends BaseController{
  @Autowired
  private ActivityBigwheelService activityBigwheelService;
  
  @Autowired
  private ActivityBigwheelAwardService activityBigwheelAwardService;
  
  @Autowired
  private ActivityBigwheelPlayruleService activityBigwheelPlayruleService;
  
  @Autowired
  private ActivityConfigService activityConfigService;
  
  @Autowired
  private ActivityBigwheelRecordService activityBigwheelRecordService;
  
  @Autowired
  private SendMessageLogService sendMessageLogService;
  @Value("#{sysconfig['bigwheelAppid']}")
  private String bigwheelAppid;
  @Autowired
  private UserserviceService userserviceService;

  /**
   * 列表页面
   * @return
   */
 @RequestMapping(value="bigwheellist",method = {RequestMethod.GET,RequestMethod.POST})
 public void list(@ModelAttribute ActivityBigwheel query,HttpServletResponse response,HttpServletRequest request,
 			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
 			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
 	 	PageQuery<ActivityBigwheel> pageQuery = new PageQuery<ActivityBigwheel>();
 	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
 	 	query.setDeleted(0);  
 	 	query.setUserid(user.getUserId());
 	 	pageQuery.setPageNo(pageNo);
 	 	pageQuery.setPageSize(pageSize);
 	 	VelocityContext velocityContext = new VelocityContext();
 		pageQuery.setQuery(query);
 		velocityContext.put("activityBigwheel",query);
 		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(activityBigwheelService.queryPageList(pageQuery)));
 		String viewName = "bigwheel/back/activityBigwheel-list.vm";
 		ViewVelocity.view(request,response,viewName,velocityContext);
 }




/**
 * 修改信息
 * @return
 */
@RequestMapping(value = "/updateActivityWithAllInfo",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson updateActivityWithAllInfo(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		//大转盘基本信息
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	        if (user == null) {
	    		j.setSuccess(false);
	    		j.setMsg("修改失败");
	    		return j;
	        }
		    ActivityBigwheel activityBigwheel = activityBigwheelService.getByIdAndUserid(Integer.parseInt(id), user.getUserId());
			if(activityBigwheel == null){
				j.setSuccess(false);
	    		j.setMsg("修改失败");
	    		return j;
			}
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ActivityBigwheel ab = activityBigwheel;
		ab.setTitle((String) request.getParameter("activity.title"));
		ab.setEnd(df.parse(request.getParameter("activity.end")));
		ab.setStart(df.parse(request.getParameter("activity.start")));
		ab.setHideparticipant((String)request.getParameter("activity.hideParticipant"));
		ab.setRuletips((String) request.getParameter("activity.ruleTips"));
		ab.setAwardsendtype(Integer.valueOf((String)request.getParameter("activity.awardSendType")));
		ab.setGlobalprobability(Integer.valueOf((String)request.getParameter("activity.globalProbability")));
		ab.setProbabilitytype(Integer.valueOf((String)request.getParameter("activity.probabilityType")));
		ab.setTrophyvalidator((String) request.getParameter("activity.trophyValidator"));
		ab.setPagejsoncontent((String) request.getParameter("gamePage.jsonContent"));
		ab.setQrcodeurl((String) request.getParameter("activity.qrcodeUrl"));
		ab.setExtendOperation((String) request.getParameter("activity.extendOperation"));
		activityBigwheelService.doEdit(ab);
		Integer activityid = ab.getId();
		//大转盘规则
		ActivityBigwheelPlayrule abp = new ActivityBigwheelPlayrule();
		
		
		abp.setExchangelimit((String) request.getParameter("playRule.exchangeLimit"));
		abp.setActivityid(activityid);
		abp.setDaylotterycount(Integer.valueOf((String)request.getParameter("playRule.dayLotteryCount")));
		abp.setGametime(Integer.valueOf((String)request.getParameter("playRule.gameTime")));
		abp.setGametimetype(Integer.valueOf((String)request.getParameter("playRule.gameTimeType")));
		abp.setHelpnum(Integer.valueOf((String)request.getParameter("helpRule.helpCount")));
		abp.setLimitlottery((String) request.getParameter("playRule.limitLottery"));
		abp.setPercentage(Integer.valueOf((String)request.getParameter("helpRule.percentage")));
		abp.setSingleuserawards(Integer.valueOf((String)request.getParameter("challengeSendRule.singleUserAwards")));
		abp.setTotallotterycount(Integer.valueOf((String)request.getParameter("playRule.totalLotteryCount")));
		
		activityBigwheelPlayruleService.doEdit(abp);
		//大转盘配置信息
		ActivityConfig ac = new ActivityConfig();
		ac.setActivityid(activityid);
		ac.setOrganizer((String) request.getParameter("activity.organizer"));
		ac.setOrganizerlink((String) request.getParameter("activity.organizerLink"));
		ac.setShowsupport((String) request.getParameter("activity.showSupport"));
		ac.setShowcopyright((String) request.getParameter("activity.showCopyright"));
		ac.setUsedefaultcopyright((String) request.getParameter("activity.useDefaultCopyright"));
		ac.setCustomcopyrightpic((String) request.getParameter("activity.customCopyrightPic"));
		ac.setHideorganizerlogo((String) request.getParameter("activity.hideOrganizerLogo"));
		ac.setOrganizerlogo((String) request.getParameter("activity.organizerLogo"));
		ac.setBgpic((String) request.getParameter("activity.bgPic"));
		//地区限制
		ac.setAddresstext((String) request.getParameter("playRule.addressText"));
		ac.setAwardtext((String) request.getParameter("activity.awardText"));
		ac.setNoawardtext((String) request.getParameter("activity.noAwardText"));
		ac.setWxlink((String) request.getParameter("activity.wxlink"));
		ac.setWithhelp((String) request.getParameter("activity.withHelp"));
	
		//是否现场活动 
		ac.setIscurrent(Integer.valueOf((String) request.getParameter("activity.iscurrent")));
		//
		ac.setNeedarea((String) request.getParameter("playRule.needArea"));
		ac.setNeedfollow((String) request.getParameter("playRule.needFollow"));
		//男女参加限制
		ac.setNeedsex((String) request.getParameter("activity.needsex"));

		ac.setShareimgpath((String) request.getParameter("imgTextShare.imgPath"));
		ac.setSharetitletext((String) request.getParameter("imgTextShare.titleText"));
		ac.setUseshareico(Integer.valueOf((String) request.getParameter("imgTextShare.sttr1")));
		ac.setUsesharetitle(Integer.valueOf((String) request.getParameter("imgTextShare.sttr3")));
		ac.setUsesharetxt(Integer.valueOf((String) request.getParameter("imgTextShare.sttr2")));
		ac.setParticipateLimit((String) request.getParameter("playRule.participateLimit"));;
		activityConfigService.doEdit(ac);
		
		//先删除，在添加
		activityBigwheelAwardService.doDelete(activityBigwheel.getId());
		ActivityBigwheelAward bigaward = new ActivityBigwheelAward();
		String awardjson = (String) request.getParameter("activity.awardTips");
		 List<JSONObject> list = JSON.parseArray(awardjson, JSONObject.class);
		 
		int awardcount = list.size();
		for(int i=0  ;i<awardcount;i++){
			if(request.getParameter("awardBindPrizes["+i+"].award.awardLevel")!=null ){
				//大转盘奖品设置
				bigaward.setAwardlevel(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.awardLevel")));
				bigaward.setAwardtype(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.awardType")));
				bigaward.setAwardname((String) request.getParameter("awardBindPrizes["+i+"].award.awardName"));
				bigaward.setAwardpic( (String) request.getParameter("awardBindPrizes["+i+"].award.awardPic")) ;
				bigaward.setProbabilityType(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.probabilityType")));
				bigaward.setOperationtip( (String) request.getParameter("awardBindPrizes["+i+"].award.operationTip") );
				bigaward.setStoreaddress( (String) request.getParameter("awardBindPrizes["+i+"].award.storeAddress") );
				bigaward.setSubhead( (String) request.getParameter("awardBindPrizes["+i+"].award.subhead") );
				bigaward.setServiceTel( (String) request.getParameter("awardBindPrizes["+i+"].award.serviceTel") );
				bigaward.setDescription((String) request.getParameter("awardBindPrizes["+i+"].award.awardLevel"));
				bigaward.setDeleted( (String) request.getParameter("awardBindPrizes["+i+"].award.deleted") );
				bigaward.setAwardrealnum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardRealNum")));
				bigaward.setAwardRemainNum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardRealNum")));
				bigaward.setTrophyname( (String) request.getParameter("awardBindPrizes["+i+"].trophy.trophyName") );
				bigaward.setAwardvirtualnum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardVirtualNum")));
				bigaward.setSinglemin( (String) request.getParameter("awardBindPrizes["+i+"].trophy.singleMin") );
				bigaward.setSinglemax( (String) request.getParameter("awardBindPrizes["+i+"].trophy.singleMax") );
				bigaward.setTotalcount( (String) request.getParameter("awardBindPrizes["+i+"].trophy.totalCount") );
				bigaward.setAwardtype( Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardType")) );
				bigaward.setValiditystart(df.parse(request.getParameter("awardBindPrizes["+i+"].trophy.validityStart")));
				bigaward.setValiditystop(df.parse(request.getParameter("awardBindPrizes["+i+"].trophy.validityStop")));
				bigaward.setSngeneratetype( Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.snGenerateType")) );
				bigaward.setIswxcard((String) request.getParameter("awardBindPrizes["+i+"].trophy.isWxCard"));
				bigaward.setActivityid(activityid );
				bigaward.setSncodes((String) request.getParameter("awardBindPrizes["+i+"].trophy.defaultSnCode"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Calendar cal = Calendar.getInstance();
				cal.setTime(df.parse(request.getParameter("activity.end")));
				long endtime = cal.getTimeInMillis();
				cal.setTime(df.parse(request.getParameter("activity.start")));
				long starttime = cal.getTimeInMillis();
				ab.setStart(df.parse(request.getParameter("activity.start")));
				long between_days = (endtime-starttime)/(1000*3600*24);
				int day = Integer.parseInt(String.valueOf(between_days));
				int awardEveryDay = (bigaward.getAwardrealnum()/day);
				bigaward.setAwardEveryDay(awardEveryDay);
				activityBigwheelAwardService.doAdd(bigaward);
				
			}else{
				break;
			}
		}

		j.setMsg("修改成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("修改失败");
	}
	return j;
}


@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "bigwheel/back/activityBigwheel-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}


/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/addActivityWithAllInfo",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson addActivityWithAllInfo(@ModelAttribute ActivityBigwheel activityBigwheel,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		//大转盘基本信息
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	        if (user == null) {
	    		j.setSuccess(false);
	    		j.setMsg("保存失败");
	    		return j;
	        }
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ActivityBigwheel ab = new ActivityBigwheel();
		ab.setTitle((String) request.getParameter("activity.title"));
		ab.setEnd(df.parse(request.getParameter("activity.end")));
		ab.setStart(df.parse(request.getParameter("activity.start")));
		ab.setHideparticipant((String)request.getParameter("activity.hideParticipant"));
		ab.setIspublish("0");
		ab.setActivitytype("2");
		ab.setRuletips((String) request.getParameter("activity.ruleTips"));
		ab.setAwardsendtype(Integer.valueOf((String)request.getParameter("activity.awardSendType")));
		ab.setGlobalprobability(Integer.valueOf((String)request.getParameter("activity.globalProbability")));
		ab.setProbabilitytype(Integer.valueOf((String)request.getParameter("activity.probabilityType")));
		ab.setTrophyvalidator((String) request.getParameter("activity.trophyValidator"));
		ab.setPagejsoncontent((String) request.getParameter("gamePage.jsonContent"));
		ab.setQrcodeurl((String) request.getParameter("activity.qrcodeUrl"));
		ab.setExtendOperation((String) request.getParameter("activity.extendOperation"));
		ab.setCreatDate(new Date());
		if(activityConfigService.isVip(this.bigwheelAppid, user.getUserId())){
			ab.setIsBuy("1");
		}else{
			ab.setIsBuy("0");
		}

		ab.setUserid(user.getUserId());
		ab.setJwid((String)request.getSession().getAttribute("jwid"));
		ab.setDeleted(0);
		activityBigwheelService.doAdd(ab);
		 Userservice userservice = new Userservice();
		 userservice.setUserservicecol0(this.bigwheelAppid);
		 userservice.setUserid(user.getUserId());
		 List<Userservice> userlist = userserviceService.queryByRecord(userservice);
		 for(Userservice us : userlist){
			 if(us.getCreatecount()>0){
				  us.setCreatecount(us.getCreatecount()-1);
				  userserviceService.doEdit(us);
				  break;
			 }
		 }
		Integer activityid = ab.getId();
		//大转盘规则
		ActivityBigwheelPlayrule abp = new ActivityBigwheelPlayrule();
		
		
		abp.setExchangelimit((String) request.getParameter("playRule.exchangeLimit"));
		abp.setActivityid(activityid);
		abp.setDaylotterycount(Integer.valueOf((String)request.getParameter("playRule.dayLotteryCount")));
		abp.setGametime(Integer.valueOf((String)request.getParameter("playRule.gameTime")));
		abp.setGametimetype(Integer.valueOf((String)request.getParameter("playRule.gameTimeType")));
		abp.setHelpnum(Integer.valueOf((String)request.getParameter("helpRule.helpCount")));
		abp.setLimitlottery((String) request.getParameter("playRule.limitLottery"));
		abp.setPercentage(Integer.valueOf((String)request.getParameter("helpRule.percentage")));
		abp.setSingleuserawards(Integer.valueOf((String)request.getParameter("challengeSendRule.singleUserAwards")));
		abp.setTotallotterycount(Integer.valueOf((String)request.getParameter("playRule.totalLotteryCount")));
		
		activityBigwheelPlayruleService.doAdd(abp);
		//大转盘配置信息
		ActivityConfig ac = new ActivityConfig();
		ac.setActivityid(activityid);
		ac.setOrganizer((String) request.getParameter("activity.organizer"));
		ac.setOrganizerlink((String) request.getParameter("activity.organizerLink"));
		ac.setShowsupport((String) request.getParameter("activity.showSupport"));
		ac.setShowcopyright((String) request.getParameter("activity.showCopyright"));
		ac.setUsedefaultcopyright((String) request.getParameter("activity.useDefaultCopyright"));
		ac.setCustomcopyrightpic((String) request.getParameter("activity.customCopyrightPic"));
		ac.setHideorganizerlogo((String) request.getParameter("activity.hideOrganizerLogo"));
		ac.setOrganizerlogo((String) request.getParameter("activity.organizerLogo"));
		ac.setBgpic((String) request.getParameter("activity.bgPic"));
		//地区限制
		ac.setAddresstext((String) request.getParameter("playRule.addressText"));
		ac.setAwardtext((String) request.getParameter("activity.awardText"));
		ac.setNoawardtext((String) request.getParameter("activity.noAwardText"));
		ac.setWxlink((String) request.getParameter("activity.wxlink"));
		ac.setWithhelp((String) request.getParameter("activity.withHelp"));
		ac.setActivityConfigcol((String) request.getParameter("activity.verfiyPassword"));
	
		//是否现场活动 
		ac.setIscurrent(Integer.valueOf((String) request.getParameter("activity.iscurrent")));
		//
		ac.setNeedarea((String) request.getParameter("playRule.needArea"));
		ac.setNeedfollow((String) request.getParameter("playRule.needFollow"));
		//男女参加限制
		ac.setNeedsex((String) request.getParameter("activity.needsex"));

		ac.setShareimgpath((String) request.getParameter("imgTextShare.imgPath"));
		ac.setSharetitletext((String) request.getParameter("imgTextShare.titleText"));
		ac.setUseshareico(Integer.valueOf((String) request.getParameter("imgTextShare.sttr1")));
		ac.setUsesharetitle(Integer.valueOf((String) request.getParameter("imgTextShare.sttr3")));
		ac.setUsesharetxt(Integer.valueOf((String) request.getParameter("imgTextShare.sttr2")));
		ac.setParticipateLimit((String) request.getParameter("playRule.participateLimit"));;
		activityConfigService.doAdd(ac);
	 
		ActivityBigwheelAward bigaward = new ActivityBigwheelAward();
		String awardjson = (String) request.getParameter("activity.awardTips");
		 List<JSONObject> list = JSON.parseArray(awardjson, JSONObject.class);
		long awardsum = 0;
		int awardcount = list.size();
		for(int i=0  ;i<awardcount;i++){
			if(request.getParameter("awardBindPrizes["+i+"].award.awardLevel")!=null ){
				//大转盘奖品设置
				bigaward.setAwardlevel(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.awardLevel")));
				bigaward.setAwardtype(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.awardType")));
				bigaward.setAwardname((String) request.getParameter("awardBindPrizes["+i+"].award.awardName"));
				bigaward.setAwardpic( (String) request.getParameter("awardBindPrizes["+i+"].award.awardPic")) ;
				bigaward.setProbabilityType(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].award.probabilityType")));
				bigaward.setOperationtip( (String) request.getParameter("awardBindPrizes["+i+"].award.operationTip") );
				bigaward.setStoreaddress( (String) request.getParameter("awardBindPrizes["+i+"].award.storeAddress") );
				bigaward.setSubhead( (String) request.getParameter("awardBindPrizes["+i+"].award.subhead") );
				bigaward.setServiceTel( (String) request.getParameter("awardBindPrizes["+i+"].award.serviceTel") );
				bigaward.setDescription((String) request.getParameter("awardBindPrizes["+i+"].award.awardLevel"));
				bigaward.setDeleted( (String) request.getParameter("awardBindPrizes["+i+"].award.deleted") );
				bigaward.setAwardrealnum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardRealNum")));
				bigaward.setAwardRemainNum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardRealNum")));
				bigaward.setTrophyname( (String) request.getParameter("awardBindPrizes["+i+"].trophy.trophyName") );
				bigaward.setAwardvirtualnum(Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardVirtualNum")));
				bigaward.setSinglemin( (String) request.getParameter("awardBindPrizes["+i+"].trophy.singleMin") );
				bigaward.setSinglemax( (String) request.getParameter("awardBindPrizes["+i+"].trophy.singleMax") );
				bigaward.setTotalcount( (String) request.getParameter("awardBindPrizes["+i+"].trophy.totalCount") );
				bigaward.setAwardtype( Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardType")) );
				bigaward.setValiditystart(df.parse(request.getParameter("awardBindPrizes["+i+"].trophy.validityStart")));
				bigaward.setValiditystop(df.parse(request.getParameter("awardBindPrizes["+i+"].trophy.validityStop")));
				bigaward.setSngeneratetype( Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.snGenerateType")) );
				bigaward.setIswxcard((String) request.getParameter("awardBindPrizes["+i+"].trophy.isWxCard"));
				bigaward.setActivityid(activityid );
				bigaward.setSncodes((String) request.getParameter("awardBindPrizes["+i+"].trophy.defaultSnCode"));
				awardsum+=Integer.valueOf((String) request.getParameter("awardBindPrizes["+i+"].trophy.awardRealNum"));
				activityBigwheelAwardService.doAdd(bigaward);
				
			}else{
				break;
			}
		}
		
		long day = BaihuaUtil.getDaysBetween(df.parse(request.getParameter("activity.start")),df.parse(request.getParameter("activity.end")))+1;
		Double everydayprob = (double) (awardsum/day);
		ActivityBigwheel ab1 = new ActivityBigwheel();
		ab1.setId(activityid);
		ab1.setEverydayprob(everydayprob);
		activityBigwheelService.doEdit(ab1);
		j.setSuccess(true);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}



/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/dodelete",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	        if (user == null) {
	    		j.setSuccess(false);
	    		j.setMsg("删除失败");
	    		return j;
	        }
	    ActivityBigwheel activityBigwheel = activityBigwheelService.getByIdAndUserid(Integer.parseInt(id), user.getUserId());
		if(activityBigwheel == null){
			j.setSuccess(false);
    		j.setMsg("删除失败");
    		return j;
		}
		activityBigwheel.setDeleted(1);
		activityBigwheelRecordService.doDelete(activityBigwheel.getId());
		activityBigwheelAwardService.doDelete(activityBigwheel.getId());
		activityConfigService.doDelete(activityBigwheel.getId());
		activityBigwheelPlayruleService.doDelete(activityBigwheel.getId());
	    activityBigwheelService.doEdit(activityBigwheel);;
		j.setMsg("删除成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("删除失败");
	}
	return j;
}


/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/dopublish",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doPublish(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	        if (user == null) {
	    		j.setSuccess(false);
	    		j.setMsg("发布失败");
	    		return j;
	        }
	    ActivityBigwheel activityBigwheel = activityBigwheelService.getByIdAndUserid(Integer.parseInt(id), user.getUserId());
		if(activityBigwheel == null){
			j.setSuccess(false);
    		j.setMsg("发布失败");
    		return j;
		}
		activityBigwheel.setIspublish("1");;
	    activityBigwheelService.doEdit(activityBigwheel);;
		j.setMsg("删除成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("删除失败");
	}
	return j;
}

/**
 * 激活VIP
 * @return
 */
@RequestMapping(value = "/vipActive",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson vipActive(@RequestParam(required = true, value = "id" ) String idnew,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	        if (user == null) {
	    		j.setSuccess(false);
	    		j.setMsg("发布失败");
	    		return j;
	        }
	        int id = Integer.parseInt(idnew);
	        String userid = user.getUserId();
	    ActivityBigwheel activityBigwheel = activityBigwheelService.getByIdAndUserid(id, userid);
		if(activityBigwheel == null){
			j.setSuccess(false);
    		j.setMsg("发布失败");
    		return j;
		}else{
			ActivityBigwheel activityBigwheelnew = new ActivityBigwheel();
			activityBigwheelnew.setIspublish("1");
			activityBigwheelnew.setId(id);
		    activityBigwheelService.doEdit(activityBigwheelnew);;
			j.setMsg("发布成功");
		}

	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("发布失败");
	}
	return j;
}



}


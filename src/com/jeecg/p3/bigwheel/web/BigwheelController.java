package com.jeecg.p3.bigwheel.web;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.cg.util.BaihuaUtil;
import org.jeecgframework.p3.cg.util.HttpsGetUtil;
import org.jeecgframework.p3.cg.util.LotteryUtil;
import org.jeecgframework.p3.core.util.SystemTools;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.UUIDGenerator;
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
import com.jeecg.p3.aboutActivity.service.ActivityWxuserService;
import com.jeecg.p3.bigwheel.entity.ActRuntime;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelConfigPlayRule;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.entity.UserAwardRecord;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelAwardService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelPlayruleService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelRecordService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelService;
import com.jeecg.p3.messageLog.service.SendMessageLogService;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.userinfo.entity.Userinfo;
import com.jeecg.p3.userinfo.service.UserinfoService;
import com.jeecg.p3.weixinaccount.entity.JwWebJwid;
import com.jeecg.p3.weixinaccount.service.JwWebJwidService;

import org.jeecgframework.p3.core.web.BaseController;

import redis.clients.jedis.Jedis;

 /**
 * 描述：</b>ActivityBigwheelController<br>
 * @author chao.hua
 * @since：2017年08月02日 16时20分33秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/bigwheel")
public class BigwheelController extends BaseController{
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
  
  @Autowired
  private JwWebJwidService jwWebJwidServicenew;
  
  @Autowired
  private UserinfoService userinfoService;
  
  @Value("#{sysconfig['redis.ip']}")
  private String redisip;
  
  @Value("#{sysconfig['freeActivityCount']}")
  private String freeActivityCount;


/**
 * 跳转到编辑页面
 * @return
 */
  
  @RequestMapping(value="index",method = {RequestMethod.GET,RequestMethod.POST})
  public void index(HttpServletResponse response,HttpServletRequest request) throws Exception{
		 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
		//分享出来的助力链接中包含fromOpenid参数，后端不用处理，前端JS会分析参数，并保存至window.game.config.fromOpenid
		 // String fromOpenid =  request.getParameter("fromOpenid");
		  Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
		 ActivityBigwheel ab =  activityBigwheelService.queryById(request.getParameter("activityid"));
	  	 	VelocityContext velocityContext = new VelocityContext();
			ActivityBigwheelConfigPlayRule abcp = activityBigwheelService.getBigwheelAll(activityId);
			if(null ==abcp || null == ab){
				velocityContext.put("error", "请确认活动是否存在！");
				String viewName = "jlb/error.vm";
				ViewVelocity.view(request,response,viewName,velocityContext);
			}
			PageQuery<ActivityBigwheelAward> pageQuery = new PageQuery<ActivityBigwheelAward>();
			 ActivityBigwheelAward aba = new ActivityBigwheelAward();
			 aba.setActivityid(activityId);
			 pageQuery.setQuery(aba);
			List<ActivityBigwheelAward> abalist =  activityBigwheelAwardService.queryAwardList(pageQuery);
			net.sf.json.JSONArray awardJO = net.sf.json.JSONArray.fromObject(abalist);
			net.sf.json.JSONObject abcpjo = net.sf.json.JSONObject.fromObject(abcp);
			if(abcp.getIspublish()!=null && abcp.getIspublish().equals("1")){
					abcpjo.put("isStart", "3");
				if(abcp.getStart().getTime()>new Date().getTime()){
					abcpjo.put("isStart", "1");
				}
				if(abcp.getEnd().getTime()<new Date().getTime()){
					abcpjo.put("isStart", "4");
				}

			}else{
				abcpjo.put("isStart", "0");
			}
			abcpjo.put("awardTips", awardJO);
			ActivityConfig abc = new ActivityConfig();
			abc = activityConfigService.queryOne(activityId) ;
  	 	velocityContext.put("extendOperation", ab.getExtendOperation());
  	 	velocityContext.put("gamePageJson", ab.getPagejsoncontent());
  	 	velocityContext.put("activityJson", abcpjo.toString());
  	 	velocityContext.put("imgTextShare", new net.sf.json.JSONObject().fromObject(abc).toString());
  	 	velocityContext.put("servertime", new Date().getTime());
  	 	
  	 	String viewName = "bigwheel/back/files/lot/index.vm";
  		ViewVelocity.view(request,response,viewName,velocityContext);
  }
  
  
@RequestMapping(value="queryawardrecord",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String queryawardrecord( HttpServletResponse response,HttpServletRequest request) throws Exception{
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
		PageQuery<ActivityBigwheelRecord> abrPageQuery = new PageQuery<ActivityBigwheelRecord>();
	 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
	 abr.setActivityid(activityId);
	 abrPageQuery.setQuery(abr);
	// Integer count = activityBigwheelRecordService.getAwardCount(abr);
	 List<UserAwardRecord> uar =  activityBigwheelRecordService.getUserAwardRecord(abrPageQuery);
	 net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
	 js.put("retCode", 0);
	js.put("message", "获取成功");
	
	net.sf.json.JSONObject js1 = new net.sf.json.JSONObject();
	net.sf.json.JSONObject js2 = new net.sf.json.JSONObject();
	js2.put("total", uar.size());
	js2.put("results", ja.fromObject(uar));
	js2.put("returnSize", uar.size());
	if(uar.size()>0){
		js2.put("first", uar.get(0));
	}else{
		js2.put("first", "");
	}

	js2.put("empty", false);
	js1.put("records",js2 );
	js.put("model", js1);
	return js.toString();
}

@RequestMapping(value="getAwardVerifyQrcode",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String getAwardVerifyQrcode( HttpServletResponse response,HttpServletRequest request) throws Exception{
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Integer arid = Integer.parseInt(request.getParameter("arid"));
	 Integer awardid = Integer.parseInt(request.getParameter("awardid"));
	 Integer activityid = Integer.parseInt(request.getParameter("activityid"));
		PageQuery<ActivityBigwheelRecord> abrPageQuery = new PageQuery<ActivityBigwheelRecord>();
	 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
	 abr.setId(arid);
	 abr.setActivityid(activityid);
	 abrPageQuery.setQuery(abr);
	 List<UserAwardRecord> uar =  activityBigwheelRecordService.getUserAwardRecord(abrPageQuery);
	 ActivityBigwheelAward aba = activityBigwheelAwardService.queryById(awardid.toString());
	 js.put("retCode", 0);
	js.put("message", "获取核销二维码成功");
	
	net.sf.json.JSONObject js1 = new net.sf.json.JSONObject();
	js1.put("awardRecord", uar.get(0));
	String url = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/activity/awardApproveUser/approve.html?activityid="+uar.get(0).getActivityId()+"&recordid="+uar.get(0).getId();
			url = URLEncoder.encode(url);
	js1.put("qrcode_url", url);
	js1.put("award", aba);
	js.put("model", js1);
	return js.toString();
}


/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/awardlist",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String awardlist(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
		PageQuery<ActivityBigwheelRecord> abrPageQuery = new PageQuery<ActivityBigwheelRecord>();
	 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
	 abr.setActivityid(activityId);
	 abr.setOpenid(user.getOpenid());
	 abrPageQuery.setQuery(abr);
	List<UserAwardRecord> uar =  activityBigwheelRecordService.getUserAwardRecord(abrPageQuery);
	net.sf.json.JSONArray ja = new net.sf.json.JSONArray();
	 js.put("retCode", 0);
	js.put("message", "获取成功");
	
	net.sf.json.JSONObject js1 = new net.sf.json.JSONObject();
	js1.put("records", ja.fromObject(uar));
	js.put("model", js1);
	
	return js.toString();
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="/dolottery",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String dolottery(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
	 String random = null;
	 if(request.getSession().getAttribute("random") !=null ){
		 random = (String) request.getSession().getAttribute("random"); 
	 }else{
		 random = request.getParameter("random");
	 }
	 String helpid =  request.getParameter("fromOpenid");
	 ActivityBigwheel ab =  activityBigwheelService.queryById(request.getParameter("activityid"));
	 
	 if(null == ab || !ab.getIspublish().equals("1") || ab.getEnd().getTime()<new Date().getTime() || ab.getStart().getTime()>new Date().getTime()){
			js.put("retCode", -38);
			js.put("message", "抱歉，您没有抽奖机会啦！");
			return js.toString();
	 }
	
		ActivityConfig ac = new ActivityConfig();
		ac.setActivityid(activityId);
	 ac = activityConfigService.queryOne(activityId) ;
	 if(user == null){
		 //未登录
			js.put("retCode", -38);
			js.put("message", "抱歉，您没有抽奖机会啦！");
			return js.toString();
	 }else{
		 //不在抽奖区域
		 if(ac.getNeedarea().equals("true") &&( ac.getAddresstext().indexOf(user.getProvince())<0 && ac.getAddresstext().indexOf(user.getCity())<0))
		 {
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 //不在现场
		 if(ac.getIscurrent().equals("true")&& !random.equals(ab.getCreatDate().getTime())){
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 //需要关注
		 if(ac.getNeedfollow().equals("true")&&user.getSubscribe()!="1"){
				js.put("retCode", -38);
				js.put("message", "关注活动公众号方可参加活动");
				return js.toString();
		 }
		 if((ac.getNeedsex().equals("1")&&user.getSex().equals("0"))||(ac.getNeedsex().equals("2")&&user.getSex().equals("1")) ){
				//j.setSuccess(false);
				//j.setMsg(ac.getNeedsex().equals("1")?"该活动只允许男性参加":"该活动只允许女性参加");
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 if( ac.getParticipateLimit()!=null && ac.getParticipateLimit().equals("true")&&user.getMobile()==null){
				js.put("retCode", -38);
				js.put("message", "填写手机号方可参与游戏");
				return js.toString();
				
		 }
		 PageQuery<ActivityBigwheelPlayrule> abppagerule = new PageQuery<ActivityBigwheelPlayrule>();
		 ActivityBigwheelPlayrule abp = new ActivityBigwheelPlayrule();
		 abp.setActivityid(activityId);
		 abppagerule.setQuery(abp);
		 List<ActivityBigwheelPlayrule> abplist = activityBigwheelPlayruleService.queryBYCol(abppagerule);
		 
		 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
		 long current=System.currentTimeMillis();
		 long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
		 abr.setDotime(new Timestamp(zero));
		 abr.setOpenid(user.getOpenid());
		 abr.setActivityid(activityId);
		 if(activityBigwheelRecordService.selectByOpenIDAndDate(abr).size()>=abplist.get(0).getDaylotterycount()){
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 abr.setDotime(null);
		 PageQuery<ActivityBigwheelRecord> pagequeryAbr = new PageQuery<ActivityBigwheelRecord>();
		 pagequeryAbr.setQuery(abr);
		 if(activityBigwheelRecordService.count(pagequeryAbr)>=abplist.get(0).getTotallotterycount()){
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 } 
		 if((ab.getIsBuy() == null || !ab.getIsBuy().equals("1")) && activityBigwheelRecordService.count(pagequeryAbr)>=Integer.parseInt(freeActivityCount)){
				js.put("retCode", -3404);
				js.put("message", "该活动由百花互动平台免费赞助支持，由于参与人数已达上限，如果想继续使用请购买套餐！");
				return js.toString();
		 } 
		 if(activityBigwheelRecordService.getAwardList(pagequeryAbr).size()>=abplist.get(0).getSingleuserawards()){
				js.put("retCode", -38);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 //中奖机率 根据 每天产生的
		// abr.setOpenid(null);
		// abr.setActivityid(activityId);

		 
			 ActivityBigwheelAward aba = new ActivityBigwheelAward();
			 aba.setActivityid(activityId);
			 int awardsum = activityBigwheelAwardService.getAwardSUM(aba);
				String ip= null;
				try {
					ip = sendMessageLogService.getIpAddr(request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				net.sf.json.JSONObject map = null;
				ActivityBigwheelAward probaward = new ActivityBigwheelAward();
				int index = -1;
				long leftday = BaihuaUtil.getDaysBetween(new Date(),ab.getEnd())+1;
				
			 synchronized(this) { 
				 List<ActivityBigwheelAward> abalist = activityBigwheelAwardService.queryAwardList(activityId);
				 double probability = 0;
				 double sumprob =0;
				 int awardremainnum = 0;
				 int b=0;
				 for(ActivityBigwheelAward aba1 : abalist){
						 awardremainnum += aba1.getAwardRemainNum();
						 //查询有多少人帮他助力，如果超过助力人数，增加中奖机率
						 ActivityBigwheelRecord a = new ActivityBigwheelRecord();
						 a.setActivityid(activityId);
						 a.setHelpid(user.getOpenid());
						 if(activityBigwheelRecordService.helpcount(a)>=abplist.get(0).getHelpnum()){
							 probability=((double)aba1.getAwardrealnum()/(double)awardsum)*(((double)ab.getGlobalprobability()+(double)ab.getGlobalprobability()*((double)abplist.get(0).getPercentage()/(double)100))/(double)100);
						 }else{
							 probability=((double)aba1.getAwardrealnum()/(double)awardsum)*((double)ab.getGlobalprobability()/(double)100);
						 }
						 abalist.get(b).setProb(probability);
						 b++;
						 sumprob+=probability; 
				 }
				 Double leftpro = (double)awardremainnum/leftday;
				 if(leftpro>=ab.getEverydayprob() || leftday==1){
					 //才有机会中
						index = LotteryUtil.lottery(abalist,Double.valueOf(( (double)ab.getGlobalprobability())/(double)100),sumprob);
						if (index>0) {//中奖啦
					        Collections.sort(abalist,Collections.reverseOrder());
					        probaward= abalist.get(index-1);
							if(probaward.getSngeneratetype() == 1){
								int i = probaward.getSncodes().indexOf("\n");
								if(i<0){
									probaward.setSncodes("");
								}else{
									probaward.setSncodes(probaward.getSncodes().substring(i+2));
								}
							}
							probaward.setAwardRemainNum(probaward.getAwardRemainNum()-1);
							//中奖后修改剩余奖品数量
							activityBigwheelAwardService.doEdit(probaward);
							map = net.sf.json.JSONObject.fromObject(probaward);
						}
					 
				 }
				 

			 }
					abr.setActivityid(activityId);
					abr.setDotime(new Date());
					abr.setHelpid(helpid);
					abr.setIp(ip);
					abr.setOpenid(user.getOpenid());
					if (index>0) {	
						if(abplist.get(0).getExchangelimit().equals("true")){
							abr.setStatus("0");
						}else{abr.setStatus("1");}
						if(probaward.getSngeneratetype() == 0){
							abr.setSncode(String.valueOf(System.currentTimeMillis()));
						}
						if(probaward.getSngeneratetype() == 1){
							int i = probaward.getSncodes().indexOf("\n");
							if(i<0){
								abr.setSncode(probaward.getSncodes());
							}else{
								abr.setSncode(probaward.getSncodes().substring(0, i));
							}
						}
						abr.setAwardid(probaward.getId());
						//中奖后添加抽奖记录
					activityBigwheelRecordService.doAdd(abr);
					map.put("status", abr.getStatus());
					net.sf.json.JSONObject jsp = new net.sf.json.JSONObject();
					jsp.put("awardRecord", map);
					js.put("retCode", 0);
					js.put("message", "恭喜你已中奖");
					js.put("model", jsp);
					}else{
						activityBigwheelRecordService.doAdd(abr);
						js.put("retCode", -38);
						js.put("message", "很遗憾没有中奖");
					}
			
			return js.toString();
	 }


}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/getJsApiTicket",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String getJsApiTicket(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 String jwid = request.getParameter("uid");
	 if(null == jwid || ""== jwid){
		 js.put("ret", 30);
		 js.put("msg", "未绑定已认证的公众号！");
	 }else{
		 JwWebJwid jw = jwWebJwidServicenew.getByJWid(jwid);
		 if( null != jw){
			 js.put("ret", 0);
			 js.put("msg", "success");
				net.sf.json.JSONObject js1 = new net.sf.json.JSONObject();
				js1.put("ticket",jw.getJsapiticket());
				js1.put("appid", jw.getAccountappid());
				js.put("model", js1);
		 }else{
			 js.put("ret", 30);
			 js.put("msg", "未绑定已认证的公众号！");
		 }
	 }

	return js.toString();
}

@RequestMapping(value = "/verifyplay",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String verifyplay(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
	 String random = request.getParameter("random");
	 ActivityBigwheel ab =  activityBigwheelService.queryById(activityId.toString());
		ActivityConfig ac = new ActivityConfig();
		ac.setActivityid(activityId);
	 ac = activityConfigService.queryOne(activityId) ;
	 if(user == null){
		 //未登录
			js.put("retCode", -3404);
			js.put("message", "抱歉，您没有抽奖机会啦！");
			return js.toString();
	 }else{
		 if(ab.getIspublish() ==null || ab.getIspublish().equals("0")){
				js.put("retCode", -3404);
				js.put("message", "活动尚未发布请等待！");
				return js.toString();
		 }
		 //不在抽奖区域
		 if(ac.getNeedarea().equals("true") &&( ac.getAddresstext().indexOf(user.getProvince())<0 && ac.getAddresstext().indexOf(user.getCity())<0))
		 {
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 //不在现场
		 if(ac.getIscurrent().equals("true")&& !random.equals(ab.getCreatDate().getTime())){
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 //需要关注
		 if(ac.getNeedfollow().equals("true")&&user.getSubscribe()!="1"){
				js.put("retCode", -3404);
				js.put("message", "关注活动公众号方可参加活动");
				return js.toString();
		 }
		 if((ac.getNeedsex().equals("1")&&user.getSex().equals("0"))||(ac.getNeedsex().equals("2")&&user.getSex().equals("1")) ){
				//j.setSuccess(false);
				//j.setMsg(ac.getNeedsex().equals("1")?"该活动只允许男性参加":"该活动只允许女性参加");
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 if( ac.getParticipateLimit() != null && ac.getParticipateLimit().equals("true")&&user.getMobile()==null){
				js.put("retCode", -3404);
				js.put("message", "填写手机号方可参与游戏");
				return js.toString();
		 }
		 PageQuery<ActivityBigwheelPlayrule> abppagerule = new PageQuery<ActivityBigwheelPlayrule>();
		 ActivityBigwheelPlayrule abp = new ActivityBigwheelPlayrule();
		 abp.setActivityid(activityId);
		 abppagerule.setQuery(abp);
		 List<ActivityBigwheelPlayrule> abplist = activityBigwheelPlayruleService.queryBYCol(abppagerule);
		 
		 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
		 long current=System.currentTimeMillis();
		 long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
		 abr.setDotime(new Timestamp(zero));
		 abr.setOpenid(user.getOpenid());
		 abr.setActivityid(activityId);
		 if(activityBigwheelRecordService.selectByOpenIDAndDate(abr).size()>=abplist.get(0).getDaylotterycount()){
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 abr.setDotime(null);
		 PageQuery<ActivityBigwheelRecord> pagequeryAbr = new PageQuery<ActivityBigwheelRecord>();
		 pagequeryAbr.setQuery(abr);
		 if(activityBigwheelRecordService.count(pagequeryAbr)>=abplist.get(0).getTotallotterycount()){
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 } 
		 
		 if((ab.getIsBuy() == null || !ab.getIsBuy().equals("1"))  && activityBigwheelRecordService.count(pagequeryAbr)>=Integer.parseInt(freeActivityCount)){
				js.put("retCode", -3404);
				js.put("message", "该活动由百花互动平台免费赞助支持，由于参与人数已达上限，如果想继续使用请购买套餐！");
				return js.toString();
		 } 
		 
		 
		 if(activityBigwheelRecordService.getAwardList(pagequeryAbr).size()>=abplist.get(0).getSingleuserawards()){
				js.put("retCode", -3404);
				js.put("message", "抱歉，您没有抽奖机会啦！");
				return js.toString();
		 }
		 
			js.put("retCode", 0);
			js.put("message", "可以参与");
			return js.toString();
}


}


@RequestMapping(value = "/query_lottery_chance",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String queryLotteryChance(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 net.sf.json.JSONObject js1 = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 Integer activityId = Integer.parseInt(request.getParameter("activityid"));
	 
	 
	 ActivityBigwheel ab =  activityBigwheelService.queryById(request.getParameter("activityid"));
	 
	 if(null != ab &&   ab.getEnd().getTime()<new Date().getTime()  ){
			js1.put("curDay", 0);
			js1.put("daylimit", 0);
			js1.put("actlimit", 0);
			js1.put("used", 0);
			js.put("model", js1);
			return js.toString();
	 }
	 if(user !=null && activityId !=null){
	 PageQuery<ActivityBigwheelPlayrule> abppagerule = new PageQuery<ActivityBigwheelPlayrule>();
	 ActivityBigwheelPlayrule abp = new ActivityBigwheelPlayrule();
	 abp.setActivityid(activityId);
	 abppagerule.setQuery(abp);
	 List<ActivityBigwheelPlayrule> abplist = activityBigwheelPlayruleService.queryBYCol(abppagerule);
	 
	 ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
	 long current=System.currentTimeMillis();
	 //获取 当天零点
	 long zero=current-TimeZone.getDefault().getRawOffset();
	 abr.setDotime(new Timestamp(zero));
	 abr.setOpenid(user.getOpenid());
	 abr.setActivityid(activityId);
	 
	 int curDay = activityBigwheelRecordService.selectByOpenIDAndDate(abr).size();
	 int daylimit = abplist.get(0).getDaylotterycount();
	 abr.setDotime(null);
	 PageQuery<ActivityBigwheelRecord> pagequeryAbr = new PageQuery<ActivityBigwheelRecord>();
	 pagequeryAbr.setQuery(abr);
	 int actlimit = abplist.get(0).getTotallotterycount();
	 int used = activityBigwheelRecordService.count(pagequeryAbr);
		js1.put("curDay", curDay);
		js1.put("daylimit", daylimit);
		js1.put("actlimit", actlimit);
		js1.put("used", used);
		js.put("model", js1);
		return js.toString();
	 }else{
			js1.put("curDay", 0);
			js1.put("daylimit", 0);
			js1.put("actlimit", 0);
			js1.put("used", 0);
			js.put("model", js1);
			return js.toString();
	 }
}


@RequestMapping(value = "/collect_user_info",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String collectUserInfo(HttpServletResponse response,HttpServletRequest request){
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 String telphone = request.getParameter("phonenum");
	 if(user == null || telphone ==null || telphone.equals("")){
		 //未登录
			js.put("retCode", -3404);
			js.put("message", "信息不完整");
			return js.toString();
	 }else{
		 user.setMobile(telphone);
		    userinfoService.updateByopenid(user);
			js.put("retCode", 0);
			js.put("message", "可以参与");
			return js.toString();
}
}

@RequestMapping(value = "/verifyaward",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String verifyAward(HttpServletResponse response,HttpServletRequest request){
	
	
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 Integer recordid = Integer.parseInt(request.getParameter("recordid"));
	 String sncode = request.getParameter("sncode");
	 String verifyPwd = request.getParameter("verifyPwd");
	 Integer activityid = Integer.parseInt(request.getParameter("activityid"));
		ActivityConfig ac = new ActivityConfig();
		ac.setActivityid(activityid);
	 ac = activityConfigService.queryOne(activityid) ;
		PageQuery<ActivityBigwheelRecord> abrPageQuery = new PageQuery<ActivityBigwheelRecord>();
	 ActivityBigwheelRecord ar = new ActivityBigwheelRecord();
	 ar.setActivityid(activityid);
	 ar.setSncode(sncode);
	 ar.setId(recordid);
	 ar.setOpenid(user.getOpenid());
	 abrPageQuery.setQuery(ar);
	 if(ac.getActivityConfigcol().equals(verifyPwd) && activityBigwheelRecordService.getUserAwardRecord(abrPageQuery) !=null){
		 ar.setStatus("3");
		 activityBigwheelRecordService.doEdit(ar);
			js.put("retCode", 0);
			js.put("message", "核销成功");
			return js.toString();
	 }else{
			js.put("retCode", -30);
			js.put("message", "核销失败");
			return js.toString();
	 }
}


@RequestMapping(value = "/init",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String init(HttpServletResponse response,HttpServletRequest request){
	 Integer activityid = Integer.parseInt(request.getParameter("activityid"));
	net.sf.json.JSONObject js =new  net.sf.json.JSONObject();
	 net.sf.json.JSONObject jsopenuser =new  net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 if(user==null){
		 net.sf.json.JSONObject initjo =new  net.sf.json.JSONObject();
		 initjo.put("retCode", -1);
		 initjo.put("message", "非正常操作");
		 
			return initjo.toString();
	 }
	 jsopenuser.put("openuser", net.sf.json.JSONObject.fromObject(user));
	 js.put("userPlayinfo", jsopenuser);
	 
	//得到playnum playtime helpnum
	 List<ActRuntime> acruntime = activityBigwheelService.getActRuntime();
	 net.sf.json.JSONObject acruntimejo =net.sf.json.JSONObject.fromObject(acruntime.get(0));
	 

	 //获取奖项设置
		PageQuery<ActivityBigwheelAward> pageQuery = new PageQuery<ActivityBigwheelAward>();
		 ActivityBigwheelAward aba = new ActivityBigwheelAward();
		 aba.setActivityid(activityid);
		 pageQuery.setQuery(aba);
	List<ActivityBigwheelAward> abalist =  activityBigwheelAwardService.queryAwardList(pageQuery);
	int a =0;
	int b =0;
	for(ActivityBigwheelAward abaa:abalist){
		a = a+abaa.getAwardrealnum();
		b = b+abaa.getAwardRemainNum();
	}
	 acruntimejo.put("awardNum", b);
	 acruntimejo.put("usedTrophyNum", b);
	 acruntimejo.put("totalTrophyNum", a);
	 
	 js.put("actRuntime", acruntimejo);
	net.sf.json.JSONArray awardJO = net.sf.json.JSONArray.fromObject(abalist);
	js.put("trophyList", awardJO);
	
	 //获取获奖记录及获奖人信息，获奖人，奖项信息
	PageQuery<ActivityBigwheelRecord> abrPageQuery = new PageQuery<ActivityBigwheelRecord>();
	ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
	abr.setActivityid(activityid);
	abr.setOpenid(user.getOpenid());
	abrPageQuery.setQuery(abr);
	List<UserAwardRecord> uarlist = activityBigwheelRecordService.getUserAwardRecord(abrPageQuery);
	net.sf.json.JSONArray abrjo = net.sf.json.JSONArray.fromObject(uarlist);
	js.put("prizeList", abrjo);
	
	ActivityBigwheelConfigPlayRule abcp = activityBigwheelService.getBigwheelAll(activityid);
	net.sf.json.JSONObject abcpjo = net.sf.json.JSONObject.fromObject(abcp);
	
	if(abcp.getIspublish()!=null && abcp.getIspublish().equals("1")){
		abcpjo.put("isStart", "3");
	if(abcp.getStart().getTime()>new Date().getTime()){
		abcpjo.put("isStart", "1");
	}
	if(abcp.getEnd().getTime()<new Date().getTime()){
		abcpjo.put("isStart", "4");
	}

	}else{
		abcpjo.put("isStart", "0");
	}
	
	abcpjo.remove("start");
	abcpjo.remove("end");
	abcpjo.put("start", abcp.getStart().getTime());
		abcpjo.put("end", abcp.getEnd().getTime());
	abcpjo.put("awardTips", awardJO);
	
	js.put("activity", abcpjo);
	
	js.put("sendRuleMap", abcp.getSingleuserawards());
	
	 net.sf.json.JSONObject initjo =new  net.sf.json.JSONObject();
	 initjo.put("retCode", 0);
	 initjo.put("message", "初始化成功");
	 initjo.put("model", js);
	 
		return initjo.toString();
}

@RequestMapping(value = "/checkverifycode",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public String checkverifycode(HttpServletResponse response,HttpServletRequest request){
	
	 net.sf.json.JSONObject js = new net.sf.json.JSONObject();
	 Userinfo user = (Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 String telphone = request.getParameter("phonenum");
	 String awardrecordid = request.getParameter("awardrecordid");
	 String realname = request.getParameter("realname");
	 if(user == null || telphone ==null || telphone.equals("")){
		 //未登录
			js.put("retCode", -3404);
			js.put("message", "信息不完整");
			return js.toString();
	 }else{
		 user.setMobile(telphone);
		 user.setRealname(realname);
		    userinfoService.updateByopenid(user);
			ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
			abr.setId(Integer.parseInt(awardrecordid));
			abr.setStatus("1");
			activityBigwheelRecordService.doEdit(abr);
			js.put("retCode", 0);
			js.put("message", "可以参与");
			return js.toString();
	 	}
 
	}


}


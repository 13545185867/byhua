package com.jeecg.p3.toupiao.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PaginatedList;
import org.jeecgframework.p3.core.utils.common.StringUtils;

import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;
import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoAConfig;
import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;
import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;
import com.jeecg.p3.toupiao.service.WxActToupiaoConfigService;
import com.jeecg.p3.toupiao.service.WxActToupiaoOptionService;
import com.jeecg.p3.toupiao.service.WxActToupiaoService;
import com.jeecg.p3.toupiao.service.WxCatToupiaoRecordService;
import com.jeecg.p3.userinfo.entity.Userinfo;
import com.jeecg.p3.weixinaccount.entity.JwWebJwid;

import redis.clients.jedis.Jedis;

import org.jeecgframework.p3.core.web.BaseController;


 /**
 * 描述：</b>WxActToupiaoController<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/toupiao")
public class ToupiaoController extends BaseController{
  @Autowired
  private WxActToupiaoService wxActToupiaoService;
  @Autowired
  private WxActToupiaoOptionService wxActToupiaoOptionService;
  @Autowired
  private WxActToupiaoConfigService wxActToupiaoConfigService;
  
  @Autowired
  private WxCatToupiaoRecordService wxCatToupiaoRecordService;
  
  @Value("#{sysconfig['redis.ip']}")
  private String redisip;
  
/**
  * 投票首页
  * @return
  */
@RequestMapping(value="toIndex",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@RequestParam(required = false, value = "searchkeyword" ) String searchkeyword, 
		HttpServletResponse response
		,HttpServletRequest request ) throws Exception{
	
	Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	long start = System.currentTimeMillis();
	// 装载微信所需参数
	String jwid = (String) request.getSession().getAttribute("jwid");
	String actId = request.getParameter("actId");

	VelocityContext velocityContext = new VelocityContext();
	if (null !=  user) {
		velocityContext.put("isinweixin",1);
	}else{
		velocityContext.put("isinweixin",0);
	}

	// 参数验证
				// 获取活动信息和活动配置信息
	WxActToupiaoAConfig wtac  = wxActToupiaoService.queryByActId(actId);
	if(wtac == null){
		velocityContext.put("error", "请确认活动是否已发布！");
		String viewName = "jlb/error.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
	velocityContext.put("toupiao", wtac);
	String expired ;
	if(wtac.getEndTime().getTime()> new Date().getTime()){
		expired = "0" ;
	}else{
		expired = "1";
	}
	if("1".equals(wtac.getFoucsUserCanJoin())){//如果活动设置了需要关注用户才能参加	
		//未关注
		 if("0".equals(user.getSubscribe())){
			 velocityContext.put("subscribe", 0);
		 }else{
			 velocityContext.put("subscribe", 1);
		 }
	 }else{
		 velocityContext.put("subscribe", 1);
	 }

	velocityContext.put("expired", expired);
	//获取活动选项前10个
 	PageQuery<WxActToupiaoOption> pageQuery = new PageQuery<WxActToupiaoOption>();
 	WxActToupiaoOption wxtpop = new WxActToupiaoOption();
 	wxtpop.setActId(actId);
 	wxtpop.setIsActive("1");
 	wxtpop.setIsDelete("0");
 	if(searchkeyword !=null&&!"".equals(searchkeyword)){
        if(searchkeyword.matches("\\d+")) {
            //全都是数字的时候
        	wxtpop.setIdentifier(Integer.parseInt(searchkeyword));
        } else {
        	   //不全都是数字
        	wxtpop.setTitle(searchkeyword);
        }
        
        velocityContext.put("backbar", true);
 	}
 	pageQuery.setPageNo(1);
 	if(wtac.getOptionViewStyle().equals("3")){
 		pageQuery.setPageSize(9);
 	}else{
		pageQuery.setPageSize(10);
 	}
	pageQuery.setQuery(wxtpop);
	velocityContext.put("wxActToupiaoOption",wxtpop);
	PageList<WxActToupiaoOption> queryPageList= wxActToupiaoOptionService.queryPageList(pageQuery);
	velocityContext.put("optioncount",queryPageList.getPagenation().getItemCount());
	velocityContext.put("pagesize",pageQuery.getPageSize());
	velocityContext.put("votecountsum",wxActToupiaoOptionService.getSumByActId(wtac.getId()));

	velocityContext.put("pageInfos",SystemTools.convertPaginatedList(queryPageList));
	Jedis resource = 	 new Jedis(redisip,6379);
	resource.incr(actId);
	velocityContext.put("visitCount",resource.get(actId));
	resource.close();
	String viewName = "toupiao/vm/index.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);

}

 /**
  * 详情
  * @return
  */
@RequestMapping(value = "/toShowOptionMore",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson toShowOptionMore(@RequestParam(required = true, value = "actId" ) String actId,@RequestParam(required = true, value = "pageNo" ) String pageNo,@RequestParam(required = true, value = "optionViewStyle" ) String optionViewStyle)throws Exception{
				//获取活动选项前10个
			 	PageQuery<WxActToupiaoOption> pageQuery = new PageQuery<WxActToupiaoOption>();
			 	WxActToupiaoOption wxtpop = new WxActToupiaoOption();
			 	wxtpop.setActId(actId);
			 	wxtpop.setIsActive("1");
			 	wxtpop.setIsDelete("0");
			 	pageQuery.setPageNo(Integer.parseInt(pageNo));
			 	if(null != optionViewStyle && optionViewStyle.equals("3")){
			 		pageQuery.setPageSize(9);
			 	}else{
					pageQuery.setPageSize(10);
			 	}
				pageQuery.setQuery(wxtpop);
				PageList<WxActToupiaoOption> queryPageList= wxActToupiaoOptionService.queryPageList(pageQuery);
				List<WxActToupiaoOption> wxtpo = queryPageList.getValues();
	
	AjaxJson j = new AjaxJson();
	j.setObj(wxtpo);
		try {
			j.setMsg("编辑成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
}


/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/dovote",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doVote(@RequestParam(required = true, value = "actId" ) String actId,
		@RequestParam(required = true, value = "ops" ) String ops 
		,HttpServletResponse response
		,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
    if(null == user){
    	j.setSuccess(false);
    	j.setMsg("投票失败");
    	return j;
    }
	WxCatToupiaoRecord wctr = new WxCatToupiaoRecord();
	WxActToupiaoAConfig wtac =  wxActToupiaoService.queryByActId(actId);
long current=System.currentTimeMillis();
long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
wctr.setVoteTime(new Timestamp(zero));
wctr.setOpenid(user.getOpenid());
wctr.setActId(actId);
List<WxCatToupiaoRecord> wctrl = wxCatToupiaoRecordService.getByOpenIDATime(wctr);
String[] options = ops.split("\\|");
if(wtac.getIsManyVtoe().equals("0")&&wctrl.size()>=1){
j.setSuccess(false);
j.setMsg("一天只能投票一次");
}else if(wtac.getIsManyVtoe().equals("1") && wctrl.size()>=wtac.getVerydayMaxVotecount()){
	j.setSuccess(false);
	j.setMsg("一天只能投票"+wtac.getVerydayMaxVotecount()+"次");
}else if(wtac.getVoteType().equals("1") && options.length>Integer.parseInt(wtac.getCheckVoteCount())){
	j.setSuccess(false);
	j.setMsg("每次投票只能选择"+wtac.getCheckVoteCount()+"项");
}else{
	try {
		for(int i=0 ;i<options.length;i++){
			wxActToupiaoOptionService.updateVoteCount(options[i]);
		}
		// 装载微信所需参数
		wctr.setActId(actId);
		wctr.setIp(request.getRemoteAddr());
		wctr.setJwid(((JwWebJwid)request.getSession().getAttribute("jw")).getJwid());
		wctr.setOpenid(user.getOpenid());
		wctr.setOptionId(ops);
		wctr.setVoteTime(new Date());

		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		wctr.setVoteTime(date);
		wxCatToupiaoRecordService.doAdd(wctr);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
}
	return j;
}


/**
* 列表页面
* @return
*/
@RequestMapping(value="detail",method = {RequestMethod.GET,RequestMethod.POST})
public void detail(@RequestParam(required = true, value = "id" ) String id,
		@RequestParam(required = true, value = "actId" ) String actId, HttpServletResponse response
		,HttpServletRequest request ) throws Exception{
	Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	     
	long start = System.currentTimeMillis();

	// 装载微信所需参数
	VelocityContext velocityContext = new VelocityContext();
	if (null != user) {
		velocityContext.put("isinweixin",1);
	}else{
		velocityContext.put("isinweixin",0);
	}
	List<WxActToupiaoAConfig> wtpac = null;
	// 参数验证
				// 获取活动信息和活动配置信息
	WxActToupiaoAConfig wtac = wxActToupiaoService.queryByActId(actId) ;
	velocityContext.put("toupiao", wtac);
	String expired ;
	if(wtac.getEndTime().getTime()> new Date().getTime()){
		expired = "0" ;
	}else{
		expired = "1";
	}
	velocityContext.put("expired", expired);
	velocityContext.put("toupiaoOption",wxActToupiaoOptionService.queryById(id));
	String viewName = "toupiao/vm/detail.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);
}



/**
 * 投票首页
 * @return
 */
@RequestMapping(value="order",method = {RequestMethod.GET,RequestMethod.POST})
public void voteOrder(HttpServletResponse response ,@RequestParam(required = true, value = "actId" ) String actId
		,HttpServletRequest request) throws Exception{
	long start = System.currentTimeMillis();
	// 装载微信所需参数
	VelocityContext velocityContext = new VelocityContext();
	
	List<WxActToupiaoAConfig> wtpac = null;
	// 参数验证
			//	validateWeixinDtoParam(weixinDto);
				// 获取活动信息和活动配置信息
				WxActToupiaoAConfig wtac = wxActToupiaoService.queryByActId(actId);
				List<WxActToupiaoOption> wtop = wxActToupiaoOptionService.getVoteOrder(actId);
				for(int i=0 ; i<wtop.size();i++){
					 String r=null,g=null,b=null;  
					 Random random = new Random(); 

					     if(i==0){
							  r = Integer.toHexString(255).toUpperCase();  
							  g = Integer.toHexString(0).toUpperCase();  
							  b = Integer.toHexString(0).toUpperCase(); } 
							  else{
							  r = Integer.toHexString(random.nextInt(255)).toUpperCase();  
							  g = Integer.toHexString(random.nextInt(255)).toUpperCase();  
							  b = Integer.toHexString(random.nextInt(255)).toUpperCase(); 
					     }
					  r = r.length()==1 ? "0" + r : r ;  
					  g = g.length()==1 ? "0" + g : g ;  
					  b = b.length()==1 ? "0" + b : b ;  
					  wtop.get(i).setImage(r+g+b);
				}
	
	velocityContext.put("toupiao", wtac);
	velocityContext.put("pageInfos",wtop);
	String viewName = "toupiao/vm/order.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);

}

 

/**
* 列表页面
* @return
*/
@RequestMapping(value="baoming",method = {RequestMethod.GET,RequestMethod.POST})
public void baoming(
		@RequestParam(required = true, value = "actId" ) String actId,
		HttpServletResponse response
		,HttpServletRequest request ) throws Exception{
	WxActToupiaoOption wxActToupiaoOption = new WxActToupiaoOption();
	VelocityContext velocityContext = new VelocityContext();
	 String viewName = "toupiao/vm/baoming.vm";
		if(!"".equals(actId)||null !=actId ){
			wxActToupiaoOption.setActId(actId);
			velocityContext.put("wxActToupiaoOption", wxActToupiaoOption);
		}
		WxActToupiaoAConfig wtac = wxActToupiaoService.queryByActId(actId);
		velocityContext.put("toupiao", wtac);
		
		String expired ;
		if(wtac.getEndEnterTime().getTime()> new Date().getTime() && wtac.getIsEnter().equals("1")){
			expired = "0" ;
		}else{
			expired = "1";
		}
		velocityContext.put("expired", expired);
		
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 * @throws Exception 
 */
@RequestMapping(value = "/dobaoming",method ={RequestMethod.GET, RequestMethod.POST})
public void doBaoMing(@ModelAttribute WxActToupiaoOption wxActToupiaoOption
		,HttpServletResponse response
		,HttpServletRequest request
		) throws Exception{
	VelocityContext velocityContext = new VelocityContext();
	 String viewName = "toupiao/back/result.vm";
	 Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
		
	 if(null == user){
		 velocityContext.put("error", "1");
	 }else{
			WxActToupiaoAConfig wtac = wxActToupiaoService.queryByActId(wxActToupiaoOption.getActId());
			if(wtac.getEndEnterTime().getTime()> new Date().getTime() && wtac.getIsEnter().equals("1")){
				try {
					if(wxActToupiaoOption.getVoteCount()==null){
						wxActToupiaoOption.setVoteCount(0);
					}
					wxActToupiaoOption.setCreatTime(new Date());
					wxActToupiaoOptionService.doAdd(wxActToupiaoOption);
					velocityContext.put("error", "0");
				} catch (Exception e) {
					velocityContext.put("error", "1");
				}
			}else{
				velocityContext.put("error", "1");
			}
	 }

	 ViewVelocity.view(request,response,viewName,velocityContext);
}

}


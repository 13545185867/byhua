package com.jeecg.p3.core.interceptors;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.cg.util.HttpsGetUtil;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;
import com.jeecg.p3.oauth2.task.WeixinAccountTokenTask;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.userinfo.entity.Userinfo;
import com.jeecg.p3.userinfo.service.UserinfoService;
import com.jeecg.p3.userservice.entity.Userservice;
import com.jeecg.p3.userservice.service.UserserviceService;
import com.jeecg.p3.weixinaccount.entity.JwWebJwid;
import com.jeecg.p3.weixinaccount.service.JwWebJwidService;

public class FrontInterceptor
  implements HandlerInterceptor
{
  public static final Logger logger = LoggerFactory.getLogger(FrontInterceptor.class);
  private List<String> excludeUrls;
  private String mode;
  @Value("#{sysconfig['appid']}")
  private String appid;
  
  @Value("#{sysconfig['secret']}")
  private String secret;
  
  @Value("#{sysconfig['jwid']}")
  private String jwid1;

  @Value("#{sysconfig['jlbid']}")
  private String jlbid;
  
  @Value("#{sysconfig['nosubscrible.page']}")
  private String page;  
  
  @Autowired
  private JwWebJwidService jwWebJwidServicenew;
  
  @Autowired
  private UserinfoService userinfoService;
  
  @Autowired
  private UserserviceService userserviceService;
  
  @Autowired
  private WeixinAccountTokenTask weixinAccountTokenTask;
  
  public List<String> getExcludeUrls()
  {
    return this.excludeUrls;
  }

  public void setExcludeUrls(List<String> excludeUrls) {
    this.excludeUrls = excludeUrls;
  }

  public String getMode() {
    return this.mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
    throws Exception
  {
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
    throws Exception
  {
  }

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
    throws Exception
  {
    if ("DEV".equals(this.mode)) {
	    request.getSession().setAttribute("vipuser",true);
    	Userinfo userinfo = new Userinfo();  
    	userinfo = userinfoService.getByOpenId("oHXea03iP1WgeH0n7os2k8n2Wf18").get(0);
    	request.getSession().setAttribute("OPERATE_ACTIVITY_USER",userinfo);
	    
      return true;
    }
    
   
    String requestPath = getRequestPath(request);
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    String jwid = request.getParameter("jwid");
    
    String basePath = request.getContextPath();
    request.setAttribute("basePath", basePath);
    if (this.excludeUrls.contains(requestPath))
    {
      return true;
    }
    
    if (requestPath.indexOf("ueditor/")>-1)
    {
      return true;
    }
    if ((requestPath != null) && (requestPath.indexOf(".html") > -1)) {
      if ((requestPath != null) && (requestPath.indexOf("/back/") > -1)) {
        return true;
      }
      if(null != jwid){
    	  request.getSession().setAttribute("jwid",jwid);
      }
      String curjwid = (String) request.getSession().getAttribute("jwid");
      JwWebJwid jw = (JwWebJwid)request.getSession().getAttribute("jw");
      if(null ==jw){
	      if(null != jwid || null !=curjwid){
	    	    jw = jwWebJwidServicenew.getByJWid(curjwid) ;
	    	    request.getSession().setAttribute("jw",jw);
	      }else{
	    	  jw = jwWebJwidServicenew.getByJWid(jwid1);
	    	  request.getSession().setAttribute("jw",jw);
	      }
      }
      Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
      if(user == null &&  code== null ){
    	  String backurl = this.getRequestUrlWithParams(request);
         // System.out.println(backurl);
    	  String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+jw.getAccountappid()+"&redirect_uri="+backurl+"&response_type=code&scope=snsapi_base&state=snsapi_base#wechat_redirect";
    	  response.sendRedirect(url);
          return false;
      }
      //SESSION涓敤鎴蜂笉涓虹┖锛岄噸鏂板垽鏂竴涓嬫槸鍚﹀凡鏀粯
      //鐢ㄨ涓虹┖锛岄潤榛楥ODE涓嶄负绌�
      if(user == null &&  code!= null ){
         // System.out.println("4");
	      String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
	                + "appid="
	                + jw.getAccountappid()
	                + "&secret="
	                + jw.getAccountappsecret()
	                + "&code=CODE&grant_type=authorization_code";
	      get_access_token_url = get_access_token_url.replace("CODE", code);
	      String json = HttpsGetUtil.doHttpsGetJson(get_access_token_url);
	        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);
	        String openid =null ;
	        String access_token = null;
	        if(state.equals("snsapi_base")){
	           // System.out.println("5");
		        try{
		         	String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		        	 openid = jsonObject.getString("openid");
		        	 access_token = jsonObject.getString("access_token");
			        get_userinfo = get_userinfo.replace("ACCESS_TOKEN", access_token);
			        get_userinfo = get_userinfo.replace("OPENID", openid);
				        String userInfoJson = HttpsGetUtil.doHttpsGetJson(get_userinfo);
				      	System.out.println(userInfoJson);
				        net.sf.json.JSONObject userInfoJO = net.sf.json.JSONObject.fromObject(userInfoJson);
				        	user = new Userinfo();
						        user.setOpenid(userInfoJO.getString("openid"));
						        user.setNickname(userInfoJO.getString("nickname"));
						        user.setSex(userInfoJO.getString("sex"));
						        user.setCity(userInfoJO.getString("city"));
						        user.setProvince(userInfoJO.getString("province"));
						        user.setCountry(userInfoJO.getString("country"));
						        user.setHeadpic(userInfoJO.getString("headimgurl"));
						        Userinfo   userinfo = new Userinfo();  
					        	userinfo = userinfoService.getByOpenId(openid).get(0);
					        	if(null != userinfo){    
							        userinfoService.updateByopenid(user);
							        userinfo.setNickname(userInfoJO.getString("nickname"));
							        userinfo.setSex(userInfoJO.getString("sex"));
							        userinfo.setCity(userInfoJO.getString("city"));
							        userinfo.setProvince(userInfoJO.getString("province"));
							        userinfo.setCountry(userInfoJO.getString("country"));
							        userinfo.setHeadpic(userInfoJO.getString("headimgurl"));
							        request.getSession().setAttribute("OPERATE_ACTIVITY_USER",userinfo);
					        	}   else{
						        	user.setStatus("1");
						        	userinfoService.doAdd(user);
						        	request.getSession().setAttribute("OPERATE_ACTIVITY_USER",user);
						        } 
		        	 
		        }catch(Exception e){
		      	  String backurl = this.getRequestUrlWithParams(request);
		      	backurl = backurl.replace("code", "c");
		      	backurl = backurl.replace(code, "");
	  	      	backurl= backurl.replace("%26state%3Dsnsapi_base", "");
		      	System.out.println(backurl);
		    	  String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+jw.getAccountappid()+"&redirect_uri="+backurl+"&response_type=code&scope=snsapi_userinfo&state=snsapi_userinfo#wechat_redirect";
		    	  response.sendRedirect(url);
		          return false;
		        }
	        }else{
	           // System.out.println("6");
	        	String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	        	 openid = jsonObject.getString("openid");
	        	 access_token = jsonObject.getString("access_token");
		        get_userinfo = get_userinfo.replace("ACCESS_TOKEN", access_token);
		        get_userinfo = get_userinfo.replace("OPENID", openid);
			        String userInfoJson = HttpsGetUtil.doHttpsGetJson(get_userinfo);
			      	System.out.println(userInfoJson);
			        net.sf.json.JSONObject userInfoJO = net.sf.json.JSONObject.fromObject(userInfoJson);
			        	user = new Userinfo();
			        	 
			        	 user.setOpenid(userInfoJO.getString("openid"));
					        user.setNickname(userInfoJO.getString("nickname"));
					        user.setSex(userInfoJO.getString("sex"));
					        user.setCity(userInfoJO.getString("city"));
					        user.setProvince(userInfoJO.getString("province"));
					        user.setCountry(userInfoJO.getString("country"));
					        user.setHeadpic(userInfoJO.getString("headimgurl"));
					        Userinfo   userinfo = new Userinfo();  
				        	userinfo = userinfoService.getByOpenId(openid).get(0);
				        	if(null != userinfo){    
						        userinfoService.updateByopenid(user);
						        userinfo.setNickname(userInfoJO.getString("nickname"));
						        userinfo.setSex(userInfoJO.getString("sex"));
						        userinfo.setCity(userInfoJO.getString("city"));
						        userinfo.setProvince(userInfoJO.getString("province"));
						        userinfo.setCountry(userInfoJO.getString("country"));
						        userinfo.setHeadpic(userInfoJO.getString("headimgurl"));
						        request.getSession().setAttribute("OPERATE_ACTIVITY_USER",userinfo);
				        	}   else{
					        	user.setStatus("1");
					        	userinfoService.doAdd(user);
					        	request.getSession().setAttribute("OPERATE_ACTIVITY_USER",user);
					        } 
	        }
      }
      
      if( user!=null){
    				 String get_userinfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
    			        get_userinfo = get_userinfo.replace("ACCESS_TOKEN", jw.getAccountaccesstoken());
    			        get_userinfo = get_userinfo.replace("OPENID", user.getOpenid());
    			        String userInfoJson = HttpsGetUtil.doHttpsGetJson(get_userinfo);
    			        net.sf.json.JSONObject userInfoJO = net.sf.json.JSONObject.fromObject(userInfoJson);
    			        boolean subscribe = false;
    			        try {
    				        user.setOpenid(userInfoJO.getString("openid"));
    				        subscribe=true;
    			        }catch (Exception e) {
    			        	subscribe=false;
    					}
    			        request.getSession().setAttribute("subscribe",subscribe);
    	  return true;
      }
  
    }
    return true;
  }

  private String getRequestPath(HttpServletRequest request)
  {
    String requestPath = request.getRequestURI();
    requestPath = requestPath.substring(request.getContextPath().length() + 1);
    return requestPath;
  }

	public String getRequestUrlWithParams(HttpServletRequest request) {
		// TODO Auto-generated method stub
	   	  String backurl = request.getScheme()+"://"+request.getServerName()+request.getRequestURI();
	   	  if(request.getQueryString() !=null){
	   		backurl = backurl+"?"+request.getQueryString();
	   	  }
	   	try {
			backurl = URLEncoder.encode(backurl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	  return backurl;
	}
 
}
package com.jeecg.p3.toupiao.web.back;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.cg.util.BaihuaUtil;
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

import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoConfig;
import com.jeecg.p3.toupiao.service.WxActToupiaoConfigService;
import com.jeecg.p3.toupiao.service.WxActToupiaoService;
import com.jeecg.p3.userservice.entity.Userservice;
import com.jeecg.p3.userservice.service.UserserviceService;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>WxActToupiaoController<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/toupiao/back/wxActToupiao")
public class WxActToupiaoController extends BaseController{
  @Autowired
  private WxActToupiaoService wxActToupiaoService;
  
  @Autowired
  private WxActToupiaoConfigService wxActToupiaoConfigService;
  
  @Value("#{sysconfig['toupiaoAppid']}")
  private String toupiaoAppid;
  
  @Autowired
  private UserserviceService userserviceService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActToupiao query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActToupiao> pageQuery = new PageQuery<WxActToupiao>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 	if(!BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
	 		query.setUserid(user.getUserId());
	 	}
	 	pageQuery.setQuery(query);
	 	if(query.getIsDelete() ==null){
	 		query.setIsDelete("0");
	 	}
		velocityContext.put("wxActToupiao",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActToupiaoService.queryPageList(pageQuery)));
		String viewName = "toupiao/back/wxActToupiao-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActToupiaoDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "toupiao/back/wxActToupiao-detail.vm";
		WxActToupiao wxActToupiao = wxActToupiaoService.queryById(id);
		velocityContext.put("wxActToupiao",wxActToupiao);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 Userservice userservice = new Userservice();
	 userservice.setUserservicecol0(this.toupiaoAppid);
	 userservice.setUserid(user.getUserId());
	 List<Userservice> userlist = userserviceService.queryByRecord(userservice);
	 if(userlist.size()<=0){
		 velocityContext.put("isbuyed", false);
	 }
	 String viewName = "toupiao/back/wxActToupiao-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

@RequestMapping(value = "/demo",method ={RequestMethod.GET, RequestMethod.POST})
public void demo(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "toupiao/back/wxActToupiao-demo.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActToupiao wxActToupiao,@ModelAttribute WxActToupiaoConfig wxActToupiaoConfig,HttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		Userservice userservice = new Userservice();
		 userservice.setUserservicecol0(this.toupiaoAppid);
		 userservice.setUserid(user.getUserId());
		 List<Userservice> userlist = userserviceService.queryByRecord(userservice);
		 if(userlist.size()<=0){
				j.setSuccess(false);
				j.setMsg("该项服务付费后方能使用，请在线购买！");
				return j;
		 }
		wxActToupiao.setIsDelete("0");
		wxActToupiao.setIsActive("0");
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		wxActToupiao.setProjectCode("toupiao");
		wxActToupiao.setCreatTime(new Date());

	 	wxActToupiao.setUserid(user.getUserId());
		wxActToupiaoService.doAdd(wxActToupiao);
		wxActToupiaoConfig.setActId(wxActToupiao.getId());
		wxActToupiaoConfigService.doAdd(wxActToupiaoConfig);
		
		 for(int a = 0 ;a<userlist.size();a++){
			 if(userlist.get(a).getCreatecount()>0){
				 userservice = userlist.get(a);
				  userservice.setCreatecount(userservice.getCreatecount()-1);
				  userserviceService.doEdit(userservice);
				  break;
			 }
		 }
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 WxActToupiao wxActToupiao = wxActToupiaoService.queryById(id);
		 velocityContext.put("wxActToupiao",wxActToupiao);
		 WxActToupiaoConfig wxActToupiaoConfig = wxActToupiaoConfigService.getByActId(id);
		 velocityContext.put("wxActToupiaoConfig",wxActToupiaoConfig);
		 String viewName = "toupiao/back/wxActToupiao-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActToupiao wxActToupiao,@ModelAttribute WxActToupiaoConfig wxActToupiaoConfig,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		WxActToupiao wxtp = wxActToupiaoService.queryById(wxActToupiao.getId());
	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
			if(wxtp.getIsActive().equals("1")){
				wxActToupiao.setActName(null);
				wxActToupiao.setBeginTime(null);
				wxActToupiao.setEndEnterTime(null);
				wxActToupiao.setEndTime(null);
			}
			wxActToupiaoService.doEdit(wxActToupiao);
			wxActToupiaoConfig.setActId(wxActToupiao.getId());
			wxActToupiaoConfigService.updateByActId(wxActToupiaoConfig);
			j.setSuccess(true);
			j.setMsg("编辑成功");
		}else{
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id ,HttpServletRequest request    ){
		AjaxJson j = new AjaxJson();
		try {
			WxActToupiao wxtp = wxActToupiaoService.queryById(id);
		 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
			if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
				wxActToupiaoService.doDelete(id);
				wxActToupiaoConfigService.deleteByActId(id);
				j.setSuccess(true);
				j.setMsg("删除成功");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


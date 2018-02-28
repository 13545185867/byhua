package com.jeecg.p3.houseaOnline.web.back;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jeecg.p3.houseaOnline.entity.XfFollow;
import com.jeecg.p3.houseaOnline.entity.XfHouse;
import com.jeecg.p3.houseaOnline.entity.XfHx;
import com.jeecg.p3.houseaOnline.service.XfFollowService;
import com.jeecg.p3.houseaOnline.service.XfHouseService;
import com.jeecg.p3.houseaOnline.service.XfHxService;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.userinfo.entity.Userinfo;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>XfHouseController<br>
 * @author chao.hua
 * @since：2017年11月19日 18时12分05秒 星期日 
 * @version:1.0
 */
@Controller
@RequestMapping("/houseaOnline/back/xfHouse")
public class XfHouseController extends BaseController{
  @Autowired
  private XfHouseService xfHouseService;
  @Autowired
  private XfHxService xfHxService;
  
  @Autowired
  private XfFollowService xfFollowService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute XfHouse query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 XfHx queryhx = new XfHx() ;
	 queryhx.setCreatuser(user.getUserId());
 	query.setCreateuser(user.getUserId());
    if(query.getIsDelete() ==null){
    	query.setIsDelete("0");
    	queryhx.setIsDelete("0");
    }
    //如果是管理员角色可以查看所有人发的
    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
    	query.setCreateuser(null);
    	 queryhx.setCreatuser(null);
    }
	 	String pid = request.getParameter("pid");
	 	String buildingid = request.getParameter("buildingid");
	 	String cellid = request.getParameter("cellid");
	 	VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("pid",pid);
		velocityContext.put("buildingid",buildingid);
		velocityContext.put("cellid",cellid);
		queryhx.setPid(pid);
	 	velocityContext.put("pagehxInfos", xfHxService.queryAllByPid(queryhx) );
	 	PageQuery<XfHouse> pageQuery = new PageQuery<XfHouse>();
	 	query.setCellid(cellid);
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
		pageQuery.setQuery(query);
		velocityContext.put("xfHouse",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(xfHouseService.queryPageList(pageQuery)));
		String viewName = "houseaOnline/back/xfHouse-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void xfHouseDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "houseaOnline/back/house_detail.vm";
	XfHouse xfhouse = new XfHouse();
	xfhouse.setId(id);
	  xfhouse = xfHouseService.getAllbyId(xfhouse);
	  if(xfhouse.getViewcount() == null){
		  xfhouse.setViewcount(1);
	  }else{
		  xfhouse.setViewcount(xfhouse.getViewcount()+1);
	  }
	 
	  PageQuery<XfFollow> pageQuery = new PageQuery<XfFollow>();
	  XfFollow xffollow = new XfFollow();
	  xffollow.setResourceid(id);
	  pageQuery.setQuery(xffollow);
	  int followcount = xfFollowService.count(pageQuery);
	  
	  velocityContext.put("followcount",followcount);
	velocityContext.put("isfollow",true);
	velocityContext.put("xfhouse",xfhouse);
	velocityContext.put("vipuser",false);
	velocityContext.put("serverdate",new Date().getTime());
	velocityContext.put("kaipandate",xfhouse.getKaipanDate().getTime());
	velocityContext.put("viewcount",xfhouse.getViewcount());
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
	 	 XfHx queryhx = new XfHx() ;
	 	queryhx.setCreatuser(user.getUserId());
	    if(null ==queryhx.getIsDelete()){
	    	queryhx.setIsDelete("0");
	    }
	    //如果是管理员角色可以查看所有人发的
	    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
	    	queryhx.setCreatuser(null);
	    }
		 	String pid = request.getParameter("pid");
		 	String buildingid = request.getParameter("buildingid");
		 	String cellid = request.getParameter("cellid");
		 	queryhx.setPid(pid);
			velocityContext.put("pid",pid);
			velocityContext.put("buildingid",buildingid);
			velocityContext.put("cellid",cellid);
		 	velocityContext.put("pagehxInfos", xfHxService.queryAllByPid(queryhx) );
	 String viewName = "houseaOnline/back/xfHouse-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute XfHouse xfHouse,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		xfHouse.setCreateuser(((LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER")).getUserId());
		xfHouse.setIsDelete("0");;
		xfHouse.setCreatetime(new Date());
		xfHouseService.doAdd(xfHouse);
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
		 XfHouse xfHouse = xfHouseService.queryById(id);
		 velocityContext.put("xfHouse",xfHouse);
		 String viewName = "houseaOnline/back/xfHouse-edit.vm";
		 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 	 XfHx queryhx = new XfHx() ;
	    if(null ==queryhx.getIsDelete()){
	    	queryhx.setIsDelete("0");
	    }
		 	queryhx.setPid(xfHouse.getPid());
		 	velocityContext.put("pagehxInfos", xfHxService.queryAllByPid(queryhx) );
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

@RequestMapping(value="toGenerate",method = RequestMethod.GET)
public void toGenerate(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 XfHouse xfHouse = new XfHouse();
		 xfHouse.setId(id);
		 xfHouse =  xfHouseService.getAllbyId(xfHouse);
		 velocityContext.put("xfHouse",xfHouse);
		 String viewName = "houseaOnline/back/xfHouse-generate.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute XfHouse xfHouse ,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    xfHouse.setCreateuser(user.getUserId());
		xfHouseService.doEdit(xfHouse);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}


/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doGenerate",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doGenerate(@ModelAttribute XfHouse xfHouse,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		Integer startfloor = Integer.parseInt(request.getParameter("startfloor"))  ;
		Integer endfloor = Integer.parseInt(request.getParameter("endfloor")) ;
		String  plusprice0 =   request.getParameter("plusprice") ;
		BigDecimal plusprice =  new BigDecimal(plusprice0);
		String price =   xfHouse.getPrice() ;
		String name = xfHouse.getName();
		BigDecimal minprice = new BigDecimal(price);
		String  priceUnit =  request.getParameter("priceUnit") ;
		
		for( int i = startfloor; i<=endfloor;i++){
			xfHouse.setName(i+name);
			xfHouse.setPrice(minprice.add(plusprice.multiply(new BigDecimal(i-startfloor)))+priceUnit);
			xfHouse.setFloor(i);
			xfHouse.setId(null);
			xfHouse.setCreateuser(((LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER")).getUserId());
			xfHouse.setIsDelete("0");;
			xfHouse.setCreatetime(new Date());
			xfHouseService.doAdd(xfHouse); 
		}
		j.setMsg("数据生成成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("数据生成失败");
	}
	return j;
}

/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			xfHouseService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


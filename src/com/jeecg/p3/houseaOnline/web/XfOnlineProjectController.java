package com.jeecg.p3.houseaOnline.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.jeecg.p3.houseaOnline.entity.XfBuilding;
import com.jeecg.p3.houseaOnline.entity.XfCell;
import com.jeecg.p3.houseaOnline.entity.XfFollow;
import com.jeecg.p3.houseaOnline.entity.XfHouse;
import com.jeecg.p3.houseaOnline.entity.XfHx;
import com.jeecg.p3.houseaOnline.entity.XfOrder;
import com.jeecg.p3.houseaOnline.entity.XfProject;
import com.jeecg.p3.houseaOnline.service.XfBuildingService;
import com.jeecg.p3.houseaOnline.service.XfCellService;
import com.jeecg.p3.houseaOnline.service.XfFollowService;
import com.jeecg.p3.houseaOnline.service.XfHouseService;
import com.jeecg.p3.houseaOnline.service.XfHxService;
import com.jeecg.p3.houseaOnline.service.XfOrderService;
import com.jeecg.p3.houseaOnline.service.XfProjectService;
import com.jeecg.p3.order.entity.Order;
import com.jeecg.p3.serviceprice.entity.Serviceprice;
import com.jeecg.p3.userinfo.entity.Userinfo;
import com.jeecg.p3.viewcount.service.ViewcountService;
import com.weixin.pay.WXPay;
import com.weixin.pay.WXPayConfig;
import com.weixin.pay.WXPayUtil;

import org.jeecgframework.p3.core.web.BaseController;

import weixin.util.DateUtils;

 /**
 * 描述：</b>XfProjectController<br>在线选房，楼盘项目表
 * @author chao.hua
 * @since：2017年11月17日 12时55分27秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/houseaOnline")
public class XfOnlineProjectController extends BaseController{
  @Autowired
  private XfProjectService xfProjectService;
  
  @Autowired
  private XfBuildingService xfBuildingService;
  
  @Autowired
  private XfCellService xfCellService;
  
  @Autowired
  private XfHouseService xfHouseService;
  
  @Autowired
  private XfHxService xfHxService;
  
  @Autowired
  private XfOrderService xfOrderService;
  
  @Value("#{sysconfig['wxpay.houseNotify.url']}")
  private String notifyurl;
  
  @Value("#{sysconfig['spbill_create_ip']}")
  private String billcreateip;
  
  @Value("#{sysconfig['appid']}")
  private String appid;
  
  @Value("#{sysconfig['shanghu.key']}")
  private String shanghukey;
  
  @Autowired
  private ViewcountService viewcountService;
  
  @Autowired
  private XfFollowService xfFollowService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="index",method = {RequestMethod.GET,RequestMethod.POST})
public void index(@ModelAttribute XfProject query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<XfProject> pageQuery = new PageQuery<XfProject>();
		String areaid = request.getParameter("areaid");
		if(areaid != null && !"".equals(areaid)){
			query.setAreaid(Integer.parseInt(areaid));
		}
		query.setIsDelete("0");
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("xfProject",query);
		velocityContext.put("areaid",areaid);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(xfProjectService.queryPageList(pageQuery)));
		String viewName = "houseaOnline/back/project.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="projectDetail",method = RequestMethod.GET)
public void projectDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "houseaOnline/back/project_detail.vm";
		XfProject xfProject = xfProjectService.queryById(id);
		if(xfProject.getXfProjectcol1() == null){
			xfProject.setXfProjectcol1(1);
		}else{
			xfProject.setXfProjectcol1(xfProject.getXfProjectcol1()+1);
		}
		
	 	 XfHx queryhx = new XfHx() ;
		    if(null ==queryhx.getIsDelete()){
		    	queryhx.setIsDelete("0");
		    }
		    
			  PageQuery<XfFollow> pageQuery = new PageQuery<XfFollow>();
			  XfFollow xffollow = new XfFollow();
			  xffollow.setResourceid(id);
			  pageQuery.setQuery(xffollow);
			  int followcount = xfFollowService.count(pageQuery);
			  velocityContext.put("followcount",followcount);
			  xffollow.setFollowuser(((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());
			  pageQuery.setQuery(xffollow);
			  boolean isfollow = false;
			  if(xfFollowService.count(pageQuery) >0){
				  isfollow = true;
			  }
			velocityContext.put("isfollow",isfollow);
		 queryhx.setPid(id);
		 velocityContext.put("pagehxInfos", net.sf.json.JSONArray.fromObject(xfHxService.queryAllByPid(queryhx)).toString() );
		velocityContext.put("xfProject",xfProject);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "houseaOnline/back/xfProject-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute XfProject xfProject){
	AjaxJson j = new AjaxJson();
	try {
		xfProjectService.doAdd(xfProject);
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
		 XfProject xfProject = xfProjectService.queryById(id);
		 velocityContext.put("xfProject",xfProject);
		 String viewName = "houseaOnline/back/xfProject-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute XfProject xfProject){
	AjaxJson j = new AjaxJson();
	try {
		xfProjectService.doEdit(xfProject);
		j.setMsg("编辑成功");
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			xfProjectService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

@RequestMapping(value="getHouseByCB",method = RequestMethod.GET)
@ResponseBody
public AjaxJson getHouseByCB( HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			String bid =request.getParameter("bid");
			String cid =request.getParameter("cid");
			String type = request.getParameter("b");
			if(type !=null && type.equals("1")){
				XfHouse xfhouse = new XfHouse();
				xfhouse.setBuildingid(bid);
				xfhouse.setCellid(cid);
				List<XfHouse> hlist = xfHouseService.queryByCondit(xfhouse);
				j.setObj(hlist);
				j.setMsg("获取数据成功");
			}else{
				
				XfCell xfcell = new XfCell();
				xfcell.setBuildingid(bid);
				List<XfCell> clist = xfCellService.queryByCondit(xfcell);
				XfHouse xfhouse = new XfHouse();
				xfhouse.setBuildingid(bid);
				xfhouse.setCellid(clist.get(0).getId());
				List<XfHouse> hlist = xfHouseService.queryByCondit(xfhouse);
				LinkedHashMap result = new LinkedHashMap();
				result.put("cellInfos", clist);
				result.put("houseInfos", hlist);
				result.put("curcell", clist.get(0).getId());
				j.setObj(result);
				j.setMsg("获取数据成功");
			}
			

		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

@RequestMapping(value="doFollow",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doFollow( HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			String resourceid =request.getParameter("resourceid");
			String type =request.getParameter("type");
			String optype = request.getParameter("optype");
			String resourcename= request.getParameter("resourcename");
			if(null !=optype &&  optype.equals("1")){
				XfFollow xffollow = new XfFollow();
				xffollow.setResourceid(resourceid);
				xffollow.setFollowuser(((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());
				xfFollowService.deleteByridAndUser(xffollow);
			}else{
				XfFollow xffollow = new XfFollow();
				if(null !=type &&  type.equals("0")){
					xffollow.setResourceurl("/houseaOnline/projectDetail.html?id="+resourceid);
				}else{
					xffollow.setResourceurl("/houseaOnline/houseDetail.html?id="+resourceid);
				}
				xffollow.setType(type);
				xffollow.setFollowdate(new Date());
				xffollow.setFollowuser(((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());
				xffollow.setResourceid(resourceid);
				xffollow.setResourcename(resourcename);
				xfFollowService.doAdd(xffollow);
			}
			j.setSuccess(true);
			j.setMsg("操作成功");
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("操作失败");
		}
		return j;
}


@RequestMapping(value="houseIndex",method = {RequestMethod.GET,RequestMethod.POST})
public void houseIndex(@ModelAttribute XfHouse query,HttpServletResponse response,HttpServletRequest request ) throws Exception{
 	VelocityContext velocityContext = new VelocityContext();
	String pid = request.getParameter("pid");
	XfProject xfproject = xfProjectService.queryById(pid);

	net.sf.json.JSONObject xfprojectjo = net.sf.json.JSONObject.fromObject(xfproject);
	velocityContext.put("projectInfos",xfprojectjo.toString());
	//getbuilding
	XfBuilding xfbuild = new XfBuilding();
	xfbuild.setPid(pid);
	List<XfBuilding> blist =null;
	try{
	blist = xfBuildingService.queryByCondit(xfbuild);
	}catch(Exception e){
		e.printStackTrace();
	}
	velocityContext.put("buildInfos",net.sf.json.JSONArray.fromObject(blist).toString());
	String buildid = null;
	if(blist.size() > 0){
		buildid= blist.get(0).getId();
	}else{
		buildid = "";
	}

	String cellid = null;	
	//getcell
	if(buildid.equals("")){
		cellid = "";
		velocityContext.put("cellInfos","[]");
	}else{
		XfCell xfcell = new XfCell();
		xfcell.setBuildingid(buildid);
		List<XfCell> clist = xfCellService.queryByCondit(xfcell);
		velocityContext.put("cellInfos",net.sf.json.JSONArray.fromObject(clist).toString());
		velocityContext.put("curbuild",buildid);

		if(clist.size() > 0){
			cellid= clist.get(0).getId();
		}else{
			cellid = "";
		}
	}
	
	if(cellid.equals("")){
		velocityContext.put("houseInfos","[]");
	}else{
		//默认取 第一栋楼 第一单元
		XfHouse xfhouse = new XfHouse();
		xfhouse.setBuildingid(buildid);
		xfhouse.setCellid(cellid);
		List<XfHouse> hlist = xfHouseService.queryByCondit(xfhouse);
		velocityContext.put("houseInfos",net.sf.json.JSONArray.fromObject(hlist).toString());
	}

	velocityContext.put("curcell",cellid);
	
		String viewName = "houseaOnline/back/house.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

@RequestMapping(value="houseDetail",method = RequestMethod.GET)
public void houseDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
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
		  xffollow.setFollowuser(((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());
		  pageQuery.setQuery(xffollow);
		  boolean isfollow = false;
		  if(xfFollowService.count(pageQuery) >0){
			  isfollow = true;
		  }
		  
		velocityContext.put("isfollow",isfollow);
		velocityContext.put("xfhouse",xfhouse);
		velocityContext.put("serverdate",new Date().getTime());
		velocityContext.put("kaipandate",xfhouse.getKaipanDate().getTime());
		velocityContext.put("viewcount",xfhouse.getViewcount());
		ViewVelocity.view(request,response,viewName,velocityContext);
}

@RequestMapping(value = "/doPay",method ={RequestMethod.GET, RequestMethod.POST})
public void doPay(HttpServletRequest request,HttpServletResponse response) throws Exception  {
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "jlb/error.vm";
	String id = request.getParameter("id");
	XfHouse xfhouse = new XfHouse();
	XfOrder order = new XfOrder();
	xfhouse.setId(id);
	xfhouse.setIsDelete("0");
	  xfhouse = xfHouseService.getAllbyId(xfhouse);
	  if(xfhouse.getKaipanDate().getTime()>new Date().getTime()){
			velocityContext.put("error", "还未开盘！");
			ViewVelocity.view(request,response,viewName,velocityContext);
	  }
	  if(!(xfhouse.getStatus().equals("0") || xfhouse.getStatus().equals("3") || request.getSession().getAttribute("OPERATE_ACTIVITY_USER") ==null || (boolean)request.getSession().getAttribute("vipuser") != true) ){
			order.setCreatetime(new Date());
			order.setCreateuser(((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());
			order.setExpiretime(DateUtils.getDate(((new Date().getTime())+1000*3600*2)));
			order.setIsDelete("0");
			order.setPaycodeurl("");
			order.setPayresult("");
			order.setStatus("0");
			order.setPaytime(new Date());
			order.setPayuserid("");
			order.setHouseprice(xfhouse.getOrderprice());
			order.setHouseid(xfhouse.getId());
			order.setXfOrdercol(xfhouse.getProjectname()+xfhouse.getBuildingname()+"栋"+xfhouse.getName()+"订金");
			order.setExpiretime(DateUtils.getDate(((new Date().getTime())+1000*3600*2)));
			xfOrderService.doAdd(order);
			viewName = "order/back/order-yf.vm";
			WXPayConfig wxpc= new WXPayConfig();
			try{
				WXPay wxp = new WXPay(wxpc, notifyurl, false, true);
				// 获取发起电脑 ip  
		        String spbill_create_ip = billcreateip;  
		        // 回调接口   
		        String trade_type = "JSAPI";  
		        String nonce_str = WXPayUtil.generateUUID();
		        Map<String,String> packageParams = new TreeMap<String,String>(); 
		        packageParams.put("body", xfhouse.getProjectname()+xfhouse.getBuildingname()+"栋"+xfhouse.getName()+"订金");  
		        packageParams.put("openid", ((Userinfo)request.getSession().getAttribute("OPERATE_ACTIVITY_USER")).getOpenid());  
		        packageParams.put("out_trade_no", order.getId());  
		        packageParams.put("total_fee",  (order.getHouseprice().multiply(new BigDecimal(100))).toString().split("\\.")[0]);  
		        packageParams.put("spbill_create_ip", spbill_create_ip);  
		        packageParams.put("trade_type", trade_type);
		        packageParams.put("nonce_str", nonce_str); 
		        //Map<String,String> requestMap =wxp.fillRequestData(packageParams);
		        Map<String,String> responseMap = wxp.unifiedOrder(packageParams); 
		        if(responseMap.get("result_code").equals("SUCCESS")){
		    		velocityContext.put("prepay_id",responseMap.get("prepay_id"));
		        }

				
				xfhouse.setStatus("2");
				xfHouseService.doEdit(xfhouse);
				
			    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
		        Map<String, String> payMap = new HashMap<String, String>();  
		        payMap.put("appId", appid);  
		        payMap.put("timeStamp", timestamp);  
		        payMap.put("nonceStr", nonce_str);  
		        payMap.put("signType", "MD5");  
		        payMap.put("package", "prepay_id=" + responseMap.get("prepay_id")); 
		        String paySign = WXPayUtil.generateSignature(payMap, shanghukey,SignType.MD5 ) ;
				velocityContext.put("prepay_id",responseMap.get("prepay_id"));
				velocityContext.put("appId",appid);
				velocityContext.put("price",order.getHouseprice());
				
				velocityContext.put("timeStamp",timestamp);
				velocityContext.put("paySign",paySign);
				velocityContext.put("nonceStr",nonce_str);
				velocityContext.put("body", xfhouse.getProjectname()+xfhouse.getBuildingname()+"栋"+xfhouse.getName()+"订金");
				velocityContext.put("signType", "MD5");
				velocityContext.put("out_trade_no", order.getId().toString());
				ViewVelocity.view(request,response,viewName,velocityContext);

			}catch (Exception e){
				
			}
	  }
		velocityContext.put("error", "出错啦");
		ViewVelocity.view(request,response,viewName,velocityContext);

}

@RequestMapping(value = "/error",method ={RequestMethod.GET, RequestMethod.POST})
public void error(HttpServletRequest request,@ModelAttribute XfOrder order,HttpServletResponse response) throws Exception  {
	String viewName = "jlb/error.vm";
	VelocityContext velocityContext = new VelocityContext();
	velocityContext.put("error", "出错啦");
	ViewVelocity.view(request,response,viewName,velocityContext);
}

}


package com.jeecg.p3.houseaOnline.web.back;

import java.util.Date;
import java.util.List;

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

import com.jeecg.p3.houseaOnline.entity.XfCell;
import com.jeecg.p3.houseaOnline.service.XfCellService;
import com.jeecg.p3.system.vo.LoginUser;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>XfCellController<br>在线选房,单元表
 * @author chao.hua
 * @since：2017年11月19日 16时52分42秒 星期日 
 * @version:1.0
 */
@Controller
@RequestMapping("/houseaOnline/back/xfCell")
public class XfCellController extends BaseController{
  @Autowired
  private XfCellService xfCellService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute XfCell query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	String buildingid = request.getParameter("buildingid");
	String pid = request.getParameter("pid");
	 	PageQuery<XfCell> pageQuery = new PageQuery<XfCell>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    query.setCreateuser(user.getUserId());
	    if(query.getIsDelete() ==null){
	    	query.setIsDelete("0");
	    }
	    //如果是管理员角色可以查看所有人发的
	    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
	    	query.setCreateuser(null);
	    }
	    query.setBuildingid(buildingid);
		pageQuery.setQuery(query);
		velocityContext.put("xfCell",query);
		velocityContext.put("buildingid",buildingid);
		velocityContext.put("pid",pid);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(xfCellService.queryPageList(pageQuery)));
		String viewName = "houseaOnline/back/xfCell-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void xfCellDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "houseaOnline/back/xfCell-detail.vm";
		XfCell xfCell = xfCellService.queryById(id);
		velocityContext.put("xfCell",xfCell);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
		String buildingid = request.getParameter("buildingid");
		velocityContext.put("buildingid",buildingid);
	 String viewName = "houseaOnline/back/xfCell-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(HttpServletRequest request,@ModelAttribute XfCell xfCell){
	AjaxJson j = new AjaxJson();
	try {
		xfCell.setCreateuser(((LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER")).getUserId());
		xfCell.setIsDelete("0");;
		xfCell.setCreatetime(new Date());
		xfCellService.doAdd(xfCell);
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
		 XfCell xfCell = xfCellService.queryById(id);
		 velocityContext.put("xfCell",xfCell);
		 String viewName = "houseaOnline/back/xfCell-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute XfCell xfCell,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    xfCell.setCreateuser(user.getUserId());
		xfCellService.doEdit(xfCell);
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
			xfCellService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


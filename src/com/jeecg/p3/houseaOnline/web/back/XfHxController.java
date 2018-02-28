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

import com.jeecg.p3.houseaOnline.entity.XfHx;
import com.jeecg.p3.houseaOnline.service.XfHxService;
import com.jeecg.p3.system.vo.LoginUser;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>XfHxController<br>在线选房,户型表
 * @author chao.hua
 * @since：2017年11月19日 14时17分34秒 星期日 
 * @version:1.0
 */
@Controller
@RequestMapping("/houseaOnline/back/xfHx")
public class XfHxController extends BaseController{
  @Autowired
  private XfHxService xfHxService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute XfHx query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
    query.setCreatuser(user.getUserId());;
    if(query.getIsDelete() ==null){
    	query.setIsDelete("0");
    }
    //如果是管理员角色可以查看所有人发的
    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
    	query.setCreatuser(null);
    }
	 	PageQuery<XfHx> pageQuery = new PageQuery<XfHx>();
	 	String pid = request.getParameter("pid");
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	query.setPid(pid);
		pageQuery.setQuery(query);
		velocityContext.put("xfHx",query);
		velocityContext.put("pid",pid);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(xfHxService.queryPageList(pageQuery)));
		String viewName = "houseaOnline/back/xfHx-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void xfHxDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "houseaOnline/back/xfHx-detail.vm";
		XfHx xfHx = xfHxService.queryById(id);
		velocityContext.put("xfHx",xfHx);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 	String pid = request.getParameter("pid");
		velocityContext.put("pid",pid);
	 String viewName = "houseaOnline/back/xfHx-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute XfHx xfHx,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		xfHx.setCreatuser(((LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER")).getUserId());
		xfHx.setIsDelete("0");;
		xfHx.setCreatetime(new Date());
		xfHxService.doAdd(xfHx);
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
		 XfHx xfHx = xfHxService.queryById(id);
		 velocityContext.put("xfHx",xfHx);
		 String viewName = "houseaOnline/back/xfHx-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute XfHx xfHx,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    xfHx.setCreatuser(user.getUserId());
		xfHxService.doEdit(xfHx);
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
			xfHxService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


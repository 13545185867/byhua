package com.jeecg.p3.bigwheel.web.back;

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

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelAward;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelAwardService;
import com.jeecg.p3.system.vo.LoginUser;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>ActivityBigwheelAwardController<br>
 * @author chao.hua
 * @since：2017年08月02日 16时21分57秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/bigwheel/back/activityBigwheelAward")
public class ActivityBigwheelAwardController extends BaseController{
  @Autowired
  private ActivityBigwheelAwardService activityBigwheelAwardService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute ActivityBigwheelAward query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<ActivityBigwheelAward> pageQuery = new PageQuery<ActivityBigwheelAward>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("activityBigwheelAward",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(activityBigwheelAwardService.queryPageList(pageQuery)));
		String viewName = "bigwheel/back/activityBigwheelAward-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void activityBigwheelAwardDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "bigwheel/back/activityBigwheelAward-detail.vm";
		ActivityBigwheelAward activityBigwheelAward = activityBigwheelAwardService.queryById(id);
		velocityContext.put("activityBigwheelAward",activityBigwheelAward);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "bigwheel/back/activityBigwheelAward-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute ActivityBigwheelAward activityBigwheelAward,HttpServletRequest request){
	 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		AjaxJson j = new AjaxJson();
     if (user == null || !BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))) {
 		j.setSuccess(false);
 		j.setMsg("保存失败");
 		return j;
     }
	try {
		activityBigwheelAwardService.doAdd(activityBigwheelAward);
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
		 ActivityBigwheelAward activityBigwheelAward = activityBigwheelAwardService.queryById(id);
		 velocityContext.put("activityBigwheelAward",activityBigwheelAward);
		 String viewName = "bigwheel/back/activityBigwheelAward-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute ActivityBigwheelAward activityBigwheelAward,HttpServletRequest request){
	 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		AjaxJson j = new AjaxJson();
    if (user == null || !BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
		return j;
    }
	try {
		activityBigwheelAwardService.doEdit(activityBigwheelAward);
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) Integer id,HttpServletRequest request){
	 LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		AjaxJson j = new AjaxJson();
   if (user == null || !BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))) {
		j.setSuccess(false);
		j.setMsg("删除失败");
		return j;
   }
		try {
			activityBigwheelAwardService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


package com.jeecg.p3.aboutActivity.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jeecg.p3.aboutActivity.entity.ActivityConfig;
import com.jeecg.p3.aboutActivity.service.ActivityConfigService;
import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>ActivityConfigController<br>
 * @author chao.hua
 * @since：2017年08月02日 16时27分56秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/activity/back/activityConfig")
public class ActivityConfigController extends BaseController{
  @Autowired
  private ActivityConfigService activityConfigService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute ActivityConfig query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<ActivityConfig> pageQuery = new PageQuery<ActivityConfig>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("activityConfig",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(activityConfigService.queryPageList(pageQuery)));
		String viewName = "activity/back/activityConfig-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void activityConfigDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "activity/back/activityConfig-detail.vm";
		ActivityConfig activityConfig = activityConfigService.queryById(id);
		velocityContext.put("activityConfig",activityConfig);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "activity/back/activityConfig-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute ActivityConfig activityConfig){
	AjaxJson j = new AjaxJson();
	try {
		activityConfigService.doAdd(activityConfig);
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
		 ActivityConfig activityConfig = activityConfigService.queryById(id);
		 velocityContext.put("activityConfig",activityConfig);
		 String viewName = "activity/back/activityConfig-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute ActivityConfig activityConfig){
	AjaxJson j = new AjaxJson();
	try {
		activityConfigService.doEdit(activityConfig);
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) Integer id){
		AjaxJson j = new AjaxJson();
		try {
			activityConfigService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


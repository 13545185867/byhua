package com.jeecg.p3.bigwheel.web.back;

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
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelRecordService;
import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>ActivityBigwheelRecordController<br>
 * @author chao.hua
 * @since：2017年08月02日 16时18分28秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/bigwheel/back/activityBigwheelRecord")
public class ActivityBigwheelRecordController extends BaseController{
  @Autowired
  private ActivityBigwheelRecordService activityBigwheelRecordService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute ActivityBigwheelRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<ActivityBigwheelRecord> pageQuery = new PageQuery<ActivityBigwheelRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("activityBigwheelRecord",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(activityBigwheelRecordService.queryPageList(pageQuery)));
		String viewName = "bigwheel/back/activityBigwheelRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void activityBigwheelRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "bigwheel/back/activityBigwheelRecord-detail.vm";
		ActivityBigwheelRecord activityBigwheelRecord = activityBigwheelRecordService.queryById(id);
		velocityContext.put("activityBigwheelRecord",activityBigwheelRecord);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "bigwheel/back/activityBigwheelRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute ActivityBigwheelRecord activityBigwheelRecord){
	AjaxJson j = new AjaxJson();
	try {
		activityBigwheelRecordService.doAdd(activityBigwheelRecord);
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
		 ActivityBigwheelRecord activityBigwheelRecord = activityBigwheelRecordService.queryById(id);
		 velocityContext.put("activityBigwheelRecord",activityBigwheelRecord);
		 String viewName = "bigwheel/back/activityBigwheelRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute ActivityBigwheelRecord activityBigwheelRecord){
	AjaxJson j = new AjaxJson();
	try {
		activityBigwheelRecordService.doEdit(activityBigwheelRecord);
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
			activityBigwheelRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


package com.jeecg.p3.aboutActivity.web.back;

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

import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;
import com.jeecg.p3.aboutActivity.entity.AwardApproveUser;
import com.jeecg.p3.aboutActivity.service.AwardApproveUserService;
import com.jeecg.p3.bigwheel.entity.ActivityBigwheel;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelService;
import com.jeecg.p3.system.vo.LoginUser;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>AwardApproveUserController<br>
 * @author chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/activity/back/awardApproveUser")
public class AwardApproveUserController extends BaseController{
  @Autowired
  private AwardApproveUserService awardApproveUserService;
  
  @Autowired
  private ActivityBigwheelService activityBigwheelService;
  
/**
  * 列表页面
  * @return
  */

@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute AwardApproveUser query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
    String actid = (String)request.getParameter("activityid");
	 ActivityBigwheel ab =  activityBigwheelService.queryById(request.getParameter("activityid"));
    //如果是管理员角色可以查看所有人发的
    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
    	query.setOpenid(null);
    }else{
    	if(ab !=null && user.getUserId().equals(ab.getUserid())){
        	query.setOpenid(ab.getUserid());
    	}

    }
    query.setActivityid(actid);
	 	PageQuery<AwardApproveUser> pageQuery = new PageQuery<AwardApproveUser>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("awardApproveUser",query);
		velocityContext.put("activityid",actid);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(awardApproveUserService.queryPageList(pageQuery)));
		String viewName = "activity/back/awardApproveUser-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}
 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void awardApproveUserDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "activity/back/awardApproveUser-detail.vm";
		AwardApproveUser awardApproveUser = awardApproveUserService.queryById(id);
		velocityContext.put("awardApproveUser",awardApproveUser);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "activity/back/awardApproveUser-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 * @throws Exception 
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void doAdd(@ModelAttribute AwardApproveUser awardApproveUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
	String actid = request.getParameter("activityid"); 
	 VelocityContext velocityContext = new VelocityContext();
	 ActivityWxuser user = (ActivityWxuser) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 String viewName = null;
	 boolean result = false;
	if(user != null && actid != null && !actid.equals("")){
		awardApproveUser.setActivityid(actid);
		awardApproveUser.setHeadpic(user.getHeadpic());
		awardApproveUser.setNickname(user.getNickname());
		awardApproveUser.setOpenid(user.getOpenid());
		awardApproveUserService.doAdd(awardApproveUser);
		viewName = "operation_result.vm";
		result = true;
	}
	velocityContext.put("result",result);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 AwardApproveUser awardApproveUser = awardApproveUserService.queryById(id);
		 velocityContext.put("awardApproveUser",awardApproveUser);
		 String viewName = "activity/back/awardApproveUser-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute AwardApproveUser awardApproveUser){
	AjaxJson j = new AjaxJson();
	try {
		awardApproveUserService.doEdit(awardApproveUser);
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
			awardApproveUserService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


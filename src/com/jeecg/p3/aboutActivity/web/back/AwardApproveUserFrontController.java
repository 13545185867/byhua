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
import com.jeecg.p3.bigwheel.entity.ActivityBigwheelRecord;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelRecordService;
import com.jeecg.p3.bigwheel.service.ActivityBigwheelService;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.userinfo.entity.Userinfo;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>AwardApproveUserController<br>
 * @author chao.hua
 * @since：2017年10月27日 16时06分46秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/activity/awardApproveUser")
public class AwardApproveUserFrontController extends BaseController{
  @Autowired
  private AwardApproveUserService awardApproveUserService;
  
  @Autowired
  private ActivityBigwheelService activityBigwheelService;
  
  @Autowired
  private ActivityBigwheelRecordService activityBigwheelRecordService;
/**
 * 保存信息
 * @return
 * @throws Exception 
 */
@RequestMapping(value = "/doAddApprover",method ={RequestMethod.GET, RequestMethod.POST})
public void doAdd( HttpServletRequest request,HttpServletResponse response) throws Exception{
	String actid = request.getParameter("activityid"); 
	 VelocityContext velocityContext = new VelocityContext();
	 Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 String viewName = "operation_result.vm";
	 boolean result = false;
	 String tip ="设置奖品核销人失败！";
	 AwardApproveUser query = new AwardApproveUser();
 
	if(user != null && actid != null && !actid.equals("")){
	    query.setActivityid(actid);
	    query.setOpenid(user.getOpenid());
		 	PageQuery<AwardApproveUser> pageQuery = new PageQuery<AwardApproveUser>();
		 	pageQuery.setPageNo(0);
		 	pageQuery.setPageSize(10);
			pageQuery.setQuery(query);
			if(awardApproveUserService.count(pageQuery)<1){
				query.setActivityid(actid);
				query.setHeadpic(user.getHeadpic());
				query.setNickname(user.getNickname());
				query.setOpenid(user.getOpenid());
				awardApproveUserService.doAdd(query);
				result = true;
				tip="您已被设置成奖品核销员";
			}
	}
	velocityContext.put("result",result);
	velocityContext.put("tip",tip);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

@RequestMapping(value="approve",method = {RequestMethod.GET,RequestMethod.POST})
public void approve(HttpServletResponse response,HttpServletRequest request) throws Exception{
	 String actid = request.getParameter("activityid");
	 String recordid = request.getParameter("recordid");
	 String viewName = "operation_result.vm";
	 boolean result = false;
	 String tip ="奖品核销失败！";
	 VelocityContext velocityContext = new VelocityContext();
	 Userinfo user = (Userinfo) request.getSession().getAttribute("OPERATE_ACTIVITY_USER");
	 AwardApproveUser query = new AwardApproveUser();
		if(user != null && actid != null && !actid.equals("")){
		    query.setActivityid(actid);
		    query.setOpenid(user.getOpenid());
			 	PageQuery<AwardApproveUser> pageQuery = new PageQuery<AwardApproveUser>();
			 	pageQuery.setPageNo(0);
			 	pageQuery.setPageSize(10);
				pageQuery.setQuery(query);
				if(awardApproveUserService.count(pageQuery)<1){
					result = false;
				}else{
					ActivityBigwheelRecord abr0 = activityBigwheelRecordService.queryById(recordid);
					if( abr0 != null && abr0.getStatus().equals("1") ){
						ActivityBigwheelRecord abr = new ActivityBigwheelRecord();
						abr.setId(Integer.parseInt(recordid));
						abr.setStatus("3");
						activityBigwheelRecordService.doEdit(abr);
						result = true;
						tip = "奖品核销成功";
					}else{
						result = false;
					}

				}
		}

	 velocityContext.put("result",result);
	 velocityContext.put("tip",tip);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}
 
}


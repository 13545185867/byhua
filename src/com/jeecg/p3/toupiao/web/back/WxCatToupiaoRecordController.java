package com.jeecg.p3.toupiao.web.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.cg.util.BaihuaUtil;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;

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
import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;
import com.jeecg.p3.toupiao.service.WxActToupiaoService;
import com.jeecg.p3.toupiao.service.WxCatToupiaoRecordService;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>WxCatToupiaoRecordController<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/toupiao/back/wxCatToupiaoRecord")
public class WxCatToupiaoRecordController extends BaseController{
  @Autowired
  private WxCatToupiaoRecordService wxCatToupiaoRecordService;
  
  @Autowired
  private WxActToupiaoService wxActToupiaoService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxCatToupiaoRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxCatToupiaoRecord> pageQuery = new PageQuery<WxCatToupiaoRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		if(query.getActId() !=null && query.getActId()!=""){
	 		WxActToupiao wxtp = wxActToupiaoService.queryById(query.getActId());
	 	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 		if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
				pageQuery.setQuery(query);
				velocityContext.put("wxCatToupiaoRecord",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxCatToupiaoRecordService.queryPageList(pageQuery)));
	 		}
		}
	 		String viewName = "toupiao/back/wxCatToupiaoRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxCatToupiaoRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "toupiao/back/wxCatToupiaoRecord-detail.vm";
		WxCatToupiaoRecord wxCatToupiaoRecord = wxCatToupiaoRecordService.queryById(id);
		velocityContext.put("wxCatToupiaoRecord",wxCatToupiaoRecord);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,@ModelAttribute WxCatToupiaoRecord wxCatToupiaoRecord)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("wxCatToupiaoRecord",wxCatToupiaoRecord);
	 String viewName = "toupiao/back/wxCatToupiaoRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxCatToupiaoRecord wxCatToupiaoRecord,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
			
		wxCatToupiaoRecordService.doAdd(wxCatToupiaoRecord);
		}
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doVote",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doVote(@ModelAttribute WeixinDto weixinDto,@RequestParam(required = true, value = "actId" ) String actId,@RequestParam(required = true, value = "optionId" ) String optionId,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		long start = System.currentTimeMillis();
		// 装载微信所需参数
		 WxCatToupiaoRecord wxCatToupiaoRecord = new WxCatToupiaoRecord();
		 wxCatToupiaoRecord.setActId(actId);
		 wxCatToupiaoRecord.setIp(request.getRemoteAddr());
		 wxCatToupiaoRecord.setOptionId(optionId);
			wxCatToupiaoRecord.setVoteTime(new Date());
			wxCatToupiaoRecord.setJwid("");
			wxCatToupiaoRecord.setNickname(weixinDto.getNickname());
			wxCatToupiaoRecord.setOpenid(weixinDto.getOpenid());
			if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
		wxCatToupiaoRecordService.doAdd(wxCatToupiaoRecord);
			}
		j.setMsg("投票成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("投票失败");
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
		 WxCatToupiaoRecord wxCatToupiaoRecord = wxCatToupiaoRecordService.queryById(id);
		 velocityContext.put("wxCatToupiaoRecord",wxCatToupiaoRecord);
		 String viewName = "toupiao/back/wxCatToupiaoRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxCatToupiaoRecord wxCatToupiaoRecord,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
		wxCatToupiaoRecordService.doEdit(wxCatToupiaoRecord);
		}
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id ,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
				wxCatToupiaoRecordService.doDelete(id);
			}
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}


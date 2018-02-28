package com.jeecg.p3.houseactivity.web.back;

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
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.dictinfo.entity.Dictinfo;
import com.jeecg.p3.dictinfo.service.DictinfoService;
import com.jeecg.p3.houseactivity.entity.Houseactivity;
import com.jeecg.p3.houseactivity.service.HouseactivityService;
import com.jeecg.p3.noticeandnews.entity.Noticeandnews;
import com.jeecg.p3.system.vo.LoginUser;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>HouseactivityController<br>房屋转让团购抵押表
 * @author chao.hua
 * @since：2017年11月10日 16时45分41秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/houseactivity/back/houseactivity")
public class HouseactivityController extends BaseController{
  @Autowired
  private HouseactivityService houseactivityService;
  
  @Autowired
  private DictinfoService dictinfoService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute Houseactivity query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<Houseactivity> pageQuery = new PageQuery<Houseactivity>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    query.setCreatuser(user.getUserId());;
	    if(query.getIsdelete() ==null){
	    	query.setIsdelete("0");
	    }
	    //如果是管理员角色可以查看所有人发的
	    if(BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
	    	query.setCreatuser(null);
	    }
		pageQuery.setQuery(query);
		velocityContext.put("houseactivity",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(houseactivityService.queryPageList(pageQuery)));
		
		
		 PageQuery<Dictinfo> pageQuery0 = new PageQuery<Dictinfo>();
		 pageQuery0.setPageNo(0);
		 pageQuery0.setPageSize(20);
		 Dictinfo dictinfo = new Dictinfo();
		 dictinfo.setTypeid(5);  
			pageQuery0.setQuery(dictinfo);
		PageList<Dictinfo> dictlist = dictinfoService.queryPageList(pageQuery0);
		 velocityContext.put("areaInfos", SystemTools.convertPaginatedList(dictlist));
		 
		 
		 PageQuery<Dictinfo> pageQuery1 = new PageQuery<Dictinfo>();
		 pageQuery1.setPageNo(0);
		 pageQuery1.setPageSize(20);
		 Dictinfo dictinfo1 = new Dictinfo();
		 dictinfo1.setTypeid(7);  
			pageQuery1.setQuery(dictinfo1);
		PageList<Dictinfo> dictlist1 = dictinfoService.queryPageList(pageQuery1);
		 velocityContext.put("typeInfos", SystemTools.convertPaginatedList(dictlist1));
		 
		String viewName = "houseactivity/back/houseactivity-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void houseactivityDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "houseactivity/back/houseactivity-detail.vm";
		Houseactivity houseactivity = houseactivityService.queryById(id);
		velocityContext.put("houseactivity",houseactivity);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 
	 PageQuery<Dictinfo> pageQuery = new PageQuery<Dictinfo>();
	 pageQuery.setPageNo(0);
	 pageQuery.setPageSize(20);
	 Dictinfo dictinfo = new Dictinfo();
	 dictinfo.setTypeid(5);  
		pageQuery.setQuery(dictinfo);
	PageList<Dictinfo> dictlist = dictinfoService.queryPageList(pageQuery);
	 velocityContext.put("areaInfos", SystemTools.convertPaginatedList(dictlist));
	 
	 PageQuery<Dictinfo> pageQuery1 = new PageQuery<Dictinfo>();
	 pageQuery1.setPageNo(0);
	 pageQuery1.setPageSize(20);
	 Dictinfo dictinfo1 = new Dictinfo();
	 dictinfo1.setTypeid(7);  
		pageQuery1.setQuery(dictinfo1);
	PageList<Dictinfo> dictlist1 = dictinfoService.queryPageList(pageQuery1);
	 velocityContext.put("typeInfos", SystemTools.convertPaginatedList(dictlist1));
	 
	 String viewName = "houseactivity/back/houseactivity-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute Houseactivity houseactivity,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		houseactivity.setCreatuser(((LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER")).getUserId());
		houseactivity.setIsdelete("0");
		houseactivity.setCreatetime(new Date());
		houseactivity.setStatus("0");
		houseactivityService.doAdd(houseactivity);
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
		 Houseactivity houseactivity = houseactivityService.queryById(id);
		 velocityContext.put("houseactivity",houseactivity);
		 String viewName = "houseactivity/back/houseactivity-edit.vm";
		 
		 PageQuery<Dictinfo> pageQuery0 = new PageQuery<Dictinfo>();
		 pageQuery0.setPageNo(0);
		 pageQuery0.setPageSize(20);
		 Dictinfo dictinfo = new Dictinfo();
		 dictinfo.setTypeid(5);  
			pageQuery0.setQuery(dictinfo);
		PageList<Dictinfo> dictlist = dictinfoService.queryPageList(pageQuery0);
		 velocityContext.put("areaInfos", SystemTools.convertPaginatedList(dictlist));
		 
		 PageQuery<Dictinfo> pageQuery1 = new PageQuery<Dictinfo>();
		 pageQuery1.setPageNo(0);
		 pageQuery1.setPageSize(20);
		 Dictinfo dictinfo1 = new Dictinfo();
		 dictinfo1.setTypeid(7);  
			pageQuery1.setQuery(dictinfo1);
		PageList<Dictinfo> dictlist1 = dictinfoService.queryPageList(pageQuery1);
		 velocityContext.put("typeInfos", SystemTools.convertPaginatedList(dictlist1));
		 
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute Houseactivity houseactivity,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    houseactivity.setCreatuser(user.getUserId());
		houseactivityService.doEdit(houseactivity);
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
			houseactivityService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

@RequestMapping(value="doDel",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDel(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request){
	Houseactivity houseactivity = new Houseactivity();
	houseactivity.setId(id);
	houseactivity.setIsdelete("1");
	    LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	    houseactivity.setCreatuser(user.getUserId());
		AjaxJson j = new AjaxJson();
		try {
			houseactivityService.doEdit(houseactivity);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

}


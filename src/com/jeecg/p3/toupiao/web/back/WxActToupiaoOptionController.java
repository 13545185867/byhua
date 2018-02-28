package com.jeecg.p3.toupiao.web.back;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
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

import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;
import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;
import com.jeecg.p3.toupiao.service.WxActToupiaoOptionService;
import com.jeecg.p3.toupiao.service.WxActToupiaoService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>WxActToupiaoOptionController<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/toupiao/back/wxActToupiaoOption")
public class WxActToupiaoOptionController extends BaseController{
  @Autowired
  private WxActToupiaoOptionService wxActToupiaoOptionService;
  
  @Autowired
  private WxActToupiaoService wxActToupiaoService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActToupiaoOption query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActToupiaoOption> pageQuery = new PageQuery<WxActToupiaoOption>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	if(query.getActId() !=null && query.getActId()!=""){
	 		WxActToupiao wxtp = wxActToupiaoService.queryById(query.getActId());
	 	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	 		if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
				pageQuery.setQuery(query);
				velocityContext.put("wxActToupiaoOption",query);
				if(query.getIsDelete() ==null || query.getIsDelete() ==""){
					query.setIsDelete("0");
				}
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActToupiaoOptionService.queryPageList(pageQuery)));
	 		}
	 	}
		String viewName = "toupiao/back/wxActToupiaoOption-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toSetImage",method = RequestMethod.GET)
public void wxActToupiaoOptionDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "toupiao/back/wxActToupiaoOption-setImage.vm";
		WxActToupiaoOption wxActToupiaoOption = wxActToupiaoOptionService.queryById(id);
		velocityContext.put("wxActToupiaoOption",wxActToupiaoOption);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(@RequestParam(required = false, value = "actId" ) String actId,HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	WxActToupiaoOption wxActToupiaoOption = new WxActToupiaoOption();
	VelocityContext velocityContext = new VelocityContext();
	 String viewName = "toupiao/back/wxActToupiaoOption-add.vm";
		if(!"".equals(actId)||null !=actId ){
			wxActToupiaoOption.setActId(actId);
			velocityContext.put("wxActToupiaoOption", wxActToupiaoOption);
		}
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActToupiaoOption wxActToupiaoOption){
	AjaxJson j = new AjaxJson();
	try {
			wxActToupiaoOption.setVoteCount(0);
		wxActToupiaoOption.setCreatTime(new Date());
		wxActToupiaoOptionService.doAdd(wxActToupiaoOption);
		
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
@RequestMapping(value = "/doSetImage",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doSetImage(@ModelAttribute WxActToupiaoOption wxActToupiaoOption,
		@RequestParam(required = true, value = "x" ) String x,
		@RequestParam(required = true, value = "y" ) String y,
		@RequestParam(required = true, value = "w" ) String w,
		@RequestParam(required = true, value = "h" ) String h,
		HttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	WxActToupiao wxtp = wxActToupiaoService.queryById(wxActToupiaoOption.getActId());
 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
	if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
		try {

			int width = 	Integer.parseInt(w);
			int height = Integer.parseInt(h);
			int rx = Integer.parseInt(x);
			int ry = Integer.parseInt(y);
			String img = wxActToupiaoOption.getImage();
			
			//文件格式
			String ext = img.substring(img.lastIndexOf(".")+1);
			String uploadDir = request.getSession().getServletContext().getRealPath("upload/img/system");
			File srcfile = new File(uploadDir+"/"+img);
			FileInputStream is = null ;
			ImageInputStream iis = null;
			try{
			
			Image image =ImageIO.read(srcfile);
			BufferedImage 	tag  = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH),0,0,null);
		    FileOutputStream out = new FileOutputStream(srcfile);  
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
			             encoder.encode(tag);  
			             out.close();  
				
			 is = new FileInputStream(srcfile);
			 iis = ImageIO.createImageInputStream(is);
			
			Iterator<ImageReader> it= ImageIO.getImageReadersByFormatName(ext);
			ImageReader reader = it.next();
			reader.setInput(iis);
			
			ImageReadParam param =  reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(Integer.parseInt(x),Integer.parseInt(y),550,660);
			param.setSourceRegion(rect);
			
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, ext, new File(uploadDir+"/1"+img));
			wxActToupiaoOption.setImage("1"+img);
			wxActToupiaoOption.setVoteCount(null);
			}finally{
	            if(is!=null)
	                is.close() ;       
	             if(iis!=null)
	                iis.close();  
	         } 
			wxActToupiaoOptionService.doEdit(wxActToupiaoOption);
			j.setMsg("保存成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
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
		 WxActToupiaoOption wxActToupiaoOption = wxActToupiaoOptionService.queryById(id);
		 velocityContext.put("wxActToupiaoOption",wxActToupiaoOption);
		 String viewName = "toupiao/back/wxActToupiaoOption-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActToupiaoOption wxActToupiaoOption ,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		WxActToupiao wxtp = wxActToupiaoService.queryById(wxActToupiaoOption.getActId());
	 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
		if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
			wxActToupiaoOption.setVoteCount(null);
			wxActToupiaoOptionService.doEdit(wxActToupiaoOption);
			j.setSuccess(true);
			j.setMsg("编辑成功");
		}
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			WxActToupiao wxtp = wxActToupiaoService.queryById(wxActToupiaoOptionService.queryById(id).getActId());
		 	LoginUser user = (LoginUser)request.getSession().getAttribute("OPERATE_WEB_LOGIN_USER");
			if(wxtp.getUserid().equals(user.getUserId()) || BaihuaUtil.isadmin((List<String>) request.getSession().getAttribute("roleIds"))){
				wxActToupiaoOptionService.doDelete(id);
				j.setMsg("删除成功");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


/**
 * 详情
 * @return
 */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxCatToupiaoRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "toupiao/back/wxActToupiaoOption-detail.vm";
		WxActToupiaoOption wxActToupiaoOption = wxActToupiaoOptionService.queryById(id);
		velocityContext.put("wxActToupiaoOption",wxActToupiaoOption);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

}


package com.jeecg.p3.toupiao.web;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.p3.core.common.utils.AjaxJson;

import org.jeecgframework.p3.core.web.BaseController;


 /**
 * 描述：</b>WxActToupiaoController<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/toupiao/upFile")
public class UpFileController extends BaseController{
  

@RequestMapping(value="doUp",method = {RequestMethod.GET,RequestMethod.POST})
public void doUpload(HttpServletResponse response
		,MultipartHttpServletRequest request) throws Exception{
	
	 VelocityContext velocityContext = new VelocityContext();
	    try {
	      MultipartFile uploadify = request.getFile("file");
	      byte[] bytes = uploadify.getBytes();
	      System.out.println(uploadify.getOriginalFilename());
	      String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/system");
	      File dirPath = new File(uploadDir);
	      if (!dirPath.exists()) {
	        dirPath.mkdirs();
	      }
	      String sep = System.getProperty("file.separator");
	      Date date = new Date();
	      String body="";  
	      String ext="";  
	      int pot=uploadify.getOriginalFilename().lastIndexOf(".");  
	      if(pot!=-1){  
	          body= date.getTime() +"";  
	          ext=uploadify.getOriginalFilename().substring(pot);  
	      }else{  
	          body=(new Date()).getTime()+"";  
	          ext="";  
	      }  
	      String newName=body+ext;  
	      File uploadedFile = new File(uploadDir + sep + newName);

	      FileCopyUtils.copy(bytes, uploadedFile);
	      String status = "1";
	      String errormsg = "";
	      String guid = body;
	      String imgthumb = newName;
	      String imgurl = newName;

	      velocityContext.put("status", status);
	      velocityContext.put("errormsg", errormsg);
	      velocityContext.put("guid", guid);
	      velocityContext.put("imgthumb", imgthumb);
	      velocityContext.put("imgurl", imgurl);
	      String viewName = "toupiao/back/baoming.vm";
	      ViewVelocity.view(response, viewName, velocityContext);
	    } catch (Exception e) {
	    }

}



@RequestMapping(value="delUp",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson delUpload(@RequestParam(required = false, value = "imgName" ) String imgName,
		HttpServletResponse response
		,HttpServletRequest request) throws Exception{
    AjaxJson j = new AjaxJson();

	      String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/system/");
	      String sep = System.getProperty("file.separator");
	      File file = new File(uploadDir+sep+imgName);
	      
	      boolean flag = false;
	        if (file.isFile() && file.exists()) {
	        	flag = file.getAbsoluteFile().delete();;
	        }
	      
	    j.setSuccess(flag);
	    	return j;
}

}


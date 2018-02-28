package com.jeecg.p3.aboutActivity.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.aboutActivity.service.ActivityWxuserService;
import com.jeecg.p3.aboutActivity.entity.ActivityWxuser;
import com.jeecg.p3.aboutActivity.dao.ActivityWxuserDao;

@Service("activityWxuserService")
public class ActivityWxuserServiceImpl implements ActivityWxuserService {
	@Resource
	private ActivityWxuserDao activityWxuserDao;

	@Override
	public void doAdd(ActivityWxuser activityWxuser) {
		activityWxuserDao.add(activityWxuser);
	}

	@Override
	public void doEdit(ActivityWxuser activityWxuser) {
		activityWxuserDao.update(activityWxuser);
	}

	@Override
	public void doDelete(String id) {
		activityWxuserDao.delete(id);
	}

	@Override
	public ActivityWxuser queryById(String id) {
		ActivityWxuser activityWxuser  = activityWxuserDao.get(id);
		return activityWxuser;
	}

	@Override
	public PageList<ActivityWxuser> queryPageList(
		PageQuery<ActivityWxuser> pageQuery) {
		PageList<ActivityWxuser> result = new PageList<ActivityWxuser>();
		Integer itemCount = activityWxuserDao.count(pageQuery);
		List<ActivityWxuser> list = activityWxuserDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public void updateByopenid(ActivityWxuser pageQuery) {
		// TODO Auto-generated method stub
		activityWxuserDao.updateByopenid(pageQuery);
	}

	@Override
	public String getRequestUrlWithParams(HttpServletRequest request) {
		// TODO Auto-generated method stub
	   	  String backurl = request.getScheme()+"://"+request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
	   	try {
			backurl = URLEncoder.encode(backurl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	  return backurl;
	}

	@Override
	public Integer count(PageQuery<ActivityWxuser> pageQuery) {
		// TODO Auto-generated method stub
		return activityWxuserDao.count(pageQuery);
	}

	@Override
	public ActivityWxuser getByOpenId(String pageQuery) {
		// TODO Auto-generated method stub
		return activityWxuserDao.getByOpenId(pageQuery);
	}
	
   
}

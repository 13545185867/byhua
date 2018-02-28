package com.jeecg.p3.aboutActivity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.aboutActivity.service.ActivityConfigService;
import com.jeecg.p3.aboutActivity.entity.ActivityConfig;
import com.jeecg.p3.aboutActivity.dao.ActivityConfigDao;
import com.jeecg.p3.userservice.entity.Userservice;
import com.jeecg.p3.userservice.service.UserserviceService;

@Service("activityConfigService")
public class ActivityConfigServiceImpl implements ActivityConfigService {
	@Resource
	private ActivityConfigDao activityConfigDao;
	  @Autowired
	  private UserserviceService userserviceService;

	@Override
	public void doAdd(ActivityConfig activityConfig) {
		activityConfigDao.add(activityConfig);
	}

	@Override
	public void doEdit(ActivityConfig activityConfig) {
		activityConfigDao.update(activityConfig);
	}

	@Override
	public void doDelete(Integer id) {
		activityConfigDao.delete(id);
	}

	@Override
	public ActivityConfig queryById(String id) {
		ActivityConfig activityConfig  = activityConfigDao.get(id);
		return activityConfig;
	}

	@Override
	public PageList<ActivityConfig> queryPageList(
		PageQuery<ActivityConfig> pageQuery) {
		PageList<ActivityConfig> result = new PageList<ActivityConfig>();
		Integer itemCount = activityConfigDao.count(pageQuery);
		List<ActivityConfig> list = activityConfigDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}



	@Override
	public boolean isVip(String modelid, String currentuser) {
		// TODO Auto-generated method stub
		 Userservice userservice = new Userservice();
		 userservice.setUserservicecol0(modelid);
		 userservice.setUserid(currentuser);
		 List<Userservice> userlist = userserviceService.queryByRecord(userservice);
		 if(userlist.size()<=0){
				return false;
		 }else{
			 return true;
		 }
	}

	@Override
	public ActivityConfig queryOne(Integer activityId) {
		// TODO Auto-generated method stub
		return activityConfigDao.queryOne(activityId);
	}
}

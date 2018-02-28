package com.jeecg.p3.toupiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.toupiao.service.WxActToupiaoConfigService;
import com.jeecg.p3.toupiao.entity.WxActToupiaoConfig;
import com.jeecg.p3.toupiao.dao.WxActToupiaoConfigDao;

@Service("wxActToupiaoConfigService")
public class WxActToupiaoConfigServiceImpl implements WxActToupiaoConfigService {
	@Resource
	private WxActToupiaoConfigDao wxActToupiaoConfigDao;

	@Override
	public void doAdd(WxActToupiaoConfig wxActToupiaoConfig) {
		wxActToupiaoConfigDao.add(wxActToupiaoConfig);
	}

	@Override
	public void doEdit(WxActToupiaoConfig wxActToupiaoConfig) {
		wxActToupiaoConfigDao.update(wxActToupiaoConfig);
	}

	@Override
	public void doDelete(String id) {
		wxActToupiaoConfigDao.delete(id);
	}

	@Override
	public WxActToupiaoConfig queryById(String id) {
		WxActToupiaoConfig wxActToupiaoConfig  = wxActToupiaoConfigDao.get(id);
		return wxActToupiaoConfig;
	}

	@Override
	public PageList<WxActToupiaoConfig> queryPageList(
		PageQuery<WxActToupiaoConfig> pageQuery) {
		PageList<WxActToupiaoConfig> result = new PageList<WxActToupiaoConfig>();
		Integer itemCount = wxActToupiaoConfigDao.count(pageQuery);
		List<WxActToupiaoConfig> list = wxActToupiaoConfigDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActToupiaoConfig getByActId(String actid) {
		// TODO Auto-generated method stub
		return wxActToupiaoConfigDao.getByActId(actid);
	}

	@Override
	public void updateByActId(WxActToupiaoConfig wxtc) {
		// TODO Auto-generated method stub
		wxActToupiaoConfigDao.updateByActId(wxtc);
	}

	@Override
	public void deleteByActId(String actid) {
		// TODO Auto-generated method stub
		wxActToupiaoConfigDao.deleteByActId(actid);
	}
	
}

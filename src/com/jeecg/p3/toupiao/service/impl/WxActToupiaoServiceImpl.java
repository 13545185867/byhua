package com.jeecg.p3.toupiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.toupiao.service.WxActToupiaoService;
import com.jeecg.p3.toupiao.entity.WxActToupiao;
import com.jeecg.p3.toupiao.entity.WxActToupiaoAConfig;
import com.jeecg.p3.toupiao.dao.WxActToupiaoDao;

@Service("wxActToupiaoService")
public class WxActToupiaoServiceImpl implements WxActToupiaoService {
	@Resource
	private WxActToupiaoDao wxActToupiaoDao;

	@Override
	public void doAdd(WxActToupiao wxActToupiao) {
		wxActToupiaoDao.add(wxActToupiao);
	}

	@Override
	public void doEdit(WxActToupiao wxActToupiao) {
		wxActToupiaoDao.update(wxActToupiao);
	}

	@Override
	public void doDelete(String id) {
		wxActToupiaoDao.delete(id);
	}

	@Override
	public WxActToupiao queryById(String id) {
		WxActToupiao wxActToupiao  = wxActToupiaoDao.get(id);
		return wxActToupiao;
	}

	@Override
	public PageList<WxActToupiao> queryPageList(
		PageQuery<WxActToupiao> pageQuery) {
		PageList<WxActToupiao> result = new PageList<WxActToupiao>();
		Integer itemCount = wxActToupiaoDao.count(pageQuery);
		List<WxActToupiao> list = wxActToupiaoDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActToupiaoAConfig queryByActId(String actid) {
		// TODO Auto-generated method stub
		return wxActToupiaoDao.queryByActId(actid);
	}
	
}

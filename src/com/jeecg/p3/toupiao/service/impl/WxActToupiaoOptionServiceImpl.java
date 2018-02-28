package com.jeecg.p3.toupiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.toupiao.service.WxActToupiaoOptionService;
import com.jeecg.p3.toupiao.entity.WxActToupiaoOption;
import com.jeecg.p3.toupiao.dao.WxActToupiaoOptionDao;

@Service("wxActToupiaoOptionService")
public class WxActToupiaoOptionServiceImpl implements WxActToupiaoOptionService {
	@Resource
	private WxActToupiaoOptionDao wxActToupiaoOptionDao;

	@Override
	public void doAdd(WxActToupiaoOption wxActToupiaoOption) {
		wxActToupiaoOptionDao.add(wxActToupiaoOption);
	}

	@Override
	public void doEdit(WxActToupiaoOption wxActToupiaoOption) {
		wxActToupiaoOptionDao.update(wxActToupiaoOption);
	}

	@Override
	public void doDelete(String id) {
		wxActToupiaoOptionDao.delete(id);
	}

	@Override
	public WxActToupiaoOption queryById(String id) {
		WxActToupiaoOption wxActToupiaoOption  = wxActToupiaoOptionDao.get(id);
		return wxActToupiaoOption;
	}

	@Override
	public PageList<WxActToupiaoOption> queryPageList(
		PageQuery<WxActToupiaoOption> pageQuery) {
		PageList<WxActToupiaoOption> result = new PageList<WxActToupiaoOption>();
		Integer itemCount = wxActToupiaoOptionDao.count(pageQuery);
		List<WxActToupiaoOption> list = wxActToupiaoOptionDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Integer getSumByActId(String actId) {
		// TODO Auto-generated method stub
		return  wxActToupiaoOptionDao.getSumByActId(actId);
		
	}

	@Override
	public void updateVoteCount(String id) {
		// TODO Auto-generated method stub
		wxActToupiaoOptionDao.updateVoteCount(id);
	}

	@Override
	public List<WxActToupiaoOption> getVoteOrder(String actId) {
		// TODO Auto-generated method stub
		return wxActToupiaoOptionDao.getVoteOrder(actId);
	}
	
}

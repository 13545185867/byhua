package com.jeecg.p3.toupiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.toupiao.service.WxCatToupiaoRecordService;
import com.jeecg.p3.toupiao.entity.WxCatToupiaoRecord;
import com.jeecg.p3.toupiao.dao.WxCatToupiaoRecordDao;

@Service("wxCatToupiaoRecordService")
public class WxCatToupiaoRecordServiceImpl implements WxCatToupiaoRecordService {
	@Resource
	private WxCatToupiaoRecordDao wxCatToupiaoRecordDao;

	@Override
	public void doAdd(WxCatToupiaoRecord wxCatToupiaoRecord) {
		wxCatToupiaoRecordDao.add(wxCatToupiaoRecord);
	}

	@Override
	public void doEdit(WxCatToupiaoRecord wxCatToupiaoRecord) {
		wxCatToupiaoRecordDao.update(wxCatToupiaoRecord);
	}

	@Override
	public void doDelete(String id) {
		wxCatToupiaoRecordDao.delete(id);
	}

	@Override
	public WxCatToupiaoRecord queryById(String id) {
		WxCatToupiaoRecord wxCatToupiaoRecord  = wxCatToupiaoRecordDao.get(id);
		return wxCatToupiaoRecord;
	}

	@Override
	public PageList<WxCatToupiaoRecord> queryPageList(
		PageQuery<WxCatToupiaoRecord> pageQuery) {
		PageList<WxCatToupiaoRecord> result = new PageList<WxCatToupiaoRecord>();
		Integer itemCount = wxCatToupiaoRecordDao.count(pageQuery);
		List<WxCatToupiaoRecord> list = wxCatToupiaoRecordDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxCatToupiaoRecord> getByOpenIDATime(WxCatToupiaoRecord wxtr) {
		// TODO Auto-generated method stub
		return wxCatToupiaoRecordDao.getByOpenIDATime(wxtr);
	}
	
}

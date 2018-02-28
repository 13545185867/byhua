package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfCellService;
import com.jeecg.p3.houseaOnline.entity.XfCell;
import com.jeecg.p3.houseaOnline.dao.XfCellDao;

@Service("xfCellService")
public class XfCellServiceImpl implements XfCellService {
	@Resource
	private XfCellDao xfCellDao;

	@Override
	public void doAdd(XfCell xfCell) {
		xfCellDao.add(xfCell);
	}

	@Override
	public void doEdit(XfCell xfCell) {
		xfCellDao.update(xfCell);
	}

	@Override
	public void doDelete(String id) {
		xfCellDao.delete(id);
	}

	@Override
	public XfCell queryById(String id) {
		XfCell xfCell  = xfCellDao.get(id);
		return xfCell;
	}

	@Override
	public PageList<XfCell> queryPageList(
		PageQuery<XfCell> pageQuery) {
		PageList<XfCell> result = new PageList<XfCell>();
		Integer itemCount = xfCellDao.count(pageQuery);
		List<XfCell> list = xfCellDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<XfCell> queryByCondit(XfCell sfcell) {
		// TODO Auto-generated method stub
		return xfCellDao.queryByCondit(sfcell);
	}
	
}

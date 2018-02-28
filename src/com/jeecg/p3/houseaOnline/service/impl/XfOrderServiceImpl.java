package com.jeecg.p3.houseaOnline.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.houseaOnline.service.XfOrderService;
import com.jeecg.p3.houseaOnline.entity.XfOrder;
import com.jeecg.p3.houseaOnline.dao.XfOrderDao;

@Service("xfOrderService")
public class XfOrderServiceImpl implements XfOrderService {
	@Resource
	private XfOrderDao xfOrderDao;

	@Override
	public void doAdd(XfOrder xfOrder) {
		xfOrderDao.add(xfOrder);
	}

	@Override
	public void doEdit(XfOrder xfOrder) {
		xfOrderDao.update(xfOrder);
	}

	@Override
	public void doDelete(String id) {
		xfOrderDao.delete(id);
	}

	@Override
	public XfOrder queryById(String id) {
		XfOrder xfOrder  = xfOrderDao.get(id);
		return xfOrder;
	}

	@Override
	public PageList<XfOrder> queryPageList(
		PageQuery<XfOrder> pageQuery) {
		PageList<XfOrder> result = new PageList<XfOrder>();
		Integer itemCount = xfOrderDao.count(pageQuery);
		List<XfOrder> list = xfOrderDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}

package com.jeecg.p3.houseactivity.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.houseactivity.entity.Houseactivity;

/**
 * 描述：</b>HouseactivityService<br>
 * @author：chao.hua
 * @since：2017年11月11日 14时16分48秒 星期六 
 * @version:1.0
 */
public interface HouseactivityService {
	
	
	public void doAdd(Houseactivity houseactivity);
	
	public void doEdit(Houseactivity houseactivity);
	
	public void doDelete(String id);
	
	public Houseactivity queryById(String id);
	
	public PageList<Houseactivity> queryPageList(PageQuery<Houseactivity> pageQuery);
	
	public List<Houseactivity> queryList(Houseactivity house);
}


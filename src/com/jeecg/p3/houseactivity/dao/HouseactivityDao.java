package com.jeecg.p3.houseactivity.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.houseactivity.entity.Houseactivity;

/**
 * 描述：</b>HouseactivityDao<br>
 * @author：chao.hua
 * @since：2017年11月11日 14时16分48秒 星期六 
 * @version:1.0
 */
public interface HouseactivityDao extends GenericDao<Houseactivity>{
	
	public Integer count(PageQuery<Houseactivity> pageQuery);
	
	public List<Houseactivity> queryPageList(PageQuery<Houseactivity> pageQuery,Integer itemCount);
	
	public List<Houseactivity> queryList(Houseactivity house);
	
}


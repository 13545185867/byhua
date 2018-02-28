package com.jeecg.p3.bigwheel.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.bigwheel.entity.ActivityBigwheelPlayrule;

/**
 * 描述：</b>ActivityBigwheelPlayruleDao<br>
 * @author：chao.hua
 * @since：2017年08月02日 16时24分30秒 星期三 
 * @version:1.0
 */
public interface ActivityBigwheelPlayruleDao extends GenericDao<ActivityBigwheelPlayrule>{
	
	public Integer count(PageQuery<ActivityBigwheelPlayrule> pageQuery);
	
	public List<ActivityBigwheelPlayrule> queryPageList(PageQuery<ActivityBigwheelPlayrule> pageQuery,Integer itemCount);
	
	public List<ActivityBigwheelPlayrule> queryBYCol(PageQuery<ActivityBigwheelPlayrule> abp);
	
}


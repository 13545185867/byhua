package com.jeecg.p3.jlb.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.jeecgframework.p3.core.utils.persistence.Entity;

import com.jeecg.p3.noticeandnews.entity.Noticeandnews;

/**
 * 描述：</b>Dictinfo:数据字典信息表<br>
 * @author chao.hua
 * @since：2017年07月25日 17时04分33秒 星期二 
 * @version:1.0
 */
public class Jlb implements Entity<Long> {
	private static final long serialVersionUID = 1L;
	
	
	private List<Noticeandnews> actNews; 
	
	public List<Noticeandnews> getActNews() {
		return actNews;
	}
	public void setActNews(List<Noticeandnews> actNews) {
		this.actNews = actNews;
	}
 
}

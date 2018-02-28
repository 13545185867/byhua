package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfHx:在线选房,户型表<br>
 * @author chao.hua
 * @since：2017年11月19日 14时17分34秒 星期日 
 * @version:1.0
 */
public class XfHx implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *户型 ID	 */	private String id;	/**	 *楼盘项目ID 	 */	private String pid;	/**	 *户型 名称	 */	private String name;	/**	 *面积	 */	private BigDecimal area;	/**	 *户型缩略图	 */	private String pic;	/**	 *是否已删除	 */	private String isDelete;	/**	 *创建时间	 */	private Date createtime;	/**	 *创建人ID	 */	private String creatuser;	/**	 *户型介绍	 */	private String hxContent;	/**	 *	 */	private String xfHxcol;	/**	 *	 */	private Integer xfHxcol1;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getPid() {	    return this.pid;	}	public void setPid(String pid) {	    this.pid=pid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public BigDecimal getArea() {	    return this.area;	}	public void setArea(BigDecimal area) {	    this.area=area;	}	public String getPic() {	    return this.pic;	}	public void setPic(String pic) {	    this.pic=pic;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getCreatuser() {	    return this.creatuser;	}	public void setCreatuser(String creatuser) {	    this.creatuser=creatuser;	}	public String getHxContent() {	    return this.hxContent;	}	public void setHxContent(String hxContent) {	    this.hxContent=hxContent;	}	public String getXfHxcol() {	    return this.xfHxcol;	}	public void setXfHxcol(String xfHxcol) {	    this.xfHxcol=xfHxcol;	}	public Integer getXfHxcol1() {	    return this.xfHxcol1;	}	public void setXfHxcol1(Integer xfHxcol1) {	    this.xfHxcol1=xfHxcol1;	}
}


package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfCell:在线选房,单元表<br>
 * @author chao.hua
 * @since：2017年11月19日 16时52分42秒 星期日 
 * @version:1.0
 */
public class XfCell implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *单元名	 */	private String name;	/**	 *单元位置	 */	private String addr;	/**	 *楼栋ID	 */	private String buildingid;	/**	 *每层房间数	 */	private Integer housecount;	/**	 *是否已删除	 */	private String isDelete;	/**	 *创建时间	 */	private Date createtime;	/**	 *创建人	 */	private String createuser;	/**	 *	 */	private String xfCellcol;	/**	 *	 */	private Integer xfCellcol1;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getAddr() {	    return this.addr;	}	public void setAddr(String addr) {	    this.addr=addr;	}	public String getBuildingid() {	    return this.buildingid;	}	public void setBuildingid(String buildingid) {	    this.buildingid=buildingid;	}	public Integer getHousecount() {	    return this.housecount;	}	public void setHousecount(Integer housecount) {	    this.housecount=housecount;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getCreateuser() {	    return this.createuser;	}	public void setCreateuser(String createuser) {	    this.createuser=createuser;	}	public String getXfCellcol() {	    return this.xfCellcol;	}	public void setXfCellcol(String xfCellcol) {	    this.xfCellcol=xfCellcol;	}	public Integer getXfCellcol1() {	    return this.xfCellcol1;	}	public void setXfCellcol1(Integer xfCellcol1) {	    this.xfCellcol1=xfCellcol1;	}
}


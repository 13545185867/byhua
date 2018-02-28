package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfBuilding:选房 楼栋表<br>
 * @author chao.hua
 * @since：2017年11月19日 15时36分14秒 星期日 
 * @version:1.0
 */
public class XfBuilding implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *楼栋ID	 */	private String id;	/**	 *楼栋名称	 */	private String name;	/**	 *楼栋位置	 */	private String addr;	/**	 *楼盘项目ID	 */	private String pid;	/**	 *	 */	private String isDelete;	/**	 *	 */	private Date createtime;
	
	private Date kaipanDate;	/**	 *	 */	private String createuser;	/**	 *	 */	private Integer floorcount;	/**	 *楼栋类型:高层（12层以上） 小高层（9-12） 洋房 低层（1-3层） 多层（4-6
层）中高层（7-9））	 */	private String type;	/**	 *	 */	private String xfBuildingcol;	/**	 *	 */	private Integer xfBuildingcol1;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getAddr() {	    return this.addr;	}	public void setAddr(String addr) {	    this.addr=addr;	}	public String getPid() {	    return this.pid;	}	public void setPid(String pid) {	    this.pid=pid;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getCreateuser() {	    return this.createuser;	}	public void setCreateuser(String createuser) {	    this.createuser=createuser;	}	public Integer getFloorcount() {	    return this.floorcount;	}	public void setFloorcount(Integer floorcount) {	    this.floorcount=floorcount;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public String getXfBuildingcol() {	    return this.xfBuildingcol;	}	public void setXfBuildingcol(String xfBuildingcol) {	    this.xfBuildingcol=xfBuildingcol;	}	public Integer getXfBuildingcol1() {	    return this.xfBuildingcol1;	}	public void setXfBuildingcol1(Integer xfBuildingcol1) {	    this.xfBuildingcol1=xfBuildingcol1;	}
	public Date getKaipanDate() {
		return kaipanDate;
	}
	public void setKaipanDate(Date kaipanDate) {
		this.kaipanDate = kaipanDate;
	}
}


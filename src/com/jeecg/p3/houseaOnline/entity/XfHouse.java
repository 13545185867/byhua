package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfHouse:<br>
 * @author chao.hua
 * @since：2017年11月19日 18时12分05秒 星期日 
 * @version:1.0
 */
public class XfHouse implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *房间编号	 */	private String name;	/**	 *单元ID	 */	private String cellid;	/**	 *楼栋ID	 */	private String buildingid;	/**	 *户型 ID	 */	private String hxid;	/**	 *项目ID	 */	private String pid;	/**	 *	 */	private String isDelete;	/**	 *创建时间	 */	private Date createtime;
	
	private Date kaipanDate;
	
	private Date deliverydate;	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	/**	 *创建人	 */	private String createuser;	/**	 *状态：待售 在售 已被预定未支付订金 已支付定金 已售 	 */	private String status;	/**	 *报价	 */	private String price;	/**	 *装修情况	 */	private String zhuangxiustatus;	/**	 *楼层	 */	private Integer floor;	/**	 *电梯情况	 */	private String elevator;	/**	 *房屋朝向	 */	private String chaoxiang;	/**	 *详情	 */	private String content;	/**	 *网上定金	 */	private BigDecimal orderprice;	/**	 *	 */	private String xfHousecol;
	
	private Integer floorcount;
	
	private String hxpic;
	
	private Integer viewcount;
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
	public String getHxpic() {
		return hxpic;
	}
	public void setHxpic(String hxpic) {
		this.hxpic = hxpic;
	}
	public String getHxcontent() {
		return hxcontent;
	}
	public void setHxcontent(String hxcontent) {
		this.hxcontent = hxcontent;
	}
	private String hxcontent;
	public Integer getFloorcount() {
		return floorcount;
	}
	public void setFloorcount(Integer floorcount) {
		this.floorcount = floorcount;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	private BigDecimal area;	/**	 *	 */
	private String projectname;
	private String buildingname;
	private String cellname;
	private String hxname;
		private Integer xfHousecol1;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getCellid() {	    return this.cellid;	}	public void setCellid(String cellid) {	    this.cellid=cellid;	}	public String getBuildingid() {	    return this.buildingid;	}	public void setBuildingid(String buildingid) {	    this.buildingid=buildingid;	}	public String getHxid() {	    return this.hxid;	}	public void setHxid(String hxid) {	    this.hxid=hxid;	}	public String getPid() {	    return this.pid;	}	public void setPid(String pid) {	    this.pid=pid;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getCreateuser() {	    return this.createuser;	}	public void setCreateuser(String createuser) {	    this.createuser=createuser;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getPrice() {	    return this.price;	}	public void setPrice(String price) {	    this.price=price;	}	public String getZhuangxiustatus() {	    return this.zhuangxiustatus;	}	public void setZhuangxiustatus(String zhuangxiustatus) {	    this.zhuangxiustatus=zhuangxiustatus;	}	public Integer getFloor() {	    return this.floor;	}	public void setFloor(Integer floor) {	    this.floor=floor;	}	public String getElevator() {	    return this.elevator;	}	public void setElevator(String elevator) {	    this.elevator=elevator;	}	public String getChaoxiang() {	    return this.chaoxiang;	}	public void setChaoxiang(String chaoxiang) {	    this.chaoxiang=chaoxiang;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public BigDecimal getOrderprice() {	    return this.orderprice;	}	public void setOrderprice(BigDecimal orderprice) {	    this.orderprice=orderprice;	}	public String getXfHousecol() {	    return this.xfHousecol;	}	public void setXfHousecol(String xfHousecol) {	    this.xfHousecol=xfHousecol;	}	public Integer getXfHousecol1() {	    return this.xfHousecol1;	}	public void setXfHousecol1(Integer xfHousecol1) {	    this.xfHousecol1=xfHousecol1;	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public String getCellname() {
		return cellname;
	}
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}
	public String getHxname() {
		return hxname;
	}
	public void setHxname(String hxname) {
		this.hxname = hxname;
	}
	public Date getKaipanDate() {
		return kaipanDate;
	}
	public void setKaipanDate(Date kaipanDate) {
		this.kaipanDate = kaipanDate;
	}
}


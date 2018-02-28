package com.jeecg.p3.houseactivity.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>Houseactivity:房屋转让团购抵押表<br>
 * @author chao.hua
 * @since：2017年11月11日 14时16分48秒 星期六 
 * @version:1.0
 */
public class Houseactivity implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *标题	 */	private String title;	/**	 *	 */	private String addr;	/**	 *总名额 	 */	private Integer totalpepole;	/**	 *剩余名额 	 */	private Integer remainpepole;
	
	private Integer viewcount;	/**	 *是否删除  0否 1删除	 */	private String isdelete;	/**	 *截止时间	 */	private Date endtime;	/**	 *详细内容 	 */	private String content;	/**	 *是否只有VIP可查看	 */	private String isvipview;	/**	 *是否只有VIP可参加	 */	private String isvipparty;	/**	 *创建时间	 */	private Date createtime;	/**	 *创建 人	 */	private String creatuser;	/**	 *缩略图	 */	private String leadpic;	/**	 *主题图显示方式（0为小图，1为大图）	 */	private String leadpicviewtype;	/**	 *所属区域ID	 */	private Integer areaid;	/**	 *0代表抵押 1代表转让 2 代表团购 3代表在现选房	 */	private String type;
	private String status;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getAddr() {	    return this.addr;	}	public void setAddr(String addr) {	    this.addr=addr;	}	public Integer getTotalpepole() {	    return this.totalpepole;	}	public void setTotalpepole(Integer totalpepole) {	    this.totalpepole=totalpepole;	}	public Integer getRemainpepole() {	    return this.remainpepole;	}	public void setRemainpepole(Integer remainpepole) {	    this.remainpepole=remainpepole;	}	public String getIsdelete() {	    return this.isdelete;	}	public void setIsdelete(String isdelete) {	    this.isdelete=isdelete;	}	public Date getEndtime() {	    return this.endtime;	}	public void setEndtime(Date endtime) {	    this.endtime=endtime;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getIsvipview() {	    return this.isvipview;	}	public void setIsvipview(String isvipview) {	    this.isvipview=isvipview;	}	public String getIsvipparty() {	    return this.isvipparty;	}	public void setIsvipparty(String isvipparty) {	    this.isvipparty=isvipparty;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getCreatuser() {	    return this.creatuser;	}	public void setCreatuser(String creatuser) {	    this.creatuser=creatuser;	}	public String getLeadpic() {	    return this.leadpic;	}	public void setLeadpic(String leadpic) {	    this.leadpic=leadpic;	}	public String getLeadpicviewtype() {	    return this.leadpicviewtype;	}	public void setLeadpicviewtype(String leadpicviewtype) {	    this.leadpicviewtype=leadpicviewtype;	}	public Integer getAreaid() {	    return this.areaid;	}	public void setAreaid(Integer areaid) {	    this.areaid=areaid;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
}


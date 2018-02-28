package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfFollow:在线选房关注表<br>
 * @author chao.hua
 * @since：2017年11月25日 15时07分14秒 星期六 
 * @version:1.0
 */
public class XfFollow implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *关注资源ID	 */	private String resourceid;	/**	 *资源类型0：项目， 1：房间	 */	private String type;	/**	 *关注人	 */	private String followuser;	/**	 *关注时间	 */	private Date followdate;	/**	 *	 */	private String xfFollowcol;	/**	 *	 */	private Integer xfFollowcol1;	/**	 *	 */	private String resourcename;	/**	 *	 */	private String resourceurl;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getResourceid() {	    return this.resourceid;	}	public void setResourceid(String resourceid) {	    this.resourceid=resourceid;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public String getFollowuser() {	    return this.followuser;	}	public void setFollowuser(String followuser) {	    this.followuser=followuser;	}	public Date getFollowdate() {	    return this.followdate;	}	public void setFollowdate(Date followdate) {	    this.followdate=followdate;	}	public String getXfFollowcol() {	    return this.xfFollowcol;	}	public void setXfFollowcol(String xfFollowcol) {	    this.xfFollowcol=xfFollowcol;	}	public Integer getXfFollowcol1() {	    return this.xfFollowcol1;	}	public void setXfFollowcol1(Integer xfFollowcol1) {	    this.xfFollowcol1=xfFollowcol1;	}	public String getResourcename() {	    return this.resourcename;	}	public void setResourcename(String resourcename) {	    this.resourcename=resourcename;	}	public String getResourceurl() {	    return this.resourceurl;	}	public void setResourceurl(String resourceurl) {	    this.resourceurl=resourceurl;	}
}


package com.jeecg.p3.houseaOnline.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>XfOrder:在线选房订单<br>
 * @author chao.hua
 * @since：2017年11月23日 16时32分16秒 星期四 
 * @version:1.0
 */
public class XfOrder implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *	 */	private String houseid;	/**	 *	 */	private BigDecimal houseprice;	/**	 *0未支付定金 1已支付 2签定合同 3 购房成功	 */	private String status;	/**	 *	 */	private String createuser;	/**	 *	 */	private Date createtime;	/**	 *	 */	private String isDelete;	/**	 *	 */	private String payuserid;	/**	 *	 */	private String xfOrdercol;	/**	 *	 */	private Integer xfOrdercol1;	/**	 *	 */	private Date expiretime;	/**	 *	 */	private String payresult;	/**	 *	 */	private Date paytime;	/**	 *	 */	private String paycodeurl;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getHouseid() {	    return this.houseid;	}	public void setHouseid(String houseid) {	    this.houseid=houseid;	}	public BigDecimal getHouseprice() {	    return this.houseprice;	}	public void setHouseprice(BigDecimal houseprice) {	    this.houseprice=houseprice;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getCreateuser() {	    return this.createuser;	}	public void setCreateuser(String createuser) {	    this.createuser=createuser;	}	public Date getCreatetime() {	    return this.createtime;	}	public void setCreatetime(Date createtime) {	    this.createtime=createtime;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public String getPayuserid() {	    return this.payuserid;	}	public void setPayuserid(String payuserid) {	    this.payuserid=payuserid;	}	public String getXfOrdercol() {	    return this.xfOrdercol;	}	public void setXfOrdercol(String xfOrdercol) {	    this.xfOrdercol=xfOrdercol;	}	public Integer getXfOrdercol1() {	    return this.xfOrdercol1;	}	public void setXfOrdercol1(Integer xfOrdercol1) {	    this.xfOrdercol1=xfOrdercol1;	}	public Date getExpiretime() {	    return this.expiretime;	}	public void setExpiretime(Date expiretime) {	    this.expiretime=expiretime;	}	public String getPayresult() {	    return this.payresult;	}	public void setPayresult(String payresult) {	    this.payresult=payresult;	}	public Date getPaytime() {	    return this.paytime;	}	public void setPaytime(Date paytime) {	    this.paytime=paytime;	}	public String getPaycodeurl() {	    return this.paycodeurl;	}	public void setPaycodeurl(String paycodeurl) {	    this.paycodeurl=paycodeurl;	}
}


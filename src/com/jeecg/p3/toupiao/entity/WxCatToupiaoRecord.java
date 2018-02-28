package com.jeecg.p3.toupiao.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxCatToupiaoRecord:<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public class WxCatToupiaoRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *投票记录ID	 */	private String id;	/**	 *活动ID	 */	private String actId;	/**	 *投票IP	 */	private String ip;	/**	 *投票人ID	 */	private String openid;	/**	 *投票人名称	 */	private String nickname;	/**	 *投票人真实名字	 */	private String realname;	/**	 *投票人电话	 */	private String phone;	/**	 *投票人地址	 */	private String address;	/**	 *投票时间	 */	private Date voteTime;	/**	 *微信APP ID	 */	private String jwid;	/**	 *已投票的选项	 */	private String optionId;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getIp() {	    return this.ip;	}	public void setIp(String ip) {	    this.ip=ip;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getRealname() {	    return this.realname;	}	public void setRealname(String realname) {	    this.realname=realname;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	    this.phone=phone;	}	public String getAddress() {	    return this.address;	}	public void setAddress(String address) {	    this.address=address;	}	public Date getVoteTime() {	    return this.voteTime;	}	public void setVoteTime(Date voteTime) {	    this.voteTime=voteTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getOptionId() {	    return this.optionId;	}	public void setOptionId(String optionId) {	    this.optionId=optionId;	}
}


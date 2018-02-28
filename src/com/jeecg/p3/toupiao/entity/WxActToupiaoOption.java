package com.jeecg.p3.toupiao.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActToupiaoOption:<br>
 * @author chao.hua
 * @since：2017年06月21日 18时22分53秒 星期三 
 * @version:1.0
 */
public class WxActToupiaoOption implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *投票选项ID	 */	private String id;	/**	 *选项所属投票活动ID	 */	private String actId;	/**	 *选项编号	 */	private Integer identifier;	/**	 *选项名称	 */	private String title;	/**	 *选项图片	 */	private String image;	/**	 *选项视频	 */	private String video;	/**	 *选项描述	 */	private String description;	/**	 *选项链接地址	 */	private String link;	/**	 *选项是否启用 0为不启用 1为启用	 */	private String isActive;	/**	 *是否已删除 0 未删除 1 已删除	 */	private String isDelete;	/**	 *投票次数	 */	private Integer voteCount;	/**	 *微信APPid	 */	private String jwid;	/**	 *选项创建时间	 */
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	private String tel;
	private String addr;
		private Date creatTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public Integer getIdentifier() {	    return this.identifier;	}	public void setIdentifier(Integer identifier) {	    this.identifier=identifier;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getImage() {	    return this.image;	}	public void setImage(String image) {	    this.image=image;	}	public String getVideo() {	    return this.video;	}	public void setVideo(String video) {	    this.video=video;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getLink() {	    return this.link;	}	public void setLink(String link) {	    this.link=link;	}	public String getIsActive() {	    return this.isActive;	}	public void setIsActive(String isActive) {	    this.isActive=isActive;	}	public String getIsDelete() {	    return this.isDelete;	}	public void setIsDelete(String isDelete) {	    this.isDelete=isDelete;	}	public Integer getVoteCount() {	    return this.voteCount;	}	public void setVoteCount(Integer voteCount) {	    this.voteCount=voteCount;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(Date creatTime) {	    this.creatTime=creatTime;	}
}


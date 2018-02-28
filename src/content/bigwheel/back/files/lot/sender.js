/**
 * 活动消息发送统一接口
 */
(function(){
	var BigData = {};
	BigData.sender = {};
	// 默认为测试环境
	BigData.sender.domain = "gametest.weijuju.com";
	
	if(location.hostname == "youyu.365huaer.com" || location.hostname == "youyu.weijuju.com" || location.hostname == "www.365huaer.com"
		|| location.hostname == "www.weijuju.com" || location.hostname == "xvote.365huaer.com" ||  location.hostname == "vote.youyushop.com"
			 || location.hostname == "yvote.365huaer.com" 
			 || /[\d]\.m\.365huaer\.com/.test(location.hostname) //有娱手机端
			 || /[\d]\.jm\.365huaer\.com/.test(location.hostname) //俱聚手机端
			 || /[\d]\.jm\.youyushop\.com/.test(location.hostname) //俱聚投票
			 || /[\d]\.m\.youyushop\.com/.test(location.hostname)
			 || /[\d]\.jm\.huaerhaokan\.com/.test(location.hostname)
	){
		// 生产环境
		BigData.sender.domain = "youyu.weijuju.com";
	}
	
	var isJsonp = BigData.sender.domain!==location.hostname;
	/**
	 * 用户访问消息，用于统计 PV 和 UV
	 * 
	 * 参数样例
	 * {
	 * 	   uid :  1, // 平台用户 uid，必填
	 *     busiPlatform:  2,  // 平台类型，必填
	 *     busiType:  20001,  // 活动类型，必填
	 *     busiId:  88,      // 活动id，必填
	 *     openid: '123456', // 当前用户的openid，必填
	 *     fromOpenid: '123', //来源者的openid
	 *     time: '2015-03-08 11:11:11', // 时间，必填
	 *     nickName: '小明', // 访问者的微信昵称
	 *     fromNickName: '小张', // 来源者的微信昵称
	 *     headimg: 'xxx.jpg', // 访问者的微信头像,
	 *     area: '广东省 深圳市 南山区', // 地区， 中间以一个空格分开，如果没有，设置为 ""
	 *     gender: 1, // 性别，跟微信保持一致，没有的话设置为 0 ， 表示未知
	 *     ip: '192.168.1.1', // 访问者的ip,
	 *     ext1: '', // 扩展字段1
	 *     ext2: '', // 扩展字段2
	 *     ...
	 *     ext15: '' // 扩展字段15
	 * }
	 */
	BigData.sender.saveVisit = function(data){
		try{
			if(isJsonp){
				$.getJSON(buildSendUrl("/bigdata/message/sender/saveVisit.do?"+$.param(data)+"&jsoncallback=?"), function(data){
				});
			}else{
				$.get(buildSendUrl("/bigdata/message/sender/saveVisit.do?"+$.param(data)));
			}
		}catch(error){
			
		}
	}
	/**
	 * 点击关注链接 消息
	 * 
	 * 参数样例
	 * {
	 * 	   uid :  1, // 平台用户 uid，必填
	 *     busiPlatform:  2,  // 平台类型，必填
	 *     busiType:  20001,  // 活动类型，必填
	 *     busiId:  88,      // 活动id，必填
	 *     openid: '123456', // 当前用户的openid，必填
	 *     fromOpenid: '123', //来源者的openid
	 *     time: '2015-03-08 11:11:11', // 时间，必填
	 *     nickName: '小明', // 当前用户的微信昵称
	 *     fromNickName: '小张', // 来源者的微信昵称
	 *     headimg: 'xxx.jpg', // 当前用户的的微信头像
	 *     ip: '192.168.1.1', // 访问者的ip,
	 *     ext1: '', // 扩展字段1
	 *     ext2: '', // 扩展字段2
	 *     ...
	 *     ext15: '' // 扩展字段15
	 * }
	 */
	BigData.sender.saveFollowClick = function(data){	
		try{
			if(isJsonp){
				$.getJSON(buildSendUrl("/bigdata/message/sender/saveFollowClick.do?"+$.param(data)+"&jsoncallback=?"), function(data){
				});
			}else{
				$.get(buildSendUrl("/bigdata/message/sender/saveFollowClick.do?"+$.param(data)));
			}
		}catch(error){
			
		}
	}
	/**
	 * 用户分享消息
	 * 
	 * 参数样例
	 * {
	 * 	   uid :  1, // 平台用户 uid，必填
	 *     busiPlatform:  2,  // 平台类型，必填
	 *     busiType:  20001,  // 活动类型，必填
	 *     busiId:  88,      // 活动id，必填
	 *     openid: '123456', // 当前用户的openid，必填
	 *     fromOpenid: '123', //来源者的openid
	 *     time: '2015-03-08 11:11:11', // 时间，必填
	 *     nickName: '小明', // 当前用户的微信昵称
	 *     fromNickName: '小张', // 来源者的微信昵称
	 *     headimg: 'xxx.jpg', // 当前用户的的微信头像
	 *     ip: '192.168.1.1', // 访问者的ip,
	 *     shareType: 0,  // 分享类型, 未知: 0  微信直接分享：1  微信朋友圈分享: 2  
	 *     ext1: '', // 扩展字段1
	 *     ext2: '', // 扩展字段2
	 *     ...
	 *     ext15: '' // 扩展字段15
	 * }
	 */
	BigData.sender.saveUserShare = function(data){		
		try{
			if(isJsonp){
				$.getJSON(buildSendUrl("/bigdata/message/sender/saveUserShare.do?"+$.param(data)+"&jsoncallback=?"), function(data){
					
				});
			}else{
				$.get(buildSendUrl("/bigdata/message/sender/saveUserShare.do?"+$.param(data)));
			}
		}catch(error){
			
		}
	}
	
	function buildSendUrl(url){
		return "http://" + BigData.sender.domain + url;
	}
	
	// 对外接口
	window.BigData = BigData;
})();
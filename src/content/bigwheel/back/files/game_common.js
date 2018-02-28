if(typeof(YYLoading) === "undefined"){
	YYLoading = {
		updatePercent:function(){},
		finishLoading:function(){},
		show:function(){},
		hide:function(){}
	};
}
(function (doc, win) {
	var docEl = doc.documentElement,
	resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
			recalc = function () {
		var clientWidth = docEl.clientWidth;
		if (!clientWidth) return;
		if(clientWidth > 800) clientWidth=800;
		docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';
	};
	
	if (!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	//doc.addEventListener('DOMContentLoaded', recalc, false);
	recalc();
})(document, window);
//对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "H+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}
var getAwardStatu = function(a) {
	a = parseInt(a);
	switch (a) {
	case -1:
		return "未领取";
	case 0:
		return "未领取";
	case 1:
		return "已领取，待兑换";
	case 2:
		return "已过期";
	case 3:
		return "已使用";
	case 4:
		return "已回收";
	case 5:
		return "已回收";
	case 6:
		return "已手动派发";
	}
};
var changeAwardStatu = function(a,awardtype) {
	a = parseInt(a);
    awardtype = parseInt(awardtype);
	switch (a) {
	case -1:
	case 0:
		return "status0.png?v=1";
	case 1:
		switch (awardtype){
			case 1: return 'status11.png?v=1';
			case 2: return 'status12.png?v=1';
		}
	case 2:
		return "status2.png?v=1";
	case 3:
		return "status3.png?v=1";
	case 4:
	case 5:
		return "status4.png?v=1";
	}
};
$.htmlEncode = function(e) {
	if(e){
		return e.replace(/&/g, "&amp;").replace(/ /g, "&nbsp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\n/g, "<br />").replace(/"/g, "&quot;").replace(/'/g, "&#39;");
	}
};

$.htmlDecode = function(e) {
	if(e){
		return e.replace(/&#39;/g, "'").replace(/<[Bb][Rr]\s*(\/)?\s*>/g, "\n").replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&quot;/g, '"').replace(/&amp;/g, "&");
	}
};

$.htmlFilter = function(e){
	return e==null?"" : e.replace(/<\s*\/?[^>]*\s*>/g,'').replace(/"/g , '');
}
$.tinyWxHeadImg = function(headImg){
	if(headImg == null) return "";
	var headLength = headImg.length;
	if(headImg.substring(headLength-2,headLength) == "/0"){
		headImg = headImg.substring(0,headLength-2)+"/96";
	}
	return headImg;
}
$.ajaxSetup({
	contentType: "application/x-www-form-urlencoded; charset=utf-8",
	type: 'post',
	dataType:"json",
	timeout: 30000,
	complete: function(xhr){
		window.game.loadingClip && window.game.loadingClip.hide();
		window.game.loadingCover && window.game.loadingCover.hide();
		//loading.fadeOut();
	},
	error: function(XMLHttpRequest, textStatus, errorThrown){
		var ua = navigator.userAgent.toLowerCase(), s,
        bom =  (s = ua.match(/msie ([\d.]+)/)) ? "ie:" + s[1] :
           (s = ua.match(/firefox\/([\d.]+)/)) ? "firefox:" + s[1] :
           (s = ua.match(/chrome\/([\d.]+)/)) ? "chrome:" + s[1] :
           (s = ua.match(/opera.([\d.]+)/)) ? "opera:" + s[1] :
           (s = ua.match(/version\/([\d.]+).*safari/)) ? "safari:" + s[1] : 0;
        var responseText = XMLHttpRequest.responseText;
        if(responseText && responseText.length > 180){
        	responseText = responseText.substring(0,180);
        }
		var msg = "responseText："+responseText+"\ntextStatus："+textStatus+"\nerrorThrown:"+errorThrown
		  + "\nXMLHttpRequest.readyState:" + XMLHttpRequest.readyState + "\nXMLHttpRequest.status:" + XMLHttpRequest.status;
		if(textStatus=="timeout"){
			//alert("timeout");
		} else {
			//alert(msg);
		}
        var errImg = new Image();
        var errImgSrc = "/mobile/newgame/jserror?errorMessage="+msg;
        errImgSrc += "&scriptURI="+encodeURIComponent(window.location.href);
        errImgSrc += "&lineNumber=0";
        errImgSrc += "&columnNumber=0";
        errImgSrc += "&errorObj="+encodeURIComponent(this.url+"?"+this.data)+"|"+openId;
        errImgSrc += "&bowserType="+ua;
        errImgSrc += "&type=1";
        errImgSrc += "&_="+new Date().getTime();
        errImg.src = errImgSrc;
        errImg.imageSrcKey = errImgSrc;
		window.game.loadingClip.hide();
		window.game.loadingCover.hide();
		YYLoading && YYLoading.finishLoading();
	}
});
//游戏通用方法
window.game = {
	config:{},		
	activity:{},	
	userPlayinfo:{},
	sendRuleMap:{},
	rankPage:{
		start:0,
		limit:20
	},
	startPage:null,
	gamePage:null,
	fireSuccMask:null,
	fireFailMask:null,
	firePrizeSuccMask:null,
	firePrizeFailMask:null,
	fireEndMask:null,
	nochanceMask:null,
	awardUserInfoMask:null,
	participateUserInfoMask:null,
	qrcodeMask:null,
	ruleMask:null,
	rankMask:null,
	
	/**
	 * 内部初始化
	 */
	_init:function(){
		var _self = this;
		window.game.danmuMask=$(".start_page");
		window.game.startPage=$('.start_page');
		window.game.gamePage=$('.game_page');
		window.game.awardHelpPage=$('.award_help_page');
		window.game.fireSuccMask=$('.fire_success');
		window.game.fireLoadingMask=$('.fire_loading');
		window.game.fireFailMask=$('.fire_fail');
		window.game.fireEndMask=$('.fire_end');
		window.game.firePrizeSuccMask=$('.fire_prize_success');
		window.game.firePrizeHbSuccMask=$('.fire_prize_hb_success');
		window.game.firePrizeFailMask=$('.fire_prize_fail');
		window.game.nochanceMask=$('.fire_nochance');
		window.game.awardUserInfoMask=$('.fire_award_userinfo');
		window.game.awardVerifyMask=$('.fire_award_verify');
		window.game.awardPhoneVerifyMask=$('.fire_award_phone_verify');
		window.game.awardAttentionVerifyMask=$('.fire_award_attention_verify');
		window.game.participateUserInfoMask=$('.fire_participate_userinfo');
		window.game.verifyPwdMask=$('.fire_verify_pwd');
		window.game.verifyPwdResultMask=$('.fire_verify_pwd_result');
		window.game.qrcodeMask=$('.fire_qrcode');
		window.game.ruleMask=$('.fire_rule');
		window.game.awardInfoMask=$('.fire_award_info');
		window.game.hbInfoMask=$('.fire_hbres');
		window.game.lotteryMask=$('.fire_lotto');
		window.game.exchangeMask=$('.fire_exchange');
		window.game.userinfoMask=$('.fire_userinfo');
		window.game.rankMask=$('.fire_rank');
		window.game.loadingCover=$('.mp_loading_cover');
		window.game.loadingClip=$('.mp_loading_clip');
		// 如果是用于PC端活动配置页面展示则不需要绑定元素事件
		if(!_manage){
			$("#youyu_qrcode_img").hide();
			$(".fire_res").find(".close").off("touchend").on("touchend",function(){
				$(this).closest(".fire_res").hide();
				return false;
			});
			if(!window.skipLoadingTimer && window.hasLoadLoadingBanner){
				// 游戏加载页面跳过事件
				var skipCount = 3;
				window.skipLoadingTimer = setInterval(function(){
					skipCount--;
					if(skipCount <= 0){
						window.game._hideFireLoading();
						window.clearInterval(window.skipLoadingTimer);
					}
					window.game.fireLoadingMask.find(".skip_count").text(skipCount);
				},1000);
			}
			$(".fire_loading .skip_btn").off("touchend").on("touchend",function(){
				//window.game.fireLoadingMask.addClass("close");
				window.game._hideFireLoading();
				window.clearInterval(window.skipLoadingTimer);
				return false;
			});
			// 规则弹出框页签点击事件
			$(".rule_myprize_link").off("touchend").live("touchend",function(){
                $(".rule_rule_link").removeClass('active');
                $(".rule_myprize_link").addClass('active');
				$(".fire_res_rule_bg").addClass("fire_res_rule_myprize_bg");
				$("#ruleWrapper").hide();
				$("#myprizeWrapper").show();
				window.game.showMyPrize();
			});
			$(".rule_rule_link").off("touchend").on("touchend",function(){
                $(".rule_myprize_link").removeClass('active');
                $(".rule_rule_link").addClass('active');
				$(".fire_res_rule_bg").removeClass("fire_res_rule_myprize_bg");
				$("#ruleWrapper").show();
				$("#myprizeWrapper").hide();
				window.game.showRule();
			});
			// 排行榜弹出框页签点击事件
			$(".rank_prizerecord_link").off("touchend").on("touchend",function(){
				$('.rank_rank_link').removeClass('active');
				$('.rank_prizerecord_link').addClass('active');
				$(".fire_res_rank_bg").addClass("fire_res_rank_prizerecord_bg");
				$("#rankWrapper").hide();
				$("#prizeRecordWrapper").show();
				window.game.showPrizeList();
			});
			$(".rank_rank_link").off("touchend").on("touchend",function(){
                $('.rank_prizerecord_link').removeClass('active');
                $('.rank_rank_link').addClass('active');
				$(".fire_res_rank_bg").removeClass("fire_res_rank_prizerecord_bg");
				$("#rankWrapper").show();
				$("#prizeRecordWrapper").hide();
				window.game.showRank();
			});
			$(".rank_link").off("touchend").on("touchend",function(){
				window.game.showRank($(this).closest(".mask"));
			});
			// 中奖后点击查看奖品详情
			
			 $(".unlock_prize_link").off("touchend").on("touchend",function(){
			 	window.game.loadUnLockAward(window.awardRecord);
			 });
			// 我的奖品列表中奖品点击事件
			$(".myprize_list_cont").on("touchend",".myprize_item",function(){
				var record = $(this).data("record");
				var fastReturn = false;
				window.awardRecord = record;
				if(record.status == -1){
					window.game.alert("奖品未领取");
					fastReturn = true;
				}
				if(record.status == 0){
					window.game.alert("奖品未兑换");
					fastReturn = true;
				}
				if(record.status == 4 || record.status == 5){
					window.game.alert("奖品已回收");
					fastReturn = true;
				}
				if(fastReturn){
					// 点击奖品列表项的扩展点，如果自定义了扩展点，并且返回了false，则不再执行后续动作
					var prizelistClickFn = window.__custom? window.__custom['prizelist.click'] : null;
					if(prizelistClickFn && typeof prizelistClickFn == 'function'){
						var customResult = prizelistClickFn.call(this , record);
						if(customResult === false){
							return ;
						}
					}
					window.game.loadUnLockAward(record);
					return;
				}
				
				console.log(record);
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				// 判断是否微信卡券类型，如果是，则调用微信JS API弹出卡券详情页面
				if(record.awardtype != 2 && record.wxBack){
					window.game.showWxCardPage(window.game.config.wuid,record.snCode,record.wxBack);
					return;
				}
				var submitData = {
					//"aid":params.aid,
					"arid":record.id,
					"awardid":record.awardid,
					//"wuid":window.game.config.wuid,
					"activityid":params.activityid
					//,"keyversion":window.game.config.keyversion
				};
				$.ajax({
					//动态
					url: serverroot + '/bigwheel/getAwardVerifyQrcode.html',
					data: submitData,
					success: function(data){
						   //data =  eval('(' + data + ')');
						   console.log(333);
						   console.log(data);
					   if(data.retCode == 0){
						   var qrcode_url = data.model.qrcode_url;
						   var award = data.model.award;
						   window.serviceTel = award.serviceTel;
						   if(record.awardtype == 2){
							   window.game.hbInfoMask.show();  
							   window.game.hbInfoMask.find(".money").text((record.awardValue/100).toFixed(2));
							   window.game.hbInfoMask.find(".wxname").text(window.game.activity.wxname);
							   window.game.hbInfoMask.find(".topheader").attr("src",$.tinyWxHeadImg(record.headPic));
							   //window.game.hbInfoMask.find(".codeOptInfo").text(award.operationtip);
							   // window.game.hbInfoMask.find(".tel").text(award.serviceTel);
							  if(award.serviceTel==""){
								   	if(window.qianxi.isQianxiAct){
								   		window.game.hbInfoMask.find(".tel").attr('href','tel:'+window.qianxi.qxAccount);
								   		window.game.hbInfoMask.find(".tel").text(window.qianxi.qxAccount);
								   	}else{
								   		window.game.hbInfoMask.find('.tel').closest('.itemList').hide();
								   	}
							   }else{
								   window.game.hbInfoMask.find(".tel").attr('href','tel:'+award.serviceTel);
								   if(window.qianxi.isQianxiAct){
								   		window.game.hbInfoMask.find(".qxAwardVerifyLinkBtn").attr('href','tel:'+award.serviceTel);
								   		window.game.hbInfoMask.find(".tel").text(award.serviceTel);
								   }
							   }
							   window.game.hbInfoMask.find(".notice").text(award.description);
						   } else {
							   window.game.awardInfoMask.find(".awardName").text(record.trophyName);
							   console.log(record.validityStart);
							   window.game.awardInfoMask.find(".codeBgTime").text(new Date(record.validityStart.time).Format("yyyy年MM月dd日"));
							   window.game.awardInfoMask.find(".codeEndTime").text(new Date(record.validityStop.time).Format("yyyy年MM月dd日"));
							   window.game.awardInfoMask.find(".code").text(record.snCode);
							   window.game.awardInfoMask.find(".codeImg").attr("src",serverroot+"/qrcode/getQRCode.do?url="+qrcode_url);
							   if (+record.status === 3) {
								   window.game.awardInfoMask.find(".codeStatu").addClass("j-write-off");
							   }
							   window.game.awardInfoMask.find(".awardSubTitle").text(award.subhead);
							   window.game.awardInfoMask.find(".codeOptInfo").text(award.operationtip);
							   console.log(award);
							   window.game.awardInfoMask.find(".address").text(award.storeaddress);
							   // window.game.awardInfoMask.find(".tel").text(award.serviceTel);
							   if(award.serviceTel==""){
								   	if(window.qianxi.isQianxiAct){
								   		window.game.awardInfoMask.find(".tel").attr('href','tel:'+window.qianxi.qxAccount);
								   		window.game.awardInfoMask.find(".tel").text(window.qianxi.qxAccount);
								   	}else{
								   		window.game.awardInfoMask.find('.tel').closest('.itemList').hide();
								   	}
							   }else{
								   window.game.awardInfoMask.find(".tel").attr('href','tel:'+award.serviceTel);
								   window.game.awardInfoMask.find(".tel").text(award.serviceTel);
								   if(window.qianxi.isQianxiAct){
								   		window.game.awardInfoMask.find(".qxAwardVerifyLinkBtn").attr('href','tel:'+award.serviceTel);
								   		window.game.awardInfoMask.find(".tel").text(award.serviceTel);
								   }
							   }
							   window.game.awardInfoMask.find(".notice").text(award.description);
							   if(record.status == 1){
								   window.game.awardInfoMask.find(".awardVerifyPwd").show();
							   } else {
								   window.game.awardInfoMask.find(".awardVerifyPwd").hide();
							   }
							   
							   // 跳转按钮										   
							   if(award.custom){
								   var _custom = $.parseJSON(award.custom);
								   if(_custom && _custom.guanwanJumpBtn){
									   if(_custom.guanwanJumpBtn == "true"){
										   $("#jumpBtn").attr("href",_custom.jumpLink).show().find(".text").text(_custom.jumpBtnName); 
									   }
								   }
							   }
							
							   window.game.awardInfoMask.show();  
						   }
					   } else {
						   window.game.alert(data.message);
					   }
					}
				});
			});
			// 绑定奖品详情的详情按钮
			window.game.awardInfoMask.find(".awardDetailBtn").on("touchend",function(){
				$(this).next(".awardDetailText").toggle();
			});
			// 绑定奖品详情的密码核销按钮
			window.game.awardInfoMask.find("#awardVerifyPwdBtn").on("touchend",function(){
				window.game.verifyPwdMask.show();
			});
			// 绑定红包奖品详情的详情按钮
			window.game.hbInfoMask.find(".awardDetailBtn").on("touchend",function(){
				$(this).next(".awardDetailText").toggle();
			});
			// “再玩一次”链接点击事件
			$(".playagain_link").off("touchend").on("touchend",function(){
				if(window.gamepage && window.gamepage.restartGame){
					$(".start_btn").trigger("click",{isRestartGame:true});
				} else {
					window.game.reload();
				}
			});
			// “回到首页”链接点击事件
			$(".goback_link").off("touchend").on("touchend",function(){
				window.game.reload();
			});
			// 助力者参与并抽完奖后显示“我要参与”的按钮
			if(window.game.config.fromOpenid && window.game.config.fromOpenid != window.game.config.openid){
				$(".join_play_link").removeClass("hide");
			}
			// “我要参与”链接点击事件
			$(".join_play_link").off("touchend").on("touchend",function(){
				// window.location.href=window.game.activity.wxlink;
                if(window.isAttent){ //关注了
                    window.location.href = window.mobileUrl;
                }else{ //没有关注
                    window.location.href = window.game.activity.wxlink;
                }
			});

			
			window.game.fireSuccMask.find(".link_lottery").on("touchend",function(){
				window.game.showLottoPage();
			});
			
			// 奖品核销密码输入框保存
			window.game.verifyPwdMask.find(".btn_save").on('touchend',function(){
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $pwd = $("input[name='verfiy_pwd']",window.game.verifyPwdMask);
				if($pwd.val() == ""){
					window.game.alert("请输入核销密码",function(){
						$pwd.focus();
					});
					return false;
				}
				$btn.addClass("disabled");
				var submitData = {
					//"aid":params.aid,
					"activityid":params.activityid,
					//"wuid":window.game.config.wuid,
					"recordid":window.awardRecord.id,
					"sncode":window.awardRecord.snCode,
					//"keyversion":window.game.config.keyversion,
					"verifyPwd":$pwd.val()
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: serverroot + '/bigwheel/verifyaward.html',
					data: submitData,
					success: function(data){
						  // data =  eval('(' + data + ')');
					   if(data.retCode == 0){
						   window.game.verifyPwdMask.hide();
						   window.game.verifyPwdResultMask.show().find(".p_tips_text").text("核销成功");
						   window.game.verifyPwdResultMask.find(".btn_save").off("touchend").on('touchend',function(){
							   window.game.reload();
						   });
					   } else {
						   window.game.verifyPwdMask.hide();
						   window.game.verifyPwdResultMask.show().find(".p_tips_text").text(data.message);
						   window.game.verifyPwdResultMask.find(".btn_save").off("touchend").on('touchend',function(){
							   window.game.verifyPwdResultMask.hide();
						   });
						   //window.game.alert(data.message);
					   }
					   $btn.removeClass("disabled");
					}
				});
			});
			
			// 领取奖品前填写个人信息保存
			$('.fire_award_userinfo .btn_save').on('touchend',function(){
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $validcode = $("input[name='validcode']",window.game.awardUserInfoMask);
				var $phone = $("input[name='phone']",window.game.awardUserInfoMask);
				if($phone.val() == ""){
					window.game.alert("请输入手机号码",function(){
						$phone.focus();
					});
					return false;
				}
				var regu =/^[0-9]{8,20}$/;
				var re = new RegExp(regu); 
				if(!re.test($phone.val())){
					window.game.alert("请输入正确的手机号码",function(){
						$phone.focus();
					});
					return;
				}
				if($validcode.val() == ""){
					window.game.alert("请输入图片验证码",function(){
						$validcode.focus();
					});
					return false;
				}
				$btn.addClass("disabled");
				var submitData = {
						"aid":params.aid,
						"activityid":params.activityid,
						"wuid":window.game.config.wuid,
						"keyversion":window.game.config.keyversion,
						"phonenum":$phone.val()
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url:serverroot+'/bigwheel/collect_user_info.html',
					data: submitData,
					success: function(data){
						 //  data =  eval('(' + data + ')');
						if(data.retCode == 0){
							window.game.alert("提交成功");
							window.game.reload();
						} else {
							window.game.alert(data.message);
						}
						$btn.removeClass("disabled");
					}
				});
			});
			// 奖品核销前填写个人信息保存
			$('.fire_award_verify .btn_save').on('touchend',function(){
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $validcode = $("input[name='validcode']",window.game.awardVerifyMask);
				var $phone = $("input[name='phone']",window.game.awardVerifyMask);
				var $address = $("input[name='address']",window.game.awardVerifyMask);
				var $username = $("input[name='username']",window.game.awardVerifyMask);
				var $expiryDetail = $("input[name='expirydetail'],select[name='expirydetail']",window.game.awardVerifyMask);
				// var $expiryDetail = $("input[name='expirydetail']",window.game.awardVerifyMask);
				// if($expiryDetail.size() == 0){
				// 	$expiryDetail = $("select[name='expirydetail']",window.game.awardVerifyMask);
				// }
				if(!$username.is(":hidden") && $username.val() == ""){
					window.game.alert("请输入领奖人姓名",function(){
						$username.focus();
					});
					return false;
				}
				if(window.game.activity.exchangelimit){
					if($phone.val() == ""){
						window.game.alert("请输入手机号码",function(){
							$phone.focus();
						});
						return false;
					}
					var regu =/^[0-9]{8,20}$/;
					var re = new RegExp(regu); 
					if(!re.test($phone.val())){
						window.game.alert("请输入正确的手机号码",function(){
							$phone.focus();
						});
						return;
					}
				}
				if(!$address.is(":hidden") && $address.val() == ""){
					var addressTip = "请输入领奖地址";
					if(window.game.activity.uid==292634 && window.game.activity.activityId==36858){
						addressTip = "请输入您的公司名称（与营业执照一致）";
					} 
					window.game.alert(addressTip,function(){
						$address.focus();
					});
					return false;
				}
				/*if(!$expiryDetail.is(":hidden") && $expiryDetail.val() == ""){
					window.game.alert("请输入其他必填信息",function(){
						$expiryDetail.focus();
					});
					return false;
				}*/
				for(var i = 0;i<$expiryDetail.length;i++){
					if(!$expiryDetail.eq(i).is(":hidden") && $expiryDetail.eq(i).val() == ""){
						window.game.alert("请输入其他必填信息",function(){
							$expiryDetail.eq(i).focus();
						});
						return false;
					}
				}
				if($validcode.val() == ""){
					window.game.alert("请输入验证码",function(){
						$validcode.focus();
					});
					return false;
				}
				$btn.addClass("disabled");
				var submitData = {
					"aid":params.aid,
					"activityid":params.activityid,
					"wuid":window.game.config.wuid,
					"randCode":$validcode.val(),
					"awardrecordid":window.awardRecord.id,
					"keyversion":window.game.config.keyversion
				};
				if(window.game.activity.exchangelimit){
					submitData.phonenum = $phone.val();
					submitData.name = $username.val();
					submitData.address = $address.val();
				}
				/*if(!$expiryDetail.is(":hidden")){
					var expiryData = {
						"fieldname":$expiryDetail.attr("data-fieldname"),
						"fieldvalue":$expiryDetail.val()
					};
					submitData.expiryDetail = $.toJSON(expiryData);
				}*/
				if($expiryDetail.length){
					var expiryData = {};
					for(var i = 0;i<$expiryDetail.length;i++){
						if(!$expiryDetail.eq(i).is(":hidden")){
							expiryData[$expiryDetail.eq(i).attr("data-fieldname")] = $expiryDetail.eq(i).val()
						}
					}
					submitData.expiryDetail = $.toJSON(expiryData);
				}
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: '/mobile/participation/checkverifycode',
					data: submitData,
					complete: function(xhr){
					},
					success: function(data){
						if(data.retCode == 0){
							//window.game.reload();
							window.game.loadingClip.show();
							window.game.loadingCover.show();
							$.ajax({
								//动态
								url:serverroot+ '/bigwheel/getAwardVerifyQrcode.html',
								data: {
									//"aid":params.aid,
									"arid":window.awardRecord.id,
									"awardid":window.awardRecord.awardid,
									"activityid":params.activityid
									//,"wuid":window.game.config.wuid,
									//"keyversion":window.game.config.keyversion
								},
								success: function(data){
									  // data =  eval('(' + data + ')');
									   console.log(677);
									   console.log(data);
								   if(data.retCode == 0){
									   
									   // 新版摇一摇红包特殊处理——跳到红包获奖列表
									   if(_gameTemplateId == 40){
										   $(".fire_award_verify").hide();
										   window.game.loadAwardRecordList(window.awardRecord);
										   return;
									   }
									   // 如果需要助力领奖，填写完验证码之后刷新页面，出现分享提示
									   if(window.game.config.isHelpAward){
										   if(window.isAfterVerifyForceReload){
											   window.game.reload();
										   }else{
											   window.awardRecord.checked = true;
											   window.game.awardVerifyMask.hide();
											   window.game.loadUnLockAward(window.awardRecord);
										   }
										   return;
									   }
									   var record = window.awardRecord;
									   var qrcode_url = data.model.qrcode_url;
									   var award = data.model.award;
									   window.serviceTel = award.serviceTel;
									   if(record.awardtype == 2){
										   window.game.hbInfoMask.find(".close").off("touchend").on("touchend",function(){
											   window.game.reload();
										   });
										   window.game.hbInfoMask.show();  
										   window.game.hbInfoMask.find(".money").text((record.awardValue/100).toFixed(2));
										   window.game.hbInfoMask.find(".wxname").text(window.game.activity.wxname);
										   window.game.hbInfoMask.find(".topheader").attr("src",$.tinyWxHeadImg(record.headPic));
										   //window.game.hbInfoMask.find(".codeOptInfo").text(award.operationtip);
									       // window.game.hbInfoMask.find(".tel").text(award.serviceTel);		
									       if(award.serviceTel==""){
											   	if(window.qianxi.isQianxiAct){
											   		window.game.hbInfoMask.find(".tel").attr('href','tel:'+window.qianxi.qxAccount);
											   		window.game.hbInfoMask.find(".tel").text(window.qianxi.qxAccount);
											   	}else{
											   		window.game.hbInfoMask.find('.tel').closest('.itemList').hide();
											   	}
										   }else{
											   window.game.hbInfoMask.find(".tel").attr('href','tel:'+award.serviceTel);
											   if(window.qianxi.isQianxiAct){
											   		window.game.hbInfoMask.find(".qxAwardVerifyLinkBtn").attr('href','tel:'+award.serviceTel);
											   		window.game.hbInfoMask.find(".tel").text(award.serviceTel);
											   }
										   }							
										   window.game.hbInfoMask.find(".notice").text(award.description);
									   } else {
										   // 判断是否微信卡券类型，如果是，则调用微信JS API弹出卡券详情页面
										   if(record.wxBack){
											   window.game.showWxCardPage(window.game.config.wuid,record.snCode,record.wxBack);
											   return;
										   }
										   window.game.awardInfoMask.find(".close").off("touchend").on("touchend",function(){
											   window.game.reload();
										   });
										   window.game.awardInfoMask.find(".awardName").text(record.trophyName);
										   window.game.awardInfoMask.find(".codeBgTime").text(new Date(record.validityStart).Format("yyyy.MM.dd"));
										   window.game.awardInfoMask.find(".codeEndTime").text(new Date(record.validityStop).Format("yyyy.MM.dd"));
										   window.game.awardInfoMask.find(".code").text(record.snCode);
										   window.game.awardInfoMask.find(".codeImg").attr("src",serverroot+"/qrcode/getQRCode.do?url="+qrcode_url);
										   window.game.awardInfoMask.find(".awardSubTitle").text(award.subhead);
										   window.game.awardInfoMask.find(".codeOptInfo").text(award.operationtip);
										   window.game.awardInfoMask.find(".address").text(award.storeaddress);
										   // window.game.awardInfoMask.find(".tel").text(award.serviceTel);
										   //如果未填写电话号码则隐藏掉
										   if(award.serviceTel==""){
											    if(window.qianxi.isQianxiAct){
											    	window.game.awardInfoMask.find(".tel").attr('href','tel:'+window.qianxi.qxAccount);
											    	window.game.awardInfoMask.find(".tel").text(window.qianxi.qxAccount);
											    }else{
											    	window.game.awardInfoMask.find('.tel').closest('.itemList').hide();
											    }
										   }else{
											  window.game.awardInfoMask.find(".tel").attr('href','tel:'+award.serviceTel);
											  if(window.qianxi.isQianxiAct){
											  		window.game.awardInfoMask.find(".qxAwardVerifyLinkBtn").attr('href','tel:'+award.serviceTel);
											   		window.game.awardInfoMask.find(".tel").text(award.serviceTel);
											   }
										   }
										   window.game.awardInfoMask.find(".notice").text(award.description);
										   window.game.awardInfoMask.find(".awardVerifyPwd").show();
										   
										   // 跳转按钮										   
										   if(award.custom){
											   var _custom = $.parseJSON(award.custom);
											   if(_custom && _custom.guanwanJumpBtn){
												   if(_custom.guanwanJumpBtn == "true"){
													   $("#jumpBtn").attr("href",_custom.jumpLink).show().find(".text").text(_custom.jumpBtnName); 
												   }
											   }
										   }
										   // 徐福记活动定制
										   if((window.awardRecord.activityId == 20155 || window.awardRecord.activityId == 24904) && window.awardRecord.awardLevel == 2){
												// 绑定奖品详情的密码核销按钮
												window.game.awardInfoMask.find("#awardVerifyPwdBtn").text("立即使用").unbind("touchend").on("touchend",function(){
													window.location.href="http://coupon.m.jd.com/coupons/show.action?key=db9e50b5469a4ff5a843495253f3a447&roleId=5109474&to=xufuji.jd.com";
												});
										   }
										   window.game.awardInfoMask.show();  
									   }
								   } else {
									   window.game.alert(data.message);
								   }
								}
							});
						} else {
							window.game.alert(data.message);
							window.game.loadingClip.hide();
							window.game.loadingCover.hide();
						}
						$btn.removeClass("disabled");
					}
				});
			});
			// 参与游戏前填写个人信息保存
			$('.fire_participate_userinfo .btn_save').on('touchend',function(){
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $validcode = $("input[name='validcode']",window.game.participateUserInfoMask);
				var $phone = $("input[name='phone']",window.game.participateUserInfoMask);
				if($phone.val() == ""){
					window.game.alert("请输入手机号码",function(){
						$phone.focus();
					});
					return false;
				}
				var regu =/^[0-9]{8,20}$/;
				var re = new RegExp(regu); 
				if(!re.test($phone.val())){
					window.game.alert("请输入正确的手机号码",function(){
						$phone.focus();
					});
					return;
				}
				$btn.addClass("disabled");
				var submitData = {
					//"aid":params.aid,
					"activityid":params.activityid,
					//"wuid":window.game.config.wuid,
					//"keyversion":window.game.config.keyversion,
					"phonenum":$phone.val()
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: serverroot+'/bigwheel/collect_user_info.html',
					data: submitData,
					success: function(data){
						  // data =  eval('(' + data + ')');
						if(data.retCode == 0){
							// 游戏开始前填完手机号码后统一刷新页面
							window.game.reload();
						} else {
							window.game.alert(data.message);
						}
						$btn.removeClass("disabled");
					}
				});
			});
			
			// 手机验证码重新发送倒计时
			function resendSmsCodeCountDown(){
				$('.resend_btn',window.game.awardPhoneVerifyMask).addClass("disabled").html("<span class='count_num'>120</span>s后重发");
				var countTime = 120;
				if(window.resendSmsCodeTimer){
					window.clearInterval(window.resendSmsCodeTimer);
				}
				window.resendSmsCodeTimer = window.setInterval(function(){
					if(--countTime < 0){
						window.clearInterval(window.resendSmsCodeTimer);
						$('.resend_btn',window.game.awardPhoneVerifyMask).removeClass("disabled").html("重新获取");
						return;
					}
					$('.resend_btn',window.game.awardPhoneVerifyMask).find(".count_num").text(countTime);
				},1000);
			}
			
			// 奖品核销前验证手机号码（发送手机短信验证码）
			$('.btn_next',window.game.awardPhoneVerifyMask).on('touchend',function(){
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $validcode = $("input[name='validcode']",window.game.awardPhoneVerifyMask);
				var $phone = $("input[name='phone']",window.game.awardPhoneVerifyMask);
				if($phone.val() == ""){
					window.game.alert("请输入手机号码",function(){
						$phone.focus();
					});
					return false;
				}
				var regu =/^[0-9]{8,20}$/;
				var re = new RegExp(regu); 
				if(!re.test($phone.val())){
					window.game.alert("请输入正确的手机号码",function(){
						$phone.focus();
					});
					return;
				}
				if($phone.val().indexOf("14") == 0){
					window.game.alert("暂时不支持虚拟运营商手机号验证",function(){
						$phone.focus();
					});
					return;
				}
				if($validcode.val() == ""){
					window.game.alert("请输入图片验证码",function(){
						$validcode.focus();
					});
					return false;
				}
				$btn.addClass("disabled");
				var submitData = {
					"aid":params.aid,
					"activityid":params.activityid,
					"wuid":window.game.config.wuid,
					"randCode":$validcode.val(),
					"phonenum":$phone.val(),
					"awardrecordid":window.awardRecord.id,
					"keyversion":window.game.config.keyversion
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: '/mobile/participation/sms_check',
					data: submitData,
					success: function(data){
						if(data.retCode == 0){
							//window.game.reload();
							$(".input_cont",window.game.awardPhoneVerifyMask).hide();
							$(".btn_next",window.game.awardPhoneVerifyMask).hide();
							$(".yuyin_tips",window.game.awardPhoneVerifyMask).hide();
							if(window.game.config.securityType){
								$(".p_tips_text",window.game.awardPhoneVerifyMask).text("请输入收到的语音验证码");
							} else {
								$(".p_tips_text",window.game.awardPhoneVerifyMask).text("请输入收到的短信验证码");
							}
							$(".phone_display",window.game.awardPhoneVerifyMask).show();
							$(".code_input_area",window.game.awardPhoneVerifyMask).show();
							var phoneNumLen = $phone.val().length;
							$(".phone_number",window.game.awardPhoneVerifyMask).html("");
							for(var i=0;i<phoneNumLen;i++){
								$(".phone_number",window.game.awardPhoneVerifyMask).append("<span>"+$phone.val().charAt(i)+"</span>");
							}
							resendSmsCodeCountDown();
						} else {
							window.game.alert(data.message);
						}
						$btn.removeClass("disabled");
					}
				});
			});
			// 奖品核销手机号码验证，短信验证码重发按钮
			$('.resend_btn',window.game.awardPhoneVerifyMask).on('touchend',function(e){
				var $btn = $(this);
				if($btn.hasClass("disabled")){
					return false;
				}
				var $phone = $("input[name='phone']",window.game.awardPhoneVerifyMask);
				var submitData = {
					"aid":params.aid,
					"activityid":params.activityid,
					"wuid":window.game.config.wuid,
					"randCode":"8888",
					"phonenum":$phone.val(),
					"awardrecordid":window.awardRecord.id,
					"keyversion":window.game.config.keyversion
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: '/mobile/participation/sms_check',
					data: submitData,
					success: function(data){
						if(data.retCode == 0){
							//window.game.reload();
							resendSmsCodeCountDown();
						} else {
							window.game.alert(data.message);
						}
					}
				});
			});
			// 奖品核销手机号码验证，短信验证码输入框事件绑定
			$('.code_input',window.game.awardPhoneVerifyMask).on('keyup',function(e){
				var $input = $(this);
				var keycode = e.which;
				var isNumber = keycode >= 48 && keycode <= 57;
				if(isNumber){
					$input.val(keycode - 48);
				}
				$input.val($input.val().replace(/[^0-9]/g,''));
				if(keycode == 8 && $input.data("value") == ""){
					$input.parent().prev().find(".code_input").focus();
				} else {
					if($input.val() != ""){
						$input.parent().next().find(".code_input").focus();
					}
					// 是否完成所有验证码的输入，当4个验证码都输入完成后自动提交后台校验
					var hasFinishCodeInput = true;
					var randCode = "";
					$('.code_input',window.game.awardPhoneVerifyMask).each(function(index,item){
						if($(item).val() == ""){
							hasFinishCodeInput = false;
							return false;
						}
						randCode += $(item).val();
					});
					if(hasFinishCodeInput){
						var submitData = {
							"aid":params.aid,
							"activityid":params.activityid,
							"wuid":window.game.config.wuid,
							"randCode":randCode,
							"awardrecordid":window.awardRecord.id,
							"keyversion":window.game.config.keyversion
						};
						window.game.loadingClip.show();
						window.game.loadingCover.show();
						$.ajax({
							//动态
							url: '/mobile/participation/check_telcode',
							data: submitData,
							complete: function(xhr){
							},
							success: function(data){
								if(data.retCode == 0){
									window.game.loadingClip.show();
									window.game.loadingCover.show();
									$.ajax({
										//动态
										url: serverroot+'/bigwheel/getAwardVerifyQrcode.html',
										data: {
											//"aid":params.aid,
											"arid":window.awardRecord.id,
											"awardid":window.awardRecord.awardid,
											"activityid":params.activityid
											//,"wuid":window.game.config.wuid,
											//"keyversion":window.game.config.keyversion
										},
										success: function(data){
											  // data =  eval('(' + data + ')');
											   console.log(1024);
											   console.log(data);
										   if(data.retCode == 0){
											   // 如果需要助力领奖，填写完验证码之后刷新页面，出现分享提示
											   if(window.game.config.isHelpAward){
												   if(window.isAfterVerifyForceReload){
													   window.game.reload();
												   }else{
													   window.awardRecord.checked = true;
													   window.game.awardVerifyMask.hide();
													   window.game.loadUnLockAward(window.awardRecord);
												   }
												   return;
											   }
											   
											   
											   var record = window.awardRecord;
									
											   
											   var qrcode_url = data.model.qrcode_url;
											   var award = data.model.award;
											   window.serviceTel = award.serviceTel;
											   window.game.hbInfoMask.find(".close").off("touchend").on("touchend",function(){
												   window.game.reload();
											   });
											   window.game.hbInfoMask.show();  
											   window.game.hbInfoMask.find(".money").text((record.awardValue/100).toFixed(2));
											   window.game.hbInfoMask.find(".wxname").text(window.game.activity.wxname);
											   window.game.hbInfoMask.find(".topheader").attr("src",$.tinyWxHeadImg(record.headPic));
											   //window.game.hbInfoMask.find(".codeOptInfo").text(award.operationtip);
											   // window.game.hbInfoMask.find(".tel").text(award.serviceTel);
											   if(award.serviceTel==""){
											   		if(window.qianxi.isQianxiAct){
											   			window.game.hbInfoMask.find(".tel").attr('href','tel:'+window.qianxi.qxAccount);
											   			window.game.hbInfoMask.find(".tel").text(window.qianxi.qxAccount);
												   	}else{
											   			window.game.hbInfoMask.find('.tel').closest('.itemList').hide();
												   	}
											   }else{
												   window.game.hbInfoMask.find(".tel").attr('href','tel:'+award.serviceTel);
												   if(window.qianxi.isQianxiAct){
												   		window.game.hbInfoMask.find(".qxAwardVerifyLinkBtn").attr('href','tel:'+award.serviceTel);
												   		window.game.hbInfoMask.find(".tel").text(award.serviceTel);
												   }
											   }
											   window.game.hbInfoMask.find(".notice").text(award.description);
										   } else {
											   window.game.alert(data.message);
										   }
										}
									});
									
								} else {
									window.game.alert(data.message);
									window.game.loadingClip.hide();
									window.game.loadingCover.hide();
								}
							}
						});
					}
				}
				$input.data("value",$input.val());
			});
			
			// “分享”按钮点击事件
			$('[data-role="share"]').on('touchend',function(event,hideInitEl){
				if(window.game.config.shareType != 0){
					window.game.shareHelpTip();
					return;
				}
				var $btn = $(this);
				$(".fire_qrcode .help_qrcode_img").attr("src","");
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					//动态
					url: '/mobile/participation/share',
					data: {
						"aid":window.game.config.aid,
						"activityid":window.game.config.activityid,
						"wuid":window.game.config.wuid,
						"keyversion":window.game.config.keyversion,
						"customParam":window["customParam"]? window["customParam"]: ""
					},
					success: function(data){
						if(data.retCode == 0){
							$(".fire_qrcode .help_qrcode_img").attr("src",data.model.qrcode);
						} else {
							window.game.alert(data.message);
						}
						if(hideInitEl){
							_self._showQrcode();
						} else {
							_self._showQrcode($btn.closest('.fire_res'));
						}
					}
				});
			});
			$('.fire_res .replay').on('touchend',function(){
				_self._replay();
			});
			// 游戏排名
			$('.fire_res .rank').on('touchend',function(){
				_showQrcode($(this).closest('.fire_res'));
				window.game.config.showRank();
			});
			// 底部版权是否自定义跳转
			if(typeof(extendOperation) != "undefined" && extendOperation.copyrightLink){
				$("#start_bottom").attr("href",extendOperation.copyrightLink);
			} else {
				$("#start_bottom").attr("href","http://youyu.weijuju.com/market/toutiao.jsp");
			}
		}
	},
	
	/**
	 * 显示分享二维码
	 */
	_showQrcode:function($initEl){
		$(".mask").hide();
		var $qrcode = window.game.qrcodeMask;
		if($initEl && $initEl.length>0){
			$initEl.hide();			
		}
		$qrcode.show().off('touchend').on('touchend',function(){
			$(this).hide();
			$initEl&&$initEl.show();
		});
		$qrcode.find('img').off('touchend').on('touchend',function(){
			return false;
		});
	},	
	
	/**
	 * 显示分享二维码自定义编辑页面
	 */
	_showQrcodeEdit:function($initEl){
		$(".mask").hide();
		var $qrcode = $("#qrcode_edit_page");
		if($initEl && $initEl.length>0){
			$initEl.hide();			
		}
		$qrcode.show().off('touchend').on('touchend',function(){
			$(this).hide();
			$initEl&&$initEl.show();
		});
		$qrcode.find('img').off('touchend').on('touchend',function(){
			return false;
		});
	},	
	/**
	 * 判断用户是否还有机会玩
	 */
	_verifyPlay:function(callback){
		
	},	
	/**
	 * 显示游戏开始页面
	 */
	_showStartPage:function(hideLoading){
		$(".mask").hide();
		window.game.startPage.show();
		window.game.gamePage.hide();
		window.game.awardHelpPage.hide();
	},
	/**
	 * 显示游戏主页面
	 * notHideLoading:是否不隐藏loading页面
	 */
	_showGamePage:function(notHideLoading){
		if(notHideLoading){
			$(".mask").not(".fire_loading").hide();
		} else {
			$(".mask").hide();
		}
		window.game.gamePage.show();
		window.game.startPage.hide();
		window.game.awardHelpPage.hide();
		// 处理活动未开始提示框
		activitySatusWrong();
	},
	/**
	 * 显示奖品助力页面
	 */
	_showAwardHelp:function(){
		$(".mask").not(".fire_loading").hide();
		window.game.awardHelpPage.show();
		window.game.gamePage.hide();
		window.game.startPage.hide();
	},
	/**
	 * 游戏加载页面弹框
	 */
	_showFireLoading:function(){
		$(".mask").hide();
		window.game.fireLoadingMask.show();
	},
	/**
	 * 隐藏游戏加载页面弹框
	 */
	_hideFireLoading:function(){
		$('.fire_loading').fadeOut(500,function(){
		});
		//手机端游戏开屏页面关闭后才显示开始页面的标题图片和按钮，这样才能看到css动画执行
		$(".start_page .title_img").show();
		$(".start_page #startBtnImg").show();
		// 是否显示弹幕
		var showDanmu = (activityJson.extraData&(Math.pow(2,11))) != 0;
		if(showDanmu && !window.awardRecord){
			window.game.showDanmu();
		}
	},
	/**
	 * 游戏开奖页面弹框
	 */
	_showLottoPage:function(){
		$(".mask").hide();
		window.game.lotteryMask.show();
	},
	/**
	 * 挑战成功弹框
	 */
	_showFireSucc:function(){
		$(".mask").hide();
		window.game.fireSuccMask.show();
	},
	/**
	 * 挑战失败弹框
	 */
	_showFireFail:function(){
		$(".mask").hide();
		window.game.fireFailMask.show();
	},
	/**
	 * 中奖弹框
	 */
	_showFirePrizeSucc:function(){
		$(".mask").hide();
		window.game.firePrizeSuccMask.show();
	},
	/**
	 * 红包中奖弹框
	 */
	_showFirePrizeHbSucc:function(){
		$(".mask").hide();
		window.game.firePrizeHbSuccMask.show();
	},
	/**
	 * 未中奖弹框
	 */
	_showFirePrizeFail:function(){
		$(".mask").hide();
		window.game.firePrizeFailMask.show();
	},
	/**
	 * 挑战结束弹框
	 */
	_showFireEnd:function(){
		$(".mask").hide();
		window.game.fireEndMask.show();
	},
	/**
	 * 中奖填写信息弹框
	 */
	_showAwardUserInfo:function(){
		$(".mask").hide();
		window.game.awardUserInfoMask.show();
	},
	/**
	 * 核销前验证个人信息弹框
	 */
	_showAwardVerify:function(prize){
		$(".mask").hide();
		window.game.awardVerifyMask.find('.p_award_name').text(prize.trophyName);
		if(window.game.activity.exchangelimit){
			// 兑奖前需要填写手机号码
			window.game.awardVerifyMask.find(".phone_cont").show();
			if(window.game.userPlayinfo.openuser.phonenum != null){
				window.game.awardVerifyMask.find("input[name='phone']").val(window.game.userPlayinfo.openuser.phonenum);
			}
			window.game.awardVerifyMask.find(".username_cont").show();
			if(window.game.userPlayinfo.openuser.realname != null){
				console.log(window.game.userPlayinfo.openuser.realname);
				window.game.awardVerifyMask.find("input[name='realname']").val(window.game.userPlayinfo.openuser.realname);
			}
			
			if((activityJson.extraData&(Math.pow(2,2))) != 0 && prize.awardtype != 2){
				// 兑奖前需要填写地址信息
				window.game.awardVerifyMask.find(".address_cont").show();
			}
			if((activityJson.extraData&(Math.pow(2,1))) != 0 && prize.awardtype != 2){
				// 兑奖前需要填写姓名
				window.game.awardVerifyMask.find(".username_cont").show();
			}
		} else if(!activityJson.trophyValidator){
			// 如果关闭领奖验证码，则自动发送验证请求领取奖品
			$(".validcode_cont",window.game.awardVerifyMask).hide();
			$("input[name='validcode']",window.game.awardVerifyMask).val("8888");
			$(".btn_save",window.game.awardVerifyMask).trigger("touchend");
			
		}
		//动态
		window.game.awardVerifyMask.find('.validcode_cont .valid_img').attr('src','/mobile/participation/getRandImg?size=2x&aid='+window.game.config.aid+'&activityid='+window.game.config.activityid+'&awardid='+prize.id+'&keyversion='+window.game.config.keyversion+'&wuid='+window.game.config.wuid+'&t='+new Date().getTime())
		 .off("touchend").on("touchend",function(){
			$(this).attr('src','/mobile/participation/getRandImg?size=2x&aid='+window.game.config.aid+'&activityid='+window.game.config.activityid+'&awardid='+prize.id+'&keyversion='+window.game.config.keyversion+'&wuid='+window.game.config.wuid+'&t='+new Date().getTime());			            								
		  });
		window.game.awardVerifyMask.show();
	},
	/**
	 * 核销前验证手机号码弹框（发送手机验证码）
	 */
	//动态
	_showPhoneAwardVerify:function(prize){
		$(".mask").hide();
		if(window.game.userPlayinfo.openuser.phonenum != null){
			window.game.awardPhoneVerifyMask.find("input[name='phone']").val(window.game.userPlayinfo.openuser.phonenum);
		}
		window.game.awardPhoneVerifyMask.find('.validcode_cont .valid_img').attr('src','/mobile/participation/getRandImg?size=2x&aid='+window.game.config.aid+'&activityid='+window.game.config.activityid+'&awardid='+prize.id+'&keyversion='+window.game.config.keyversion+'&wuid='+window.game.config.wuid+'&t='+new Date().getTime())
		.off("touchend").on("touchend",function(){
			$(this).attr('src','/mobile/participation/getRandImg?size=2x&aid='+window.game.config.aid+'&activityid='+window.game.config.activityid+'&awardid='+prize.id+'&keyversion='+window.game.config.keyversion+'&wuid='+window.game.config.wuid+'&t='+new Date().getTime());			            								
		});
		if(window.game.config.securityType){
			// 语音验证
			window.game.awardPhoneVerifyMask.find(".yuyin_tips").text("验证码将会以语音电话形式发送，请注意接听来电");
		} else {
			// 短信验证
			window.game.awardPhoneVerifyMask.find(".yuyin_tips").text("验证码将会以短信形式发送，请注意接收");
		}
		window.game.awardPhoneVerifyMask.show();
	},
	/**
	 * 核销前验证未关注商家公众号弹出框
	 */
	_showAttentionAwardVerify:function(prize){
		$(".wxname",window.game.awardAttentionVerifyMask).text(window.game.activity.wxname);
		window.game.awardAttentionVerifyMask.show();
	},
	/**
	 * 参与前填写信息弹框
	 */
	_showParticipateUserInfo:function(){
		$(".mask").hide();
		window.game.participateUserInfoMask.show();
	},
	/**
	 * 机会用完的弹框
	 */
	_showFireNochance:function(){
		$(".mask").hide();
		window.game.nochanceMask.show();
	},
	/**
	 * 兑换奖品的弹框
	 */
	_showFireExchange:function(){
		$(".mask").hide();
		window.game.exchangeMask.show();
	},
	/**
	 * 奖品详情的弹框
	 */
	_showAwardInfo:function(){
		$(".mask").hide();
		window.game.awardInfoMask.show();
	},
	/**
	 * 红包奖品详情的弹框
	 */
	_showHbInfo:function(){
		$(".mask").hide();
		window.game.hbInfoMask.show();
	},
	/**
	 * 填写用户信息的弹框
	 */
	_showFireUserInfo:function(){
		$(".mask").hide();
		window.game.userinfoMask.show();
	},
	/**
	 * 显示排名榜单弹出框
	 */
	_showRank:function($initEl,showTab){
		if(showTab === undefined){showTab = true;}
		$(".mask").hide();
		window.game.rankMask.show();
		if(showTab){
			$(".fire_res_rank_bg").removeClass("fire_res_rank_prizerecord_bg");
			$("#rankWrapper").show();
			$("#prizeRecordWrapper").hide();
		}
		if($initEl && $initEl.length>0){
			window.game.rankMask.find(".close").off('touchend').on('touchend',function(){
				window.game.rankMask.hide();
				$initEl&&$initEl.show();
			});
		}
	},
	/**
	 * 显示获奖名单弹出框
	 */
	_showPrizeRecord:function($initEl,showTab){
		if(showTab === undefined){showTab = true;}
		$(".mask").hide();
		window.game.rankMask.show();
		if(showTab){
			$(".fire_res_rank_bg").addClass("fire_res_rank_prizerecord_bg");
			$("#rankWrapper").hide();
			$("#prizeRecordWrapper").show();
		}
		if($initEl && $initEl.length>0){
			window.game.rankMask.find(".close").off('touchend').on('touchend',function(){
				window.game.rankMask.hide();
				$initEl&&$initEl.show();
			});
		}
	},
	/**
	 * 显示活动规则框
	 */
	_showRule:function($initEl,showTab){
		if(showTab === undefined){showTab = true;}
		$(".mask").hide();
		window.game.ruleMask.show();
		if(showTab){
			$(".fire_res_rule_bg").removeClass("fire_res_rule_myprize_bg");
			$("#ruleWrapper").show();
			$("#myprizeWrapper").hide();
		}
		if($initEl && $initEl.length>0){
			window.game.ruleMask.find(".close").off('touchend').on('touchend',function(){
				window.game.ruleMask.hide();
				$initEl&&$initEl.show();
			});
		}
		if(!window.game.myScroll){
			window.game.myScroll = new iScroll('ruleWrapper',{hScrollbar:false,hScroll:false,vScrollbar:true,hideScrollbar:false});
		}
	},
	/**
	 * 显示我的奖品弹出框
	 */
	_showMyPrize:function($initEl,showTab){
		if(showTab === undefined){showTab = true;}
		$(".mask").hide();
		window.game.ruleMask.show();
		if(showTab){
			$(".fire_res_rule_bg").addClass("fire_res_rule_myprize_bg");
			$("#ruleWrapper").hide();
			$("#myprizeWrapper").show();
		}
		if($initEl && $initEl.length>0){
			window.game.ruleMask.find(".close").off('touchend').on('touchend',function(){
				window.game.ruleMask.hide();
				$initEl&&$initEl.show();
			});
		}
	},
	/**
	 * 再玩一次
	 */
	_replay:function(){
		window.game.loadingClip.show();
		window.game.loadingCover.show();
		var submitData = {
			//"aid":window.game.config.aid,
			"activityid":window.game.config.activityid
			//,"wuid":window.game.config.wuid,
			//"keyversion":window.game.config.keyversion
		};
		if(params.isFromApiFilter == 1){
			submitData.isFromApiFilter = 1;
		}
		$.ajax({
			//动态
			url: serverroot+'/bigwheel/verifyplay.html',
			data: submitData,
			type: 'get',
			success: function(data){
				  // data =  eval('(' + data + ')');
			   if(data.retCode == 0){
				   window.game.reload();
			   } else if(data.retCode <= -3403 && data.retCode >= -3405){
				   window.game.nochanceMask.find(".response_text").text(data.message);
				   window.game.nochanceMask.show();
			   } else {
				   window.game.alert(data.message);
			   }
			}
		});
		 
	},
	
	/*************************************以下为对外提供的方法****************************************/
	
	/**
	 * config为初始化配置项（所有均为必填项），initCallback为初始化结束后的回调方法，可在回调里面执行页面的入口代码
	 * config的配置项如下：
	 * {
	 * 	activityId:xx,	 //明文活动id
	 * 	aid:xx,	//密文活动id,
	 * 	startGame:function(){}, //开始游戏方法，用于进入游戏界面
	 * 	replayGame:function(){}, //再玩一次方法，用于重新开始游戏
	 * 	unstartGame:function(){}, //跳到游戏未开始界面
	 * 	endGame:function(){}, //跳到游戏已结束界面
	 * 	gameUrl:"http://...", //游戏首页链接（全链接）
	 * }
	 */
	init : function(config,initCallback) {
		window.game.config=config;
		window.game._init();
	},
	
	/**
	 * 替换图片路径中的皮肤路径占位符
	 */
	replaceResRoot : function(path){
		return path.replace(/\*_resRoot\*/g,_resRoot).replace(/\*_qiniuResRoot\*/g,_qiniuResRoot).replace(/\.\/lot/g,_resRoot);
	},
	
	/**
	 * 页面通过ajax从后台获取init信息后的动作，默认是关闭loading，页面可覆盖这个方法
	 */
	afterInit : function(){
		if(typeof(allImage) === "undefined"){
			YYLoading && YYLoading.finishLoading();
		}
	},
	
	/**
	 * 点击“开始游戏”时调用
	 */
	start:function(){
	},
	
	/**
	 * 游戏结束后调用
	 * @extend 扩展 ，格式  {url:"/" , data:{} , refreshScore:true}
	 */
	score:function(score,leftTime,extend){
		var oldScore = score;
		
		if(window.game.imgTextShare && window.game.imgTextShare.noawardtext){
			share_content = window.game.imgTextShare.noawardtext.replace(/\${nickName}/g,window.game.config.nickname).replace(/\${score}/g,score);
			initShareInfoNew(shareConfig.successCallback);
		}
		window.game.config.score = score;
		window.game.loadingClip.show();
		window.game.loadingCover.show();
		
		if(leftTime!=null && leftTime<0){
			leftTime = 0;
		}
		
		//ajax submit
		var submitData = {
			"aid":params.aid,
			"activityid":params.activityid,
			"wuid":window.game.config.wuid,
			"keyversion":window.game.config.keyversion,
			"score":score,
			"leftTime": leftTime
		};
		
		if(extend && extend.data){
			submitData = $.extend(submitData , extend.data);
		}
		//动态		
		var commitScoreUrl = '/mobile/participation/commitscore';
		if(extend && extend.url){
			commitScoreUrl = extend.url;
		}
		$.ajax({
			url: commitScoreUrl,
			data: submitData,
			success: function(data){
				var refreshScore = (extend && extend.refreshScore===true);
				
				$(".fire_rank .rank_table").data("hasload",false);
				$(".fire_rank .prizelist_table").data("hasload",false);
				score = (+score + (leftTime || 0));
			    if(data.retCode == 0){
			    	//挑战类游戏
			    	if(activityJson.awardSendType == 2){
			    		if(refreshScore && data.model.calScore){
			    			if(window.game.imgTextShare && window.game.imgTextShare.noawardtext){
			    				share_content = window.game.imgTextShare.noawardtext.replace(/\${nickName}/g,window.game.config.nickname).replace(/\${score}/g,data.model.calScore);
			    				initShareInfoNew(shareConfig.successCallback);
			    			}
			    			window.game.config.score = data.model.calScore;
			    			score = data.model.calScore;
			    		}
			    		
			    		var dayLimit = data.model.daylimit+data.model.allow-data.model.curDay;
			    		var actLimit = data.model.actlimit+data.model.allow-data.model.used;
			    		
			    		// 如果当天已玩的次数大于等于每日限制次数，则直接取allow作为每日剩余次数和总剩余次数(by lcp 2017-04-26)
			    		if(data.model.curDay >= data.model.daylimit){
			    			dayLimit = data.model.allow;
			    		}
			    		if(data.model.used >= data.model.actlimit){
			    			actLimit = data.model.allow;
			    		}
			    		// 计算 dayLimit的时候要考虑有没有限制总抽奖机会  (by ctz 2017-03-07 14:59)
			    		if(window.game.activity.limitLottery){
			    			// 今天剩余抽奖次数不能大于总抽奖次数
				    		dayLimit = Math.min(dayLimit,actLimit);
			    		}
			    		
			    		if(dayLimit < 0){
			    			dayLimit = 0;
			    		}
			    		if(data.model.challenge){
			    			window.game.fireSuccMask.find(".score").text(score);
			    			window.game.fireSuccMask.find(".daylimit").text(dayLimit);
			    			window.game.fireSuccMask.find(".actlimit").text(actLimit);
			    		}
			    		// 是否限制参与总次数
			    		if(window.game.activity.limitLottery){
			    			window.game.fireSuccMask.find(".p_text_2 .p_text").html("今天还有<span style='color:#f64f33;'>"+dayLimit+"</span>次，总共还有"+actLimit+"次抽奖机会");
			    		} else {
			    			window.game.fireSuccMask.find(".p_text_2 .p_text").html("今天还有<span style='color:#f64f33;'>"+dayLimit+"</span>次抽奖机会");
			    		}
			    		if(dayLimit <= 0 && window.activityJson.withHelp){
			    			window.game.fireSuccMask.find(".p_text_3 .p_text").text("邀请好友可以获得更多抽奖机会哦！").show();
			    			window.game.fireSuccMask.find(".p_text_3").show();
			    		}
			    		if(data.model.isLottery){
			    			window.game._showFireSucc();
			    		} else {
			    			if(data.model.challenge){
			    				window.game.fireSuccMask.find(".link_lottery").hide();
			    				window.game.fireSuccMask.find(".fire_btn_cont").addClass("nolottery");
			    				window.game._showFireSucc();
			    			} else {
			    				window.game.fireFailMask.find(".score").text(score);
			    				window.game._showFireFail();
			    			}
			    		}
			    	} else if(activityJson.awardSendType == 1){		    		
			    		if(refreshScore && data.model.personInfo.score){
			    			if(window.game.imgTextShare && window.game.imgTextShare.noawardtext){
			    				share_content = window.game.imgTextShare.noawardtext.replace(/\${nickName}/g,window.game.config.nickname).replace(/\${score}/g,data.model.personInfo.score);
			    				initShareInfoNew(shareConfig.successCallback);
			    			}
			    			window.game.config.score = data.model.personInfo.score;
			    			score = data.model.personInfo.score;
			    			
			    			// 打砖块当前分数，由于后台目前没有返回curScore,所以需要特殊处理
			    			if(_gameTemplateId == 56){
				    			window.game.config.score = oldScore + leftTime;
				    			score = oldScore + leftTime;
				    		}
			    		}
			    		
			    		// 排名类游戏
			    		window.game.fireEndMask.find(".score_text").text(score);
			    		var personInfo = data.model.personInfo;
			    		if(personInfo){
			    			window.game.fireEndMask.find(".total_score").text(personInfo.score);
			    			window.game.fireEndMask.find(".rank_text").text(personInfo.ranking);
			    			if(share_content){
			    				share_content = share_content.replace(/\${rank}/g,personInfo.ranking);
			    				initShareInfoNew(shareConfig.successCallback);
			    			}
			    		}
			    		window.game._showFireEnd();
			    	}
			    } else {
				    window.game.alert(data.message);
				    window.game.loadingClip.hide();
					window.game.loadingCover.hide();
			    }
			}
		});
	},
	
	/**
	 * 活动规则弹框
	 */
	showRule:function($initEl){
		window.game._showRule($initEl,false);
		if(!window.game.myScroll){
			window.game.myScroll = new iScroll('ruleWrapper',{hScrollbar:false,hScroll:false,vScrollbar:true,hideScrollbar:false});
		}
	},
	
	/**
	 * 我的奖品弹框
	 */	
	showMyPrize:function($initEl){
		console.log(0);
		var $myPrizeWrapper = $("#myprizeWrapper");
		if($(".fire_rule .myprize_list_cont").data("hasload")){
			console.log(1);
			window.game._showMyPrize($initEl,false);
		} else {
			console.log(2);
			function loadMyPrizeList(){
				if($myPrizeWrapper.data("loading")) return;
				var start = $(".fire_rank .myprize_item").size();
				var submitData = {
					//"aid":params.aid,
					"activityid":params.activityid,
					//"wuid":window.game.config.wuid,
					//"keyversion":window.game.config.keyversion,
					//"platformId":window.game.config.platformId
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$myPrizeWrapper.data("loading",true);
				$.ajax({
					//type: "GET",
					//动态
					url: serverroot+'/bigwheel/awardlist.html',
					data: submitData,
					success: function(data){
						  // data =  eval('(' + data + ')');
						console.log(data);
						$myPrizeWrapper.data("loading",false);
						if(data.retCode == 0){
							var $myprizeList = $(".fire_rule .myprize_list_cont").html("");
							var records = data.model.records;
							var imgUrl = _resRoot + '/';//默认路径前缀
							var trophyList = window.game.trophyList;//获取自定义奖品图片信息
							if(records != null && records.length > 0){
							   window.awardList = records;
							   for(var i=0;i<records.length;i++){
								   var record = records[i];
								   var awardName = record.awardName;
								   var prizeName = record.trophyName;
								   var prizeName2 = record.trophyName;
								   var statuClass = "s"+record.status;
								   var exchangeStatu = getAwardStatu(record.status);
								   var exchangeStatuImg /*changeAwardStatu(record.status)*/;
								   var awardLevel = record.awardLevel;//奖品等级
								   var prizeImg;
								   // 发现活动结束后 awardLevel 有可能为null
								   if(awardLevel != null && trophyList[awardLevel-1].awardPic) {//有自定义奖品图片
									   prizeImg = customImgUrl + trophyList[awardLevel-1].awardPic;
								   }else if(record.awardtype == 2){ //红包
									   prizeImg = imgUrl + 'hongbaoimg.png';
								   }else{ //奖券
                                       prizeImg = imgUrl + 'jiangquanimg.png';
								   }
								   if(record.awardtype == 1 || record.awardtype == 4 || record.awardtype == 5){
									   // 商家自定义，附带SN码
									   prizeName = record.snCode;
									   statuClass = "s0";
                                       exchangeStatuImg = changeAwardStatu(record.status,1);
								   } else if(record.awardtype == 2){
									   //现金红包类型
									   prizeName = "现金红包￥"+(record.awardValue/100).toFixed(2);
									   // if(record.status == 1){
										//    // 红包奖品“已领取，待兑换”状态相等于“已使用”状态
										//    exchangeStatuImg = changeAwardStatu(record.status,2);
									   // }
                                       exchangeStatuImg = changeAwardStatu(record.status,2)
								   } else if(record.awardtype == 3){
									   //流量类型
                                       exchangeStatuImg = changeAwardStatu(record.status,1)
								   } else if(record.awardtype == 4){
									   //优惠券类型
								   }
								   var $record = $('<div data-index="'+i+'" class="myprize_item '+statuClass+'"><div class="prize_img"><img src="'+prizeImg+'"/></div><div class="award_info"><p class="award_name">'+awardName+'</p><p class="expired_time">'+prizeName2+'</p><p class="exchange_statu">'+prizeName+'<p></div><img class="statu_img" src="'+imgUrl+exchangeStatuImg+'"/></div>');
								   $record.data("record",record);
								   $myprizeList.prepend($record);
							   }
							} else {
								$(".fire_rule .myprize_empty").show();
								$myPrizeWrapper.data("nomore",true);
							}
							$(".fire_rule .myprize_list_cont").data("hasload",true);
						} else {
							window.game.alert(data.message);
						}
						window.game._showMyPrize($initEl,false);
						if(!window.game.myPrizeScroll){
							window.game.myPrizeScroll = new iScroll('myprizeWrapper',{
								hScrollbar:false,
								hScroll:false,
								vScrollbar:true,
								hideScrollbar:false,
								onScrollMove:function(){
									if(this.y < (this.maxScrollY - 5)){
										$myPrizeWrapper.data("refresh",true);
									} else {
										$myPrizeWrapper.data("refresh",false);
									}
								},
								onScrollEnd:function(){
									/**
									if($myPrizeWrapper.data("refresh") && !$rankTablewrapper.data("nomore")){
										loadMyPrizeList();
									}
									**/
								},
							});
						} else {
							window.game.myPrizeScroll.refresh();
						}
					}
				});
			}
			loadMyPrizeList();
		}
	},
	/**
	 * 排行榜弹框
	 */	
	showRank:function($initEl){
		var $rankTablewrapper = $("#rankTableWrapper");
		initAnimation($rankTablewrapper.find('tbody td'),'','','',1.8);
		if($(".fire_rank .rank_table").data("hasload")){
			window.game._showRank($initEl,false);
		} else {
			function loadRankList(){
				if($rankTablewrapper.data("loading")) return;
				var start = $(".fire_rank .rank_table tbody tr").size();
				start = start>0?start+3:start;
				var submitData = {
					"aid":params.aid,
					"activityid":params.activityid,
					"wuid":window.game.config.wuid,
					"keyversion":window.game.config.keyversion,
					"type":2,
					"start":start,
					"size":9
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$rankTablewrapper.data("loading",true);
				$.ajax({
					url: '/mobile/participation/queryranking',
					data: submitData,
					success: function(data){
						$rankTablewrapper.data("loading",false);
						if(data.retCode == 0){
							var $tbody = $(".fire_rank .rank_table tbody");
							var rankList = data.model.rankList.results;
							var personInfo = data.model.personInfo;
							console.log(data);
							if(start==0&&rankList != null && rankList.length > 0){
								$('#rankSpecialWrapper .rank_firstPlace .personName').text(rankList[0].nickName);
								$('#rankSpecialWrapper .rank_firstPlace .personScore').text(rankList[0].score);
								$('#rankSpecialWrapper .rank_firstPlace .headerimg').html('<img src="'+$.tinyWxHeadImg(rankList[0].headPic)+'">');
								if(rankList.length > 1){
									$('#rankSpecialWrapper .rank_secondPlace .personName').text(rankList[1].nickName);
									$('#rankSpecialWrapper .rank_secondPlace .personScore').text(rankList[1].score);
									$('#rankSpecialWrapper .rank_secondPlace .headerimg').html('<img src="'+$.tinyWxHeadImg(rankList[1].headPic)+'">');
								}
								if(rankList.length > 2){
									$('#rankSpecialWrapper .rank_thirdPlace .personName').text(rankList[2].nickName);
									$('#rankSpecialWrapper .rank_thirdPlace .personScore').text(rankList[2].score);
									$('#rankSpecialWrapper .rank_thirdPlace .headerimg').html('<img src="'+$.tinyWxHeadImg(rankList[2].headPic)+'">');
								}
								for(var i=3;i<rankList.length;i++){
									var record = rankList[i];
									var $record = $('<tr><td>'+(parseInt(start)+parseInt(i)+1)+'</td><td><img class="headerimg" src="'+$.tinyWxHeadImg(record.headPic)+'"><span class="nickname">'+record.nickName+'</span></td><td>'+record.score+'</td></tr>');
									$tbody.append($record);
								}
							}else if(start>0&&rankList != null){
								for(var i=0;i<rankList.length;i++){
									var record = rankList[i];
									var $record = $('<tr><td>'+(parseInt(start)+parseInt(i)+1)+'</td><td><img class="headerimg" src="'+$.tinyWxHeadImg(record.headPic)+'"><span class="nickname">'+record.nickName+'</span></td><td>'+record.score+'</td></tr>');
									$tbody.append($record);
                                   initAnimation($tbody.find('td'),'','','','0');
								}
								if(rankList.length < 10 || $(".fire_rank .rank_table tbody tr").size() >= 100){
									$(".fire_rank .ranklist_empty").show();
									$rankTablewrapper.data("nomore",true);
								} else {
									$(".fire_rank .ranklist_empty").hide();
								}
							} else {
								$(".fire_rank .ranklist_empty").show();
								$rankTablewrapper.data("nomore",true);
							}
							var ranking_text = "0";
							var percent_text = "0";
							if(personInfo != null){
								ranking_text = personInfo.ranking;
								percent_text = personInfo.total <= 1 ? "100":(((personInfo.total-personInfo.ranking)/(personInfo.total))*100).toFixed(2);
							} else {
								ranking_text = data.model.rankList.total+1;
							}
							
							$(".fire_rank .ranking_text").text(ranking_text);
							$(".fire_rank .percent_text").text(percent_text);
							$(".fire_rank .rank_table").data("hasload",true);
						} else {
							window.game.alert(data.message);
						}
						window.game._showRank($initEl,false);
						if(!window.game.rankScroll){
							window.game.rankScroll = new iScroll('rankTableWrapper',{
								hScrollbar:false,
								hScroll:false,
								vScrollbar:true,
								hideScrollbar:false,
								onScrollMove:function(){
									if(this.y < (this.maxScrollY - 5)){
										$rankTablewrapper.data("refresh",true);
									} else {
										$rankTablewrapper.data("refresh",false);
									}
								},
								onScrollEnd:function(){
									if($rankTablewrapper.data("refresh") && !$rankTablewrapper.data("nomore")){
										loadRankList();
									}
								},
							});
						} else {
							window.game.rankScroll.refresh();
						}
					}
				});
			}
			loadRankList();
		}
	},
	
	/**
	 * 中奖列表弹框
	 */	
	showPrizeList:function($initEl){
		var $prizeListTablewrapper = $("#prizeListTableWrapper");
		if($(".fire_rank .prizelist_table").data("hasload")){
			window.game._showPrizeRecord($initEl,false);
		} else {
			function loadPrizeList(){
				if($prizeListTablewrapper.data("loading")) return;
				var start = $(".fire_rank .prizelist_table tbody tr").size();
				var submitData = {
					//"aid":params.aid,
					"activityid":params.activityid
					//,"wuid":window.game.config.wuid,
					//"keyversion":window.game.config.keyversion,
					//"start":start,
					//"size":10
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$prizeListTablewrapper.data("loading",true);
				$.ajax({
					//动态
					url: serverroot+'/bigwheel/queryawardrecord.html',
					data: submitData,
					success: function(data){
						 //  data =  eval('(' + data + ')');
						$prizeListTablewrapper.data("loading",false);
						if(data.retCode == 0){
							var $tbody = $(".fire_rank .prizelist_table tbody");
							var prizeList = data.model.records.results;
							console.log(data);
							if(prizeList != null && prizeList.length > 0){
								for(var i=0;i<prizeList.length;i++){
									var record = prizeList[i];
									var $record = $('<tr><td><img class="headerimg" src="'+$.tinyWxHeadImg(record.headPic)+'"></td><td class="nickname_td"><span class="nickname">'+record.nickName+'</span></td><td>'+record.trophyName+'</td></tr>');
									$tbody.append($record);
								}
								if(prizeList.length < 10 || $(".fire_rank .prizelist_table tbody tr").size() >= 100){
									$(".fire_rank .prizelist_empty").show();
									$prizeListTablewrapper.data("nomore",true);
								} else {
									$(".fire_rank .prizelist_empty").hide();
								}
							} else {
								$(".fire_rank .prizelist_empty").show();
								$prizeListTablewrapper.data("nomore",true);
							}
							$(".fire_rank .prizelist_table").data("hasload",true);
						} else {
							window.game.alert(data.message);
						}
						window.game._showPrizeRecord($initEl,false);
						if(!window.game.prizeListScroll){
							window.game.prizeListScroll = new iScroll('prizeListTableWrapper',{
								hScrollbar:false,
								hScroll:false,
								vScrollbar:true,
								hideScrollbar:false,
								onScrollMove:function(){
									if(this.y < (this.maxScrollY - 5)){
										$prizeListTablewrapper.data("refresh",true);
									} else {
										$prizeListTablewrapper.data("refresh",false);
									}
								},
								onScrollEnd:function(){
									if($prizeListTablewrapper.data("refresh") && !$prizeListTablewrapper.data("nomore")){
										loadPrizeList();
									}
								},
							});
						} else {
							window.game.prizeListScroll.refresh();
						}
					}
				});
			}
			loadPrizeList();
		}
	},
	
	/**
	 * 兑奖弹框
	 */	
	showExchange:function(awardRecord){
		$(".fire_exchange .awardName").text(awardRecord.awardname);
		$(".fire_exchange .prizeName").text(awardRecord.awardname);
		$(".fire_exchange .snCode").text(awardRecord.snCode);
		$(".fire_exchange .validityTime").text(awardRecord.validityStart+"-"+awardRecord.validityEnd);
		window.game._showFireExchange();
	},
	/**
	 * 用户信息弹框
	 */	
	showUserInfo:function(){
		window.game._showFireUserInfo();
	},
	
	/**
	 * 跳转到奖品列表
	 */
	redirectToPrizelist:function(){
		
	},
	
	/**
	 * 获取游戏参与人数
	 */
	getPlayerCount:function(){
		
	},
	
	/**
	 * 弹出提示框
	 */
	alert:function(msg,callback){
		alert(msg);
		callback&&callback();
	},
	/**
	 * 显示抽奖页面
	 * isUseMypage
	 * successCallBack
	 */
	showLottoPage:function($initEl, obj){
		$(".mask").hide();
		if(typeof obj === "undefined"){
			obj = {};
		}
		if(!obj.isUseMypage){
			window.game.lotteryMask.show();
		}
		
		$.ajax({
			//动态
			url: serverroot+'/bigwheel/dolottery.html',
			data: {
				//"aid":window.game.config.aid,
				"activityid":window.game.config.activityid
				//,"keyversion":window.game.config.keyversion,
				//"score":window.game.config.score || 1
			},
			type: 'get',
			complete: function(xhr){
			},
			success: function(data){
				  // data =  eval('(' + data + ')');
				if(obj.isUseMypage){
					obj.successCallBack(data);
					return;
				}
				
			   setTimeout(function(){
				   window.game.lotteryMask.find(".loader_lotto").css("animation","null");
				   if(data.retCode == 0){
					   //抽中
					   //console.log(data);
					   isPrize = true;
					   window.awardId = data.model.awardRecord.id;
					   window.awardRecord = data.model.awardRecord;
					   if(window.game.imgTextShare){
						   share_content = window.game.imgTextShare.awardtext.replace(/\\${awardName}/g,window.awardRecord.awardname);
					   }
					   if(window.awardRecord.awardtype == 1 || window.awardRecord.awardtype == 4
							   || window.awardRecord.awardtype == 5){
						   window.game.firePrizeSuccMask.find(".prizeName").text(window.awardRecord.awardname);
						   window.game.firePrizeSuccMask.find(".jiangpinName").text(window.awardRecord.trophyname);
						   console.log(window.game.activity.exchangelimit =="false");
						   if(window.game.activity.exchangelimit =="false"){
							   $(".unlock_prize_link").off("touchend").on("touchend",function(){
					                $(".rule_rule_link").removeClass('active');
					                $(".rule_myprize_link").addClass('active');
									$(".fire_res_rule_bg").addClass("fire_res_rule_myprize_bg");
									$("#ruleWrapper").hide();
									$("#myprizeWrapper").show();
									window.game.showMyPrize();
								});
						   }
						   
						   window.game.firePrizeSuccMask.show();
					   } else if(window.awardRecord.awardtype == 2){
						   window.game.firePrizeHbSuccMask.show();
					   }
				   } else if(data.retCode == -38){
					   //没抽中
					   isPrize = false;
					   window.game.firePrizeFailMask.show();
				   } else {
					   window.game.alert(data.message);
				   }
			   },3000);
			}
		});
		
	},
	/**
	 * 加载抽奖页面
	 */
	loadLottoPage:function($initEl){
		window.game.loadingClip.show();
		window.game.loadingCover.show();
		$.ajax({
			url: '/mobile/newgame/game_lottery.jsp',
			type:"get",
			dataType:"html",
			data: {
				"aid":params.aid,
				"activityid":params.activityid,
				"wuid":params.wuid
			},
			complete: function(xhr){
				window.game.gamePage.hide();
				window.game.startPage.show();
			},
			success: function(data){
				$(".mask").hide();
				window.game.lotteryMask.html(data).show().data("hasload",true);
			}
	
		});
	},
	
	/**
	 * 弹出收藏提示蒙版
	 * @param: prize - object - 奖品
	 * @param: isUseMyAwardHelpPage - boolean - 是否使用自己的倒计时页面
	 */
	loadUnLockAward : function(prize, isUseMyAwardHelpPage) {
		console.log(window.game.config);
		console.log(activityJson);
		console.log(prize);
		// 是否助力领奖
		var unlockPrizeCount = 0;
		if(window.game.config.isHelpAward && activityJson.extendOperation != ""){
			console.log(0);
			unlockPrizeCount = $.parseJSON(activityJson.extendOperation).unlockPrizeCount;
		}
		console.log(1);
	    window.game._showAwardHelp();
		console.log(2);
	    window.game.awardHelpPage.find(".p_title").text(window.game.activity.wxname);
	    window.game.awardHelpPage.find(".p_awardname").text(prize.trophyname);
	    window.game.awardHelpPage.find(".unlockPrizeCount").text(unlockPrizeCount);
		window.game.loadingClip.show();
		window.game.loadingCover.show();

		
		// 特殊处理，使用自己的游戏奖品助力页面（自燃红包）
		if (isUseMyAwardHelpPage) {
			console.log(4);
			$(".award_help_page").hide();
			window.game.hideAjaxLoading();
			return false;
		} 
		
		if(prize.status == 4){
			// 已回收
			console.log(5);
			window.game.awardHelpPage.find(".p_time").text("未能领取");
			window.game.awardHelpPage.find(".p_text_1").text("没能在规定时间内集满伙伴");
			window.game.awardHelpPage.find(".invite_btn").text("再次挑战").data("recycle",true);
		} else if(prize.status == 0){
			console.log(6);
			// 已领取，未兑换
			if(window.game.activity.exchangelimit){
				console.log(7);
				// 兑奖前需要填写手机号码
				window.game.awardHelpPage.find(".phone_cont").show();
				window.game.awardHelpPage.find(".p_text_4").show();
				if(window.game.userPlayinfo.openuser.mobile != null){
					window.game.awardHelpPage.find("input[name='phone']").val(window.game.userPlayinfo.openuser.mobile);
				}
				window.game.awardVerifyMask.find(".username_cont").show();
				if(window.game.userPlayinfo.openuser.realname != null){
					console.log(window.game.userPlayinfo.openuser.realname);
					window.game.awardHelpPage.find("input[name='realname']").val(window.game.userPlayinfo.openuser.realname);
				}
			}
			window.game.awardHelpPage.find(".award_statu_1").hide();
			window.game.awardHelpPage.find(".award_statu_2").show();
			window.game.awardHelpPage.find(".p_awardname").css({"color":"#ed4d41","padding-bottom":"0"});
			//window.game.awardHelpPage.find(".p_text_2 .p_text").text("集满伙伴啦！");
			
			window.game.awardHelpPage.find(".invite_btn").text("领取").data("exchange",true);
			
		} 
		window.game.awardHelpPage.find(".invite_btn").off("touchend").on("touchend",function(){
			if($(this).data("recycle")){
				// 再次开始
				var submitData = {
					//"aid":params.aid,
					"activityid":params.activityid,
					//,"wuid":window.game.config.wuid,
					"arid":prize.id
					//,"keyversion":window.game.config.keyversion
				};
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				$.ajax({
					url: '/mobile/participation/confirm_recycle',
					data: submitData,
					success: function(data){
					   if(data.retCode == 0){
						   window.game._showStartPage();
					   } else {
						   window.game.alert(data.message);
					   }
					}
				});
			} else if($(this).data("exchange")){
				console.log("a");
				var $btn = $(this);
				if($btn.hasClass("disabled")) return;
				var $realname = $("input[name='realname']",window.game.awardHelpPage);
				var $phone = $("input[name='phone']",window.game.awardHelpPage);
				if(window.game.activity.exchangelimit){
					if($phone.val() == ""){
						window.game.alert("请输入手机号码",function(){
							$phone.focus();
						});
						return false;
					}
					if($realname.val() == ""){
						window.game.alert("请输入您的姓名",function(){
							$realname.focus();
						});
						return false;
					}
					var regu =/^[0-9]{8,20}$/;
					var re = new RegExp(regu); 
					if(!re.test($phone.val())){
						window.game.alert("请输入正确的手机号码",function(){
							$phone.focus();
						});
						return;
					}
				}
				/*if($validcode.val() == ""){
					window.game.alert("请输入图片验证码",function(){
						$validcode.focus();
					});
					return false;
				}*/
				$btn.addClass("disabled");
				var submitData = {
					"aid":params.aid,
					"activityid":params.activityid,
					"wuid":window.game.config.wuid,
					"realname":$realname.val(),
					"awardrecordid":prize.id,
					"keyversion":window.game.config.keyversion
				};
				if(window.game.activity.exchangelimit){
					submitData.phonenum = $phone.val();
				}
				window.game.loadingClip.show();
				window.game.loadingCover.show();
				console.log(window.awardRecord.awardtype);
				$.ajax({
					url: serverroot+'/bigwheel/checkverifycode.html',
					data: submitData,
					success: function(data){
						console.log(9);
						//data =  eval('(' + data + ')');
					   if(data.retCode == 0){
						   if(window.awardRecord.awardtype == 1 || window.awardRecord.awardtype == 4
								|| window.awardRecord.awardtype == 5){
							   console.log(10);
								$(".fire_rule .myprize_list_cont").data("hasload",false);
								$(".fire_res_rule_bg").addClass("fire_res_rule_myprize_bg");
								$("#ruleWrapper").hide();
								$("#myprizeWrapper").show();
								console.log(11);
								window.game.showMyPrize();
								// 此处关闭我的奖品后刷新页面
								window.game.ruleMask.find(".close").off('touchend').on('touchend',function(){
									console.log(12);
									window.game.reload();
								});
						   } else {
							   console.log("b");
							    window.game.loadAwardRecordList(window.awardRecord);
								$(".fire_rule .myprize_list_cont").data("hasload",false);
								$(".fire_res_rule_bg").addClass("fire_res_rule_myprize_bg");
								$("#ruleWrapper").hide();
								$("#myprizeWrapper").show();
								window.game.showMyPrize();
								// 此处关闭我的奖品后刷新页面
								window.game.ruleMask.find(".close").off('touchend').on('touchend',function(){
									window.game.reload();
								});
						   }
						   //window.game.reload();
					   } else {
						   window.game.alert(data.message);
					   }
					   $btn.removeClass("disabled");
					}
				});
			} else {
				// 提示助力
				$('[data-role="share"]').eq(0).trigger("touchend",true);
				//window.game.awardHelpTip();
			}
		});
		var submitData = {
			//"aid":params.aid,
			"activityid":params.activityid
			//,"wuid":window.game.config.wuid,
			//"keyversion":window.game.config.keyversion
		};
		window.game.loadingClip.show();
		window.game.loadingCover.show();
		if(window.game.config.isHelpAward){
			// 加载助力列表
			$.ajax({
				url: '/mobile/participation/help_info',
				data: submitData,
				success: function(data){
					if(data.retCode == 0){
						var helpList = data.model.helpList;
						var unlockCount = 0;
						if(helpList != null && helpList.length > 0){
							unlockCount = helpList.length;
							for(var i=0;i<helpList.length;i++){
								var helpItem = helpList[i];
								var html='<li>'+
								'<img class="headimg" src="'+helpItem.headImg+'"> '+
								'	<p class="nickname">'+helpItem.nickName+'</p> '+
								'	<p class="time">'+helpItem.description+'</p> '+
								'	<p class="word">我来拆奖，友谊的小船不会翻</p> '+
								'</li>';
								window.game.awardHelpPage.find(".help_list").append($(html));
							}
						} else {
							
						}
						window.game.awardHelpPage.find(".unlockCount").text(unlockCount);
						window.game.awardHelpPage.find(".unlockLeft").text(Math.max(unlockPrizeCount - unlockCount,0));
						console.log(data);
					}
				}
			});
		} else {
			window.game.awardHelpPage.find(".help_list_cont").hide();
			window.game.awardHelpPage.find(".p_text_2").hide();
			window.game.loadingClip.hide();
			window.game.loadingCover.hide();
		}
	},
	
	/**
	 * 弹出收藏提示蒙版
	 */
	favorite : function() {
		if ($("#favoritePanel").length > 0) {
			$("#favoritePanel").show();
			return;
		}
		$("body").append("<div id='favoritePanel'></div>");
		$("#favoritePanel").on("touchend touchstart", function(a) {
			setTimeout(function() {
				$("#favoritePanel").hide()
			}, 200)
		});
	},
	/**
	 * 弹出助力提示蒙版
	 */
	awardHelpTip : function() {
		if ($("#awardHelpPanel").length > 0) {
			$("#awardHelpPanel").show();
			return;
		}
		$("body").append("<div id='awardHelpPanel'></div>");
		$("#awardHelpPanel").on("touchend touchstart", function(a) {
			setTimeout(function() {
				$("#awardHelpPanel").hide()
			}, 200)
		});
	},
	/**
	 * 弹出分享提示蒙版
	 */
	shareHelpTip : function() {
		if ($("#gameSharePanel").length > 0) {
			$("#gameSharePanel").show();
			return;
		}
		$("body").append("<div id='gameSharePanel'></div>");
		$("#gameSharePanel").on("touchend touchstart", function(a) {
			setTimeout(function() {
				$("#gameSharePanel").hide()
			}, 200)
		});
	},
	/**
	 * 弹出红包中奖人列表蒙版
	 */
	loadAwardRecordList : function(prizeRecord) {
		$(".hbres .wxname").text(window.game.activity.wxname);
		function getSharpStr(num){
			if(num>9){
				return num;
			}else{
				return '0'+num;
			}
		}
		function prependItem(header,nickname,time,amount){	
			time=new Date(time);
			var html='<div class="item"> '+
					 	'<img class="header" src="'+header+'"> '+
						'<div class="info"> '+
						'	<p>'+nickname+'</p> '+
						'	<p class="time">'+(getSharpStr(time.getHours())+':'+getSharpStr(time.getMinutes()))+'</p> '+
						'</div> '+
						'<span style="float: right;">'+amount.toFixed(2)+'元</span> '+
					 '</div>';
			$(".hblist").append($(html));
			//$(html).insertAfter($('.hbres .hblist .list_header'));
		}
		var $hbres=$('.hbres');
		var $hbList = $(".hbres .hblist");
		var $header=$('.hbres .topheader.header');
		if($hbList.data("hasload")) return;
		var submitData = {
			//"aid":window.game.config.aid,
			"activityid":window.game.config.activityid
			//,"wuid":window.game.config.wuid,
			//"keyversion":window.game.config.keyversion,
			//"start":0,
			//"size":30
		};
		
		// 如果有传数据
		if(prizeRecord){
			$hbres.find('.money').html((prizeRecord.awardValue/100).toFixed(2));
			$header.attr('src',prizeRecord.headPic);
			window.game.loadingClip.show();
			window.game.loadingCover.show();
		}else{
			// 新版摇一摇红包特殊处理
			$(".hbres .topheader").attr("src",window.game.userPlayinfo.openuser.headimgurl)
			$(".hbres").find("p").eq(0).hide();
			$(".hbres").find("p").eq(1).hide();
			$(".hbres").find("p").eq(2).hide();
			$(".hblist").css({
				"top":"25%"
			})
		}
		
		$.ajax({
			//动态
			url: serverroot+'/bigwheel/queryawardrecord.html',
			data: submitData,
			success: function(data){
				console.log(data);
				 //  data =  eval('(' + data + ')');
			   if(data.retCode == 0){
				   if(data.model.records.results){
					   var records = data.model.records.results;
						$(".list_header .sendTotal").text(data.model.records.total);
						for(var i=0;i<records.length;i++){
							var awardRecord = records[i];						
							prependItem($.tinyWxHeadImg(awardRecord.headPic),awardRecord.nickName,awardRecord.date,awardRecord.awardValue/100);
						}
						$hbres.show();
				   }else{
					   alert("暂没数据~");
				   }
					
			   } else {
				   window.game.alert(data.message);
			   }
			}
		});
	},
	/**
	 * 弹出微信卡券详情页面
	 * wuid : 商家绑定公众号的wuid
	 * sncode : 平台生成的sn码
	 * cardid : 奖项对应的微信卡券id
	 */
	showWxCardPage:function(wuid,sncode,cardid){
		window.game.showAjaxLoading();
		if(window.hasLoadJsTicketForWxCard){
			$.ajax({
				//动态
				url:serverroot+'/bigwheel/getJsApiTicket.html?uid=' + wuid,
				dataType:'json',
				type:'get',
				success:function(data){
					  // data =  eval('(' + data + ')');
					if(data.ret != 0){
						window.game.alert("获取微信卡券Ticket出错："+data.msg);
						return;
					}
					var time=parseInt(new Date().getTime()/1000)+"";
					var ticket = data.model.ticket;
					var arr = new Array(time,cardid,sncode,ticket);
					arr.sort();
					var str = '';
					for(var i = 0 ;i < arr.length ;++i)
					{
						str += arr[i];
					}
					var signature = hex_sha1(str);
					var cardExt = {"code": sncode, "timestamp" : time,"signature" : signature};
					wx.addCard({
						cardList : [ {
							cardId: cardid,
							cardExt : JSON.stringify(cardExt)
						} ], // 需要添加的卡券列表
						success : function(res) {
							// alert('已添加卡券：' + JSON.stringify(res.cardList));
						},
						cancel: function (res) {
							// alert(JSON.stringify(res))
						},
						fail:function(res){
							// alert('添加失败');
							// console.log("失败")
					
						}
					});
				}
			})
		} else {
			//发送请求获取appId和jsApiTicket
			$.ajax({
				//动态
				url: serverroot+'/bigwheel/getJsApiTicket.html?uid=' + wuid,
				dataType:'json',
				complete: function(xhr){
				},
				type:'get',
				success:function(data){
					 //  data =  eval('(' + data + ')');
					if(data.ret != 0){
						window.game.alert("获取微信卡券Ticket出错："+data.msg);
						return;
					}
					var appid = data.model.appid;
					var ticket = data.model.ticket;
					var timestamp=new Date().getTime()+'';
					var nonceStr=timestamp+parseInt(Math.random()*100000)+'';
					var str ='jsapi_ticket='+ticket+'&noncestr='+nonceStr+'&timestamp='+timestamp+'&url='+window.location.href;
					wx.config({
						debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId: appid, // 必填，公众号的唯一标识
						timestamp: timestamp, // 必填，生成签名的时间戳
						nonceStr: nonceStr, // 必填，生成签名的随机串
						signature:hex_sha1(str),// 必填，签名，见附录1
						jsApiList: ['addCard','checkJsApi'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});
					wx.checkJsApi({
						jsApiList: [
			            'getNetworkType',
			            'previewImage',
			            'addCard'
			            ],
			            success: function (res) {
			            	// alert("可用js"+JSON.stringify(res));
			            	window.hasLoadJsTicketForWxCard = true;
			            	window.game.showWxCardPage(wuid,sncode,cardid);
			            }
					});
				}
			})
		}
	},
	showAjaxLoading: function(){
		window.game.loadingClip.show();
		window.game.loadingCover.show();
	},
	hideAjaxLoading: function(){
		window.game.loadingClip.hide();
		window.game.loadingCover.hide();
	},
	reload : function(){
		var gamePageLink = location.pathname+"?activityid="+params.activityid;
		//if(params.isFromApiFilter == 1){
		//	gamePageLink += "&isFromApiFilter=1";
		//}
		//if(params.groupId){
			//gamePageLink += "&groupId="+window.game.config.groupId;
		//}
		if(params.fromOpenid){
			gamePageLink += "&fromOpenid="+params.fromOpenid;
		}
		//if(params.encrypt_code){
		//	gamePageLink += "&encrypt_code="+params.encrypt_code;
		//}
		//gamePageLink += "&keyversion="+window.game.config.keyversion;
		gamePageLink += "&_t="+new Date().getTime();
		window.location.replace(gamePageLink);
	},
	/*
	 * 替换资源-手机端替换资源，pc端回填资源
	 * @param config 资源对象
	 * 需要在dom元素上加上属性，值为资源对象的key
	 * 替换图片 data-replace-img=xxx
	 * 替换样式 data-replace-style=xxx
	 * 替换文字 data-replace-text=xxx
	 * domEl 父节点 只扫描父节点下的元素，节省操作
	 */
	replaceRes: function(config, domEl){
    	if (!config) return false;
    	if (config.image) {	// 图片
    		for (key in config.image) {
                var $dom = $("[data-replace-img='"+key+"']");
                if($dom.length>0 && !!config.image[key]){
                	$dom.each(function(){
                    	if (/img/i.test($(this)[0].nodeName)) {
                    	    $(this).attr("src", config.image[key]);
                    	} else {
                    	    $(this).css("background-image", 'url("' + config.image[key] + '")');
                    	}
                    });
                }
        	}
    	}
    	if (config.style) {	// 样式
    		for (key in config.style) {
                var $dom = $("[data-replace-style='"+key+"']");	
                if($dom.length>0 && !!config.style[key]){
                	$dom.each(function(){
                		$(this).css(config.style[key]);
                    });
                }
        	}
    	}
    	if (config.text) {	// 文字
    		for(key in config.text){
                var $dom = $("[data-replace-text='"+key+"']");	
                if($dom.length>0 && !!config.text[key]){
                	$dom.each(function(){
                		$(this).html($.htmlEncode(config.text[key]));
                	});
                }
        	}
    	}
    },
 
    clearDanmu : function(){
    	window.game.danmuMask.find(".danmu-msg-item").remove();
    	window.clearInterval(window.game.danmuTimer);
    	window.game.danmuTimer = 0;
    },
    /* 弹幕形式显示中奖信息
     */
    showDanmu: function(isManager){
    	var messageQueue=[];
    	if(isManager){
    		messageQueue = [
            {"nickname": "Peter潘","content": "100元优惠券","head":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEKkNXzuZEaiaqyhVRTJ7Mg7GiaPATGEnKXKsJFpJKJtLQT5JvCYR2U7MJvF7m2HyDZoCwnTooerD6Jg/0"},
            {"nickname": "沿途有你","content": "100元红包","head":"http://wx.qlogo.cn/mmopen/iclPUlkyyNteSFOJEIttd24tKicy42HXmq51GQ37DnuwuBeo4MCD8dN92eLjRElh6piclEuJWsCDjlp3tE57stoFyFFficph95bk/0"},
            {"nickname": "Better me","content": "50元优惠券","head":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEITXmtzeSVicIQjzCCBVicgsfN5zNqjxkl0yIw8aKic7w3YwH1eQpPicpYx7TYicC7ibTicCXaPCZIoLqAjQ/0"},
            {"nickname": "请叫我包子","content": "10元红包","head":"http://wx.qlogo.cn/mmopen/Sd1Sj1o1ibiaKUHk8ickCJ8SEMtKxMn1Qncf2NOZ4cfHBxbFwad2U5VKd9uoG4tZFDZyRiaErASjVbrv76JXQPR7GQ/0"},
            {"nickname": "Chloeee","content": "20元红包","head":"http://wx.qlogo.cn/mmopen/Sd1Sj1o1ibiaKdE2Mr4kUk0cjNyn4kxxRGd6JWoIlaQEIYuRwVFNoClCzdT9fiatmYGnQibbkKfqg8aXwO4AdEOlRAatYryQam3ib/0"},
            {"nickname": "聆听","content": "50元优惠券","head":"http://wx.qlogo.cn/mmopen/iclPUlkyyNtfGO8LUnzeWgicHd27PeNiatpEEnnAcNlRh42UVD6Zg8L9yYUs9b4iaicz4SBogaq7K3Opw4je1J9zorgx1bXFPjxAn/0"},
            {"nickname": "fasheng001","content": "50元红包","head":"http://wx.qlogo.cn/mmopen/ktZ0nuwFpAb48y7oCoEYadRcqjc2Hh4xtzVckf0h5wcPu0ibpkLX220ARvepzdZVMKfjT3mdYTI21Ko1vA0ugaU6xyyCoXHk2/0"},
            {"nickname": "好好先生","content": "100元优惠券","head":"http://wx.qlogo.cn/mmopen/Sd1Sj1o1ibiaKBWIYzYMicMSib5Nwia9H26pvqialeKxRicLeRfToVibf5GCJgnCgkektT9AwtFGxbIlBJSC7MJ7rV0aZ4qSic3iaC90Au/0"},
            {"nickname": "滑翔机","content": "70元优惠券","head":"http://wx.qlogo.cn/mmopen/Sd1Sj1o1ibiaIROaicUVM0CpjepAOZKicksdVtn30po5jHIjufWr0Lic1IuozpnTEChe2vhqcoJAAQTF7BIhOFXsTliac41YJVMKOO/0"},
            {"nickname": "唯美印象摄影","content": "50元优惠券","head":"http://wx.qlogo.cn/mmopen/iclPUlkyyNteSFOJEIttd2ibuR0CpJrDUQGAFJX1mQZU9oxy7nCLHXERn53IGQtPibQK1X3aX9R70fYHf5Z8AvWr4Ipic2Ik8dAs/0"}
            ];
    	}

    	// 获取随机颜色
    	function randomColor() {  
            var color = Math.ceil(Math.random() * 16777215).toString(16);
            while(color.length<6){
                color="0"+color;
            }
            return color;
        }
    	
    	// 插入消息
    	function appendMessage(obj,i){
    		if(!!obj && !!obj.content){
    			// 酷炫
    			if(!obj.head){
    				obj.head = "http://wx.qlogo.cn/mmopen/WEznNWtWKmDShAXyQSoDJJaj8Egwg4RjBGPRWU4oLQvUoc6StqnFZslmpQIqTeHgAiaNFyolhHWnWQA8NRKs3m809ib2wicOqs8/132";
    			}
    			var html = '<div class="danmu-msg-item not_comsume" style="top:'+(1+i*2.5)+'rem;left:100%;">' +
    							'<div class="user-photo">' +
    								'<img src="'+obj.head+'">' +
    							'</div>' +
    							'<div class="user-content"><span class="nickname ellipsis">'+obj.nickname+"</span><span class='ellipsis'>获得了：</span><span class='prize_name ellipsis'>"+obj.content+'</span></div>' +
    						'</div>';
    			window.game.danmuMask.append($(html));
    		}
    	}
    	function changeDanmu(typeSpeed) {
    		// 拿取弹幕 速度
			var speed = 11000; // 中
			if(typeSpeed == 1){
				speed = 8000; // 快
			}else if(typeSpeed == 2){
				speed = 11000; // 中
			}else if(typeSpeed == 3){
				speed = 15000; // 慢
			}
			if(messageQueue.length>0){
				var len=messageQueue.length;
				if (len > 3) {
					len = 3;
				}
				for(var i=0;i<len;i++){
					var now_msg = messageQueue.shift();
					messageQueue.push(now_msg);
					appendMessage(now_msg,i%3);
					// 将第一个写回到最后一个
				}
				var index = 1.3; // 延迟基数
				// 插入父页面
				window.game.danmuMask.find(".danmu-msg-item.not_comsume").each(function(i,item){
					var left = $(this).offset().left;
					var that = $(this);
					var width = +$('body', window.document).width() + left; // 这里要根据 不同的页面改
					index += 0.1;	// 一条消息 加0.1 --- 按后台一次最多30条算
					var delay_time = (1000*(i+1) + Math.random() * 400)/1000;
					var randomSpeed = speed - (i%3+1)*Math.random() * 800;
					that.removeClass("not_comsume");
					that.css({
						"transform":"translateX(-"+width+"px)",
						"-webkit-transform":"translateX(-"+width+"px)",
						"-moz-transform":"translateX(-"+width+"px)",
						"-o-transform":"translateX(-"+width+"px)",
						"-ms-transform":"translateX(-"+width+"px)",
						"transition":"all "+randomSpeed/1000+"s linear "+delay_time+"s",
						"-webkit-transition":"all "+randomSpeed/1000+"s linear "+delay_time+"s",
						"-moz-transition":"all "+randomSpeed/1000+"s linear "+delay_time+"s",
						"-o-transition":"all "+randomSpeed/1000+"s linear "+delay_time+"s",
						"-ms-transition":"all "+randomSpeed/1000+"s linear "+delay_time+"s"
					});
					var timeReal = +randomSpeed+1000*(i+1);
					// 清除消息
					setTimeout(function(){
						that.remove();
					},timeReal);
				});
			}
    	};
    	
    	if(window.game.danmuMask.data("loading")){
    		return;
    	}
    	// 如果游戏没有封面就不显示
    	
    	
    	window.game.clearDanmu();
    	if(!isManager){
    		var submitData = {
				//"aid":params.aid,
				"activityid":params.activityid
				//,"wuid":window.game.config.wuid,
				//"keyversion":window.game.config.keyversion,
				//"start":0,
				//"size":10
    		};
    		window.game.danmuMask.data("loading",true);
    		$.ajax({
    			url: serverroot+'/bigwheel/queryawardrecord.html',
    			data: submitData,
    			success: function(data){
    				//   data =  eval('(' + data + ')');
    				window.game.danmuMask.data("loading",false);
    				if(data.retCode == 0){
    					var prizeList = data.model.records.results;
    					if(prizeList != null && prizeList.length > 0){
    						for(var i=0;i<prizeList.length;i++){
    							var record = prizeList[i];
    							messageQueue.push({"nickname": record.nickName,"content": record.trophyName,"head":record.headPic});
    						}
    			    		changeDanmu(1);
    			    		if(!window.game.danmuTimer){
    			    			window.game.danmuTimer = setInterval(function(){
    			    				changeDanmu(1);
    			    			},3000);
    			    		}
    					}
    				} else {
    					window.game.alert(data.message);
    				}
    			}
    		});
    	}else {
    		changeDanmu(1);
    		if(!window.game.danmuTimer){
    			window.game.danmuTimer = setInterval(function(){
    				changeDanmu(1);
    			},3000);
    		}
    	}
    }
}

/*手机页面通用引擎优化函数*/
$(function(){
	//给所有动画样式做初始化
    var div;
    var delay = 0.3;
    var i = 0;
    var gap = 0.8;
    //排行榜当前排名
    animationPrepare($('#rankWrapper .rank_text'),{'position':'relative','left':'-13rem'});
    initAnimation($('#rankWrapper .rank_text'),'RanklistFadeIn',gap,"",'0s','forwards');

    //排行榜底座
    div = $('.fire_rank .rank_special .dizuo');
    animationPrepare(div,{'position':'relative','left':'-12rem'});
    initAnimation(div,'RanklistFadeIn',gap,'ease-in-out',delay*(i++),'forwards');

    //排行榜第一二三名的
    div = $('.fire_rank .rank_special .rank_firstPlace .jiangtai,.fire_rank .rank_special .rank_firstPlace .jiangtai .personName,.fire_rank .rank_special .rank_firstPlace .jiangtai .personScore,.fire_rank .rank_special .rank_firstPlace .jiangtai .jiangpai,.fire_rank .rank_special .rank_secondPlace .jiangtai,.fire_rank .rank_special .rank_secondPlace .jiangtai .personName,.fire_rank .rank_special .rank_secondPlace .jiangtai .personScore,.fire_rank .rank_special .rank_secondPlace .jiangtai .jiangpai,.fire_rank .rank_special .rank_thirdPlace .jiangtai,.fire_rank .rank_special .rank_thirdPlace .jiangtai .personName,.fire_rank .rank_special .rank_thirdPlace .jiangtai .personScore,.fire_rank .rank_special .rank_thirdPlace .jiangtai .jiangpai');
    initAnimation(div,'firstRaise',gap,'ease-in-out','','forwards');

    //排行榜第1名
    div =$('.fire_rank .rank_special .rank_firstPlace .jiangtai,.fire_rank .rank_special .rank_firstPlace .jiangtai .personName,.fire_rank .rank_special .rank_firstPlace .jiangtai .personScore,.fire_rank .rank_special .rank_firstPlace .jiangtai .jiangpai')
    animationPrepare(div,{'margin-bottom':'-7rem'});
    initAnimation(div,'','','',delay*(i++));

    //排行榜第2名
    div =$('.fire_rank .rank_special .rank_secondPlace .jiangtai,.fire_rank .rank_special .rank_secondPlace .jiangtai .personName,.fire_rank .rank_special .rank_secondPlace .jiangtai .personScore,.fire_rank .rank_special .rank_secondPlace .jiangtai .jiangpai')
    animationPrepare(div,{'margin-bottom':'-7rem'});
    initAnimation(div,'','','',delay*(i++));
    //排行榜第3名
    div =$('.fire_rank .rank_special .rank_thirdPlace .jiangtai,.fire_rank .rank_special .rank_thirdPlace .jiangtai .personName,.fire_rank .rank_special .rank_thirdPlace .jiangtai .personScore,.fire_rank .rank_special .rank_thirdPlace .jiangtai .jiangpai')
    animationPrepare(div,{'margin-bottom':'-7rem'});
    initAnimation(div,'','','',delay*(i++));

    //排行榜表头
    div = $('.rank_table .header th');
    animationPrepare(div,{'position':'relative','left':'-12rem'});
    initAnimation(div,'RanklistFadeIn',gap,'ease-in-out',delay*(i++),'forwards');
    //排行榜表格数据（ajax请求返回append 的数据所以直接写css）
    // div = $('.rank_table').find('tbody td');
    // animationPrepare(div,{'position':'relative','left':'-12rem'});
    // initAnimation(div,'fadeIn','0.8s','ease-in-out','4.8s','forwards');

//活动未开始
	//倒计时动画

    $('.activity_unStart .close').on('touchend',function () {
		$('.activity_unStart').hide();
    });
    $('.activity_unStart .statement').on('touchend',function () {
        window.game.showRule();
    })
    $('.activity_unStart .focus').on('touchend',function () {
        window.location.href=activityData.wxlink;
    })


    $('.phoneBox .close').on('touchend',function(){
    	$('.dialPhone').hide();
    })
    
    
    
})

//要是用animation需要初始化
function animationPrepare(div,cssJson){
	div.css(cssJson);
}
//animation样式初始化，通过function封装
function initAnimation(div,animaName,animaTime,animaFunction,animaDelay,animaMode){
	if(animaName!==""){
		div.css({
			"animation-Name":animaName,
			"-webkit-animation-Name":animaName,
			"-moz-animation-Name":animaName,
			"-o-animation-Name":animaName
		})
	}
	if(animaTime!==""){
		div.css({
            "animation-duration":animaTime+'s',
            "-webkit-animation-duration":animaTime+'s',
            "-moz-animation-duration":animaTime+'s',
            "-o-animation-duration":animaTime+'s'
		})
	}
    if(animaFunction!==""){
        div.css({
            "animation-timing-function":animaFunction,
            "-webkit-animation-timing-function":animaFunction,
            "-moz-animation-timing-function":animaFunction,
            "-o-animation-timing-function":animaFunction
        })
    }
    if(animaDelay!==""){
        div.css({
            "animation-delay":animaDelay+'s',
            "-webkit-animation-delay":animaDelay+'s',
            "-moz-animation-delay":animaDelay+'s',
            "-o-animation-delay":animaDelay+'s'
        })
    }
    if(animaMode!==""){
        div.css({
            "animation-fill-mode":animaMode,
            "-webkit-fill-mode":animaMode,
            "-moz-fill-mode":animaMode,
            "-o-fill-mode":animaMode
        })
    }


}


//倒计时效果
//时间格式化
function initDownTime(){
	var arr = formatTime();
	var n = [];
	for(var i in arr){
		n = n.concat(arr[i]);
	}
	var i =0;
	$('.activity_unStart .timeSplit').each(function () {
            $(this).text(n[i++]);
    })
    setInterval(function(){
        arr = countDown(arr);
    },1000)
}
function formatTime(){
    // if()
	var timeArray=[];
    var secShow = 1000;
    var minShow = 60*1000;
    var hourShow = 60*60*1000;
    var dayShow = 24*60*60*1000;

    var now = new Date().getTime();
    var dis = parseInt(activityLeftTime);
    var day = Math.floor(dis/dayShow);
    var hour = Math.floor((dis-day*dayShow)/hourShow);
    var min =Math.floor((dis-day*dayShow-hour*hourShow)/minShow);
    var sec =Math.floor((dis-day*dayShow-hour*hourShow-min*minShow)/secShow);
	day = add0(day);
	hour = add0(hour);
	min = add0(min);
	sec = add0(sec);
	timeArray.push(day.split(''));
	timeArray.push(hour.split(''));
	timeArray.push(min.split(''));
	timeArray.push(sec.split(''));
	return timeArray;
}



function countDown(timeArray) {
	var sec =parseInt(timeArray[3].join('')) ;
	var min =parseInt(timeArray[2].join('')) ;
	var hour =parseInt(timeArray[1].join('')) ;
	var day =parseInt(timeArray[0].join('')) ;

    activityLeftTime -= 1000;
    if(activityLeftTime<=0){
    	window.location.reload();
	}
	sec--;

	if(sec<0){
		var newArray = formatTime();
	}else{
        var newArray = [];
        newArray.push(add0(day).split(''));
        newArray.push(add0(hour).split(''));
        newArray.push(add0(min).toString().split(''));
        newArray.push(add0(sec).toString().split(''));
	}
    for(var i=3;i>=0;i--){
        for(var j=1;j>=0;j--){
            if(newArray[i][j]!==timeArray[i][j]){
                var s = 2*i+j;
                var str = '<span class="timeSplit" style="top:0.925rem;margin-left: 0rem">'+newArray[i][j]+'</span>'
                var layer = $('.activity_unStart .bottomLayer').eq(s);
                layer.append(str);
                layer.find('.timeSplit').eq(0).animate({top:'-0.925rem'},function () {
                    $(this).remove();
                });
                layer.find('.timeSplit').eq(1).animate({top:'0'});

            }
        }
    }
	return newArray;
}


function add0(m){
    var result = m<10?'0'+m:m;
    return result.toString();
}
//倒计时效果done

//活动状态
function activitySatusWrong(){
	if(window.activityFlag){
        $('.activity_unStart').show();
        return true; //说明未发布
	}
}

// 跳转我的奖品
$(".activity_isOver_awards .btn").eq(0).on("touchend",function(){
	// 这里直接调用showMyPrice不会先显示我的奖品面板，导致计算高度不准确，应该触发我的奖品按钮的touchen事件执行完整逻辑
	// window.game.showMyPrize();
	$(".rule_myprize_link").trigger("touchend");
	console.log("执行了");
});
//跳转到排行榜
$(".activity_isOver_awards .btn").eq(1).on("touchend",function(){
    $(".rank_rank_link").trigger("touchend")
});
$(".acitivity_isOver .btn").on("touchend",function(){
    $(".rank_rank_link").trigger("touchend")
});

/*----------模态框相关-----------*/
(function(){
	/* 模态框关闭部分 */
	//var canCloseEles = '.qx-modal,.qx-modal-backdrop,.icon_close,.cancelLine'; //可以关闭模态框的元素
	var canCloseEles = '.icon_close'; //可以关闭模态框的元素
	//列模态框的扩展
	$.fn.initColModal = function() {
	    if(this.selector === '.qx-modal-column') {
	    	this.css({
	    		bottom: '-' + this.height() +'px'
	    	});
	    }
	};
	$.fn.colModal = function(opt) {
	    if(this.selector === '.qx-modal-column') {
	    	if(opt === 'show') {
	    	    $('.qx-modal-backdrop').addClass('in');
	    	    this.css({
	    	    	bottom: 0
	    	    });
	    	}else if(opt === 'hide') {
	    	    $('.qx-modal-backdrop').removeClass('in');
	    	    this.initColModal();
	    	}
	    }
	};
	$('.qx-modal-column').initColModal();
	$(canCloseEles).on('click', function(e) {
		var eTarget = e.target,
			qxModal = $('.qx-modal')[0],
			qxBgModal = $('.qx-modal-backdrop')[0],
			closeIcon = $('.icon_close')[0],
			cancelLine = $('.cancelLine')[0];
		if (eTarget === qxModal || eTarget === closeIcon) {
			$('.qx-modal,.qx-modal-backdrop').removeClass('in');
		} else if (eTarget === qxBgModal || e.currentTarget === cancelLine) {
			$('.qx-modal-column').colModal('hide');
		}
	});
})();
/*----------分享二维码绘制----------*/
$(function(){
	$('.qx_topbar_unpublish .qx_tb_share_btn').on('click',function(){
		drawShareQrcode.drawShareImg();
	});
	$('.qx-share-qrcode-box .qxShareCloseIcon').on('click',function(){
		$('.qx-share-qrcode-box').addClass('hide');
	});
});
var drawShareQrcode={
	drawShareImg:function(){
		var canvas = document.getElementById('qxShareQrcodeCanvas'),
			ctx = canvas.getContext("2d"),
			activityName = document.title,
			url = window.game.qxMobileUrl,
			fontSize = document.documentElement.style.fontSize,
			width,
			height;
		fontSize = parseFloat(fontSize.substring(0,fontSize.length - 2));
        width = 15 * fontSize;
        height = 19.3 * fontSize;        
		$('.qx-share-qrcode-box').removeClass('hide');//显示阴影
		$('.qxShareCloseIcon').show();//显示关闭图标
		canvas.width = width;
		canvas.height = height;		
		ctx.clearRect(0,0,width,height);
		//画背景
		ctx.fillStyle = "#FCFCFC";
		ctx.fillRect(0,0,width,height);
		//画文字
		ctx.fillStyle = '#5184EF';		
		ctx.font = (0.95 * fontSize) + 'px' + ' bold Arial';
		ctx.textAlign = 'center';
		ctx.fillText('快来参与“'+this.qxCutStr(activityName,8)+'”啦！',7.7 * fontSize,3.4 * fontSize);		
		ctx.fillText('豪礼等你哦',7.5 * fontSize,4.69 * fontSize);
		ctx.fillStyle = '#9F9F9F';		
		ctx.font = 0.64 * fontSize + 'px' + ' Arial';		
		ctx.fillText('长按保存活动二维码分享该活动',7.5 * fontSize,6.4 * fontSize);
		//画二维码		
		$('#qxShareQrcodeDiv').qrcode({width: 8.53 * fontSize,height: 8.53 * fontSize,text: url});
		var base64 = $('#qxShareQrcodeDiv canvas')[0].toDataURL('image/jpeg',0.9);
		var shareImg =  new Image();
		shareImg.src = base64;
		shareImg.onload = function() {
			ctx.drawImage(shareImg,2.56 * fontSize,7.68 * fontSize,9.81 * fontSize,9.81 * fontSize);
			var shareUrl = canvas.toDataURL('image/jpeg',0.9);
			$('#qxShareQrcodeImg').attr('src',shareUrl);
			$('#qxShareQrcodeImg').show();
		}
	},
	qxCutStr:function(str,len){
		var str_length = 0;
		var str_len = 0;
		str_cut = new String();
		str_len = str.length;
		for (var i = 0; i < str_len; i++) {
			a = str.charAt(i);
			str_length++;
			if (escape(a).length > 4) {
				//中文字符的长度经编码之后大于4
				str_length++;
			}
			str_cut = str_cut.concat(a);
			if (str_length >= len) {
				console.log(str_length);
				str_cut = str_cut.concat("...");
				return str_cut;
			}
		}
		//如果给定字符串小于指定长度，则返回源字符串；		
		if (str_length < len) {
			return str;
		}
	}
}
/*$(function(){
	function drawShareImg(){
		var canvas = document.getElementById('qxShareQrcodeCanvas'),
			ctx = canvas.getContext("2d"),
			activityName = document.title,
			url = 'http://1.m.gametest.weijuju.com/mobile/newgame/index.jsp?aid=d3749e7db15e4dfb9a2d123026ceab35&activityid=4823&wuid=1&keyversion=0&isFromApiFilter=1',
			fontSize = document.documentElement.style.fontSize,
			width = 332,
			height = 400;
		$('.qx-share-qrcode-box').removeClass('hide');//显示阴影
		$('.qxShareCloseIcon').show();//显示关闭图标
		canvas.width = width;
		canvas.height = height;
		ctx.clearRect(0,0,width,height);
		//画背景
		ctx.fillStyle = "#FCFCFC";
		ctx.fillRect(0,0,332,400);
		//画文字
		ctx.fillStyle = '#5184EF';
		ctx.font = '22px Arial';
		ctx.fillText('快来参与'+qxCutStr(activityName,8)+'啦！',50,50);
		ctx.fillText('豪礼等你哦',120,80);
		ctx.fillStyle = '#9F9F9F';
		ctx.font = '15px Arial';
		ctx.fillText('长按保存活动二维码分享该活动',60,120);
		//画二维码
		$('#qxShareQrcodeDiv').qrcode({width: 200,height: 200,text: url});
		var base64 = $('#qxShareQrcodeDiv canvas')[0].toDataURL('image/jpeg',0.9);
		var shareImg =  new Image();
		shareImg.src = base64;
		shareImg.onload = function() {
			ctx.drawImage(shareImg,65,150,200,200);
			var shareUrl = canvas.toDataURL('image/jpeg',0.9);
			$('#qxShareQrcodeImg').attr('src',shareUrl);
			$('#qxShareQrcodeImg').show();
		}
	}

	function qxCutStr(str,len) {
		var str_length = 0;
		var str_len = 0;
		str_cut = new String();
		str_len = str.length;
		for (var i = 0; i < str_len; i++) {
			a = str.charAt(i);
			str_length++;
			if (escape(a).length > 4) {
				//中文字符的长度经编码之后大于4
				str_length++;
			}
			str_cut = str_cut.concat(a);
			if (str_length >= len) {
				str_cut = str_cut.concat("...");
				return str_cut;
			}
		}
		//如果给定字符串小于指定长度，则返回源字符串；
		if (str_length < len) {
			return str;
		}
	}

});*/


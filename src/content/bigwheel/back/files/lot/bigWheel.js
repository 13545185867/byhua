
var params = $.deparam(window.location.search.substring(1));
if(params.groupId || params.fromOpenid){
	params.isFromApiFilter = 1;
}
var activityData;
$(function(){
	function showEndPage(){
		var submitData = {
				"aid":params.aid,
				"activityid":params.activityid,
				"wuid":window.game.config.wuid,
				"keyversion":window.game.config.keyversion,
				"platformId":window.game.config.platformId
			};
			window.game.loadingClip.show();
			window.game.loadingCover.show();
			$.ajax({
				//动态
				url: serverroot + '/bigwheel/awardlist.html',
				data: submitData,
				success: function(data){
					   //data =  eval('(' + data + ')');
					if(data.retCode == 0){
						var records = data.model.records;
						if(records != null && records.length > 0){
							$(".activity_isOver_awards").show();
						}else{
							$(".acitivity_isOver").show();
						}
						/*如果按排名的话，就把结束页面的排行榜按钮展示出来。默认隐藏*/
						if(activityData.awardSendType==1){
                            $('.acitivity_isOver .btn').show()
						}
					} else {
						$(".acitivity_isOver").show();
					}
				}
			});
	}
	//动态
	window.game.init({
		"aid":params.aid,
		"activityid":params.activityid,
		"keyversion":params.keyversion||"0",
		"wuid":params.wuid,
		"groupId":params.groupId,
		"fromOpenid":params.fromOpenid,
		"platformId":"0",
		"userImgDomain":serverroot + "/content/bigwheel/files/lot/",
		"imgTextShare":imgTextShare,
		"wjjPageDomain":"http://www.byhua.com/",
		"pageDomain":"http://www.byhua.com/",
		"shareType":(activityJson.extraData&(Math.pow(2,7))),
		"securityType":(activityJson.extraData&(Math.pow(2,13)))==0,
		"isHelpAward":(activityJson.extraData&(Math.pow(2,5))) != 0
	});
	if(activityJson.awardSendType == 1){
		$(".start_page .rank_btn").show();
	}
	window.game.loadingClip && window.game.loadingClip.show();
	window.game.loadingCover && window.game.loadingCover.show();
	var initSubmitData = {
		"aid":params.aid,
        "wuid":params.wuid,
		"activityid":params.activityid,
		"fromOpenid":params.fromOpenid,
		"keyversion":window.game.config.keyversion
	}
	if(params.codeId){
		initSubmitData.codeId = params.codeId;
	}
	if(params.signature){
		initSubmitData.signature = params.signature;
	}
	$.ajax({
		//动态
		url: serverroot + '/bigwheel/init.html',
		type: 'get',
		data: initSubmitData,
		success: function(data){
			   //data =  eval('(' + data + ')');
			   console.log(data);
		   if(data.retCode == 0){
			   //$btn.removeClass("disabled");
			   window.activityData = data.model.activity;
               window.activityLeftTime = activityData.start-(new Date().getTime()+serverTimeDelta);
               if(data.model.userPlayinfo){
               		window.game.qxMobileUrl = data.model.userPlayinfo.mobileUrl;
               }
               if(activityData.isStart == 2 || activityData.isStart == 3){ //暂停中或者进行中才需要去判断
				   if(data.model.userPlayinfo){
                       window.isAttent = data.model.userPlayinfo.isAttention;
                       window.mobileUrl = data.model.userPlayinfo.mobileUrl;
				   }
			   }

			   var userPlayinfo = data.model.userPlayinfo;
			   var sendRuleMap = data.model.sendRuleMap;
			   var actRuntime = data.model.actRuntime;
			   var prizeList = data.model.prizeList;
			   var trophyList = data.model.trophyList;
			   var helpMap = data.model.helpMap ? data.model.helpMap: {}; // 获取助力相关信息
			   // 获取活动参与人数，判断是否人数超出限制
			   if(activityData.userJoin != null){
				   $("#join_line_cont .join_num").text(activityData.userJoin + activityJson.participantVirtualAddCount);
					// 免费版用户手机端限制最大参与人数6000人
//动态
				   if(_isFreeUser && activityData.userJoin>_maxParticipateNumber){
					   var gameOverPageLink = "/mobile/newgame/game_over.jsp?aid="+window.game.config.aid+"&activityid="+window.game.config.activityid+"&wuid="+window.game.config.wuid;
					   if(params.isFromApiFilter == 1){
						  gameOverPageLink += "&isFromApiFilter=1";
					   }
					   gameOverPageLink += "&keyversion="+window.game.config.keyversion;
					   window.location.replace(gameOverPageLink);
				   }			   
			   } else {
				   $("#join_line_cont").hide();
			   }
		       // 活动总中奖次数
		       window.game.config.userGetPrize = 0;
		       if(actRuntime && actRuntime.usedTrophyNum != null){
		    	   window.game.config.userGetPrize = actRuntime.usedTrophyNum;
		       }
			   var getHelpScore = 0;
			   var awardTips = activityData.awardTips;
			   if(userPlayinfo && userPlayinfo.getHelpScore){
				   getHelpScore = userPlayinfo.getHelpScore;
			   }
			   window.game.helpMap = helpMap;
			   window.game.getHelpScore = getHelpScore;
			   window.game.activity = activityData;
			   window.game.userPlayinfo = userPlayinfo || {};
			   window.game.sendRuleMap = sendRuleMap;
			   window.game.prizeList = prizeList;
			   // 活动奖项数据
			   window.game.trophyList = trophyList;
			   var getHelpNum = 0;
			   if(userPlayinfo && userPlayinfo.getHelpNum){
				   getHelpNum = userPlayinfo.getHelpNum;
			   }
			   window.game.getHelpNum = getHelpNum;
			   window.game.actRuntime = actRuntime;
			   window.game.extendOperation = activityData.extendOperation;
			   document.title = activityData.title;
			   // 先判断是否有需要助力的奖品
			   if(prizeList != null &&window["isSkipTree"]!=null&& window["isSkipTree"] != true){
				   for(var i=0;i<prizeList.length;i++){
					   var prize = prizeList[i];
					   if(prize.status == -1 || prize.status == 4 || prize.status == 0){
						   window.awardRecord = prize;
						   var prizeCheck = function(){
							   // 记录是否三级防刷，下面跳过首页时要做这个判断，否则会把防刷页面覆盖掉
							   window.isPrizeChecked = true;
							   // 过滤自燃红包活动，使用自己的倒计时页面，并在助力成功之后才进入三级防刷
							   if (_gameTemplateId === 25 && !prize.checked && prize.status === 0) { 
								   window.game.loadUnLockAward(prize, true);
								   return false;
							   } else {
								   window.game.loadUnLockAward(prize);
							   }
						   }
						   // 中奖之后，跳过助力阶段
						   if (window["isSkipHelpTree"]) {
							   if (prize.status != -1) {
								   prizeCheck();
							   }
						   } else {
							   prizeCheck();
						   }
						   break;
					   }
				   }
			   }
			   //activityFlag为true则说明距离活动开始还有一段时间
               window.activityFlag = false;
			   // 如果要跳过开始页面就在verifyplay接口里提示一次就好了，详细见game_main.js
			   if (!window["isSkipStartPage"]) {
				   if(activityData.isStart == 0){
					   //console.log("活动未发布");
					   $(".topbar_unpublish").show();
				   } else if(activityData.isStart == 1){
				       //活动未开始
                       if(activityLeftTime>0){
                           activityFlag = true;
                           initDownTime();
                           $('.activity_unStart').show();
                           console.log("活动未开始");
                       }
					   //return;
				   } else if(activityData.isStart == 4){
					   showEndPage();
				   } else if(activityData.isStart == 6){
					   showEndPage();
				   }
			   } else {
				   if(activityData.isStart == 0){
				   		$(".topbar_unpublish").show();
				   }else if(activityData.isStart == 1){
//				       活动未开始
                       if(activityLeftTime>0){
                           activityFlag = true;
                           initDownTime();
                           $('.activity_unStart').show();
                       }
                   }else if(activityData.isStart == 4){
                	   showEndPage();
				   } else if(activityData.isStart == 6){
					   showEndPage();
				   } 
               }
			   window.game.ruleMask.find(".content .fire_res_rule_tips_text").html($.htmlEncode(activityData.ruletips));
			   var startDate = new Date(activityData.start).Format("yyyy年MM月dd日");
			   var endDate = new Date(activityData.end).Format("yyyy年MM月dd日");
			   window.game.ruleMask.find("#rule_time").html(startDate+"~"+endDate);
			   if(awardTips){
				   var awardHtml = "";
				   var awardSize = awardTips.length;
				   for (var i=0;i<awardSize;i++) {
					   var award = awardTips[i];
					   awardHtml += "<p>";
					   awardHtml += award.awardname;
					   awardHtml += "：";
					   awardHtml += award.trophyname;
					   awardHtml += "</p>";
				   }
				   window.game.ruleMask.find("#rule_award").html(awardHtml);
			   }
			   if(sendRuleMap){
				   window.game.fireFailMask.find(".prize_score").text(sendRuleMap.passCondition);
			   }
			 
			   window.game.ruleMask.find(".organizer").text(activityJson.organizer);
			   window.game.awardInfoMask.find(".organizerName").text(activityJson.organizer);
				if(activityJson.showCopyright){
					$("#start_bottom").show();
				} else {
					$("#start_bottom").hide();
				}
				if(!activityJson.usedefaultcopyright){
					$("#startLogoImg").attr("src",window.game.config.userImgDomain+activityJson.customcopyrightpic);
				}
				if(actRuntime){
					// 统一从数据中心获取活动数据
					//$("#join_line_cont .join_num").text(actRuntime.playerNum+activityJson.participantVirtualAddCount);
				} else {
					$("#join_line_cont").hide();
				}
				if(activityJson.hideparticipant){
					$("#join_line_cont").hide();
				}
			   
			   $(".subscribe_link").on("touchend",function(){
				   window.location.href=activityData.wxlink;
			   });
			   if(activityJson.organizerlink && activityJson.organizerlink!=""){
				   $(".organizer_link").on("touchend",function(){
					  window.location.href=activityJson.organizerlink; 
				   });
			   }
			   //没有开启分享，隐藏分享的相关提示文字和按钮
			   if(!activityData.withHelp){
				   $('.fire_res .share').hide();
				   $('.fire_res .share_text').hide();
			   }
			   if(window.game.config.isHelpAward){
				   $('.fire_res .share_text').hide();
			   }

			   if(window.qianxi.isTestAct || window.qianxi.isInFreetime){//钱戏演示活动
			   		//alert('钱戏演示活动');
			   		$('.qx-copyright-box').removeClass('hide');
			   }else if(window.qianxi.isShoperAct){//钱戏商家活动
			   		if(window.qianxi.isShoperPackageOverTime){//钱戏商家套餐到期
			   			if(window.qianxi.isShoperEnterSelfAct){//钱戏商家进入自己创建的活动
			   				$('.qxShoperModal').addClass('in');
			   				//alert('套餐到期,钱戏商家进入自己创建的活动');
			   			}else{
			   				$('.qxFansModal').addClass('in');
			   				//alert('套餐到期,钱戏粉丝进入自己创建的活动');
			   			}
			   		}
			   }
			   //参与前提交个人信息判断
			   if(!window.qianxi.isShoperPackageOverTime){
				   if(activityData.participateLimit && userPlayinfo && userPlayinfo.openuser.mobile==null){
					   window.game._showParticipateUserInfo();
					   return false;
				   }
			   }
		   } else if(data.retCode == -60){
			   $(".fire_participate_overlimit").show();
			   return;
		   } else {
			   window.game.alert(data.message);
		   }
		   
		   // 初始化游戏
		   if(window.gamepage && activityData){
			   var gameTimeType = activityData.gameTimeType;
			   if(gameTimeType == 1){
			       gameTime = activityData.gameTime;
			   }
			   gameConfig.headerimg = window.game.config.headimg;
			   gameConfig.gameTime = gameTime;
			   window.gamepage.initGame(gameConfig);
		   }
		  	// 初始化页面分享信息
		    window.shareConfig = {
		        link: null,
		        title: activityJson.title,
		        desc: activityJson.title,
		        img_url: serverroot+"/content/bigwheel/files/lot/bigwheel.jpg",
		        img_width: 80,
		        img_heigth: 80,
		        successCallback: function(){
			    	 // 页面分享成功回调后台统计数据
					$.ajax({
						url: '/mobile/participation/share_rollback',
						type: 'get',
						data: {
							"aid":params.aid,
							"activityid":params.activityid,
							"keyversion":window.game.config.keyversion
						},
						success: function(data){
							console.log(data);
						}
					});
			    }
		    }
		  	
		   // 特殊游戏的分享标题放在自定义分享标题前面
	
		  	
			if(imgTextShare){
			   window.game.imgTextShare = imgTextShare;
			   shareConfig.link = imgTextShare.linkUrl;
			   if(activityJson.withHelp){
				   shareConfig.link = window.game.config.pageDomain + "/mobile/newgame/game_help.jsp?aid="+
						   window.game.config.aid + "&activityid=" + window.game.config.activityid + "&wuid=" +
						   window.game.config.wuid + "&openid=" + window.game.config.openid + "&keyversion="+window.game.config.keyversion;
			   } else {
				   shareConfig.link = window.game.config.pageDomain + "/mobile/newgame/index.jsp?aid="+
						   window.game.config.aid + "&activityid=" + window.game.config.activityid + "&wuid=" +
						   window.game.config.wuid + "&fromOpenid=" + (window.game.config.fromOpenid || window.game.config.openid) + "&keyversion="+window.game.config.keyversion;
			   }
			   // !自定义微信分享标题
			   var sttr3 = window.game.imgTextShare.sttr3; //自定义分享标题
			   var sttr2 = window.game.imgTextShare.sttr2; //自定义分享内容
			   var sttr1 = window.game.imgTextShare.sttr1; //自定义分享图片
			   var shareTitleText = window.game.imgTextShare.titleText || "";
			   shareTitleText =  $.trim(shareTitleText.replace(/\${nickName}/g,window.game.config.nickname));
			   if (sttr3 && sttr3==1){ //等于就是自定义
				   shareConfig.title = shareTitleText || activityJson.title;
			   }else {
				   //shareConfig.title = activityJson.title;
			   }
			  if(window.game.imgTextShare.awardtext !=null){
			   window.game.imgTextShare.awardtext = window.game.imgTextShare.awardtext.replace(/\${nickName}/g,window.game.config.nickname);
			  }
			   var descText = imgTextShare.noawardtext;
			   if(descText != null){
				   descText = descText.replace(/\${nickName}/g,window.game.config.nickname);
				   var lastScore = 0;
				   if(window.game.userPlayinfo && window.game.userPlayinfo.lastScore){
					   lastScore = window.game.userPlayinfo.lastScore;
				   }
				   descText = descText.replace(/\${score}/g,lastScore);
			   }
			   shareConfig.desc = descText;
			   if(imgTextShare.sttr1 == "1" && imgTextShare.imgPath){ //自定义分享图片
				   //使用自定义短图文分享图片
				   shareConfig.img_url = window.game.config.userImgDomain + imgTextShare.imgPath;
			   }
		    }
		  	
		    // 初始化完成后的回调方法
		    window.game.afterInit(data.retCode);
			// 是否显示扩展信息
			var showExpiryDetail = (activityJson.extraData&(Math.pow(2,14))) != 0;
			if(showExpiryDetail){
				if(extendOperation.checkAwardExtend){
					var expiry = extendOperation.checkAwardExtend.split(',');
					for(var i = 0;i<expiry.length;i++){
						var $div = $(".fire_award_verify .expiry-area").eq(i)
						$div.find('input[name=expirydetail]').attr('data-fieldname',expiry[i]);
						$div.find('input[name=expirydetail]').attr('placeholder',("请输入"+expiry[i]));
						$div.show();
					}
				}else{
					$(".fire_award_verify .expiry_cont").hide();
				}
				//$(".fire_award_verify .expiry_cont").show();
			} else {
				$(".fire_award_verify .expiry_cont").hide();
			}
		    
		  	if(!window["isCommonHelp"]){
			   shareConfig.link = window.game.config.pageDomain + "/mobile/newgame/index.jsp?aid="+
			   window.game.config.aid + "&activityid=" + window.game.config.activityid + "&wuid=" +
			   window.game.config.wuid + "&fromOpenid=" + (window.game.config.fromOpenid || window.game.config.openid) + "&keyversion="+window.game.config.keyversion;
			  window['initShareInfo'](shareConfig.title,shareConfig.desc,shareConfig.img_url,shareConfig.link,shareConfig.successCallback,33345);				   
		  	} else{
				   window['initShareInfo'](shareConfig.title,shareConfig.desc,shareConfig.img_url,shareConfig.link,shareConfig.successCallback,33345);	  			
		  	}
		    // 如果有groupId参数，表示对战游戏，可以直接开始游戏
		    if(window.game.config.groupId && window.game.awardHelpPage.is(":hidden")){
		    	$(".start_btn").click();
		    }
		    // 新版砍价活动特殊处理
		    if(_gameTemplateId == 19 && !window.awardRecord){
			   if(window.game.config.fromOpenid && window.game.config.fromOpenid != window.game.config.openid){
				   window.gamepage.startHelpGame(function(score){
				   });
			   } else {
				   window.gamepage.startGame(function(score){
				   });
			   }
			   //window.game._showGamePage();
		    }
		    
		    // 大转盘（29）
		    // 跳过开始页面直接显示自己的页面，同时也要判断是否要过滤三级防刷
		    if(_gameTemplateId == 29 || window["isSkipStartPage"] && !window.awardRecord){
		    	// 如果是助力人就提示跳转关注链接
		    	 if (params.fromOpenid && window["isSkipFllow"] != true) {
                     if(window.isAttent){ //关注了，就什么都不做，正常进行游戏
                         // window.location.href = window.mobileUrl;
                     }else{ //没有关注
                         $(".fire_common").find(".p_text_1").html("关注一下嘛~<br>人家才让你玩！");
                         $(".fire_common").find(".subscribe_link").show().children("a").attr("src", window.game.activity.wxlink);
                         $(".fire_common").show();
                         return false;
                     }
		    	}

		    	if (!window.isPrizeChecked) {
		    		// 模拟点击开始按钮，获取游戏资格，显示游戏页面，详细见game_main.js
			    	$(".start_btn").click();
		    	}
		    }
		    
		    // 如果有fromOpenid参数且fromOpenid不是自己，可以直接开始游戏并且记录助力信息
		    if(window.game.config.fromOpenid && 
		    		window.game.config.openid != window.game.config.fromOpenid  && 
		    		window.game.awardHelpPage.is(":hidden") && _gameTemplateId != 19){
//动态
		    	$.ajax({
	    			url: serverroot + '/bigwheel/help_user.html',
	    			data: {
		    			"aid":params.aid,
		    			"activityid":params.activityid,
		    			"wuid":params.wuid,
		    			"keyversion":params.keyversion||"0",
		    			"openid":params.fromOpenid
		    		},
	    			type:"GET",
	    			success: function(data){
	    				//   data =  eval('(' + data + ')');
	    			   if(data.retCode == 0){
	    				 // TODO
	    			   } else if(data.retCode == -3501){
	    			   } else {
	    				 // TODO
	    			   }
	    			}
	    		});
	   
		    	$(".start_btn").click();
		    }
		}
	});
});
// 根据分享方式判断是否隐藏分享菜单
if ((activityJson.extraData & (Math.pow(2, 7))) != 0) {
	// 图文分享方式
	// 定制处理
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('showOptionMenu');
	});

} else {
	// 图片分享方式
	// 目前只针对新版砍价游戏进行隐藏分享菜单，其他游戏考虑传播效果默认开放分享菜单
	if (_gameTemplateId == 19 || _gameTemplateId == 40 || window["isHideShare"]) {
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			WeixinJSBridge.call('hideOptionMenu');
		});
	} else {
		if(activityJson.id == 37995){
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
				WeixinJSBridge.call('hideOptionMenu');
			});
		} else {
			// 图文分享方式
			// 定制处理
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
				WeixinJSBridge.call('showOptionMenu');
			});
		}
	}
}

/**
  *
  *	全局变量
  *
  **/
var gameConfig = {}; // 游戏参数
var overCallback = null; // 回调函数
var imglist = {}; // 图片列表
var loadImgs = []; // 加载图片数组

// 音乐
var music1 = $("#music1")[0];
var music2 = $("#music2")[0];


//轮播中奖信息
var shufflingNewsList = document.getElementById("shufflingNewsList");
var shufflingNewsList_Div = shufflingNewsList.getElementsByTagName("div")[0];
var sn_ul = shufflingNewsList.getElementsByTagName("p");

var sn_ul_width = 0;
var shufflingNewsListLeft = 0;


// 轮播
function shuffling(){
	shufflingNewsList.innerHTML += shufflingNewsList.innerHTML;
	window.requestAnimationFrame(function(){
		shufflingNewsListLeft -= 1;
		if(shufflingNewsList.offsetLeft < -(sn_ul_width+10)){
			shufflingNewsListLeft = 0;
		}
		shufflingNewsList.style.left = shufflingNewsListLeft+"px";
		
		window.requestAnimationFrame(arguments.callee);
	})

}

/**
  *
  *	加载图片
  *
  **/
function loading( aimg, fn ){
    var _index = 0;
    for (var i = 0; i < aimg.length; i++) {
        var img = new Image();
        img.src = aimg[i];
        img.onload = function(){
            _index++;
            var p = parseInt((_index / aimg.length*100));
            $("#loading p").css("backgroundPosition","0 -" + parseInt(195*(p/100)) + "px");
            $("#loading span").html(p + "%");

            if (_index == aimg.length) {
                fn && fn();
            };
        }
    };
}

// 随机生成n~m之间（包括n和m）的整数
function numRandom(_startNum, _endNum){
	return Math.floor(_startNum + Math.random()*(_endNum - _startNum + 1));
}

//替换图片
function replaceImg(config){
	for(key in config.image){
		var p = $("[data-name='"+key+"']");	
		if(p.length>0){	
			p.each(function(){
				if (/img/i.test($(this)[0].nodeName)) {
					$(this).attr("src", config.image[key])
				} else {
					$(this).css("background-image", 'url("' + config.image[key] + '")')
				}
			});
		}
	}
};

$(function(){

	window.gamepage = {
		// 初始化调用
		initGame: function(config){
			
			config = config ? config : {};

			// 游戏图片
			config.image = config.image ? config.image : {};

			config.image["BG"] = config.image["BG"] ?config.image["BG"] : (basepath+"BG.jpg");
			config.image["2"] = config.image["2"] ? config.image["2"] : (basepath+"2.png");
			config.image["3"] = config.image["3"] ? config.image["3"] : (basepath+"3.png");
			config.image["bt"] = config.image["bt"] ? config.image["bt"] : (basepath+"bt.png");
			config.image["btn1"] = config.image["btn1"] ? config.image["btn1"] : (basepath+"btn1.png");
			config.image["btn2"] = config.image["btn2"] ? config.image["btn2"] : (basepath+"btn2.png");
			config.image["den1"] = config.image["den1"] ? config.image["den1"] : (basepath+"den1.png");
			config.image["den2"] = config.image["den2"] ? config.image["den2"] : (basepath+"den2.png");
			config.image["den3"] = config.image["den3"] ? config.image["den3"] : (basepath+"den3.png");
			
			//游戏音效
			config.music = config.music?config.music:{};

			config.music['backgroundMusic'] = config.music['backgroundMusic']?config.music['backgroundMusic']:(basepath+'bg_music.mp3');

			// 把游戏参数赋给全局变量
			gameConfig = config;

			// 初始化图片对象变量
			imglist = {
				"BG": gameConfig.image["BG"],
				"2": gameConfig.image["2"],
				"3": gameConfig.image["3"],
				"bt": gameConfig.image["bt"],
				"btn1": gameConfig.image["btn1"],
				"btn2": gameConfig.image["btn2"],
				"den1": gameConfig.image["den1"],
				"den2": gameConfig.image["den2"],
				"den3": gameConfig.image["den3"]
			};

			
			for(var i in imglist){
				loadImgs.push(imglist[i]); // 把图片路径加入加载图片数组
			}
			
			// 替换图片
			replaceImg(gameConfig);
			
			if(window.game.activity.activityId == 45448){
				//隐藏分享
				document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
					WeixinJSBridge.call('hideOptionMenu');
				});
			}
			
			if(window.game.activity.activityId == 57464){
				$(".sv_tip").hide();
				$("#shufflingNews").hide();
				$("#smallView").css("background-image" , "url("+basepath + "2_1.png)");
				$("#wheelBox.prizeList6").css("background-image" , "url("+basepath + "6_03_1.png)");
			}
		},

		// 游戏发起者调用
		startGame: function(){
			loading(loadImgs, function(){
			    // 图片加载完成后执行代码
			    $("#loading").hide(); // 隐藏加载层
		    gameRun(); // 运行游戏
			}); 

		},
		

		// 助力者调用
		startHelpGame: function(){
			loading(loadImgs, function(){
			    // 图片加载完成后执行代码
			    $("#loading").hide(); // 隐藏加载层
			    gameRun(); // 运行游戏
			});
		},

		// 重新开始游戏，游戏结束后会回调gameOverCallback，并带上游戏得分作为回调参数
		restartGame: function(gameOverCallback){
		}
	}
});

function ajaxGame(){
	// 奖品列表
	var listr = "";
	var defaultImg = basepath+"p3.png";
	var defaultImg2 = basepath+"p8.png";

	
	var jp_length = window.game.trophyList.length;
	if(jp_length>0 && jp_length<3){
		var boxNum = 4;
		var liClass = "prize4";
		var divClass = "prizeList4";
		window.rotaAngle_Arr = window.rotaAngle_Arr4;
	}else if(jp_length>2 && jp_length<5){
		var boxNum = 6;
		var liClass = "prize6";
		var divClass = "prizeList6";
		window.rotaAngle_Arr = window.rotaAngle_Arr6;
	}else if(jp_length>4 && jp_length<7){
		var boxNum = 8;
		var liClass = "prize";
		var divClass = "";
		window.rotaAngle_Arr = window.rotaAngle_Arr8;
	}else if(jp_length>6 && jp_length<9){
		var boxNum = 10;
		var liClass = "prize10";
		var divClass = "prizeList10";
		window.rotaAngle_Arr = window.rotaAngle_Arr10;
	}
	
	for(var i=0; i<boxNum; i++){
		if(window.game.trophyList[i]){
			listr += '<li class="'+liClass+'"><h3 class="prize_name">'+window.game.trophyList[i].trophyname+'</h3><img class="prize_img" src="'+(window.game.trophyList[i].awardpic!=""?(window.game.trophyList[i].awardpic):(defaultImg))+'" alt="奖品图片"></li>';
		}else{
			listr += '<li class="'+liClass+'"><h3 class="prize_name">谢谢参与</h3><img class="prize_img" src="'+defaultImg2+'"></li>';
		}
	}
	window.divClass = divClass;
	$("#prizeList").html(listr);
	$("#wheelBox").removeClass().addClass(divClass);
	
	if(activityData.isStart == 1){
	       //活动未开始
		$("#topbar_state").text("未开始");
		   //return;
	   } else if(activityData.isStart == 4){
		   $("#topbar_state").text("已结束");
	   }  
	   else if(activityData.isStart == 3){
		   $("#topbar_state").text("进行中");
	   }
	console.log(activityData.isStart);
	console.log($("#topbar_state"));
	$("#topbar_state").show();
	// 获取活动获奖明细
	$.ajax({
		url:serverroot + '/bigwheel/queryawardrecord.html',
		type:"post",
		dataType:"json",
		data:{
			activityid:window.params.activityid,
			aid:window.params.aid,
        	keyversion:window.params.keyversion,
			start: 0,
			size: 10
		},
		success:function(data){
			//   data =  eval('(' + data + ')');
			if(data.model.records.total>0){
				for(var i in data.model.records.results){
					$("#shufflingNewsList").find(".listBox").eq(0).append('<p><span class="sn_nickName">'+data.model.records.results[i].nickName+'</span>刚刚抽中了<span class="sn_prize">'+data.model.records.results[i].awardName+'</span>，快来试试你的运气吧，分享给朋友后你的中奖率会大增哦！</p>');
				}
				// window.onload = function(){
					for(var i=0; i<sn_ul.length;i++){
						sn_ul_width = sn_ul_width+sn_ul[i].offsetWidth;
					}
					shufflingNewsList_Div.style.width = (sn_ul_width+10)+"px";
		
					shuffling();
			// }
			}
			
		},
		error:function(){
			alert("网络连接错误~")
		}
	});
	// 获取用户的可抽奖次数
	$.ajax({
		url:serverroot + '/bigwheel/query_lottery_chance.html',
		type:"post",
		dataType:"json",
		data:{
			activityid:window.params.activityid,
			aid:window.params.aid,
        	keyversion:window.params.keyversion
		},
		success:function(data){
//			data.model.used //已使用抽奖次数
//			data.model.allow//助力增加抽奖次数
//			data.model.curDay //当天已抽奖次数
//			data.model.daylimit //每天可抽奖次数
//			data.model.actlimit //总抽奖次数限制
			//   data =  eval('(' + data + ')');
			// true 有限制  false 没限制（总抽奖次数）
			if(activityData.limitlottery){
				$("#all_time").text(data.model.actlimit-data.model.used);
			}else{
				$("#all_time").text("无限");
			}
			
			window.allCjNum = data.model.actlimit - data.model.used; // 总共剩余抽奖次数
			window.nowCjNum = data.model.daylimit - data.model.curDay; // 今天剩余抽奖次数
			if(window.game.activity.limitlottery && window.allCjNum < 1  ){
				window.nowCjNum = 0;
			}else if(window.game.activity.limitlottery && window.allCjNum < data.model.daylimit){
				window.nowCjNum = window.allCjNum;
			}
			$("#now_time").text(window.nowCjNum ); // 今天抽奖次数
		},
		error:function(){
			alert("网络连接错误~")
		}
	});
}


function gameRun(){
	
	var wheelBox = document.getElementById('wheelBox'),
		luckyBtn1 = document.getElementById('luckyBtn1'),
		luckyBtn2 = document.getElementById('luckyBtn2'),
		den = document.getElementsByClassName("den"),
		head = document.getElementsByTagName("head")[0];

	var html_style = document.createElement("style");

	var btn_click = true; // 抽奖按钮是否可以点击

	window.rotaAngle_Arr8 = [-22,-112,158,67,-67,-158,112,22];
	window.rotaAngle_Arr4 = [-45, -225, -135, -315]; // 四个奖品旋转角度数组
	window.rotaAngle_Arr6 = [-30, -150, -270, -90, -210, -330]; // 六个奖品旋转角度数组
	window.rotaAngle_Arr10 = [-18, -90, -162, -234, -306, -54, -126, -198, -270, -342]; // 十格旋转角度数组
	window.rotaAngle = 0;
//	window.rotaAngle = rotaAngle_Arr[Math.floor(Math.random()*8)];

	// 请求ajax
	ajaxGame();

	// 大转盘类
	var Wheel = function(){

		var self = this;
		/**
		 *
		 *	跑马灯动画
		 *
		 **/
		self.denIndex = 0;
		self.index = 0;
		self.distance = 2;
		self.den_move = function(){
			self.index++;
			if(self.index >= self.distance){
				for(var i=0; i<den.length; i++){
					den[i].style.display = "none";
				}
				self.index = 0;
				den[self.denIndex].style.display = "block";
				self.denIndex++;
				if(self.denIndex>den.length-1){
					self.denIndex = 0;
				}
			}
		};
		/**
		 *
		 *	添加转盘动画样式
		 *
		 **/
		self.addStyle = true;
		self.luckyAnimation_Style = function(_rotaAngle){
			// if(self.addStyle){
				self.addStyle = false;
				html_style.innerHTML += "@keyframes rotateWheel{from{transform: rotate(0deg);}to{transform: rotate("+(720+_rotaAngle)+"deg);}}";
				html_style.innerHTML += "@-webkit-keyframes rotateWheel{from{-webkit-transform: rotate(0deg);}to{-webkit-transform: rotate("+(720+_rotaAngle)+"deg);}}";
				html_style.innerHTML += "@-moz-keyframes rotateWheel{from{-moz-transform: rotate(0deg);}to{-moz-transform: rotate("+(720+_rotaAngle)+"deg);}}";
				head.appendChild(html_style);
			// }	
		}
	} 

	// 创建大转盘对象
	var wheel = new Wheel();
	// 轮播跑马灯
	/*window.requestAnimationFrame(function(){
		wheel.den_move();
		window.requestAnimationFrame(arguments.callee);
	})*/


	/* 监听CSS3动画事件结束 */
	wheelBox.addEventListener("webkitAnimationEnd", function() {
		luckyBtn1.style.display = "block";
		luckyBtn2.style.display = "none";
		btn_click = true;
	});

	// 点击抽奖按钮
	wheelBox.style.transform = "rotate("+rotaAngle+"deg)";
	luckyBtn1.onclick = function(){

		if (activitySatusWrong()){
        	return false;
        }
 
		
		if(btn_click){
			
			if(window.nowCjNum <= 0){
				window.game.nochanceMask.find(".p_text_1").html("你的抽奖机会已经用完了~");
				window.game.nochanceMask.attr("loaded", true).show();
		
				$(".fire_btn.luckdraw").hide();
				
				return;
			}
			
			music1.play();
			music2.play();
			
			wheelBox.className = "";
			$("#wheelBox").addClass(divClass);
			
			// 判断是否有限制总抽奖次数
			if(game.activity.limitlottery){
				if(window.nowCjNum>0 && window.nowCjNum>0){
					window.allCjNum--; // 总共剩余抽奖次数
				}
			}
			
			if(window.nowCjNum>0){
				window.nowCjNum--; // 今天剩余抽奖次数
			}
		
			// 判断是否有限制总抽奖次数
			if(game.activity.limitlottery){
				$("#all_time").text(window.allCjNum>0?window.allCjNum:0);
			}
			
			$("#now_time").text(window.nowCjNum>0?window.nowCjNum:0);
			
			wheelBox.className = wheelBox.className + " wheelBoxAnimate2";
			btn_click = false;
			luckyBtn1.style.display = "none";
			luckyBtn2.style.display = "block";
			
			setTimeout(function(){
				luckyBtn1.style.display = "block";
				luckyBtn2.style.display = "none";
			},300);
			
			// 抽奖
			$.ajax({
				url : serverroot + '/bigwheel/dolottery.html',
				data : {
					activityid:window.params.activityid,
					aid:window.params.aid,
		        	keyversion:window.params.keyversion,
					"score" : 1
				},
				complete : function(xhr) {
				},
				success : function(data) {
					  // data =  eval('(' + data + ')');
					   console.log(data);
					setTimeout(
						function() {
							window.game.lotteryMask.find(".loader_lotto").css("animation","null");
							if (data.retCode == 0) {
								// 抽中							
								window.awardId = data.model.awardRecord.id; 
								window.awardRecord = data.model.awardRecord;
								
								for(var i=0; i<window.game.trophyList.length; i++){
									if(data.model.awardRecord.id == window.game.trophyList[i].id){
										rotaAngle = rotaAngle_Arr[i];
									}
								}
								
								if (window.game.imgTextShare && window.game.imgTextShare.awardtext !=null) {
									share_content = window.game.imgTextShare.awardtext.replace(/\\${awardName}/g,window.awardRecord.awardname);
								}
								if (window.awardRecord.awardtype == 1
										|| window.awardRecord.awardtype == 4
										|| window.awardRecord.awardtype == 5) {
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
									setTimeout(function(){
										window.game.firePrizeSuccMask.show();
									},1000);
									
									
								} else if (window.awardRecord.awardtype == 2) {
									setTimeout(function(){
										window.game.firePrizeHbSuccMask.show();
									},1000);
									
									// 隐藏ajax请求动画
									window.game.hideAjaxLoading();
								}
							} else if (data.retCode == -38) {
								// 没抽中
								if(rotaAngle_Arr.length>window.game.trophyList.length){
									var num = numRandom(window.game.trophyList.length,rotaAngle_Arr.length-1);
									rotaAngle = rotaAngle_Arr[num];
								}else{
									rotaAngle = 360;
								}
								
								setTimeout(function(){
									window.game.firePrizeFailMask.show();
								},1000);
								
							} else {
//								window.game.alert(data.message);
								window.game.nochanceMask.find(".p_text_1").html(data.message);
								window.game.nochanceMask.attr("loaded", true).show();
							}
							
							setTimeout(function(){
								wheel.luckyAnimation_Style(rotaAngle);
								wheelBox.className = wheelBox.className+" wheelBoxAnimate";
								music2.pause();
								
							}, 30);
							
						}, 3000);
						
				},
				error: function(){
					wheel.luckyAnimation_Style(0);
					wheelBox.className = wheelBox.className+" wheelBoxAnimate";
					music2.pause();
					
					window.game.nochanceMask.find(".p_text_1").html("网络错误~");
					window.game.nochanceMask.attr("loaded", true).show();
				}
			});
		}
	}

	
}




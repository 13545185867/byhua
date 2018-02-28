// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "H+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
// 数字添加千位分隔符
function toThousands(num) {
    var num = (num || 0).toString(), result = '';
    while (num.length > 3) {
        result = ',' + num.slice(-3) + result;
        num = num.slice(0, num.length - 3);
    }
    if (num) { result = num + result; }
    return result;
}
var getAwardName = function (a) {
    a = parseInt(a);
    switch (a) {
        case 1:
            return "奖项一";
        case 2:
            return "奖项二";
        case 3:
            return "奖项三";
        case 4:
            return "奖项四";
        case 5:
            return "奖项五";
        case 6:
            return "奖项六";
        case 7:
            return "奖项七";
        case 8:
            return "奖项八";
    }
};
$.htmlEncode = function (e) {
    return e.replace(/&/g, "&amp;").replace(/ /g, "&nbsp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\n/g, "<br />").replace(/"/g, "&quot;").replace(/'/g, "&#39;");
};

$.htmlDecode = function (e) {
    return e.replace(/&#39;/g, "'").replace(/<[Bb][Rr]\s*(\/)?\s*>/g, "\n").replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&quot;/g, '"').replace(/&amp;/g, "&");
};

$.htmlFilter = function (e) {
    return e == null ? "" : e.replace(/<\s*\/?[^>]*\s*>/g, '').replace(/"/g, '');
}
//初始化省市
var initCitySelector = function () {
    var sel = aSelect({data: aLocation, default_text: "不限制"});
    // 初始省
    sel.bind('#addr_prov', '0');
    // 初始城市
    sel.bind('#addr_city', '0');
};
/**
 * 游戏页面加载完后续的处理，游戏特殊处理
 */
function afterPreviewWinLoad(data) {
	
	
	// 调用子页面方法，保证 init回调成功且子页面加载完成
	if (window.afterInitLoadFn && typeof window.afterInitLoadFn == "function") {
    	window.afterInitLoadFn(data);
    }
	
	// 多选择省市
	 if( window.save_addressList ){
		 console.log( window.save_addressList )
		 pageData.addressList = JSON.parse(window.save_addressList);
		 
    	renderAddressList( pageData.addressList );
    	renderAddressText( pageData.addressList );
    	$("#show_add_area_new").hide();
     	$("#show_view_area_list").show();
    }
 
    
    // 新版砍价活动特殊处理
    if (_isNewBargain) {
        $(".del_award_tab_item").hide();
        $(".add_award_tab_item").hide();
        $("#totalLength_tooltip").popover({
            title: "什么是拿奖总距离",
            trigger: "hover",
            content: "用于活动页面中显示，影响用户助力的次数，限制距离为1～300的整数。"
        });
        $("#elongation_tooltip").popover({
            title: "什么是单次拿奖伸长距离范围",
            trigger: "hover",
            content: "好友拿奖的“手”伸长的距离范围，影响用户拿奖的速度与邀请好友的人数，建议根据拿奖总距离与单次拿奖缩短距离酌情设置。"
        });
        $("#shorten_tooltip").popover({
            title: "什么是单次拿奖缩短距离范围",
            trigger: "hover",
            content: "好友拿奖的“手”缩短的距离范围，影响用户拿奖的速度与邀请好友的人数，建议根据拿奖总距离与单次拿奖伸长距离范围酌情设置。"
        });
    }
     
     
 
    //拼字红包特殊处理
    if (_isNewPzhb) {
        //省略派奖模式
        $("#set_awardSendType_box").hide();
        //省略抽奖门槛
        $("#set_minscore_box").hide();
        //限制只能一次 不可选
        $("#set_singleUserAwards_box #singleUserAwards").attr("disabled", "disabled");
        //默认开启助力 不可注销
        $("#set_withHelp_box #withHelp").attr("disabled", "disabled");

        //抽奖限制不可更改 template中不进行限制（设置比较大的数）
        $("input[name='totalLotteryCount']").attr("disabled", "disabled");
        $("input[name='dayLotteryCount']").attr("disabled", "disabled");

        // 助力效果只留下  助力领奖一个
        $(".helpType_1_cont #helpEffectForHelp").attr("checked", "checked").closest("label").addClass("single-radio");
        $(".helpType_1_cont #helpEffectForHelp").trigger("change");
        $(".helpType_1_cont #helpEffectForAddPlayCount").closest("label").hide();

        //游戏设置隐藏掉游戏时间
        $("#adv_subtab_3 .controls").eq(0).hide();
        $("#adv_subtab_3 label").eq(0).hide();

        // 只支持图片分享
        $("#adv_subtab_2 [name='shareType']:eq(0)").click();
        $("#adv_subtab_2 [name='shareType']:eq(1)").closest("label").hide();
        $("#sharetype_tooltip").hide();

        if (hasPublish) {
            $("input[data-editable='false']").attr("disabled", "disabled");
        }
    }

    // 新版自燃红包
    if(_isNewZrhb){
    	$("#set_daylimit_box .help-inline").hide();
    	$("#set_daylimit_box br").hide();
    	
    	$("#set_awardSendType_box").hide();
    	$("#set_minscore_box").hide();
    	$("#set_withHelp_box #withHelp").attr("disabled" , "disabled");
    	
    	// 助力效果只留下  助力领奖一个
    	$(".helpType_1_cont #helpEffectForHelp").attr("checked","checked").closest("label").addClass("single-radio");
    	$(".helpType_1_cont #helpEffectForHelp").trigger("change");
    	$(".helpType_1_cont #helpEffectForAddPlayCount").closest("label").hide();
    	
    	// 只支持图片分享
    	onlySupportImgShareType();
    	
    	// 没有封面，隐藏一些企业信息
    	$("#adv_subtab_content #set_logo_box").hide();
    	
    	if(hasPublish){
			$("input[data-editable='false']").attr("disabled","disabled");
		}
    }
    // 3d大转盘
    if(_isThreeDPan){
    	// 只支持短图文分享
    	$("#adv_subtab_2 .control-group .controls").find(".radio.inline").eq(0).hide();
    	$("#adv_subtab_2 .control-group .controls").find(".radio.inline").eq(1).show();
		$("#adv_subtab_2 [name='shareType']:eq(1)").click();
		$("#sharetype_tooltip").hide();
		// 隐藏助力效果
		$("#set_withHelp_box #withHelp").removeAttr("checked");
    }
    // 从红包专场页面创建的活动
    if (_isFromHb ) {
    	// 限制只能微信红包
        if (isEdit) {
            $(".award_tab select[name='awardType']").val(2).trigger("change").attr("disabled", "disabled");
        } else {
            $(".award_tab select[name='awardType']").val(2).trigger("change").attr("disabled", "disabled");
        }
    }
    
    // 新版摇一摇红包
    if( _isShakeRedEnvelope ){
    	 if (isEdit) {
    		 initRedpackPrizeInEdit(data.model.records.awardBindPrizes);
         } else {
             $(".award_tab select[name='awardType']").val(2).change().attr("disabled", "disabled");
         }
    	 $("#dayLotteryCount").attr("disabled","disabled");
    }
    
    
    
    // 摇一摇红包雨
    if (_isNewShakeRedpack) {
        $("#set_awardSendType_box").hide();
        $("#set_daylimit_box .help-inline").hide();
        $("#set_daylimit_box br").hide();

        $("#set_minscore_box").hide();

        // 隐藏助力效果
        hideHelpEffect();
        // 处理分享，只支持图文分享
        onlySupportArticleShareType();
        // 隐藏游戏设置
        $(".config_view #game_setting_tab").hide();
        // 隐藏企业logo
        hideLogo();

        if (hasPublish) {
            $("input[data-editable='false']").attr("disabled", "disabled");
        }
    }
    // 咻一咻红包
    if (_isXiuyixiu) {
        $("#set_awardSendType_box").hide();
        $("#set_minscore_box").hide();
        // 隐藏助力效果
        hideHelpEffect();
        // 只支持图文分享
        onlySupportArticleShareType();
        // 隐藏游戏设置
        $(".config_view #game_setting_tab").hide();
        // 隐藏企业logo
        hideLogo();

        // 游戏内容回填第一个奖品的名称
        $$(".game-subtitle span").text($(".award_tab_content input[name=trophyName]").eq(0).val());
        $(".award_tab_content input[name=trophyName]").eq(0).keyup(function (event) {
            $$(".game-subtitle span").text($(event.target).val());
        });

        // 显示总的奖品虚拟数量
        $("#set_globalAwardVirtualNum_box").show();

        $("#set_daylimit_box .help-inline").hide();
        $("#lucky_award_box br").remove();
    }
    // 狂欢大抽奖
    if (_isExciteLottery) {
        $("#set_participant_box input[name=hideParticipant]").click();
        $("#set_participant_box").hide();
        $("#set_awardSendType_box").hide();

        $("#set_daylimit_box .help-inline").hide();
        $("#lucky_award_box br").remove();

        $("#set_minscore_box").hide();

        hideHelpEffect();
        hideLogo();
        hideGameSetting();
        onlySupportArticleShareType();
    }
    // 口令红包
    if (_isKlhb) {
        $("#set_awardSendType_box").hide();
        $("#set_actlimit_box").hide();
        $("#set_daylimit_box").hide();
        $("#set_minscore_box").hide();
        $("#set_singleUserAwards_box #singleUserAwards").attr("disabled", "disabled");
        $("#set_globalProbability_box #globalProbability").val(100).attr("disabled", "disabled");
        $("#set_probabilityType_box").hide();
        hideHelpEffect();
        hideLogo();
        onlySupportArticleShareType();
        hideGameSetting();
        $("#set_globalProbability_box").find(".help-inline").hide();
        
        $("#lucky_award_box #globalProbability").val(100);
        $("#lucky_award_box #set_probabilityMode_box").hide();
    }
    // 经典大转盘
    if (_isBigWheel) {
        //$("#probabilityType").attr("disabled","disabled");
    }

    if (_isNewYyhb) {
        $("#set_awardSendType_box").hide();
        $('input:radio[name="totalLotteryCountRadio"]').eq(1).attr("checked", 'checked');
        $('input:radio[name="totalLotteryCountRadio"]').eq(0).closest("label").hide();
        $('#totalLotteryCount').attr("disabled", false);
        $("#helpEffectForHelp").closest(".radio").hide();
        onlySupportImgShareType();
        hideGameSetting();
        $("#helpEffectForHelp").hide();
        var $allAward = $(".award_tab select[name='awardType']");
        if (isEdit) {
        	$allAward.val(2);  
            // 没有使用的奖品这时候要执行 change事件，否则新增奖品的时候，奖品的内容会有问题
            var awardBindPrizes = data.model.records.awardBindPrizes;
            for(var i=0; i<awardBindPrizes.length; i++){
            	var awardItem = awardBindPrizes[i];
            	if(awardItem.award.deleted){
            		$allAward.eq(i).change();
            	}
            }
            $allAward.attr("disabled", "disabled");
        } else {
        	$allAward.val(2).change().attr("disabled", "disabled");
        }
    }

    if (_isYyttl) {
        $("input[name='totalLotteryCountRadio']").eq(1).closest("label").hide();
        $("#set_globalProbability_box #globalProbability").val(100).attr("disabled", "disabled");
        $('#probabilityType').closest("label").hide();
        $("#set_globalProbability_box").hide();
        $('#set_singleUserAwards_box').hide();
        $('#set_minscore_box').hide();
        $('#set_awardSendType_box').hide();
        $('#game_setting_tab').hide();
        $('.del_award_tab_item').hide();
        $('.add_award_tab_item').hide();
        hideLogo();
        onlySupportImgShareType();
        // 隐藏助力领奖
        $("input[name='helpEffect']").eq(1).closest(".radio").hide();
        $("input[name='helpEffect']").eq(1).hide();
        $("input[name='totalLotteryCountRadio']").eq(0).closest('.controls').css({"margin-top":"9px"});
        $("#globalProbability").val(100).attr("disabled", "disabled");
        // 防止回填不显示
        if (isEdit) {
        	initRedpackPrizeInEdit(data.model.records.awardBindPrizes);
            $("#add_redpack_num").closest(".control-group").hide();
        } else {
            $(".award_tab select[name='awardType']").val(2).change().attr("disabled", "disabled");
        }
        $("input[name='awardRealNum']").attr("disabled", "disabled");
        $('.redpack_award_type').hide();
        $('#add_redpack_num').val(1);
        $('#form_add_redpack_setting .add_redpack_num').hide();
		// 特殊游戏不支持内定中奖
		$(".set_defaultWinner_box").hide();
        
        yyttlUpdateWithdrawPersonCount();
        yyttlBindWithdrawEvent();
    }

    // 百分百大转盘
    if (_isAbsolutelyBigWheel) {
        $("#withHelp").attr("checked", "checked").attr("disabled", "disabled");
        //hideHelpEffect();
        $("#other_cgroup").find("input").attr("disabled", "disabled");
    }
    
    // 众筹起床
    if(_isZcqc){
    	$(".del_award_tab_item").hide();
        $(".add_award_tab_item").hide();
        hideLogo();
        hideGameSetting();
        onlySupportRedpack();
        hideParticipant();
        hideDefaultWinner();
        hideSecurity();
        hideHelpEffect();
        // 隐藏中奖概率模式后边的文字提示
        $("#set_probabilityMode_box .help-inline").hide();
        // 隐藏关注
        $("input[name='participateLimit']").closest(".control-group").hide();
        // 黑名单默认关闭
        $("input[name='blacklist']").eq(1).attr("checked","checked");
        $("input[name='blacklist']").closest(".control-group").hide();
        // 不走三级防刷
        $("input[name='securityLevel']").eq(0).attr("checked","checked");
        // 隐藏奖品名字 
        $("#award_detail_form_0 .control-group").eq(2).hide();
    }
    
    // 砸金蛋
    if(window.templateId == 37){
    	 $("input[name='singleUserAwards']").attr("disabled", "disabled");
    }
    //由于需要等待游戏iframe初始化完成之后才能进行分享二维码自定义页面初始的部分
    initQrcodeBindPage ();
    
	// 默认选中“游戏环节”
	$(".topBar").find(".column").each(function(index,item){
		if($(item).find(".name").html() == "游戏环节"){
			$(item).click();
			return;
		}
	});

    // 待全部处理后在隐藏loading
    $(".mp_loading_cover").hide();
    $(".mp_loading_clip").hide();
    
    // PC端奖品页面添加一个提示
    $$(".award_detail_view_tip").show();
}
function initRedpackPrizeInEdit(awardBindPrizes){
	var $allAward = $(".award_tab select[name='awardType']");
	$allAward.val(2);  
    // 没有使用的奖品这时候要执行 change事件，否则新增奖品的时候，奖品的内容会有问题
    for(var i=0; i<awardBindPrizes.length; i++){
    	var awardItem = awardBindPrizes[i];
    	if(awardItem.award.deleted){
    		$allAward.eq(i).change();
    	}
    }
    $allAward.attr("disabled", "disabled");
}
function yyttlBindWithdrawEvent(){
	var $firstPrize =  $(".yyttl-withdraw").eq(0);
	var $radios = $firstPrize.find("input[name=withdrawThresholdRadio]");
	$radios.change(function(){
		yyttlUpdateWithdrawPersonCount();
	});
	
	$firstPrize.find(".withdrawThreshold_Min").keyup(function(){
		yyttlUpdateWithdrawPersonCount();
	});
	$(".yyttl-withdraw-max .withdrawThreshold_Max").keyup(function(){
		yyttlUpdateWithdrawPersonCount();
	});
	$(".trophy_redpack_text").eq(0).find("input[name=totalCount]").keyup(function(){
		yyttlUpdateWithdrawPersonCount();
	});
}
/** 摇摇天天乐金额验证  **/
function yyttlUpdateWithdrawPersonCount(){
	var $firstPrize =  $(".yyttl-withdraw").eq(0);
	var minRadio = $firstPrize.find("input[name=withdrawThresholdRadio]:checked").val();
	
	var totalCount = Number($('input[name="totalCount"]').val());
	var min = Number($(".withdrawThreshold_Min").val());
	var max = Number($(".withdrawThreshold_Max").val());
	var limitCount = Math.floor(totalCount/((min+max)/2));
	if(minRadio == 1){
		limitCount = Math.floor(totalCount/((max+1)/2));
		$("#may_act").html(limitCount+"人");
		$("input[name='awardRealNum']").val(limitCount);
		$("#smsfreeze_total").html(limitCount);
	}else if(minRadio == 3){
		limitCount = Math.floor(totalCount/((max+3)/2));
		$("#may_act").html(limitCount+"人");
		$("input[name='awardRealNum']").val(limitCount);
		$("#smsfreeze_total").html(limitCount);
	}else{
		$("#may_act").html(limitCount+"人");
		$("input[name='awardRealNum']").val(limitCount);
		$("#smsfreeze_total").html(limitCount);
	}
}

//只支持微信红包奖品
function onlySupportRedpack() {
    $(".award_tab select[name='awardType']").val(2).trigger("change").attr("disabled", "disabled");
}


// 只支持图文分享
function onlySupportArticleShareType() {
    // 处理分享，只支持图文分享
    $("#adv_subtab_2 [name='shareType']:eq(1)").click();
    $("#adv_subtab_2 [name='shareType']:eq(1)").closest("label").addClass("single-radio");
    $("#adv_subtab_2 [name='shareType']:eq(0)").closest("label").hide();
    $("#sharetype_tooltip").hide();
}
//只支持图片分享
function onlySupportImgShareType() {
    $("#adv_subtab_2 [name='shareType']:eq(0)").click();
    $("#adv_subtab_2 [name='shareType']:eq(1)").closest("label").hide();
    $("#sharetype_tooltip").hide();
}
//只支持图文分享
function onlySupportQrcodeShareType() {
    $("#adv_subtab_2 [name='shareType']:eq(1)").click();
    $("#adv_subtab_2 [name='shareType']:eq(0)").closest("label").hide();
    $("#sharetype_tooltip").hide();
}
// 隐藏助力效果
function hideHelpEffect() {
    // 隐藏助力效果
    $("#set_withHelp_box #withHelp").removeAttr("checked");
    $("#set_withHelp_box").hide();
    $(".helpType_1_cont").closest(".control-group").hide();
}
// 隐藏企业logo
function hideLogo() {
    $("#adv_subtab_content #set_logo_box").hide();
}
// 隐藏版权设置
function hideCopyright() {
	$("#set_copyright_box").hide();
}
// 隐藏游戏设置
function hideGameSetting() {
    $(".config_view #game_setting_tab").hide();
}
// 隐藏参与人数
function hideParticipant() {
	$("#set_participant_box input[name=hideParticipant]:eq(1)").attr("checked","checked");
	$("#set_participant_box").hide();
}
// 隐藏奖项中的内定中奖人
function hideDefaultWinner(){
	$(".award_detail_form .set_defaultWinner_box").hide();
}
// 隐藏红包安全机制
function hideSecurity(){
	$("#securityLevel_2_tips").hide();
	$("#security_choice_group").hide();
}
/**
 * 初始化页面显示
 */
// 加载默认模板和默认值
var load_default_template = function (templateId) {
    // 默认生成8个奖项，只有一个奖项显示
    var tab_item = {items: []};
    for (var i = 0; i < 8; i++) {
        var trophyName = getTrophyName(1, i);
        tab_item.items.push({
            "index": i + 1,
            "hidden": i > ((_isZqJiZi || _isZqBoBing || _isNewBargain || _isJiWuFu || _isGuangGun || _isWanSheng || _isYyttl || _isZcqc || _isValuationHunter) ? 0 : 2),
            "awardName": HdGame.getLevelName(i + 1),
            "operationTip": "凭券联系现场工作人员兑奖",
            "storeAddress": "请填写您的兑奖地址或者门店地址",
            "trophyName": trophyName,
            "integralConvert": 8 - i
        });
    }
    // 模板状态下是未发布
    hasPublish = false;
    // 初始化活动开始和结束时间
    var now_time = new Date().getTime();
    var seven_day_time = now_time + 7 * 24 * 60 * 60 * 1000;
    $("#startDate").val(new Date(now_time).Format("yyyy-MM-dd HH:mm:ss"));
    $("#endDate").val(new Date(seven_day_time).Format("yyyy-MM-dd HH:mm:ss"));
    // 众筹起床初始化 TODO
    if(_isZcqc){
    	$("#startTime").timepicker({
    		ampm: true,
    		hourMin: 6,
    		hourMax: 9,
    		timeFormat: 'hh:mm'
    	}).val("06:00");
    	$("#endTime").timepicker({
    		ampm: true,
    		hourMin: 6,
    		hourMax: 9,
    		timeFormat: 'hh:mm'
    	}).val("06:15");
    }
    // 加载奖项页签
    load_award_tab(tab_item);

    // 初始化奖品时间，默认7天有效期
    $("input[name='validityStart']").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
    $("input[name='validityStop']").val(new Date(seven_day_time).Format("yyyy-MM-dd HH:mm:ss"));
    // 初始化开始和结束时间
    $("input[name='validityStart']").datetimepicker();
    var validityStart = new Date().getTime($('#validityStart').val());
    $("input[name='validityStop']").datetimepicker({
        minDate: new Date(validityStart)
    });

    // 加载template内容
    $.ajax({
        url: serverroot+'/content/bigwheel/back/files/zhp1.html',
        timeout: 15000,
        dataType: "json",
        data: {
            id: pageTemplateId,
            uid: global_uid
        },
        success: function (data) {
            if (data.ret == 0) {
                var tempData = data.model.records;
                console.log(data);
                window.actGamePagePath = tempData.path;
                $("#actName").text(tempData.activityName);
                $("input[name='title']").val(tempData.activityName);
                $("input[name='gameTime']").val(tempData.gameTime);
                $("input[name='passCondition']").val(tempData.passThreshhold);
                $("input[name='totalLotteryCount']").val(tempData.totalLotteryCount);
                $("input[name='dayLotteryCount']").val(tempData.dayLotteryCount);
                $("input[name='singleUserAwards']").val(tempData.singleUserAwards);
                if (tempData.limitLottery) {
                    $("input[name='totalLotteryCountRadio']").eq(1).attr("checked", "checked");
                }
                $("textarea[name='ruleTips']").val(tempData.activityRule);
                $("#helpType").val(tempData.helpType);
                if (tempData.helpType == 1) {
                    $(".helpType_1_cont").show();
                    $(".helpType_2_cont").hide();
                } else {
                    $(".helpType_1_cont").hide();
                    $(".helpType_2_cont").show();
                }
                $("input[name='shareType']").eq(0).attr("checked", "checked").trigger("change");
                $("input[name='probabilityMode']").eq(0).attr("checked", "checked").trigger("change");                
                $("input[name='awardSendType']").eq(tempData.helpType == 1 ? 0 : 1).attr("checked", "checked");
                $("#organizerLogo").val(static_res_prefix + "/content/bigwheel/back/files/lot/organizer_logo.jpg");
                $("#openClickPic").val(static_res_prefix + "/content/bigwheel/back/files/lot/open_click_btn.png");
                $("input[name='securityLevel'][value='2']").attr("checked", "checked").trigger("change");
                // 联动更新手机编辑页的内容
                var triggerTimer = setInterval(function () {
                    if (previewWinHasLoad) {
                        try {
                            $("#ruleTips").trigger("keyup");
                            $("#globalProbability").trigger("keyup");
                            $("#initialProbability").trigger("keyup"); // 初始中奖率
                            $("#helpProbability").trigger("keyup"); // 助力成功率
                            $("#helpNum").trigger("keyup"); // 助力成功人数
                            $("#organizer").trigger("keyup");
                            $("#startDate").trigger("change");
                            $("#passCondition").trigger("keyup");
                            $("input[name='awardName']").eq(0).trigger("keyup");
                            $("input[name='awardSendType']").trigger("change");

                            // 口令红包祝福语
                            if ($("#set_bless_box").length > 0) {
                                $("#bless").trigger("keyup");
                            }
                            // 口令红包商家名称
                            if ($("#set_seller_name_box").length > 0) {
                                $("#seller_name").trigger("keyup");
                            }
                            // 奖品详情-输入框编辑联动
                            triggerAwardDetailPhoneTxt(true);

                            // 游戏特殊处理
                            afterPreviewWinLoad();
                            // 后台编辑我的奖品列表显示用
                            var $awardRecord = $('<div data-index="0" class="myprize_item s0"><p class="award_name">' + $("input[name='awardName']").eq(0).val() + '</p><p class="expired_time">' + $("input[name='trophyName']").eq(0).val() + '</p><p class="exchange_statu">未领取<p></div>');
                            $$(".fire_rule .myprize_list_cont").prepend($awardRecord);
                            window.clearInterval(triggerTimer);

                        } catch (e) {
                            window.clearInterval(triggerTimer);
                            console && console.error(e);
                        }
                    }
                    initBecomeGrey();
                }, 200);                
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("服务器正在紧张的运行中，请稍后再试");
        }
    });
};

// 若是编辑页面，加载活动配置
var load_activity = function (activityId) {
    $.ajax({
        url: '/admin/new_activity/getActivityWithAllInfoAndGamePage',
        timeout: 15000,
        dataType: "json",
        data: {
            aid: activityId,
            uid: global_uid
        },
        success: function (data) {
            if (data.ret == 0) {
                //根据中奖方式隐藏总中奖概率
                if(data.model.records.awardSendType==2){
                    $('#set_probabilityMode_box .controls:eq(1),#set_probabilityMode_box .probabilityInfo').hide();
                }

                // 回填活动信息
                if (data.model.total <= 0) {
                    alert("您查询的活动不存在");
                    return;
                }
                console.log(data);
                var activity = data.model.records.activity;
                window.activityJson = activity;
                window.activityJson.aidEncrypt = data.model.aidEncrypt;
                var imgTextShare = data.model.records.imgTextShare;
                window.actPageId = activity.pageId;

                /*	总开关
                 *  获取开关-extraData&Math.pow(2,(index-1))
                 *  设置开关-无需改变直接将页面中开关的value相加即可
                 *  第8位-分享形式
                 *  第7位-安慰奖
                 *  第6位-助力效果
                 *  第5位-黑名单
                 * */
                var extraData = activity.extraData;

                // 游戏页面编辑配置数据
                if (getPreviewWin().Edit && getPreviewWin().Edit.originDef) {
                    var gamePageJson = $.parseJSON(data.model.records.gamePage.jsonContent);
                    console.log("gamePageJson：" + gamePageJson);
                }
                
                $("#actName").text(activity.title);
                // 基础配置
                $("#title").val(activity.title);
                $("#startDate").val(new Date(activity.start).Format("yyyy-MM-dd HH:mm:ss"));
                $("#endDate").val(new Date(activity.end).Format("yyyy-MM-dd HH:mm:ss"));
                $("#ruleTips").val(activity.ruleTips);
                $("#organizer").val(activity.organizer);
                // 是否显示弹幕
                $("input[name='showDanmu']").eq((extraData & (Math.pow(2, 11))) == 0 ? 1 : 0).attr("checked", "checked");
                $("#organizerLink").val(activity.organizerLink);
                $("input[name='hideOrganizerLogo']").eq(activity.hideOrganizerLogo ? 0 : 1).attr("checked", "checked");
                if (activity.hideOrganizerLogo) {
                    $(".orgLogoControls").hide().find("input").attr("disabled", "disabled");
                } else {
                    $(".orgLogoControls").show().find("input").removeAttr("disabled");
                    if (activity.organizerLogo.indexOf("http") == -1) {
                        $(".orgLogoControls .thumb_img").attr("src", user_img_domain + activity.organizerLogo);
                    } else {
                        $(".orgLogoControls .thumb_img").attr("src", activity.organizerLogo);
                    }
                }
                $("#organizerLogo").val(activity.organizerLogo || static_res_prefix + "/content/bigwheel/back/files/lot/organizer_logo.jpg");

                $("input[name='useDefaultQrcodeBg']").eq(activity.useDefaultQrcodeBg ? 0 : 1).attr("checked", "checked");
                
                // 派奖设置
                $("input[name='awardSendType']").eq(activity.awardSendType == 2 ? 0 : 1).attr("checked", "checked");
                if (activity.awardSendType == 1) {
                    // 排名派发
                    var rankingSendRule = data.model.records.rankingSendRule;
                    window.rankingSendRuleId = rankingSendRule.id;
                    $(".helpType_1_cont").hide();
                    $(".helpType_2_cont").show();
                } else if (activity.awardSendType == 2) {
                    // 抽奖派发
                    var challengeSendRule = data.model.records.challengeSendRule;
                    console.log(challengeSendRule)
                    window.challengeSendRuleId = challengeSendRule.id;
                    $("#passCondition").val(challengeSendRule.passCondition);
                    $("#singleDayAwards").val(challengeSendRule.singleDayAwards);
                    $("#singleUserAwards").val(challengeSendRule.singleUserAwards);
                    $(".helpType_1_cont").show();
                    $(".helpType_2_cont").hide();
                }
                // 获取分享形式(8)开关，0为二维码，1为分享图文
                $("input[name='shareType']").eq((extraData & (Math.pow(2, 7))) == 0 ? 0 : 1).attr("checked", "checked");
                $("#adv_subtab_2").attr("shareId", imgTextShare.id);
                if ((extraData & (Math.pow(2, 7))) == 0 || !imgTextShare) {
                    $("#set_qrcodebg_box").show();
                    $("#wxshare_box").hide();
                    // 分享二维码背景图
                    if (activity.useDefaultQrcodeBg) {
                        $(".qrcodeBgControls").hide().find("input").attr("disabled", "disabled");
                    } else {
                        $(".qrcodeBgControls").show().find("input").removeAttr("disabled");
                        $(".qrcodeBgControls .thumb_img").attr("src", user_img_domain + activity.shareQrcodeBg);
                    }
                    $("#shareQrcodeBg").val(activity.shareQrcodeBg);
                } else {
                    $("#set_qrcodebg_box").hide();
                    $("#wxshare_box").show();
                    // 微信分享图标
                    $("input[name='useShareIco']").eq(imgTextShare.sttr1 == 0 || "" ? 0 : 1).attr("checked", "checked");
                    if (imgTextShare.sttr1 == "0" || "") {	// 默认
                        $("#share_ico_controls").hide().find("input").attr("disabled", "disabled");
                    } else {
                        $("#share_ico_controls").show().find("input").removeAttr("disabled");
                    }
                    if (imgTextShare.imgPath) {
                        $("#share_ico_controls .thumb_img").attr("src", user_img_domain + imgTextShare.imgPath);
                    }
                    $("#shareIco").val(imgTextShare.imgPath);
                    // 微信分享内容
                    $("input[name='useShareTxt']").eq(imgTextShare.sttr2 == "0" ? 0 : 1).attr("checked", "checked");
                    $("#share_txt_controls .j-share-txt-content").eq(0).html(imgTextShare.noAwardText.replace(/\${nickName}/g, '<span class="tag" contenteditable="false">玩家名称</span>').replace(/\${score}/g, '<span class="tag" contenteditable="false">游戏成绩</span>').replace(/\${rank}/g, '<span class="tag" contenteditable="false">游戏排名</span>'));
                    $("#share_txt_controls .j-share-txt-content").eq(1).html(imgTextShare.awardText.replace(/\${nickName}/g, '<span class="tag" contenteditable="false">玩家名称</span>').replace(/\${score}/g, '<span class="tag" contenteditable="false">游戏成绩</span>').replace(/\${rank}/g, '<span class="tag" contenteditable="false">游戏排名</span>').replace(/\${awardName}/g, '<span class="tag" contenteditable="false">奖项等级</span>').replace(/\${trophyName}/g, '<span class="tag" contenteditable="false">奖品名称</span>'));
                    if (imgTextShare.sttr2 == 1) {
                        $("#share_txt_controls").show();
                    }
                    // ! 微信分享标题,回填
                    $("input[name='useShareTitle']").eq(imgTextShare.sttr3 == "0" ? 0 : 1).attr("checked", "checked");
                    $("#share_title_controls .j-share-title-content").eq(0).html(imgTextShare.titleText.replace(/\${nickName}/g, '<span class="tag" contenteditable="false">玩家名称</span>'));
                    if (imgTextShare.sttr3 == 1) {
                        $("#share_title_controls").show();
                    }
                }
                $("input[name='useDefaultCopyright']").eq(activity.useDefaultCopyright ? 0 : 1).attr("checked", "checked");
                if (activity.useDefaultCopyright) {
                    $(".copyrightPicControls").hide().find("input").attr("disabled", "disabled");
                } else {
                    $(".copyrightPicControls").show().find("input").removeAttr("disabled");
                    $(".copyrightPicControls .thumb_img").attr("src", user_img_domain + activity.customCopyrightPic);
                }
                $("#customCopyrightPic").val(activity.customCopyrightPic);
                //$("#useDefaultQrcodeBg").val(activity.useDefaultQrcodeBg);                
                if (activity.probabilityType == 1) {//开启奖品均匀发放                	
                    $("#probabilityType").attr("checked", "checked");
                }
                $("input[name='hideParticipant']").eq(activity.hideParticipant ? 1 : 0).attr("checked", "checked");
                $("#participantVirtualAddCount").val(activity.participantVirtualAddCount);
                $("input[name='trophyValidator']").eq(activity.trophyValidator ? 0 : 1).attr("checked", "checked");
                // 黑名单(5)开关，0关闭，1开启
                $("input[name='blacklist']").eq((extraData & (Math.pow(2, 4))) == 0 ? 1 : 0).attr("checked", "checked");

                // 安全验证模式
                $("input[name='securityType']").eq((extraData & (Math.pow(2, 13))) == 0 ? 1 : 0).attr("checked", "checked").trigger("change");
                
                $("input[name='showCopyright']").eq(activity.showCopyright ? 1 : 0).attr("checked", "checked");
                $("#globalProbability").val(activity.globalProbability);
                $("#initialProbability").val(activity.initialProbability); // 初始中奖率
                $("#helpProbability").val(activity.helpProbability); // 助力成功率
                $("#helpNum").val(activity.helpNum); // 助力成功人数
                // 红包安全机制
                $("input[name='securityLevel'][value='" + activity.securityLevel + "']").attr("checked", "checked").trigger("change");
                pageTemplateId = activity.pageTemplateId;
                window.actGamePagePath = activity.path;
                // 参与配置
                var playRule = data.model.records.playRule;
                // 多选择省市
                if ( playRule.needArea ) {
                	window.save_needArea = true;
                	if( playRule.areaCode ){
                		window.save_addressList = playRule.areaCode; // 回写数据
                	}else{
                		window.save_addressList = "";
                	}
                	
                	window.save_province = playRule.province; // 省
                    window.save_city = playRule.city; // 市
                }
               // 老的判断区域限制
              /*  if (playRule.needArea) {
                    $("#needLimitAddress").attr("checked", true);
                    $("#addressGroup").show();
                    $("#addr_prov").find("option").each(function (index, item) {
                        if ($(item).text() == playRule.province) {
                            $(item).attr("selected", true);
                            return false;
                        }
                    });
                    $("#addr_prov").trigger("change");
                    $("#addr_city").find("option").each(function (index, item) {
                        if ($(item).text() == playRule.city) {
                            $(item).attr("selected", true);
                            return false;
                        }
                    });
                    var selected_city = playRule.city;
                    setTimeout(function () {
                        $("#addr_city").find("option").each(function (index, item) {
                            if ($(item).text() == selected_city) {
                                $(item).attr("selected", true);
                                return false;
                            }
                        });
                    }, 2000);
                }*/

                // 助力效果(6)开关，0增加抽奖次数 ，1助力领奖
                $("input[name='helpEffect']").eq((extraData & (Math.pow(2, 5))) == 0 ? 0 : 1).attr("checked", "checked");
                $("input[name='helpEffect']").trigger("change", (extraData & (Math.pow(2, 5))));
                // 助力规则
                var helpRule = data.model.records.helpRule;
                $("#withHelp").attr("checked", activity.withHelp);
                if (activity.withHelp) {
                    if (helpRule) {
                        $("#percentage").val(helpRule.percentage).attr("disabled", false);
                        $("#helpCount").val(helpRule.helpCount).attr("disabled", true);
                        $("#exchangeCount").val(helpRule.exchangeCount).attr("disabled", true);
                        $("#minCount").val(helpRule.minCount).attr("disabled", true);
                        $("#maxCount").val(helpRule.maxCount).attr("disabled", true);
                    }
                    $("#gamePointAddPercent").removeAttr("disabled");
                    $(".helpType_1_cont, .helpType_2_cont").find("input").removeAttr("disabled");
                } else {
                    $("#gamePointAddPercent").attr("disabled", "disabled");
                    $(".helpType_1_cont, .helpType_2_cont").find("input").attr("disabled", "disabled");
                }
                var extendOperation = $.parseJSON(activity.extendOperation);
                
                // 主办单位开启跳转
                $("input[name='jumpLinkBtn']").eq(extendOperation.jumpLinkBtn==1 ? 0 : 1).attr("checked", "checked");
                if(extendOperation.jumpLinkBtn==1){
                	$("#set_jumpLink2_box").hide().find("input").attr("disabled", "disabled");

                }else if(extendOperation.jumpLinkBtn==2){
                	 $("#set_jumpLink2_box").show().find("input").removeAttr("disabled");
                }
                
                // 口令红包祝福语回填
                if ($("input[name='bless']").length > 0) {
                    $("input[name='bless']").val(extendOperation.bless);
                }
                // 口令红包商家名称回填
                if ($("#set_seller_name_box").length > 0) {
                    $("#seller_name").val(extendOperation.seller_name);
                }

                if ($("textarea[name='commandWord']").length > 0) {
                    $("textarea[name='commandWord']").val(extendOperation.commandWord);
                }
                if (helpRule != null) {
                    $("#lotteryAddCount").val(helpRule.lotteryAddCount);
                    if (extendOperation) {
                        $("#awardCycleTime").val(extendOperation.awardCycleTime);
                        $("#unlockPrizeCount").val(extendOperation.unlockPrizeCount);
                    }
                    $("#gamePointAddPercent").val(helpRule.gamepointAddPercent);
                    $("#helpType").val(helpRule.helpType);
                }
                if (extendOperation && extendOperation.loadingLink) {
                    $("#openingLink").val(extendOperation.loadingLink);
                }
                if (extendOperation && extendOperation.copyrightLink) {
                    $("#copyrightLink").val(extendOperation.copyrightLink);
                }
                if (extendOperation && extendOperation.isOpenClick) {
                    $("input[name='isOpenClick']").eq(extendOperation.isOpenClick ? 1 : 0).attr("checked", "checked");
                }
                if (extendOperation && extendOperation.isOpenClick) {
                    $(".openClickControls").show().find("input").removeAttr("disabled");
                    if (extendOperation.openClickPic.indexOf("http") == -1) {
                        $(".openClickControls .thumb_img").attr("src", user_img_domain + extendOperation.openClickPic);
                    } else {
                        $(".openClickControls .thumb_img").attr("src", extendOperation.openClickPic);
                    }
                } else {
                    $(".openClickControls").hide().find("input").attr("disabled", "disabled");
                }
                $("#openClickPic").val(extendOperation.openClickPic || static_res_prefix + "/content/bigwheel/back/files/lot/open_click_btn.png");

                if (extendOperation && extendOperation.collect) {
                    $("#collect").val(extendOperation.collect);
                }
                // 百分百大转盘
                if (extendOperation && extendOperation.initialProbability) {
                    $("#initialProbability").val(extendOperation.initialProbability);
                }
                if (extendOperation && extendOperation.helpNum) {
                    $("#helpNum").val(extendOperation.helpNum);
                }
                if (extendOperation && extendOperation.slogan) {
                    //$("#slogan").val(extendOperation.slogan);
                    // 集五福、新版砍价特殊处理
                    if (_isJiWuFu && extendOperation.slogan != "") {
                        var $jiwufuTextForm = $("#form_jiwufu_gametext_setting");
                        $("input[name='jiwufu_slogan_radio']", $jiwufuTextForm).eq(1).attr("checked", "checked");
                        $("input[name='jiwufu_slogan_radio']", $jiwufuTextForm).trigger("change");
                        try {
                            var cusText = extendOperation.slogan.split("::");
                            $("#cus_jiwufu_text_cont input").each(function (index, item) {
                                var $input = $(item);
                                $input.val(cusText[index]);
                            });
                        } catch (e) {

                        }
                    }
                    // 奥运火炬
                    if (templateId == 14) {
                    	var $jiwufuTextForm = $("#form_jiwufu_gametext_setting");
                        $("input[name='jiwufu_slogan_radio']", $jiwufuTextForm).eq(1).attr("checked", "checked");
                        $("input[name='jiwufu_slogan_radio']", $jiwufuTextForm).trigger("change");
                        try {
                            var cusText = extendOperation.slogan.split("::");
                            $("#cus_jiwufu_text_cont input").each(function (index, item) {
                                var $input = $(item);
                                $input.val(cusText[index]);
                            });
                        } catch (e) {

                        }
                    }
                }

                // 新版砍价特殊处理
                if (_isNewBargain) {
                    $("input[name='totalLength']").val(extendOperation.totalLength);
                    $("input[name='elongationMin']").val(extendOperation.elongationMin);
                    $("input[name='elongationMax']").val(extendOperation.elongationMax);
                    $("input[name='shortenMin']").val(extendOperation.shortenMin);
                    $("input[name='shortenMax']").val(extendOperation.shortenMax)
                }
                // 众筹起床
                if(_isZcqc){
                	$("#startTime").val(extendOperation.startTime);
                	$("#endTime").val(extendOperation.endTime);
                }
                
                // 估价猎人
                if( _isValuationHunter ){
                	
                	if(extendOperation.hunter){
                		
                		var hunter_json = extendOperation.hunter;
                    	
                    	var li_list = $("#valuationHunter_goodsImg_modal").find(".hunter_ul").find("li");
                    	
                    	for(var i=0; i<hunter_json.size; i++){
  	
                    		var numArr = hunter_json[ (i+1)+"_size" ].match(/\d+/g);
                    		
                    		setTimeout(function(){
                    			$$(".vh_good").attr( "src", hunter_json["1_path"] );
                    			$$(".vh_tip2_1").text("以上物品价格为"+hunter_json[ "1_size" ].match(/\d+/g)[0]+"~"+hunter_json[ "1_size" ].match(/\d+/g)[1]+"元" );
                    		},1200);
                    		
                    		
                    		// 隐藏input
                    		li_list.eq(i).find(".fileupload_input").val( hunter_json[ (i+1)+"_path" ] );
                    		// 展示图片
                    		li_list.eq(i).find(".hunter_img").attr( "src", hunter_json[ (i+1)+"_path" ] );
                    		// 最小范围
                    		li_list.eq(i).find(".hunter_minNum").val( numArr[0] );
                    		// 最大范围
                    		li_list.eq(i).find(".hunter_maxNum").val( numArr[1] );
                    		
                    		// 显示出来
                    		li_list.eq(i).removeClass("hunter_li_hide");
                    		
                    		
                    		$(function(){
                    			$(".hunter_add_btn").hide();
								$(".hunter_delete_btn").hide();
								var v_length = $("#valuationHunter_goodsImg_modal").find("li").length-1;
								var v_length2 = $(".hunter_li_hide").length;
								$("#valuationHunter_goodsImg_modal").find("li").eq( v_length - v_length2 -1 ).find(".hunter_delete_btn").show();
										
								if( (v_length - v_length2) < 5 ){
									$("#valuationHunter_goodsImg_modal").find("li").eq( v_length - v_length2 -1 ).find(".hunter_add_btn").show();
								}
                    		})	
                    		
                    		// 如果是发布状态
                    		if(_hasPublish){
                    			$(".hunter_minNum").attr("disabled","disabled");
                        		$(".hunter_maxNum").attr("disabled","disabled");
                        		$(".hunter_add_btn").hide();
                        		$(".hunter_delete_btn").hide();
                    		}
                    		
                    		
                    	}
                	}
                }
                
                

                $("#dayCount").val(playRule.dayCount);
                $("#dayIncrCount").val(playRule.dayIncrCount);
                $("#initCount").val(playRule.initCount);
                $("#playCount").val(playRule.playCount);
                $("#totalLotteryCount").val(playRule.totalLotteryCount);
                $("input[name='totalLotteryCountRadio']").eq(playRule.limitLottery ? 1 : 0).attr("checked", "checked");
                if (playRule.limitLottery) {
                    $("#totalLotteryCount").removeAttr("disabled");
                }
                $("#dayLotteryCount").val(playRule.dayLotteryCount);
                $("input[name='gameTimeRadio']").eq(1).attr("checked", "checked");
                $("input[name='forceAttent']").eq(playRule.needFollow ? 0 : 1).attr("checked", "checked");
                /**
                 if(playRule.gameTimeType==0){
        			$("#gameTime").attr("disabled","disabled")
        		} else {
        			$("#gameTime").removeAttr("disabled");
        		}
                 **/
                $("#gameTime").val(playRule.gameTime);
                $("input[name='participateLimit']").eq(playRule.participateLimit ? 0 : 1).attr("checked", "checked");
                $("input[name='exchangeLimit']").eq(playRule.exchangeLimit ? 0 : 1).attr("checked", "checked").trigger("change");
                // 兑奖信息是否需要收集地址
                if ((extraData & (Math.pow(2, 2))) != 0) {
                    $("input[name='exchangeLimitDetail'][value='4']").attr("checked", "checked");
                }
                // 兑奖信息是否需要收集姓名
                if ((extraData & (Math.pow(2, 1))) != 0) {
                    $("input[name='exchangeLimitDetail'][value='2']").attr("checked", "checked");
                }
                // 兑奖信息是否需要收集扩展信息
                if ((extraData & (Math.pow(2, 14))) != 0) {
                	//$("input[name='exchangeLimitDetail'][value='6']").attr("checked", "checked");
                	if(extendOperation.checkAwardExtend){
                		var checkAwardExtend = extendOperation.checkAwardExtend.split(',');
                        var html = '';
                        if(checkAwardExtend.length>=2){
                            $('#set_exchangeLimitDetail_box .add-undefind-box').addClass('hide');
                        }
                        for(var i = 0;i<checkAwardExtend.length;i++){
                            html += '<div class="exchange-content">'
                                 +      '<label class="checkbox inline">'
                                 +          '<span class="exchangeName">'+checkAwardExtend[i]+'</span>'
                                 +      '</label>'
                                 +      '<span class="close-btn">&times;</span>'
                                 + '</div>';
                        }
                        $('#set_exchangeLimitDetail_box .exchangeLimitDetail-content').append(html);
                	}
                }
                // 奖项配置
                var awardBindPrizes = data.model.records.awardBindPrizes;
                $("#awardCount").attr("disabled", true);
                $("#awardCount").val(awardBindPrizes.length);
                // 红包总数量
                var totalHBawardRealNum = 0;
                var tab_item = {items: []};
                for (var i = 0; i < awardBindPrizes.length; i++) {
                    var trophyName = getTrophyName(1, i);
                    if (awardBindPrizes[i].award.operationTip == "") {
                        awardBindPrizes[i].award.operationTip = "凭券联系现场工作人员兑奖";
                    }
                    if (awardBindPrizes[i].award.storeAddress == "") {
                        awardBindPrizes[i].award.storeAddress = "请填写您的兑奖地址或者门店地址";
                    }
                    tab_item.items.push({
                        "hidden": awardBindPrizes[i].award.deleted,
                        "index": i + 1,
                        "awardId": awardBindPrizes[i].award.id,
                        "trophyId": awardBindPrizes[i].trophy.id,
                        "trophyName": awardBindPrizes[i].trophy.trophyName,
                        "operationTip": awardBindPrizes[i].award.operationTip,
                        "storeAddress": awardBindPrizes[i].award.storeAddress,
                        "description": awardBindPrizes[i].award.description,
                        "subhead": awardBindPrizes[i].award.subhead,
                        "serviceTel": awardBindPrizes[i].award.serviceTel,
                        "integralConvert": awardBindPrizes[i].award.integralConvert,
                    });
                    if (!awardBindPrizes[i].award.deleted) {
                        if (awardBindPrizes[i].trophy.awardType == 2) {
                            totalHBawardRealNum += awardBindPrizes[i].trophy.awardRealNum;
                        }
                    }
                }
                $("#smsfreeze_total").text(totalHBawardRealNum);
                hasPublish = activity.statu != 0 && !isReCreate;
                // 加载奖项页签
                load_award_tab(tab_item);
               
        		for (var i = 0; i < awardBindPrizes.length; i ++) {
        			// 回显奖项
        			var $tab = $(".award_tab_" + i);
        			var award = awardBindPrizes[i].award;
        			var awardCustom = $.parseJSON(award.custom);
        			$tab.find("select[name='awardLevel']").val(award.awardLevel);
        			$tab.find("input[name='awardName']").val(award.awardName);
        			// 如果是按总中奖概率则默认单个奖项的概率为0
        			if((extraData & (Math.pow(2, 10))) == 0){
        				$tab.find("input[name='probabilityValue']").val(10);
        			} else {
        				$tab.find("input[name='probabilityValue']").val(award.probabilityValue);
        			}
        			var trophy = awardBindPrizes[i].trophy;
        			var extendData = awardBindPrizes[i].extendData;
        			$tab.find("select[name='awardType']").val(trophy.awardType);
        			$tab.find("input[name='trophyName']").val(trophy.trophyName);
        			$tab.find("input[name='awardRealNum']").val(trophy.awardRealNum);
        			$tab.find("input[name='awardVirtualNum']").val(trophy.awardVirtualNum);
        			$tab.find("input[name='totalCount']").val(trophy.totalCount/100).trigger("keyup");
        			$tab.find("input[name='singleMin']").val(trophy.singleMin/100);
        			$tab.find("input[name='singleMax']").val(trophy.singleMax/100);
        			$tab.find("input[name='validityStart']").val(new Date(trophy.validityStart).Format("yyyy-MM-dd HH:mm:ss"));
        			$tab.find("input[name='validityStop']").val(new Date(trophy.validityStop).Format("yyyy-MM-dd HH:mm:ss"));
        			$tab.find("select[name='awardType']").trigger("change",true);
        			$tab.find("input[name='isWxCard']").eq(trophy.isWxCard?0:1).attr("checked","checked");
        			// 是否内定中奖人
        			if((award.logicSwitch & (Math.pow(2, 0))) == 0){
        				$tab.find("input[name='defaultWinner']").eq(0).attr("checked","checked").trigger("change");
        			} else {
        				$tab.find("input[name='defaultWinner']").eq(1).attr("checked","checked").trigger("change");
        				$tab.find("input[name='defaultWinnerAmount']").val(awardCustom.setSwitchNum);
        				//$tab.find(".awardNumLabel").text("正常奖品数量");
        			}
        			
        			// 跳转按钮
        			if(awardCustom && awardCustom.guanwanJumpBtn){
        				if(awardCustom.guanwanJumpBtn == "true"){
        					$tab.find("input[name='guanwanJumpBtn']").eq(1).attr("checked","checked").trigger("change");
        					$tab.find(".jumpBtnNameBox").show().find("input[name='jumpBtnName']").val(awardCustom.jumpBtnName);
        					$tab.find(".jumpLinkBox").show().find("input[name='jumpLink']").val(awardCustom.jumpLink);
        					$$("#jumpBtn").find(".text").text(awardCustom.jumpBtnName);
        				}
        			} 
        			
            		// 初始化奖品图片上传控件
            		initFileUpload($tab.find("input[name='fileupload']"),function(data,$cont){
            			 
            			 for(var i=0; i<$(".award_tab").length; i++){
            				 if(!$(".award_tab").eq(i).is(":hidden")){
            					 var indexId =$(".award_tab").eq(i).attr("data-id");
            					 break;
            				 }
            			 }
            			console.log(indexId)
            			// 大转盘
            			$$("#prizeList").find("li").eq(indexId).find(".prize_img").attr("src",data._response.result.fileUrl);
            			console.log(data._response.result.fileUrl);
            			$tab.find("input[name='prize_image']").val(data._response.result.fileUrl);
            			$tab.find(".prize_thumb_img").attr("src",data._response.result.fileUrl);
            		});
            		
            		
            		
        			if(hasPublish){
        				if (trophy.awardType == 2) {
        					$tab.find("input[name='awardRealNum']").closest(".redpack_award_type").children(".control-label").text("红包剩余：");
        					$tab.find("input[name='awardRealNum']").attr("disabled",true);
        					$tab.find(".trophy_redpack_text").show();
        					$tab.find(".trophy_datetime").hide().find("input").attr("disabled","disabled");
        					$tab.find(".j-sncode-type").hide().find("textarea").attr("disabled","disabled");
        					$tab.find(".add_redpack_award").removeClass("hide");
        				} else {
        					$tab.find(".add_sncode_award").removeClass("hide");
        					$tab.find("input[name='awardRealNum']").attr("disabled","disabled");
        				}
        				$tab.find("input[name='awardRealNum']").val(extendData.dataMap.enableNum);
            			$tab.find("input[name='awardLoseNum']").val(Number(trophy.awardRealNum) - Number(extendData.dataMap.enableNum));
            			$tab.find(".awardLoseBox").show();
            			
            			// 内定中奖人
            			//$tab.find(".awardDefaultWinnerBox").show();
            			$tab.find(".defaultWinnerLoseBox").show();
            			var switchInitNum = extendData.dataMap.switchInitNum || 0;
            			var switchUsedNum = extendData.dataMap.switchUsedNum || 0;
            			// 是否内定中奖人
            			if((award.logicSwitch & (Math.pow(2, 0))) != 0){
            				//$tab.find("input[name='awardRealNum']").val(extendData.dataMap.enableNum);
                			//$tab.find("input[name='awardLoseNum']").val(Number(trophy.awardRealNum) - Number(extendData.dataMap.enableNum));	
            			}
            			$tab.find("input[name='defaultWinnerAmount']").attr("disabled","disabled").val(switchInitNum-switchUsedNum);
            			$tab.find("input[name='defaultWinnerLoseNum']").val(switchUsedNum);
            			$tab.find("input[name='awardDefaultWinnerNum']").val(switchInitNum);
            			$tab.find(".defaultWinnerAmount-tips").hide();
            			$tab.find(".add_defaultwinner_award").show();
            			
        				$tab.find("input[name='validityStart']").attr("disabled",true);
        				$tab.find("input[name='validityStop']").attr("disabled",true);
        				$tab.find("input[name='trophyName']").attr("disabled",true);
        				$tab.find("input[name='totalCount']").attr("disabled",true);
        				$tab.find("input[name='singleMin']").attr("disabled",true);
        				$tab.find("input[name='singleMax']").attr("disabled",true);
        			}
        			// sn码自定义(券号，1为系统生成，3为手工导入)
        			var awardType = Number(trophy.awardType);
        			var deleted = trophy.deleted;
        			if (awardType != 2 && !deleted) {
        				var trophyId = Number($("input[name='trophyId']").eq(i).val());
            			var sncodeType = Number(trophy.snGenerateType);
            			var sncodeInput = $tab.find("input[name='sncodeType']");
            			var sncodeControls = sncodeInput.closest(".controls").siblings(".j-sncode-controls");
            			if (sncodeType === 3) {
            				sncodeInput.eq(0).attr("checked", "false");
            				sncodeInput.eq(1).attr("checked", "checked");
            				sncodeControls.show();
            				sncodeControls.children("textarea").removeAttr("disabled");
            				// 校验sn码
            				bindSncodeCheck();
            				// 信息回填
            				var sncodesArray = data.model.records._userImportSnMap;
        					var unusedSncodes = sncodesArray[trophyId]['0'];
        					var usedSncodes = sncodesArray[trophyId]['1'];
            				sncodeControls.children("textarea").html(unusedSncodes.toString().replace(/,/g, "\n"));
            				// 将已派发的sn码加到dom中
            				sncodeControls.children("textarea").attr("usedSncodes", usedSncodes);
            			}
        			}
            		 
            		 
        		}
        		
        		// 加载页面图片回写特殊处理
                if(extendOperation && extendOperation.loadingPic){           
                	var loadingPic_str = extendOperation.loadingPic;
                	var loadingPic_url = loadingPic_str.replace(/(^url\(")/,"").replace(/"\)$/,"");
                	$(".loadPagePicControls").eq(0).find(".thumb_img").attr("src",loadingPic_url);
                	$(".loadPagePicControls").eq(0).find(".fileupload_input").val(loadingPic_url);
                	
                	var defaulurl = $(".loadPagePicControls").eq(0).find("input[name='fileupload']").attr("data-defaulturl");
                	if(defaulurl != loadingPic_url){
                		$("input[name='useDefaultLoadPage']").eq(1).attr("checked","checked").trigger("change");
                    	$(".loadPagePicControls").eq(0).find(".huifuBtn").show();
                	}

                }
                
                if(extendOperation && extendOperation.loadingLogo){
                	var loadingLogo_str = extendOperation.loadingLogo;
                	var loadingLogo_url = loadingLogo_str.replace(/(^url\(")/,"").replace(/"\)$/,"");
                	$(".loadPagePicControls").eq(1).find(".thumb_img").attr("src",loadingLogo_url);
                	$(".loadPagePicControls").eq(1).find(".fileupload_input").val(loadingLogo_url);
                	
                	var defaulurl = $(".loadPagePicControls").eq(1).find("input[name='fileupload']").attr("data-defaulturl");
                	if(defaulurl != loadingLogo_url){
                		$("input[name='useDefaultLoadPage']").eq(1).attr("checked","checked").trigger("change");
                    	$(".loadPagePicControls").eq(1).find(".huifuBtn").show();
                	}
                	
                }
                
        		// 高级配置
        		$("#wxname").val(activity.wxname);
        		$("#wxlink").val(activity.wxlink);
        		// 加载广告是否开启跳转(4)开关，0关闭，1开启
        		$("input[name='open_opening_link']").eq((extraData&(Math.pow(2,3))) == 0?0:1).attr("checked","checked");
        		if($("input[name='open_opening_link']:checked").val() == 0){
        			$("#opening_link, #set_openClick_box").hide();
        			$(".openClickControls").hide().find("input").attr("disabled","disabled");
        		} else {
        			$("#opening_link, #set_openClick_box").show();
        		}
        		 
        		// 初始化开始和结束时间
        		$("input[name='validityStart']").datetimepicker();
        		var validityStart = new Date().getTime($('#validityStart').val());
        		$("input[name='validityStop']").datetimepicker({
        			minDate: new Date(validityStart)
        		});
        		  
                // 中奖概率模式开关，1按总中奖概率 ，1按单个奖项中奖概率
                $("input[name='probabilityMode']").eq((extraData & (Math.pow(2, 10))) == 0 ? 0 : 1).attr("checked", "checked");
        		if(hasPublish){
        			$("input[data-editable='false']").attr("disabled","disabled");
        			$("select[data-editable='false']").attr("disabled","disabled");
        			$(".del_award_tab_item,.add_award_tab_item").hide();
        		}
        	} else {
        		alert(data.msg);
        	}
        	
        	// 联动更新手机编辑页的内容
        	var triggerTimer = setInterval(function(){
        		if(previewWinHasLoad){
        			try{
        				$("#ruleTips").trigger("keyup");
        				$("#globalProbability").trigger("keyup");
        				$("#initialProbability").trigger("keyup"); // 初始中奖率
        				$("#helpProbability").trigger("keyup"); // 助力成功率
        				$("#helpNum").trigger("keyup"); // 助力成功人数
        				$("#organizer").trigger("keyup");
        				$("#startDate").trigger("change");
        				$("#passCondition").trigger("keyup");
        				$("input[name='awardName']").eq(0).trigger("keyup");
        				$("input[name='awardSendType']").trigger("change");
        				$("input[name='probabilityMode']").trigger("change");
        				$("input[name='showDanmu']").trigger("change");
        				if(activity.showCopyright){
        					$$("#start_bottom").show();
        				} else {
        					$$("#start_bottom").hide();
        				}
        				if(activity.hideParticipant){
        					$$("#join_line_cont").hide();
        				} else {
        					$$("#join_line_cont").show();
        				}
        				if(!activity.useDefaultCopyright){
        					$$("#startLogoImg").attr("src",user_img_domain+activity.customCopyrightPic);
        				}
        				if(!activity.useDefaultQrcodeBg){
        					$$("#help_qrcode_img").attr("src",user_img_domain+activity.shareQrcodeBg);
        				}
        				if(!activity.hideOrganizerLogo){
                			$$(".organizer-btn").show();
                		}
        				if (extendOperation && extendOperation.isOpenClick){
        					$$(".openClick-btn").show();
        				}
        				
        				// 口令红包祝福语
        				if ($("#set_bless_box").length > 0) {
        					$("#bless").trigger("keyup");
        				}
        				// 口令红包商家名称
        				if ($("#set_seller_name_box").length > 0) {
        					$("#seller_name").trigger("keyup");
        				}
        				
        				// 奖品详情-输入框编辑联动
						triggerAwardDetailPhoneTxt(true);
						
        				// 后台编辑我的奖品列表显示用
					    var $awardRecord = $('<div data-index="0" class="myprize_item s0"><p class="award_name">'+$("input[name='awardName']").eq(0).val()+'</p><p class="expired_time">'+$("input[name='trophyName']").eq(0).val()+'</p><p class="exchange_statu">未领取<p></div>');
					    $$(".fire_rule .myprize_list_cont").prepend($awardRecord);
					    
					    // 摇一摇红包雨
		        		if(_isNewShakeRedpack){
		        			if(extendOperation && extendOperation.redpackText){
		        				// 处理红包文字回填
		        				var $redpackTextList = $$(".redpack_text p");
		        				var items = extendOperation.redpackText.split("::");
		            			for(var i=0; i<items.length; i++){
		            				var item = items[i];
		            				$("#newshakeredpack_redpacktext_"+(i+1)).val(item);
		            				$redpackTextList.eq(i).text(item);
		            			}
		        			}
		        		}
		        		if(_isNewPzhb){
		        			// 新版拼字红包
		        			extendOperation && extendOperation.newPzhb1 && $("#newPzhb1").val(extendOperation.newPzhb1);
		        			extendOperation && extendOperation.newPzhb2 && $("#newPzhb2").val(extendOperation.newPzhb2);
		        			extendOperation && extendOperation.newPzhb3 && $("#newPzhb3").val(extendOperation.newPzhb3);
		        		}
		        		
					    // 设置总的虚拟奖品数量，此字段目前只有咻一咻红包使用，但其他活动也可以使用
		        		$("#set_globalAwardVirtualNum_box #globalAwardVirtualNum").val(extendOperation.globalAwardVirtualNum || 0); 
					    // 游戏特殊处理
					    afterPreviewWinLoad(data);
					    
		        		if ($("input[name='helpEffect']:checked").val() === "32") {
		        			if (extraData&Math.pow(2,(1-1)) === 1) {	// 动态助力
	        					$("#helpAward").hide();
	        					$("#helpAward2").show();
	        					$("#helpEffectMs1").trigger("click");
	        				} else {	// 固定助力
	        					$("#helpAward").show();
	        					$("#helpAward2").hide();
	        					$("#helpEffectMs2").trigger("click");
	        				}
		        		}
	        			if(extendOperation && extendOperation.awardCycleTime){
	        				$("#awardCycleTime2").val(extendOperation.awardCycleTime);
	        			}
	        			if(extendOperation && extendOperation.unlockPrizeCount){
	        				$("#unlockPrizeCount2").val(extendOperation.unlockPrizeCount);
	        			}
	        			
	        			// 全景红包
		        		if(_isPanoredpack){
		        			$("#lotteryCount").remove();
		        			if(hasPublish){
		        				$(".helpType_1_cont").find("input").attr("disabled","disabled");
		        			}
		        		}
		        		
					    // 大转盘特殊处理
		               if (_isBigWheel || _isAbsolutelyBigWheel) {
		                    // 在大转盘显示奖品名字
		                    var at = JSON.parse(activity.awardTips);
		                    if(at.length>0 && at.length<3){    
		                    	$$("#wheelBox").removeClass().addClass("prizeList4");
		            			$$("#prizeList").find("li").removeClass().addClass("prize4");
		            		}else if(at.length>2 && at.length<5){
		            			
		            			$$("#wheelBox").removeClass().addClass("prizeList6");
		            			$$("#prizeList").find("li").removeClass().addClass("prize6");
		            		}else if(at.length>4 && at.length<7){	
		            			$$("#wheelBox").removeClass();
		            			$$("#prizeList").find("li").removeClass().addClass("prize");
		            		}else if(at.length>6 && at.length<9){
		            			$$("#wheelBox").removeClass().addClass("prizeList10");
		            			$$("#prizeList").find("li").removeClass().addClass("prize10");
		            		}
		                    setTimeout(function () {
		                        for (ai in at) {
		                            $$("#prizeList").find("li").eq(ai).find(".prize_name").text(at[ai].trophyName);
		                        	// 平台资源
			                		$$("#prizeList").find("li").eq(ai).find(".prize_img").attr("src",static_res_prefix+"/content/bigwheel/back/files/lot/p3.png");
			                		
		                        }
		                    }, 0);
		                }
					    
					    // 回写大转盘奖品图片
		                var at = JSON.parse(activity.awardTips);
					    console.log(awardBindPrizes)
					    for (var i = 0; i < awardBindPrizes.length; i ++) {
					    	if(i>at.length-1){
					    		break;
					    	}
					    	var $tab = $(".award_tab_" + i);
					    	var award = awardBindPrizes[i].award;
					    	if(award.awardPic){
	                			console.log(award.awardPic)
	                			console.log($tab.find("input[name='prize_image']"))
	                			$tab.find("input[name='prize_image']").val(award.awardPic);
	                			$tab.find(".prize_thumb_img").attr("src",user_img_domain+award.awardPic);
	                			// 用户上传
	                			$$("#prizeList").find("li").eq(i).find(".prize_img").attr("src",user_img_domain+award.awardPic);
	                		}
					    }
	            					    
        				window.clearInterval(triggerTimer);
        				
        			}catch(e){	
        				window.clearInterval(triggerTimer);
     			   }
        		}        		
        	},200);
        	initBecomeGrey();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("服务器正在紧张的运行中，请稍后再试");
        }
    });


};

var lipin_award = ["价值100元礼品券", "价值50元礼品券", "价值10元礼品券", "价值5元礼品券", "价值2元礼品券", "价值1元礼品券", "价值1元礼品券", "价值1元礼品券"];
var youhui_award = ["100元优惠券", "50元优惠券", "10元优惠券", "5元优惠券", "2元优惠券", "1元优惠券", "1元优惠券", "1元优惠券"];
var dikou_award = ["100元抵扣券", "50元抵扣券", "10元抵扣券", "5元抵扣券", "2元抵扣券", "1元抵扣券", "1元抵扣券", "1元抵扣券"];
var hongbao_award = ["100元现金红包", "50元现金红包", "10元现金红包", "5元现金红包", "2元现金红包", "1元现金红包", "1元现金红包", "1元现金红包"];

var getTrophyName = function (trophyType, idx) {
    if (trophyType == 1 || trophyType == "1") {
        return lipin_award[idx];
    } else if (trophyType == 2 || trophyType == "2") {
        return hongbao_award[idx];
    } else if (trophyType == 4 || trophyType == "4") {
        return youhui_award[idx];
    } else if (trophyType == 5 || trophyType == "5") {
        return dikou_award[idx];
    }
};

// 加载奖项奖品
var load_award_tab = function (tab_item) {
    // item 模板
    var award_tab_item_tpl = document.getElementById("award_tab_item_tpl").text;
    var award_tab_content_tpl = document.getElementById("award_tab_content_tpl").text;

    var award_tab_item_html = $.template(award_tab_item_tpl).render(tab_item);
    var award_tab_content_html = $.template(award_tab_content_tpl).render(tab_item);

    $(".award_tab_item").html(award_tab_item_html);
    $(".award_tab_content").html(award_tab_content_html);

    $(".award_tab_item_link").off("click").on("click", function () {
        $(".award_tab_item>li").removeClass("active");
        $(this).closest("li").addClass("active");
        $(".award_tab").hide();
        $(".award_tab_" + $(this).data("idx")).show();

        // 奖品详情-触发输入框编辑联动
        triggerAwardDetailPhoneTxt();
    });
    var singleMin = 1;
    var singleMax = 200;
    if(_isZcqc){ // 众筹起床特殊处理
    	singleMin = 0.1;
    	singleMax = 5;
    }
    // 在加载奖品HTML的时候初始化表单校验
    $(".award_tab_content .award_tab").each(function (index, item) {
        $(item).find("form").validate({
            onfocusout: false,
            rules: {
                awardName: {required: true, maxlength: 16},
                trophyName: {required: true, maxlength: 16},
                awardRealNum: {required: true, awardNum: true},
                defaultWinnerAmount: {required: true, defaultWinnerAmount: true},
                probabilityValue: {required: true, digits: true, probabilityValueTotal: true},
                totalCount: {required: true, number: true, min: 1, max: 400000, validateTotalMoney: true},
                singleMin: {required: true, number: true, min: singleMin, max: singleMax},
                singleMax: {required: true, number: true, min: singleMin, max: singleMax, singleMax: true},
                validityStart: {required: true},
                operationTip: {required: true},
                sncodes: {required: !hasPublish, snCode: true},
                validityStop: {required: true, validityStop: true},
                detailLink: {url: true},
                virtualNum: {required: true, digits: true, min: 0, max: 400000},
                totalLength: {required: true, digits: true, min: 1, max: 300},
                withdrawThresholdMin: {required: true,number: true,min: 1,max:200,withdrawThresholdMin:true},//TODO
    			withdrawThresholdMax: {required: true,number: true,min: 1,max:200,withdrawThresholdMax:true},
                jumpBtnName:{required: true, maxlength: 16},
                jumpLink: {required: true, url: true}
            },
            messages: {
                awardName: {required: "不能为空", maxlength: "不能超过16个字符"},
                trophyName: {required: "不能为空", maxlength: "不能超过16个字符"},
                awardRealNum: {required: "不能为空", awardNum: "奖品总数量在1-10000之间"},
                defaultWinnerAmount: {required: "不能为空", defaultWinnerAmount: "内定奖品数量必须大于0且不能大于奖品数量"},
                probabilityValue: {required: "不能为空", digits: "必需为数值", probabilityValueTotal: "所有奖项中奖概率之和不能大于100%"},
                totalCount: {required: "不能为空", number: "必需为数值", min: "数值至少为1", max: "数值最大为400000"},
                singleMin: {required: "不能为空", number: "必需为数值", min: "数值至少为1", max: "数值最大为200"},
                singleMax: {required: "不能为空", number: "必需为数值", min: "数值至少为1", max: "数值最大为200"},
                validityStart: {required: "不能为空"},
                operationTip: {required: "不能为空"},
                sncodes: {required: "不能为空", snCode: "券号格式错误"},
                validityStop: {required: "不能为空"},
                virtualNum: {required: "不能为空", digits: "必需为数值", min: "数值至少为0", max: "数值最大为400000"},
                totalLength: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为300"},
                withdrawThresholdMin: {required: "不能为空",number: "必需为数值", min: "数值至少为1", max: "数值最大为200"},//TODO
    			withdrawThresholdMax: {required: "不能为空",number: "必需为数值", min: "数值至少为1", max: "数值最大为200"},
    			jumpBtnName:{required: "不能为空", maxlength: "不能超过16个字符"},
    			jumpLink: {required: "不能为空", url: "格式错误"}
            },
            showErrors: function (errorMap, errorList) {
                if (errorList && errorList.length > 0) {
                    $.each(errorList,
                        function (index, obj) {
                            var item = $(obj.element);
                            if (item.is(".cover")) {
                                alert(obj.message);
                            }
                            // 给输入框添加出错样式
                            item.closest(".control-group").addClass('error');
                            item.attr("title", obj.message);
                        });
                } else {
                    var item = $(this.currentElements);
                    item.closest(".control-group").removeClass('error');
                    item.removeAttr("title");
                }
            },
            submitHandler: function () {
                return false;
            }
        });
    });   
	/** 摇摇天天乐特殊配置 start **/ // TODO
	$.validator.addMethod("withdrawThresholdMin",function(value,element,params){
		if(_isYyttl){
			var totalCount = Number($('input[name="totalCount"]').val());
			var min = Number(value); // 输入框中值
			var realMin;
			var max = Number($(".withdrawThreshold_Max").val());
			var minRadio = $("input[name='withdrawThresholdRadio']:checked").val();
			if(minRadio == 1){
				realMin = 1;
			}else if(minRadio == 3){
				realMin = 3;
			}else if(minRadio == 4){
				realMin = Number($(".withdrawThreshold_Min").val());
			}
			var limitCount = Math.floor(totalCount/((+realMin+max)/2));
			if(realMin <= max && realMin <= 200 && realMin >= 1 && limitCount > 0){
				return true;
			}else{
				return false;
			}
		}
	},"1.提现最高金额门槛要大于提现最低金额门槛2.红包总金额至少可供1个用户参与游戏3.红包最高金额限制为200元以内");
	$.validator.addMethod("withdrawThresholdMax",function(value,element,params){
		if(_isYyttl){
			var totalCount = Number($('input[name="totalCount"]').val());
			var max = Number(value);
			var min = Number($(".withdrawThreshold_Min").val());
			var realMin;
			var minRadio = $("input[name='withdrawThresholdRadio']:checked").val();
			if(minRadio == 1){
				realMin = 1;
			}else if(minRadio == 3){
				realMin = 3;
			}else if(minRadio == 4){
				realMin = Number($(".withdrawThreshold_Min").val());
			}
			var limitCount = Math.floor(totalCount/((+realMin+max)/2));
			if(max >= realMin && max >= 1 && max <= 200 && limitCount > 0){
				return true;
			}else{
				return false;
			}
		}
	},"1.提现最高金额门槛要大于提现最低金额门槛2.红包总金额至少可供1个用户参与游戏3.红包最高金额限制为200元以内");
	/** 摇摇天天乐特殊配置 end **/
	
    // 得奖限制-光棍节
    if ($("#award_convert_limit_form").length > 0) {
        var award_convert_limit_tpl = document.getElementById("award_convert_limit_tpl").text;
        var award_convert_limit_html = $.template(award_convert_limit_tpl).render(tab_item);
        $("#award_convert_limit").html(award_convert_limit_html);

        // 得奖限制表单校验
        var award_convert_limit_validator = $("#award_convert_limit_form").validate({
            onfocusout: false,
            rules: {
                integralConvert0: {required: true, digits: true, min: 1, max: 999},
                integralConvert1: {required: true, digits: true, min: 1, max: 999},
                integralConvert2: {required: true, digits: true, min: 1, max: 999},
                integralConvert3: {required: true, digits: true, min: 1, max: 999},
                integralConvert4: {required: true, digits: true, min: 1, max: 999},
                integralConvert5: {required: true, digits: true, min: 1, max: 999},
                integralConvert6: {required: true, digits: true, min: 1, max: 999},
                integralConvert7: {required: true, digits: true, min: 1, max: 999},
                percentage: {required: true, digits: true, min: 1, max: 100}
            },
            messages: {
                integralConvert0: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert1: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert2: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert3: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert4: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert5: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert6: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                integralConvert7: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为999"},
                percentage: {required: "不能为空", digits: "必需为数值", min: "数值至少为1", max: "数值最大为100"}
            },
            showErrors: function (errorMap, errorList) {
                if (errorList && errorList.length > 0) {
                    $.each(errorList,
                        function (index, obj) {
                            var item = $(obj.element);
                            if (item.is(".cover")) {
                                alert(obj.message);
                            }
                            // 给输入框添加出错样式
                            item.closest(".control-group").addClass('error');
                            item.attr("title", obj.message);
                        });
                } else {
                    var item = $(this.currentElements);
                    item.closest(".control-group").removeClass('error');
                    item.removeAttr("title");
                }
            },
            submitHandler: function () {
                return false;
            }
        });
    }
};

// 校验sn码-用于导航切换时模拟点击所有奖项页面的检测按钮
function sncodeCheck($checkBtn) {
    if ($checkBtn.hasClass("disabled")) {
        return false;
    }
    $checkBtn.addClass("disabled").text("检测中...");
    var sncodeControls = $checkBtn.closest(".control-group").children(".j-sncode-controls");
    var sncodeTxt = sncodeControls.children("textarea");
    // 兼容在奖品数量修改弹出框的校验逻辑
    var $addAwardModal = $checkBtn.closest("#add_sncode_award_modal");
    if ($addAwardModal.size() > 0) {
        var awardRealNum = Number($addAwardModal.find("input[name='award_unsend_num']").val());
    } else {
        var awardRealNum = Number(sncodeControls.closest(".control-group").siblings(".redpack_award_type").find("input[name='awardRealNum']").val());
    }

    // 获取文本域里的数值并转换为数组
    var sncodeArray = sncodeTxt.val().split(/\n/g);
    var sncodeArrayLen = sncodeArray.length;
    // 奖品发布之后输入框可以为0或空
    if (hasPublish && awardRealNum === 0) {
        if (sncodeTxt.val() && sncodeArrayLen !== awardRealNum) {
            sncodeControls.find(".j-sncode-msg").text("当前券号数量为" + sncodeArray.length + "个，与奖品数量" + awardRealNum + "不一致");
            $checkBtn.removeClass("disabled").text("检测格式");
            return false;
        }
        sncodeControls.find(".j-sncode-msg").text("检测通过！");
        $checkBtn.removeClass("disabled").text("检测格式");
        return true;
    } else {
        // 检测数量
        if (sncodeArrayLen !== awardRealNum) {
            sncodeControls.find(".j-sncode-msg").text("当前券号数量为" + sncodeArray.length + "个，与奖品数量" + awardRealNum + "不一致");
            $checkBtn.removeClass("disabled").text("检测格式");
            return false;
        }
        // 检测空值
        if (!sncodeTxt.val()) {
            sncodeControls.find(".j-sncode-msg").text("券号不能为空！");
            $checkBtn.removeClass("disabled").text("检测格式");
            return false;
        }
        // 检测特殊字符
        for (var i = 0; i < sncodeArrayLen; i++) {
            // 消空格
            sncodeArray[i] = sncodeArray[i].replace(/\s/g, "");
            if (!(/^[0-9a-zA-Z]{1,20}$/g).test(sncodeArray[i]) || sncodeArray[i] == 0) {
                sncodeControls.find(".j-sncode-msg").html("格式错误，券号格式为20以内的数字和字母！<br/ >错误券号为：" + sncodeArray[i]);
                $checkBtn.removeClass("disabled").text("检测格式");
                return false;
            }
        }
        // 检测是否重复
        var repeatCodeArray = [];
        var exitSncode = sncodeTxt.attr("usedsncodes");
        if (exitSncode) {
            var allSncodeArray = sncodeArray.concat(exitSncode.split(","));
        } else {
            var allSncodeArray = sncodeArray;
        }
        for (var i = 0; i < allSncodeArray.length; i++) {
            var thisCode = allSncodeArray[i];
            for (var j = 0; j < allSncodeArray.length; j++) {
                var otherCode = allSncodeArray[j];
                if (i != j && otherCode == thisCode) {
                    if (repeatCodeArray.indexOf(thisCode) == -1) {
                        repeatCodeArray.push(thisCode);
                    }
                }
            }
        }
        if (repeatCodeArray.length > 0) {
            sncodeControls.find(".j-sncode-msg").text("券号重复，重复券号为：" + repeatCodeArray.join("、"));
            $checkBtn.removeClass("disabled").text("检测格式");
            return false;
        } else {
            sncodeControls.find(".j-sncode-msg").text("检测通过！");
            $checkBtn.removeClass("disabled").text("检测格式");
            return true;
        }
    }
}

// 绑定校验sn码-用于点击检测按钮
function bindSncodeCheck() {
    $(".award_tab form").each(function () {
        var $form = $(this);
        $form.find(".j-check-sncode").on("click", function () {
            sncodeCheck($(this));
        });
    });
}

// 插入微信分享内容
function insertShareTxt() {
    var shareTxtControls = $("body");
    shareTxtControls.on("click", ".j-insert-tag", function () {
        var shareTxt = $(this).text();
        var shareTxtContent = $(this).closest(".j-share-txt-box").children(".j-share-txt-content");
        var text = $(this).text();
        var html = $('<span class="tag" contenteditable="false">' + shareTxt + '</span>');
        var sel = window.getSelection();
        var range = shareTxtContent.data('range');
        if (!range) {
            shareTxtContent.append(html);
        } else {
            sel.removeAllRanges();
            sel.addRange(range);
            range.deleteContents();
            range.insertNode(html[0]);
            range = range.cloneRange();
            range.setStartAfter(html[0]);
            range.collapse(true);
            sel.removeAllRanges();
            sel.addRange(range);
            shareTxtContent.data('range', range);
        }
        shareTxtContent.find('br').remove();
        shareTxtContent.append('<br>');
    });
}

// 分享内容光标插入
$("body").on('click', ".j-share-txt-content", function (e) {
    if ($(e.target).hasClass('tag')) {
        return;
    }
    ;
    var sel = window.getSelection();
    $(this).data('range', sel.getRangeAt(0).cloneRange());
}).on('keyup paste',".j-share-txt-content", function () {
    var sel = window.getSelection();
    $(this).data('range', sel.getRangeAt(0).cloneRange());
}).on('blur', ".j-share-txt-content",function () {
    checkShareContet(this);
}).on("focus",".j-share-txt-content",function () {
    $(this).removeClass("error");
    $(this).siblings(".j-share-msg").hide();
});

// 微信分享内容校验
function checkShareContet(_target) {
    var content = $(_target).html().replace(/[ ]/g, "").replace("<br>", "");
    // 检验非空
    if (!content) {
        $(_target).addClass("error");
        $(_target).siblings(".j-share-msg").text("自定义分享内容不能为空！").show();
        $("input[name='checkShareTxt']").val(0);
        return false;
    }
    var sensitiveList = ["红包"];
    var wordsList = [];
    var wordStr = '';
    for (var i = 0; i < sensitiveList.length; i++) {
        var sensitive = sensitiveList[i];
        if (content.indexOf(sensitive) != -1) {
            wordsList.push('“' + sensitive + '”');
        }
    }
    for (var j = 0; j < wordsList.length; j++) {
        wordStr += wordsList[j];
        if (j < wordsList.length - 1) {
            wordStr += "、";
        }
    }
    if (wordsList.length > 0) {
        $(_target).addClass("error");
        $(_target).siblings(".j-share-msg").text("分享内容存在微信敏感词" + wordStr + "，请先修改。").show();
        $("input[name='checkShareTxt']").val(0);
        return false;
    } else {
        $("input[name='checkShareTxt']").val(1);
        return true;
    }
}

// 校验奖品数量（总数10000）
function awardNumCheck(input, limit) {
	// 一般类奖项总数
    var awardSnTotalNum = 0;
    // 红包类奖项总数
    var awardRedPackTotalNum = 0;
    var awardSnLimit = 10000;
    var awardRedPackLimit = 10000;
    if(_packageType == 5){
    	// 有娱体验版
    	awardRedPackLimit = 10000;
    } else if(_packageType == 2){
    	// 有娱标准版
    	awardRedPackLimit = 50000;
    } else if(_packageType == 3){
    	// 有娱专业版
    	awardRedPackLimit = 100000;
    } else if(_packageType == 4){
    	// 有娱旗舰版
    	awardRedPackLimit = 300000;
    	awardSnLimit = 50000;
    } else if(_packageType == 10){
    	// 有娱集团版
    	awardRedPackLimit = 500000;
    	awardSnLimit = 100000;
    }
    var $this = input || "";
    var isRedPackAward = $this.closest(".award_tab").find("select[name='awardType']").val() == 2;
    // (单个奖项的奖品数量+已发放的奖品数量)必须大于0
    if (Number(input.val()) + Number(input.parent().find("input[name='awardLoseNum']").val()) < 0) {
        return false;
    }
    // 获取所有奖项的总和
    $(".prize_tab_item:not('.hide')").each(function () {
        // 根据tab获取奖项个数
        var award_idx = $(this).children("a").attr("data-idx");
        // 获取当前tab下的奖品数量
        var award_real_num = Number($(".award_tab_" + award_idx).find("input[name='awardRealNum']").val());
        // 获取已领的奖品数量
        var award_lose_num = Number($(".award_tab_" + award_idx).find("input[name='awardLoseNum']").val());
        // 计算总和
        if($(".award_tab_" + award_idx).find("select[name='awardType']").val() == 2){
        	awardRedPackTotalNum = awardRedPackTotalNum + award_real_num + award_lose_num;
        } else {
        	// 发布后修改奖品数量的校验
        	if(input.is("#add_sncode_award_num") && 
        			$(".award_tab_" + award_idx).attr("data-awardid") == $("#add_sncode_award_modal").attr("data-awardid")){
        		// 忽略这个数量，后面再加上input里面的数量
        	} else {
        		awardSnTotalNum = awardSnTotalNum + award_real_num + award_lose_num;
        	}
        }
    });

    if ($("input[name='vehicle']").is(":checked")) {
        var limit = 1000000;
    } else {
        var limit = 10000;
    }
    // 加上追加红包的数量校验
    if (input.is("#add_redpack_num")) {
    	awardRedPackTotalNum += Number(input.val());
    	isRedPackAward = true;
    }
    // 加上追加实物奖品的数量校验
    if (input.is("#add_sncode_award_num")) {
    	awardSnTotalNum += Number(input.val());
    	isRedPackAward = false;
    }
    if (!isRedPackAward && (awardSnTotalNum > awardSnLimit || awardSnTotalNum == 0)) {
        $this.closest(".control-group").addClass("error");
        $this.siblings(".j-awardNum-tips").html('<span class="help-inline">活动的奖券类奖品总数必须大于0且不能超过'+toThousands(awardSnLimit)+'</span>').show();
        return false;
    } else if (isRedPackAward && (awardRedPackTotalNum > awardRedPackLimit || awardRedPackTotalNum == 0)) {
        $this.closest(".control-group").addClass("error");
        $this.siblings(".j-awardNum-tips").html('<span class="help-inline">活动的红包类奖品总数必须大于0且不能超过'+toThousands(awardRedPackLimit)+'</span>').show();
        return false;
    } else {
        $this.closest(".control-group").removeClass("error");
        $this.siblings(".j-awardNum-tips").hide();
        return true;
    }
}

//奖品详情-触发输入框编辑联动
function triggerAwardDetailPhoneTxt(isInit) {
    if (isInit) {	// 首次进入
        $(".award_tab_content .award_tab").eq(0)
            .find("input[name='trophyName'], input[name='subhead'], " +
                "input[name='operationTip'], input[name='storeAddress'], " +
                "input[name='serviceTel'], textarea[name='description']").trigger("keyup");
        $(".award_tab_content .award_tab").eq(0)
            .find("input[name='validityStart'], input[name='validityStop']").trigger("change");
    } else {
        $("input[name='trophyName']:visible, input[name='subhead']:visible, " +
            "input[name='operationTip']:visible, input[name='storeAddress']:visible, " +
            "input[name='serviceTel']:visible, textarea[name='description']:visible").trigger("keyup");
        $("input[name='validityStart']:visible, input[name='validityStop']:visible").trigger("change");
    }
    // 跳转按钮
    $("input[name='jumpBtnName']:visible").trigger("keyup");
   
    if($("input[name='guanwanJumpBtn']:visible:checked").val() == 1){
    	$$("#jumpBtn").hide();
    }else if($("input[name='guanwanJumpBtn']:visible:checked").val() == 2){
    	
    	$$("#jumpBtn").show().find(".text").text($("input[name='jumpBtnName']:visible").val());
    }
}

var initActivity = function () {

    // 初始化省市
    initCitySelector();

    // 初始化开始和结束时间-加入前端校验
    $('#startDate').datetimepicker();
    var startDate = new Date().getTime($('#startDate').val());
    var three_month_time = new Date().getTime() + 90 * 24 * 60 * 60 * 1000;
    $('#endDate').datetimepicker({
        minDate: new Date(startDate),
        maxDate: new Date(three_month_time)
    });

    // 页面通用校验
    $(document).on('input', '.onlyNum', function () {
        $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
    });
    $(document).on('input', '.onlyIntNum', function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });
    $(document).on('input', '.onlyNumWithFixed', function () {
    	// 默认支持两位小数
    	var fixed = $(this).attr("fixed") || 2;
    	var v = $(this).val().replace(/[^0-9\.]/g, '');
    	var pointStart = v.indexOf("."); 
    	if(pointStart >= 0 && v.length-pointStart-1 > fixed){
    		v = v.substr(0 , pointStart+3);
    	}
    	var dotCount = 0;
    	for(var i=0; i<v.length; i++){
    		if(v[i] == '.'){
    			dotCount++;
    		}
    	}
    	if(dotCount > 1){
    		v = "0";
    	}
    	$(this).val(v);
    });


    // 总奖品数量校验(总数限制已参数形式传入，方便后期套餐限制)
    $(".award_tab_content").on("input", "input[name='awardRealNum']", function () {
        awardNumCheck($(this), 10000);
    });
    $("#add_redpack_modal").on("input", "input[name='add_redpack_num']", function () {
    	awardNumCheck($(this), 10000);
    });
    $("#add_sncode_award_modal").on("input", "input[name='award_unsend_num']", function () {
    	awardNumCheck($(this), 10000);
    });

    // 奖项切换
    /**
     $("#awardCount").on("change", function(){
		var awardCount = $(this).val();
		var tab_item = {items: []};
		for (var i = 0; i < awardCount; i++) {
			tab_item.items.push({"index":i+1});
		}
		load_award_tab(tab_item);
		
		// 初始化奖品时间，默认一年有效期
		var now_timestamp = new Date().getTime();
		var one_year_later = now_timestamp + 365 * 24 * 60 * 60 * 1000;
		$("input[name='validityStart']").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
		$("input[name='validityStop']").val(new Date(one_year_later).Format("yyyy-MM-dd HH:mm:ss"));
		
		// 初始化开始和结束时间
		$("input[name='validityStart']").datetimepicker();
		$("input[name='validityStop']").datetimepicker();
	});
     **/

    // 添加奖项
    $(".add_award_tab_item").live("click", function () {
        var $nextTab = $(".award_tab_item li.hide").eq(0);
        $nextTab.removeClass("hide");
        if ($(".award_tab_item li.hide").size() == 0) {
            $(this).hide();
        }
        $(".del_award_tab_item").show();
        $("input[name='awardName']").eq(0).trigger("keyup");
        // 得奖限制
        if ($("#award_convert_limit_form").length > 0) {
            var index = $nextTab.index();
            var convert_limit = $("#award_convert_limit").find(".convert_limit").eq(index);
            convert_limit.show().removeClass("hide");
            convert_limit.find("input").removeAttr("disabled");
        }
    });

    // 删除奖项
    $(".del_award_tab_item").live("click", function () {
        if ($(".award_tab_item li.prize_tab_item:visible").size() <= 1) {
            $(this).hide();
            return false;
        }
        var $lastTab = $(".award_tab_item li.prize_tab_item:visible:last");
        if ($lastTab.hasClass("active")) {
            $lastTab.prev().find(".award_tab_item_link").click();
        }
        $lastTab.addClass("hide");
        if ($(".award_tab_item li.prize_tab_item:visible").size() <= 1) {
            $(this).hide();
        }
        $(".add_award_tab_item").show();
        $("input[name='awardName']").eq(0).trigger("keyup");
        //奖品详情-触发输入框编辑联动
        triggerAwardDetailPhoneTxt();
        // 得奖限制
        if ($("#award_convert_limit_form").length > 0) {
            var index = $lastTab.index();
            var convert_limit = $("#award_convert_limit").find(".convert_limit").eq(index);
            convert_limit.hide().addClass("hide");
            convert_limit.find("input").attr("disabled", "disabled");
        }
    });

    // 奖品类型切换
    $(".award_tab_content").on("change", "select[name='awardType']", function (event, isInit) {
        var awardType = $(this).val();
        var thisTab = $(this).closest(".award_tab");
        if (awardType == 2) {
            thisTab.find(".trophy_redpack_text").show().find("input").removeAttr("disabled");
            thisTab.find(".trophy_datetime").hide().find("input").attr("disabled", "disabled");
            thisTab.find(".set_isWxCard_box").hide();
            thisTab.find(".awardSettingLine").hide();
            thisTab.find(".awardDetailSetting").hide();
            thisTab.find(".redpack_award_type .control-label").text("红包数量");
            thisTab.find(".j-sncode-type").hide().find("textarea").attr("disabled", "disabled");
        } else {
            thisTab.find(".trophy_redpack_text").hide().find("input").attr("disabled", "disabled");
            thisTab.find(".trophy_datetime").show().find("input").removeAttr("disabled");
            thisTab.find(".set_isWxCard_box").show();
            thisTab.find(".awardSettingLine").show();
            thisTab.find(".awardDetailSetting").show();
            thisTab.find(".redpack_award_type .control-label").text("奖品数量：");
            thisTab.find(".j-sncode-type").show();
            if (thisTab.find("input[name='sncodeType']:checked").val() != 1) {
                $("input[name='sncodeType']").trigger("change");
            }
        }
        // 使用默认的奖品名称，页面初始化触发change事件的时候不改变奖项名称
        if (!isInit) {
            var idx = thisTab.find("select[name='awardLevel']").data("value");
            thisTab.find("input[name='trophyName']").val(getTrophyName(awardType, idx - 1));
        }
        $("input[name='trophyName']:visible").trigger("keyup");
    });

    // 显示/隐藏生成券号设置(sn码)
    $(".award_tab_content").on("change", ".award_tab:visible form input[name='sncodeType']", function () {
        var sncode = $(this).val();
        var sncodeControls = $(this).closest(".control-group").children(".j-sncode-controls");
        if (sncode == 1) {
            sncodeControls.hide();
            sncodeControls.children("textarea").attr("disabled", "disabled");
        } else {
            sncodeControls.show();
            sncodeControls.children("textarea").removeAttr("disabled");
            // 校验sn码
            bindSncodeCheck();
        }
    });

    // 显示/隐藏奖品详情设置
    $(".awardSettingLine").live("click", function () {
        $(this).toggleClass("showDetail");
        $(this).next(".awardDetailSetting").toggle();
        $$("#awardCusTextInfo").toggle();
    });
    

    // 显示/隐藏主办单位开启跳转
    $(".config-tab-content").on("change","input[name='jumpLinkBtn']",function () {
        var jumpLinkBox = $(this).val();
        if (jumpLinkBox == 1) {
            $("#set_jumpLink2_box").hide().find("input").attr("disabled", "disabled");
        } else if(jumpLinkBox == 2) {
            $("#set_jumpLink2_box").show().find("input").removeAttr("disabled");
        }
    });
    
    
    // 显示/隐藏企业LOGO设置
    $("input[name='hideOrganizerLogo']").change(function () {
        var hideOrgLogo = $(this).val();
        if (hideOrgLogo == 1) {
            $(".orgLogoControls").hide().find("input").attr("disabled", "disabled");
            $$(".organizer-btn").hide();
        } else {
            $(".orgLogoControls").show().find("input").removeAttr("disabled");
            $$(".organizer-btn").show();
        }
    });
    
    
    // 显示/隐藏加载页面开启可点击提示
    $("input[name='isOpenClick']").change(function () {
        if ($(this).val() == 0) {
            $(".openClickControls").hide().find("input").attr("disabled", "disabled");
            $$(".openClick-btn").hide();
        } else {
            $(".openClickControls").show().find("input").removeAttr("disabled");
            $$(".openClick-btn").show();
        }
    });

    // 显示/隐藏版权图片设置
    $("input[name='useDefaultCopyright']").change(function () {
        var useDefaultCopyright = $(this).val();
        if (useDefaultCopyright == 1) {
            $(".copyrightPicControls").hide().find("input").attr("disabled", "disabled");
        } else {
            $(".copyrightPicControls").show().find("input").removeAttr("disabled");
        }
    });
    
    // 显示/隐藏加载页面图片设置
    $("input[name='useDefaultLoadPage']").change(function () {
        var useDefaultLoadPage = $(this).val();
        if (useDefaultLoadPage == 1) {
            $(".loadPagePicControls").hide().find("input").attr("disabled", "disabled");
        } else {
            $(".loadPagePicControls").show().find("input").removeAttr("disabled");
        }
    });
    
    // 显示/隐藏跳转按钮
    $(".award_tab_content").on("change","input[name='guanwanJumpBtn']",function () {
        var limitTotalLotteryCount = $(this).val();
        if (limitTotalLotteryCount == 1) {
        	$$("#jumpBtn").hide();
            $(this).parents(".controls").find(".jumpBtnNameBox").hide().find(".jumpBtnName").attr("disabled", "disabled");;
            $(this).parents(".controls").find(".jumpLinkBox").hide().find(".jumpLink").attr("disabled", "disabled");;
        } else {
        	$$("#jumpBtn").show();
        	$(this).parents(".controls").find(".jumpBtnNameBox").show().find(".jumpBtnName").removeAttr("disabled");;
            $(this).parents(".controls").find(".jumpLinkBox").show().find(".jumpLink").removeAttr("disabled");;
        }
    });
    
    // 跳转按钮名称编辑
    $(".award_tab_content").on("keyup","input[name='jumpBtnName']",function(){
    	  
    	   $$("#jumpBtn").find(".text").text( $(this).val());
    });


    // 显示/隐藏总抽奖设置
    $("input[name='totalLotteryCountRadio']").change(function () {
        var limitTotalLotteryCount = $(this).val();
        if (limitTotalLotteryCount == 1) {
            $("#totalLotteryCount").attr("disabled", "disabled")
        } else {
            $("#totalLotteryCount").removeAttr("disabled");
        }
    });

    // 显示/隐藏游戏时间设置
    $("input[name='gameTimeRadio']").change(function () {
        var gameTimeRadio = $(this).val();
        if (gameTimeRadio == 0) {
            $("#gameTime").attr("disabled", "disabled")
        } else {
            $("#gameTime").removeAttr("disabled");
        }
    });

    // 分享形式切换
    $("input[name='shareType']").change(function () {
        var shareType = $(this).val();
        if (shareType == 0) {	// 图片分享
            $("#wxshare_box").hide();
            $("#wxshare_box").find("input").attr("disabled", "disabled");
            $("#set_qrcodebg_box").show();
            $("input[name='useDefaultQrcodeBg']").removeAttr("disabled");
            var shareQrcodeBg = $("input[name='useDefaultQrcodeBg']:checked").val();
            if (shareQrcodeBg == 1) {
                $(".qrcodeBgControls").hide().find("input").attr("disabled", "disabled");
            } else {
                $(".qrcodeBgControls").show().find("input").removeAttr("disabled");
            }
        } else {	// 短图文分享
            $("#set_qrcodebg_box").hide();
            $("#set_qrcodebg_box").find("input").attr("disabled", "disabled");
            $("#wxshare_box").show();
            $("input[name='useShareIco'], input[name='useShareTxt'], input[name='useShareTitle']").removeAttr("disabled");
            var shareIco = $("input[name='useShareIco']:checked").val();
            var shareTxtType = $("input[name='useShareTxt']:checked").val();
            // !判断被选中的按钮
            var shareTitle = $("input[name='useShareTitle']:checked").val();
            
            if (shareIco == 1) {
                $("#share_ico_controls").hide().find("input").attr("disabled", "disabled");
            } else {
                $("#share_ico_controls").show().find("input").removeAttr("disabled");
            }
            if (shareTxtType == 1) {
                $("#share_txt_controls").hide();
                $("input[name='checkShareTxt']").val(1);
            } else {
                $("#share_txt_controls").show();
                $("input[name='checkShareTxt']").removeAttr("disabled");
            }
            // !点击短图文触发 1为默认，2为自定义
            if (shareTitle == 1) {
            	$("#share_title_controls").hide();
                $("input[name='checkShareTxt']").val(1); 
            } else {
                $("#share_title_controls").show();
                $("input[name='checkShareTxt']").removeAttr("disabled");
            }
        }
    });

    // 显示/隐藏分享二维码背景图设置
    $("input[name='useDefaultQrcodeBg']").change(function () {
        var shareQrcodeBg = $(this).val();
        if (shareQrcodeBg == 1) {
            $(".qrcodeBgControls").hide().find("input").attr("disabled", "disabled");
        } else {
            $(".qrcodeBgControls").show().find("input").removeAttr("disabled");
        }
    });

    // 显示/隐藏微信分享图标设置
    $("input[name='useShareIco']").change(function () {
        var shareIco = $(this).val();
        if (shareIco == 1) {
            $("#share_ico_controls").hide().find("input").attr("disabled", "disabled");
        } else {
            $("#share_ico_controls").show().find("input").removeAttr("disabled");
        }
    });

    // 显示/隐藏微信分享内容设置
    $("input[name='useShareTxt']").change(function () {
        var shareTxtType = $(this).val();
        if (shareTxtType == 1) {
            $("#share_txt_controls").hide();
            $("input[name='checkShareTxt']").val(1);
        } else {
            $("#share_txt_controls").show();
        }
    });
   
    // !显示/隐藏微信分享标题设置
    $("input[name='useShareTitle']").change(function () {
        var shareTitleType = $(this).val();
        if (shareTitleType == 1) {
            $("#share_title_controls").hide();
            $("input[name='checkShareTxt']").val(1);
        } else {
            $("#share_title_controls").show().find("input").removeAttr("disabled");
        }
    });
   
     // 插入微信分享内容
    insertShareTxt();
    
    /** 输入框编辑联动 **/
    $("#title").on("keyup", function () {
        $("#actName").text($(this).val());
    });
    $("#globalProbability").on("keyup", function () {
        $(".globalProbabilityText").text($(this).val());
    });
    $("#ruleTips").on("keyup", function () {
        $$("#rule_content_text").html($.htmlEncode($(this).val()));
    });
    $("#organizer").on("keyup", function () {
        $$(".organizer").html($.htmlEncode($(this).val()));
    });
    // 口令红包祝福语
    if ($("#set_bless_box").length > 0) {
        $("#bless").on("keyup", function () {
            $$("#bless").text($(this).val());
        });
    }
    // 口令红包商家名称
    if ($("#set_seller_name_box").length > 0) {
        $("#seller_name").on("keyup", function () {
            $$("#seller_name").text($(this).val());
        });
    }

    $("input[name='showCopyright']").on("change", function () {
        if ($(this).val() == 2) {
            $$("#start_bottom").hide();
        } else {
            $$("#start_bottom").show();
        }
    });
    $("input[name='open_opening_link']").on("change", function () {
        if ($("input[name='open_opening_link']:checked").val() == 0) {
            $("#opening_link, #set_openClick_box").hide();
            $(".openClickControls").hide().find("input").attr("disabled", "disabled");
            $$(".openClick-btn").hide();
        } else {
            $("#opening_link, #set_openClick_box").show();
            if ($("input[name='isOpenClick']:checked").val() == 0) {
                $(".openClickControls").hide().find("input").attr("disabled", "disabled");
                $$(".openClick-btn").hide();
            } else {
                $(".openClickControls").show().find("input").removeAttr("disabled");
                $$(".openClick-btn").show();
            }
        }
    });
    
    // 红包安全机制切换
    $("#securityLevel-alert-modal-1 .securityLevel-alert-confirm").click(function(){
    	if($(this).hasClass("disabled")) return;
    	$("#securityLevel-alert-modal-1").modal("hide");
    	$("input[name='securityLevel'][value='1']").attr("checked","checked").trigger("change",true);
    });
    $("#securityLevel-alert-modal-2 .securityLevel-alert-confirm").click(function(){
    	if($(this).hasClass("disabled")) return;
    	$("#securityLevel-alert-modal-2").modal("hide");
    	$("input[name='securityLevel'][value='2']").attr("checked","checked").trigger("change",true);
    });
    $("#securityLevel-alert-modal-3 .securityLevel-alert-confirm").click(function(){
    	if($(this).hasClass("disabled")) return;
    	$("#securityLevel-alert-modal-3").modal("hide");
    	$("input[name='securityLevel'][value='3']").attr("checked","checked").trigger("change",true);
    });
    $(".securityLevel-alert-modal input[name='securityLevel_alert_check']").change(function(){
    	var $modal = $(this).closest(".securityLevel-alert-modal");
    	if($(this).is(":checked")){
    		$(".securityLevel-alert-confirm",$modal).removeClass("disabled");
    	} else {
    		$(".securityLevel-alert-confirm",$modal).addClass("disabled");
    	}
    });
    
    $("input[name='securityLevel']").on("click", function (e) {
        var securityLevel = $("input[name='securityLevel']:checked").val();
        if (securityLevel == 1) {
			$("#securityLevel-alert-modal-1").modal("show");
        } else if (securityLevel == 2) {
        	$("#securityLevel-alert-modal-2").modal("show");
        } else if (securityLevel == 3) {
        	$("#securityLevel-alert-modal-3").modal("show");
        }
		e.stopPropagation();
		return false;
    });
    $("input[name='securityType']").change(function(){
    	var secType = $("input[name='securityType']:checked").val();
    	if(secType == 1){
    		$("label[for='smsfreeze_total']").text("预计短信验证量：");
    		$("label[for='sms_total']").text("账户短信余额：");
    	} else {
    		$("label[for='smsfreeze_total']").text("预计语音验证量：");
    		$("label[for='sms_total']").text("账户语音余额：");
    	}
    });

    $("input[name='securityLevel']").on("change", function (e) {
        var securityLevel = $("input[name='securityLevel']:checked").val();
        if (securityLevel == 1) {
            $("#securityLevel_1_tips").show();
            $("#securityLevel_2_tips").hide();
            $("#security_type_group").hide();
        } else if (securityLevel == 2 || securityLevel == 3) {
        	$("#security_type_group").show();
            if (securityLevel == 2) {
                $("#securityLevel_2_tips .tips2").show();
                $("#securityLevel_2_tips .tips3").hide();
            } else {
                $("#securityLevel_2_tips .tips2").hide();
                $("#securityLevel_2_tips .tips3").show();
            }
            // 更新预计短信发送量
            var awardCount = $(".award_tab_item>li.prize_tab_item").length;
            // 红包总数量
            var totalHBawardRealNum = 0;
            // 奖项赋值
            for (var i = 0; i < awardCount; i++) {
                var $tab = $(".award_tab_item>li.prize_tab_item").eq(i);
                if ( !$tab.hasClass("hide") &&  $(".award_tab_" + i + " select[name='awardType']").val() == 2) {
                    totalHBawardRealNum += parseInt($(".award_tab_" + i + " input[name='awardRealNum']").val());
                }
            }
            $("#securityLevel_2_tips").show().find("#smsfreeze_total").text(totalHBawardRealNum);
            // 更新账户短信余额
            var submitData = {}
            $.ajax({
                url: serverroot+'/content/bigwheel/back/files/query_account_by_uid.html',
                timeout: 15000,
                type: "get",
                dataType: "json",
                data: submitData,
                success: function (data) {
                    if (data.ret == 0) {
                        console.log(data);
                        if (data.model.isjoin_sms100) {
                            $("#securityLevel_2_tips").find(".sms_free_join").hide();
                        }
                        $("#securityLevel_2_tips").show().find("#sms_total").text(data.model.simAccount.enableNum);
                    } else {
                        alert(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("服务器正在紧张的运行中，请稍后再试");
                }
            });
            $("#securityLevel_1_tips").hide();
            $("#securityLevel_2_tips").show();
        }
    });

    // 派奖模式切换
    $("input[name='awardSendType']").on("change", function () {
        if ($("input[name='awardSendType']:checked").val() == 1) {	// 按排名
            $("#lucky_award_box").hide().find("input").attr("disabled", "disabled");
            $(".help-inline-awardSendType2").hide();
            $(".help-inline-awardSendType1").show();
            $(".helpType_1_cont").hide();
            $(".helpType_2_cont").show();
            $("#helpType").val(2);
            $$(".rank_btn").show();
            $(".set_defaultWinner_box").hide();
        } else {	// 按抽奖
            $("#lucky_award_box").show().find("input").removeAttr("disabled", "disabled");
            // 显示/隐藏总抽奖设置
            if ($("input[name='totalLotteryCountRadio']:checked").val() == 1) {
                $("#totalLotteryCount").attr("disabled", "disabled")
            } else {
                $("#totalLotteryCount").removeAttr("disabled");
            }
            $(".help-inline-awardSendType1").hide();
            $(".help-inline-awardSendType2").show();
            $(".helpType_1_cont").show();
            $(".helpType_2_cont").hide();
            if ($("input[name='helpEffect']:checked").val() == 0) {
                $("#helpType").val(1);
            } else {
                $("#helpType").val(4);
            }
            $$(".rank_btn").hide();
            $(".set_defaultWinner_box").show();
        }
        // 切换顶部的游戏页面页签
        showTabByStyle($("input[name='awardSendType']:checked").val());
    });

    // 助力效果  1-增加抽奖次数;4-助力领奖;2-按排名
    $("input[name='helpEffect']").on("change", function (e, i) {
        if (i == 0 || $(this).val() == 0) { // 增加抽奖次数
            $("#lotteryCount").show();
            $("#helpAward").hide();
            $("#lotteryCount").find("input").removeAttr("disabled");
            $("#helpAward").find("input").attr("disabled", "disabled");
            $("#helpType").val(1);
            $("#set_daylimit_box .help-inline-1").show();
            $("#set_daylimit_box .help-inline-2").hide();
        } else { // 助力领奖
            $("#helpAward").show();
            $("#lotteryCount").hide();
            $("#helpAward").find("input").removeAttr("disabled");
            $("#lotteryCount").find("input").attr("disabled", "disabled");
            $("#helpType").val(4);
            $("#set_daylimit_box .help-inline-1").hide();
            $("#set_daylimit_box .help-inline-2").show();
        }
    });

    // 显示/隐藏兑奖信息设置
    $("input[name='exchangeLimit']").change(function () {
        var exchangeLimit = $(this).val();
        if (exchangeLimit == 1) {
            $("#set_exchangeLimitDetail_box").show();
        } else {
            $("#set_exchangeLimitDetail_box").hide();
        }
    });
    //显示/隐藏验证手机号的提示
    $("input[name='exchangeLimitPhone']").change(function () {
        var exchangeLimitPhone = $(this).val();
        var p = $(this).closest('.controls').find('>p');
        if (exchangeLimitPhone == 1) {
            p.show();
        } else {
            p.hide();
        }
    });

    //手机支付端是否开启
    //开启的话把商品底价设为0
    $("input[name='exchangePayWay']").change(function () {
        var pay = $(this).val();
        if(pay==1){
            var flag = false; //表示可以改
            if(hasPublish) flag = true; //活动发布之后，不可以改

            $('.award_tab').each(function(){
                var input = $(this).find('input[name="cls_bargain_min_price"]');
                input.attr('disabled',flag);
            })
        }else{
            $('.award_tab').each(function(){
                var input = $(this).find('input[name="cls_bargain_min_price"]');
                input.val('0');
                input.attr('disabled',true)
            })
        }

    });

    $("input[name='hideParticipant']").on("change", function () {
        if ($(this).val() == 1) {
            $$("#join_line_cont").hide();
        } else {
            $$("#join_line_cont").show();
        }
    });
    $("input[name='useDefaultCopyright']").on("change", function () {
        if ($(this).val() == 1) {
            $$("#startLogoImg").attr("src", static_res_prefix + "/content/bigwheel/back/files/lot/yylogo.png")
        } else {
            $$("#startLogoImg").attr("src", user_img_domain + $("#customCopyrightPic").val());
        }
    });
    $("input[name='useDefaultQrcodeBg']").on("change", function () {
        if ($(this).val() == 1) {
            $$("#help_qrcode_img").attr("src", _resRoot + "/share.png")
        } else {
            $$("#help_qrcode_img").attr("src", user_img_domain + $("#shareQrcodeBg").val());
        }
    });
    $("#startDate,#endDate").change(function () {
        var startDate = $("#startDate").datetimepicker('getDate').Format("yyyy年MM月dd日");
        var endDate = $("#endDate").datetimepicker('getDate').Format("yyyy年MM月dd日");
        $$("#rule_time").html(startDate + "~" + endDate);
    });

    $("input[name='awardName'],input[name='trophyName']").live("keyup", function () {
        var awardCount = $(".prize_tab_item ").not(".hide").length;
        var awardHtml = "";
        for (var i = 0; i < awardCount; i++) {
            awardHtml += "<p>";
            awardHtml += $(".award_tab_" + i + " input[name='awardName']").val();
            awardHtml += "：";
            awardHtml += $(".award_tab_" + i + " input[name='trophyName']").val();
            awardHtml += "</p>";
        }
        $$("#rule_award").html(awardHtml);
    });

    // 追加红包按钮事件
    $(".add_redpack_award").live("click", function () {
        var awardId = $(this).data("awardid");
        var $addRedpackModal = $("#add_redpack_modal");
        $addRedpackModal.modal("show");
        $addRedpackModal.attr("data-awardid", awardId);
    });

    // 实物奖品修改数量按钮事件
    $(".add_sncode_award").live("click", function () {
        var awardId = $(this).data("awardid");
        var $cont = $(this).parent();
        var $form = $(this).closest(".award_detail_form");
        var $addSncodeModal = $("#add_sncode_award_modal");
        $addSncodeModal.modal("show");
        $addSncodeModal.attr("data-awardid", awardId);
        var sncodeType = $form.find("input[name='sncodeType']:checked").val();
        console.log(sncodeType);
        $("input[name='award_unsend_num']", $addSncodeModal).val($cont.find("input[name='awardRealNum']").val());
        $("input[name='award_send_num']", $addSncodeModal).val($cont.find("input[name='awardLoseNum']").val());
        if (sncodeType == 3) {
            $("textarea[name='sncodes']", $addSncodeModal).removeAttr("disabled");
            $(".j-sncode-cont", $addSncodeModal).show();
            $("textarea[name='sncodes']", $addSncodeModal).val($form.find("textarea[name='sncodes']").val());
            $addSncodeModal.find(".j-check-sncode").off("click").on("click", function () {
                sncodeCheck($(this));
            });
        } else {
            $("textarea[name='sncodes']", $addSncodeModal).attr("disabled", "disabled").val("");
            $(".j-sncode-cont", $addSncodeModal).hide();

        }
    });

    // 奖品详情-输入框编辑联动
    $(".award_tab_content").on("keyup", "input[name='trophyName']", function () {
        $$("#trophyNamePhoneTxt").text($(this).val());
    });
    $(".award_tab_content").on("change", "input[name='validityStart']", function () {
        $$("#validityStartPhoneTxt,#validityStartPhoneTxt1").text($(this).val().substring(0, 10).replace(/\-/g, "."));
    });
    $(".award_tab_content").on("change", "input[name='validityStop']", function () {
        $$("#validityStopPhoneTxt,#validityStopPhoneTxt1").text($(this).val().substring(0, 10).replace(/\-/g, "."));
    });
    $(".award_tab_content").on("keyup", "input[name='subhead']", function () {
        $$("#subheadPhoneTxt").text($(this).val());
    });
    $(".award_tab_content").on("keyup", "input[name='operationTip']", function () {
        $$("#operationTipPhoneTxt").text($(this).val());
    });
    $(".award_tab_content").on("keyup", "input[name='storeAddress']", function () {
        $$("#storeAddressPhoneTxt").text($(this).val());
    });
    //现在不用联动回显了
    // $(".award_tab_content").on("keyup", "input[name='serviceTel']", function () {
    //     // $$("#serviceTelPhoneTxt").text($(this).val());
    // });
    $(".award_tab_content").on("keyup", "textarea[name='description']", function () {
        $$("#descriptionPhoneTxt").text($(this).val());
    });

    // 追加红包设置判断
    var add_redpack_validator = $("#form_add_redpack_setting").validate({
        onfocusout: false,
        rules: {
            add_redpack_num: {required: true, awardNum: true},
            add_redpack_amount: {required: true, number: true, min: 1, max: 400000, validateAddRedpackTotalMoney: true}
        },
        messages: {
            add_redpack_num: {required: "不能为空", maxlength: "长度不能大于30"},
            add_redpack_amount: {required: "不能为空", number: "必需为数值", min: "数值至少为1", max: "数值最大为400000"}
        },
        showErrors: function (errorMap, errorList) {
            if (errorList && errorList.length > 0) {
                $.each(errorList,
                    function (index, obj) {
                        var item = $(obj.element);
                        if (item.is(".cover")) {
                            alert(obj.message);
                        }
                        // 给输入框添加出错样式
                        item.closest(".control-group").addClass('error');
                        item.attr("title", obj.message);
                    });
            } else {
                var item = $(this.currentElements);
                item.closest(".control-group").removeClass('error');
                item.removeAttr("title");
            }
        },
        submitHandler: function () {
            var awardId = $("#add_redpack_modal").data("awardid");
            var $btn = $("#add_redpack_confirm");
            var $form = $("#form_add_redpack_setting");
            if ($btn.hasClass("disabled")) return;
            var submitData = {
                "awardId": awardId,
                "addValue": Math.round(Number($("#add_redpack_amount", $form).val()) * 100),
                "addNum": $('#add_redpack_num', $form).val()
            }
            $btn.addClass("disabled");
            $.ajax({
                url: '/admin/new_activity/add_redpack',
                timeout: 15000,
                type: "post",
                dataType: "json",
                data: submitData,
                success: function (data) {
                    if (data.ret == 0) {
                        var model = data.model;
                        unbindBeforeUnload();
                        alert("红包追加成功");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                    $btn.removeClass("disabled");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("服务器正在紧张的运行中，请稍后再试");
                    $btn.removeClass("disabled");
                }
            });
        }
    });

    // 修改实物奖品设置判断
    var add_sncode_award_validator = $("#form_add_sncode_award_setting").validate({
        onfocusout: false,
        rules: {
            award_unsend_num: {required: true, number: true,awardNum: true, min: 1, max: 400000},
            sncodes: {required: true, snCode: true}
        },
        messages: {
            award_unsend_num: {required: "不能为空", number: "必需为数值", min: "数值至少为1", max: "数值最大为400000"},
            sncodes: {required: true, snCode: "券号格式错误"}
        },
        showErrors: function (errorMap, errorList) {
            if (errorList && errorList.length > 0) {
                $.each(errorList,
                    function (index, obj) {
                        var item = $(obj.element);
                        if (item.is(".cover")) {
                            alert(obj.message);
                        }
                        // 给输入框添加出错样式
                        item.closest(".control-group").addClass('error');
                        item.attr("title", obj.message);
                    });
            } else {
                var item = $(this.currentElements);
                item.closest(".control-group").removeClass('error');
                item.removeAttr("title");
            }
        },
        submitHandler: function () {
            var awardId = $("#add_sncode_award_modal").data("awardid");
            var $btn = $("#add_sncode_award_confirm");
            var $form = $("#form_add_sncode_award_setting");
            if ($btn.hasClass("disabled")) return;
            var updateNum = parseInt($('input[name="award_unsend_num"]', $form).val()) + parseInt($('input[name="award_send_num"]', $form).val());
            var submitData = {
                "awardId": awardId,
                "updateNum": updateNum
            }
            if (!$('textarea[name="sncodes"]', $form).is(":disabled")) {
                submitData.snList = $('textarea[name="sncodes"]', $form).val().split(/\n/g).toString();
            }
            $btn.addClass("disabled");
            $.ajax({
                url: '/admin/new_activity/update_snaward',
                timeout: 15000,
                type: "post",
                dataType: "json",
                data: submitData,
                success: function (data) {
                    if (data.ret == 0) {
                        var model = data.model;
                        unbindBeforeUnload();
                        alert("奖品数量修改成功");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                    $btn.removeClass("disabled");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("服务器正在紧张的运行中，请稍后再试");
                    $btn.removeClass("disabled");
                }
            });
        }
    });

    $("#passCondition").on("keyup", function () {
        $$("#resule-status-minscore .prize_score").text($(this).val());
    });
    // 计算平台技术费
    $("input[name='totalCount']").live("keyup", function () {
        var totalCount = Number($(this).val());
        var $cont = $(this).closest(".control-group");
        if (totalCount > 10) {
            $cont.find(".service_charge").text((totalCount * 0.03).toFixed(2));
        } else {
            $cont.find(".service_charge").text(0);
        }
    });
    $("input[name='add_redpack_amount']").live("keyup", function () {
        var $cont = $(this).closest("#form_add_redpack_setting");
        $cont.find(".service_charge").text(($(this).val() * 0.03).toFixed(2));
    });


    // 显示/隐藏总抽奖设置 TODO
    $("body").on("change",'input[name="withdrawThresholdRadio"]',function(){
    	 var limitWithdrawThreshold = $(this).val();
         if (limitWithdrawThreshold == 1 || limitWithdrawThreshold == 3) {
             $(this).closest(".radio").siblings(".withdrawThreshold_min").hide();
         } else {
        	 $(this).closest(".radio").siblings(".withdrawThreshold_min").show().css({"display":"inline-block","padding-top":"0"});
         }
    });

    /** 奖项配置表单校验 **/
    $.validator.addMethod("singleMax", function (value, element, params) {
    	if(!_isZcqc){
    		var singleMax = Number(value);
            var singleMin = Number($(element).parent().find("input[name='singleMin']").val());
            return singleMax >= singleMin;
    	}else{ // TODO
    		var singleMax = Number(value);
            var singleMin = Number($(element).parent().find("input[name='singleMin']").val());
            if(singleMin < 0.1 || singleMin > 5 || singleMax > 5 || singleMax < 0.1){
            	return false;
            }
            return singleMax >= singleMin;
    	}
    }, '红包最大值取值必需大于最小值');

    // 校验sn码
    $.validator.addMethod("snCode", function (value, element, params) {
        return sncodeCheck($(element).closest(".controls").find(".j-check-sncode"));
    }, "券号格式错误");

    // 校验奖品数量
    $.validator.addMethod("awardNum", function (value, element, params) {
        return awardNumCheck($(element), 10000);
    }, "奖品总数量在1-10000之间");
    
    // 校验奖品内定数量
    $.validator.addMethod("defaultWinnerAmount", function (value, element, params) {
    	var awardRealNum = Number($(element).closest(".award_detail_form").find("input[name='awardRealNum']").val());
        var defaultWinnerAmount = Number(value);
        return defaultWinnerAmount > 0 && defaultWinnerAmount <= awardRealNum;
    }, "内定奖品数量必须大于0且不能大于奖品数量");
    
    // 校验所有奖项概率之和不能大于100%
    $.validator.addMethod("probabilityValueTotal", function (value, element, params) {
        var probabilityValueTotal = 0;
        // 获取所有奖项的总和
        $(".prize_tab_item:not('.hide')").each(function () {
            // 根据tab获取奖项个数
            var award_idx = $(this).children("a").attr("data-idx");
            var probabilityValue = Number($(".award_tab_" + award_idx).find("input[name='probabilityValue']").val());
            // 计算总和
            probabilityValueTotal = probabilityValueTotal + probabilityValue;
        });
        return probabilityValueTotal <= 100;
    }, "所有奖项中奖概率之和不能大于100%");

    $.validator.addMethod("validityStop", function (value, element, params) {
        var $startDate = $(element).closest("form").find("input[name='validityStart']");
        var $endDate = $(element);
        $startDate.datetimepicker('setDate', $startDate.val());
        $endDate.datetimepicker('setDate', $endDate.val());
        return $endDate.datetimepicker('getDate') > $startDate.datetimepicker('getDate');
    }, '兑奖结束时间必须大于开始时间');

    $.validator.addMethod("validateTotalMoney", function (value, element, params) {
        if (_isYyttl) {return true;}
        var $form = $(element).closest("form");
        var awardType = $form.find("select[name='awardType']").val();
        if (awardType != 2) return true;
        var prizeTotalMoney = Number($form.find("input[name='totalCount']").val());
        var totalSendNumber = Number($form.find("input[name='awardRealNum']").val());
        var maxMoney = Number($form.find("input[name='singleMax']").val());
        var minMoney = Number($form.find("input[name='singleMin']").val());
        return prizeTotalMoney >= Number((totalSendNumber * minMoney).toFixed(2)) && prizeTotalMoney <= Number((totalSendNumber * maxMoney).toFixed(2));
    }, '总金额不正确，必须符合：（单个红包最小金额×总数量）<总金额<（单个红包最大金额×总数量）');

    // 校验追加红包设置中的总金额
    $.validator.addMethod("validateAddRedpackTotalMoney", function (value, element, params) {
        if (_isYyttl) {
            return true;
        }
        var awardId = $("#add_redpack_modal").data("awardid");
        var $form = $(element).closest("form")
        var $awardForm = $(".award_detail_form[data-awardid='" + awardId + "']");
        var awardType = $awardForm.find("select[name='awardType']").val();
        if (awardType != 2) return true;
        var prizeTotalMoney = Number($form.find("input[name='add_redpack_amount']").val());
        var totalSendNumber = Number($form.find("input[name='add_redpack_num']").val());
        var maxMoney = Number($awardForm.find("input[name='singleMax']").val());
        var minMoney = Number($awardForm.find("input[name='singleMin']").val());
        return prizeTotalMoney >= Number((totalSendNumber * minMoney).toFixed(2)) && prizeTotalMoney <= Number((totalSendNumber * maxMoney).toFixed(2));
    }, '总金额不正确，必须符合：（单个红包最小金额×总数量）<总金额<（单个红包最大金额×总数量）');
};
function pageChange() {
    $('input,textarea').keydown(function () {
        $(this).unbind('keydown');
        //unbindBeforeUnload();
        $(window).bind('beforeunload', function (event) {
            var message = '离开页面内容将会消失';
            event.returnValue = message;
            return message;
        });
    });
}
function unbindBeforeUnload() {
    $(window).unbind('beforeunload');
}

//如果奖品均匀发放复选框被选中
function initBecomeGrey() {
	if($('input[name=probabilityType]')[0].checked) {//开启
		$("input[name='globalProbability']").attr("disabled","disabled");
		$('#set_probabilityMode_box').css('color','grey');
		$('#set_probabilityMode_box input[type=radio]').attr('checked',false);
	}else {
		$('#set_probabilityType_box').css('color','grey');
	}
	ShowProbabilityInfo();
}

//取消奖品均匀发放的选中
function cancelProbabilityType() {
	$('#set_probabilityMode_box').css('color','#000');
	$('#globalProbability').removeAttr('disabled');
	$('#set_probabilityType_box').css('color','grey');
	$('#probabilityType').attr('checked',false);
	ShowProbabilityInfo();
}

//选择中奖概率模式或奖品均匀发放时出现文字提示信息
function ShowProbabilityInfo() {
	if($('#probabilityType').is(':checked')) {//开启
		$('#set_probabilityMode_box .probabilityInfo').css('color','#ff4c4c');//红
		$('#set_probabilityMode_box .probabilityInfo').text('（概率派奖已失效）')
		$('#set_probabilityType_box .probabilityInfo').css('color','#0099e5');//蓝
		$('#set_probabilityType_box .probabilityInfo').text('（按照均匀派奖）');	
	}else {//不开启
		$('#set_probabilityMode_box .probabilityInfo').css('color','#0099e5');//蓝
		$('#set_probabilityMode_box .probabilityInfo').text('（按照概率派奖）')
		$('#set_probabilityType_box .probabilityInfo').css('color','#ff4c4c');//红
		$('#set_probabilityType_box .probabilityInfo').text('（均匀发放已失效）');				
	}
}


(function () {

    initActivity();
    pageChange();    
    
    if (isEdit || isReCreate) {
        // 编辑活动内容
        load_activity(global_aid);        
        // 显示编辑标题
        $(".edit_title").show();       
    } else {

        // 初始化默认值
        // 初始化默认模板
        load_default_template();
        // 显示新建标题
        $(".add_title").show();
    }
    

})();
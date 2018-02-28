//获取游戏的window对象
	var reviewPageWin = null;
	var previewWinHasLoad = false;
	function getPreviewWin() {
		if (!reviewPageWin) {
			var reviewPageFrame = document.getElementById("gamePreviewPage");
			reviewPageWin = !reviewPageFrame ? window : reviewPageFrame.contentWindow;
		}
		return reviewPageWin;
	}

	function $$(selector) {
		if (typeof selector === 'function') {
			//if()
		}
		return getPreviewWin().$(selector);
	}
	function showTabByStyle(gameType) {
		// 默认是按抽奖模式
		gameType = gameType || 2;
		var $topBar = $('.topBar');
		var lastName = $('.topBar .column.checked .name').html();
		var $columnCont = $('.topBar .columnCont').html("");
		var columnConfig = [
			["加载页面", reviewPageWin.game._showFireLoading],
			["封面", reviewPageWin.game._showStartPage],
			["活动说明", reviewPageWin.game._showRule],
			["游戏环节", reviewPageWin.game._showGamePage],
			["我的奖品", reviewPageWin.game._showMyPrize],
			["奖品详情", reviewPageWin.game._showAwardInfo],
			["挑战成功", reviewPageWin.game._showFireSucc],
			["挑战失败", reviewPageWin.game._showFireFail],
			//["助力环节", reviewPageWin.game._showQrcodeEdit],
			["抽奖页面", reviewPageWin.game._showLottoPage],
			["中奖", reviewPageWin.game._showFirePrizeSucc],
			["未中奖", reviewPageWin.game._showFirePrizeFail]
		];
		  
		// 新版砍价活动特殊处理
		if (_isNewBargain) {
			columnConfig.splice(1, 1);
			columnConfig.splice(5, 6, ["助力环节", reviewPageWin.game._showQrcodeEdit]);
		}
		   
		// 幸运大转盘活动
		if (_isBigWheel) {
			columnConfig.splice(1, 1);
			columnConfig.splice(7, 1);
			columnConfig.splice(5, 2);
		}
		// 百分百大转盘活动
		if (_isAbsolutelyBigWheel) {
			columnConfig.splice(1, 1);
			// columnConfig.splice(7, 1);
			columnConfig.splice(5, 2);
		}
		  
		// 狂欢大抽奖
		if (_isExciteLottery) {
			columnConfig.splice(6, 3);
			columnConfig.splice(1, 1);
		}
		  
		// 3d大转盘
		if(_isThreeDPan){
			columnConfig.splice(1,1);
			columnConfig.splice(5,4);
		}
		 
		// 经典砍价
		if (_isClassicsBargain){
			columnConfig.splice(11, 1);
			columnConfig.splice(9, 1);
			columnConfig.splice(6, 2);
			columnConfig.splice(6, 2);
			columnConfig.splice(1, 1);
		}
		   
		if (_isFromHb) {
			// 红包活动统一隐藏奖品详情页签
			for (var ci = 0; ci < columnConfig.length; ci++) {
				if (columnConfig[ci][0] === "奖品详情") {
					columnConfig.splice(ci, 1);
					break;
				}
			}
		}
		var z_index = 20;
		for (var i = 0, n = columnConfig.length; i < n; i++) {
			var thisName = columnConfig[i][0];
			var checked = '';
			if (!lastName && i == 0) {
				checked = 'checked';
			} else if (lastName == thisName) {
				checked = 'checked';
			}
			$columnCont.append('<div class="column ' + checked + (i === 0 ? ' first-column' : '') + (i === n - 1 ? ' last-column' : '') + '" style="z-index:' + (z_index--) + '"><span class="name">' + columnConfig[i][0] + '</span></div>')
		}
		
		$topBar.off("click", ".column").on('click', '.column', function () {
          
			if ($(this).index() == 1 || $(this).index() == 2) {
				$('#awardDetailBox').hide();
			}
			$('#homeBgmoduleLayer').hide();
			columnConfig[$(this).index()][1]();
			$('.topBar .transitionPanel').css('left', ($(this).offset().left + 2) + 'px');
			$('.topBar .column').removeClass('checked');
			$(this).addClass('checked');
			if ($(this).find('.name').html() == '奖品详情' && !$('.tabSetting').eq(1).hasClass('checked')) {
				$('.tabSetting').eq(1).click();
			}
			if ($(this).find('.name').html() == '助力环节') {
				$('.preview-control').show();
			} else {
				$('.preview-control').hide();
			}
		});

		var column = $('.topBar .column');
		var columnNum = column.length;
		$('#changeRight').off("click").click(function () {
			var index = column.index($('.topBar .checked')) + 1;
			index = index >= columnNum ? 0 : index;
			column.eq(index).click();
		});
		$('#changeLeft').off("click").click(function () {
			var index = column.index($('.topBar .checked')) - 1;
			index = index < 0 ? (columnNum - 1) : index;
			column.eq(index).click();
		})
		if ($('.topBar .column.checked').length == 0) {
			$('.topBar .column').eq(0).click();
		}
	}

	// 初始化页面导航条
	$('#gamePreviewPage').load(function () {
		//获取游戏的window对象
		var reviewPageWin = getPreviewWin();
		previewWinHasLoad = true;
		//showTabByStyle();
	});
	$(function () {
		HdGame.initModuleLayer('#actName', 0, "actName", true);
		$("#configTab li").click(function () {
			var $li = $(this);
			var index = $li.index();
			if ($li.hasClass(".active")) {

			} else {
				var curIndex = $("#configTab li.active").index();
				var validFlag = true;
				$(".config_view").eq(curIndex).find("form").each(function (index, item) {
					// 特殊处理，被隐藏的奖项表单不校验
					if ($(item).is(".award_detail_form")) {
						var index = $(item).parent().index();
						if ($(".award_tab_item .prize_tab_item").eq(index).is(":hidden")) {
							return true;
						}
					}
					var valid = $(item).validate().form();
					if (!valid) {
						validFlag = false;
						return true;
					}
				});
				if (validFlag) {
					$("#configTab li.active").removeClass("active");
					$(".config_view").hide();
					$li.addClass("active")
					$(".config_view").eq(index).show();
				} else {
					alert("尚有信息未填写或填写错误，请先填写或修改");
				}
			}
		});
		$("#adv_subtab li").click(function () {
			var $li = $(this);
			var index = $li.index();
			console.log($li);
			console.log(index);
			console.log($li.hasClass('active'));
			if ($li.hasClass('active')) {
				
			} else {
				console.log($("#adv_subtab_content").find(".active").validate());
				//console.log($("#panel_4").find("form").validate());
				//var valid = $("#panel_4").find("form").validate().form();
				var valid = $("#adv_subtab_content").find(".active").validate().form();
				if (!valid) {
					alert("尚有信息未填写或填写错误，请先填写或修改");
					return false;
				}
			}
		});
		
	
		
	});
   
	$(function(){
		// 点击加载页面编辑查看示例
		$("#vExampleBtn").on("click",function(){
			$("#loadPage_example").show();
		});
		$("#loadPage_example").on("click",function(){
			$(this).hide();
		});
		//点击显示版权查看示例
        $("#copyRightExampleBtn").on("click",function(){
            $("#copyRight_example").show();
        });
        $("#copyRight_example").on("click",function(){
            $(this).hide();
        });
        //点击显示跳转示例
        $("#jumpPageExampleBtn").on("click",function(){
            $("#jumppage_example").show();
        });
        $("#jumppage_example").on("click",function(){
            $(this).hide();
        });

        //点击显示微信分享图标
		$('#wxShareIconExampleBtn').on('click',function(){
            $("#wxShareIcon_example").show();
		})
		$('#wxShareIcon_example').on('click',function(){
            $(this).hide();
		})
        //点击显示微信分享标题
        $('#wxShareTitleExampleBtn').on('click',function(){
            $("#wxShareTitle_example").show();
        })
        $('#wxShareTitle_example').on('click',function(){
            $(this).hide();
        })
		//点击显示微信分享内容
        $('#wxShareContentExampleBtn').on('click',function(){
            $("#wxShareContent_example").show();
        })
        $('#wxShareContent_example').on('click',function(){
            $(this).hide();
        })
	});
	
	
	// 上传图片
	/*
	*	upload_type : 1(默认图片)，2（音乐）
	*/
	function initFileUpload($fileUpload, sucCallback, upload_type){
		// 上传文件类型
		upload_type = upload_type ? upload_type : 1;

		$fileUpload.fileupload({
			dataType: 'json',
			progressall: function (e, data) {
				var $cont = $(this).closest(".cover-area");
				var progress = parseInt(data.loaded / data.total * 100, 10);
				var $progress = $cont.find(".fileupload-process");
				if ($progress.size() == 0) {
					$progress = $("<div class='fileupload-process progress-animated'><div class='bar' style='width: 0%;''></div></div>").appendTo($cont);
				}
				$progress.find(".bar").css(
						'width',
						progress + '%'
				);
			},
			change: function (e, data) {

				var flag = true;
				var $cont = $(this).closest(".cover-area");
				$.each(data.files, function (index, file) {
					var fileName = file.name;
					var fileSize = file.size;
					var $fileInput = $(data.fileInputClone[index]);
					var extStart = fileName.lastIndexOf(".");
					var ext = fileName.substring(extStart, fileName.length).toUpperCase();
					if( upload_type == 1 ){
						if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
							alert("图片限于bmp,png,gif,jpeg,jpg格式");
							flag = false;
							return false;
						}
					}else if( upload_type == 2 ){
						console.log(ext)
						if (ext != ".MP3" && ext != ".OGG" && ext != ".WAV") {
							alert("音乐限于mp3,ogg,wav格式");
							flag = false;
							return false;
						}
					}
					
					var sizeLimit = 200;
					if ($fileInput.data("sizelimit")) {
						sizeLimit = $fileInput.data("sizelimit");
					}
					if (fileSize > sizeLimit * 1024) {
						if( upload_type == 1 ){
							alert("图片大小不能超过" + sizeLimit + "KB");
						}else if( upload_type == 2 ){
							alert("音乐大小不能超过" + sizeLimit + "KB");
						}
						
						flag = false;
						return false;
					}
				});
				if (!flag) {
					return false;
				}
			},
			done: function (e, data) {
				console.log(data);
				var result = data._response.result._response.result;
				console.log(result);
				var $cont = $(this).closest(".cover-area");
				var $progress = $cont.find(".fileupload-process");
				if (result.error == "filetype" && upload_type == 1 ) {
					
					alert("只能上传图片格式：" + result.allowtype);
					$progress.remove();
					return false;
				}
									
				var $fileInput = $cont.find(".fileupload_input");
				
				// 加载页面图片编辑特殊处理
				if( upload_type == 1 ){
					// 图片
					if($fileInput.attr("data-target")){
						 $$($fileInput.attr("data-target")).css("background-image","url("+result.fileUrl+")");
					}
					console.log(result.fileUrl);
					$cont.find(".thumb_img").attr("src", result.fileUrl);
				}else if( upload_type == 2 ){
					// 音乐
					$cont.find(".music_name").html( result.fileUrl );
					$cont.find(".music_cover-recovery").show();
					$cont.find(".upload_music_1_1").val(result.fileUrl )
				}
				
				$cont.find(".huifuBtn").show();
				
				$cont.find(".cover-del").show();
				$cont.find(".upload_text").hide();
				$progress.remove();
				$fileInput.val(result.fileUrl);
				setJqSrc($$($cont.find(".cover-bd").data("from")), result.fileUrl);
					
				GameImgReady(result.fileUrl,function(){
					$$($cont.find(".cover-bd").data("from")).attr("data-rawwidth",this.width);
					$$($cont.find(".cover-bd").data("from")).attr("data-rawheight",this.height);
				});
				
				sucCallback && sucCallback(data, $cont);
			}
		});
	}
	$(function () {
		$("input[name='fileupload']").each(function (index, item) {
			var $fileUpload = $(item);

			if( $fileUpload.parents("#upload_music_modal").length > 0 ){
				initFileUpload($fileUpload,function(){},2);		
			}else{
				// 大转盘奖品图片上传
				if (_isBigWheel || _isAbsolutelyBigWheel) {
					initFileUpload($fileUpload, function (data, $cont) {
						$$("#prizeList").find("li").eq(index).find(".prize_img").attr("src", data._response.result.fileUrl);
					});

				} else{
					initFileUpload($fileUpload , function(data , $cont){
						window.uploadFileCallback && window.uploadFileCallback.call(this , data , $cont);
					});
				}
			}
	
		});
	});
	
	// 估价猎人上传商品图片特殊处理
	$(function(){
		
		$(".hunter_add_btn").hide();
		$(".hunter_delete_btn").hide();
		
		$(".hunter_add_btn").eq(0).show();
		
		$("#valuationHunter_closeBtn").on("click",function(){
			var li_1 = $(".hunter_ul").find("li").eq(0);
			$$(".vh_good").attr("src", li_1.find(".hunter_img").attr("src") );
			$$(".vh_tip2_1").text("以上物品价格为"+li_1.find(".hunter_minNum").val()+"~"+li_1.find(".hunter_maxNum").val()+"元" );
			$("#valuationHunter_goodsImg_modal").hide();
		});
		
		// 点击添加按钮
		$("#valuationHunter_goodsImg_modal").on("click",".hunter_add_btn", function(e){
			 e.stopPropagation();  
			$("#valuationHunter_goodsImg_modal").find("li").eq($(this).attr("data_show")).removeClass("hunter_li_hide");			
			resetBtn();
		});
		
		// 点击删除按钮
		$("#valuationHunter_goodsImg_modal").on("click",".hunter_delete_btn", function(e){
			 e.stopPropagation();  
			$(this).closest(".cover-area").addClass("hunter_li_hide");
			resetBtn();
		});
		
		// 监听输入框为空的情况
		$(".hunter_minNum").on("blur",function(){
			
			var parentEle = $(this).parents("li");
			
			if( $(this).val() == "" ){
				$(this).val(0);
			}else{
				var minNum = parseInt( $(this).val() );
				var maxNum = parseInt( parentEle.find(".hunter_maxNum").val() );
				if(  minNum >  maxNum ){
					alert("低区间的值不能高于高区间的值！");
					parentEle.find(".hunter_maxNum").val( ++minNum );
				}
			}
			
		});	
		$(".hunter_maxNum").on("blur",function(){
			
			var parentEle = $(this).parents("li");
			
			if( $(this).val() == "" ){
				$(this).val(0);
			}else{
				var maxNum = parseInt( $(this).val() );
				var minNum = parseInt( parentEle.find(".hunter_minNum").val() );
				if(  minNum >  maxNum ){
					alert("低区间的值不能高于高区间的值！");
					$(this).val( ++minNum );
				}
			}
		});
		
		function resetBtn(){
			$(".hunter_add_btn").hide();
			$(".hunter_delete_btn").hide();
			var v_length = $("#valuationHunter_goodsImg_modal").find("li").length-1;
			var v_length2 = $(".hunter_li_hide").length;
			$("#valuationHunter_goodsImg_modal").find("li").eq( v_length - v_length2 -1 ).find(".hunter_delete_btn").show();
					
			if( (v_length - v_length2) < 5 ){
				$("#valuationHunter_goodsImg_modal").find("li").eq( v_length - v_length2 -1 ).find(".hunter_add_btn").show();
			}
		}
		
	});
	
	window.skinId = "1";
	$(".skin_list li[data-id="+window.skinId+"]").addClass("selected");
	$(".skin_list li").click(function () {
		var skinId = $(this).data("id");
		window.skinId = skinId;
		if (isEdit) {
			window.location.href = "config.jsp?aid=" + global_aid + "&skinId=" + skinId;
		} else {
			window.location.href = "config.jsp?templateId=" + params.templateId + "&skinId=" + skinId;
		}
	});
	$("#sharetype_tooltip").popover({
		title: "什么是分享形式",
		trigger: "hover",
		content: "图片分享是保存图片后，以二维码的形式展示给好友，好友需要通过微信扫二维码的方式进入链接；图文分享是通过微信链接分享，好友可以直接点击分享的链接进入活动。"
	});
	$('#withhelp_tooltip').popover({
		title: "什么是助力",
		trigger: "hover",
		content: "助力是一种为活动参与者增加抽奖机会或积分的方式。活动参与者通过分享助力链接或者二维码，邀请好友为自己助力，好友通过点击邀请页面或者页面中的相关按钮完成助力，被助力者根据“被助力效果”的设置，增加抽奖次数或者游戏积分。"
	});
	$('#probabilityMode_tooltip').popover({
		title: "什么是单个奖项中奖概率？",
		trigger: "hover",
		content: "与总中奖概率不同，需要单独设置每个奖项的中奖概率。此时不中奖概率=100% - 所有奖项中奖概率之和。所有奖项中奖概率相加应不大于100%。"
	});
	$('#probabilityType_tooltip').popover({
		title: "什么是奖品均匀发放？",
		trigger: "hover",
		content: "设置总中奖概率后，易出现奖品发放速度过快、贵重奖品过早被抽走的情况。启用“奖品均匀发放”功能，将根据活动的持续时间和奖品数量调整粉丝抽中的奖品，可有效避免上述两种情况"
	});
	$('#gamescore_tooltip').popover({
		title: "什么是积分",
		html: true,
		trigger: "hover",
		content: "<p>积分高低是排名派奖模式中排名的依据，活动参与者获得积分的途径有两种</p><p>1、参与游戏，获得最高分</p><p>2、邀请好友助力，助力后获得相应积分</p><p>最终积分=参与者的最高游戏积分+好友助力积分</p><p>好友助力积分按助力时参与者的最高积分的百分比计算。</p><p>例如活动参与者当前最高积分为100分，助力积分按1%计算，那么当有一名好友为其助力时，这名活动参与者的排名积分为100+1=101分。</p>"
	}); 
 
	var user_balance = 0;
	var user_img_domain = localhostPath;
	var _resRoot = localhostPath+"/"+ "content/bigwheel/back/files/lot";
	var _isFreeUser = true;
	var _qiniuResRoot = localhostPath+"/";
	window.templateId = 34;
		// 免费用户提示文字
		if (_isFreeUser) {
			$(".nofree_tips").show();
		} else {
			$(".nofree_tips").hide();
		}
 
		// 新版砍价活动特殊处理
		if (_isNewBargain) {
			$("#set_participant_box").hide();
			$("#set_logo_box").hide();
			$("#set_copyright_box>.control-group").hide();
			$("#adv_subtab>li").eq(2).hide();
			$(".j-share-txt-box").eq(1).hide();
			$(".j-share-txt-box").eq(0).find(".share-txt-titel").text("分享内容");
			$(".j-share-txt-box").eq(0).find(".j-insert-tag").eq(1).hide();
			$("#singleUserAwards-help-inline").hide();
		}
		  
		if (_isBigWheel) {
			$("#set_awardSendType_box").find(".radio").eq(1).hide();
			$("#set_minscore_box,#set_withHelp_box,#other_cgroup,#game_setting_tab").hide();
		}
		// 特殊游戏不支持单项中奖概率
		if(_isQiangHuo || _isJiWuFu || _isNewBargain || _isGuangGun || _isWanSheng || _isZqJiZi || _isTruthOrDare){
			$("#set_probabilityMode_box .controls").eq(0).hide();
			$("#set_probabilityMode_box .controls").eq(1).css("margin-top","0");
		}
		// 特殊游戏不支持弹幕消息轮播
		if(_isYyttl || _isAbsolutelyBigWheel || _isBigWheel || _isKlhb || _isExciteLottery || _isNewShakeRedpack || _isXiuyixiu || _isNewZrhb || _isZcqc){
			$("#set_showdanmu_box").hide().find("input").attr("disabled","disabled");
		}
		if(_isFuDan){
	      $("#singleUserAwards-help-inline").hide();
	      $("#singleUserAwards-help-inline").siblings(".help-inline").hide();
	    }
		if(_isZcqc){
			$("#set_probabilityType_box").hide();
		}
 
		// 多选择省市
		var pageData = {
            addressList: []  // 地址数据
        };


        /*具体的操作*/
        $(function(){
        	
        	window.save_needArea = false;
        	window.save_addressList = null; // 回写数据
        	window.save_province = ""; // 省
            window.save_city = ""; // 市
        	
            /*****************地址交互组***************/
			
            if( window.save_pageData ){
            	 renderAddressList( pageData.addressList );
            	 $("#show_add_area_new").hide();
             	$("#show_view_area_list").show();
            }
            
            // 点击“增加区域限制”按钮
            $("#show_add_area_new").on("click", function(){
                $("#render_modal").modal("show");
                // 加载省
                $("#address-list-choose").html(getAddressOption("province"));
                renderAddressList( pageData.addressList );
            });
            
            // 点击修改区域限制按钮
            $("#show_view_area").on("click", function(){
                $("#render_modal").modal("show");
                // 加载省
                $("#address-list-choose").html(getAddressOption("province"));
                renderAddressList( pageData.addressList );
            });
            
            // 点击确认删除按钮
            $("#tip_btn_confirm").on("click",function(){
            	$("#j_modal-confirm").modal("hide");
            	window.save_needArea = false;
            	window.save_addressList = null; // 回写数据
            	 window.save_province = ""; // 省
                 window.save_city = ""; // 市
            	$("#show_add_area_new").show();
            	$("#show_view_area_list").hide();
				alert($(".address-text").text());
				$(".address-text").text("");
            });
            
         	// 点击取消删除按钮
            $("#tip_btn_close,#tip-btn-close2").on("click",function(){
            	$("#j_modal-confirm").modal("hide");
				renderAddressText(getAddressItems());
            })
            
            // 点击删除按钮
            $("#delete_view_area").on("click",function(){            	
            	$("#j_modal-confirm").modal("show");
				renderAddressText('');
            });

            // 点击更新地址
            $("#btn_add_area").on("click", function(){
                var newAddress = getAddressItems();
                if(newAddress.length && newAddress.length > 0){
                    pageData.addressList = newAddress;  // 这里就是最终的数据
                    
                    // 保存数据
                    window.save_needArea = true;
                    window.save_addressList = JSON.stringify( pageData.addressList );  // 回写数据
                    window.save_province = ""; // 省
                    window.save_city = ""; // 市
                    
                    for( var i=0; i<pageData.addressList.length; i++ ){
                    	  window.save_province += addressFullList[ pageData.addressList[i]["province"] ] + ",";
                    	  for( var j=0; j<pageData.addressList[i]["city"].length; j++ ){
                    		  window.save_city += addressFullList[ pageData.addressList[i]["city"][j] ] + ",";
                    	  }
                    }
                    console.log( window.save_province )
                    console.log( window.save_city )
                    console.log(  window.save_addressList );
                    console.log( JSON.parse( window.save_addressList ) );
                    
                    renderAddressText(newAddress);
                    $("#show_add_area_new").hide();
                    $("#show_view_area_list").show();
                    $("#render_modal").modal("hide");
                    
          
                }
            });


            // 展开省
            $("#address-list-choose").on("click", ".province", function(){
                var id = $(this).data("id");
                if($(this).find("ul").length > 0){
                    $(this).find(".check-icon-add").html("+");
                    $(this).find("ul").remove();
                }else{
                    $(this).find(".check-icon-add").html("&minus;");
                    $(this).append(getAddressOption("city",id));
                    $(this).find("ul").slideDown();
                }
            });

            // 展开省
            $("#address-list-choosed").on("click", ".province", function(){
                var state = $(this).find("ul").css("display");
                if(state == "none"){
                    $(this).find("ul").slideDown();
                    $(this).find(".check-icon-add").html("&minus;");
                }else{
                    $(this).find(".check-icon-add").html("+");
                    $(this).find("ul").hide();
                }
            });
            // 阻止点击市事件冒泡
            $("#address-list-choosed").on("click", ".wrap-city", function(){
                return false;
            });

            $("#address-list-choose").on("click", ".city", function(){
                return false;
            });

            // 选择省
            $("#address-list-choose").on("click", ".province>.check-icon", function(){
                var id = $(this).closest(".province").data("id");
                var data_add = getAddressList(id, "province");
                var newAddress = getAddressItems(data_add);
                renderAddressList(newAddress);
                return false;
            });

            // 控制选中效果
            $("#address-list-choose").on("mouseover", ".province", function(){
                if($(this).find("ul").length > 0){
                    $(this).css("backgroundColor", "#fff");
                }else{
                    $(this).css("backgroundColor", "#f5f6f8");
                }
                return false;
            });

            $("#address-list-choose").on("mouseout", ".province", function(){
                $(this).css("backgroundColor", "#fff");
                return false;
            });
            $("#address-list-choosed").on("mouseover", ".province", function(){
                if($(this).find("ul").css("display") == "none"){
                    $(this).css("backgroundColor", "#f5f6f8");
                }else{
                    $(this).css("backgroundColor", "#fff");
                }
                return false;
            });
            $("#address-list-choosed").on("mouseout", ".province", function(){
                $(this).css("backgroundColor", "#fff");
                return false;
            });

            // 选择市
            $("#address-list-choose").on("click", ".city>.check-icon", function(){
                var id = $(this).closest(".city").data("id");
                var province_id =  $(this).closest(".province").data("id");
                var data_add = getAddressList(id, "city", province_id);
                var newAddress = getAddressItems(data_add);
                renderAddressList(newAddress);
                return false;
            });
            // 删除省
            $("#address-list-choosed").on("click", ".province>.check-icon", function(){
                $(this).closest(".province").remove();
                return false;
            });

            // 删除市
            $("#address-list-choosed").on("click", ".city>.check-icon", function(){
                if($(this).closest(".wrap-city").find("li").length == 1){
                    $(this).closest(".province").remove();
                }else{
                    $(this).closest(".city").remove();
                }
                return false;
            });
        });

        // 一些函数
        // 根据数据进行渲染已选地址
        function renderAddressList (dataList){
            var data_list = dataList || {};
            var html_tpl = [];
            if(data_list.length > 0){
                html_tpl.push('<ul class="wrap-province" data-id="parent">');
                for(var dataIndex= 0;  dataIndex < data_list.length ; dataIndex++){
                    if(data_list[dataIndex].city.length == addressFullPro[data_list[dataIndex].province]){
                        html_tpl.push('<li class="province" data-id="'+ data_list[dataIndex].province +'"><span class="check-icon-add">+</span><span>'+ addressFullList[data_list[dataIndex].province] +'</span><span class="check-icon"></span>');
                        html_tpl.push('<ul class="wrap-city" style="display: none;">');
                    }else{
                        html_tpl.push('<li class="province" data-id="'+ data_list[dataIndex].province +'"><span class="check-icon-add">&minus;</span><span>'+ addressFullList[data_list[dataIndex].province] +'</span><span class="check-icon"></span>');
                        html_tpl.push('<ul class="wrap-city">');

                    }
                    for(var cityItem = 0;  cityItem < data_list[dataIndex].city.length; cityItem++){
                        html_tpl.push('<li class="city" data-id="'+ data_list[dataIndex].city[cityItem] +'"><span>'+ addressFullList[data_list[dataIndex].city[cityItem]] +'</span><span class="check-icon"></span></li>');
                    }
                    html_tpl.push('</ul>');
                    html_tpl.push('</li>');
                }
                html_tpl.push('</ul>');
                $("#address-list-choosed").html(html_tpl.join(""));
            }else{
                $("#address-list-choosed").html('<p class="address-empty-text">暂未选择推广区域</p>');
            }

        }

        // 渲染地址文本概览
        function renderAddressText (dataList){
            var data_list = dataList || [];
            var html_tpl = [];
            var html_tpl_pro = [];
            html_tpl_pro.push('<p class="address-text">');
            if(data_list.length > 0){
                for(var dataIndex= 0;  dataIndex < data_list.length ; dataIndex++){
                    if(data_list[dataIndex].city.length == addressFullPro[data_list[dataIndex].province]){
                        html_tpl_pro.push(addressFullList[data_list[dataIndex].province] + "&nbsp;");
                    }else{
                        html_tpl.push('<p class="address-text">'+ addressFullList[data_list[dataIndex].province] +'：&nbsp;');
                        for(var cityItem = 0;  cityItem < data_list[dataIndex].city.length; cityItem++){
                            if(cityItem == 0){
                                html_tpl.push(addressFullList[data_list[dataIndex].city[cityItem]]);
                            }else{
                                html_tpl.push('、' + addressFullList[data_list[dataIndex].city[cityItem]]);
                            }
                        }
                        html_tpl.push('</p>');
                    }
                }
            }
            html_tpl_pro.push('</p>');

            // 统一
            html_tpl_pro = html_tpl_pro.concat(html_tpl);
            $("#view_area_list").html(html_tpl_pro.join(""));

        }

        // 数组去重
        function uniqueList(data_list){
            var n = []; //一个新的临时数组
            // 为确保数据类型统一，先遍历一次转换类型
            for(var i = 0; i < data_list.length; i++) //遍历当前数组
            {
                data_list[i] = data_list[i] + "";
            }

            for(var i = 0; i < data_list.length; i++) //遍历当前数组
            {
                //如果当前数组的第i已经保存进了临时数组，那么跳过，
                //否则把当前项push到临时数组里面
                if (n.indexOf(data_list[i]) == -1) n.push(data_list[i]);
            }
            return n;
        };

        // 构造地址数组（接受新增数据）
        function getAddressItems (dataList){
            var $province = $("#address-list-choosed .province");
            var address_list = [];
            var _dataList = dataList || {}; // 默认传入新增的内容
            var address_type = "new";
            if($province.length > 0){
                $province.each(function(){
                    var id = $(this).data("id");
                    var $city = $(this).find(".city");
                    var city_list =[];
                    var province_object = {};

                    $city.each(function(){
                        var id = $(this).data("id") +"";
                        city_list.push(id);
                    });

                    if(_dataList.province && _dataList.province == id){
                        address_type = "province";
                        city_list = city_list.concat(_dataList.city);
                        city_list= uniqueList(city_list);
                    }
                    province_object.province = id;
                    province_object.city = city_list;

                    address_list.push(province_object);
                });
            }

            if(address_type == "new" && _dataList.province){
                address_list.push(_dataList);
            }
            return address_list;
        }	 
		 
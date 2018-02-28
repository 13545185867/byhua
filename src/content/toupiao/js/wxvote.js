doing = false;
var votesusalert = "提交成功";
var votedalert = "已"+votebtntxtinfo.btntxt;
var votebtntxt = votebtntxtinfo.btntxt;
var votenumtxt = votebtntxtinfo.unit;
var votemorebtntxt = "提 交 投 票";
var _lskey = "wxparam_"+guid;
var _lsnokey = "wxparamno_"+guid;


function getScrollTop()
{
    var scrollTop=0;
    if(document.documentElement&&document.documentElement.scrollTop)
    {
        scrollTop=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
        scrollTop=document.body.scrollTop;
    }
    return scrollTop;
}
function getClientHeight()
{
    var clientHeight=0;
    if(document.body.clientHeight&&document.documentElement.clientHeight)
    {
        var clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;        
    }
    else
    {
        var clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;    
    }
    return clientHeight;
}
function getScrollHeight()
{
    return Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
}

function getWxOptionBox(_option,i)
{
	var _div;
	if(optionViewStyle==1){
		_div = "<div id='wxop_"+_option.id+"' class='wxop ' style='width:100%;'>";
			_div += "<div class='wxopimg '>";
				_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"
				_div += "<img src='"+serverroot+"/upload/img/system/"+_option.image+"'  /></a>";
			_div += "</div>";
		
			_div += "<div class='wxoptxt'>";
			_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"+_option.identifier+". "+_option.title+"</a>";
			_div += "</div>";		
			

			_div += "<div class='wxopvotediv'>";
			if(wxsubmittype == 0)
				_div += "<div class='wxvbtn'><button class='btn btn-info wxvotebutton' "+" name='"+_option.id+"'><i class='icon-thumbs-up'></i> "+votebtntxt+"</button></div>";
			else
				
				_div += "<div class='wxopradio wxopraido"+_option.id+" newwxopradio '><input type='checkbox' name='opids[]' value='"+_option.id+"' class=''/></div>";
				
			 
			_div += "<div class='wxvinfo' id='wxinfo_"+_option.id+"'>";
				_div += "<span>"+_option.voteCount+"</span>"+votenumtxt;
			_div += "</div>";
			_div += "</div></div>";
						
	}
	
	if(optionViewStyle==2){
		if((parseInt(i)+1)%2 == 1){
			_div = "<div id='wxop_"+_option.id+"' class='wxop wxopcol1 '>";
				
			}else{
			_div = "<div id='wxop_"+_option.id+"' class='wxop wxopcol2 '>";
				
			}
			
			_div += "<div class='wxopimg '>";

			_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"
			_div += "<img src='"+serverroot+"/upload/img/system/"+_option.image+"'  /></a>";
		_div += "</div>";

		_div += "<div class='wxoptxt'>";
		_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"+_option.identifier+". "+_option.title+"</a>";
		_div += "</div>";		
		

		_div += "<div class='wxopvotediv'>";
		if(wxsubmittype == 0)
			_div += "<div class='wxvbtn'><button class='btn btn-info wxvotebutton' "+" name='"+_option.id+"'><i class='icon-thumbs-up'></i> "+votebtntxt+"</button></div>";
		else

		
		_div += "<div class='wxopradio wxopraido"+_option.id+" newwxopradio '><input type='checkbox' name='opids[]' value='"+_option.id+"' class=''/></div>";
		
		_div += "<div class='wxvinfo' id='wxinfo_"+_option.id+"'>";
			_div += "<span>"+_option.voteCount+"</span>"+votenumtxt;
		_div += "</div>";
		_div += "</div></div>";
			
			
	}
	
	if(optionViewStyle==3){
		if((parseInt(i)+1)%3 != 0){
		_div = "<div id='wxop_"+_option.id+"' class='wxop wxopcol3 '>";
			
		}else{
		_div = "<div id='wxop_"+_option.id+"' class='wxop wxopcol4 '>";
			
		}
		
		_div += "<div class='wxopimg '>";

		_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"
		_div += "<img src='"+serverroot+"/upload/img/system/"+_option.image+"'  /></a>";

	_div += "</div>";

	_div += "<div class='wxoptxt'>";
	_div += "<a href='"+serverroot+"/toupiao/detail.do?actId="+guid+"&id="+_option.id+"'>"+_option.identifier+". "+_option.title+"</a>";
	_div += "</div>";		
	

	_div += "<div class='wxopvotediv'>";
	if(wxsubmittype == 0)
		_div += "<div class='wxvbtn'><button class='btn btn-info wxvotebutton' "+" name='"+_option.id+"'><i class='icon-thumbs-up'></i> "+votebtntxt+"</button></div>";
	else
		
		_div += "<div class='wxopradio wxopraido"+_option.id+" newwxopradio '><input type='checkbox' name='opids[]' value='"+_option.id+"' class=''/></div>";
		
	_div += "<div class='wxvinfo' id='wxinfo_"+_option.id+"'>";
		_div += "<span>"+_option.voteCount+"</span>"+votenumtxt;
	_div += "</div>";
	_div += "</div></div>";
	}
	return _div;	

}

function showWxOptionListMore()
{
	if(loadpagedone)
		return false;
	if(loadingpage)
		return false;
	
	loadingpage = true;
	doing = true;
	wxpage += 1;
	$(".loadingpagealert").show();
	$("#loadmorebtn").hide();
	$.post(serverroot+"/toupiao/toShowOptionMore.do", {action:"toShowOptionMore.do",actId:guid,optionViewStyle:optionViewStyle,pageNo:wxpage},function(json){
        
		
		$(".loadingpagealert").hide();
		loadingpage = false;
        if(json.obj.length >= 1)
        {
        	for(i in json.obj)
        	{
        		optionpagenum= optionpagenum+1;
        		_opt = json.obj[i];
        		optdiv = getWxOptionBox(_opt,i);
        		$(".voteoplist").append(optdiv);
        	}
        	
        	
        	if(json.obj.length == 0)
        	{
        		$(".loadingpagealert").html("没有更多选手了");
        		$("#loadmorebtn").hide();
        	}
        	else
        	{
        		$(".loadingpagealert").hide();
        		$("#loadmorebtn").show();
        		initWxVoteBtnClk();
        	}
        	
        	if(json.obj.length< pagesize)
        	{
        		loadpagedone = true;
        		$("#loadmorebtn").hide();
        	}
        }
        doing = false;
		
      
  
	},"json");
}
function showWxOptionListMoreOnerow(_guid,limit)
{
	if(loadpagedone)
		return false;
	if(loadingpage)
		return false;
	
	loadingpage = true;
	doing = true;
	wxpage += 1;
	$(".loadingpagealert").show();
	$("#loadmorebtn").hide();
	$.post(serverroot+"op.php", {action:"loadvoteoptionbypageonerow",guid:_guid,page:wxpage,limit:limit,tpldiyfile:tpldiyfile},function(json){
        
		
		$(".loadingpagealert").hide();
		loadingpage = false;
        if(json.ret == 1)
        {
        	
        	
        	for(i in json.retinfo.list.left)
        	{
        		_opt = json.retinfo.list.left[i];
        		optdiv = getWxOptionBox(_opt);
        		$(".leftoptions").append(optdiv);
        		
        		if(i%3 ==2)
        		{
        			$("#wxop_"+_opt.opid).addClass("wxoplast");
        		}
        	}
        	
        	if(json.retinfo.list.num == 0)
        	{
        		$(".loadingpagealert").html("没有更多了");
        		$("#loadmorebtn").hide();
        	}
        	else
        	{
        		$(".loadingpagealert").hide();
        		$("#loadmorebtn").show();
        		initWxVoteBtnClk();
        	}
        	
        	if(json.retinfo.list.num < optionpagenum)
        	{
        		loadpagedone = true;
        		$("#loadmorebtn").hide();
        	}
        }
        doing = false;
		
      
  
	},"json");
}
function hideWapAlert()
{
	$(".wapalert").hide();
}
function showWapAlert(msg,autohide)
{
	$(".wapalert p").html(msg);
	$(".wapalert").show();
	if(autohide )
	{
		setTimeout ("hideWapAlert()",2000);
	}
}
function checkMoreSelNum(checkmin)
{
	_opinputselnum = $(".wxopradio input:checked").length;
	_mustminsel = minselectionnum==0?1:minselectionnum;
	
	if(wxsubmittype == 1)
	{
		
	
		if(_opinputselnum == 0 && checkmin)
		{
			
			showWapAlert(langs.failed + ","+ langs.dowxvotesmustselminnum.replace("{num}",_mustminsel).replace("{unit}",optionunitname),true);
			return false;
		}
		else
		{
			if(_mustminsel > 1 && _opinputselnum < _mustminsel &&  checkmin)
			{
				
				showWapAlert(langs.failed + ","+ langs.dowxvotesmustselminnum.replace("{num}",_mustminsel).replace("{unit}",optionunitname),true);
				return false;
			}
			
			if(wxeachsubmitnum > 0 && _opinputselnum>wxeachsubmitnum )
			{
				
				showWapAlert(langs.failed + ","+ langs.dowxvotesmustselmaxnum.replace("{num}",wxeachsubmitnum).replace("{unit}",optionunitname),true);
				return false;
			}
		}
	}
	if(wxsubmittype == 2)
	{
		if(_opinputselnum != wxeachsubmitnum )
		{
			showWapAlert(langs.dowxvotemustselecterror.replace("{num}",wxeachsubmitnum).replace("{unit}",optionunitname),true);
			return false;
		}
	}
	return true;
}
function setHadSelOpNum(selop,cktype)
{
	if(cktype == 'add')
		hadselnum += 1
	else
		hadselnum -= 1
	if(hadselnum<0)
		hadselnum = 0 ;
	
	if(wxsubmittype == 1)
	{
		if(wxeachsubmitnum > 0 && hadselnum>wxeachsubmitnum )
		{
			showWapAlert(langs.attention + ":"+ langs.dowxvotesmustselmaxnum.replace("{num}",wxeachsubmitnum).replace("{unit}",optionunitname),true);
			
		}
	}
	if(wxsubmittype == 2)
	{
		if(hadselnum>wxeachsubmitnum )
		{
			showWapAlert(langs.dowxvotemustselecterror.replace("{num}",wxeachsubmitnum).replace("{unit}",optionunitname),true);
		}
	}
	$(".hadselnumshow").html(hadselnum);
	if(hadselnum > 0)
	{
		$(".toprulebar").show();
	}
	
}
function initWxVoteBtnClk()
{
	if(wxsubmittype > 0)
	{
		$('.newwxopradio input').each(function(){
		    var self = $(this);
		      
		      self.iCheck({
		      checkboxClass: 'icheckbox_line-grey',
		      radioClass: 'iradio_line-grey',
		      insert: '<div class="icheck_line-icon"></div><span data-checked="'+checkedtxt+'"  data-unchecked="'+checktxt+'">' + checktxt +'</span>'
		    });
		  });
		
		
		
		
		$('.newwxopradio input').on('ifChecked', function(event){
			
			_span = $(this).parent().find("span");
			_span.html(_span.attr("data-checked"));
			
			setHadSelOpNum($(this),'add');
			
		});
		$('.newwxopradio input').on('ifUnchecked', function(event){
			_span = $(this).parent().find("span");
			_span.html(_span.attr("data-unchecked"));
			
			setHadSelOpNum($(this),'sub');
		});
		$('.newwxopradio').each(function(){
			  
		    $(this).removeClass("newwxopradio");
		})
	}
	
	$(".wxlapiaobutton").unbind("click");
	$(".wxvotebutton").unbind("click");
	$(".wxlapiaobutton").click(function(){
		$(".helphimwalert").show();
	});
	$(".wxvotebutton").click(function(){
		if(doing)
			return false;
		_clk = $(this);
		
		if(caninqq == 1)
		{
			if(isinqq == 0 && isinweixin == 0)
			{
				_clk.html(votebtntxt);
				return false;
			}
		}
		else
		{
			if(isinweixin == 0)
			{
				_clk.html("微信内"+votebtntxt);
				return false;
			}
		}
		if(expired == 1)
		{
			showWapAlert("活动已结束",true);
			return false;
		}
		
		
		wxparam = "";
		needinwx = 1 ;
		if($("#wxparam").is("input"))
		{
			wxparam = $("#wxparam").val();
			//alert(wxparam);
			needinwx = 1;
		}
		
		
		if(wxparam == "")
		{
			
			if(getByHash(_lskey))
			{
				//wxparam = getByHash(_lskey);
				if(guid == "d58ef00b-fe32-ca76-e969-48d38bef42fa")
				{
					//alert('请刷新页面');
					//return false;
				}
			}
			
		}
		
		//alert(getByHash(_lskey));
		
		//1只能在微信投票
		if(needinwx == 1)
		{
			if(isinweixin==1 && subscribe==0 && ismustfollow==1)
			{
				$(".dofollowalert").show();
				return false;
			}
			
		}
		//alert('encoded');
		$(this).html("提交中");
		if(wxvrcode == 1 )
		{
			//alert('gee');
			
			$(".geetestalert").show();
			$("#geetestbox h4").html("拖动下方滑块完成验证");
			geetestObj.refresh();
			
			geetestObj.onSuccess(function(){
				
				$("#geetestbox h4").html("正在提交....");

				gtdata = geetestObj.getValidate();
				ops = _clk.attr("name");
				param = {action:"dovote.do",actId:guid,ops:ops,openid:openid,jwid:jwid,subscribe:subscribe};
				param.geetest_challenge = gtdata.geetest_challenge;
				param.geetest_seccode = gtdata.geetest_seccode;
				param.geetest_validate = gtdata.geetest_validate;
				param.wxparam = wxparam;
				doing = true;
				$.post(serverroot+"/toupiao/dovote.do",param,function(json){
		            
					$(".geetestalert").hide();
					
					
					
		            if(json.ret == 1)
		            {
		            	doing = false;
		            	_nownum = parseInt($("#wxinfo_"+ops+" span").html());
		            	_newnum = _nownum + 1;
		            	showWapAlert(votesusalert,true);
		            	_clk.html(votedalert);
		            	$("#wxinfo_"+ops+" span").html(_newnum);
		            	
		            }
		            else
		            {
		            	doing = false;
		            	
		            	_clk.html("<i class='icon-thumbs-up''></i> "+votebtntxt);
		            	showWapAlert(json.retinfo.errormsg,true);
		            }
		           },"json");
			})
			
			return false;
		}
		
		else
		{
			
			ops = $(this).attr("name");
			param = {action:"dovote.do",actId:guid,ops:ops,openid:openid,jwid:jwid,subscribe:subscribe};
			param.wxparam = wxparam;
			doing = true;
			$.post(serverroot+"/toupiao/dovote.do",param,function(json){
	            
				 if(json.success == true)
	            {
	            	doing = false;
	            	//hidevoteshow();
	            	//submitalert(langs.success,langs.dovoteok+" "+langs.jumping,"success");
	            	//setTimeout("window.location.href='"+json.retinfo.votelink+"?ref="+Math.random()+"#viewvote'",1000);
	            	
	            	//setTimeout("window.location.href='"+json.retinfo.votelink+"'",1000);
	            	
	            	//alert('sus');
	            	_nownum = parseInt($("#wxinfo_"+ops+" span").html());
	            	_newnum = _nownum + 1;
	            	showWapAlert(votesusalert,true);
	            	_clk.html(votedalert);
	            	$("#wxinfo_"+ops+" span").html(_newnum);
	            	
	            }
	            else
	            {
	            	doing = false;
	            	//alert(json.retinfo.errormsg);
	            	_clk.html("<i class='icon-thumbs-up''></i> "+votebtntxt);
	            	showWapAlert(json.msg,true);
	            	//if(json.retinfo.errorcode == "1090909")
	            	//{
	            		//window.location.href='/action/fixwxvote.html';
	            	//}
	            }
	           },"json");
			
		}
		
		

	})
}

function loadWXUserInfo()
{
	
}

$(document).ready(function(){
	
	
	wxparam_init = $("#wxparam").val();
	
	
	
		if(wxparam_init != "" && (getByHash(_lskey) == null || getByHash(_lskey) == false || getByHash(_lskey)!=wxparam_init  ))
		{
			if(ismustfollow == 1)
				setByHash(_lskey,wxparam_init);
			
		}
	
	
	//alert(wxparam_init);
	
	$("#cancelfollowalert").click(function(){
		
		$(".dofollowalert").hide();
		return false;
		
	})
	if($("#optionbypage").is("input"))
	{
		if($("#optionbypage").val() == 1)
		{
			
			
		}
	}
	
	if($("#showvotedescinfo").is("div"))
	{
		$("#showvotedescinfo").click(function(){
			
			if($("#votedescinfo").css("display") == "none")
			{
				$("#votedescinfo").show();
				$("#showvotedescinfo .arrow").removeClass("icon-double-angle-down");
				$("#showvotedescinfo .arrow").addClass("icon-double-angle-up");
			}
			else
			{
				$("#votedescinfo").hide();
				$("#showvotedescinfo .arrow").removeClass("icon-double-angle-up");
				$("#showvotedescinfo .arrow").addClass("icon-double-angle-down");
			}
			
		})
	}
	
	initWxVoteBtnClk();
	
	
	if(wxsubmittype > 0)
	{
		if($('.wxopradio input').length>0)
		{
			
			$('.wxopradio input').each(function(){
			    var self = $(this);
			      /*
			      label_unchecked= self.next();
			      label_unchecked_text = label_unchecked.text();
			      label_checked= label_unchecked.next();
			      label_checked_text = label_checked.text();
			      label_unchecked.remove();
			      label_checked.remove();
			      */
			      self.iCheck({
			      checkboxClass: 'icheckbox_line-grey',
			      radioClass: 'iradio_line-grey',
			      insert: '<div class="icheck_line-icon"></div><span data-checked="'+checkedtxt+'"  data-unchecked="'+checktxt+'">' + checktxt +'</span>'
			    });
			  });
			
			
			$('.wxopradio input').on('ifChecked', function(event){
				
				_span = $(this).parent().find("span");
				_span.html(_span.attr("data-checked"));
				
				
				setHadSelOpNum($(this),'add');
			});
			$('.wxopradio input').on('ifUnchecked', function(event){
				_span = $(this).parent().find("span");
				_span.html(_span.attr("data-unchecked"));
				
				
				setHadSelOpNum($(this),'sub');
			});
			
		}
		
		
		$("#wxmoreselsubmitbtn").click(function(){
			_clk = $(this);
			
			if(doing)
				return false;
			
			if(caninqq == 1)
			{
				if(isinqq == 0 && isinweixin == 0)
				{
					_clk.html(votemorebtntxt);
					showWapAlert("只能在微信或手机QQ内提交",true);
					return false;
				}
			}
			else
			{
				if(isinweixin == 0)
				{
					_clk.html(votemorebtntxt);
					showWapAlert("只能在微信内提交",true);
					return false;
				}
			}
			
			
			if(expired == 1)
			{
				showWapAlert("活动已结束",true);
				return false;
			}
			
			wxparam = "";
			needinwx = 1 ;
			if($("#wxparam").is("input"))
			{
				wxparam = $("#wxparam").val();
				
				needinwx = 1;
			}
			/* 只在微信 needinwx1*/
			if(needinwx == 1)
			{
				if(isinweixin==1 && subscribe==0 && ismustfollow==1)
				{
					$(".dofollowalert").show();
					return false;
				}
				
			}
			
			if(!checkMoreSelNum(true))
			{
				return false;
			}
			
			ops = "";
			$(".wxopradio input:checked").each(function(){
				ops += ops==""?$(this).val():"|"+$(this).val();
				
			})
			_clk = $(this);
			param = {action:"dovote.do",actId:guid,ops:ops,openid:openid,jwid:jwid,subscribe:subscribe};
			param.wxparam = wxparam;
			doing = true;
			$(this).html("提交中");
			_clk = $(this);
			$.post(serverroot+"/toupiao/dovote.do",param,function(json){
				
				doing = false;
	            if(json.success == true)
	            {
	            	
	            	showWapAlert(votesusalert,true);
	            	_clk.html(votedalert);
	            	
	            }
	            else
	            {

	            	showWapAlert(json.msg,true);
	            	_clk.html(votemorebtntxt);
	            	
	            }
	           },"json");
			
			
		})
	}
	
	$(".giftbutton").click(function(){
		
		_opguid = $(this).attr("name");
		window.location.href="/action/viewwxsendgift.html?guid="+_opguid;
		$(this).html("正在加载");
		return false;
		
	})
	
	$(".giftpagelist li").click(function(){
		
		_clk = $(this);
		
		
		if(expired == 1)
		{
			$("#dobuygift").html("活动已结束");
			$("#dobuygift").addClass("disabled");
		}
		$(".buygiftconfirmboxbg").show();
		
		$(".onegiftbox .giftname").html(_clk.find(".name").html());
		$(".onegiftbox img").attr("src",_clk.find("img").attr("src"));
		$(".onegiftbox .giftpointinfo").html(_clk.find(".point").html());
		
		$(".buygiftconfirmboxbg #addp").html(_clk.find(".point").attr("name"));
		
		$(".priceinfo span").html(_clk.attr("name"));
		_giftmid = $(this).attr("id");
		
		$("#dobuygift").unbind("click");
		$("#dobuygift").click(function(){
				
				_thisclk = $(this);
				if(expired == 1)
				{
					_thisclk.html("活动已结束");
					_thisclk.addClass("disabled");
					//return false;
				}
				if(doing )
					return false;
				
				if(openid == "")
				{
					showWapAlert("请在微信中访问",true);
					return false;
					
				}
				_clkhtml = $(this).html();
				_thisclk.html("正在生成订单，请稍后...");
				doing = true;
				param = {};
				param.openid = openid;
				param.openidtime = openidtime;
				param.openidhash = openidhash;
				param.giftmid = _giftmid;
				param.guid = guid;
				param.guidobj = guidobj;
				param.action = "dobuygift";
				$.post("/op.php",param,function(json){
				
				
	            if(json.ret == 1)
	            {
	            	_thisclk.html("订单创建成功，正在跳转...");
	            	//alert(json.retinfo.url);
	            	window.location.href = json.retinfo.url;
	            	
	            }
	            else
	            {
	            	doing = false;
	            	//alert(json.retinfo.errormsg)
	            	showWapAlert(json.retinfo.errormsg,true);
	            	_thisclk.html(_clkhtml);
	            	
	            }
	           },"json");
				
				return false;
			
		})
		
	})
	$(".buygiftconfirmboxbg #closegiftbox").click(function(){
		
		$(".buygiftconfirmboxbg").hide();
		
	})
	if(expired == 0)
	{
		try{
			
			/*
			var skt = io.connect('http://'+notifyserver);
			skt.emit("register",{guid:guid,ghash:ghash});
			skt.on('updatevotenum',function(data){
				
				$("#wxallvotenum").html(data.votenum);
				$("#wxallviewnum").html(data.views);
				
				_newopnum = parseInt(data.opnum);
				_nowopnum = parseInt($("#wxinfo_"+data.opid+" span").html());
				if(_newopnum > _nowopnum)
				{
					$("#wxinfo_"+data.opid+" span").html(data.opnum);
					$("#wxinfo_"+data.opid+" span").css({color:"red"});
					$("#wxinfo_"+data.opid).animate({"font-size":"20px"});
					$("#wxinfo_"+data.opid).animate({"font-size":"14px"});
					
				}
				//console.log(data);
			})
			*/
			
		}
		catch(err)
		{
			
		}
	}
	
})
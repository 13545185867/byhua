function checkCreateRegForm()
{
	
	_title = $("#inputTitle").val();
	
	
	
	if(_title == "")
	{
		showFormError("#inputTitleDiv",langs.formsurpleasefill+langs.registrationformtitle);
		submitalertnoclearformerror("",langs.formsurpleasefill+langs.registrationformtitle,"error");
		
		
	    return false;
	}
	
	if($("#regformdonecontent").val() == "")
	{
		showFormError("#inputDoneContentDiv",langs.formsurpleasefill+langs.regformdonecontent);
		submitalertnoclearformerror("",langs.formsurpleasefill+langs.regformdonecontent,"error");
		
		return false;
	}
	
	
	if($(".diyfields:checked").length>0)
	{
		needdiyerror = false;
		$(".diyfields:checked").each(function(){
			
			_selkey = $(this).val();
			//alert(_selkey);
			_diyname = $("#ufdn_"+_selkey).val();
			//alert(_diyname);
			if(_diyname == "")
			{
				
				
				needdiyerror = true;
				
			}
		})
		
		if(needdiyerror)
		{
			submitalertnoclearformerror("",langs.needtitlefordiyfield,"error");
			return false;
		}
	}
	submitalert("",langs.votesaveing,"warning");
	//$("#createRegForm").submit();
	return true;
}
function checkDoRegistrationForm()
{
	_return = false;
	
	if(typeof(showvrcode) == "undefined")
	{
		showvrcode = 1;
	}
	
	$(".userinfofield").each(function(){
		
		_id = $(this).attr("id");
		_need =$(this).attr("title");
		_key = _id.substring(3,_id.length);
		_val = "";
		
		if($(this).attr("type") == "radio")
		{
			_val = $("."+_id+":checked").val();
		}
		else
		{
			_val = $(this).val();
		}
		if(_need == 1 && (_val == ""|| _val == undefined || _val == "undefined" ))
		{
			
			showFormError("#input"+_key+"Div",langs.pleaseinput+langs[_key]);
			_return = true;
			
		}
		
		
			
		
	})
	if(_return)
	{
		return false;
	}
	
	if(showvrcode == 1)
	{
		_captchacode = $("#inputCaptchacode").val();
		if(_captchacode.length < QRCODELENGTH)
		{
			$("#inputCaptchacode").val('');
			clearFormError();
			showFormError("#inputCaptchacodeDiv",langs.captchacodeholder);
			return false;
		}
	}
	submitalert("",langs.doingwarning,"warning");
	
}
$(document).ready(function(){
	
	if($("#inputExpairtime").is("input"))
	{
		$('.form_datetime').datetimepicker({
			language: langs.datepickerlang,
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1,
	        pickerPosition: "top-left"
	        
	       
	    });
		
	}

	$("#regdonebtn").click(function(){
		
		window.close();
		
	})
	
	try
	{
		$('#copyregformurl').zclip({
			path:'/js/ZeroClipboard.swf',
			copy:$('#regformurl').val(),
			afterCopy:function(){ 	
				$("#regformurl").jAlert(langs.copyvoteurldone,"success");
			}
		});
			
	}
	catch(err)
	{
		
	}
	
})
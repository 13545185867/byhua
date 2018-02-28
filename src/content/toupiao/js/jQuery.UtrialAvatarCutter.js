jQuery.UtrialAvatarCutter = function(){
	
	var api = null;

	var sel = this;

	this.reload = function(img_url){
		sel.init();
	}
	
	this.init = function(){
		if(api!=null){
			api.destroy();
		}
	
	api = $.Jcrop('#pic_view',{ 
			onChange : showPreview,
			onSelect : showPreview,
			aspectRatio :55/66,
			allowResize : true,
			maxSize:			[ 550, 660 ],
			minSize:				[ 550, 660 ]
		});
	
	}
	
	var showPreview =	function(coords)
{
	var rx = 550 / coords.w;
	var ry = 660 / coords.h;
	var ow = $("#pic_view").width();
	var oh = $("#pic_view").height();
	
	
	$('#preview').css({
		width: 	Math.round(rx * ow) + 'px',
		height: Math.round(ry * oh) + 'px',
		marginLeft: '-' + Math.round(rx * coords.x) + 'px',
		marginTop: '-' + Math.round(ry * coords.y) + 'px'
	});
	$("#w").attr("value",Math.round(rx * ow));
	$("#h").attr("value",Math.round(ry * oh));
	$("#x").attr("value",Math.round(rx * coords.x));
	$("#y").attr("value",Math.round(ry * coords.y));
	
};
	
}
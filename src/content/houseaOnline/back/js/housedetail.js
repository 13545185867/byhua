  new Vue({
    el: '#carousel',
    data: {
        slideList:slideList1,
        currentIndex: 0,
        timer: '',
        payconfirm:false,
        status:status1,
        followcount:followcount1,
        id:id,
        vipuser:vipuser1,
        none:"none",
        isfollow:isfollow1,
        loading:false,
        iskaipan:false,
        djs:""
    },
    methods: {
    	ConfirmBuy() {
    		if(this.vipuser){
    			this.none = "";
    			this.payconfirm=true;
    		}else{
    			alert("非VIP会员用户无选房资格！");
    		}
    		
        },
        dopay(a) {
        	if(a==0){
    			this.payconfirm=false;
    		}
    		if(a==1){
    			if(status!=0 || status!=3 ){
    			 window.location.href=serverroot+'/houseaOnline/doPay.html?id='+this.id;
    			} 
    		}
        },
        doFollow(){
        	var type;
        	this.loading = true;
	        if(this.isfollow){
		     	type = 1;
	        }else{
	        	type = 0;
	        }
	            var _this = this;
				$.ajax({
					url: serverroot+'/houseaOnline/doFollow.html',
					dataType: "text",
					data: {'resourceid':_this.id,'optype':type,'resourcename':resourcename,'type':resourcetype},
					success: function(data){
					 console.log(data);
						var data = JSON.parse(data);
					   if(data.success){
						 	if(type == 0){
					   			_this.followcount = _this.followcount +1;
					   		}else{
					   			_this.followcount = _this.followcount -1;
					   		}
						   _this.isfollow = !_this.isfollow;
					   } else {
						  alert("出错啦！");
					   }
					   	_this.loading = false;
					}
				});
		    
        },
        go() {
            this.timer = setInterval(() => {
                this.autoPlay()
            }, 4000)
        },
        stop() {
            clearInterval(this.timer)
            this.timer = null
        },
        change(index) {
            this.currentIndex = index
        },
        autoPlay() {
            this.currentIndex++
            if (this.currentIndex > this.slideList.length - 1) {
                this.currentIndex = 0
            }
        },
        ShowCountDown(kpdate,timedif) 
        { 
	        var now = new Date(); 
	        var leftTime=kpdate-now.getTime()+timedif; 
	        var leftsecond = parseInt(leftTime/1000); 
	        var day1=Math.floor(leftsecond/(60*60*24)); 
	        var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	        var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	        var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
	        if(leftsecond <=0){ 
		        this.iskaipan=true;
		        return false;
	        }else{
		        this.djs = day1+"天"+hour+"小时"+minute+"分"+second+"秒"+"后开抢"; 
	        }

        } 
    },
    created() {
        this.$nextTick(() => {
            this.timer = setInterval(() => {
                this.autoPlay()
            }, 4000)
        })
        if((kaipandate-(new Date()).getTime() + (clientdate-serverdate))>0){
        	 setInterval(() => {
                 this.ShowCountDown(kaipandate,(clientdate-serverdate))
             }, 1000)
        }else{
        	this.iskaipan=true;
        }
    }
});

  new Vue({
    el: '#carousel',
    data: {
        slideList:slideList,
        currentIndex: 0,
        timer: '',
        pagehxInfos:pagehxInfos,
        isfollow:isfollow1,
        followcount:followcount1,
        loading:false
    },
    methods: {
        go() {
            this.timer = setInterval(() => {
                this.autoPlay()
            }, 4000)
        },
        stop() {
            clearInterval(this.timer)
            this.timer = null
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
					data: {'resourceid':id,'optype':type,'resourcename':resourcename,'type':resourcetype},
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
					}
				});
	        this.loading = false;
        },
        change(index) {
            this.currentIndex = index
        },
        autoPlay() {
            this.currentIndex++
            if (this.currentIndex > this.slideList.length - 1) {
                this.currentIndex = 0
            }
        }
    },
    created() {
        this.$nextTick(() => {
            this.timer = setInterval(() => {
                this.autoPlay()
            }, 4000)
        })
    }
});
